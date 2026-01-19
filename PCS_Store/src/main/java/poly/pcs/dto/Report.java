package poly.pcs.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Report {
    private String categoryName;
    private Double sumPrice;
    private Long countProducts;
    private Double maxPrice;
    private Double minPrice;
    private Double avgPrice;
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Double getSumPrice() {
		return sumPrice;
	}
	public void setSumPrice(Double sumPrice) {
		this.sumPrice = sumPrice;
	}
	public Long getCountProducts() {
		return countProducts;
	}
	public void setCountProducts(Long countProducts) {
		this.countProducts = countProducts;
	}
	public Double getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}
	public Double getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}
	public Double getAvgPrice() {
		return avgPrice;
	}
	public void setAvgPrice(Double avgPrice) {
		this.avgPrice = avgPrice;
	}
    
	public Report(String categoryName, Double sumPrice, Long countProducts,
            Double maxPrice, Double minPrice, Double avgPrice) {
  this.categoryName = categoryName;
  this.sumPrice = sumPrice;
  this.countProducts = countProducts;
  this.maxPrice = maxPrice;
  this.minPrice = minPrice;
  this.avgPrice = avgPrice;
}
}

