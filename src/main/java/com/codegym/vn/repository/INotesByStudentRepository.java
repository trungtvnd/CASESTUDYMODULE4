package com.codegym.vn.repository;

import com.codegym.vn.model.NotesByStudent;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

@Repository
public interface INotesByStudentRepository extends PagingAndSortingRepository<NotesByStudent,Long> {
}
