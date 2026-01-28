package com.heaven.patientservice.mapper;

import com.heaven.patientservice.dto.PatientRequestDTO;
import com.heaven.patientservice.dto.PatientResponseDTO;
import com.heaven.patientservice.model.Patient;
import jakarta.validation.Valid;

import java.time.LocalDate;

public class PatientMapper {
    public static PatientResponseDTO toPatientResponseDTO(Patient patient) {
        PatientResponseDTO patientDTO = new PatientResponseDTO();
        patientDTO.setId(patient.getId().toString());
        patientDTO.setName(patient.getName());
        patientDTO.setAddress(patient.getAddress());
        patientDTO.setEmail(patient.getEmail());
        patientDTO.setDateOfBirth(patient.getDateOfBirth().toString());
        patientDTO.setRegisteredDate(patient.getRegisteredDate().toString());

        return patientDTO;
    }

    public static Patient toModel(@Valid PatientRequestDTO patientResponseDTO) {
        Patient patient = new Patient();
        patient.setName(patientResponseDTO.getName());
        patient.setAddress(patientResponseDTO.getAddress());
        patient.setEmail(patientResponseDTO.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientResponseDTO.getDateOfBirth()));
        patient.setRegisteredDate(LocalDate.parse(patientResponseDTO.getRegisteredDate()));

        return patient;
    }
}
