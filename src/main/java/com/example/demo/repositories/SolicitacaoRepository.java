package com.example.demo.repositories;

import java.util.List;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.solicitacao.Solicitacao;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, UUID> {
	List<Solicitacao> findBySolicitanteMatricula(String matricula);
	long countByStatus(com.example.demo.domain.solicitacao.SolicitacaoStatus status);
	List<Solicitacao> findByStatus(com.example.demo.domain.solicitacao.SolicitacaoStatus status);
}
