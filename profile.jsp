<%--
  @file   profile.jsp
  @author Robert Liu <rql@andrew.cmu.edU>
  @date   4/05/2012
  @class  15-437
--%>

<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>Profile</title>
    <link href="style.css" rel="stylesheet" type="text/css" media="screen" />

    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script>
      /* It's initially showing the calendar */
      var showing = "#calendar";

      function showDiv(elemName)
      {
        var id = "#" + elemName;
        if (id != showing)
        {
          $(showing).fadeOut(100);
          $(id).delay(100).fadeIn(200);
          showing = id;
        }
      }
    </script> 
  </head>

  <body id="profile">
    <div class="container">
      <div id="header">
        <span>${user.username}</span>

        <a href="logout.do">Logout</a>
        <a href="settings.do">Account Settings</a>
      </div>

      <div id="profile-tabs">
        <ul>
          <li>
            <a href="#" onclick="showDiv('calendar')">Calendar</a>
          </li>
          <li>
            <a href="#" onclick="showDiv('show-subs')">My Shows</a>
          </li>
        </ul>
      </div>
      <div id="profile-main-container">
        <div id="calendar">
        </div>

        <div id="show-subs">
          <ul>
            <li>
            Show 1
            </li>
            <li>
            Show 2
            </li>
            <li>
            Show 3
            </li>
          </ul>
        </div>
      </div>
    </div>
  </body>
</html>
