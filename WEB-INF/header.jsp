<%--
  @file   header.jsp
  @author Robert Liu <rql@andrew.cmu.edU>
  @author Jacob Olson <jholson@andrew.cmu.edu>
  @date   4/05/2012
  @class  15-437
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="header">
  <div id="logo-container">
    <a href="home.do" tabindex="0">
      <img src="bluetube.jpg" />
    </a>
  </div>

  <div id="search-bar">
    <form action="search.do">
      <input class="search-box" type="text" name="query" tabindex="1" />
      <input class="search-button" type="submit" name="action" 
             value="Search" />
    </form> 
  </div>

  <div id="account-misc">
    <c:choose>
      <c:when test="${empty user}">
        <span>
          <a href="login.do">Login/Register</a>
        </span>
      </c:when>
      <c:otherwise>
        <span>
          <a href="profile.do">Profile</a>
          <a href="logout.do">Logout</a>
          <a href="settings.do">Account Settings</a>
        </span>
      </c:otherwise>
    </c:choose>
  </div>
</div>
