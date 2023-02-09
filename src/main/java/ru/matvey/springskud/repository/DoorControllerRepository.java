package ru.matvey.springskud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.matvey.springskud.model.DoorController;

public interface DoorControllerRepository extends JpaRepository<DoorController, Long> {
}
