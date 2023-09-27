package groub2.backend.service;

import groub2.backend.entities.Company;
import groub2.backend.entities.Donvi;
import groub2.backend.res.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository res;

    public List<Company> getAll() {
        return res.findAll();
    }

    public boolean saveCompany(Company company) {

        try {
            res.save(company);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
    public Optional<Company> findCompanybyID(int id){
        return res.findById(id);

    }

    public void editCompany(Company company){
        res.save(company);
    }
    public boolean deleteCompanyId(int id){
        try {
            res.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
    public boolean isCompanyNameExists(String name) {
        Company existingCompany = res.findByName(name);
        return existingCompany != null;
    }

}