/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package groub2.backend.service;

import groub2.backend.entities.Taophieukham;
import groub2.backend.entities.Toathuoc;
import groub2.backend.res.ListtoathuocRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ann
 */
@Service
public class ListtoathuocService {
    
    @Autowired
    private ListtoathuocRepository res;
    public List<Toathuoc> getAllByCreateAtToday() {
        return res.findAllByCreateAtToday();
    }
}
