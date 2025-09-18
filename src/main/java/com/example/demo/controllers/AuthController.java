package com.example.demo.controllers;

import com.example.demo.domain.funcionario.Funcionario;
import com.example.demo.repositories.FuncionarioRepository;
import com.example.demo.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String matricula = loginData.get("matricula");
        String senha = loginData.get("senha");
        Funcionario funcionario = funcionarioRepository.findByMatricula(matricula);
        if (funcionario == null || !passwordEncoder.matches(senha, funcionario.getSenha())) {
            return ResponseEntity.status(401).body("Matrícula ou senha inválidos");
        }
        boolean isGestor = funcionario.getFuncao() != null && funcionario.getFuncao().isGestor();
        String token = jwtUtil.generateToken(matricula, isGestor);
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        Map<String, Object> funcionarioData = new HashMap<>();
        funcionarioData.put("matricula", funcionario.getMatricula());
        funcionarioData.put("nome", funcionario.getNome());
        funcionarioData.put("funcao", funcionario.getFuncao() != null ? funcionario.getFuncao().name() : null);
        response.put("funcionario", funcionarioData);
        return ResponseEntity.ok(response);
    }
}
