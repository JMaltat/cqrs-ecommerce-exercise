package com.margo.sample.cqrs.ecommerce;


import com.margo.sample.cqrs.ecommerce.commands.AddProduct;
import com.margo.sample.cqrs.ecommerce.commands.Command;
import com.margo.sample.cqrs.ecommerce.events.Event;
import com.margo.sample.cqrs.ecommerce.events.ProductAdded;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class BasketTest {
	private static final EventStore eventStore = new EventStore();
	private final Basket basket = new Basket(eventStore);

	@Before
	public void setup(){
		eventStore.clear();
	}

	@AfterClass
	public static void clear() {
		eventStore.clear();
	}

	@Test
	public void should_add_product() {
		AddProduct addProduct = AddProduct.builder()
				.withProductId(UUID.randomUUID().toString())
				.withQuantity(40)
				.build();
		Command<AddProduct> command = Command.builderFor(AddProduct.class)
				.withAggregateId(UUID.randomUUID().toString())
				.withData(addProduct)
				.build();

		basket.addProduct(command);
		List<Event> history = eventStore.getHistory(command.getAggregateId());
		assertThat(history).hasSize(1);
		Event<ProductAdded> event = history.get(0);
		assertThat(event.getData()).isInstanceOf(ProductAdded.class);
		assertThat(event.getAggregateId()).isEqualTo(command.getAggregateId());
		assertThat(event.getCommandId()).isEqualTo(command.getId());
		assertThat(event.getType()).isEqualTo(ProductAdded.class);
		ProductAdded productAdded = event.getData();
		assertThat(productAdded.getProductId()).isEqualTo(addProduct.getProductId());
		assertThat(productAdded.getQuantity()).isEqualTo(addProduct.getQuantity());
	}

	@Test
	public void should_remove_product() {
	}

	@Test
	public void should_be_validated() {
	}

}