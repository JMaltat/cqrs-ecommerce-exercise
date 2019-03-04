package com.margo.sample.cqrs.ecommerce.events;

import java.time.Instant;
import java.util.UUID;

public class Event<T> {
	private final UUID id;
	private final String aggregateId;
	private final Instant publishedTime;
	private final Class<T> type;
	private final T data;
	private final UUID commandId;

	private Event(final UUID id,
				  final String aggregateId,
				  final Instant publishedTime,
				  final Class<T> type,
				  final T data,
				  final UUID commandId) {
		this.id = id;
		this.aggregateId = aggregateId;
		this.publishedTime = publishedTime;
		this.type = type;
		this.data = data;
		this.commandId = commandId;
	}

	public UUID getId() {
		return id;
	}

	public String getAggregateId() {
		return aggregateId;
	}

	public Instant getPublishedTime() {
		return publishedTime;
	}

	public Class<T> getType() {
		return type;
	}

	public T getData() {
		return data;
	}

	public UUID getCommandId() {
		return commandId;
	}

	public static <T> Builder<T> builderFor(final Class<T> type) {
		return new Builder<>(type);
	}


	public static final class Builder<T> {
		private final UUID id = UUID.randomUUID();
		private String aggregateId;
		private UUID commandId;
		private final Instant publishedTime = Instant.now();
		private final Class<T> type;
		private T data;

		private Builder(final Class<T> type) {
			this.type = type;
		}

		public Builder<T> withAggregateId(String aggregateId) {
			this.aggregateId = aggregateId;
			return this;
		}


		public Builder<T> withCommandId(final UUID commandId) {
			this.commandId = commandId;
			return this;
		}


		public Builder withData(T data) {
			this.data = data;
			return this;
		}

		public Event<T> build() {
			return new Event<>(id, aggregateId, publishedTime, type, data, commandId);
		}
	}
}
