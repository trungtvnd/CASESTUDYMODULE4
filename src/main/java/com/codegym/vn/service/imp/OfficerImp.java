package com.codegym.vn.service.imp;


import com.codegym.vn.model.Officer;
import com.codegym.vn.repository.IOfficerRepository;
import com.codegym.vn.service.IOfficerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class OfficerImp implements IOfficerService {
    @Autowired
    private IOfficerRepository iOfficerRepository;

    @Override
    public Iterable<Officer> findAll() {
        return iOfficerRepository.findAll();
    }

    @Override
    public Officer save(Officer officer) {
        return iOfficerRepository.save(officer);
    }

    @Override
    public void delete(Long id) {
        iOfficerRepository.deleteById(id);
    }

    @Override
    public Page<Officer> findPage(Pageable pageable) {
        return iOfficerRepository.findAll(pageable);
    }

    @Override
    public Optional<Officer> findById(Long id) {
        return iOfficerRepository.findById(id);
    }

}
