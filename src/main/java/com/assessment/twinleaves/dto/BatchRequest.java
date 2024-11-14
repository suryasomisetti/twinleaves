package com.assessment.twinleaves.dto;

import java.time.LocalDate;
import java.util.List;

public class BatchRequest {

	private Integer mrp;
	private Integer sp;
	private Integer purchasePrice;
	private Integer availableQuantity;
	private LocalDate inwardedOn;
	private List<Long> gtinIds;

	public Integer getMrp() {
		return mrp;
	}

	public void setMrp(Integer mrp) {
		this.mrp = mrp;
	}

	public Integer getSp() {
		return sp;
	}

	public void setSp(Integer sp) {
		this.sp = sp;
	}

	public Integer getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Integer purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Integer getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(Integer availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	public LocalDate getInwardedOn() {
		return inwardedOn;
	}

	public void setInwardedOn(LocalDate inwardedOn) {
		this.inwardedOn = inwardedOn;
	}

	public List<Long> getGtinIds() {
		return gtinIds;
	}

	public void setGtinIds(List<Long> gtinIds) {
		this.gtinIds = gtinIds;
	}

}
