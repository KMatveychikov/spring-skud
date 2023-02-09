package ru.matvey.springskud.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WorkDayResponse {
    private String employeeName;
    private String date;
    private String worktime;
}
