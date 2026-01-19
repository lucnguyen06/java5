package poly.pcs.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import poly.pcs.entity.Category;

public interface CategoryDAO extends JpaRepository<Category, String> {

    // Category có sản phẩm còn bán
    @Query("""
        SELECT DISTINCT c FROM Category c
        JOIN c.products p
        WHERE p.available = true
    """)
    List<Category> findCategoriesHavingProducts();

    // Đếm số lượng sản phẩm mỗi category
    @Query("""
        SELECT c.name, COUNT(p) FROM Category c
        LEFT JOIN c.products p
        GROUP BY c.name
    """)
    List<Object[]> countProductsByCategory();
}
