package model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


/**
 * The persistent class for the games database table.
 * 
 */
@Entity
@Table(name="games")
@NamedQuery(name="Game.findAll", query="SELECT g FROM Game g")
public class Game implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="game_id")
	private int gameId;

	private String genre;

	private String platform;

	private String title;

	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="game")
	private List<Comment> comments;

	//bi-directional many-to-many association to Genre
	@ManyToMany(mappedBy="games")
	private List<Genre> genres;

	//bi-directional many-to-many association to Studio
	@ManyToMany
	@JoinTable(
		name="games_studios"
		, joinColumns={
			@JoinColumn(name="game_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="studio_id")
			}
		)
	private List<Studio> studios;

	//bi-directional many-to-one association to UserGame
	@OneToMany(mappedBy="game")
	private List<UserGame> userGames;

	public Game() {
	}

	public int getGameId() {
		return this.gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public String getGenre() {
		return this.genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getPlatform() {
		return this.platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setGame(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setGame(null);

		return comment;
	}

	public List<Genre> getGenres() {
		return this.genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public List<Studio> getStudios() {
		return this.studios;
	}

	public void setStudios(List<Studio> studios) {
		this.studios = studios;
	}

	public List<UserGame> getUserGames() {
		return this.userGames;
	}

	public void setUserGames(List<UserGame> userGames) {
		this.userGames = userGames;
	}

	public UserGame addUserGame(UserGame userGame) {
		getUserGames().add(userGame);
		userGame.setGame(this);

		return userGame;
	}

	public UserGame removeUserGame(UserGame userGame) {
		getUserGames().remove(userGame);
		userGame.setGame(null);

		return userGame;
	}

}