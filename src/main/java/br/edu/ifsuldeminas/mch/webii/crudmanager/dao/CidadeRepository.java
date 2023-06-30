package br.edu.ifsuldeminas.mch.webii.crudmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifsuldeminas.mch.webii.crudmanager.model.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer>{}
