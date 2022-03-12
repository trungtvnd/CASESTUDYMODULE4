package com.codegym.vn.repository;

import com.codegym.vn.model.NoteByClass;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INoteByClassesRepository extends PagingAndSortingRepository<NoteByClass, Long> {
}
