package ttt.web.controller;

import java.security.Principal;
import java.text.DecimalFormat;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import ttt.model.GameWithAI;
import ttt.model.GameWithPlayer;
import ttt.model.SavedSteps;
import ttt.model.Users;
import ttt.model.dao.UserDao;
import ttt.model.gamehistory;
import ttt.service.Oservice;
import ttt.service.Stepservice;
import ttt.service.Xservice;
import ttt.service.gameservice;
import ttt.service.hostservice;
import ttt.service.joinservice;

@Controller
public class UserController {

	@Autowired
	private UserDao userDao;

	@Autowired
	joinservice joins;

	@Autowired
	hostservice hosts;

	@Autowired
	gameservice gs;

	@Autowired
	Stepservice steps;

	@Autowired
	Xservice xs;

	@Autowired
	Oservice os;

	
	@RequestMapping("/join.json")
	public String joinJson(ModelMap models) {

		models.put("joiners", joins.getJoiners());
		return "jsonView";
	}

	@RequestMapping("/host.json")
	public String hostJson(ModelMap models) {
		models.put("hosters", hosts.getHosters());
		return "jsonView";
	}

	@RequestMapping("/step.json")
	public String stepJson(ModelMap models) {
		models.put("steps", steps.getsteps());
		return "jsonView";
	}

	@RequestMapping("/hostgame.html")
	public String hg(HttpSession session, ModelMap model) {
		String uname = (String) session.getAttribute("uname");

		hosts.add(uname);

		String whoami = "player1";
		session.setAttribute("whoami", whoami);

		List<String> joinlist = joins.getJoiners();
		String msg = "no user";
		String nu = msg;

		if (joinlist.size() != 0) {

			List<String> hostlist = hosts.getHosters();

			String player1 = hostlist.get(0);
			String player2 = joinlist.get(0);
			gs.addHoster(player1);
			gs.addJoiner(player2);

			hosts.remove(player1);
			joins.remove(player2);

			gs.addGamer(player1 + "&" + player2);

			return "redirect:/ttt2.html";
		}

		model.put("msg", msg);
		model.put("nu", nu);

		return "hostgame";
	}

	@RequestMapping("/joingame.html")
	public String jg(HttpSession session, ModelMap model) {

		String uname = (String) session.getAttribute("uname");
		joins.add(uname);

		String whoami = "player2";
		session.setAttribute("whoami", whoami);

		List<String> hostlist = hosts.getHosters();
		String msg = "no user";
		String nu = msg;

		if (hostlist.size() != 0) {

			List<String> joinlist = joins.getJoiners();

			String player1 = hostlist.get(0);
			String player2 = joinlist.get(0);

			gs.addHoster(player1);
			gs.addJoiner(player2);

			hosts.remove(player1);
			joins.remove(player2);

			gs.addGamer(player1 + "&" + player2);
			return "redirect:/ttt2.html";
		}

		model.put("msg", msg);
		model.put("nu", nu);
		return "joingame";

	}

	@RequestMapping("/cancelhost.html")
	public String ch(HttpSession session) {

		hosts.remove((String) session.getAttribute("uname"));

		return "welcomepage";
	}

	@RequestMapping("/canceljoin.html")
	public String cj(HttpSession session) {

		joins.remove((String) session.getAttribute("uname"));

		return "welcomepage";
	}

	@RequestMapping("/cancelgame.html")
	public String cg(HttpSession session) {

		gs.removeAll();
		xs.removeAll();
		os.removeAll();
		steps.removeAll();
		return "welcomepage";
	}

	
	@RequestMapping("/join.deferred.json")
	@ResponseBody
	public DeferredResult<String> wosDeferred() {

		DeferredResult<String> result = new DeferredResult<String>();
		joins.addResult(result);
		return result;
	}

	@RequestMapping("/host.deferred.json")
	@ResponseBody
	public DeferredResult<String> hostDeferred() {

		DeferredResult<String> result = new DeferredResult<String>();
		hosts.addResult(result);
		return result;
	}

	@RequestMapping("/step.deferred.json")
	@ResponseBody
	public DeferredResult<String> stepDeferred() {

		DeferredResult<String> result = new DeferredResult<String>();
		steps.addResult(result);
		return result;
	}

