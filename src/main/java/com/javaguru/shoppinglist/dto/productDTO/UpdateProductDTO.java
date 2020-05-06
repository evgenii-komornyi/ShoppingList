package com.javaguru.shoppinglist.dto.productDTO;

public class UpdateProductDTO extends ProductBasicDTO {
    private ProductDTO product;

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }
}
