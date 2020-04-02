<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Create Product Modal -->
<div class="modal fade" id="createProductModal" tabindex="-1" role="dialog" aria-labelledby="createProductModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="createProductModalLabel">Create Product</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form method="post" action="product/addProduct" name="product">
                    <div class="form-row">
                        <div class="col-6 mb-3">
                            <label for="validationProduct">Product</label>
                            <input type="text" class="form-control" id="validationProduct" name="productName" />
                            <span class="invalid-feedback">
                                Field can't be empty!
                            </span>
                        </div>
                        <div class="col-6 mb-3">
                            <label for="validationPrice">Price</label>
                            <input type="text" class="form-control" id="validationPrice" name="productPrice"/>
                            <div class="invalid-feedback">
                                Field can't be empty!
                            </div>
                        </div>
                        <div class="col-6 mb-3">
                            <label for="validationCategory">Category</label>
                            <select class="custom-select" id="validationCategory" name="productCategory">
                                <option selected disabled value="">Choose...</option>
                                <option value="ALCOHOL">Alcohol</option>
                                <option value="BREAD">Bread</option>
                                <option value="FISH">Fish</option>
                                <option value="FRUITS">Fruits</option>
                                <option value="MEAT">Meat</option>
                                <option value="MILK">Milk</option>
                                <option value="SOFT_DRINKS">Soft drinks</option>
                                <option value="SWEETS">Sweets</option>
                                <option value="VEGETABLES">Vegetables</option>
                            </select>
                            <div class="invalid-feedback">
                                Please select a valid category!
                            </div>
                        </div>
                        <div class="col-6 mb-3">
                            <label for="validationDiscount">Discount</label>
                            <input type="text" class="form-control" id="validationDiscount" placeholder="0.00" name="productDiscount"/>
                            <div class="valid-feedback">
                                Optional field.
                            </div>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col-12 mb-3">
                            <label for="validationDescription">Description</label>
                            <div class="input-group">
                                <textarea class="form-control" id="validationDescription" aria-describedby="inputGroupPrepend" name="productDescription"></textarea>
                                <div class="valid-feedback">
                                    Optional field.
                                </div>
                            </div>
                        </div>
                    </div>
                    <button href="product/addProduct" class="btn btn-primary saveProduct" type="submit">Save product</button>
                </form>
            </div>
            <div class="modal-footer">
                <div class="errors"></div>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<script>
    // Example starter JavaScript for disabling form submissions if there are invalid fields
    (function() {
        'use strict';
        window.addEventListener('load', function() {
            // Fetch all the forms we want to apply custom Bootstrap validation styles to
            var forms = document.getElementsByClassName('needs-validation');
            // Loop over them and prevent submission
            var validation = Array.prototype.filter.call(forms, function(form) {
                form.addEventListener('submit', function(event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();
</script>