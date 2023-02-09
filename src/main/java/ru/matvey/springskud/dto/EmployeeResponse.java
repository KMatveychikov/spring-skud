package ru.matvey.springskud.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeResponse {
    private String name;
    private String position;
    private String department;
    private String lastPassDate;
    private String lastPassDoor;
    private String workTimeByDay;
}