	@RequestMapping(value = "/ttt2.html", method = RequestMethod.GET)
	public String ttt2(ModelMap model, HttpSession request) {

		String whoami = (String) request.getAttribute("whoami");
		String uname = (String) request.getAttribute("uname");

		List<String> host = gs.getHosters();
		List<String> join = gs.getJoiners();

		if (!host.contains(uname) && whoami.equals("player1")) {
			hosts.remove(uname);
			return "redirect:/hostgame.html";
		}
		if (!join.contains(uname) && whoami.equals("player2")) {
			joins.remove(uname);
			return "redirect:/joingame.html";
		}

		int hsize = gs.getHosters().size() - 1;
		int jsize = gs.getJoiners().size() - 1;

		String player1 = host.get(hsize);
		String player2 = join.get(jsize);

		String msg="";
		if (whoami.equals("player1")) {
			String turn = "my";
			model.put("turn",turn);
			msg = player2+" has joined the game.Please make your move.";
		}
		if (whoami.equals("player2")) {
			String turn = "your";
			model.put("turn",turn);
			msg = " Joined game hosted by "+player1+". Waiting for "+player1+"'s move";
		}



		List<String> xlist = new ArrayList<String>();
		List<String> olist = new ArrayList<String>();
		
		model.put("xlist",xlist);
		model.put("olist",olist);
		model.put("whoami", whoami);
		model.put("uname", uname);
		model.put("player1", player1);
		model.put("player2", player2);

		request.setAttribute("player1", player1);
		request.setAttribute("player2", player2);
		return "ttt2player";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/ttt2player.html", method = RequestMethod.GET)
	public String ttt2player(ModelMap model, HttpSession request,
			@RequestParam String click) {

		int play=0;
		
		String player1 = (String) request.getAttribute("player1");
		String player2 = (String) request.getAttribute("player2");
		String whoami = (String) request.getAttribute("whoami");
		
		if (click.equals("nothing")) {
			String turn="my";		
			model.put("turn", turn);
			model.put("msg", "Make your move...");
		} else {
			String turn="your";
			steps.add(click);
			model.put("turn", turn);		
			model.put("msg", "Waiting for opponent's move");
			
			if(whoami.equals("player1")){
				xs.add(click);
			}
			if(whoami.equals("player2")){
				os.add(click);
			}
			
		}
		
		Date starttime = (Date) request.getAttribute("starttime");
		if (starttime == null) {
			starttime = Calendar.getInstance().getTime();
			request.setAttribute("starttime", starttime);
		}

		
		
		
		List<String> xlist = xs.getXs();
		List<String> olist = os.getOs();
		String msg="";
		String result = "";
		int flag=0;
		
		if ((xlist.contains("b1") && xlist.contains("b2") && xlist
				.contains("b3"))
				|| (xlist.contains("b4") && xlist.contains("b5") && xlist
						.contains("b6"))
				|| (xlist.contains("b7") && xlist.contains("b8") && xlist
						.contains("b9"))
				|| (xlist.contains("b1") && xlist.contains("b4") && xlist
						.contains("b7"))
				|| (xlist.contains("b2") && xlist.contains("b5") && xlist
						.contains("b8"))
				|| (xlist.contains("b3") && xlist.contains("b6") && xlist
						.contains("b9"))
				|| (xlist.contains("b1") && xlist.contains("b5") && xlist
						.contains("b9"))
				|| (xlist.contains("b3") && xlist.contains("b5") && xlist
						.contains("b7"))) {
			if(whoami.equals("player1")){
			msg = "** Congratulations! You Won ! **";
			result = "win";}
			else{
				msg = " You Lost !!";
				}
			play=1;
			flag = 1;
			
			model.put("msg", msg);

		} else if ((olist.contains("b1") && olist.contains("b2") && olist
				.contains("b3"))
				|| (olist.contains("b4") && olist.contains("b5") && olist
						.contains("b6"))
				|| (olist.contains("b7") && olist.contains("b8") && olist
						.contains("b9"))
				|| (olist.contains("b1") && olist.contains("b4") && olist
						.contains("b7"))
				|| (olist.contains("b2") && olist.contains("b5") && olist
						.contains("b8"))
				|| (olist.contains("b3") && olist.contains("b6") && olist
						.contains("b9"))
				|| (olist.contains("b1") && olist.contains("b5") && olist
						.contains("b9"))
				|| (olist.contains("b3") && olist.contains("b5") && olist
						.contains("b7"))) {
			play=1;
			if(whoami.equals("player2")){
				msg = "** Congratulations! You Won ! **";
				
			}else{
					msg = " You Lost !!";
					result = "lose";
			}
			model.put("msg", msg);

			result = "lose";
			flag = 1;
		} else if ((xlist.contains("b1") || olist.contains("b1"))
				&& (xlist.contains("b2") || olist.contains("b2"))
				&& (xlist.contains("b3") || olist.contains("b3"))
				&& (xlist.contains("b4") || olist.contains("b4"))
				&& (xlist.contains("b5") || olist.contains("b5"))
				&& (xlist.contains("b6") || olist.contains("b6"))
				&& (xlist.contains("b7") || olist.contains("b7"))
				&& (xlist.contains("b8") || olist.contains("b8"))
				&& (xlist.contains("b9") || olist.contains("b9"))) {
			msg = "Game Tied !";
			model.put("msg", msg);
			play=1;
			result = "tie";
			flag = 1;
			
		}
		
		if(flag==1 && whoami.equals("player1")){
			
		

			GameWithPlayer gwp = new GameWithPlayer();
			Users u = userDao.getUsers(player1);
			Users u2 = userDao.getUsers(player2);
			gwp.setUser1st(u);
			gwp.setUser2nd(u2);
			
			starttime = (Date) request.getAttribute("starttime");
			gwp.setStarttime(starttime);

			Date endtime = Calendar.getInstance().getTime();

			gwp.setEndtime(endtime);

			if(result.equals("tie")){
				gwp.setTie(true);
			}else{
				gwp.setTie(false);
			
				if(result.equals("win")){
					gwp.setWinner(u);
					gwp.setLoser(u2);
				}else if(result.equals("lose")){
					gwp.setWinner(u2);
					gwp.setLoser(u);
				}
			}

			userDao.saveplayerdata(gwp);
			request.removeAttribute("starttime");
			
			
		}
		

		List<String> allsteps = steps.getsteps();
		model.put("play",play);
		model.put("allsteps", allsteps);
		model.put("xlist", xs.getXs());
		model.put("olist", os.getOs());
		return "ttt2player";
	}

	@RequestMapping("/users.html")
	public String users(ModelMap models) {
		models.put("users", userDao.getUsers(null));
		return "users";

	}

	@RequestMapping(value="/welcome", method = RequestMethod.GET)
	public String printWelcome(ModelMap model,HttpSession session,Principal principal) {
 
		String name = principal.getName();
	
		session.setAttribute("uname", name);
		return "redirect:/welcomepage.html";
 
	}
 
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
 
		return "login";
 
	}
	
	@RequestMapping(value="/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
 
		model.addAttribute("error", "Invalid Credentials");
		return "login";
 
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
 
		return "login";
 
	}

	@RequestMapping(value = "/register.html", method = RequestMethod.GET)
	public String register(ModelMap model) {
		model.put("Users", new Users());
		return "register";

	}

	@RequestMapping(value = "/register.html", method = RequestMethod.POST)
	public String register(@ModelAttribute("Users") Users u) {

		boolean flag = userDao.regUser(u);

		return "redirect:/login.html";

	}

	@RequestMapping(value = "/welcomepage.html", method = RequestMethod.GET)
	public String welcome(ModelMap model, HttpSession session) {

		String uname = (String) session.getAttribute("uname");
		model.put("uname", uname);
	

		return "welcomepage";

	}

