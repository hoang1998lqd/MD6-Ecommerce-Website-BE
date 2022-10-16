package com.example.md6be.model.DTO;

public class DTOProductSold {
    Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSold() {
        return sold;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }

    public DTOProductSold(Long id, Integer sold) {
        this.id = id;
        this.sold = sold;
    }

    Integer sold;

    public DTOProductSold() {
    }
}
