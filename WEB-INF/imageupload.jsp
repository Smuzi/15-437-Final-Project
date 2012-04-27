<%--
  @file   imageupload.jsp
  @author Robert Liu <rql@andrew.cmu.edu>
  @author Jacob Olson <jholson@andrew.cmu.edu>
  @date   4/26/2012
  @class  15-437
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>Upload image</title>
    <c:if test="${form.action == 'upload' && fn:length(errors) == 0}">
      <meta http-equiv="refresh" content="0; url=show.do?id=${form.showId}" />
    </c:if>
    <link href="style.css" rel="stylesheet" type="text/css" media="screen" />
  </head>

  <body>
    <div class="container">
      <jsp:include page="header.jsp" />

      <jsp:include page="errorlist.jsp" />

      <form method="POST" action="imageupload.do"
            enctype="multipart/form-data">
        <input type="hidden" name="showId" value="${form.showId}" />
        <input type="hidden" name="action" value="upload" />

        <label>Upload new image:</label>
        <input type="file" name="image" />
        <br />

        <input type="submit" value="Upload" />
      </form>

      <a href="show.do?id=${form.showId}">Back to show</a>
    </div>
  </body>
</html>
