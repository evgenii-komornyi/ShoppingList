<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="particles/_header.jsp" />

<div class="container">
    <div class="row">
        <div class="col-12">
            <h1>Edit product</h1>
        </div>
    </div>
    <div class="row">
        <div class="col-12">
            <form method="put" action="product/editProduct/${productID}" name="ed_product" class="needs-validation" novalidate>
                <div class="form-row">
                    <div class="col-6 mb-3">
                        <label for="validationProduct">Product</label>
                        <input type="text" class="form-control" id="validationProduct" name="productName" value="${productName}" />
                        <div class="invalid-feedback">
                            Field can't be empty!
                        </div>
                    </div>
                    <div class="col-6 mb-3">
                        <label for="validationPrice">Price</label>
                        <input type="text" class="form-control" id="validationPrice" name="productPrice" value="${productPrice}"/>
                        <div class="invalid-feedback">
                            Field can't be empty!
                        </div>
                    </div>
                    <div class="col-6 mb-3">
                        <label for="validationCategory">Category</label>
                        <select class="custom-select" id="validationCategory" name="productCategory">
                            <option selected disabled value="">${productCategory}</option>
                            <option>ALCOHOL</option>
                            <option>BREAD</option>
                            <option>FISH</option>
                            <option>FRUITS</option>
                            <option>MEAT</option>
                            <option>MILK</option>
                            <option>SOFT_DRINKS</option>
                            <option>SWEETS</option>
                            <option>VEGETABLES</option>
                        </select>
                        <div class="invalid-feedback">
                            Please select a valid category!
                        </div>
                    </div>
                    <div class="col-6 mb-3">
                        <label for="validationDiscount">Discount</label>
                        <input type="text" class="form-control" id="validationDiscount" placeholder="0" name="productDiscount" value="${productDiscount}"/>
                        <div class="valid-feedback">
                            Optional field.
                        </div>
                    </div>
                </div>
                <div class="form-row">
                    <div class="col-12 mb-3">
                        <label for="validationDescription">Description</label>
                        <div class="input-group">
                            <textarea class="form-control" id="validationDescription" aria-describedby="inputGroupPrepend" name="productDescription" placeholder="${productDescription}"></textarea>
                            <div class="valid-feedback">
                                Optional field.
                            </div>
                        </div>
                    </div>
                </div>
                <a href="/product/editProduct" class="btn btn-primary edit" type="submit">Save product</a>
            </form>
        </div>
    </div>

<jsp:include page="particles/_footer.jsp" />