<%--
  @file   imageupload.jsp
  @author Robert Liu <rql@andrew.cmu.edu>
  @author Jacob Olson <jholson@andrew.cmu.edu>
  @date   4/26/2012
  @class  15-437
--%>

<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>Upload image</title>
    <meta http-equiv="refresh" content="0; url=show.do?${form.getShowId}" />
  </head>

  <body>
    <div class="container">
      <jsp:include page="header.jsp" />

      <jsp:include page="errorlist.jsp" />

      <form method="POST" action="imageupload.do"
            enctype="multipart/form-data">
        <input type="hidden" name="showId" value="${form.getShowId}" />

        <label>Upload new image:</label>
        <input type="file" name="image" />
        <br />

        <input type="submit" value="Upload" />
      </form>

      <a href="show.do?id=${form.getShowId}">Back to show</a>
    </div>
  </body>
</html>
