/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package groub2.backend.service;

import groub2.backend.entities.Appointment;
import groub2.backend.entities.Patient;
import groub2.backend.res.AppointmentRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hokim
 */
@Service
public class AppointmentService {
    @Autowired
    AppointmentRepository res;

    public List<Appointment> findAll() {
        return res.findAll();
    }

    public Appointment getAppointmentById(Integer id) {
        return res.findById(id).orElse(null);
    }

    public void addAppointment(Appointment newAppointment) {
        res.save(newAppointment);
    }

    public Appointment updateAppointment(Appointment updatedAppointment, int id) {
//        Appointment appointment = res.findById(updatedAppointment.getId()).orElse(null);
//        if (appointment != null) {
//            // Kiểm tra xem các trường có được cung cấp để cập nhật hay không
//            if (updatedAppointment.getStarttime()!= null) {
//                appointment.setStarttime(updatedAppointment.getStarttime());
//            }
//            if (updatedAppointment.getEndtime()!= null) {
//                appointment.setEndtime(updatedAppointment.getEndtime());
//            }
//            if (updatedAppointment.getSymptom()!= null) {
//                appointment.setSymptom(updatedAppointment.getSymptom());
//            }
//            if (updatedAppointment.getTypePayment()!= null) {
//                appointment.setTypePayment(updatedAppointment.getTypePayment());
//            }
//            if (updatedAppointment.getPrice()!= null) {
//                appointment.setPrice(updatedAppointment.getPrice());
//            }
//            if (updatedAppointment.getDate()!= null) {
//                appointment.setDate(updatedAppointment.getDate());
//            }
//            if (updatedAppointment.getDoctorId()!= null) {
//                appointment.setDoctorId(updatedAppointment.getDoctorId());
//            }
//
//            // Lưu đối tượng Casher đã cập nhật vào cơ sở dữ liệu
//            return res.save(appointment);
//        }
//        return null;
        Appointment appointment = res.findById(id).get();
        appointment.setStarttime(updatedAppointment.getStarttime());
        appointment.setEndtime(updatedAppointment.getEndtime());
        appointment.setSymptom(updatedAppointment.getSymptom());
        appointment.setTypePayment(updatedAppointment.getTypePayment());
        appointment.setPrice(updatedAppointment.getPrice());
        appointment.setDate(updatedAppointment.getDate());
        appointment.setDoctorId(updatedAppointment.getDoctorId());
        return res.save(appointment);
    }

    public void deleteAppointment(Integer id) {
        res.deleteById(id);
    }
    
        public List<Appointment> getAllByPatientId(Patient id) {
        return res.findByPatientId(id);
    }
        
          public List<Appointment> getAppointmentByDate(Date startDate,Date endDate) {
        return res.findAppointmentByDate(startDate,endDate);
    }
}
