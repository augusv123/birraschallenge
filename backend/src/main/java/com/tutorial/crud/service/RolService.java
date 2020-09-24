package com.tutorial.crud.service;

import com.tutorial.crud.entity.Rol;
import com.tutorial.crud.enums.RolName;
import com.tutorial.crud.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional

public class RolService {

    @Autowired
    RolRepository rolRepository;

    public Optional<Rol>  getByRolName(RolName rolName){
        return rolRepository.findByRolName(rolName);
    }
    public void save(Rol rol){
       rolRepository.save(rol);
    }


}
