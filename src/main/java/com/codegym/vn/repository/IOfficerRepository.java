package com.codegym.vn.repository;


import com.codegym.vn.model.Officer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOfficerRepository extends PagingAndSortingRepository<Officer,Long> {
}
