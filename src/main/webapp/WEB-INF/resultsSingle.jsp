<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Film</title>
<!-- CSS only -->
<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx"
    crossorigin="anonymous">
</head>
<body>
	<a href="index.html">Home</a>
	
    <script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
        crossorigin="anonymous"></script>
        
          <c:choose>
    <c:when test="${! empty film}">
      <ul>
        <li>Title: ${film.title}</li>
        <form action="editDisplay.do" method="GET">
        
<input type="hidden" name="id" value="${film.id}">
<input type="hidden" name="title" value="${film.title}">
<input type="hidden" name=description value="${film.description}">
<input type="hidden" name="releaseYear" value="${film.releaseYear}">
<input type="hidden" name="languageID" value="${film.languageID}">
<input type="hidden" name="rentalDuration" value="${film.rentalDuration}">
<input type="hidden" name="rentalRate" value="${film.rentalRate}">
<input type="hidden" name="length" value="${film.length}">
<input type="hidden" name="replacementCost" value="${film.replacementCost}">
<input type="hidden" name="rating" value="${film.rating}">
<input type="hidden" name="specialFeatures" value="${film.specialFeatures}">
<input type="hidden" name="language" value="${film.language}">
<input type="hidden" name="category" value="${film.category}">
        <li>Description: ${film.description}</li>
        <li>Release Year: ${film.releaseYear}</li>
        <li>Rating: ${film.rating}</li>
        <li>Language: ${film.language}</li>
        <li>Category: ${film.category}</li>
        
      Cast:
      <table>
      <tr>
    
    <c:forEach items="${film.actorList}" var="actor">
      <td>
         ${actor.firstName } ${actor.lastName }, 
      </td>
      </ul>
    </c:forEach>
      
      </tr>
      </table>
      <br>
      <input type=submit value=edit/delete>

</form>
    </c:when>
    <c:otherwise>
      <p>No film found</p>
    </c:otherwise>
  </c:choose>
        
</body>
</html>