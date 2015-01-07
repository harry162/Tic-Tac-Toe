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
public class Xservice {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    List<String> xs;


    public Xservice()
    {
        xs = new ArrayList<String>();
    }

    public List<String> getXs()
    {
        return xs;
    }

    public void add( String joiner )
    {
    	xs.add( joiner );
      
    }

    public void remove( String joiner )
    {
    	xs.remove( joiner );
    
    }


    public void removeAll()
    {
    	xs.clear();
    		
    }
}
