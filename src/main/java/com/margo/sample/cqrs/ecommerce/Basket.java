package com.margo.sample.cqrs.ecommerce;

import com.google.common.collect.Maps;
import com.margo.sample.cqrs.ecommerce.commands.AddProduct;
import com.margo.sample.cqrs.ecommerce.commands.Command;
import com.margo.sample.cqrs.ecommerce.commands.RemoveProduct;
import com.margo.sample.cqrs.ecommerce.commands.Validate;
import com.margo.sample.cqrs.ecommerce.events.Event;
import com.margo.sample.cqrs.ecommerce.events.ProductAdded;

import java.util.List;
import java.util.Map;

public class Basket {
	private final EventStore eventStore;

	Basket(final EventStore eventStore) {
		this.eventStore = eventStore;
	}


	void addProduct(final Command<AddProduct> addProduct) {
		Event event = Event.builderFor(ProductAdded.class)
				.withAggregateId(addProduct.getAggregateId())
				.withCommandId(addProduct.getId())
				.withData(ProductAdded.builder()
						.withProductId(addProduct.getData().getProductId())
						.withQuantity(addProduct.getData().getQuantity())
						.build())
				.build();
		eventStore.save(event);
	}

	void removeProduct(final Command<RemoveProduct> removeProduct) {

	}

	void validate(final Command<Validate> validate) {
		Decision decision = new Decision(eventStore.getHistory(validate.getAggregateId()));
		if (!decision.canValidate()){
			//store reject event
			return;
		}
		// store validated event
	}

	class Decision {
		private Map<String, Integer> quantityByProduct = Maps.newConcurrentMap();

		private Decision(final List<Event> history){
			history.forEach(this::apply);
		}

		void apply(Event event){
			if (event.getType().equals(ProductAdded.class)){
				ProductAdded data = (ProductAdded) event.getData();
				quantityByProduct.compute(data.getProductId(), (key, value) -> value != null ? value + data.getQuantity() : data.getQuantity());
			}
		}

		boolean canValidate(){
			return !quantityByProduct.isEmpty();
		}
	}

}
