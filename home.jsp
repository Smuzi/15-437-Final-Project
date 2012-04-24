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

  <body class="two-col-body">
    <div class="container">
      <div id="header">
        <div id="search-bar">
          <form action="search.do">
            <input class="search-button" type="submit" name="action" 
                   value="Search" />
            <div class="search-box">
              <input type="text" name="query" />
            </div>
          </form> 
        </div>

        <div id="login-link">
          <!-- TODO switch to action -->
          <span>
            <a href="login_reg.jsp">Login/Register</a>
          </span>
        </div>
      </div>

      <div id="main-content">
        <div id="show-sidebar">
          <ul id="top-show-list">
            <li>
              <a href="foo">
                <img class="show-list-image"
                     src="./amelie_home.jpg">
                </img>
              </a>
              <span>
                foo
              </span>
            </li>

            <li>
              <a href="foo">
                <img class="show-list-image"
                     src="./amelie_home.jpg">
                </img>
              </a>
              <span>
                bar
              </span>
            </li>

            <li>
              <a href="foo">
                <img class="show-list-image"
                     src="./amelie_home.jpg">
                </img>
              </a>
              <span>
                qux
              </span>
            </li>

            <li>
              <a href="foo">
                <img class="show-list-image"
                     src="./amelie_home.jpg">
                </img>
              </a>
              <span>
                frob
              </span>
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
