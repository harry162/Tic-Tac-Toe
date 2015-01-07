package ttt.model.dao;

import java.util.Date;
import java.util.List;








import ttt.model.GameWithAI;
import ttt.model.GameWithPlayer;
import ttt.model.SavedSteps;
import ttt.model.Users;


public interface UserDao {

    Users getUsers( String username );
   // Users getUsers( int id );
    
   // List<Users> getUsers();
  
     List<GameWithAI> getGamesWithAI(Users user);
     List<SavedSteps> getUserSteps(Users user);
     List<GameWithAI> getAllGames(Users user);
     List<GameWithAI> getAIGames(Users user);
     boolean checkUser(String uname, String pwd);
     boolean logoutuser(Users u);
	 boolean regUser(Users u);
     boolean temp();
     GameWithAI saveAIdata(GameWithAI gwa);
     List<GameWithAI> getWinsAI(Users u);
     List<GameWithPlayer> getWinsplayer(Users u);
	List<GameWithPlayer> getPlayerGames(Users u);

	List<GameWithAI> getMonthaigames(Users u);

	List<GameWithPlayer> getMonthplayergames(Users u);
	String getOpp(int id);

	List<GameWithAI> forsave();

	GameWithAI getgameid(Integer id);

	SavedSteps savegame(SavedSteps ss);

	List<GameWithAI> getSavedGames(Users u);


	List<SavedSteps> getXlist(Integer id);
	List<SavedSteps> getOlist(Integer id);

	boolean removegame(Integer id);

	boolean removesteps(SavedSteps ss);

	List<SavedSteps> getStepbyid(GameWithAI ai);

	GameWithPlayer saveplayerdata(GameWithPlayer gwp);
}