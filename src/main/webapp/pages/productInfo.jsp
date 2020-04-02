<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    <jsp:include page="particles/_header.jsp" />

    <div class="container">
        <div class="row">
            <div class="col-12">
                <h1>Products Info</h1>
            </div>
        </div>
        <div class="row">
            <div class="col-4">
                <h2>
                    <c:out value="${productName}"/>
                    <c:out value="${productDescription}"/>
                </h2>
            </div>
        </div>
    </div>

    <jsp:include page="particles/_footer.jsp" />
