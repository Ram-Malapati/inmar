package com.inmar.app.jpa.model;


import javax.persistence.*;

@Table(name = "metadata")
@Entity
public class MetaData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meta_data_id", nullable = false)
    private long metaDataId;

    @Column(name = "location")
    private String location;

    @Column(name = "department")
    private String department;
    @Column(name = "category")
    private String category;
    @Column(name = "subcategory")
    private String subcategory;

    public long getMetaDataId() {
        return metaDataId;
    }

    public void setMetaDataId(long metaDataId) {
        this.metaDataId = metaDataId;
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