	@RequestMapping(value = "/resumegame.html", method = RequestMethod.GET)
	public String resumegame(ModelMap model, HttpSession session) {

		String uname = (String) session.getAttribute("uname");
		model.put("uname", uname);
	
		Users u = userDao.getUsers(uname);

		List<GameWithAI> savedgames = userDao.getSavedGames(u);

		model.put("savedgames", savedgames);

		return "resumejsp";
	}

	@RequestMapping(value = "/resumegame2.html", method = RequestMethod.GET)
	public String resumegame2(ModelMap model, HttpSession session,
			@RequestParam String idx) {

		String uname = (String) session.getAttribute("uname");
		model.put("uname", uname);
	
		int id = Integer.parseInt(idx);

		GameWithAI ai = userDao.getgameid(id);

		ArrayList<String> xlist = new ArrayList<String>();
		ArrayList<String> olist = new ArrayList<String>();

		Date starttime = ai.getStarttime();

		List<SavedSteps> allx = userDao.getXlist(id);
		List<SavedSteps> allo = userDao.getOlist(id);

		for (int i = 0; i < allx.size(); i++) {
			xlist.add(allx.get(i).getPosition());
		}

		for (int i = 0; i < allo.size(); i++) {
			olist.add(allo.get(i).getPosition());
		}
		String print = "Please make your move:";
		String byDefault = print;
		String checknew = print;

		session.setAttribute("starttime", starttime);
		session.setAttribute("xlist", xlist);

		model.put("xlist", xlist);
		session.setAttribute("olist", olist);
		model.put("olist", olist);
		session.setAttribute("print", print);
		model.put("print", print);
		model.put("byDefault", byDefault);
		model.put("checknew", checknew);
		session.setAttribute("byDefault", byDefault);
		List<SavedSteps> ss = userDao.getStepbyid(ai);

		for (int a = 0; a < ss.size(); a++) {
			userDao.removesteps(ss.get(a));
		}

		boolean removegame = userDao.removegame(id);
		// /////////////////////////////////////////////////////////////////
		return "TicTacToe";
	}

	@RequestMapping(value = "/savegame.html", method = RequestMethod.GET)
	public String savegame(ModelMap model, HttpSession request) {

		// ///////
		String uname = (String) request.getAttribute("uname");
	
		GameWithAI gwa = new GameWithAI();

		Users u = userDao.getUsers(uname);
		gwa.setUsername(u);

		Date starttime = (Date) request.getAttribute("starttime");
		gwa.setStarttime(starttime);

		Date savetime = Calendar.getInstance().getTime();

		gwa.setSavetime(savetime);

		boolean gamefinished = false;
		gwa.setGamefinished(gamefinished);

		userDao.saveAIdata(gwa);
		// ////////////
		List<String> xlist = (List<String>) request.getAttribute("xlist");
		List<String> olist = (List<String>) request.getAttribute("olist");

		List<GameWithAI> forsave = userDao.forsave();
		List<Integer> allid = new ArrayList<Integer>();

		for (int i = 0; i < forsave.size(); i++) {
			allid.add(forsave.get(i).getId());
		}

		Integer id = Collections.max(allid);

		SavedSteps ss = new SavedSteps();
		GameWithAI ai = userDao.getgameid(id);

		ss.setGameid(ai);

		for (int x = 0; x < xlist.size(); x++) {
			String pos = xlist.get(x);
			ss.setPosition(pos);
			boolean userpos = true;
			ss.setUserpos(userpos);

			userDao.savegame(ss);
		}

		for (int o = 0; o < olist.size(); o++) {
			String pos = olist.get(o);
			ss.setPosition(pos);
			boolean userpos = false;
			ss.setUserpos(userpos);

			userDao.savegame(ss);
		}

		request.removeAttribute("starttime");
		return "welcomepage";

	}

