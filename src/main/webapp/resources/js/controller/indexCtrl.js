shoppingList.controller('indexCtrl', ['$scope', '$location', '$http', function($scope, $location) {
    $scope.title = "Main Page";

    $scope.isActive = function (viewLocation) {
        const active = (viewLocation === $location.path());
        return active;
    }
}]);