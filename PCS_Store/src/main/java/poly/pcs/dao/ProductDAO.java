package poly.pcs.dao;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import poly.pcs.dto.Report;
import poly.pcs.entity.Product;

public interface ProductDAO extends JpaRepository<Product, Integer> {

    // ================= BASIC =================
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.category.id = ?1")
    Page<Product> findByCategoryId(String cid, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.brand.id = ?1")
    Page<Product> findByBrandId(String bid, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.discount > 0")
    Page<Product> findDiscountProducts(Pageable pageable);

    // ================= HOT PRODUCTS (ĐÚNG CHUẨN) =================
    @Query("""
        SELECT p FROM OrderDetail od
        JOIN od.product p
        GROUP BY p
        ORDER BY SUM(od.quantity) DESC
    """)
    Page<Product> findHotProducts(Pageable pageable);

    // ================= REPORT =================
    @Query("""
        SELECT new poly.pcs.dto.Report(
            p.category.name,
            SUM(p.price),
            COUNT(p),
            MAX(p.price),
            MIN(p.price),
            AVG(p.price)
        )
        FROM Product p
        GROUP BY p.category.name
    """)
    List<Report> getInventoryByCategory();
}
