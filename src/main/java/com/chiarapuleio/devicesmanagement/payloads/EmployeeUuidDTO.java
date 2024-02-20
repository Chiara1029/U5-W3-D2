package com.chiarapuleio.devicesmanagement.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record EmployeeUuidDTO(
        @NotEmpty(message = "ID required.")
        @Size(min = 3, max = 36, message = "ID max value is 36 characters.")
        UUID id
) {

}
