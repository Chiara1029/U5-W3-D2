package com.chiarapuleio.devicesmanagement.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record EmployeeDTO (
        @NotEmpty(message = "Username required.")
        @Size(min = 3, max = 20, message = "Username must have 3 to 20 chars.")
        String username,
        @NotEmpty(message = "Name required.")
        @Size(min = 3, max = 20, message = "Name must have 3 to 20 chars.")
        String name,
        @NotEmpty(message = "Surname required.")
        @Size(min = 3, max = 20, message = "Surname must have 3 to 20 chars.")
        String surname,
        @NotEmpty(message = "Email required.")
        @Email(message = "Invalid email.")
        String email,

        @NotEmpty(message = "Password required.")
        @Size(min=5, max=10, message = "Psw must be from 5 to 10 chars.")
        String password
        ){
}
