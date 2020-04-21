shoppingList.controller('cartInfoCtrl', function ($scope, $http, $routeParams) {
    $scope.URL = "http://localhost:8085";

    _refreshCartInfo();

    function _refreshCartInfo() {
        $http({
            method: "GET",
            url: $scope.URL + "/cart/" + $routeParams.id
        }).then(function (response) {
            $scope.cart = response.data;
        });
    };

    $scope.removeItem = function (productId, cartId, productName) {
        $http({
            method: "DELETE",
            url: $scope.URL + "/cart/removeProduct?productId=" + productId + "&cartId=" + cartId
        }).then(function () {
             alert("Product: " + productName + " was successfuly removed from cart")
            _refreshCartInfo();
        });
    };

    $scope.clearCart = function (cartId, cartName) {
        $http({
            method: "DELETE",
            url: $scope.URL + "/cart/clearCart?cartId=" + cartId
        }).then(function () {
            alert("Cart " + cartName + " is cleaned");
            _refreshCartInfo();
        })
    }
});