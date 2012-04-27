<%--
  @file   calendar.jsp
  @author Robert Liu <rql@andrew.cmu.edu>
  @date   4/26/2012
  @class  15-437
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="calendar-tabs">
  <ul>
    <c:forEach var="showVM" items="${showCalVMs}">
      <li>
        <a href="#" onclick="showShowDiv('foo${showVM.showId}')">
          ${showVM.showName}
        </a>
      </li>
    </c:forEach>
  </ul>
</div>

<div id="calendar">
  <c:forEach var="showVM" items="${showCalVMs}">
    <div id="foo${showVM.showId}" class="show-div">
      <ul>
        <li id="calendar-header">
          <a href="deleteshow.do?id=${showVM.showId}">
            Delete Show
          </a>
          <a href="show.do?id=${showVM.showId}">
            Go To Show Page
          </a>
        </li>

        <li>
          <span id="day">Monday</span>
          <ul>
            <c:forEach var="airing" items="${showVM.mondayAirings}">
              <li class="airing">
                <span class="air-time">
                  Start:
                  <fmt:formatDate
                       value="${airing.startTime}"
                       type="both"
                       timeZone="${user.timeZone}"
                       pattern="MM-dd-yyyy | h:mm aa" />
                </span>
                <span class="air-time">
                  End:
                  <fmt:formatDate
                       value="${airing.stopTime}"
                       type="both"
                       timeZone="${user.timeZone}"
                       pattern="MM-dd-yyyy | h:mm aa" />
                </span>
              </li>
            </c:forEach>
          </ul>
        </li>

        <li>
          <span id="day">Tuesday</span>
          <ul>
            <c:forEach var="airing" items="${showVM.tuesdayAirings}">
              <li>
                <span class="air-time">
                  Start:
                  <fmt:formatDate
                       value="${airing.startTime}"
                       type="both"
                       timeZone="${user.timeZone}"
                       pattern="MM-dd-yyyy | h:mm aa" />
                </span>
                <span class="air-time">
                  End:
                  <fmt:formatDate
                       value="${airing.stopTime}"
                       type="both"
                       timeZone="${user.timeZone}"
                       pattern="MM-dd-yyyy | h:mm aa" />
                </span>
              </li>
            </c:forEach>
          </ul>
        </li>

        <li>
          <span id="day">Wednesday</span>
          <ul>
            <c:forEach var="airing" items="${showVM.wednesdayAirings}">
              <li class="airing">
                <span class="air-time">
                  Start:
                  <fmt:formatDate
                       value="${airing.startTime}"
                       type="both"
                       timeZone="${user.timeZone}"
                       pattern="MM-dd-yyyy | h:mm aa" />
                </span>
                <span class="air-time">
                  End:
                  <fmt:formatDate
                       value="${airing.stopTime}"
                       type="both"
                       timeZone="${user.timeZone}"
                       pattern="MM-dd-yyyy | h:mm aa" />
                </span>
              </li>
            </c:forEach>
          </ul>
        </li>

        <li>
          <span id="day">Thursday</span>
          <ul>
            <c:forEach var="airing" items="${showVM.thursdayAirings}">
              <li class="airing">
                <span class="air-time">
                  Start:
                  <fmt:formatDate
                       value="${airing.startTime}"
                       type="both"
                       timeZone="${user.timeZone}"
                       pattern="MM-dd-yyyy | h:mm aa" />
                </span>
                <span class="air-time">
                  End:
                  <fmt:formatDate
                       value="${airing.stopTime}"
                       type="both"
                       timeZone="${user.timeZone}"
                       pattern="MM-dd-yyyy | h:mm aa" />
                </span>
              </li>
            </c:forEach>
          </ul>
        </li>

        <li>
          <span id="day">Friday</span>
          <ul>
            <c:forEach var="airing" items="${showVM.fridayAirings}">
              <li class="airing">
                <span class="air-time">
                  Start:
                  <fmt:formatDate
                       value="${airing.startTime}"
                       type="both"
                       timeZone="${user.timeZone}"
                       pattern="MM-dd-yyyy | h:mm aa" />
                </span>
                <span class="air-time">
                  End:
                  <fmt:formatDate
                       value="${airing.stopTime}"
                       type="both"
                       timeZone="${user.timeZone}"
                       pattern="MM-dd-yyyy | h:mm aa" />
                </span>
              </li>
            </c:forEach>
          </ul>
        </li>

        <li>
          <span id="day">Saturday</span>
          <ul>
            <c:forEach var="airing" items="${showVM.saturdayAirings}">
              <li class="airing">
                <span class="air-time">
                  Start:
                  <fmt:formatDate
                       value="${airing.startTime}"
                       type="both"
                       timeZone="${user.timeZone}"
                       pattern="MM-dd-yyyy | h:mm aa" />
                </span>
                <span class="air-time">
                  End:
                  <fmt:formatDate
                       value="${airing.stopTime}"
                       type="both"
                       timeZone="${user.timeZone}"
                       pattern="MM-dd-yyyy | h:mm aa" />
                </span>
              </li>
            </c:forEach>
          </ul>
        </li>

        <li>
          <span id="day">Sunday</span>
          <ul>
            <c:forEach var="airing" items="${showVM.sundayAirings}">
              <li class="airing">
                <span class="air-time">
                  Start:
                  <fmt:formatDate
                       value="${airing.startTime}"
                       type="both"
                       timeZone="${user.timeZone}"
                       pattern="MM-dd-yyyy | h:mm aa" />
                </span>
                <span class="air-time">
                  End:
                  <fmt:formatDate
                       value="${airing.stopTime}"
                       type="both"
                       timeZone="${user.timeZone}"
                       pattern="MM-dd-yyyy | h:mm aa" />
                </span>
              </li>
            </c:forEach>
          </ul>
        </li>

      </ul>
    </div>
  </c:forEach>
</div>


