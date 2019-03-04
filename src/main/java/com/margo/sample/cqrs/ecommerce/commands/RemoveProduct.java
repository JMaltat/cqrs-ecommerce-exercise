package com.margo.sample.cqrs.ecommerce.commands;

public class RemoveProduct {
	private final String productId;
	private final int quantity;

	private RemoveProduct(final String productId, final int quantity) {
		this.productId = productId;
		this.quantity = quantity;
	}

	public String getProductId() {
		return productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public static final Builder builder() {
		return new Builder();
	}


	public static final class Builder {
		private String productId;
		private int quantity;

		private Builder() {
		}

		public Builder withProductId(String productId) {
			this.productId = productId;
			return this;
		}

		public Builder withQuantity(int quantity) {
			this.quantity = quantity;
			return this;
		}

		public RemoveProduct build() {
			return new RemoveProduct(productId, quantity);
		}
	}
}
