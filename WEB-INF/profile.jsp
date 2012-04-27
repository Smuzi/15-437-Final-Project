<%--
  @file   profile.jsp
  @author Robert Liu <rql@andrew.cmu.edU>
  @date   4/05/2012
  @class  15-437
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>Profile</title>
    <link href="style.css" rel="stylesheet" type="text/css" media="screen" />
    <%-- TODO: stylesheet for the calendar. Not sure if I can just stick a 
         <head> tag in calendar.jsp --%>
    <link href="cal.css" rel="stylesheet" type="text/css" media="screen" />

    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script>
      /* Displays a div for the times for a show */
      // This is just a placeholder since we don't show anything at first
      var showing = "foo";

      function showShowDiv(showName)
      {
        var id = "#" + showName;
        if (id != showing)
        {
          $(showing).fadeOut(100);
          $(id).delay(100).fadeIn(200);
          showing = id;
        }
        else
        {
          $(showing).fadeOut(100);
          showing = "foo";
        }
      }
    </script> 
  </head>

  <body id="profile">
    <div class="container">
      <jsp:include page="header.jsp" />

      <div id="main-content">
        <div>
          <span>
            <span>${user.username}</span>
          </span>
        </div>

        <div id="profile-main-container">
          <div id="calendar">
            <jsp:include page="calendar.jsp" />
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
