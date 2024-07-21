package dev.canessaalvamiguel.servicecompany.controller;

import dev.canessaalvamiguel.servicecompany.entities.Company;
import dev.canessaalvamiguel.servicecompany.entities.Product;
import dev.canessaalvamiguel.servicecompany.service.CompanyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/companies")
@Slf4j
@AllArgsConstructor
public class CompanyController {

  private final CompanyService companyService;

  //TODO: Implement pagination
  @GetMapping
  public ResponseEntity<List<Company>> getCompanies(){
    log.info("Getting all companies");
    return ResponseEntity.ok(companyService.getCompanies());
  }

  @GetMapping("/{companyId}")
  public ResponseEntity<Company> getCompanyById(@PathVariable Long companyId){
    log.info("Getting company by id: {}", companyId);
    return ResponseEntity.ok(companyService.getCompanyById(companyId));
  }

  @PostMapping
  public ResponseEntity<Company> createCompany(@RequestBody Company company){
    log.info("Creating new company: {}", company);
    return ResponseEntity.ok(companyService.createCompany(company));
  }

  @GetMapping("/{companyId}/products")
  public ResponseEntity<Page<Product>> getProductsByCompany(
      @PathVariable Long companyId,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size
  ){
    log.info("Getting company by id: {} with page {} and size {}", companyId, page, size);
    return ResponseEntity.ok(companyService.getProductsByCompany(companyId, page, size));
  }

}
