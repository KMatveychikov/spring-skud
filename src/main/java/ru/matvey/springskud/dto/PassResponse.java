package ru.matvey.springskud.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PassResponse {
    private String doorControllerDescription;
    private String date;
    private String employeeName;
}
