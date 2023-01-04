package com.amtrp.youtube.pt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amtrp.youtube.pt.model.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long>{

}
