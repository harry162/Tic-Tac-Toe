package ttt.model.dao.jpa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ttt.model.GameWithPlayer;
import ttt.model.SavedSteps;
import ttt.model.GameWithAI;
import ttt.model.Users;
import ttt.model.dao.UserDao;

@Repository
public class UserDaolmpl implements UserDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Users getUsers(String username) {

		/*
		 * List<Users> user=entityManager.createQuery( "from Users", Users.class
		 * ) .getResultList();//getUsers();
		 */
		return entityManager
				.createQuery("from Users where username=:name", Users.class)
				.setParameter("name", username).getSingleResult();

		/*
		 * for(Users u :user) { if(u.getUsername().equalsIgnoreCase(username))
		 * return u; } return null;
		 */

	}

	@Override
	public List<GameWithAI> getGamesWithAI(Users user) {
		int id = user.getId();
		return entityManager.createQuery(
				"from GameWithAI where username=" + id
						+ " and endtime is not null", GameWithAI.class)
				.getResultList();
	}

	public List<SavedSteps> getUserSteps(Users user) {
		return entityManager.createQuery("from SavedSteps", SavedSteps.class)
				.getResultList();

	}

	public List<GameWithAI> getAllGames(Users user) {
		return entityManager.createQuery(
				"from GameWithAI where endtime = null", GameWithAI.class)
				.getResultList();
	}

	@Override
	public boolean checkUser(String uname, String pwd) {
		try {
			Users u = entityManager
					.createQuery(
							"from Users where username = :u and password = :p",
							Users.class).setParameter("u", uname)
					.setParameter("p", pwd).getSingleResult();
			if (u.getUsername() != null) {
				return true;
			} else {
				return false;
			}
		} catch (NoResultException e) {
			return false;
		}
	}

	@Transactional
	@Override
	public boolean regUser(Users u) {

		entityManager.merge(u);
		return true;

	}

	@Override
	public boolean logoutuser(Users u) {

		return true;
	}

	@Override
	public boolean temp() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Transactional
	public GameWithAI saveAIdata(GameWithAI gwa) {
		// TODO Auto-generated method stub
		return entityManager.merge(gwa);
	}

	@Override
	@Transactional
	public GameWithPlayer saveplayerdata(GameWithPlayer gwp) {
		// TODO Auto-generated method stub
		return entityManager.merge(gwp);
	}

	
	@Override
	@Transactional
	public SavedSteps savegame(SavedSteps ss) {
		// TODO Auto-generated method stub
		return entityManager.merge(ss);
	}

	
	@Override
	@PreAuthorize ("principal.username == #u.username")
	public List<GameWithAI> getSavedGames(Users u) {
		int id = u.getId();
		return entityManager
				.createQuery(
						"from GameWithAI where username="
								+ id
								+ " and savetime is not null and gamefinished = false and endtime is null and result is null",
						GameWithAI.class).getResultList();

	}

	@Override
	public List<GameWithAI> getAIGames(Users user) {
		int id = user.getId();
		return entityManager.createQuery(
				"from GameWithAI where username=" + id
						+ " and result IS NOT NULL", GameWithAI.class)
				.getResultList();
	}

	@Override
	public List<GameWithPlayer> getPlayerGames(Users u) {
		int id = u.getId();
		return entityManager.createQuery(
				"from GameWithPlayer where user1st=" + id + " or user2nd=" + id
						+ " ", GameWithPlayer.class).getResultList();
	}

	@Override
	public List<GameWithAI> getWinsAI(Users u) {
		int id = u.getId();
		return entityManager.createQuery(
				"from GameWithAI where username=" + id + " and result = 'win'",
				GameWithAI.class).getResultList();
	}

	@Override
	public List<GameWithPlayer> getWinsplayer(Users u) {
		int id = u.getId();
		return entityManager.createQuery(
				"from GameWithPlayer where winner = " + id + "",
				GameWithPlayer.class).getResultList();
	}

	@Override
	public List<GameWithAI> getMonthaigames(Users u) {
		// TODO Auto-generated method stub
		int id = u.getId();

		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		Date cd = c.getTime();

		return entityManager
				.createQuery(
						"from GameWithAI where username="
								+ id
								+ " and starttime >= '"
								+ cd
								+ "'  and starttime is not null and endtime is not null",
						GameWithAI.class).getResultList();

	}

	@Override
	public List<GameWithPlayer> getMonthplayergames(Users u) {
		// TODO Auto-generated method stub

		int id = u.getId();

		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);

		Date cd = c.getTime();

		return entityManager
				.createQuery(
						"from GameWithPlayer where user1st="
								+ id
								+ " or user2nd="
								+ id
								+ " and starttime >= '"
								+ cd
								+ "' and starttime is not null and endtime is not null",
						GameWithPlayer.class).getResultList();
	}

	@Override
	public String getOpp(int id) {

		Users u = entityManager.find(Users.class, id);
		String s = u.getUsername();
		return s;
	}

	@Override
	public List<GameWithAI> forsave() {
		return entityManager.createQuery(
				"from GameWithAI where savetime is not null", GameWithAI.class)
				.getResultList();
	}

	@Override
	public GameWithAI getgameid(Integer id) {
		return entityManager
				.createQuery("from GameWithAI where id=:idx", GameWithAI.class)
				.setParameter("idx", id).getSingleResult();

	}

	@Override
	public List<SavedSteps> getXlist(Integer id) {
		return entityManager.createQuery(
				"from SavedSteps where gameid=" + id + "and userpos is true",
				SavedSteps.class).getResultList();
	}

	@Override
	public List<SavedSteps> getOlist(Integer id) {
		return entityManager.createQuery(
				"from SavedSteps where gameid=" + id + "and userpos is false",
				SavedSteps.class).getResultList();
	}

	@Transactional
	@Override
	public boolean removegame(Integer id) {

		entityManager.createQuery("DELETE from GameWithAI where id =:id ")
				.setParameter("id", id).executeUpdate();
		return true;
	}

	@Transactional
	@Override
	public boolean removesteps(SavedSteps ss) {

		entityManager.remove(ss);

		return true;
	}

	@Override
	public List<SavedSteps> getStepbyid(GameWithAI id) {

		return entityManager
				.createQuery("from SavedSteps where gameid=:id",
						SavedSteps.class).setParameter("id", id)
				.getResultList();
	}

}