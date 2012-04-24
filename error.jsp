<%--
  @file   error.jsp
  @author Jacob Olson <jholson@andrew.cmu.edu>
  @date   4/05/2012
  @class  15-437
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head><title>Error</title></head>
<meta http-equiv="Content-Style-Type" content="text/css">
<link href="style.css" rel="stylesheet" type="text/css">
<body>
<div id="wrap">
  <div id="error">
    Errors:<br/>

    <c:forEach var="error" items="${errors}">
      <span class="error-text">${error}</span><br/>
    </c:forEach>
  </div>
</div>
</div>
</body>
</html>
