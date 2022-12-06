package com.example.finishwithboot.serviceImpl;

import com.example.finishwithboot.model.Company;
import com.example.finishwithboot.repository.CompanyRepository;
import com.example.finishwithboot.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

import java.io.IOException;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public void saveCompany(Company company){
        validator(company.getCompanyName(), company.getLocatedCountry());
        companyRepository.save(company);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }


    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElseThrow(
                () -> new RuntimeException("not found")
        );
    }

    @Override
    public void updateCompany(Company company, Long id) {
        validator(company.getCompanyName(), company.getLocatedCountry());
        Company company1 = companyRepository.findById(id).orElseThrow(
                () -> new RuntimeException("not found "));
        company1.setCompanyName(company.getCompanyName());
        company1.setLocatedCountry(company.getLocatedCountry());
        companyRepository.save(company);
    }

    @Override
    public void deleteCompany(Long id) {
        companyRepository.delete(companyRepository.findById(id).get());
    }

    @SneakyThrows
    private void validator(String companyName, String locatedCountry) {
        for (Character i : companyName.toCharArray()) {
            if (!Character.isAlphabetic(i)) {
                    throw new IOException("no nums in company name");
                }
            }
            for (Character i : locatedCountry.toCharArray()) {
                if (!Character.isAlphabetic(i)) {
                    throw new IOException("no nums in company located country");
                }
            }
        }

}
