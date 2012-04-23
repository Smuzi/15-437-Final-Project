<%--
  @file   search.jsp
  @author Robert Liu <rql@andrew.cmu.edU>
  @date   4/05/2012
  @class  15-437
--%>

<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>Search</title>
    <link href="style.css" rel="stylesheet" type="text/css" media="screen" />
  </head>

  <body id="search">
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

        <div id="results">
          <h1>Search results for "blah"</h1>
          <ol>
            <li class="search-result">
              <div>Show Name#1</div>  
              <img class="search-result-img"
                   src="./amelie_home.jpg">
              </img>
              <div class="search-result-info">
                <div class="search-result-description">
                  foo
                </div> 
                <div class="search-result-date">
                  4/23/12
                </div> 
              </div>
            </li>

            <li class="search-result">
              <div>Show Name#2</div>  
              <img class="search-result-img"
                   src="./amelie_home.jpg">
              </img>
              <div class="search-result-info">
                <div class="search-result-description">
                  foo
                </div> 
                <div class="search-result-date">
                  4/23/12
                </div> 
              </div>
            </li>
          </ol> 
        </div>

      </div>
    </div>
  </body>
</html>
