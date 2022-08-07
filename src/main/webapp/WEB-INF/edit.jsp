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
       	<h2>Edit a Film</h2>
       	<br>
       	<br>
       	<h3>Film ID: ${film.id }</h3>
	<form action="editFilm.do" method="GET">
	<input type ="hidden" id="id" name="id" value="${film.id }">
		<br>
		Title: <input type="text" id="title" name="title" value="${film.title}" placeholder ="${film.title }" /> 
		<br>
		<br>
		Description: <input type="text" id="description" name="description" value="${film.description}" placeholder ="${film.description }"/> 
		<br>
		<br>
		Release Year: <input type="text" id="releaseYear" name="releaseYear" value="${film.releaseYear}" placeholder ="${film.releaseYear }" /> 
		<br>
		<br>
		<section class="languageId">
        <label for="languageId">Language ID:</label>
        <select id="languageId" name="languageID">
          <option value=""></option>
          <option value="1">1. English</option>
          <option value="2">2. Italian</option>
          <option value="3">3. Japanese</option>
          <option value="4">4. Mandarin</option>
          <option value="5">5. French</option>
          <option value="6">6. German</option>
        </select>
      </section>
		<br>
		Rental Duration: <input type="text" id="rentalDuration" name="rentalDuration" value="${film.rentalDuration}" placeholder ="${film.rentalDuration }"/> 
		<br>
		<br>
		Rental Rate: <input type="text" id="rentalRate" name="rentalRate" value="${film.rentalRate}" placeholder ="${film.rentalRate }"/> 
		<br>
		<br>
		Length: <input type="text" id="length" name="length" value="${film.length}" placeholder ="${film.length }"/> 
		<br>
		<br>
		Replacement Cost: <input type="text" id="replacementCost" name="replacementCost" value="${film.replacementCost}" placeholder ="${film.replacementCost }"/> 
		<br>
		<br>
			<section class="rating">
        <label for="rating">Rating:</label>
        <select id="rating" name="rating">
          <option value=""></option>
          <option value="G">G</option>
          <option value="PG">PG</option>
          <option value="PG13">PG13</option>
          <option value="R">R</option>
          <option value="NC17">NC17</option>
        </select>
      </section>
		<br>
		<br>
	<section class="specialFeatures">
        <label for="specialFeatures">Special Features:</label>
        <select id="specialFeatures" name="specialFeatures">
          <option value=""></option>
          <option value="Trailers">Trailers</option>
          <option value="Commentaries">Commentaries</option>
          <option value="Deleted Scenes">Deleted Scenes</option>
          <option value="Behind the Scenes">Behind the Scenes</option>
        </select>
      </section>		
      <br>
		<br>
		<section class="category">
        <label for="category">Category:</label>
        <select id="category" name="category">
          <option value=""></option>
          <option value="Action">Action</option>
          <option value="Animation">Animation</option>
          <option value="Children">Children</option>
          <option value="Classics">Classics</option>
          <option value="Comedy">Comedy</option>
          <option value="Documentary">Documentary</option>
          <option value="Drama">Drama</option>
          <option value="Family">Family</option>
          <option value="Foreign">Foreign</option>
          <option value="Games">Games</option>
          <option value="Horror">Horror</option>
          <option value="Music">Music</option>
          <option value="New">New</option>
          <option value="Sci-Fi">Sci-Fi</option>
          <option value="Sports">Sports</option>
          <option value="Travel">Travel</option>
        </select>
      </section>
		
		<br>
		<br>
		
		<input type="submit"
			value="Update" />
			
	</form>
	<form action="deleteFilm.do" method="GET">
	<input type ="hidden" id="id" name="id" value="${film.id }">
		<input type="submit"
			value="Delete" />
    </c:when>
    <c:otherwise>
      <p>No film found</p>
    </c:otherwise>
  </c:choose>
        
</body>
</html>