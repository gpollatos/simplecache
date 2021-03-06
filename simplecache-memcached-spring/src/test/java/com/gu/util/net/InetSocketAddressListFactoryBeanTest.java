package com.gu.util.net;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import org.junit.Test;

import java.net.InetSocketAddress;
import java.util.List;

public class InetSocketAddressListFactoryBeanTest {

	@Test
	public void shouldCreateAddressesListFromString() throws Exception {
		String addresses = "depression.int.gnl:999, despair.int.gnl:666, inferiority.int.gnl:8888";

		List<InetSocketAddress> list = getAddressesFromFactory(addresses);
        assertThat(list, hasItems(new InetSocketAddress("depression.int.gnl", 999),
				new InetSocketAddress("despair.int.gnl", 666),
				new InetSocketAddress("inferiority.int.gnl", 8888)));
        assertThat(list.size(), equalTo(3));

	}

    @Test
	public void shouldGiveAConsistentlyOrderedListWhateverOrderAddessesAreInTheInputList() throws Exception {
		String addresses = "depression.int.gnl:999, despair.int.gnl:666, inferiority.int.gnl:8888";
		String reorderedAddresses = "despair.int.gnl:666, depression.int.gnl:999, inferiority.int.gnl:8888";

		List<InetSocketAddress> listFromAddresses = getAddressesFromFactory(addresses);
		List<InetSocketAddress> listFromReorderedAddresses = getAddressesFromFactory(reorderedAddresses);

        for (int i = 0; i < listFromAddresses.size(); i++) {
            assertThat(listFromAddresses.get(i), equalTo(listFromReorderedAddresses.get(i)));
        }

	}

	@SuppressWarnings("unchecked")
	private List<InetSocketAddress> getAddressesFromFactory(String addresses) throws Exception {
		InetSocketAddressListFactoryBean factory = new InetSocketAddressListFactoryBean(addresses);
		factory.afterPropertiesSet();

        return (List<InetSocketAddress>) factory.getObject();
	}
}
