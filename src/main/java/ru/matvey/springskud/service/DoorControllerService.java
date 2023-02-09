package ru.matvey.springskud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.matvey.springskud.model.DoorController;
import ru.matvey.springskud.repository.DoorControllerRepository;

@Service
public class DoorControllerService {
    @Autowired
    DoorControllerRepository doorControllerRepository;

    DoorController getById(Long id) {
        return doorControllerRepository.findById(id).orElseThrow();
    }
}
