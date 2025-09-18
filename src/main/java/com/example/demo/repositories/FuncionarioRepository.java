package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.funcionario.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, String> {
	Funcionario findByMatricula(String matricula);
}
