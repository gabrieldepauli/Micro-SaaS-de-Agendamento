package model;

import java.sql.Date;
import java.util.List;

//Classe do Prestador de Serviços e seus atributos (Professor)
public class Teacher {
	private int id;
	private String businessName;
	private String name;
	private String profilePicture;
	private String specialty;
	private String adress;
	private String description;
	private List<String> serviceImages;
    private Date date;
    
    public Teacher() {}
    
    public Teacher(String businessName, String name, String profilePicture, String specialty, String adress,
			String description, List<String> serviceImages, Date date) {
		this.businessName = businessName;
		this.name = name;
		this.profilePicture = profilePicture;
		this.specialty = specialty;
		this.adress = adress;
		this.description = description;
		this.serviceImages = serviceImages;
		this.date = date;
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
	
	public String getAdress() {
		return adress;
	}
	
	public void setAdress(String adress) {
		this.adress = adress;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<String> getServiceImages() {
		return serviceImages;
	}
	
	public void setServiceImages(List<String> serviceImages) {
		this.serviceImages = serviceImages;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
}