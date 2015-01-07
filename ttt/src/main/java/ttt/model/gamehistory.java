package ttt.model;

import java.util.Date;

public class gamehistory {
	
	Date starttime;
	
	String opponent;
	
	String duration;
	
	String result;
	
	public gamehistory(Date starttime, String opponent, String duration, String result){
		this.starttime=starttime;
		this.opponent=opponent;
		this.duration=duration;
		this.result=result;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public String getOpponent() {
		return opponent;
	}

	public void setOpponent(String opponent) {
		this.opponent = opponent;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	

}
