package com.mateus.webmail.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mateus.webmail.domain.Associado;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Integer> {
}
