let shoppingList = angular.module('shoppingListApp', ['ngRoute']);

shoppingList.config(function ($routeProvider) {
    $routeProvider
    .when('/main', {
        templateUrl: 'main',
        controller: 'indexCtrl',
        title: 'Main Page'
    })
    .when('/products', {
        templateUrl: 'products',
        controller: 'productCtrl',
        title: 'Products'
    })
    .when('/product/:id', {
        templateUrl: 'product',
        controller: 'productInfoCtrl',
        title: 'Product Info'
    })
    .when('/carts', {
        templateUrl: 'carts',
        controller: 'cartCtrl',
        title: 'Carts'
    })
    .when('/cart/:id', {
        templateUrl: 'cart',
        controller: 'cartInfoCtrl',
        title: 'Cart Info'
    })
    .when('/addNewProduct', {
        templateUrl: 'addNewProduct',
        controller: 'productCtrl',
        title: 'Add new product'
    })
    .otherwise({redirectTo: 'main'})
});

shoppingList.run(['$rootScope', function($rootScope) {
    $rootScope.$on('$routeChangeSuccess', function (event, current, previous) {
        $rootScope.title = current.$$route.title;
    });
}]);