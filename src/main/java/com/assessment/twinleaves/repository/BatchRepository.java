package com.assessment.twinleaves.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.assessment.twinleaves.entity.Batch;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Long>{

	@Query("SELECT b FROM Batch b JOIN b.gtins g WHERE g.gtin = ?1")
	List<Batch> findByGtinNumber(String gtinNumber);
	
    @Query("SELECT b FROM Batch b WHERE b.availableQuantity > 0")
    List<Batch> findBatchesWithPositiveQuantity();
    
    @Query("SELECT b FROM Batch b WHERE b.availableQuantity <= 0")
    List<Batch> findBatchesWithNegativeOrZeroQuantity();
    
    @Query("SELECT b FROM Batch b WHERE b.availableQuantity <= 0 AND DATE(b.inwardedOn) = DATE((SELECT MAX(b2.inwardedOn) FROM Batch b2))")
     List<Batch> findBatchesForLatestDateWithZeroOrNegativeQuantity();
    
    @Query("SELECT b FROM Batch b WHERE b.availableQuantity <= 0 ORDER BY b.inwardedOn DESC LIMIT 1")
    Optional<Batch> findLatestBatchWithZeroOrNegativeQuantity();
	
}
