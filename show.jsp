<%--
  @file   show.jsp
  @author Robert Liu <rql@andrew.cmu.edU>
  @date   4/05/2012
  @class  15-437
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <%-- TODO: Switch this to autogenerate an appropriate title --%>
    <title>Generic Show</title>
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
      }
    </script> 
  </head>

  <body id="show">
    <div class="container">
      <jsp:include page="header.jsp" />

      <div id="main-content">
        <div id="show-header">
          <h3>Modern Family</h3> 
          5.0/5.0
        </div>

        <div id="show-img">
          <img src="./amelie_home.jpg">
          </img>
        </div>
        <div id="show-description">
          foo
        </div>

        <div id="show-tabs">
          <ul>
            <li>
              <a href="#" onclick="showDiv('show-times')">Show Times</a>
            </li>
            <li>
              <a href="#" onclick="showDiv('show-reviews')">Reviews</a>
            </li>
          </ul>
        </div>
        <div id="show-info-container">
          <div id="show-times">
            <h3>some times</h3>
          </div>
          <div id="show-reviews">
            <h3>i'm a review</h3>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
