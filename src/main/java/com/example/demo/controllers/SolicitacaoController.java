package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.example.demo.domain.solicitacao.Solicitacao;
import com.example.demo.repositories.FuncionarioRepository;
import com.example.demo.repositories.SolicitacaoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/solicitacoes")
public class SolicitacaoController {
    @Autowired
    private SolicitacaoRepository solicitacaoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;


    @GetMapping
    @PreAuthorize("@jwtUtil.isGestorFromRequest(#root)")
    public List<Solicitacao> listarSolicitacaos() {
        return solicitacaoRepository.findAll();
    }

    @GetMapping("/pendentes")
    @PreAuthorize("@jwtUtil.isGestorFromRequest(#root)")
    public List<Solicitacao> listarSolicitacoesPendentes() {
        return solicitacaoRepository.findByStatus(com.example.demo.domain.solicitacao.SolicitacaoStatus.pendente);
    }

    @GetMapping("/funcionario/{matricula}")
    @PreAuthorize("isAuthenticated()")
    public List<Solicitacao> listarSolicitacaoByMatricula(@PathVariable String matricula) {
        return solicitacaoRepository.findBySolicitanteMatricula(matricula);
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Solicitacao> criarSolicitacao(@RequestBody Solicitacao solicitacao) {
        // Buscar e associar o solicitante existente
        if (solicitacao.getSolicitante() != null) {
            String matricula = solicitacao.getSolicitante().getMatricula();
            solicitacao.setSolicitante(funcionarioRepository.findById(matricula).orElse(null));
        }
        // Buscar e associar o avaliador existente, se informado
        if (solicitacao.getAvaliador() != null) {
            String matricula = solicitacao.getAvaliador().getMatricula();
            solicitacao.setAvaliador(funcionarioRepository.findById(matricula).orElse(null));
        }
        // Definir data_solicitacao automaticamente se não enviada
        if (solicitacao.getData_solicitacao() == null) {
            solicitacao.setData_solicitacao(java.time.LocalDate.now());
        }
        Solicitacao salva = solicitacaoRepository.save(solicitacao);
        return new ResponseEntity<>(salva, HttpStatus.CREATED);
    }

    @PatchMapping("/{uuid}")
    @PreAuthorize("@jwtUtil.isGestorFromRequest(#root)")
    public ResponseEntity<Solicitacao> responderSolicitacao(
        @PathVariable("uuid") UUID uuid,
        @RequestBody Solicitacao solicitacaoPatch) {
        Optional<Solicitacao> optSolicitacao = solicitacaoRepository.findById(uuid);
        if (optSolicitacao.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Solicitacao solicitacao = optSolicitacao.get();
        // Atualiza campos do avaliador
        if (solicitacaoPatch.getAvaliador() != null) {
            String matricula = solicitacaoPatch.getAvaliador().getMatricula();
            solicitacao.setAvaliador(funcionarioRepository.findById(matricula).orElse(null));
        }
        if (solicitacaoPatch.getJustificativa_avaliador() != null) {
            solicitacao.setJustificativa_avaliador(solicitacaoPatch.getJustificativa_avaliador());
        }
        if (solicitacaoPatch.getData_avaliacao() != null) {
            solicitacao.setData_avaliacao(solicitacaoPatch.getData_avaliacao());
        } else {
            solicitacao.setData_avaliacao(java.time.LocalDate.now());
        }
        if (solicitacaoPatch.getStatus() != null) {
            solicitacao.setStatus(solicitacaoPatch.getStatus());
        }
        Solicitacao salva = solicitacaoRepository.save(solicitacao);
        return ResponseEntity.ok(salva);
    }
}