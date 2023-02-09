package ru.matvey.springskud.dto;

import lombok.Data;

@Data
public class PassRequest {
    private Long doorControllerId;
    private String cardId;
}
