package com.example.demo;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
public class POI {
    @Id
    private Long id;
    private Long purchaseOrderId;
    private String skuDescription;
    private String skuBrand;
    private Long unitValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(Long purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public String getSkuDescription() {
        return skuDescription;
    }

    public void setSkuDescription(String skuDescription) {
        this.skuDescription = skuDescription;
    }

    public String getSkuBrand() {
        return skuBrand;
    }

    public void setSkuBrand(String skuBrand) {
        this.skuBrand = skuBrand;
    }

    public Long getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(Long unitValue) {
        this.unitValue = unitValue;
    }

    @Override
    public String toString() {
        return "POI{" +
                "id=" + id +
                ", purchaseOrderId=" + purchaseOrderId +
                ", skuDescription='" + skuDescription + '\'' +
                ", skuBrand='" + skuBrand + '\'' +
                ", unitValue=" + unitValue +
                '}';
    }
}
