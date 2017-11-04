# Programação Back-End Avançada
Projeto final da disciplia de Programação Back-End Avançada. Sistema de emprestimos.

## Requisito
* Java instalado na versão 8+;
* Execução prévia do projeto Sistema Cadastro Positivo (https://github.com/brunokarpo/cadastro-positivo-nodejs)


## Criando executável
Faça o clone do projeto. Navegue até o diretório e execute o seguinte comando:
* Ambiente Linux:
``sh mvnw clean test install``  
* Ambiente Windows: ``mvnw.cmd clean test install``

## Executando o projeto
Será criado o diretório `target`. Navegue até dentro desse diretório e execute o comando de inicialização do Java:  
``` 
cd target/
java -jar emprestimos-0.0.1-SNAPSHOT.jar
```

Após isso volte no diretório principal e você observará que existe um arquivo com o seguinte nome:
`Sistema-emprestimo.postman_collection.json`

Esse arquivo contém as requisições que podem ser importadas no Postman para testar o projeto.