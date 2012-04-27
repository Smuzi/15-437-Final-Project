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

        <li>
          <span>Monday</span>
          <ul>
            <c:forEach var="airing" items="${showVM.mondayAirings}">
              <li class="airing">
                <span>${airing.startTime}</span>
                <span>${airing.stopTime}</span>
                <div>${airing.channelNumber} ${airing.channelName}</div>
              </li>
            </c:forEach>
          </ul>
        </li>

        <li>
          <span>Tuesday</span>
          <ul>
            <c:forEach var="airing" items="${showVM.tuesdayAirings}">
              <li class="airing">
                <span>${airing.startTime}</span>
                <span>${airing.stopTime}</span>
                <div>${airing.channelNumber} ${airing.channelName}</div>
              </li>
            </c:forEach>
          </ul>
        </li>

        <li>
          <span>Wednesday</span>
          <ul>
            <c:forEach var="airing" items="${showVM.wednesdayAirings}">
              <li class="airing">
                <span>${airing.startTime}</span>
                <span>${airing.stopTime}</span>
                <div>${airing.channelNumber} ${airing.channelName}</div>
              </li>
            </c:forEach>
          </ul>
        </li>

        <li>
          <span>Thursday</span>
          <ul>
            <c:forEach var="airing" items="${showVM.thursdayAirings}">
              <li class="airing">
                <span>${airing.startTime}</span>
                <span>${airing.stopTime}</span>
                <div>${airing.channelNumber} ${airing.channelName}</div>
              </li>
            </c:forEach>
          </ul>
        </li>

        <li>
          <span>Friday</span>
          <ul>
            <c:forEach var="airing" items="${showVM.fridayAirings}">
              <li class="airing">
                <span>${airing.startTime}</span>
                <span>${airing.stopTime}</span>
                <div>${airing.channelNumber} ${airing.channelName}</div>
              </li>
            </c:forEach>
          </ul>
        </li>

        <li>
          <span>Saturday</span>
          <ul>
            <c:forEach var="airing" items="${showVM.saturdayAirings}">
              <li class="airing">
                <span>${airing.startTime}</span>
                <span>${airing.stopTime}</span>
                <div>${airing.channelNumber} ${airing.channelName}</div>
              </li>
            </c:forEach>
          </ul>
        </li>

        <li>
          <span>Sunday</span>
          <ul>
            <c:forEach var="airing" items="${showVM.sundayAirings}">
              <li class="airing">
                <span>${airing.startTime}</span>
                <span>${airing.stopTime}</span>
                <div>${airing.channelNumber} ${airing.channelName}</div>
              </li>
            </c:forEach>
          </ul>
        </li>

      </ul>
    </div>
  </c:forEach>
</div>


