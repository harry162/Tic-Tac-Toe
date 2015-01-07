package ttt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "savedsteps")
public class SavedSteps {

	@Id
	@GeneratedValue
	private Integer id;

	@ManyToOne
	private GameWithAI gameid;
	
	private String position;

	private boolean userpos;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GameWithAI getGameid() {
		return gameid;
	}

	public void setGameid(GameWithAI gameid) {
		this.gameid = gameid;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public boolean isUserpos() {
		return userpos;
	}

	public void setUserpos(boolean userpos) {
		this.userpos = userpos;
	}
	
	
	
}
