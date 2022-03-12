package com.codegym.vn.repository;

import com.codegym.vn.model.Classes;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClassesRepository extends PagingAndSortingRepository<Classes,Long> {
}
