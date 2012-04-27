<%--
  @file   show.jsp
  @author Robert Liu <rql@andrew.cmu.edU>
  @date   4/05/2012
  @class  15-437
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <%-- TODO: Switch this to autogenerate an appropriate title --%>
    <title>${show.showName}</title>
    <link href="style.css" rel="stylesheet" type="text/css" media="screen" />

    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script>
      /* It's initially showing show-times */
      var showing = "#show-times";

      function showDiv(elemName)
      {
        var id = "#" + elemName;
        if (id != showing)
        {
          $(showing).fadeOut(100);
          $(id).delay(100).fadeIn(200);
          showing = id;
        }
        else
        {
          $(showing).fadeOut(100);
          showing = "foo"
        }
      }
    </script> 
  </head>

  <body id="show">
    <div class="container">
      <jsp:include page="header.jsp" />

      <div id="main-content">
        <div id="show-header">
          <h3>${show.showName}</h3> 
          <c:if test="${isFavorite == false}">
            <a id="add-show" href="addshow.do?id=${show.id}">Add Show</a>
          </c:if>
          <c:if test="${user.admin}">
            <form method="POST" action="imageupload.do"
                  enctype="multipart/form-data">
              <input type="hidden" name="showId" value="${show.id}" />
              <input type="hidden" name="action" value="toForm" />
              <a href="#" onClick="parentNode.submit()">Upload image</a>
            </form>
          </c:if>
        </div>

        <div id="show-img">
          <img src="image.do?id=${show.imageId}">
          </img>
        </div>
        <div id="show-description">
          <c:choose>
            <c:when test="${not empty show.description}">
              ${show.description}
            </c:when>
            <c:otherwise>
              No description.
            </c:otherwise>
          </c:choose>
        </div>

        <div id="show-tabs">
          <ul>
            <li>
              <a href="#" onclick="showDiv('show-times')">Show Times</a>
            </li>
            <%--
            <li>
              <a href="#" onclick="showDiv('show-reviews')">Reviews</a>
            </li>
            --%>
          </ul>
        </div>
        <div id="show-info-container">
          <div id="show-times">
            <ul>
              <c:forEach var="airing" items="${airings}">
                <li class="airing">
                  <span class="air-time">
                    Start:
                    <fmt:formatDate
                         value="${airing.startTime}"
                         type="both"
                         timeZone="${user.timeZone}"
                         pattern="MM-dd-yyyy | h:mm aa" />
                  </span>
                  <span class="air-time">
                    End:
                    <fmt:formatDate
                         value="${airing.stopTime}"
                         type="both"
                         timeZone="${user.timeZone}"
                         pattern="MM-dd-yyyy | h:mm aa" />
                  </span>
                  <div class="air-name">
                    ${airing.channelNumber} ${airing.channelName}
                  </div>
                </li>
              </c:forEach>
            </ul>
          </div>
          <%--
          <div id="show-reviews">
            <h3>i'm a review</h3>
          </div>
          --%>
        </div>
      </div>
    </div>
  </body>
</html>
