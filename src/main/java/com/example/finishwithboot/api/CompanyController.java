package com.example.finishwithboot.api;

import com.example.finishwithboot.model.Company;
import com.example.finishwithboot.service.CompanyService;
import com.example.finishwithboot.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/companies")
@EnableWebMvc
public class CompanyController {

    private final CompanyService companyService;
    private final CourseService courseService;

    @GetMapping()
    private String getAllCompanies(Model model) {
        List<Company> companies = companyService.getAllCompanies();
        model.addAttribute("companies", companies);
        return "company/companies";
    }


    @GetMapping("/addCompany")
    private String addCompany(Model model) {
        model.addAttribute( "company", new Company());
        return "company/addCompany";
    }

    @PostMapping("/saveCompany")
    private String saveCompany(@ModelAttribute("company") Company company){
        companyService.saveCompany(company);
        return "redirect:/companies";
    }


    @GetMapping("/getCompany/{companyId}")
    private String getCompanyById(@PathVariable("companyId") Long id, Model model) {
        model.addAttribute("company", companyService.getCompanyById(id));
        System.out.println(companyService.getCompanyById(id));
        return "/innerPage";
    }


    @GetMapping("/edit/{id}")
    private String updateCompany(@PathVariable("id") Long id, Model model) {
        model.addAttribute("company", companyService.getCompanyById(id));
        return "company/updateCompany";
    }


    @PostMapping("/{id}/update")
    private String saveUpdateCompany(@ModelAttribute("company") Company company,
                                     @PathVariable("id") Long id) {
        companyService.updateCompany(company, id);
        return "redirect:/companies";
    }


    @PostMapping("/{id}")
    private String deleteCompany(@PathVariable("id") Long id) {
        companyService.deleteCompany(id);
        return "redirect:/companies";
    }

    @GetMapping("/infoPage/{companyId}")
    private String infoPage(@PathVariable("companyId") Long id, Model model){
        model.addAttribute("course", courseService.getCourseById(id));
        return "company/infoPage";
    }
}
