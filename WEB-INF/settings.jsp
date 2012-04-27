<%--
  @file   settings.jsp
  @author Robert Liu <rql@andrew.cmu.edU>
  @date   4/05/2012
  @class  15-437
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>Account Settings</title>
    <link href="style.css" rel="stylesheet" type="text/css" media="screen" />
  </head>

  <body id="settings">
    <div class="container">
      <jsp:include page="header.jsp" />

      <h3>Account Settings</h3>
      <jsp:include page="errorlist.jsp" />

      <form id="settings-form" method="POST" action="settings.do">
        <label>Email</label>
        <input type="text" name="email" value="${form.email}" />
        <br />

        <label>Phone Number (for calendar notifications)</label>
        <input type="text" name="phoneNumber" value="${form.phoneNumber}" />
        <br />

        <label>Time Zone</label>
        <input type="text" name="timeZone" value="${form.timeZone}" />
        <br />

        <label>Zipcode</label>
        <input type="text" name="zipcode" value="${form.zipcode}" />
        <br />

        <input class="submit-button" type="submit" value="Update Settings" />
      </form>

      <form id="provider-form" method="POST" action="updateprovider.do">
        <label>Service Provider</label>
        <select name="providerIdAsString">
          <option value="${selectedProvider.id}" select="selected">
            ${selectedProvider.name}
          </option>
          <c:forEach var="providerChoice" items="${providerChoices}">
            <c:if test="${providerChoice != selectedProvider}">
              <option value="${providerChoice.id}">
                ${providerChoice.name}
              </option>
            </c:if>
          </c:forEach>
        </select>
        <br />

        <input class="submit-button" type="submit" value="Update Provider" />
      </form>
    </div>
  </body>
</html>
