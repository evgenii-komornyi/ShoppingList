<div class="container">
    <div ng-init="getCart(cart.id)">
        <h1>{{ title }}: {{ cart.name }}</h1>
        <a href="" class="btn btn-danger" style="float: right;" ng-click="clearCart(cart.id, cart.name)">
            <i class="far fa-trash-alt"></i> clear cart
        </a>
        <table class="table table-hover table-dark" id="productList">
            <thead>
            <tr>
                <th>Product Name</th>
                <th>Category</th>
                <th>Regular Price</th>
                <th>Discount</th>
                <th>Actual Price</th>
                <th>Description</th>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="product in cart.products">
                <td>{{ product.name }}</td>
                <td>{{ product.category }}</td>
                <td>{{ product.regPrice | currency }}</td>
                <td>{{ product.discount }} %</td>
                <td>{{ product.actPrice | currency }}</td>
                <td>{{ product.description }}</td>
                <td>
                    <a ng-click="removeItem(product.id, cart.id, product.name)">
                        <i class="fas fa-2x fa-minus-circle" title="Remove Product from cart"></i>
                    </a>
                </td>
            </tr>
            <tr>
                <td>
                    Total amount
                </td>
                <td>
                    {{ cart.amount | currency }}
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>

