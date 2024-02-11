package model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;


/**
 * The persistent class for the studios database table.
 * 
 */
@Entity
@Table(name="studios")
@NamedQuery(name="Studio.findAll", query="SELECT s FROM Studio s")
public class Studio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="studio_id")
	private int studioId;

	private String name;

	//bi-directional many-to-many association to Game
	@ManyToMany(mappedBy="studios")
	private List<Game> games;

	public Studio() {
	}
	
	public Studio(String s) {
		this.name = s;
	}

	public int getStudioId() {
		return this.studioId;
	}

	public void setStudioId(int studioId) {
		this.studioId = studioId;
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