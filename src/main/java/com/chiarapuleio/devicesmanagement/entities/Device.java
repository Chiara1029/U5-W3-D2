package com.chiarapuleio.devicesmanagement.entities;

import com.chiarapuleio.devicesmanagement.enums.DeviceStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name="devices")
public class Device {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String type;
    @Enumerated(EnumType.STRING)
    private DeviceStatus deviceStatus;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Device(String type, DeviceStatus deviceStatus) {
        this.type = type;
        this.deviceStatus = deviceStatus;
    }
}
