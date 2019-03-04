package com.margo.sample.cqrs.ecommerce.events;

public class ProductAdded {
	private final String productId;
	private final int quantity;

	private ProductAdded(final String productId, final int quantity) {
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

		public ProductAdded build() {
			return new ProductAdded(productId, quantity);
		}
	}
}
