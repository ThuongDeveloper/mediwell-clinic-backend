/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package groub2.backend.firebase;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Data
    @Service
    @Configuration
    @ConfigurationProperties(prefix = "firebase")
    public class Properties {
        private String bucketName = "project-hk4-27286.appspot.com";
        private String imageUrl;
    }
