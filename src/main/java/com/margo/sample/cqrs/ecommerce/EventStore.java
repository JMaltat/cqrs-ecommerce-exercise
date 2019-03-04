package com.margo.sample.cqrs.ecommerce;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multimap;
import com.margo.sample.cqrs.ecommerce.events.Event;

import java.util.List;

public class EventStore {
	private final Multimap<String, Event> aggregateEvents = ArrayListMultimap.create();

	void save(final Event event) {
		aggregateEvents.put(event.getAggregateId(), event);
	}

	List<Event> getHistory(final String aggregateId) {
		return ImmutableList.copyOf(aggregateEvents.get(aggregateId));
	}

	@VisibleForTesting
	void clear(){
		aggregateEvents.clear();
	}
}
