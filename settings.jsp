<%--
  @file   settings.jsp
  @author Robert Liu <rql@andrew.cmu.edU>
  @date   4/05/2012
  @class  15-437
--%>

<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>Account Settings</title>
    <link href="style.css" rel="stylesheet" type="text/css" media="screen" />
  </head>

  <body>
    <div class="container">
      <div id="header">
        <div id="logo-container">
          <a href="home.jsp">
            <img src="bluetube.jpg"> 
          </a>
        </div>

        <div id="search-bar">
          <form action="search.do">
            <input class="search-box" type="text" name="query" />
            <input class="search-button" type="submit" name="action" 
                   value="Search" />
          </form> 
        </div>

        <div id="account-misc">
          <a href="login_reg.jsp">Login/Register</a>
        </div>
      </div>

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
