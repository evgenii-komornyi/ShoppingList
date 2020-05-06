<div class="container">
    <div class="row">
        <div class="col-12">
            <h1>{{ title }}</h1>
        </div>
    </div>
    <div class="row">
        <div class="col-12">
            <div class="table-responsive">
                <table class="table table-hover table-bordered table-dark">
                    <thead>
                    <tr>
                        <th scope="col">Product</th>
                        <th scope="col">Category</th>
                        <th scope="col">Regular Price</th>
                        <th scope="col">Discount</th>
                        <th scope="col">Actual Price</th>
                        <th scope="col">Description</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>{{ productName }}</td>
                        <td>{{ productCategory }}</td>
                        <td>{{ productPrice | currency }}</td>
                        <td>{{ productDiscount }} %</td>
                        <td>{{ product.actPrice | currency }}</td>
                        <td>{{ productDescription }}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="col-12">
            <button class="btn btn-block btn-success" ng-click="toggleEditForm()">Edit this product</button>
        </div>
    </div>
    <div class="row">
        <div class="col-12">
            <form ng-hide="editForm">
                <div class="form-row">
                    <div class="col-6 mb-3">
                        <label for="validationProduct">Product</label>
                        <input type="text" class="form-control" ng-model="productName" id="validationProduct" name="productName"/>
                        <div class="invalid-feedback">
                            Field can't be empty!
                        </div>
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
                            <option selected disabled value=""></option>
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
                        <input type="text" class="form-control" ng-model="productDiscount" id="validationDiscount" placeholder="0" name="productDiscount"/>
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
                <button class="btn btn-primary" ng-click="update()">Update product</button>
                <button class="btn btn-outline-primary" ng-click="discard()">Discard</button>
            </form>
        </div>
        <div class="col-12">
            <div class="errors">
                <ul>
                    <li ng-repeat="error in validErrors">
                        Error: {{ error }}
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>