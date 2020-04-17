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
                    </tr>
                    </thead>
                    <tbody>
                        <tr ng-repeat="prod in products">
                            <td>{{ prod.name }}</td>
                            <td>{{ prod.category }}</td>
                            <td>{{ prod.regPrice | currency }}</td>
                            <td>{{ prod.discount }} %</td>
                            <td>{{ prod.actPrice | currency }}</td>
                            <td>
                                <a href="#!/product/{{ prod.id }}" title="Product info">
                                    <i class="fas fa-2x fa-info-circle"></i>
                                </a>
                            </td>
                            <td>
                                <div class="dropdown">
                                    <a href="" class="dropdown-toggle"
                                       id="dropdownCarts" data-toggle="dropdown"
                                       aria-haspopup="true" aria-expanded="false"
                                       title="Add to Cart">
                                        <i class="fas fa-2x fa-cart-plus"></i>
                                    </a>
                                    <div class="dropdown-menu" aria-labelledby="dropdownCarts">
                                        <a class="dropdown-item createCart" data-target="#createCartModal" data-toggle="modal"><i class="fas fa-plus-circle"></i> Add new cart</a>
                                        <a ng-repeat="cart in carts" class="dropdown-item" ng-click="addToCart(prod.id, cart.id, cart.name)">{{ cart.name }}</a>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <a ng-click="remove(prod)" title="Remove product">
                                    <i class="far fa-2x fa-trash-alt"></i>
                                </a>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="createCartModal" tabindex="-1" role="dialog" aria-labelledby="createCartModalLabel" aria-hidden="true" ng-controller="cartCtrl">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="createCartModalLabel">Create Cart</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-row">
                        <div class="col-12 mb-3">
                            <label for="validationCart">Cart name</label>
                            <input type="text" class="form-control" ng-model="cartName" id="validationCart" name="cartName" />
                            <span class="invalid-feedback">
                                Field can't be empty!
                            </span>
                        </div>
                    </div>
                    <div class="form-row">
                        <button class="btn btn-primary" ng-click="save()">Save cart</button>
                    </div>
                </form>
            </div>
            <div class="errors">
                <ul>
                    <li ng-repeat="valError in valErrors">
                        Error: {{ valError }}
                    </li>
                    <li ng-repeat="dbError in DataBaseErrors">
                        Error: {{ dbError }}
                    </li>
                </ul>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>