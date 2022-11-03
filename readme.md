# Getting Started

Projeto para gerenciamento de posições em um campeonato, permitindo cadastrar os participantes, pontos em casa e pontos fora de casa
O projeto é foi criado apenas para fins de aprendizado e hobby pessoal, e não faz sentido para o público geral

A pagina principal do projeto é localhost:8080/firzen/campeonato

Caso seja necessário obter o token, acessar via postman a seguinte URL:
localhost:8080/firzen/authenticate
Passa o texto como raw e no formato escolhe json. Um token deve ser retornado
{
    "username": "admin",
    "password":"admin"
}

localhost:8080/firzen/rest/campeonato/list
Incluir o Authorization no Header

Tutorial de referência

https://www.javainuse.com/spring/boot-jwt

Desenvolvido por Firzen592798