package com.duoc.health_scheduler_api.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.duoc.health_scheduler_api.models.Doctor;
import com.duoc.health_scheduler_api.service_impl.DoctorServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(DoctorController.class)
class DoctorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DoctorServiceImpl doctorServiceMock;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getAllDoctorsTest() throws Exception {
        Doctor doctor1 = new Doctor(1, "Juan Pérez", "Cardiología");
        Doctor doctor2 = new Doctor(2, "Ana Gómez", "Dermatología");

        List<Doctor> doctors = Arrays.asList(doctor1, doctor2);
        when(doctorServiceMock.getAllDoctors()).thenReturn(doctors);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/doctors"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.doctorList.length()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.doctorList[0].doctorName").value("Juan Pérez"))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.doctorList[1].doctorName").value("Ana Gómez"));
    }

    @Test
    void getDoctorByIdTest() throws Exception {
        int doctorId = 1;
        Doctor doctor = new Doctor(doctorId, "Juan Pérez", "Cardiología");

        when(doctorServiceMock.getDoctorById(doctorId)).thenReturn(doctor);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/doctor/{doctorId}", doctorId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(doctorId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.doctorName").value("Juan Pérez"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.speciality").value("Cardiología"))
                .andExpect(MockMvcResultMatchers.jsonPath("$._links.self.href").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$._links.update-doctor.href").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$._links.delete-doctor.href").exists());
    }

    @Test
    void addDoctorTest() throws Exception {
        Doctor doctor = new Doctor(0, "Ana Gómez", "Dermatología");
        Doctor savedDoctor = new Doctor(1, "Ana Gómez", "Dermatología");

        when(doctorServiceMock.addDoctor(any(Doctor.class))).thenReturn(savedDoctor);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/doctor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(doctor)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.doctorName").value("Ana Gómez"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.speciality").value("Dermatología"))
                .andExpect(MockMvcResultMatchers.jsonPath("$._links.self.href").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$._links.all-doctors.href").exists());
    }

    @Test
    void addDoctorValidationErrorTest() throws Exception {
        Doctor doctor = new Doctor(0, "", ""); // Campos vacíos para provocar errores de validación

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/doctor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(doctor)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.doctorName").value("Doctor name is required"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.speciality").value("Speciality is required"));
    }
}
