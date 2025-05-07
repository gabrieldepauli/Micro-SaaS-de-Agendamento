package model;

public class Client {
	private String name;
	private String cpf;
	private String adress;
	private String number;
	
	public Client(String name, String cpf, String adress, String number) {
		this.name = name;
		this.cpf = cpf;
		this.adress = adress;
		this.number = number;
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
