package com.heaven.patientservice.service;

import com.heaven.patientservice.dto.PatientRequestDTO;
import com.heaven.patientservice.dto.PatientResponseDTO;
import com.heaven.patientservice.exeption.EmailAlreadyExistsExeption;
import com.heaven.patientservice.exeption.PatientNotFoundException;
import com.heaven.patientservice.grpc.BillingServiceGrpcClient;
import com.heaven.patientservice.mapper.PatientMapper;
import com.heaven.patientservice.model.Patient;
import com.heaven.patientservice.repository.PatientRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final BillingServiceGrpcClient billingServiceGrpcClient;

    public PatientService(PatientRepository patientRepository, BillingServiceGrpcClient billingServiceGrpcClient) {
        this.patientRepository = patientRepository;
        this.billingServiceGrpcClient = billingServiceGrpcClient;
    }

    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();

        return patients.stream()
                .map(PatientMapper::toPatientResponseDTO).toList();
    }

    public PatientResponseDTO createPatient(@Valid PatientRequestDTO patientResponseDTO) {

        if (patientRepository.existsByEmail(patientResponseDTO.getEmail())) {
            throw new EmailAlreadyExistsExeption( "A patient with this email "
            + "already exists" + patientResponseDTO.getEmail());
        }

        Patient newPatient = patientRepository.save(
                PatientMapper.toModel(patientResponseDTO));

        billingServiceGrpcClient.createBillingAccount(newPatient.getId().toString(),
                newPatient.getName(), newPatient.getEmail());

        return PatientMapper.toPatientResponseDTO(newPatient);
    }


    public PatientResponseDTO updatePatient(@Valid PatientRequestDTO patientResponseDTO, UUID patientId) {

        Patient patient = patientRepository.findById(patientId).orElseThrow(
                () -> new PatientNotFoundException("Patient not found with ID: " + patientId));

        if (patientRepository.existsByEmailAndIdNot(patientResponseDTO.getEmail(), patientId)) {
            throw new EmailAlreadyExistsExeption( "A patient with this email "
                    + "already exists" + patientResponseDTO.getEmail());
        }

        patient.setName(patientResponseDTO.getName());
        patient.setAddress(patientResponseDTO.getAddress());
        patient.setEmail(patientResponseDTO.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientResponseDTO.getDateOfBirth()));

        Patient updatePatient = patientRepository.save(patient);

        return PatientMapper.toPatientResponseDTO(updatePatient);
    }

    public void deletePatient(UUID patientId) {
        patientRepository.deleteById(patientId);
    }

}
