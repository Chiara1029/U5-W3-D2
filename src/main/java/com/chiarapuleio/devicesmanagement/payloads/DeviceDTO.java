package com.chiarapuleio.devicesmanagement.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record DeviceDTO (
        @NotEmpty(message = "Type required.")
        @Size(min = 3, max = 10, message = "Type must have 3 to 20 chars.")
        String type
){

}
