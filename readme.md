# Campeonato de Dados Backend

![java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![html](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![css](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)

Projeto para gerenciamento de posições em um campeonato, permitindo cadastrar os participantes, pontos em casa e pontos fora de casa
O projeto é foi criado apenas para fins de aprendizado e hobby pessoal, e não permite rodar nenhum campeonato. Caso queira ver os projetos que executam um campeonato, tenho esses dois: [Campeonato de Dados](https://github.com/Firzen592798/CampeonatoDeDados) [Campeonato de Dados Indireto](https://github.com/Firzen592798/CampeonatoDeDadosIndireto)

# Telas do sistema
Pendente...

# Obtenção do Token para API de backend
A API que existe aqui não tem nenhuma utilidade prática senão estudos com autenticação JWT para utilização de serviços rest, e o único endpoint é rest/campeonato/list

Caso seja necessário obter o token, acessar via postman a seguinte URL:
localhost:8080/firzen/authenticate
Passa o texto como raw e no formato escolhe json. Um token deve ser retornado
{
    "username": "admin",
    "password":"admin"
}

localhost:8080/firzen/rest/campeonato/list
Incluir o Authorization no Header com Bearer precedido do token retornado pelo authenticate

Tutorial de referência

https://www.javainuse.com/spring/boot-jwt



