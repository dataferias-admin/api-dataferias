package com.example.demo.domain.solicitacao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.EnumType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "solicitacoes")
@Table(name = "solicitacoes")

public class Solicitacao {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid DEFAULT gen_random_uuid()")
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "fk_matricula_solicitante")
    private com.example.demo.domain.funcionario.Funcionario solicitante;

    private LocalDate data_inicio;
    private LocalDate data_fim;
    private String observacao_solicitante;

    @ManyToOne
    @JoinColumn(name = "fk_matricula_avaliador")
    private com.example.demo.domain.funcionario.Funcionario avaliador;

    private String justificativa_avaliador;

    @Enumerated(EnumType.STRING)
    private SolicitacaoStatus status = SolicitacaoStatus.pendente;

    private LocalDate data_solicitacao;
    private LocalDate data_avaliacao;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public com.example.demo.domain.funcionario.Funcionario getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(com.example.demo.domain.funcionario.Funcionario solicitante) {
        this.solicitante = solicitante;
    }

    public LocalDate getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(LocalDate data_inicio) {
        this.data_inicio = data_inicio;
    }

    public LocalDate getData_fim() {
        return data_fim;
    }

    public void setData_fim(LocalDate data_fim) {
        this.data_fim = data_fim;
    }

    public String getObservacao_solicitante() {
        return observacao_solicitante;
    }

    public void setObservacao_solicitante(String observacao_solicitante) {
        this.observacao_solicitante = observacao_solicitante;
    }

    public com.example.demo.domain.funcionario.Funcionario getAvaliador() {
        return avaliador;
    }

    public void setAvaliador(com.example.demo.domain.funcionario.Funcionario avaliador) {
        this.avaliador = avaliador;
    }

    public String getJustificativa_avaliador() {
        return justificativa_avaliador;
    }

    public void setJustificativa_avaliador(String justificativa_avaliador) {
        this.justificativa_avaliador = justificativa_avaliador;
    }

    public SolicitacaoStatus getStatus() {
        return status;
    }

    public void setStatus(SolicitacaoStatus status) {
        this.status = status;
    }

    public LocalDate getData_solicitacao() {
        return data_solicitacao;
    }

    public void setData_solicitacao(LocalDate data_solicitacao) {
        this.data_solicitacao = data_solicitacao;
    }

    public LocalDate getData_avaliacao() {
        return data_avaliacao;
    }

    public void setData_avaliacao(LocalDate data_avaliacao) {
        this.data_avaliacao = data_avaliacao;
    }
}
