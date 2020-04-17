shoppingList.controller("productCtrl", function ($scope, $http, $location) {
    $scope.products = [];
    $scope.carts = [];
    $scope.URL = "http://localhost:8085";

    if ($location.$$path == "/products") {
        $scope.title = 'Products List';
    }

    if ($location.$$path == "/addNewProduct") {
        $scope.title = 'Create new Product';
    }

    _refreshProductList();

    function _refreshProductList() {
        $http({
            method: "GET",
            url: $scope.URL + "/allProducts"
        }).then(function(response) {
            $scope.products = response.data.products;
            $scope.carts = response.data.carts;
        });
    };

    $scope.save = function save() {
        let params = {
            'productName': $scope.productName,
            'productPrice': $scope.productPrice,
            'productCategory': $scope.productCategory,
            'productDiscount': $scope.productDiscount,
            'productDescription': $scope.productDescription
        }

        $http({
            method: "POST",
            url: $scope.URL + "/create",
            data: params
        }).then(function(response){
            $scope.data = response.data;

            if ($scope.data.validationErrors != null || $scope.data.dbErrors != null) {
                $scope.validErrors = response.data.validationErrors;
                $scope.DBErrors = response.data.dbErrors;
            } else {
                alert("Product with name " + $scope.productName + " was added!");

                $scope.productName = '';
                $scope.productPrice = '';
                $scope.productCategory = '';
                $scope.productDiscount = '';
                $scope.productDescription = '';
            }
            });
    };

    $scope.addToCart = function (productId, cartId, cartName) {
        let params = {
            'cartID': cartId,
            'productID': productId
        }

        $http({
            method: "PUT",
            url: $scope.URL + "/addToCart",
            data: params
        }).then(function () {
            alert("Product was added to cart: " + cartName);
        });
        _refreshProductList();
    };

    $scope.remove = function remove(product) {
        $http({
            method: "DELETE",
            url: $scope.URL + "/product/" + product.id
        }).then(function () {
            alert("Product with ID " + product.id + "was deleted.");
            _refreshProductList();
        });
    };
});