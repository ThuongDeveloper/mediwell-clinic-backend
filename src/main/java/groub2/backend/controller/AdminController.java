/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package groub2.backend.controller;

import groub2.backend.entities.Admin;
import groub2.backend.res.AdminRespository;
import java.util.List;
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
@RequestMapping("/url")
public class AdminController {
    @Autowired
    AdminRespository res;
     @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Admin> read(){
        return res.findAll();
    }
}
