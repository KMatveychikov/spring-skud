package ru.matvey.springskud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.matvey.springskud.dto.PassRequest;
import ru.matvey.springskud.dto.PassResponse;
import ru.matvey.springskud.service.PassService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pass")
public class PassController {

    @Autowired
    PassService passService;

    @PostMapping("/add")
    boolean addPass(@RequestBody PassRequest pass) {
        return passService.addPass(pass);
    }

    @GetMapping("/all")
    List<PassResponse> getAllPass() {
        return passService.getAllPass();
    }

}
