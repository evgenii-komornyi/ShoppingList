<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    <jsp:include page="particles/_header.jsp" />

    <div class="container">
        <div class="row">
            <div class="col-12">
                <h1>Products List</h1>
            </div>
        </div>
        <div class="row row-cols-1 row-cols-md-3">
            <c:forEach items="${products}" var="prod">
                <div class="col-4 mb-4">
                    <div class="card text-white bg-dark mb-3 all_products">
                        <img src="http://placehold.it/150x100" class="card-img-top" alt="...">
                        <div class="card-header">
                            <div class="product-name">
                                <a href="${pageContext.request.contextPath}/product/${prod.productID}"><c:out value="${prod.productName}"/></a>
                                <div class="buy dropdown">
                                    <a class="dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" title="Add to cart">
                                        <i class="fas fa-cart-plus fa-2x"></i>
                                    </a>
                                    <div class="dropdown-menu" aria-labelledby="dropdownMenuButton" ng-controller="myController">
                                        <a class="dropdown-item" data-toggle="modal" data-target="#createCartModal"><i class="fas fa-plus plus"></i> New cart</a>
                                        <c:forEach items="${carts}" var="cart">
                                            <a class="dropdown-item addToCart" ng-click="addToCart(${prod.productID}, ${cart.cartId})">${cart.cartName}</a>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card-body">
                            <div class="price">
                                <div class="product-regular_price">
                                    <fmt:formatNumber value="${prod.productRegularPrice}" currencySymbol="&euro;" type="currency"/>
                                </div>
                                <div class="product-actual_price">
                                    <fmt:formatNumber value="${prod.calculateActualPrice()}" currencySymbol="&euro;" type="currency"/>
                                </div>
                            </div>
                            <div class="product-discount">
                                -<c:out value="${prod.productDiscount}"/>%
                            </div>
                            <div class="product-category">
                                <c:out value="${prod.category}"/>
                            </div>
                            <div class="card-footer">
                                <div class="control">
                                    <a href="product/editProduct/${prod.productID}" title="Edit product">
                                        <i class="far fa-edit fa-2x"></i>
                                    </a>
                                    <a data-toggle="modal" data-target="#modal-${prod.productID}" title="Remove product">
                                        <i class="fas fa-trash-alt fa-2x"></i>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <!-- Modal -->
    <c:forEach items="${products}" var="prod">
    <div class="modal fade" id="modal-${prod.productID}" tabindex="-1" role="dialog" aria-labelledby="modal${prod.productID}Label" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Remove product</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <p>Do you want to remove product by ID: ${prod.productID} from shop?</p>
                </div>
                <div class="modal-footer">
                    <a href="/product/${prod.productID}" class="btn btn-danger delete">Yes</a>
                    <a class="btn btn-secondary" data-dismiss="modal">No</a>

                </div>
            </div>
        </div>
    </div>
    </c:forEach>

    <jsp:include page="particles/_footer.jsp" />