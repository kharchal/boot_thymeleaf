package ua.com.hav.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.hav.model.Pet;

@Repository
public interface PetRepo extends JpaRepository<Pet, Long> {
}
