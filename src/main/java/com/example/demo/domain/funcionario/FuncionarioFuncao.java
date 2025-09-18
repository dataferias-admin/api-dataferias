package com.example.demo.domain.funcionario;

public enum FuncionarioFuncao {
    operacional("operacional"),
    gestor("gestor");

    private String funcao;

    FuncionarioFuncao(String funcao){
        this.funcao = funcao;
    }

    public String getFuncao(){
        return funcao;
    }

    public boolean isGestor() {
        return this == gestor;
    }
}
