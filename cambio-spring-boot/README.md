# 💱 Câmbio — Sistema de Conversão de Moedas

**Integrantes:** Vito Luigi Zanata e Vitor de Lima
**Disciplina:** Programação Orientada a Objetos
**Tema:** Conversor de moedas com cotações em tempo real e lista de moedas favoritas

## Descrição do sistema

O Câmbio é uma aplicação web feita em Java com Spring Boot que permite:

- Converter um valor de uma moeda para outra, usando cotações reais obtidas de uma
  API externa gratuita;
- Consultar a cotação utilizada em cada conversão (ex.: `1 USD = 5,0890 BRL`);
- Cadastrar moedas favoritas (pares de moeda, como `USD → BRL`), que aparecem em uma
  área lateral da tela inicial já com a cotação atual;
- Gerenciar essas moedas favoritas com um CRUD completo (cadastrar, listar, editar e
  excluir), persistido em um banco de dados MySQL através do Spring Data JPA.

Toda a interação acontece por formulários HTML tradicionais (GET/POST) e Thymeleaf,
sem nenhuma linha de JavaScript — todo o processamento é feito no servidor, pelos
Controllers do Spring Boot.

## API utilizada

**Frankfurter API** — `https://api.frankfurter.app`
Documentação: https://www.frankfurter.app/docs/

API pública, gratuita e sem necessidade de chave de acesso, usada para obter as
cotações de câmbio em tempo real consumidas pelo conversor e pela área de moedas
favoritas.