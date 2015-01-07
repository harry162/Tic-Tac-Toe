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
public class Stepservice {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    List<String> steps;

    Queue<DeferredResult<String>> results;

    public Stepservice()
    {
        steps = new ArrayList<String>();
        results = new LinkedList<DeferredResult<String>>();
    }

    public List<String> getsteps()
    {
        return steps;
    }

    public void add( String joiner )
    {
    	steps.add( joiner );
      
        processDeferredResults();
    }

    public void remove( String joiner )
    {
    	steps.remove( joiner );
    
        processDeferredResults();
    }

    public void addResult( DeferredResult<String> result )
    {
        results.add( result );
    }
    public void removeAll()
    {

    	steps.clear();
    }
    private void processDeferredResults()
    {

    	String json = "";
        try
        {
            StringWriter sw = new StringWriter();
            objectMapper.writeValue( sw, steps );
            json = sw.toString();
        }
        catch( Exception e )
        {
           
        }

        // process queued results
        for( DeferredResult<String> result : results )
        {
            result.setResult( json );
        }
        
        results.clear();
    }

}
