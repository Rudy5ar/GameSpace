package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private int userId;

	private String email;

	@Column(name="password")
	private String password;

	private String username;

	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	private List<Comment> comments;

	//bi-directional many-to-one association to UserGame
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	private List<UserGame> userGames;
	
	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="idRole")
	private Role role;

	public User() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setUser(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setUser(null);

		return comment;
	}

	public List<UserGame> getUserGames() {
		return this.userGames;
	}

	public void setUserGames(List<UserGame> userGames) {
		this.userGames = userGames;
	}

	public UserGame addUserGame(UserGame userGame) {
		getUserGames().add(userGame);
		userGame.setUser(this);

		return userGame;
	}

	public UserGame removeUserGame(UserGame userGame) {
		getUserGames().remove(userGame);
		userGame.setUser(null);

		return userGame;
	}
	
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}