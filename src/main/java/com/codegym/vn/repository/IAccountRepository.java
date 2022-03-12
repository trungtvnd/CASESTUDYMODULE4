package com.codegym.vn.repository;

import com.codegym.vn.model.Account;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepository extends PagingAndSortingRepository<Account, Long> {
}
