package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repositories.FuncionarioRepository;
import com.example.demo.repositories.SolicitacaoRepository;
import com.example.demo.domain.solicitacao.SolicitacaoStatus;

@RestController
@RequestMapping("/info")
public class InfoController {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private SolicitacaoRepository solicitacaoRepository;


    @GetMapping("/funcionarios/count")
    @PreAuthorize("@jwtUtil.isGestorFromRequest(#root)")
    public long countFuncionarios() {
        return funcionarioRepository.count();
    }

    @GetMapping("/solicitacoes/pendentes")
    @PreAuthorize("@jwtUtil.isGestorFromRequest(#root)")
    public long countSolicitacoesPendentes() {
        return solicitacaoRepository.countByStatus(SolicitacaoStatus.pendente);
    }

    @GetMapping("/solicitacoes/aprovadas")
    @PreAuthorize("@jwtUtil.isGestorFromRequest(#root)")
    public long countSolicitacoesAprovadas() {
        return solicitacaoRepository.countByStatus(SolicitacaoStatus.aprovado);
    }

    @GetMapping("/solicitacoes/rejeitadas")
    @PreAuthorize("@jwtUtil.isGestorFromRequest(#root)")
    public long countSolicitacoesRejeitadas() {
        return solicitacaoRepository.countByStatus(SolicitacaoStatus.rejeitado);
    }
}
