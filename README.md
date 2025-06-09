# Projeto MicroSaaS - Agendamento de Aulas

## Descrição do Projeto

### Autor 👤

O projeto do MicroSaaS para Agendamento de Aulas foi criado pelo aluno do curso de Análise e Desenvolvimento de Sistemas no IFSP - Campus Araraquara abaixo:
- **Gabriel de Pauli Santos**

### Tecnologias utilizadas 👩‍💻

- **JSP:** Utilizado na criação das páginas usadas no projeto.
- **Java:** Principal linguagem de programação utilizada, necessária para a criação de toda a lógica das funções do programa. **OBS: O aplicativo está utilizando o Java 21.**
- **Servlet:** Utilizado nos FrontControllers.
- **Session:** Utilizado para criar a sessão do usuário.
- **Filtros:** Utilizado para conferir a sessão do usuário para poder realizar o encaminhamento para as páginas, que necessitam de Login para serem acessadas.
- **JNDI:** Utilizado para acessar o Banco de Dados e realizar transações.
- **SQL:** Utilizado para armazenar os dados no Banco de Dados.
- **Bootstrap:** Utilizado para estilizar as página JSP.
- **CSS:** Utilizado para fazer a estilização das páginas utilizadas.
- **Pool de conexões:** Utilizado para gerenciar as conexões do banco de dados. 
- **Padrões de Projeto:** Foram utilizados os padrões FrontControler, MVC, DAO.
- **Aplicativos:** MySQL Workbench e Eclipse.

## Sobre o projeto ℹ️

Projeto da disciplina de Desenvolvimento de Software para Web 2, com o objetivo de criar um Micro SaaS para agendamentos. No qual permite cadastro e acessos de dois tipos de Usuário (aluno / professor).

O Professor pode realizar seu cadastro, efetuar login e ter acesso ao seu portal de professor com algumas opções. Essas opções são de "Cadastrar seus horários disponíveis", "Adicionar / visualizar imagens no portfólio", "Ver agendamentos", "Ver histórico de status de um agendamento em específico" e o "Logout".

O Aluno (cliente) pode realizar seu cadastro, efetuar login e ter acesso ao seu portal de estudante com algumas opções. Essas opções são a de visualizar todos professores e poder filtrar tanto por localização quanto por especialidade, visualizar o portfólio de um professor em específico, visualizar e agendar horários com algum professor específico, visualizar seus agendamentos e andamentos do status do agendamento, além de poder fazer o Logout.
