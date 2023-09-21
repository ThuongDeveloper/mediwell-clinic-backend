/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package groub2.backend.service;

import groub2.backend.entities.Casher;
import groub2.backend.entities.Patient;
import groub2.backend.res.AdminRespository;
import groub2.backend.res.CasherRepository;
import groub2.backend.res.DoctorRepository;
import groub2.backend.res.PatientRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author admin
 */
@Service
public class PatientService {

    @Autowired
    PatientRepository res;
    
    @Autowired
    private AdminRespository adminRepository;

    @Autowired
    private CasherRepository cashierRepository;

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<Patient> findAll() {
        return res.findAll();
    }

    public Patient getPatientById(Integer id) {
        return res.findById(id).orElse(null);
    }

    public void addPatient(Patient newPatient) {
        res.save(newPatient);
    }

    public Patient updatePatient(Patient updatePatient) {
        Patient patient = res.findById(updatePatient.getId()).orElse(null);
        if (patient != null) {
            // Kiểm tra xem các trường có được cung cấp để cập nhật hay không
            if (updatePatient.getName() != null) {
                patient.setName(updatePatient.getName());
            }
            if (updatePatient.getPassword() != null) {
                patient.setPassword(bCryptPasswordEncoder.encode(updatePatient.getPassword()));
            }
            if (updatePatient.getEmail() != null) {
                patient.setEmail(updatePatient.getEmail());
            }
            if (updatePatient.getAddress() != null) {
                patient.setAddress(updatePatient.getAddress());
            }
            if (updatePatient.getPhone() != null) {
                patient.setPhone(updatePatient.getPhone());
            }
            if (updatePatient.getImage()!= null) {
                patient.setImage(updatePatient.getImage());
            }
            // Lưu đối tượng Casher đã cập nhật vào cơ sở dữ liệu
            return res.save(patient);
        }
        return null;
    }

    public void deletePatient(Integer id) {
        res.deleteById(id);
    }

    public boolean checkEmailExists(String email) {
        // Sử dụng repository để kiểm tra xem email đã tồn tại trong cơ sở dữ liệu hay chưa
        return res.existsByEmail(email);
    }

    public Patient findPatientByEmail(String email) {
        // Sử dụng patientRepository hoặc phương thức nào đó để tìm kiếm bệnh nhân bằng email
        return res.findByEmail(email);
    }

    public boolean checkEmailAndPhoneExists(String email, String phone) {
        return res.existsByEmailAndPhone(email, phone);
    }

    public boolean checkEmailExistsForAnyUserType(String email) {
        // Kiểm tra email tồn tại trong các loại người dùng khác nhau
        boolean emailExistsInAdmin = adminRepository.existsByEmail(email);
        boolean emailExistsInCashier = cashierRepository.existsByEmail(email);
        boolean emailExistsInPatient = res.existsByEmail(email);
        boolean emailExistsInDoctor = doctorRepository.existsByEmail(email);

        // Trả về true nếu email tồn tại trong bất kỳ loại người dùng nào
        return emailExistsInAdmin || emailExistsInCashier || emailExistsInPatient || emailExistsInDoctor;
    }
}
