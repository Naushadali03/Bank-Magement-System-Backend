package com.dev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dev.model.Account;

import jakarta.transaction.Transactional;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
		public Optional<Account> findByAcc(int acc);
		
		public Optional<Account> findBalanceById(Long uid);
		public Optional<Account> findAccById(Long uid);
		
		@Transactional
	    @Modifying
	    @Query("UPDATE Account a SET a.balance = :newBalance WHERE a.acc = :acc")
	    void updateBalanceByAcc(@Param("acc") int acc, @Param("newBalance") int newBalance);
		
		public Optional<Account> findBalanceByAcc(int acc);
		
}
