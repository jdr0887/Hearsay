<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<title>Hearsay CARNAC</title>
<link rel="stylesheet" media="all" type="text/css" href="<c:url value="/resources/css/jquery-ui-1.8.16.custom.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/bower_components/tipsy/src/stylesheets/tipsy.css" />" />
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/dojo/1.10.2/dijit/themes/claro/claro.css" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/CANVAS.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/Master.css"/>" />
</head>

<body class="claro">
  <form:form method="post" action="carnac" commandName="participant">
    <table>
      <tr>
        <td>Participant:</td>
        <td>
          <form:select path="participant">
            <form:option value="0" label="--- Select ---" />
            <c:if test="${!empty participantList}">
              <form:options items="${participantList}" itemValue="name" itemLabel="name" />
            </c:if>
          </form:select>
        </td>
        <td><input type="submit" value="Submit" /></td>
      </tr>
    </table>
  </form:form>
</body>
</html>
