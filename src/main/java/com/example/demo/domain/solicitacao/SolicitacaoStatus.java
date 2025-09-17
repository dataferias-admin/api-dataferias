package com.example.demo.domain.solicitacao;

public enum SolicitacaoStatus {
    pendente("pendente"),
    rejeitado("rejeitado"),
    aprovado("aprovado");

    private String status;

    SolicitacaoStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }
}
