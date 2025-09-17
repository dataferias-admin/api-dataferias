package com.example.demo.domain.funcionario;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

@Entity(name = "funcionarios")
@Table(name = "funcionarios")

public class Funcionario {
    @Id
    private String matricula;
    private String nome;
    @Enumerated(EnumType.STRING)
    private FuncionarioFuncao funcao;
    private String senha;

    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public FuncionarioFuncao getFuncao() {
        return funcao;
    }
    public void setFuncao(FuncionarioFuncao funcao) {
        this.funcao = funcao;
    }
    public String getSenha() {
        return senha;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public void setSenha(String senha) {
        this.senha = senha;
    }
}