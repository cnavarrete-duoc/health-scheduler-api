package com.duoc.health_scheduler_api.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.duoc.health_scheduler_api.models.Doctor;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Test
    void testAddDoctor() {
        // Arrange
        int doctorId = 1;
        Doctor doctor = new Doctor(doctorId, "Juan Pérez", "Cardiología");

        // Act
        Doctor result = doctorRepository.save(doctor);

        // Assert
        assertNotNull(result);
        assertEquals("Juan Pérez", result.getDoctorName());
    }

}
