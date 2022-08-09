package org.websitebook.ws.domain.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.websitebook.ws.domain.dao.BookDAO;
import org.websitebook.ws.domain.dao.constants.ConnectionSql;
import org.websitebook.ws.domain.dao.entities.Book;
import org.websitebook.ws.domain.dao.entities.Bookmark;
import org.websitebook.ws.domain.dao.entities.User;

public class BookDAOImpl implements BookDAO{
	
	private static BookDAOImpl instance;
	private Book book = null;
	private Collection<Bookmark> data = null;
	private StringBuilder sqlQuery = null;
	private Connection conn = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;
		
	private BookDAOImpl() {}
	
	public static BookDAOImpl getInstance() {
		if(instance == null) {
			instance = new BookDAOImpl();
		}
		return instance;
	}
/*
	@Override
	public Book getById(Long id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getAll() throws SQLException {
		
		data = new ArrayList<>();
		sqlQuery = new StringBuilder();
		sqlQuery.append(" SELECT b.id, b.title, b.publication_year, a.name AS authors, b.book_genre_id, b.amazon_rating ");
		sqlQuery.append(" --b.image_url, ");
		sqlQuery.append(" FROM dbo.book b ");
		sqlQuery.append(" LEFT JOIN dbo.book_author ba ON ba.book_id = b.id ");
		sqlQuery.append(" LEFT JOIN dbo.author a ON a.id = ba.author_id ");
		sqlQuery.append(" WHERE b.id NOT IN (SELECT ub.book_id FROM User u LEFT JOIN User_Book ub  ON ub.user_id = u.id WHERE u.id = userId) ");
		
		try {
			Class.forName("org.postgresql.Driver");
			
			conn = DriverManager.getConnection(ConnectionSql.URL,ConnectionSql.USER_DB, ConnectionSql.PASS_DB);
			st = conn.prepareStatement(sqlQuery.toString());
			rs = st.executeQuery();

			while (rs.next()) {
			
				book = new Book();
				
				book.setId(rs.getLong("id"));
				book.setTitle(rs.getString("title"));
				book.setPublicationYear(Integer.parseInt(rs.getString("publication_year")));
				book.setAuthors(rs.getString("authors").split(","));
				book.setGenre(rs.getString("book_genre_id"));
				book.setAmazonRating(rs.getDouble("amazon_rating"));
				book.setImageUrl(rs.getString("image_url"));
				data.add(book);
			
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		} 
		
		return null;
	}*/

}
