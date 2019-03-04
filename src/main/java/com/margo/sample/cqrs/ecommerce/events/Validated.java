package com.margo.sample.cqrs.ecommerce.events;

import java.util.Map;

public class Validated {
	private final Map<String, Integer> productsQuantity;

	private Validated(final Map<String, Integer> productsQuantity) {
		this.productsQuantity = productsQuantity;
	}

	public Map<String, Integer> getProductsQuantity() {
		return productsQuantity;
	}

	public static Builder builder() {
		return new Builder();
	}


	public static final class Builder {
		private Map<String, Integer> productsQuantity;

		private Builder() {
		}

		public Builder withProductsQuantity(Map<String, Integer> productsQuantity) {
			this.productsQuantity = productsQuantity;
			return this;
		}

		public Validated build() {
			return new Validated(productsQuantity);
		}
	}
}
