package com.margo.sample.cqrs.ecommerce.events;

public class ProductRemoved {
	private final String productId;
	private final int quantity;

	private ProductRemoved(final String productId, final int quantity) {
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

		public ProductRemoved build() {
			return new ProductRemoved(productId, quantity);
		}
	}
}
