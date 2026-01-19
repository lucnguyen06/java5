package poly.pcs.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Brands")
@Data
public class Brand {
    @Id
    private String id;
    private String name;
    private String country;
    private String description;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
