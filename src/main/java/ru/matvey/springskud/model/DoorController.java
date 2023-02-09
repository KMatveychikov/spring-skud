package ru.matvey.springskud.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DoorController {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "door_controller_id")
    private Long id;
    private String description;

}
