shoppingList.controller("cartCtrl", function ($scope, $http) {
    $scope.URL = "http://localhost:8085";

    _refreshCartList();

    function _refreshCartList() {
        $http({
            method: "GET",
            url: $scope.URL + "/allCarts"
        }).then(function(response) {
            $scope.carts = response.data.carts;
        });
    };

    $scope.save = function save() {
        let params = {
            'cartName': $scope.cartName
        }

        $http({
            method: "POST",
            url: $scope.URL + "/addCart",
            data: params
        }).then(function(response){
            $scope.data = response.data;

            if ($scope.data.validationErrors != null || $scope.data.dbErrors != null) {
                $scope.valErrors = response.data.validationErrors;
                $scope.DataBaseErrors = response.data.dbErrors;
            } else {
                alert("Cart with name " + $scope.cartName + " was created!");
            }
        });
    };

    $scope.remove = function(cartId) {
        $http({
            method: "DELETE",
            url: $scope.URL + "/carts/deleteCart?cartId=" + cartId
        }).then(function (response) {
            if (response.data.stat == "SUCCESS") {
                alert("Cart was deleted");
                _refreshCartList();
            } else {
                alert("You can't delete non empty cart");
            }
        });
    };

    $scope.refreshCart = function () {
        $http.get($scope.URL + "cart/" + $scope.cartId)
            .success(function (data) {
                $scope.productsIn = data;
            });
    }

    $scope.getCart = function (cartId) {
        $scope.cartId = cartId;
        $scope.refreshCart(cartId);
    }
});