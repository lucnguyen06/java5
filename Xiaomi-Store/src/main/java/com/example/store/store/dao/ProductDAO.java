package com.example.store.store.dao;

import com.example.store.store.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDAO {

    // Dữ liệu ảo - không cần database
    private final List<Product> products = new ArrayList<>();

    public ProductDAO() {
        initProducts();
    }

    private void initProducts() {
        // Thêm nhiều sản phẩm hơn
        // Xiaomi 11T
        Product p6 = new Product();
        p6.setId(6);
        p6.setName("Xiaomi 11T");
        p6.setPrice(8990000);
        p6.setDiscount(12);
        p6.setImage("agate.png");
        p6.setDescription("Xiaomi 11T với chip MediaTek Dimensity 1200, camera 108MP. Màn hình AMOLED 6.67 inch, sạc nhanh 67W.");
        p6.setRam("8GB");
        p6.setRom("128GB");
        p6.setScreen("6.67\"");
        p6.setResolution("Full HD+");
        p6.setSalePeriod("01-31/01");
        p6.setBadge("HOT");
        p6.setCodename("AGATE");
        products.add(p6);

        // Xiaomi 13T
        Product p7 = new Product();
        p7.setId(7);
        p7.setName("Xiaomi 13T");
        p7.setPrice(10990000);
        p7.setDiscount(8);
        p7.setImage("aristotle.png");
        p7.setDescription("Xiaomi 13T với chip MediaTek Dimensity 8200 Ultra, camera Leica 50MP. Màn hình AMOLED 6.67 inch.");
        p7.setRam("12GB");
        p7.setRom("256GB");
        p7.setScreen("6.67\"");
        p7.setResolution("Full HD+");
        p7.setSalePeriod("01-31/01");
        p7.setBadge("NEW");
        p7.setCodename("ARISTOTLE");
        products.add(p7);

        // Redmi Note 14 5G
        Product p8 = new Product();
        p8.setId(8);
        p8.setName("Redmi Note 14 5G");
        p8.setPrice(5990000);
        p8.setDiscount(15);
        p8.setImage("beryl.png");
        p8.setDescription("Redmi Note 14 5G với chip Snapdragon 4 Gen 2, camera 50MP. Màn hình AMOLED 6.67 inch.");
        p8.setRam("8GB");
        p8.setRom("256GB");
        p8.setScreen("6.67\"");
        p8.setResolution("Full HD+");
        p8.setSalePeriod("01-31/01");
        p8.setBadge("HOTSALE");
        p8.setCodename("BERYL");
        products.add(p8);

        // Xiaomi 13T Pro
        Product p9 = new Product();
        p9.setId(9);
        p9.setName("Xiaomi 13T Pro");
        p9.setPrice(12990000);
        p9.setDiscount(10);
        p9.setImage("corot.png");
        p9.setDescription("Xiaomi 13T Pro với chip MediaTek Dimensity 9200+, camera Leica 50MP. Màn hình AMOLED 6.67 inch, sạc nhanh 120W.");
        p9.setRam("12GB");
        p9.setRom("512GB");
        p9.setScreen("6.67\"");
        p9.setResolution("Full HD+");
        p9.setSalePeriod("01-31/01");
        p9.setBadge("NEW");
        p9.setCodename("COROT");
        products.add(p9);

        // Redmi 13C / R 5G
        Product p10 = new Product();
        p10.setId(10);
        p10.setName("Redmi 13C / R 5G");
        p10.setPrice(3990000);
        p10.setDiscount(20);
        p10.setImage("air.png");
        p10.setDescription("Redmi 13C 5G với chip MediaTek Dimensity 6100+, camera 50MP. Màn hình IPS LCD 6.74 inch.");
        p10.setRam("6GB");
        p10.setRom("128GB");
        p10.setScreen("6.74\"");
        p10.setResolution("HD+");
        p10.setSalePeriod("01-31/01");
        p10.setBadge("SALE");
        p10.setCodename("AIR");
        products.add(p10);

        // Xiaomi 14 Ultra / Ti
        Product p11 = new Product();
        p11.setId(11);
        p11.setName("Xiaomi 14 Ultra / Ti");
        p11.setPrice(22990000);
        p11.setDiscount(5);
        p11.setImage("aurora.png");
        p11.setDescription("Xiaomi 14 Ultra với chip Snapdragon 8 Gen 3, camera Leica 50MP. Màn hình LTPO AMOLED 6.73 inch.");
        p11.setRam("16GB");
        p11.setRom("512GB");
        p11.setScreen("6.73\"");
        p11.setResolution("QHD+");
        p11.setSalePeriod("01-31/01");
        p11.setBadge("NEW");
        p11.setCodename("AURORA");
        products.add(p11);

        // Redmi Note 13R 5G
        Product p12 = new Product();
        p12.setId(12);
        p12.setName("Redmi Note 13R 5G");
        p12.setPrice(5490000);
        p12.setDiscount(18);
        p12.setImage("breeze.png");
        p12.setDescription("Redmi Note 13R 5G với chip Snapdragon 4 Gen 2, camera 50MP. Màn hình AMOLED 6.67 inch.");
        p12.setRam("8GB");
        p12.setRom("256GB");
        p12.setScreen("6.67\"");
        p12.setResolution("Full HD+");
        p12.setSalePeriod("01-31/01");
        p12.setBadge("HOT");
        p12.setCodename("BREEZE");
        products.add(p12);

        // Redmi K60 Ultra
        Product p13 = new Product();
        p13.setId(13);
        p13.setName("Redmi K60 Ultra");
        p13.setPrice(11990000);
        p13.setDiscount(12);
        p13.setImage("corot.png");
        p13.setDescription("Redmi K60 Ultra với chip MediaTek Dimensity 9200+, camera 50MP. Màn hình AMOLED 6.67 inch, sạc nhanh 120W.");
        p13.setRam("12GB");
        p13.setRom("256GB");
        p13.setScreen("6.67\"");
        p13.setResolution("Full HD+");
        p13.setSalePeriod("01-31/01");
        p13.setBadge("HOT");
        p13.setCodename("COROT");
        products.add(p13);

        // Redmi Note 14 Pro+ 5G
        Product p14 = new Product();
        p14.setId(14);
        p14.setName("Redmi Note 14 Pro+ 5G");
        p14.setPrice(7990000);
        p14.setDiscount(10);
        p14.setImage("amethyst.png");
        p14.setDescription("Redmi Note 14 Pro+ 5G với chip Snapdragon 7s Gen 3, camera 200MP. Màn hình AMOLED 6.67 inch.");
        p14.setRam("12GB");
        p14.setRom("512GB");
        p14.setScreen("6.67\"");
        p14.setResolution("Full HD+");
        p14.setSalePeriod("01-31/01");
        p14.setBadge("NEW");
        p14.setCodename("AMETHYST");
        products.add(p14);

        // Xiaomi MIX Fold 3
        Product p15 = new Product();
        p15.setId(15);
        p15.setName("Xiaomi MIX Fold 3");
        p15.setPrice(29990000);
        p15.setDiscount(8);
        p15.setImage("babylon.png");
        p15.setDescription("Xiaomi MIX Fold 3 smartphone gập với chip Snapdragon 8 Gen 2, camera Leica. Màn hình gập 8.03 inch.");
        p15.setRam("16GB");
        p15.setRom("1TB");
        p15.setScreen("8.03\"");
        p15.setResolution("QHD+");
        p15.setSalePeriod("01-31/01");
        p15.setBadge("NEW");
        p15.setCodename("BABYLON");
        products.add(p15);

        // Xiaomi Civi 4 Pro
        Product p16 = new Product();
        p16.setId(16);
        p16.setName("Xiaomi Civi 4 Pro");
        p16.setPrice(9990000);
        p16.setDiscount(15);
        p16.setImage("chenfeng.png");
        p16.setDescription("Xiaomi Civi 4 Pro với chip Snapdragon 8s Gen 3, camera 50MP. Màn hình AMOLED 6.55 inch, thiết kế mỏng nhẹ.");
        p16.setRam("12GB");
        p16.setRom("512GB");
        p16.setScreen("6.55\"");
        p16.setResolution("Full HD+");
        p16.setSalePeriod("01-31/01");
        p16.setBadge("HOT");
        p16.setCodename("CHENFENG");
        products.add(p16);

        // Xiaomi 12
        Product p17 = new Product();
        p17.setId(17);
        p17.setName("Xiaomi 12");
        p17.setPrice(12990000);
        p17.setDiscount(25);
        p17.setImage("cupid.png");
        p17.setDescription("Xiaomi 12 với chip Snapdragon 8 Gen 1, camera 50MP. Màn hình AMOLED 6.28 inch, thiết kế cao cấp.");
        p17.setRam("8GB");
        p17.setRom("256GB");
        p17.setScreen("6.28\"");
        p17.setResolution("Full HD+");
        p17.setSalePeriod("01-31/01");
        p17.setBadge("SALE");
        p17.setCodename("CUPID");
        products.add(p17);
    }

    public List<Product> findAll() {
        return new ArrayList<>(products);
    }

    public Product findById(int id) {
        return products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void insert(Product p) {
        // TODO Auto-generated method stub
    }

    public void update(Product p) {
        // TODO Auto-generated method stub
    }

    public void delete(int id) {
        // TODO Auto-generated method stub
    }
}
