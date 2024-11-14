package com.assessment.twinleaves.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Batch")
public class Batch {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "batch_id")
	private Long id;

	@Column(name = "mrp")
	private Integer mrp;

	@Column(name = "sp")
	private Integer sp;

	@Column(name = "purchase_price")
	private Integer purchasePrice;

	@Column(name = "available_quantity")
	private Integer availableQuantity;

	@Column(name = "inwarded_on")
	private LocalDate inwardedOn = LocalDate.now();

	@ManyToMany
	@JoinTable(name = "batch_gtin", joinColumns = @JoinColumn(name = "batch_id"), inverseJoinColumns = @JoinColumn(name = "gtin_id"))
	@JsonManagedReference
	private List<Gtin> gtins = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public List<Gtin> getGtins() {
		return gtins;
	}

	public void setGtins(List<Gtin> gtins) {
		this.gtins = gtins;
	}

}
