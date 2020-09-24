package com.tutorial.crud.repository;

import com.tutorial.crud.entity.Rol;
import com.tutorial.crud.enums.RolName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol,Integer> {
    Optional<Rol> findByRolName(RolName rolName);
}
