package groub2.backend.dto;

import groub2.backend.entities.Doctor;
import groub2.backend.entities.Patient;
import lombok.Getter;

public class RatingDAO {
    private Doctor doctorId;
    private Patient patientId;
    private Double rating;
    private String comment;
    public RatingDAO() {
    }

    public Doctor getDoctor_id() {
        return doctorId;
    }

    public void setDoctor_id(Doctor doctor_id) {
        this.doctorId = doctor_id;
    }

    public Patient getPatient_id() {
        return patientId;
    }

    public void setPatient_id(Patient patient_id) {
        this.patientId = patient_id;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
