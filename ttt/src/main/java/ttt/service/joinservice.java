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
public class joinservice {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    List<String> joiners;

    Queue<DeferredResult<String>> results;

    public joinservice()
    {
        joiners = new ArrayList<String>();
        results = new LinkedList<DeferredResult<String>>();
    }

    public List<String> getJoiners()
    {
        return joiners;
    }

    public void add( String joiner )
    {
    	joiners.add( joiner );
      
        processDeferredResults();
    }

    public void remove( String joiner )
    {
    	joiners.remove( joiner );
    
        processDeferredResults();
    }

    public void addResult( DeferredResult<String> result )
    {
        results.add( result );
    }

    private void processDeferredResults()
    {
        // convert username list to json
        String json = "";
        try
        {
            StringWriter sw = new StringWriter();
            objectMapper.writeValue( sw, joiners );
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
