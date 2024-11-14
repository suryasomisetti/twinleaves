package com.assessment.twinleaves.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.assessment.twinleaves.dto.GtinRequest;
import com.assessment.twinleaves.entity.Batch;
import com.assessment.twinleaves.entity.Gtin;
import com.assessment.twinleaves.entity.Product;
import com.assessment.twinleaves.exception.ResourceNotFoundException;
import com.assessment.twinleaves.repository.BatchRepository;
import com.assessment.twinleaves.repository.GtinRepository;
import com.assessment.twinleaves.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class GtinService {

	private final GtinRepository gtinRepository;
	private final BatchRepository batchRepository;
	private final ProductRepository productRepository;

	public GtinService(GtinRepository gtinRepository, BatchRepository batchRepository,
			ProductRepository productRepository) {
		this.gtinRepository = gtinRepository;
		this.batchRepository = batchRepository;
		this.productRepository = productRepository;
	}

	@Transactional
	public Gtin saveGtin(GtinRequest gtinRequest) throws ResourceNotFoundException {
		Product product = productRepository.findById(gtinRequest.getProductId()).orElseThrow(
				() -> new ResourceNotFoundException("Product not found with id: " + gtinRequest.getProductId()));
		Gtin gtin = new Gtin();
		gtin.setProduct(product);
		gtin.setGtin(gtinRequest.getGtin());
		List<Batch> batches = new ArrayList<>();
		if(gtinRequest.getBatchIds() != null && gtinRequest.getBatchIds().size() > 0) {
			for (Long gtinId : gtinRequest.getBatchIds()) {
				batchRepository.findById(gtinId).ifPresent(batch -> {
					batches.add(batch);
					batch.getGtins().add(gtin);
					batchRepository.save(batch);
				});
			}
		}
		gtin.setBatches(batches);
		product.getGtins().add(gtin);
		productRepository.save(product);
		return gtin;
	}
	
	public Gtin getGtinById(Long gtinId) throws ResourceNotFoundException {
		return gtinRepository.findById(gtinId)
				.orElseThrow(() -> new ResourceNotFoundException("Gtin not found with id: " + gtinId));
	}
	
	public List<Gtin> getAllGtins() {
		return gtinRepository.findAll();
	}

	public Gtin getGtinByGtin(String gtin) throws ResourceNotFoundException {
		return gtinRepository.findByGtin(gtin)
				.orElseThrow(() -> new ResourceNotFoundException("Gtin not found with gtin: " + gtin));
	}

	public Gtin getGtinByProductId(Long productId) throws ResourceNotFoundException {
		return gtinRepository.findByProductId(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Gtin not found with prduct Id: " + productId));
	}

	public List<Gtin> getGtinsByGtinContainingIgnoreCase(String gtin) {
		return gtinRepository.findByGtinContainingIgnoreCase(gtin);
	}

	public List<Gtin> getGtinsByGtinStartingWith(String gtinPrefix) {
		return gtinRepository.findByGtinStartingWith(gtinPrefix);
	}

	public List<Gtin> getGtinsByGtinEndingWith(String gtinSuffix) {
		return gtinRepository.findByGtinEndingWith(gtinSuffix);
	}

	public List<Gtin> getGtinsWithNegativeOrZeroQuantity() {
		return gtinRepository.findGtinsWithNegativeOrZeroQuantity();
	}

	public List<Gtin> getGtinsWithPositiveQuantity() {
		return gtinRepository.findGtinsWithPositiveQuantity();
	}

}
