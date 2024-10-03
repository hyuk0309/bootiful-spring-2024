package org.example.bootifulspring2024.adoption;

import org.springframework.data.repository.ListCrudRepository;

interface DogRepository extends ListCrudRepository<Dog, Integer> {
}
