<div class="container">
    <div class="row">
        <div class="col-12">
            <h1>{{ title }}</h1>
        </div>
        <div class="col-12">
            <form ng-submit="save()">
                <div class="form-row">
                    <div class="col-6 mb-3">
                        <label for="validationProduct">Product</label>
                        <input type="text" class="form-control" ng-model="productName" id="validationProduct" name="productName" />
                        <span class="invalid-feedback">
                                Field can't be empty!
                            </span>
                    </div>
                    <div class="col-6 mb-3">
                        <label for="validationPrice">Price</label>
                        <input type="text" class="form-control" ng-model="productPrice" id="validationPrice" name="productPrice"/>
                        <div class="invalid-feedback">
                            Field can't be empty!
                        </div>
                    </div>
                    <div class="col-6 mb-3">
                        <label for="validationCategory">Category</label>
                        <select class="custom-select" ng-model="productCategory" id="validationCategory" name="productCategory">
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
                        <input type="text" class="form-control" ng-model="productDiscount" id="validationDiscount" placeholder="0.00" name="productDiscount"/>
                        <div class="valid-feedback">
                            Optional field.
                        </div>
                    </div>
                </div>
                <div class="form-row">
                    <div class="col-12 mb-3">
                        <label for="validationDescription">Description</label>
                        <div class="input-group">
                            <textarea class="form-control" ng-model="productDescription" id="validationDescription" aria-describedby="inputGroupPrepend" name="productDescription"></textarea>
                            <div class="valid-feedback">
                                Optional field.
                            </div>
                        </div>
                    </div>
                </div>
                <button class="btn btn-primary" type="submit">Save product</button>
            </form>
        </div>
        <div class="col-12">
            <div class="errors">
                <ul>
                    <li ng-repeat="error in validErrors">
                        Error: {{ error }}
                    </li>
                </ul>
                <ul>
                <li ng-repeat="error in DBErrors">
                    Error: {{ error }}
                </li>
            </ul>
            </div>
        </div>
    </div>
</div>
