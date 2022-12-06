package com.example.finishwithboot.repository;

import com.example.finishwithboot.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}