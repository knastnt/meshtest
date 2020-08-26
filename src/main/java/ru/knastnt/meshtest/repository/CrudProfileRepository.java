package ru.knastnt.meshtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.knastnt.meshtest.model.Profile;

public interface CrudProfileRepository extends JpaRepository<Profile, Integer> {

    Profile getByEmailIgnoreCase(String email);

    Profile getTopByOrderByIdDesc();

}
