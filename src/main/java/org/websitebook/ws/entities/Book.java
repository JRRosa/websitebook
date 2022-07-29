package org.websitebook.ws.entities;

public class Book extends Bookmark {

	private Integer publicationYear;
	private String publisher;
	private String[] authors;
	private String genre;
	private Double amazonRating;
	
	public Integer getPublicationYear() {
		return publicationYear;
	}
	public void setPublicationYear(Integer publicationYear) {
		this.publicationYear = publicationYear;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String[] getAuthors() {
		return authors;
	}
	public void setAuthors(String[] authors) {
		this.authors = authors;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public Double getAmazonRating() {
		return amazonRating;
	}
	public void setAmazonRating(Double amazonRating) {
		this.amazonRating = amazonRating;
	}
	
}
