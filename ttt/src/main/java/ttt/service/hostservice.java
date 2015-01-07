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
public class hostservice {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    List<String> hosters;

    Queue<DeferredResult<String>> results;

    public hostservice()
    {
    	hosters = new ArrayList<String>();
        results = new LinkedList<DeferredResult<String>>();
    }

    public List<String> getHosters()
    {
        return hosters;
    }

    public void add( String username )
    {
    	hosters.add( username );
      
        processDeferredResults();
    }

    public void remove( String username )
    {
    	hosters.remove( username );
    
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
            objectMapper.writeValue( sw, hosters );
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
