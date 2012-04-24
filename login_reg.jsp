<%--
  @file   login_reg.jsp
  @author Robert Liu <rql@andrew.cmu.edU>
  @date   4/05/2012
  @class  15-437
--%>

<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>Login/Registration</title>
    <link href="style.css" rel="stylesheet" type="text/css" media="screen" />
  </head>

  <body id="login_reg">
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

      <!-- This must go before login -->
      <div id="register">
        <h2>Register</h2>
        <form id="register-form" method="POST">
          <label>Username</label>
          <input type="text" name="username" />
          <br />

          <label>Password</label>
          <input type="password" name="password" />
          <br />

          <label>Reenter your password</label>
          <input type="password" name="passwordCopy" />
          <br />
          
          <label>Email</label>
          <input type="text" name="email" />
          <br />

          <input class="submit-button" type="submit" name="action"
                 value="Register" />
        </form>
      </div>

      <div id="login">
        <h2>Login</h2>
        <form id="login-form" method="POST">
          <label>Username</label>
          <input type="text" name="username" />
          <br />

          <label>Password</label>
          <input type="password" name="password" />
          <br />

          <input class="submit-button" type="submit" name="action"
                 value="Login" />
        </form>
      </div>
    </div>
  </body>
</html>
