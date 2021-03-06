<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
  <h1 id="banner">Unauthorized Access !!</h1>
  <hr />
  <c:if test="${not empty error}">
    <div style="color: red">
      Your fake login attempt was busted, dare again !!<br /> Caused : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
    </div>
  </c:if>
  <p class="message">Access denied!</p>
  <a href="<c:url value="/login" />">Go back to login page</a>
</body>
</html>