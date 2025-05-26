package model;

import java.sql.Date;
import java.util.List;

//Classe do Prestador de Serviços e seus atributos (Professor)
public class Teacher {
	private int id; // Auto increment no Banco de Dados
	private String businessName;
	private String name;
	private String profilePicture;
	private String specialty;
	private String address;
	private String description;
    private Date date;
    private List<String> imagens;
    
    public Teacher() {}
    
    public Teacher(String businessName, String name, String profilePicture, String specialty, String address,
			String description, List<String> serviceImages, Date date) {
		this.businessName = businessName;
		this.name = name;
		this.profilePicture = profilePicture;
		this.specialty = specialty;
		this.address = address;
		this.description = description;
	}	
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBusinessName() {
		return businessName;
	}
	
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getProfilePicture() {
		return profilePicture;
	}
	
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
	
	public String getSpecialty() {
		return specialty;
	}
	
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public List<String> getImagens() {
	    return imagens;
	}

	public void setImagens(List<String> imagens) {
	    this.imagens = imagens;
	}
	
}