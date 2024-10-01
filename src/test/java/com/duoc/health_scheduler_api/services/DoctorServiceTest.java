package com.duoc.health_scheduler_api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.duoc.health_scheduler_api.models.Doctor;
import com.duoc.health_scheduler_api.repository.DoctorRepository;
import com.duoc.health_scheduler_api.service_impl.DoctorServiceImpl;

@ExtendWith(MockitoExtension.class)
class DoctorServiceTest {

    @InjectMocks
    private DoctorServiceImpl doctorService;

    @Mock
    private DoctorRepository doctorRepositoryMock;

    @Test
    void testAddDoctor() {
        // Arrange
        int doctorId = 1;
        Doctor doctor = new Doctor(doctorId, "Juan Pérez", "Cardiología");

        when(doctorRepositoryMock.save(any(Doctor.class))).thenReturn(doctor);

        // Act
        Doctor result = doctorService.addDoctor(doctor);

        // Assert
        assertEquals("Juan Pérez", result.getDoctorName());
    }

    @Test
    void testGetDoctorById() {
        // Arrange
        int doctorId = 1;
        Doctor doctor = new Doctor(doctorId, "Juan Pérez", "Cardiología");

        when(doctorRepositoryMock.findById(doctorId)).thenReturn(Optional.of(doctor));

        // Act
        Doctor result = doctorService.getDoctorById(doctorId);

        // Assert
        assertNotNull(result);
        assertEquals(doctorId, result.getId());
        assertEquals("Juan Pérez", result.getDoctorName());
        assertEquals("Cardiología", result.getSpeciality());

        // Verify
        verify(doctorRepositoryMock, times(1)).findById(doctorId);

    }

    @Test
    void testUpdateDoctor() {
        // Arrange
        int doctorId = 1;
        Doctor updatedDoctor = new Doctor(doctorId, "Juan Pérez", "Neurología");

        when(doctorRepositoryMock.save(any(Doctor.class))).thenReturn(updatedDoctor);

        // Act
        Doctor result = doctorService.updateDoctor(updatedDoctor);

        // Assert
        assertNotNull(result);
        assertEquals("Neurología", result.getSpeciality());
        assertEquals("Juan Pérez", result.getDoctorName());

        // Verify
        verify(doctorRepositoryMock, times(1)).save(updatedDoctor);
    }

}
