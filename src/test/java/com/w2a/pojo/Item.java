package com.w2a.pojo;

public class Item {
    private String name;
    private String description;
    private Money unit_amount;
    private String quantity;
    private String category;
    private String sku;
    private String image_url;
    private String url;
    private UPC upc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Money getUnit_amount() {
        return unit_amount;
    }

    public void setUnit_amount(Money unit_amount) {
        this.unit_amount = unit_amount;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public UPC getUpc() {
        return upc;
    }

    public void setUpc(UPC upc) {
        this.upc = upc;
    }
}
