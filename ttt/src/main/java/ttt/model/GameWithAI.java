package ttt.model;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "gamewithai")

public class GameWithAI {

	@Id
	@GeneratedValue
	private Integer id;

	@ManyToOne
	private Users username;
		
	private Date starttime;
	
	private Date endtime;
	
	private boolean gamefinished = false;
	
	private Date savetime;
	
	private String result;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Users getUsername() {
		return username;
	}

	public void setUsername(Users username) {
		this.username = username;
	}


		
	public boolean isGamefinished() {
		return gamefinished;
	}

	public void setGamefinished(boolean gamefinished) {
		this.gamefinished = gamefinished;
	}

	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
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

	public Date getSavetime() {
		return savetime;
	}

	public void setSavetime(Date savetime) {
		this.savetime = savetime;
	}

	
}
