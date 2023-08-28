/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package groub2.backend.controller;

import groub2.backend.entities.Casher;
import groub2.backend.firebase.FirebaseImageService;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author DELL
 */
@RestController
@RequestMapping("/firebase")
public class FirebaseController {
    @Autowired
    FirebaseImageService _FirebaseImageService;
    
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public String listCasher() throws InterruptedException, ExecutionException, IOException {
     var a =    _FirebaseImageService.listFilesInBucket("project-hk4-27286.appspot.com");
        System.out.println(a);
        return  "bok";
    }

}
