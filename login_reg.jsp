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
      <!-- This must go before login -->
      <div id="register">
        <h2>Register</h2>
        <form id="register-form">
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
        <form id="login-form">
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
