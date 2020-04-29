shoppingList.controller('productInfoCtrl', function ($scope, $http, $routeParams) {
    $scope.URL = "http://localhost:8086";

    _refreshProductInfo();

    function _refreshProductInfo() {
        $http({
            method: "GET",
            url: $scope.URL + "/product/" + $routeParams.id
        }).then(function(response) {
            $scope.product = response.data.product;

            $scope.productName = $scope.product.name;
            $scope.productPrice = $scope.product.regPrice;
            $scope.productCategory = $scope.product.category;
            $scope.productDiscount = $scope.product.discount;
            $scope.productDescription = $scope.product.description;
        });
    };

    $scope.editForm = true;
    $scope.toggleEditForm = function() {
        $scope.editForm = $scope.editForm === false ? true : false;
    };

    $scope.update = function() {
        let params = {
            'productName': $scope.productName,
            'productPrice': $scope.productPrice,
            'productCategory': $scope.productCategory,
            'productDiscount': $scope.productDiscount,
            'productDescription': $scope.productDescription
        }

        $http({
            method: "PUT",
            url: $scope.URL + "/product/" + $routeParams.id,
            data: params
        }).then(function(response) {
            $scope.data = response.data;

            if ($scope.data.validationErrors != null || $scope.data.dbErrors != null) {
                $scope.validErrors = response.data.validationErrors;
                $scope.DBErrors = response.data.dbErrors;
            } else {
                alert("Product with name " + $scope.productName + " was updated!");
                _refreshProductInfo();

                $scope.productName = '';
                $scope.productPrice = '';
                $scope.productCategory = '';
                $scope.productDiscount = '';
                $scope.productDescription = '';
            }
        });
    };

    $scope.discard = function () {
        _refreshProductInfo();
    }
})