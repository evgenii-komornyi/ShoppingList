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
                        <th scope="col">Cart</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="cart in carts">
                        <td>{{ cart.name }}</td>
                        <td>
                            <a href="#!/cart/{{ cart.id }}" title="Cart info">
                                <i class="fas fa-2x fa-shopping-basket"></i>
                            </a>
                        </td>
                        <td>
                            <a ng-click="remove(cart.id)" title="Remove cart">
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