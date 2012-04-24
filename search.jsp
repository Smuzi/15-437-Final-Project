<%--
  @file   search.jsp
  @author Robert Liu <rql@andrew.cmu.edU>
  @date   4/05/2012
  @class  15-437
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>Search</title>
    <link href="style.css" rel="stylesheet" type="text/css" media="screen" />
  </head>

  <body id="search">
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
          <c:choose>
            <c:when test="${empty user}">
              <span>
                <a href="login.do">Login/Register</a>
              </span>
            </c:when>
            <c:otherwise>
              <span>
                <a href="logout.do">Logout</a>
                <a href="settings.do">Account Settings</a>
              </span>
            </c:otherwise>
          </c:choose>
        </div>
      </div>

      <div id="main-content">
        <h1>Search results for "blah"</h1>
        <ol>

          <li class="search-result">
            <div>Show Name#1</div>  
            <div class="search-result-body">
              <div class="result-img-container">
                <img class="search-result-img"
                     src="./amelie_home.jpg">
                </img>
              </div>
              <div class="search-result-info">
                <div class="search-result-description">
                  foo
                </div> 
                <div class="search-result-date">
                  4/23/12
                </div> 
              </div>
            <div>
          </li>

          <li class="search-result">
            <div>Show Name#2</div>  
            <div class="search-result-body">
              <div class="result-img-container">
                <img class="search-result-img"
                     src="./amelie_home.jpg">
                </img>
              </div>
              <div class="search-result-info">
                <div class="search-result-description">
                  bar
                </div> 
                <div class="search-result-date">
                  4/23/12
                </div> 
              </div>
            <div>
          </li>

        </ol> 
      </div>
    </div>
  </body>
</html>
