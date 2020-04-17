<!doctype html>
<html lang="en" ng-app="shoppingListApp">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title ng-bind="title + '&mdash; ShoppingListApp'">ShoppingListApp</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/logo.ico" type="image/x-icon">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/all.min.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
            integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/js/libs/bootstrap.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark" ng-controller="indexCtrl">
    <div class="container">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarText">
            <ul class="navbar-nav mr-auto">
                <li ng-class="{ active: isActive('/main')}" class="nav-item">
                    <a class="nav-link" href="#!/main">Home</a>
                </li>
                <li class="nav-item" ng-class="{ active: isActive('/products')}">
                    <a class="nav-link" href="#!/products">Products</a>
                </li>
            </ul>
            <a href="#!/addNewProduct" title="Add new product">
                <i class="fas fa-plus fa-2x"></i>
            </a>
            <a href="#!/carts" title="Carts">
                <i class="fas fa-shopping-cart fa-2x"></i>
            </a>
        </div>
    </div>
</nav>
<div class="ng-view" autoscroll></div>

<script src="${pageContext.request.contextPath}/js/libs/angular.min.js"></script>
<script src="${pageContext.request.contextPath}/js/libs/angular-route.min.js"></script>
<script src="${pageContext.request.contextPath}/js/module/shoppingList.js"></script>
<script src="${pageContext.request.contextPath}/js/controller/indexCtrl.js"></script>
<script src="${pageContext.request.contextPath}/js/controller/productCtrl.js"></script>
<script src="${pageContext.request.contextPath}/js/controller/productInfoCtrl.js"></script>
<script src="${pageContext.request.contextPath}/js/controller/cartCtrl.js"></script>
<script src="${pageContext.request.contextPath}/js/controller/cartInfoCtrl.js"></script>

</body>
</html>