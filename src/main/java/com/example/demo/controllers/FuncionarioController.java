package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.example.demo.domain.funcionario.Funcionario;
import com.example.demo.repositories.FuncionarioRepository;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping
    public List<Funcionario> listarFuncionarios() {
        return funcionarioRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Funcionario> criarFuncionario(@RequestBody Funcionario funcionario) {
        String salt = BCrypt.gensalt();
        String senhaHash = BCrypt.hashpw(funcionario.getSenha(), salt);
        funcionario.setSenha(senhaHash);
        Funcionario salvo = funcionarioRepository.save(funcionario);
        return new ResponseEntity<>(salvo, HttpStatus.CREATED);
    }
}
