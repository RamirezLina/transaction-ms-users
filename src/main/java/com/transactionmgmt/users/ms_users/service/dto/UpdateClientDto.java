package com.transactionmgmt.users.ms_users.service.dto;

import com.transactionmgmt.users.ms_users.domain.client.GenderType;
import jakarta.validation.constraints.*;

public record UpdateClientDto(
        @NotBlank(message = "El nombre es obligatorio")
        String name,
        @NotNull(message = "El género es obligatorio")
        GenderType gender,
        @Min(value = 0, message = "La edad no puede ser negativa")
        int age,
        @NotBlank(message = "La identificación es obligatoria")
        String identification,
        @NotBlank(message = "La dirección es obligatoria")
        String address,
        @NotBlank(message = "El número de teléfono es obligatorio")
        String phoneNumber
        ) {
}
