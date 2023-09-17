package com.inmar.app.jpa.model;


import javax.persistence.*;

@Table(name = "skudata")
@Entity
public class SkuData {

    @Id
    @Column(name = "sku_data_id", nullable = false)
    private long skuDataId;
    @Column(name = "sku_name")
    private String skuName;
    @Column(name = "location")
    private String location;

    @Column(name = "department")
    private String department;
    @Column(name = "category")
    private String category;
    @Column(name = "subcategory")
    private String subcategory;

    public long getSkuDataId() {
        return skuDataId;
    }

    public void setSkuDataId(long skuDataId) {
        this.skuDataId = skuDataId;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }
}
