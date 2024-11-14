package com.assessment.twinleaves.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.assessment.twinleaves.entity.Gtin;

@Repository
public interface GtinRepository extends JpaRepository<Gtin, Long>{

	Optional<Gtin> findByGtin(String gtin);
	
	@Query("SELECT g FROM Gtin g WHERE g.product.id = :productId")
	Optional<Gtin> findByProductId(@Param("productId") Long productId);
	
	List<Gtin> findByGtinContainingIgnoreCase(String gtin);

    List<Gtin> findByGtinStartingWith(String gtinPrefix);
    
    List<Gtin> findByGtinEndingWith(String gtinSuffix);
    
    @Query("SELECT g FROM Gtin g JOIN g.batches b WHERE b.availableQuantity <= 0")
    List<Gtin> findGtinsWithNegativeOrZeroQuantity();
    
    @Query("SELECT g FROM Gtin g JOIN g.batches b WHERE b.availableQuantity > 0")
    List<Gtin> findGtinsWithPositiveQuantity();
	
}
