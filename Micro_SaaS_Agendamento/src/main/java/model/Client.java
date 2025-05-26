package model;

// Classe do Cliente e seus atributos (Aluno)
public class Client {
	private int id; // Auto increment no Banco de Dados
	private String name;
	private String cpf;
	private String adress;
	private String number;
	
	public Client() {}
	
	public Client(String name, String cpf, String adress, String number) {
		this.name = name;
		this.cpf = cpf;
		this.adress = adress;
		this.number = number;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
}