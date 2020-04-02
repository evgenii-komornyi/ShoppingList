<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="particles/_header.jsp" />

<div class="container">
    <div class="row">
        <div class="col-12">
            <h1>Cart List</h1>
        </div>
    </div>
    <div class="row row-cols-1 row-cols-md-3">
        <c:forEach items="${carts}" var="cart">
            <div class="col-4 mb-4">
                <div class="card text-white bg-dark mb-3 all_products" style="max-width: 18rem;">
                    <i class="fas fa-cart-arrow-down fa-5x card-img-top"></i>
                    <div class="card-header">
                        <div class="cart-name">
                            <a href="${pageContext.request.contextPath}/cart/${cart.cartId}"><c:out value="${cart.cartName}"/></a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<jsp:include page="particles/_footer.jsp" />