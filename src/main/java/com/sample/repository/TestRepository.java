package com.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.model.Test;


public interface TestRepository extends JpaRepository<Test, Integer> {

}
