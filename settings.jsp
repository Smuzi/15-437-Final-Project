<%--
  @file   settings.jsp
  @author Robert Liu <rql@andrew.cmu.edU>
  @date   4/05/2012
  @class  15-437
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>Account Settings</title>
    <link href="style.css" rel="stylesheet" type="text/css" media="screen" />
  </head>

  <body>
    <div class="container">
      <jsp:include page="settings.jsp" />

      <h3>Account Settings</h3>
      <form id="settings-form">
        <label>Email</label>
        <input type="text" name="email" />
        <br />

        <label>Phone Number (for calendar notifications)</label>
        <input type="text" name="email" />
        <br />
      </form>
    </div>
  </body>
</html>
