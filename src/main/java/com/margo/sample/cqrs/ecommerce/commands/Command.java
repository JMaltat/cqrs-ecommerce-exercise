package com.margo.sample.cqrs.ecommerce.commands;

import java.time.Instant;
import java.util.UUID;

public class Command<T> {
	private final UUID id;
	private final String aggregateId;
	private final Instant publishedTime;
	private final Class<T> type;
	private final T data;

	private Command(final UUID id,
					final String aggregateId,
					final Instant publishedTime,
					final Class<T> type,
					final T data) {
		this.id = id;
		this.aggregateId = aggregateId;
		this.publishedTime = publishedTime;
		this.type = type;
		this.data = data;
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

	public static <T> Builder<T> builderFor(final Class<T> type) {
		return new Builder<>(type);
	}


	public static final class Builder<T> {
		private final UUID id = UUID.randomUUID();
		private String aggregateId;
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

		public Builder<T> withData(T data) {
			this.data = data;
			return this;
		}

		public Command<T> build() {
			return new Command<>(id, aggregateId, publishedTime, type, data);
		}
	}

}
