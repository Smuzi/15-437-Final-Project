<%--
  @file   search.jsp
  @author Robert Liu <rql@andrew.cmu.edU>
  @date   4/05/2012
  @class  15-437
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>Search</title>
    <link href="style.css" rel="stylesheet" type="text/css" media="screen" />
  </head>

  <body id="search">
    <div class="container">
      <jsp:include page="header.jsp" />

      <div id="main-content">
        <c:if test="${(fn:length(errors)) == 0}">
          <h1>Search results for "${form.query}"</h1>
        </c:if>

        <jsp:include page="errorlist.jsp" />

        <ol>
          <c:forEach var="show" items="${shows}">
            <li class="search-result">
              <div>${show.showName}</div>  
              <div class="search-result-body">
                <a class="result-img-container" href="show.do?id=${show.id}">
                  <img class="search-result-img"
                       src="image.do?id=${show.imageId}">
                  </img>
                </a>
                <div class="search-result-info">
                  <div class="search-result-description">
                    ${show.description}
                  </div> 
                </div>
              <div>
            </li>
          </c:forEach>
        </ol> 
      </div>
    </div>
  </body>
</html>
