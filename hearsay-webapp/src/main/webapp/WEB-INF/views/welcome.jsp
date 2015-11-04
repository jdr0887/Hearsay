<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<body>
  <h2>Hearsay</h2>
  <sec:authorize access="hasRole('CARNAC')">
    <a href="<c:url value="/carnac/participantList" />">CARNAC</a>
  </sec:authorize>
</body>
</html>
