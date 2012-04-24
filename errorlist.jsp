<%--
  @file   errorlist.jsp
  @author Jacob Olson <jholson@andrew.cmu.edu>
  @date   4/05/2012
  @class  15-437
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="error" items="${errors}">
  <span class="error-text">${error}</span><br/>
</c:forEach>
