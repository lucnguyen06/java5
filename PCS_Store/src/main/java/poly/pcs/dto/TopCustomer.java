package poly.pcs.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopCustomer {
    private String fullname;
    private Double totalSpent;
    private LocalDateTime firstPurchase;
    private LocalDateTime lastPurchase;
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public Double getTotalSpent() {
		return totalSpent;
	}
	public void setTotalSpent(Double totalSpent) {
		this.totalSpent = totalSpent;
	}
	public LocalDateTime getFirstPurchase() {
		return firstPurchase;
	}
	public void setFirstPurchase(LocalDateTime firstPurchase) {
		this.firstPurchase = firstPurchase;
	}
	public LocalDateTime getLastPurchase() {
		return lastPurchase;
	}
	public void setLastPurchase(LocalDateTime lastPurchase) {
		this.lastPurchase = lastPurchase;
	}
    
}