	@RequestMapping(value = "/gamehistory.html", method = RequestMethod.GET)
	public String gamehistory(ModelMap model, HttpSession session) {
		String uname = (String) session.getAttribute("uname");
		model.put("uname", uname);
		Users u = userDao.getUsers(uname);

		List<GameWithAI> aigames = userDao.getAIGames(u);
		List<GameWithPlayer> playergames = userDao.getPlayerGames(u);

		double cmpltgames = aigames.size() + playergames.size();
		double oneplayer = aigames.size();
		double twoplayer = playergames.size();

		int cmplt = (int) cmpltgames;
		int one = (int) oneplayer;
		int two = (int) twoplayer;

		List<GameWithAI> aiwingames = userDao.getWinsAI(u);
		List<GameWithPlayer> playerwingames = userDao.getWinsplayer(u);

		model.put("cmpltgames", cmplt);
		model.put("oneplayer", one);
		model.put("twoplayer", two);

		if (oneplayer == 0) {
			oneplayer = 1;
		}

		if (twoplayer == 0) {
			twoplayer = 1;
		}

		double aiwin = (aiwingames.size() * 100) / oneplayer;
		double playerwin = (playerwingames.size() * 100) / twoplayer;

		aiwin = Math.round(aiwin * 100.0) / 100.0;
		playerwin = Math.round(playerwin * 100.0) / 100.0;

		DecimalFormat f = new DecimalFormat("##.##");
		f.format(aiwin);
		f.format(playerwin);

		model.put("aiwin", aiwin);
		model.put("playerwin", playerwin);
		// ////////////////////////////////////////////
		List<GameWithAI> monthaigames = userDao.getMonthaigames(u);
		List<GameWithPlayer> monthplayergames = userDao.getMonthplayergames(u);
		List<gamehistory> gh = new ArrayList<gamehistory>();

		for (int i = 0; i < monthaigames.size(); i++) {
			Date starttime = monthaigames.get(i).getStarttime();
			Date endtime = monthaigames.get(i).getEndtime();

			String opponent = "AI";
			// /////////////////////////////

			long diff = monthaigames.get(i).getEndtime().getTime()
					- monthaigames.get(i).getStarttime().getTime();
			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			int diffInDays = (int) diff / (1000 * 60 * 60 * 24);

			String duration = diffInDays + " Days " + diffHours + " Hours "
					+ diffMinutes + " Minutes " + diffSeconds + " Seconds";
			// ////////////////////////////////
			String result = monthaigames.get(i).getResult();

			gh.add(new gamehistory(starttime, opponent, duration, result));
		}

		for (int i = 0; i < monthplayergames.size(); i++) {
			Date starttime = monthplayergames.get(i).getStarttime();
			Date endtime = monthplayergames.get(i).getEndtime();

			String opponent = "";
			String result = "";
			if (monthplayergames.get(i).getUser1st().getUsername()
					.equals(uname)) {
				opponent = userDao.getOpp(monthplayergames.get(i).getUser2nd()
						.getId());

				if (monthplayergames.get(i).getWinner() == null) {
					result = "tie";
				} else {
					if (monthplayergames.get(i).getWinner().getId() == monthplayergames
							.get(i).getUser1st().getId()) {
						result = "win";
					} else {
						result = "lose";
					}
				}
			} else if (monthplayergames.get(i).getUser2nd().getUsername()
					.equals(uname)) {
				opponent = userDao.getOpp(monthplayergames.get(i).getUser1st()
						.getId());

				if (monthplayergames.get(i).getWinner() == null) {
					result = "tie";
				} else {
					if (monthplayergames.get(i).getWinner().getId() == monthplayergames
							.get(i).getUser2nd().getId()) {
						result = "win";
					} else {
						result = "lose";
					}
				}
			}
			// /////////////////////////////

			long diff = monthplayergames.get(i).getEndtime().getTime()
					- monthplayergames.get(i).getStarttime().getTime();
			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			int diffInDays = (int) diff / (1000 * 60 * 60 * 24);

			String duration = diffInDays + " Days " + diffHours + " Hours "
					+ diffMinutes + " Minutes " + diffSeconds + " Seconds";
			// ////////////////////////////////

			gh.add(new gamehistory(starttime, opponent, duration, result));

		}

		Collections.sort(gh, new Comparator<gamehistory>() {
			public int compare(gamehistory starttime, gamehistory sestarttime) {
				return starttime.getStarttime().compareTo(
						sestarttime.getStarttime());
			}
		});

		model.put("gh", gh);
		return "gamehistory";

	}

