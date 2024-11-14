package com.assessment.twinleaves.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.assessment.twinleaves.dto.BatchRequest;
import com.assessment.twinleaves.entity.Batch;
import com.assessment.twinleaves.entity.Gtin;
import com.assessment.twinleaves.entity.Product;
import com.assessment.twinleaves.exception.ResourceNotFoundException;
import com.assessment.twinleaves.repository.BatchRepository;
import com.assessment.twinleaves.repository.GtinRepository;
import com.assessment.twinleaves.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class BatchService {

	private final GtinRepository gtinRepository;
	private final BatchRepository batchRepository;
	private final ProductRepository productRepository;

	public BatchService(GtinRepository gtinRepository, BatchRepository batchRepository,
			ProductRepository productRepository) {
		this.gtinRepository = gtinRepository;
		this.batchRepository = batchRepository;
		this.productRepository = productRepository;
	}

	@Transactional
	public Batch saveBatch(BatchRequest batchRequest) throws ResourceNotFoundException {
		Batch batch = new Batch();
		batch.setAvailableQuantity(batchRequest.getAvailableQuantity());
		batch.setInwardedOn(batchRequest.getInwardedOn());
		batch.setMrp(batchRequest.getMrp());
		batch.setPurchasePrice(batchRequest.getPurchasePrice());
		batch.setSp(batchRequest.getSp());

		List<Gtin> gtins = new ArrayList<>();
		if(batchRequest.getGtinIds() != null && batchRequest.getGtinIds().size() > 0) {
			for (Long gtinId : batchRequest.getGtinIds()) {
				Gtin gtin = gtinRepository.findById(gtinId)
						.orElseThrow(() -> new ResourceNotFoundException("Batch not found with id: " + gtinId));
				gtins.add(gtin);
				gtin.getBatches().add(batch);
				gtinRepository.save(gtin);
			}
		} else {
			throw new ResourceNotFoundException("Batches Data not Sent.");
		}
		

		batch.setGtins(gtins);
		return batchRepository.save(batch);
	}
	
	public Batch getBatchById(Long batchId) throws ResourceNotFoundException {
		return batchRepository.findById(batchId)
				.orElseThrow(() -> new ResourceNotFoundException("Batch not found with id: " + batchId));
	}
	
	public List<Batch> getAllBatches() {
		return batchRepository.findAll();
	}
	
	public List<Batch> getBatchesByGtinNumber(String gtinNumber) {
        return batchRepository.findByGtinNumber(gtinNumber);
    }

    public List<Batch> getBatchesWithPositiveQuantity() {
        return batchRepository.findBatchesWithPositiveQuantity();
    }

    public List<Batch> getBatchesWithNegativeOrZeroQuantity() {
        return batchRepository.findBatchesWithNegativeOrZeroQuantity();
    }

    public List<Batch> getBatchesForLatestDateWithZeroOrNegativeQuantity() {
        return batchRepository.findBatchesForLatestDateWithZeroOrNegativeQuantity();
    }

    public Batch getLatestBatchWithZeroOrNegativeQuantity() {
        return batchRepository.findLatestBatchWithZeroOrNegativeQuantity().orElse(null);
    }


}
