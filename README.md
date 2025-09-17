{
  "openapi": "3.0.0",
  "info": {
    "title": "dataferias",
    "version": "1.0.0",
    "description": ""
  },
  "servers": [
    {
      "url": "localhost"
    }
  ],
  "paths": {
    "/funcionarios": {
      "parameters": [],
      "get": {
        "summary": "funcionarios",
        "tags": [],
        "parameters": [],
        "responses": {}
      },
      "post": {
        "summary": "funcionarios",
        "tags": [],
        "parameters": [],
        "responses": {},
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "type": "object",
                "properties": {
                  "matricula": {
                    "type": "string",
                    "format": "utc-millisec"
                  },
                  "nome": {
                    "type": "string"
                  },
                  "funcao": {
                    "type": "string"
                  },
                  "senha": {
                    "type": "string",
                    "format": "color"
                  }
                }
              },
              "example": {
                "matricula": "25525",
                "nome": "Matheus Silveira",
                "funcao": "operacional",
                "senha": "123456"
              }
            }
          }
        }
      }
    },
    "/solicitacoes": {
      "parameters": [],
      "get": {
        "summary": "solicitacoes",
        "tags": [],
        "parameters": [],
        "responses": {}
      },
      "post": {
        "summary": "solicitacoes",
        "tags": [],
        "parameters": [],
        "responses": {},
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "type": "object",
                "properties": {
                  "solicitante": {
                    "type": "object",
                    "properties": {
                      "matricula": {
                        "type": "string",
                        "format": "utc-millisec"
                      }
                    }
                  },
                  "data_inicio": {
                    "type": "string",
                    "format": "date"
                  },
                  "data_fim": {
                    "type": "string",
                    "format": "date"
                  },
                  "observacao_solicitante": {
                    "type": "string"
                  }
                }
              },
              "example": {
                "solicitante": {
                  "matricula": "25525"
                },
                "data_inicio": "2025-12-08",
                "data_fim": "2026-01-07",
                "observacao_solicitante": "Curtir o ano novo"
              }
            }
          }
        }
      }
    },
    "/solicitacoes/pendentes": {
      "parameters": [],
      "get": {
        "summary": "solicitacoes pendentes",
        "tags": [],
        "parameters": [],
        "responses": {}
      }
    },
    "/solicitacoes/funcionario/45455": {
      "parameters": [],
      "get": {
        "summary": "solicitacoes por funcionário",
        "tags": [],
        "parameters": [],
        "responses": {}
      }
    },
    "/solicitacoes/15e311bb-0987-48c6-b0fa-32456e896803": {
      "parameters": [],
      "patch": {
        "summary": "solicitacoes",
        "tags": [],
        "parameters": [],
        "responses": {},
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "type": "object",
                "properties": {
                  "avaliador": {
                    "type": "object",
                    "properties": {
                      "matricula": {
                        "type": "string",
                        "format": "utc-millisec"
                      }
                    }
                  },
                  "justificativa_avaliador": {
                    "type": "string"
                  },
                  "status": {
                    "type": "string"
                  }
                }
              },
              "example": {
                "avaliador": {
                  "matricula": "40028"
                },
                "justificativa_avaliador": "LGTM",
                "status": "aprovado"
              }
            }
          }
        }
      }
    },
    "/info/funcionarios/count": {
      "parameters": [],
      "get": {
        "summary": "info funcionarios count",
        "tags": [],
        "parameters": [],
        "responses": {}
      }
    },
    "/info/solicitacoes/pendentes": {
      "parameters": [],
      "get": {
        "summary": "info pendentess count",
        "tags": [],
        "parameters": [],
        "responses": {}
      }
    },
    "/info/solicitacoes/rejeitadas": {
      "parameters": [],
      "get": {
        "summary": "info rejeitadas count",
        "tags": [],
        "parameters": [],
        "responses": {}
      }
    },
    "/info/solicitacoes/aprovadas": {
      "parameters": [],
      "get": {
        "summary": "info aprovadas count",
        "tags": [],
        "parameters": [],
        "responses": {}
      }
    }
  }
}
