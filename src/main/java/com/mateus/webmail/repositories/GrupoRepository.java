package com.mateus.webmail.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mateus.webmail.domain.Grupo;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Integer>{
}
