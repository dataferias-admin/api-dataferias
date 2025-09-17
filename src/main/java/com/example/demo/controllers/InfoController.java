package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
    public long countFuncionarios() {
        return funcionarioRepository.count();
    }

    @GetMapping("/solicitacoes/pendentes")
    public long countSolicitacoesPendentes() {
        return solicitacaoRepository.countByStatus(SolicitacaoStatus.pendente);
    }

    @GetMapping("/solicitacoes/aprovadas")
    public long countSolicitacoesAprovadas() {
        return solicitacaoRepository.countByStatus(SolicitacaoStatus.aprovado);
    }

    @GetMapping("/solicitacoes/rejeitadas")
    public long countSolicitacoesRejeitadas() {
        return solicitacaoRepository.countByStatus(SolicitacaoStatus.rejeitado);
    }
}
