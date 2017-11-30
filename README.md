# Projeto Gestor de Campanhas

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/f99952dce79f46cd816e5f2b2815588c)](https://www.codacy.com/app/ander-f-silva/gestor-campanhas?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=ander-f-silva/gestor-campanhas&amp;utm_campaign=Badge_Grade)
[![Coverage Status](https://coveralls.io/repos/github/ander-f-silva/gestor-campanhas/badge.svg?branch=master)](https://coveralls.io/github/ander-f-silva/gestor-campanhas?branch=master)

As tarefas 1 e 2 são projetos para gerencias os micro serviços de **campanha** e **sócio torcedor**
e as demais tarefas estarão na Wiki.

## Tecnologias utilizadas

* Linguagem de Programação Java;

```
java version "1.8.0_121"
Java(TM) SE Runtime Environment (build 1.8.0_121-b13)
Java HotSpot(TM) 64-Bit Server VM (build 25.121-b13, mixed mode)
```

* Ferramenta de Build e Gestão de Dependências Maven ;

```
Apache Maven 3.3.9 (bb52d8502b132ec0a5a3f4c09453c07478323dc5; 2015-11-10T14:41:47-02:00)
Maven home: /apache-maven-3.3.9
Java version: 1.8.0_121, vendor: Oracle Corporation
Java home: /jdk1.8.0_121/jre
Default locale: pt_BR, platform encoding: UTF-8
OS name: "linux", version: "4.6.0-040600-lowlatency", arch: "amd64", family: "unix"
```

* Framerwork Web Spring (Boot, Cloud, Netfilx OSS , Data e React);

```
Para solucionar os dois primeiros problemas adotei como arquitetura o modelo Micro Serviços, pois os serviços precisavam ser autônomos. Tanto que cadas serviço possui seu próprio container e banco.

Nesta solução caso o serviço de campanha esteja fora do ar, o serviço de sócio torcedor não será afetado.
```

* Banco de Dados No Sql Mongo Mock (embarcado e armazenamento em memória);

* Serviço de Mensageria Active MQ (embarcado e armazenamento em memória);

* Frameworks de Testes são Junit (sufixo Test) para os teste unitários e TestNg (sufixo IT) para o teste integrado;

## Para realizar teste, construção e publicação
```
Importante mencionar que criei dois serviços extras, um para centralizar as configurações através de um container e outro para registrar os serviços que estão disponíveis:

1) servico-configuracao -> serviço de middleware para apontar para um repositório com arquivos de configuração dos micro serviços;

2) servico-registro -> serviço que registras os micro serviços atraveś do Netflix Eureka Server. Ao subir o projeto acesso http://localhost:9082.
```

Passos:

Observação:  Caso não tenha o maven instalado use o wrapper que se encontra na raiz do projeto (./mvnw) 

1) Abrir o terminal e  digitar o comando para realizar o build:
```
cd ./gestor-campanhas && mvn clean package -DskipTests 
```

2) Abrir a diretório servico-configuracao:
``` 
 cd ./servidor-configuracao && mvn spring-boot:run
```

3) Abrir outro terminal e abrir o diretório servico-registro :
``` 
 cd ./servidor-registro && mvn spring-boot:run

Acesso o navegador e digite http://localhost:9082
```

4) Abrir outro terminal e abrir o diretório gestor-campanhas:

``` 
cd ./gestor-campanhas && mvn test
```

5) Abrir outro terminal e abrir o diretório campanha-api:

``` 
 cd ./campanha-api && mvn spring-boot:run
```

6) Abrir outro terminal e abrir o diretório socio-torcedor-api:

``` 
 cd ./socio-torcedor-api && mvn spring-boot:run
```



## Passos para construção dos projetos

Mas as etapas foram:

* Passo 0: Criação do projeto no https://start.spring.io/
* Passo 1: Contrução das classes de dominio;
* Passo 2: Construção dos testes unitários e integrados;
* Passo 3: Construção da API e mecanismo para armazenar os dados;


## Informações gerais

Importe os projetos para testar a api no Postman (https://chrome.google.com/webstore/detail/postman/fhbjgbiflinjbdggehcddcbncdddomop)

Direitório: postman

Para maiores detalhes das outras atividades acesse o Wiki.
