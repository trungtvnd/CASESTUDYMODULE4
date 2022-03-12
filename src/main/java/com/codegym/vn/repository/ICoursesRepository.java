package com.codegym.vn.repository;

import com.codegym.vn.model.Courses;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICoursesRepository extends PagingAndSortingRepository<Courses,Long> {
}
