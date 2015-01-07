package ttt.model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "gamewithplayer")
public class GameWithPlayer {

	public GameWithPlayer() {
	}

	@Id
	@GeneratedValue
	private Integer id;

	@ManyToOne
	private Users user1st;

	@ManyToOne
	private Users user2nd;

	private Date starttime;

	private Date endtime;

	private boolean tie = false;

	@ManyToOne
	private Users winner;

	@ManyToOne
	private Users loser;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Users getUser1st() {
		return user1st;
	}

	public void setUser1st(Users user1st) {
		this.user1st = user1st;
	}

	public Users getUser2nd() {
		return user2nd;
	}

	public void setUser2nd(Users user2nd) {
		this.user2nd = user2nd;
	}


	public boolean isTie() {
		return tie;
	}

	public void setTie(boolean tie) {
		this.tie = tie;
	}

	public Users getWinner() {
		return winner;
	}

	public void setWinner(Users winner) {
		this.winner = winner;
	}

	public Users getLoser() {
		return loser;
	}

	public void setLoser(Users loser) {
		this.loser = loser;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}


	
	
}