	@RequestMapping(value = "/ttt.html", method = RequestMethod.GET)
	public String ttt(ModelMap model, HttpSession request) {
		String uname = (String) request.getAttribute("uname");
		model.put("uname", uname);
	
		// ///////////////////////////////////////////////////////////////////

		ArrayList<String> xlist = new ArrayList<String>();
		ArrayList<String> olist = new ArrayList<String>();

		xlist.clear();
		olist.clear();

		String print = "Please make your move:";
		String byDefault = print;
		String checknew = "";

		request.setAttribute("xlist", xlist);
		model.put("xlist", xlist);
		request.setAttribute("olist", olist);
		model.put("olist", olist);
		request.setAttribute("print", print);
		model.put("print", print);
		model.put("byDefault", byDefault);
		model.put("checknew", checknew);
		request.setAttribute("byDefault", byDefault);

		// /////////////////////////////////////////////////////////////////
		return "TicTacToe";

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/NextStep.html", method = RequestMethod.GET)
	public String ns(ModelMap model, HttpSession request,
			@RequestParam String click) {

		String uname = (String) request.getAttribute("uname");
		model.put("uname", uname);
	
		Date starttime = (Date) request.getAttribute("starttime");
		if (starttime == null) {
			starttime = Calendar.getInstance().getTime();
			request.setAttribute("starttime", starttime);
		}

		List<String> xlist = (List<String>) request.getAttribute("xlist");
		List<String> olist = (List<String>) request.getAttribute("olist");

		String print = "Please make your move:";
		String byDefault = "Please make your move:";
		String checknew = "Please make your move:";
		/*-------------------Putting X in the Game--------------- */
		// String click = request.getParameter("click");

		xlist.add(click);

		/*----------------putting O in the game-------------------*/
		if (olist.contains("b1") && olist.contains("b2")
				&& !xlist.contains("b3")) {
			olist.add("b3");

		} else if (olist.contains("b1") && olist.contains("b3")
				&& !xlist.contains("b2")) {
			olist.add("b2");

		} else if (olist.contains("b2") && olist.contains("b3")
				&& !xlist.contains("b1")) {
			olist.add("b1");

		}
		// ///////////////////////////////////////
		else if (olist.contains("b4") && olist.contains("b5")
				&& !xlist.contains("b6")) {
			olist.add("b6");

		} else if (olist.contains("b4") && olist.contains("b6")
				&& !xlist.contains("b5")) {
			olist.add("b5");

		} else if (olist.contains("b5") && olist.contains("b6")
				&& !xlist.contains("b4")) {
			olist.add("b4");
		}
		// ///////////////////////////////////////
		else if (olist.contains("b7") && olist.contains("b8")
				&& !xlist.contains("b9")) {
			olist.add("b9");
		} else if (olist.contains("b7") && olist.contains("b9")
				&& !xlist.contains("b8")) {
			olist.add("b8");
		} else if (olist.contains("b8") && olist.contains("b9")
				&& !xlist.contains("b7")) {
			olist.add("b7");
		}

		/*-----*/

		else if (olist.contains("b1") && olist.contains("b4")
				&& !xlist.contains("b7")) {
			olist.add("b7");
		} else if (olist.contains("b1") && olist.contains("b7")
				&& !xlist.contains("b4")) {
			olist.add("b4");
		} else if (olist.contains("b4") && olist.contains("b7")
				&& !xlist.contains("b1")) {
			olist.add("b1");
		}
		// ///////////////////////////////////////
		else if (olist.contains("b2") && olist.contains("b5")
				&& !xlist.contains("b8")) {
			olist.add("b8");
		} else if (olist.contains("b2") && olist.contains("b8")
				&& !xlist.contains("b5")) {
			olist.add("b5");
		} else if (olist.contains("b5") && olist.contains("b8")
				&& !xlist.contains("b2")) {
			olist.add("b2");
		}
		// //////////////////////////////////////
		else if (olist.contains("b3") && olist.contains("b6")
				&& !xlist.contains("b9")) {
			olist.add("b9");
		} else if (olist.contains("b3") && olist.contains("b9")
				&& !xlist.contains("b6")) {
			olist.add("b6");
		} else if (olist.contains("b6") && olist.contains("b9")
				&& !xlist.contains("b3")) {
			olist.add("b3");
		}

		/*-----*/

		else if (olist.contains("b1") && olist.contains("b5")
				&& !xlist.contains("b9")) {
			olist.add("b9");
		} else if (olist.contains("b1") && olist.contains("b9")
				&& !xlist.contains("b5")) {
			olist.add("b5");
		} else if (olist.contains("b5") && olist.contains("b9")
				&& !xlist.contains("b1")) {
			olist.add("b1");
		}
		// ///////////////////////////////////////
		else if (olist.contains("b3") && olist.contains("b5")
				&& !xlist.contains("b7")) {
			olist.add("b7");
		} else if (olist.contains("b3") && olist.contains("b7")
				&& !xlist.contains("b5")) {
			olist.add("b5");
		} else if (olist.contains("b5") && olist.contains("b7")
				&& !xlist.contains("b3")) {
			olist.add("b3");
		}
		/*----*/// ////////
		else if (xlist.contains("b1") && xlist.contains("b2")
				&& !olist.contains("b3")) {
			olist.add("b3");
		} else if (xlist.contains("b1") && xlist.contains("b3")
				&& !olist.contains("b2")) {
			olist.add("b2");
		} else if (xlist.contains("b2") && xlist.contains("b3")
				&& !olist.contains("b1")) {
			olist.add("b1");
		}
		// ///////////////////////////////////////
		else if (xlist.contains("b4") && xlist.contains("b5")
				&& !olist.contains("b6")) {
			olist.add("b6");
		} else if (xlist.contains("b4") && xlist.contains("b6")
				&& !olist.contains("b5")) {
			olist.add("b5");
		} else if (xlist.contains("b5") && xlist.contains("b6")
				&& !olist.contains("b4")) {
			olist.add("b4");
		}
		// ///////////////////////////////////////
		else if (xlist.contains("b7") && xlist.contains("b8")
				&& !olist.contains("b9")) {
			olist.add("b9");
		} else if (xlist.contains("b7") && xlist.contains("b9")
				&& !olist.contains("b8")) {
			olist.add("b8");
		} else if (xlist.contains("b8") && xlist.contains("b9")
				&& !olist.contains("b7")) {
			olist.add("b7");
		}

		/*-----*/

		else if (xlist.contains("b1") && xlist.contains("b4")
				&& !olist.contains("b7")) {
			olist.add("b7");
		} else if (xlist.contains("b1") && xlist.contains("b7")
				&& !olist.contains("b4")) {
			olist.add("b4");
		} else if (xlist.contains("b4") && xlist.contains("b7")
				&& !olist.contains("b1")) {
			olist.add("b1");
		}
		// ///////////////////////////////////////
		else if (xlist.contains("b2") && xlist.contains("b5")
				&& !olist.contains("b8")) {
			olist.add("b8");
		} else if (xlist.contains("b2") && xlist.contains("b8")
				&& !olist.contains("b5")) {
			olist.add("b5");
		} else if (xlist.contains("b5") && xlist.contains("b8")
				&& !olist.contains("b2")) {
			olist.add("b2");
		}
		// ///////////////////////////////////////
		else if (xlist.contains("b3") && xlist.contains("b6")
				&& !olist.contains("b9")) {
			olist.add("b9");
		} else if (xlist.contains("b3") && xlist.contains("b9")
				&& !olist.contains("b6")) {
			olist.add("b6");
		} else if (xlist.contains("b6") && xlist.contains("b9")
				&& !olist.contains("b3")) {
			olist.add("b3");
		}

		/*-----*/

		else if (xlist.contains("b1") && xlist.contains("b5")
				&& !olist.contains("b9")) {
			olist.add("b9");
		} else if (xlist.contains("b1") && xlist.contains("b9")
				&& !olist.contains("b5")) {
			olist.add("b5");
		} else if (xlist.contains("b5") && xlist.contains("b9")
				&& !olist.contains("b1")) {
			olist.add("b1");
		}
		// ///////////////////////////////////////
		else if (xlist.contains("b3") && xlist.contains("b5")
				&& !olist.contains("b7")) {
			olist.add("b7");
		} else if (xlist.contains("b3") && xlist.contains("b7")
				&& !olist.contains("b5")) {
			olist.add("b5");
		} else if (xlist.contains("b5") && xlist.contains("b7")
				&& !olist.contains("b3")) {
			olist.add("b3");
		}
		/*------ else logic--------*/
		else {
			String num = "";
			int flag = 0;

			/*
			 * for(int i = 0; i<1000; i++){ Random r = new Random(); int
			 * randomNum = r.nextInt(8)+1; num = "b" +
			 * Integer.toString(randomNum); System.out.println("Wrong " + num);
			 * if (!xlist.contains(num) && !olist.contains(num)) {
			 * System.out.println(num); olist.add(num); break; } }
			 */
			while (flag == 0) {
				if (olist.size() + xlist.size() == 9) {
					break;
				}

				Random r = new Random();
				int randomNum = r.nextInt(8) + 1;
				num = "b" + Integer.toString(randomNum);
				if (!xlist.contains(num) && !olist.contains(num)) {
					olist.add(num);
					flag = 1;
				}
			}

		}

		/*-------------------------------------------------------*/

		/*----------------Check Win or Loose---------*/
		String result = "";
		int flag = 0;
		if ((xlist.contains("b1") && xlist.contains("b2") && xlist
				.contains("b3"))
				|| (xlist.contains("b4") && xlist.contains("b5") && xlist
						.contains("b6"))
				|| (xlist.contains("b7") && xlist.contains("b8") && xlist
						.contains("b9"))
				|| (xlist.contains("b1") && xlist.contains("b4") && xlist
						.contains("b7"))
				|| (xlist.contains("b2") && xlist.contains("b5") && xlist
						.contains("b8"))
				|| (xlist.contains("b3") && xlist.contains("b6") && xlist
						.contains("b9"))
				|| (xlist.contains("b1") && xlist.contains("b5") && xlist
						.contains("b9"))
				|| (xlist.contains("b3") && xlist.contains("b5") && xlist
						.contains("b7"))) {
			print = "** Congratulations! Player Won ! **";
			result = "win";
			flag = 1;
			int del = olist.size() - 1;
			olist.remove(del);

		} else if ((olist.contains("b1") && olist.contains("b2") && olist
				.contains("b3"))
				|| (olist.contains("b4") && olist.contains("b5") && olist
						.contains("b6"))
				|| (olist.contains("b7") && olist.contains("b8") && olist
						.contains("b9"))
				|| (olist.contains("b1") && olist.contains("b4") && olist
						.contains("b7"))
				|| (olist.contains("b2") && olist.contains("b5") && olist
						.contains("b8"))
				|| (olist.contains("b3") && olist.contains("b6") && olist
						.contains("b9"))
				|| (olist.contains("b1") && olist.contains("b5") && olist
						.contains("b9"))
				|| (olist.contains("b3") && olist.contains("b5") && olist
						.contains("b7"))) {
			print = "-- Computer Won ! --";
			result = "lose";
			flag = 1;
		} else if ((xlist.contains("b1") || olist.contains("b1"))
				&& (xlist.contains("b2") || olist.contains("b2"))
				&& (xlist.contains("b3") || olist.contains("b3"))
				&& (xlist.contains("b4") || olist.contains("b4"))
				&& (xlist.contains("b5") || olist.contains("b5"))
				&& (xlist.contains("b6") || olist.contains("b6"))
				&& (xlist.contains("b7") || olist.contains("b7"))
				&& (xlist.contains("b8") || olist.contains("b8"))
				&& (xlist.contains("b9") || olist.contains("b9"))) {
			print = "Game Tied !";
			result = "tie";
			flag = 1;
		}
		/*--------------------------------------------*/
		if (flag == 1) {
			uname = (String) request.getAttribute("uname");

			GameWithAI gwa = new GameWithAI();
			Users u = userDao.getUsers(uname);
			gwa.setUsername(u);

			starttime = (Date) request.getAttribute("starttime");
			gwa.setStarttime(starttime);

			Date endtime = Calendar.getInstance().getTime();

			gwa.setEndtime(endtime);

			boolean gamefinished = true;
			gwa.setGamefinished(gamefinished);

			gwa.setResult(result);

			userDao.saveAIdata(gwa);
			request.removeAttribute("starttime");

		}
		/*--------------------------------------------*/
		request.setAttribute("xlist", xlist);
		model.put("xlist", xlist);
		request.setAttribute("olist", olist);
		model.put("olist", olist);
		request.setAttribute("print", print);
		request.setAttribute("byDefault", byDefault);
		model.put("print", print);
		model.put("byDefault", byDefault);
		model.put("checknew", checknew);

		return "TicTacToe";

	}

	@RequestMapping(value = "/loORnew.html", method = RequestMethod.GET)
	public String loORnew(ModelMap model, HttpSession session,
			@RequestParam String click) {

		String uname = (String) session.getAttribute("uname");

		model.put("uname", uname);
	
		GameWithAI gwa = new GameWithAI();
		Users u = userDao.getUsers(uname);
		gwa.setUsername(u);

		Date starttime = (Date) session.getAttribute("starttime");
		gwa.setStarttime(starttime);

		Date endtime = Calendar.getInstance().getTime();

		gwa.setEndtime(endtime);

		boolean gamefinished = false;
		gwa.setGamefinished(gamefinished);

		String result = "lose";
		gwa.setResult(result);

		userDao.saveAIdata(gwa);

		if (click.equals("logout")) {
			session.invalidate();
			return "redirect:/login.html";

		} else if (click.equals("new")) {
			session.removeAttribute("starttime");
			return "redirect:/ttt.html";
		} else if (click.equals("welcome")) {
			session.removeAttribute("starttime");
			return "redirect:/welcomepage.html";
		}

		return null;
	}
	
	@RequestMapping(value = "/guestTTT.html", method = RequestMethod.GET)
	public String guestTTT(ModelMap model, HttpSession request) {

		ArrayList<String> xlist = new ArrayList<String>();
		ArrayList<String> olist = new ArrayList<String>();

		xlist.clear();
		olist.clear();

		String print = "Please make your move:";
		String byDefault = print;
		request.setAttribute("xlist", xlist);
		model.put("xlist", xlist);
		request.setAttribute("olist", olist);
		model.put("olist", olist);
		model.put("print", print);
		request.setAttribute("byDefault", byDefault);
		model.put("byDefault", byDefault);

		return "guestTTT";
	}

	@RequestMapping(value = "/newgame.html", method = RequestMethod.GET)
	public String guestAI(ModelMap model, HttpSession request) {
		
		request.invalidate();
		return "redirect:/guestTTT.html";
	
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/guestAI.html", method = RequestMethod.GET)
	public String guestAI(ModelMap model, HttpSession request,
			@RequestParam String click) {

		List<String> xlist = (List<String>) request.getAttribute("xlist");
		List<String> olist = (List<String>) request.getAttribute("olist");

		String print = "Please make your move:";
		String byDefault = "Please make your move:";
		/*-------------------Putting X in the Game--------------- */

		xlist.add(click);

		/*----------------putting O in the game-------------------*/
		if (olist.contains("b1") && olist.contains("b2")
				&& !xlist.contains("b3")) {
			olist.add("b3");
			System.out.println("if-1");
		} else if (olist.contains("b1") && olist.contains("b3")
				&& !xlist.contains("b2")) {
			olist.add("b2");
			System.out.println("if-2");
		} else if (olist.contains("b2") && olist.contains("b3")
				&& !xlist.contains("b1")) {
			olist.add("b1");
			System.out.println("if-3");
		}
		// ///////////////////////////////////////
		else if (olist.contains("b4") && olist.contains("b5")
				&& !xlist.contains("b6")) {
			olist.add("b6");
			System.out.println("if-4");
		} else if (olist.contains("b4") && olist.contains("b6")
				&& !xlist.contains("b5")) {
			olist.add("b5");
			System.out.println("if-5");
		} else if (olist.contains("b5") && olist.contains("b6")
				&& !xlist.contains("b4")) {
			olist.add("b4");
			System.out.println("if-6");
		}
		// ///////////////////////////////////////
		else if (olist.contains("b7") && olist.contains("b8")
				&& !xlist.contains("b9")) {
			olist.add("b9");
			System.out.println("if-7");
		} else if (olist.contains("b7") && olist.contains("b9")
				&& !xlist.contains("b8")) {
			olist.add("b8");
			System.out.println("if-8");
		} else if (olist.contains("b8") && olist.contains("b9")
				&& !xlist.contains("b7")) {
			olist.add("b7");
			System.out.println("if-9");
		}

		/*-----*/

		else if (olist.contains("b1") && olist.contains("b4")
				&& !xlist.contains("b7")) {
			olist.add("b7");
			System.out.println("if-10");
		} else if (olist.contains("b1") && olist.contains("b7")
				&& !xlist.contains("b4")) {
			olist.add("b4");
			System.out.println("if-11");
		} else if (olist.contains("b4") && olist.contains("b7")
				&& !xlist.contains("b1")) {
			olist.add("b1");
			System.out.println("if-12");
		}
		// ///////////////////////////////////////
		else if (olist.contains("b2") && olist.contains("b5")
				&& !xlist.contains("b8")) {
			olist.add("b8");
			System.out.println("if-13");
		} else if (olist.contains("b2") && olist.contains("b8")
				&& !xlist.contains("b5")) {
			olist.add("b5");
			System.out.println("if-14");
		} else if (olist.contains("b5") && olist.contains("b8")
				&& !xlist.contains("b2")) {
			olist.add("b2");
			System.out.println("if-15");
		}
		// //////////////////////////////////////
		else if (olist.contains("b3") && olist.contains("b6")
				&& !xlist.contains("b9")) {
			olist.add("b9");
			System.out.println("if-16");
		} else if (olist.contains("b3") && olist.contains("b9")
				&& !xlist.contains("b6")) {
			olist.add("b6");
			System.out.println("if-17");
		} else if (olist.contains("b6") && olist.contains("b9")
				&& !xlist.contains("b3")) {
			olist.add("b3");
			System.out.println("if-18");
		}

		/*-----*/

		else if (olist.contains("b1") && olist.contains("b5")
				&& !xlist.contains("b9")) {
			olist.add("b9");
			System.out.println("if-19");
		} else if (olist.contains("b1") && olist.contains("b9")
				&& !xlist.contains("b5")) {
			olist.add("b5");
			System.out.println("if-20");
		} else if (olist.contains("b5") && olist.contains("b9")
				&& !xlist.contains("b1")) {
			olist.add("b1");
			System.out.println("if-21");
		}
		// ///////////////////////////////////////
		else if (olist.contains("b3") && olist.contains("b5")
				&& !xlist.contains("b7")) {
			olist.add("b7");
			System.out.println("if-22");
		} else if (olist.contains("b3") && olist.contains("b7")
				&& !xlist.contains("b5")) {
			olist.add("b5");
			System.out.println("if-23");
		} else if (olist.contains("b5") && olist.contains("b7")
				&& !xlist.contains("b3")) {
			olist.add("b3");
			System.out.println("if-24");
		}
		/*----*/// ////////
		else if (xlist.contains("b1") && xlist.contains("b2")
				&& !olist.contains("b3")) {
			olist.add("b3");
			System.out.println("if-25");
		} else if (xlist.contains("b1") && xlist.contains("b3")
				&& !olist.contains("b2")) {
			olist.add("b2");
			System.out.println("if-26");
		} else if (xlist.contains("b2") && xlist.contains("b3")
				&& !olist.contains("b1")) {
			olist.add("b1");
			System.out.println("if-27");
		}
		// ///////////////////////////////////////
		else if (xlist.contains("b4") && xlist.contains("b5")
				&& !olist.contains("b6")) {
			olist.add("b6");
			System.out.println("if-28");
		} else if (xlist.contains("b4") && xlist.contains("b6")
				&& !olist.contains("b5")) {
			olist.add("b5");
			System.out.println("if-29");
		} else if (xlist.contains("b5") && xlist.contains("b6")
				&& !olist.contains("b4")) {
			olist.add("b4");
			System.out.println("if-30");
		}
		// ///////////////////////////////////////
		else if (xlist.contains("b7") && xlist.contains("b8")
				&& !olist.contains("b9")) {
			olist.add("b9");
			System.out.println("if-31");
		} else if (xlist.contains("b7") && xlist.contains("b9")
				&& !olist.contains("b8")) {
			olist.add("b8");
			System.out.println("if-32");
		} else if (xlist.contains("b8") && xlist.contains("b9")
				&& !olist.contains("b7")) {
			olist.add("b7");
			System.out.println("if-33");
		}

		/*-----*/

		else if (xlist.contains("b1") && xlist.contains("b4")
				&& !olist.contains("b7")) {
			olist.add("b7");
			System.out.println("if-34");
		} else if (xlist.contains("b1") && xlist.contains("b7")
				&& !olist.contains("b4")) {
			olist.add("b4");
			System.out.println("if-35");
		} else if (xlist.contains("b4") && xlist.contains("b7")
				&& !olist.contains("b1")) {
			olist.add("b1");
			System.out.println("if-36");
		}
		// ///////////////////////////////////////
		else if (xlist.contains("b2") && xlist.contains("b5")
				&& !olist.contains("b8")) {
			olist.add("b8");
			System.out.println("if-37");
		} else if (xlist.contains("b2") && xlist.contains("b8")
				&& !olist.contains("b5")) {
			olist.add("b5");
			System.out.println("if-38");
		} else if (xlist.contains("b5") && xlist.contains("b8")
				&& !olist.contains("b2")) {
			olist.add("b2");
			System.out.println("if-39");
		}
		// ///////////////////////////////////////
		else if (xlist.contains("b3") && xlist.contains("b6")
				&& !olist.contains("b9")) {
			olist.add("b9");
			System.out.println("if-40");
		} else if (xlist.contains("b3") && xlist.contains("b9")
				&& !olist.contains("b6")) {
			olist.add("b6");
			System.out.println("if-41");
		} else if (xlist.contains("b6") && xlist.contains("b9")
				&& !olist.contains("b3")) {
			olist.add("b3");
			System.out.println("if-42");
		}

		/*-----*/

		else if (xlist.contains("b1") && xlist.contains("b5")
				&& !olist.contains("b9")) {
			olist.add("b9");
			System.out.println("if-43");
		} else if (xlist.contains("b1") && xlist.contains("b9")
				&& !olist.contains("b5")) {
			olist.add("b5");
			System.out.println("if-44");
		} else if (xlist.contains("b5") && xlist.contains("b9")
				&& !olist.contains("b1")) {
			olist.add("b1");
			System.out.println("if-45");
		}
		// ///////////////////////////////////////
		else if (xlist.contains("b3") && xlist.contains("b5")
				&& !olist.contains("b7")) {
			olist.add("b7");
			System.out.println("if-46");
		} else if (xlist.contains("b3") && xlist.contains("b7")
				&& !olist.contains("b5")) {
			olist.add("b5");
			System.out.println("if-47");
		} else if (xlist.contains("b5") && xlist.contains("b7")
				&& !olist.contains("b3")) {
			olist.add("b3");
			System.out.println("if-48");
		}
		/*------ else logic--------*/
		else {
			String num = "";
			int flag = 0;

			/*
			 * for(int i = 0; i<1000; i++){ Random r = new Random(); int
			 * randomNum = r.nextInt(8)+1; num = "b" +
			 * Integer.toString(randomNum); System.out.println("Wrong " + num);
			 * if (!xlist.contains(num) && !olist.contains(num)) {
			 * System.out.println(num); olist.add(num); break; } }
			 */
			while (flag == 0) {
				if (olist.size() + xlist.size() == 9) {
					break;
				}

				Random r = new Random();
				int randomNum = r.nextInt(8) + 1;
				num = "b" + Integer.toString(randomNum);
				System.out.println("Wrong " + num);
				if (!xlist.contains(num) && !olist.contains(num)) {
					System.out.println(num);
					olist.add(num);
					flag = 1;
				}
			}

		}

		/*-------------------------------------------------------*/

		/*----------------Check Win or Loose---------*/
		if ((xlist.contains("b1") && xlist.contains("b2") && xlist
				.contains("b3"))
				|| (xlist.contains("b4") && xlist.contains("b5") && xlist
						.contains("b6"))
				|| (xlist.contains("b7") && xlist.contains("b8") && xlist
						.contains("b9"))
				|| (xlist.contains("b1") && xlist.contains("b4") && xlist
						.contains("b7"))
				|| (xlist.contains("b2") && xlist.contains("b5") && xlist
						.contains("b8"))
				|| (xlist.contains("b3") && xlist.contains("b6") && xlist
						.contains("b9"))
				|| (xlist.contains("b1") && xlist.contains("b5") && xlist
						.contains("b9"))
				|| (xlist.contains("b3") && xlist.contains("b5") && xlist
						.contains("b7"))) {
			print = "** Congratulations! Player Won ! **";

			int del = olist.size() - 1;
			olist.remove(del);
		} else if ((olist.contains("b1") && olist.contains("b2") && olist
				.contains("b3"))
				|| (olist.contains("b4") && olist.contains("b5") && olist
						.contains("b6"))
				|| (olist.contains("b7") && olist.contains("b8") && olist
						.contains("b9"))
				|| (olist.contains("b1") && olist.contains("b4") && olist
						.contains("b7"))
				|| (olist.contains("b2") && olist.contains("b5") && olist
						.contains("b8"))
				|| (olist.contains("b3") && olist.contains("b6") && olist
						.contains("b9"))
				|| (olist.contains("b1") && olist.contains("b5") && olist
						.contains("b9"))
				|| (olist.contains("b3") && olist.contains("b5") && olist
						.contains("b7"))) {
			print = "-- Computer Won ! --";
		} else if ((xlist.contains("b1") || olist.contains("b1"))
				&& (xlist.contains("b2") || olist.contains("b2"))
				&& (xlist.contains("b3") || olist.contains("b3"))
				&& (xlist.contains("b4") || olist.contains("b4"))
				&& (xlist.contains("b5") || olist.contains("b5"))
				&& (xlist.contains("b6") || olist.contains("b6"))
				&& (xlist.contains("b7") || olist.contains("b7"))
				&& (xlist.contains("b8") || olist.contains("b8"))
				&& (xlist.contains("b9") || olist.contains("b9"))) {
			print = "Game Tied !";
		}
		/*--------------------------------------------*/

		request.setAttribute("xlist", xlist);
		model.put("xlist", xlist);
		request.setAttribute("olist", olist);
		model.put("olist", olist);
		request.setAttribute("print", print);
		request.setAttribute("byDefault", byDefault);

		return "guestTTT";
	}


}