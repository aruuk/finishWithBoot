package com.example.finishwithboot.service;

import com.example.finishwithboot.model.Company;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyService {

    void saveCompany(Company company) ;

    List<Company> getAllCompanies();

    Company getCompanyById(Long id);

    void deleteCompany(Long id);

    void updateCompany(Company company, Long id);
}
