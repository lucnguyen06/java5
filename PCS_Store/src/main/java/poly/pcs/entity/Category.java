package poly.pcs.entity;

import java.util.List;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Categories")
@Data
public class Category {

	@Id
	private String id;
	private String name;

	// THIẾU CHÍNH LÀ ĐÂY
	@OneToMany(mappedBy = "category")
	private List<Product> products;

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

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
