<%--
  @file   home.jsp
  @author Robert Liu <rql@andrew.cmu.edU>
  @author Jacob Olson <jholson@andrew.cmu.edu>
  @date   4/05/2012
  @class  15-437
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>Home</title>
    <link href="style.css" rel="stylesheet" type="text/css" media="screen" />
  </head>

  <body id="home">
    <div class="container">
      <jsp:include page="header.jsp" />

      <div id="main-content">
        <div id="show-sidebar">
          <ul id="top-show-list">
            <div>Most popular shows:</div>
            <c:forEach var="show" items="${topShows}">
              <li>
                <a class="show-list-image-container" 
                   href="show.do?id=${show.id}">
                  <img class="show-list-image"
                       src="image.do?id=${show.imageId}">
                  </img>
                </a>
                <a class="show-list-name" href="show.do?id=${show.id}">
                  ${show.showName}
                </a>
              </li>
            </c:forEach>
          </ul>
        </div>

        <div id="news">
          <div id="news-image">
            <img src ="./amelie_home.jpg" />
          </div>
        </div>
      </div>
    </div>

  </body>
</html>
