# Projeto Gestor de campanhas

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/24755ef707014807b75950e4aa23141d)](https://www.codacy.com/app/ander-f-silva/missao-marte?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=ander-f-silva/missao-marte&amp;utm_campaign=Badge_Grade)
[![Coverage Status](https://coveralls.io/repos/github/ander-f-silva/gestor-campanhas/badge.svg?branch=master)](https://coveralls.io/github/ander-f-silva/gestor-campanhas?branch=master)

Projeto responsável pelas apis que irão gerenciar os dados das campanhas e dos sócios torcedores.

## Tecnologias utilizadas

* Linguagem Java - Versão 1.8 (Oracle 1.8.0_121)

```
java version "1.8.0_121"
Java(TM) SE Runtime Environment (build 1.8.0_121-b13)
Java HotSpot(TM) 64-Bit Server VM (build 25.121-b13, mixed mode)
```

* Maven 3 - Ferramenta de Build

```
Apache Maven 3.3.9 (bb52d8502b132ec0a5a3f4c09453c07478323dc5; 2015-11-10T14:41:47-02:00)
Maven home: /apache-maven-3.3.9
Java version: 1.8.0_121, vendor: Oracle Corporation
Java home: /jdk1.8.0_121/jre
Default locale: pt_BR, platform encoding: UTF-8
OS name: "linux", version: "4.6.0-040600-lowlatency", arch: "amd64", family: "unix"
```

O maven esta embarcado com o projeto, basta utilizar os comandos 

* Spring Boot - Framerwork Web para geração das API's (versão 2.0.0) com Netty

O repositório utilizado é o Github, onde utilizei o Git flow com a branch develop e master para gerenciar o fonte.

Para solucionar os dois primeiros problemas para ganhar escalabilidade e independência entreos as apis, adotei como arquitetura
o modelo arquitetural Microserviços, a forma de programar com modelo reativo (orientado a eventos, assincrono).

Vejo que apis de campanha e socio torcedor precisavam desta solução, pois se api campanha estiver fora do ar, é possivel manter a api 
de sócio torcedor.

Para persistencia estou utilizando banco NOSQL Mongo embarcado (esta funcionando em memória) para cada api, 
o serviço de notificações usei o fila ampq (também em memória)

Utilizei o framerk Junit (sufixo Test) para os teste unitários e TestNg (sufixo IT) para o teste integrado.


* Motivaçções sobre o Framework Spring

Utilizei este framework por estar maduro para desenlvolver aplicações na arquitetura de Microserviços (por ter o container integrado)
e por ter várias soluções para integrar com banco de dados (relacional), serviço de menssageria, uma stack forte para serviço de cloud e 
por ser muito facil para montar uma e usar os frameworks de teste.

## Para realizar o build, teste unitários e iniciar o software

Para realizar o build do projeto e realizar somente os testes unitários execute o comando:

```
mvn clean package
```

Para iniciar o software: 

Acessar a pasta ./gestor-campanhas/campanhas-api e digitar o comando abaixo de depois acessar
./gestor-campanhas/socio-torcedor-api


``` 
mvn spring-boot:run
```

## Gestão do Projeto e técnicas para construção da API

Não precisei usar Kaban paraa administrar as atividades, tendo conhecimento do problema.

Mas as etapas foram:

* Passo 0: Criação do projeto no https://start.spring.io/
* Passo 1: Contrução das classes de dominio;
* Passo 2: Construção dos testes unitários;
* Passo 3: Construção da API e mecanismo para armazenar os dados;
