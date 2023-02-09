package ru.matvey.springskud.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "door_controller_id")
    private DoorController doorController;
    private String cardId;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;
}
