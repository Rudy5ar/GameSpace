package model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;


/**
 * The persistent class for the user_games database table.
 * 
 */
@Entity
@Table(name="user_games")
@NamedQuery(name="UserGame.findAll", query="SELECT u FROM UserGame u")
public class UserGame implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_game_id")
	private int userGameId;

//	private byte favorite;

	//bi-directional many-to-one association to Game
	@ManyToOne
	@JoinColumn(name="game_id")
	private Game game;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	public UserGame() {
	}

	public int getUserGameId() {
		return this.userGameId;
	}

	public void setUserGameId(int userGameId) {
		this.userGameId = userGameId;
	}

	public Game getGame() {
		return this.game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}