package com.service;

import org.springframework.data.jpa.repository.JpaRepository;


public abstract class BaseService<T, K> {
	protected JpaRepository<T, K> repository;
    
}
