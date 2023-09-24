<<<<<<< HEAD
=======
<<<<<<< HEAD
=======

>>>>>>> e17282b3f1ece8f44e33b5a10a184a0acc6aa17c
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
<<<<<<< HEAD
=======

>>>>>>> e17282b3f1ece8f44e33b5a10a184a0acc6aa17c
>>>>>>> 48499ed57239d9b363880c60e71f27a7b6e75e61
package groub2.backend.controller;
import groub2.backend.entities.Patient;
import groub2.backend.service.PatientService;
import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.context.annotation.Bean;
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

            try {
                // Generate a new strong password
                String newPassword = generateStrongRandomPassword();

                // Generate a new password
                //  String newPassword = generateRandomPassword();
                // Update the patient's password in the database
                Patient patient = patientService.findPatientByEmail(email);
                //String encryptedPassword = bCryptPasswordEncoder.encode(newPassword);
                patient.setPassword(newPassword);
                patientService.updatePatient(patient);

                // Check if the password was updated successfully
                boolean passwordUpdated = isPasswordUpdated(email, newPassword);

                if (passwordUpdated) {
                    // Send the password reset email
                    boolean emailSent = sendPasswordResetEmail(email, newPassword);

                    if (emailSent) {
                        // Password reset successful
                        return ResponseEntity.ok("Password reset email sent successfully.");
                    } else {
                        // Email sending failed
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body("An error occurred while sending the password reset email.");
                    }
                } else {
                    // Password update failed
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("An error occurred while updating the password.");
                }
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("An error occurred while processing your request.");
            }

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Patient with the provided email and phone does not exist.");
        }
    }

    private String generateStrongRandomPassword() {
        int passwordLength = 12; // Đổi độ dài theo yêu cầu của bạn
        Random random = new SecureRandom();
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String special = "!@#$%^&*()-_+=<>?";

        String allCharacters = upper + lower + digits + special;
        StringBuilder password = new StringBuilder(passwordLength);

        // Đảm bảo ít nhất một ký tự từ mỗi loại
        password.append(upper.charAt(random.nextInt(upper.length())));
        password.append(lower.charAt(random.nextInt(lower.length())));
        password.append(digits.charAt(random.nextInt(digits.length())));
        password.append(special.charAt(random.nextInt(special.length())));

        // Điền phần còn lại của mật khẩu với ký tự ngẫu nhiên
        for (int i = 4; i < passwordLength; i++) {
            password.append(allCharacters.charAt(random.nextInt(allCharacters.length())));
        }

        // Trộn lại mật khẩu để tạo tính ngẫu nhiên
        char[] passwordArray = password.toString().toCharArray();
        for (int i = 0; i < passwordLength; i++) {
            int randomIndex = random.nextInt(passwordLength);
            char temp = passwordArray[i];
            passwordArray[i] = passwordArray[randomIndex];
            passwordArray[randomIndex] = temp;
        }

        return new String(passwordArray);
    }

    private boolean sendPasswordResetEmail(String email, String newPassword) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(email);
            helper.setSubject("Password Reset");

            // You can customize the email body as needed
            String emailBody = "Your new password is: " + newPassword;

            helper.setText(emailBody, true);

            // Send the email
            mailSender.send(message);

            return true; // Email sent successfully
        } catch (MessagingException e) {
            return false; // Email sending failed
        }
    }

    private boolean isPasswordUpdated(String email, String newPassword) {
        // Retrieve the encrypted password from the database
        Patient patient = patientService.findPatientByEmail(email);
        String encryptedPassword = patient.getPassword();

        // Check if the provided newPassword matches the stored encrypted password
        return bCryptPasswordEncoder.matches(newPassword, encryptedPassword);
    }
}

<<<<<<< HEAD
=======
>>>>>>> e17282b3f1ece8f44e33b5a10a184a0acc6aa17c
>>>>>>> 48499ed57239d9b363880c60e71f27a7b6e75e61
