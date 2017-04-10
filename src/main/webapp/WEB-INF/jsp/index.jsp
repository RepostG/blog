
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>G的时间表</title>
<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<link rel="icon" href="${pageContext.request.contextPath}/static/images/ico.ico" type="image/x-icon" />
<style>
@import url('https://fonts.googleapis.com/css?family=Nunito:300,400,700');

/* _variables.css */

/* _mixins.css */

/* _global.css */
* {
  box-sizing: border-box;
}

body {
  font-family: 'Nunito', sans-serif;
  background: -webkit-linear-gradient(right, #BE93C5 , #7BC6CC);
  background: linear-gradient(to left, #BE93C5 , #7BC6CC);
}

img {
  max-width: 100%;
  height: auto;
}

/* _site-header.css */
.site-header {
  text-align: center;
  padding: 40px 0;

}
.site-header__title {
  font-size: 36px;
  color: #fff;
}

/* _wrapper.css */
.wrapper {
  padding-left: 18px;
  padding-right: 18px;
  max-width: 1236px;
  margin-left: auto;
  margin-right: auto;
}


/* _timeline.css */
.timeline {
  position: relative;
  margin: 30px auto;
  padding: 60px 0;

}
.timeline::before {
  content: "";
  position: absolute;
  top: 0;
  left: 10%;
  width: 4px;
  height: 100%;
  background-color: #8d94b1;
}
@media (min-width: 800px){
  .timeline::before{
    left: 50%;
    margin-left: -2px;
  }
}
.timeline__item {
  margin-bottom: 100px;
  position: relative;
}
.timeline__item::after{
  content: "";
  clear: both;
  display: table;
}
.timeline__item:nth-child(2n) .timeline__item__content {
  float: right;
}
.timeline__item:nth-child(2n) .timeline__item__content::before {
  content: '';
  right: 40%;
}
@media (min-width: 800px){
  .timeline__item:nth-child(2n) .timeline__item__content::before{
    left: inherit;
  }
}
.timeline__item:nth-child(2n) .timeline__item__content__date {
  background-color: #b292c5;
}
.timeline__item:nth-child(2n) .timeline__item__content__description {
  color: #b292c5;
}
.timeline__item:last-child {
  margin-bottom: 0;
}
.timeline__item-bg {
  -webkit-transition: all 1s ease-out;
  transition: all 1s ease-out;
  color: #fff;
}
.timeline__item-bg:nth-child(2n) .timeline__item__station {
  background-color: #b292c5;
}
.timeline__item-bg:nth-child(2n) .timeline__item__content {
  background-color: #b292c5;
}
.timeline__item-bg:nth-child(2n) .timeline__item__content::before {
  background-color: #b292c5;
}
.timeline__item-bg:nth-child(2n) .timeline__item__content__description {
  color: #fff;
}
.timeline__item-bg .timeline__item__station {
  background-color: #65adb7;
}
.timeline__item-bg .timeline__item__content {
  background-color: #65adb7;
}
.timeline__item-bg .timeline__item__content::before {
  background-color: #65adb7;
}
.timeline__item-bg .timeline__item__content__description {
  color: #fff;
}
.timeline__item__station {
  background-color: #9aa0b9;
  width: 40px;
  height: 40px;
  position: absolute;
  border-radius: 50%;
  padding: 10px;
  top: 0;
  left: 10%;
  margin-left: -33px;
  border: 4px solid #8d94b1;
  -webkit-transition: all .3s ease-out;
  transition: all .3s ease-out;
}
@media (min-width: 800px){
  .timeline__item__station{
    left: 50%;
    margin-left: -30px;
    width: 60px;
    height: 60px;
    padding: 15px;
    border-width: 6px;
  }
}
.timeline__item__content {
  width: 80%;
  background: #fff;
  padding: 20px 30px;
  border-radius: 6px;
  float: right;
  -webkit-transition: all .3s ease-out;
  transition: all .3s ease-out;
}
@media (min-width: 800px){
  .timeline__item__content{
    width: 40%;
    float: inherit;
    padding: 30px 40px;
  }
}
.timeline__item__content::before {
  content: '';
  position: absolute;
  left: 10%;
  background: #8d94b1;
  top: 20px;
  width: 10%;
  height: 4px;
  z-index: -1;
  -webkit-transition: all .3s ease-out;
  transition: all .3s ease-out;
}
@media (min-width: 800px){
  .timeline__item__content::before{
    left: 40%;
    top: 30px;
    height: 4px;
    margin-top: -2px;
  }
}
.timeline__item__content__date {
  margin: 0;
  padding: 8px 12px;
  font-size: 15px;
  margin-bottom: 10px;
  background-color: #65adb7;
  color: #fff;
  display: inline-block;
  border-radius: 4px;
  border: 2px solid #fff;
}
.timeline__item__content__description {
  margin: 0;
  padding: 0;
  font-size: 16px;
  line-height: 24px;
  font-weight: 300;
  color: #65adb7;
}
@media (min-width: 800px){
  .timeline__item__content__description{
    font-size: 19px;
    line-height: 28px;
  }
}

/* _site-footer.css */
.site-footer {
  padding: 50px 0 200px 0;

}
.site-footer__text {
  color: #e6e6e6;
  font-size: 14px;
  text-align: center;
}
.site-footer__text__link {
  color: #8287a9;
}</style>
</head>
<body>

<script src="${pageContext.request.contextPath}/static/js/jquery.waypoints.min.js"></script>

<header class="site-header">
  <div class="wrapper">
    <h1 class="site-header__title">G的时间表</h1>
    <p class="site-header__title" style="font-size: 10px">余生好长，你好难忘。</p>
  </div>
</header>

<section class="timeline">
  <div class="wrapper">
  
  <c:forEach items="${findall }" var="findalls" varStatus="vs">
    <div class="timeline__item timeline__item--${vs.index }">
      <div class="timeline__item__station"></div>
      <a href="about.html?id=${findalls.id }" style="text-decoration:none">
      <div class="timeline__item__content">
        <h2 class="timeline__item__content__date"><fmt:formatDate value="${findalls.adddates }" pattern="yyyy" /> <fmt:formatDate value="${findalls.adddates }" pattern="MM" />. <fmt:formatDate value="${findalls.adddates }" pattern="dd" /></h2>
        <p class="timeline__item__content__description">${findalls.title }</p>
      </div>
      </a>
    </div>
  </c:forEach>
    
  </div>
</section>

<script>
function customWayPoint(className, addClassName, customOffset) {
  var waypoints = $(className).waypoint({
    handler: function(direction) {
      if (direction == "down") {
        $(className).addClass(addClassName);
      } else {
        $(className).removeClass(addClassName);
      }
    },
    offset: customOffset
  });
}

var defaultOffset = '50%';

for (i = 0; i < 17; i++) {
  customWayPoint('.timeline__item--' + i, 'timeline__item-bg', defaultOffset);
}</script>

</body>
</html>
