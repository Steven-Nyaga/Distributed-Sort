package com.distributed.sort.distributedsort.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.distributed.sort.distributedsort.models.RandomNumber;

public interface RandomNumberRepository extends JpaRepository<RandomNumber, Integer> {

}
