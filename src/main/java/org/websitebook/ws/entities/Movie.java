package org.websitebook.ws.entities;

public class Movie extends Bookmark {

	private Integer releaseYear;
	private String[] cast;
	private String[] directors;
	private String genre;
	private Double imdbRating;
	
	public Integer getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}
	public String[] getCast() {
		return cast;
	}
	public void setCast(String[] cast) {
		this.cast = cast;
	}
	public String[] getDirectors() {
		return directors;
	}
	public void setDirectors(String[] directors) {
		this.directors = directors;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public Double getImdbRating() {
		return imdbRating;
	}
	public void setImdbRating(Double imdbRating) {
		this.imdbRating = imdbRating;
	}
	
}
