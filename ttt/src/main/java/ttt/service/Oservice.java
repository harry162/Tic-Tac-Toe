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
public class Oservice {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    List<String> os;


    public Oservice()
    {
        os = new ArrayList<String>();
    }

    public List<String> getOs()
    {
        return os;
    }

    public void add( String joiner )
    {
    	os.add( joiner );
      
    }

    public void remove( String joiner )
    {
    	os.remove( joiner );
    
    }
    
    public void removeAll()
    {

    	os.clear();

    }



}
