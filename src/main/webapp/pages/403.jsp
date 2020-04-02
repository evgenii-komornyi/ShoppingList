<%--
  Created by IntelliJ IDEA.
  User: sir.evgenius
  Date: 22.03.2020
  Time: 18:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Access denied</title>
    <jsp:include page="particles/_cssLink.jsp" />
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-12">
            <h1>Code ${code}</h1>
            <h2>${error}</h2>
        </div>
    </div>
</div>

<jsp:include page="particles/_js.jsp" />
</body>
</html>
