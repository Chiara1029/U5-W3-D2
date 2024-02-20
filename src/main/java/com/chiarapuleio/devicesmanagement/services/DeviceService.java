package com.chiarapuleio.devicesmanagement.services;

import com.chiarapuleio.devicesmanagement.dao.DeviceDAO;
import com.chiarapuleio.devicesmanagement.entities.Device;
import com.chiarapuleio.devicesmanagement.entities.Employee;
import com.chiarapuleio.devicesmanagement.enums.DeviceStatus;
import com.chiarapuleio.devicesmanagement.exceptions.NotFoundException;
import com.chiarapuleio.devicesmanagement.payloads.DeviceDTO;
import com.chiarapuleio.devicesmanagement.payloads.EmployeeUuidDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeviceService {
    @Autowired
    private DeviceDAO deviceDAO;
    @Autowired
    private EmployeeService empSrv;

    public Device save(DeviceDTO device){
        Device newDev = new Device();
        newDev.setType(device.type());
        newDev.setDeviceStatus(DeviceStatus.AVAILABLE);

        return deviceDAO.save(newDev);
    }

    public Device findById(UUID id){
        return deviceDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Device findByIdAndUpdate(UUID id, Device device){
        Device devFound = this.findById(id);
        devFound.setType(device.getType());
        devFound.setDeviceStatus(device.getDeviceStatus());

        return deviceDAO.save(devFound);
    }

    public void findByIdAndDelete(UUID id){
        Device devFound = this.findById(id);
        deviceDAO.delete(devFound);
    }

    public Device setDeviceEmployee(UUID deviceId, EmployeeUuidDTO employeeId){
        Employee employee = empSrv.findById(employeeId.id());
        Device devFound = this.findById(deviceId);
        devFound.setEmployee(employee);
        devFound.setDeviceStatus(DeviceStatus.ASSIGNED);
        return deviceDAO.save(devFound);
    }
}
