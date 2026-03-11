---
name: jsf-wildfly-primefaces
description: Cria projeto Jakarta Faces 11 com PrimeFaces 15 e Wildfly 39
scope: project
---

# Jakarta Faces 11 + PrimeFaces 15 + Wildfly 39

Esta skill cria um projeto Maven com Jakarta Faces 11 e PrimeFaces 15 configurado para rodar no Wildfly 39.

## Estrutura do Projeto

```
<project-root>/
├── pom.xml
└── src/main/webapp/
    ├── WEB-INF/web.xml
    └── index.xhtml
    └── resources/
```

## O que eu faco

1. **Cria pom.xml** com:
   - Packaging WAR
   - Dependencia jakarta.jakartaee-web-api 11.0.0 (scope provided)
   - Dependencia org.primefaces:primefaces:15.0.0
   - maven.compiler.source/target = 11
   - Plugin maven-war-plugin 3.4.0

2. **Cria web.xml** em src/main/webapp/WEB-INF/ com:
   - Servlet Faces configurado com jakarta.faces.webapp.FacesServlet
   - Load-on-startup 1
   - URL pattern *.xhtml

3. **Cria model AplicacaoRendaFixa** em src/main/java/com/app/model/:
   - id, nomeAplicacao, instituicaoFinanceira
   - valorInvestido, percentualRetorno, valorRetorno
   - dataAplicacao, dataVencimento
   - tipoAplicacao (CDB, LCI, LCA, CRI, CRA, etc.)
   - observacoes, ativo

4. **Cria managed bean AplicacaoBean** em src/main/java/com/app/bean/:
   - @Named @ViewScoped
   - CRUD completo (salvar, editar, excluir)
   - Calculo automatico de retorno
   - Totais geral (investido/retorno)

5. **Cria index.xhtml** com:
   - Formulario de cadastro com componentes PrimeFaces
   - DataTable com listagem e paginacao
   - Calculadora de totais
   - Tipos: CDB, LCI, LCA, CRI, CRA, Debentures, Tesouro Direto, Poupanca

## Wildfly

- Localizacao padrao: /home/op/volume/wildfly-39.0.1.Final
- URL de acesso: http://localhost:8080

## Comandos

- Compilar: mvn compile
- Gerar WAR: mvn package
- Deploy: cp target/jsp-test.war /home/op/volume/wildfly-39.0.1.Final/standalone/deployments/

## Quando usar

- Criar projeto JSF 11 + PrimeFaces 15 do zero
- Sistema de gerenciamento de investimentos renda fixa

