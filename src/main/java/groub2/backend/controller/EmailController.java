/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package groub2.backend.controller;

import groub2.backend.entities.Patient;
import groub2.backend.service.PatientService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
@RequestMapping("/api/password-reset")
public class EmailController {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private PatientService patientService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/request")
    public ResponseEntity<String> requestPasswordReset(@RequestParam("email") String email, @RequestParam("phone") String phone) throws MessagingException {
        // Check if a patient with the provided email and phone exists
        boolean patientExists = patientService.checkEmailAndPhoneExists(email, phone);

        if (patientExists) {
         
                // Generate a new password
                String newPassword = generateRandomPassword();

                // Update the patient's password in the database
                Patient patient = patientService.findPatientByEmail(email);
                patient.setPassword(bCryptPasswordEncoder.encode(newPassword));
                patientService.updatePatient(patient);

                // Send the password reset email
                sendPasswordResetEmail(email, newPassword);

                return ResponseEntity.ok("Password reset email sent successfully.");
            
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Patient with the provided email and phone does not exist.");
        }
    }

    private String generateRandomPassword() {
        // Generate a random password, you can implement your own logic here
        return UUID.randomUUID().toString().substring(0, 8); // This generates an 8-character password
    }

    private void sendPasswordResetEmail(String email, String newPassword) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(email);
        helper.setSubject("Password Reset");

        // You can customize the email body as needed
        String emailBody = "Your new password is: " + newPassword;

        helper.setText(emailBody, true);

        // Send the email
        mailSender.send(message);
    }
}
