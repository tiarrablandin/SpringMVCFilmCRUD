package com.skilldistillery.film.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	private String user = "student";
	private String pass = "student";

	public DatabaseAccessorObject() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Film findFilmById(int filmId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs;
		Film film = null;
		String sql = "SELECT * FROM film f JOIN language l ON f.language_id = l.id WHERE f.id = ?";
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, filmId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				String releaseYear = rs.getString("release_year");
				int languageID = rs.getInt("language_id");
				int rentalDuration = rs.getInt("rental_duration");
				double rentalRate = rs.getDouble("rental_rate");
				int length = rs.getInt("length");
				double replacementCost = rs.getDouble("replacement_cost");
				String rating = rs.getString("rating");
				String specialFeatures = rs.getString("special_features");
				List<Actor> actorList = findActorsByFilmId(filmId);
				String language = rs.getString("name");

				film = new Film(id, title, description, releaseYear, languageID, rentalDuration, rentalRate, length,
						replacementCost, rating, specialFeatures, actorList, language);
			}
			conn.close();
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}

	@Override
	public Actor findActorById(int actorId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs;
		Actor actor = null;
		String sql = "SELECT * FROM actor WHERE id = ?";
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, actorId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				actor = new Actor(id, firstName, lastName);
			}
			conn.close();
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actorList = new ArrayList<Actor>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs;
		Actor actor = null;
		String sql = "SELECT a.first_name, a.last_name FROM actor a JOIN film_actor f ON a.id = f.actor_id JOIN film fm ON f.film_id = fm.id WHERE f.film_id = ?";

		try {
			conn = DriverManager.getConnection(URL, user, pass);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, filmId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				actor = new Actor(firstName, lastName);
				actorList.add(actor);
			}
			conn.close();
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actorList;
	}

	public List<Film> findFilmByKeyWord(String key) {
		key = "%" + key + "%";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs;
		List<Film> filmList = new ArrayList<>();
		Film film = null;
		String sql = "SELECT * FROM film f JOIN language l ON f.language_id = l.id WHERE title LIKE ? OR description LIKE ?";
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, key);
			pstmt.setString(2, key);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				String releaseYear = rs.getString("release_year");
				int languageID = rs.getInt("language_id");
				int rentalDuration = rs.getInt("rental_duration");
				double rentalRate = rs.getDouble("rental_rate");
				int length = rs.getInt("length");
				double replacementCost = rs.getDouble("replacement_cost");
				String rating = rs.getString("rating");
				String specialFeatures = rs.getString("special_features");
				List<Actor> actorList = new ArrayList<Actor>(findActorsByFilmId(id));
				String language = rs.getString("name");

				film = new Film(id, title, description, releaseYear, languageID, rentalDuration, rentalRate, length,
						replacementCost, rating, specialFeatures, actorList, language);
				filmList.add(film);

			}
			conn.close();
			pstmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return filmList;

	}

	public Film createFilm(Film film) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false);
			String sql = "INSERT INTO film (title, language_id) " + " VALUES (?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, film.getTitle());
			stmt.setInt(2, film.getLanguageID());
			int updateCount = stmt.executeUpdate();
			if (updateCount == 1) {
				ResultSet keys = stmt.getGeneratedKeys();
				if (keys.next()) {
					int newFilmId = keys.getInt(1);
					film.setId(newFilmId);
					if (film.getActorList() != null && film.getActorList().size() > 0) {
						sql = "INSERT INTO film_actor (actor_id, film_id) VALUES (?,?)";
						stmt = conn.prepareStatement(sql);
						for (Actor actor : film.getActorList()) {
							stmt.setInt(1, actor.getId());
							stmt.setInt(2, newFilmId);
							updateCount = stmt.executeUpdate();
						}
					}
				}
				System.out.println(film);
			} else {
				film = null;
			}
			conn.commit();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			throw new RuntimeException("Error inserting actor " + film);
		}
		return film;
	}

//	public boolean saveFilm(Film film) {
//		String user = "student";
//		String pass = "student";
//		Connection conn = null;
//		try {
//			conn = DriverManager.getConnection(URL, user, pass);
//			conn.setAutoCommit(false); // START TRANSACTION
//			String sql = "UPDATE actor SET first_name=?, last_name=? " + " WHERE id=?";
//			PreparedStatement stmt = conn.prepareStatement(sql);
//			stmt.setString(1, actor.getFirstName());
//			stmt.setString(2, actor.getLastName());
//			stmt.setInt(3, actor.getId());
//			int updateCount = stmt.executeUpdate();
//			if (updateCount == 1) {
//				// Replace actor's film list
//				sql = "DELETE FROM film_actor WHERE actor_id = ?";
//				stmt = conn.prepareStatement(sql);
//				stmt.setInt(1, film.getId());
//				updateCount = stmt.executeUpdate();
//				sql = "INSERT INTO film_actor (film_id, actor_id) VALUES (?,?)";
//				stmt = conn.prepareStatement(sql);
//				for (Film film : film.getFilms()) {
//					stmt.setInt(1, film.getId());
//					stmt.setInt(2, film.getId());
//					updateCount = stmt.executeUpdate();
//				}
//				conn.commit(); // COMMIT TRANSACTION
//			}
//		} catch (SQLException sqle) {
//			sqle.printStackTrace();
//			if (conn != null) {
//				try {
//					conn.rollback();
//				} // ROLLBACK TRANSACTION ON ERROR
//				catch (SQLException sqle2) {
//					System.err.println("Error trying to rollback");
//				}
//			}
//			return false;
//		}
//		return true;
//	}
	public boolean deleteFilm(Film film) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false);
			String sql = "DELETE FROM film WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, film.getId());
			int updateCount = stmt.executeUpdate();
			conn.commit();
			System.out.println(updateCount + " Films deleted TITLE: " + film.getTitle());
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			return false;
		}
		return true;
	}
}
