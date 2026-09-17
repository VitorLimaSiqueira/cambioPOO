# Funcionalidade Implementada com a API

A Frankfurter API é usada em dois pontos do sistema Câmbio:

## 1. Conversor de moedas

A cada conversão solicitada pelo usuário (valor, moeda de origem e moeda de
destino), o sistema consulta a API com esses parâmetros, recebe o valor já
convertido e calcula, a partir dele, a cotação unitária (ex.: `1 USD = 5,0890 BRL`),
exibida junto ao resultado.

## 2. Moedas favoritas

Na área lateral da tela inicial, para cada par de moedas que o usuário cadastrou
como favorito (ex.: `USD → BRL`), o sistema faz uma nova consulta à API pedindo a
cotação de 1 unidade, e exibe essa cotação atualizada ao lado do favorito.

## Como os dados são processados (não apenas exibidos)

1. O sistema monta a URL da API com o valor e as moedas escolhidas;
2. Faz a requisição HTTP e converte o JSON retornado em um objeto Java;
3. Lê o valor convertido dentro do JSON (`rates`) e calcula a cotação unitária
   (`resultado / valor`), que a API não fornece diretamente;
4. Formata os valores no padrão brasileiro (vírgula decimal) antes de exibir;
5. Repete a consulta para cada favorito cadastrado, montando a frase
   `1 USD = 5,0890 BRL` ao lado de cada um.

Em nenhum momento o JSON bruto da API é mostrado ao usuário — os dados são sempre
processados e formatados antes de chegar à tela.