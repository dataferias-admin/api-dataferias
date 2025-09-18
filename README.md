
# API Dataferias

Documentação das rotas da API para gerenciamento de funcionários e solicitações de férias.

---

## Autenticação

### Login

**POST** `/auth/login`

**Request Body:**
```json
{
    "matricula": "77777",
    "senha": "123456"
}
```

**Response 200:**
```json
{
    "funcionario": {
        "funcao": "gestor",
        "matricula": "77777",
        "nome": "Túlio Fernandes"
    },
    "token": "<jwt_token>"
}
```

---


## Funcionários

### Listar Funcionários

**GET** `/funcionarios`

**Response 200:**
```json
[
    {
        "matricula": "12345",
        "nome": "João Silva",
        "funcao": "operacional"
    },
    {
        "matricula": "67880",
        "nome": "Maria Santos",
        "funcao": "gestor"
    }
    // ...
]
```

---


### Criar Funcionário

**POST** `/funcionarios`

**Request Body:**
```json
{
    "matricula": "77777",
    "nome": "Túlio Fernandes",
    "funcao": "gestor",
    "senha": "123456"
}
```

**Response 201:**
```json
{
    "matricula": "77777",
    "nome": "Túlio Fernandes",
    "funcao": "gestor"
}
```

---


## Solicitações

### Listar Solicitações (Gestor)

**GET** `/solicitacoes`

**Headers:**
- Authorization: Bearer `<token>`
- Funcionário precisa ser gestor

**Response 200:**
```json
[
    {
        "uuid": "6953bc00-8dde-48d4-ab91-4ebefcbe9966",
        "solicitante": {
            "matricula": "12345",
            "nome": "João Silva",
            "funcao": "operacional"
        },
        "data_inicio": "2025-09-15",
        "data_fim": "2025-10-15",
        "observacao_solicitante": "Viagem em família",
        "avaliador": {
            "matricula": "67880",
            "nome": "Maria Santos",
            "funcao": "gestor"
        },
        "justificativa_avaliador": "Solicitado fora do prazo institucional",
        "status": "rejeitado",
        "data_solicitacao": "2025-09-13",
        "data_avaliacao": "2025-09-14"
    },
    {
        "uuid": "e984864f-6c39-4a30-83c8-a7fd83b11b1c",
        "solicitante": {
            "matricula": "77777",
            "nome": "Túlio Fernandes",
            "funcao": "gestor"
        },
        "data_inicio": "2025-10-01",
        "data_fim": "2025-10-30",
        "observacao_solicitante": "Preciso dar uma descansada nesse final de ano. Volto com força para cumprir as baixas de final de ano!",
        "avaliador": null,
        "justificativa_avaliador": null,
        "status": "pendente",
        "data_solicitacao": "2025-09-18",
        "data_avaliacao": null
    }
    // ...
]
```

---


### Listar Solicitações Pendentes (Gestor)

**GET** `/solicitacoes/pendentes`

**Headers:**
- Authorization: Bearer `<token>`
- Funcionário precisa ser gestor

**Response 200:**
```json
[
    {
        "uuid": "a837cdcb-e9f6-48a9-b168-dd98b9c4c05f",
        "solicitante": {
            "matricula": "25525",
            "nome": "Matheus Silveira",
            "funcao": "operacional"
        },
        "data_inicio": "2026-02-02",
        "data_fim": "2026-03-04",
        "observacao_solicitante": "Curtir o carnaval",
        "avaliador": null,
        "justificativa_avaliador": null,
        "status": "pendente",
        "data_solicitacao": "2025-09-17",
        "data_avaliacao": null
    }
    // ...
]
```

---


### Listar Solicitações de um Funcionário

**GET** `/solicitacoes/funcionario/{matricula}`

**Headers:**
- Authorization: Bearer `<token>`

**Response 200:**
```json
[
    {
        "uuid": "6953bc00-8dde-48d4-ab91-4ebefcbe9966",
        "solicitante": {
            "matricula": "12345",
            "nome": "João Silva",
            "funcao": "operacional"
        },
        "data_inicio": "2025-09-15",
        "data_fim": "2025-10-15",
        "observacao_solicitante": "Viagem em família",
        "avaliador": {
            "matricula": "67880",
            "nome": "Maria Santos",
            "funcao": "gestor"
        },
        "justificativa_avaliador": "Solicitado fora do prazo institucional",
        "status": "rejeitado",
        "data_solicitacao": "2025-09-13",
        "data_avaliacao": "2025-09-14"
    }
    // ...
]
```

---


### Criar Solicitação de Férias

**POST** `/solicitacoes`

**Headers:**
- Authorization: Bearer `<token>`

**Request Body:**
```json
{
    "solicitante": { "matricula": "12345" },
    "data_inicio": "2027-01-01",
    "data_fim": "2026-01-31",
    "observacao_solicitante": "Curtir o ano novo"
}
```

**Response 201:**
```json
{
    "uuid": "63aab397-f74a-471c-a19d-480678795971",
    "solicitante": {
        "matricula": "12345",
        "nome": "João Silva",
        "funcao": "operacional"
    },
    "data_inicio": "2027-01-01",
    "data_fim": "2026-01-31",
    "observacao_solicitante": "Curtir o ano novo",
    "avaliador": null,
    "justificativa_avaliador": null,
    "status": "pendente",
    "data_solicitacao": "2025-09-18",
    "data_avaliacao": null
}
```

---


### Avaliar Solicitação (Gestor)

**PATCH** `/solicitacoes/{uuid}`

**Headers:**
- Authorization: Bearer `<token>`
- Funcionário precisa ser gestor

**Request Body:**
```json
{
    "avaliador": { "matricula": "77777" },
    "justificativa_avaliador": "Solicitação repetida",
    "status": "rejeitado"
}
```

**Response 200:**
```json
{
    "uuid": "63aab397-f74a-471c-a19d-480678795971",
    "solicitante": {
        "matricula": "12345",
        "nome": "João Silva",
        "funcao": "operacional"
    },
    "data_inicio": "2027-01-01",
    "data_fim": "2026-01-31",
    "observacao_solicitante": "Curtir o ano novo",
    "avaliador": {
        "matricula": "77777",
        "nome": "Túlio Fernandes",
        "funcao": "gestor"
    },
    "justificativa_avaliador": "Solicitação repetida",
    "status": "rejeitado",
    "data_solicitacao": "2025-09-18",
    "data_avaliacao": "2025-09-18"
}
```

---


## Informações Gerais

### Contar Funcionários

**GET** `/info/funcionarios/count`

**Headers:**
- Authorization: Bearer `<token>`
- Funcionário precisa ser gestor

**Response 200:**
```
RETORNA UM NÚMERO
```

---

### Contar Solicitações Pendentes

**GET** `/info/solicitacoes/pendentes`

**Headers:**
- Authorization: Bearer `<token>`
- Funcionário precisa ser gestor

**Response 200:**
```
RETORNA UM NÚMERO
```

---

### Contar Solicitações Rejeitadas

**GET** `/info/solicitacoes/rejeitadas`

**Headers:**
- Authorization: Bearer `<token>`
- Funcionário precisa ser gestor

**Response 200:**
```
RETORNA UM NÚMERO
```

---

### Contar Solicitações Aprovadas

**GET** `/info/solicitacoes/aprovadas`

**Headers:**
- Authorization: Bearer `<token>`
- Funcionário precisa ser gestor

**Response 200:**
```
RETORNA UM NÚMERO
```