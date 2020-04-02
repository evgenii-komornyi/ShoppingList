package com.javaguru.shoppinglist.dto.productDTO;

import com.javaguru.shoppinglist.domain.product.response.ProductCreateResponse;

public class CreateProductDTO extends ProductBasicDTO {
    private ProductCreateResponse createResponse;

    public ProductCreateResponse getCreateResponse() {
        return createResponse;
    }

    public void setCreateResponse(ProductCreateResponse createResponse) {
        this.createResponse = createResponse;
    }
}
