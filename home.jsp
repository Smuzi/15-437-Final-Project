<%--
  @file   home.jsp
  @author Robert Liu <rql@andrew.cmu.edU>
  @author Jacob Olson <jholson@andrew.cmu.edu>
  @date   4/05/2012
  @class  15-437
--%>

<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>Home</title>
    <link href="style.css" rel="stylesheet" type="text/css" media="screen" />
  </head>

  <body id="home">
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

      <div id="main-content">
        <div id="show-sidebar">
          <ul id="top-show-list">
            <div>Most popular shows:</div>
            <li>
              <a class="show-list-image-container" href="foo">
                <img class="show-list-image"
                     src="./amelie_home.jpg">
                </img>
              </a>
              <a href="foo">foo</a>
            </li>

            <li>
              <a class="show-list-image-container" href="foo">
                <img class="show-list-image"
                     src="./amelie_home.jpg">
                </img>
              </a>
              <a href="bar">bar</a>
            </li>

            <li>
              <a class="show-list-image-container" href="foo">
                <img class="show-list-image"
                     src="./amelie_home.jpg">
                </img>
              </a>
              <a href="qux">qux</a>
            </li>

            <li>
              <a class="show-list-image-container" href="foo">
                <img class="show-list-image"
                     src="./amelie_home.jpg">
                </img>
              </a>
              <a href="frob">frob</a>
            </li>
          </ul>
        </div>

        <div id="news">
          <div id="news-image">
            <img src ="./amelie_home.jpg" />
          </div>
        </div>
      </div>
    </div>

  </body>
</html>
