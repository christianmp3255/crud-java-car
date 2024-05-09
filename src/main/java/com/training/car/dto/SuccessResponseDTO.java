package com.training.car.dto;

import lombok.Data;

@Data
public class SuccessResponseDTO {
    private String code;
    private String status;
    private String message;
}
