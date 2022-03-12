package com.codegym.vn.service;


import com.codegym.vn.model.Officer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.Optional;

public interface IOfficerService {

    Iterable<Officer> findAll();

    Officer save(Officer officer);

    void delete(Long id);

    Page<Officer> findPage(Pageable pageable);

    Optional<Officer> findById(Long id);
}
