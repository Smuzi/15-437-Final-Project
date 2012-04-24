<%--
  @file   login_reg.jsp
  @author Robert Liu <rql@andrew.cmu.edU>
  @date   4/05/2012
  @class  15-437
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>Login/Registration</title>
    <link href="style.css" rel="stylesheet" type="text/css" media="screen" />
  </head>

  <body id="login_reg">
    <div class="container">
      <!-- TODO Fix so that errors display in proper section --!>
      <c:forEach var="error" items="${errors}">
        <span class="error-text">${error}</span><br/>
      </c:forEach>

      <!-- This must go before login -->
      <div id="register">
        <h2>Register</h2>
        <form id="register-form" method="POST" action="register.do">
          <label>Username</label>
          <input type="text" name="username" tabindex=2
                 value="${registerForm.username}" />
          <br />

          <label>Password</label>
          <input type="password" name="password" tabindex=2 />
          <br />

          <label>Reenter your password</label>
          <input type="password" name="passwordAgain" tabindex=2 />
          <br />
          
          <label>Email</label>
          <input type="text" name="email" tabindex=2
                 value="${registerForm.email}" />
          <br />

          <label>Time zone</label>
          <input type="text" name="timeZone" tabindex=2
                 value="${registerForm.timeZone}" />

          <label>Zipcode</label>
          <input type="text" name="zipcode" tabindex=2
                 value="${registerForm.zipcode}" />

          <input class="submit-button" type="submit" tabindex=2
                 value="Register" />
        </form>
      </div>

      <div id="login">
        <h2>Login</h2>
        <form id="login-form">
          <label>Username</label>
          <input type="text" name="username" tabindex=1
                 value="${loginForm.username}" />
          <br />

          <label>Password</label>
          <input type="password" name="password" tabindex=1 />
          <br />

          <input class="submit-button" type="submit" tabindex=1
                 value="Login" />
        </form>
      </div>
    </div>
  </body>
</html>
