package model;

//Classe do Usuário e seus atributos (podendo ser ou um aluno ou um professor)
public class User {
	private int id; // Auto increment no Banco de Dados
    private String email;
    private String senha;
    private String tipo; // "cliente (aluno)" ou "professor (prestador de serviço)"

    public User() {}

    public User(String email, String senha, String tipo) {
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
    }

    public User(int id, String email, String senha, String tipo) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}