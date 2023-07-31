package com.cg.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.pms.project.Patient;
import com.cg.service.PatientService;

@RestController
//@RequestMapping("/patientonboarding")
public class PatientController {
private final PatientService patientService;

// Constructor injection
public PatientController(PatientService patientService) {
this.patientService = patientService;
}


@GetMapping("/{id}")
public Patient getPatient(@PathVariable Long id) {
return patientService.getPatient(id);
}

@PutMapping("/{id}")
public Patient updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
return patientService.updatePatient(id, patient);
}


@PostMapping("/patients")
public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {
	Patient createdPatient = patientService.addPatient(patient);
	return new ResponseEntity<>(createdPatient, HttpStatus.CREATED); }

// Add endpoints for patient onboarding, editing, and history tracking
}