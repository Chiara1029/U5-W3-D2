package com.chiarapuleio.devicesmanagement.dao;

import com.chiarapuleio.devicesmanagement.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeviceDAO extends JpaRepository<Device, UUID> {
}
