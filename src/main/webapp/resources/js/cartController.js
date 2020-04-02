var app = angular.module("myapp", []).controller(
    "myController",
    function($scope, $http) {
        $scope.baseURL = "http://localhost:1025/";

        $scope.getProductList = function() {
            $http.get("http://localhost:8090/pages/getProductsList")
                .success(function(data) {
                    $scope.products = data;
                });
        }

        $scope.addToCart = function(productId, cartId) {
            $http.put($scope.baseURL + "addToCart-" + cartId + "/" + productId)
                .success(function() {
                    alert("Added Successfully to " + cartId);
                })
        }

        $scope.refreshCart = function() {
            $http.get(
                "http://localhost:8090/pages/cart/getCart/"
                + $scope.cartId).success(function(data) {

                $scope.carts = data;
            })
        }

        $scope.getCart = function(cartId) {
            $scope.cartId = cartId;
            $scope.refreshCart(cartId);
        }

        $scope.removeFromCart = function(cartItemId) {
            $http.put(
                "http://localhost:8090/pages/cart/removeCartItem/"
                + cartItemId).success(function() {
                $scope.refreshCart();
            });
        }

        $scope.clearCart = function() {
            $http.put(
                "http://localhost:8090/pages/cart/removeAllItems/"
                + $scope.cartId).success(function() {
                $scope.refreshCart();
            });
        }

        $scope.calculateGrandTotal = function() {
            var grandTotal = 0.0;
            for (var i = 0; i < $scope.carts.cartItem.length; i++)
                grandTotal = grandTotal + $scope.carts.cartItem[i].price;
            return grandTotal;

        }
    });