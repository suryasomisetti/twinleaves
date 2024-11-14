package com.assessment.twinleaves.dto;

import java.util.List;

public class GtinRequest {

	private long productId;
	private String gtin;
	private List<Long> batchIds;

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public List<Long> getBatchIds() {
		return batchIds;
	}

	public void setBatchIds(List<Long> batchIds) {
		this.batchIds = batchIds;
	}

	public String getGtin() {
		return gtin;
	}

	public void setGtin(String gtin) {
		this.gtin = gtin;
	}

}
