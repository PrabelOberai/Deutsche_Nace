package com.deutsche.nace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.deutsche.nace.entity.Nace;

public interface NaceRepository extends JpaRepository<Nace, String> {

	@Query(value="SELECT n FROM Nace n WHERE n.orderId = :orderId")
	Nace getNaceDataFromOrderId(@Param("orderId") String orderId);
	
}
