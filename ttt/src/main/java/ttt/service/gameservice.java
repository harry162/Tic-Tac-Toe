package ttt.service;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class gameservice {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	List<String> hosters;
	List<String> joiners;
	List<String> gamers;

	Queue<DeferredResult<String>> results;

	public gameservice() {
		hosters = new ArrayList<String>();
		joiners = new ArrayList<String>();
		gamers = new ArrayList<String>();
		results = new LinkedList<DeferredResult<String>>();
	}

	public List<String> getHosters() {
		return hosters;
	}

	public List<String> getJoiners() {
		return joiners;
	}

	public List<String> getGamers() {
		return gamers;
	}

	public void addHoster(String username) {
		hosters.add(username);

		processDeferredResults();
	}

	public void addJoiner(String username) {
		joiners.add(username);

		processDeferredResults();
	}

	public void addGamer(String username) {
		gamers.add(username);

		processDeferredResults();
	}

	public void removeHoster(String username) {
		hosters.remove(username);

		processDeferredResults();
	}

	public void removeJoiner(String username) {
		joiners.remove(username);

		processDeferredResults();
	}

	public void removeGamer(String username) {
		gamers.remove(username);

		processDeferredResults();
	}

	public void removeAll() {
		
		gamers.clear();

		joiners.clear();

		hosters.clear();

		processDeferredResults();
	}

	public void addResult(DeferredResult<String> result) {
		results.add(result);
	}

	private void processDeferredResults() {
		// convert username list to json
		String json = "";
		try {
			StringWriter sw = new StringWriter();
			objectMapper.writeValue(sw, hosters);
			objectMapper.writeValue(sw, joiners);
			objectMapper.writeValue(sw, gamers);
			json = sw.toString();
		} catch (Exception e) {

		}

		// process queued results
		for (DeferredResult<String> result : results) {
			result.setResult(json);
		}

		results.clear();
	}

}
