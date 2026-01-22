package com.example.store.store.model;

public class Product {

    private int id;
    private String name;
    private double price;
    private double discount; // Phần trăm giảm giá
    private String image;
    private String description;
    private String ram; // VD: "8GB"
    private String rom; // VD: "256GB"
    private String screen; // VD: "6.57\""
    private String resolution; // VD: "Full HD+"
    private String salePeriod; // VD: "10-31/01"
    private String badge; // VD: "HOTSALE", "NEW", "HOT"
    private String codename; // VD: "AGATE", "ARISTOTLE"

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public double getDiscount() { return discount; }
    public void setDiscount(double discount) { this.discount = discount; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public String getDescription() { return description; }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getRam() { return ram; }
    public void setRam(String ram) { this.ram = ram; }

    public String getRom() { return rom; }
    public void setRom(String rom) { this.rom = rom; }

    public String getScreen() { return screen; }
    public void setScreen(String screen) { this.screen = screen; }

    public String getResolution() { return resolution; }
    public void setResolution(String resolution) { this.resolution = resolution; }

    public String getSalePeriod() { return salePeriod; }
    public void setSalePeriod(String salePeriod) { this.salePeriod = salePeriod; }

    public String getBadge() { return badge; }
    public void setBadge(String badge) { this.badge = badge; }

    public String getCodename() { return codename; }
    public void setCodename(String codename) { this.codename = codename; }

    // Tính giá sau giảm
    public double getFinalPrice() {
        return price * (1 - discount / 100);
    }
}
