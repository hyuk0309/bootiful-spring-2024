package org.example.bootifulspring2024.adoption;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

record Dog(@Id Integer id, String name, String description, LocalDate dob, String owner) {
}
