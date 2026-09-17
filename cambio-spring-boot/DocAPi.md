# Endereço e Documentação da API Utilizada

**API:** Frankfurter API

**Endereço base:** `https://api.frankfurter.app`

**Documentação oficial:** https://www.frankfurter.app/docs/

## Sobre a API

A Frankfurter API é uma API pública de cotações de câmbio, gratuita e que não exige
cadastro nem chave de acesso (API key). Por isso foi escolhida para este projeto
acadêmico: não é necessário configurar credenciais nem lidar com limites de uso.

## Endpoint utilizado no projeto

```
GET https://api.frankfurter.app/latest?amount=67&from=USD&to=BRL
```

Parâmetros:

- `amount` — valor a converter
- `from` — código da moeda de origem (ex.: `USD`)
- `to` — código da moeda de destino (ex.: `BRL`)

Exemplo de resposta:

```json
{
  "amount": 67.0,
  "base": "USD",
  "date": "2024-06-10",
  "rates": {
    "BRL": 340.96
  }
}
```