package model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the genres database table.
 * 
 */
@Entity
@Table(name="genres")
@NamedQuery(name="Genre.findAll", query="SELECT g FROM Genre g")
public class Genre implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="genre_id")
	private int genreId;

	private String name;

	//bi-directional many-to-many association to Game
	@ManyToMany
	@JoinTable(
		name="game_genres"
		, joinColumns={
			@JoinColumn(name="genre_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="game_id")
			}
		)
	private List<Game> games;

	public Genre() {
	}

	public int getGenreId() {
		return this.genreId;
	}

	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Game> getGames() {
		return this.games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

}