
package filterSeq;

import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;

/**
 * @author Alok Dhamanaskar (alokd@uga.edu)
 * @see LICENSE (MIT style license file). 
 * 
 * <br/><br/> A class the represents FilterSequences web service.
 *
 */
@WebService(
        name            = "filterSequences", 
        targetNamespace = "http://wsannotations.ctegd.uga.edu/services/",
        serviceName     = "filterSequences",
        portName        = "FilterSequencesPort",
        wsdlLocation    = "filterSequences.wsdl"
        )
public class FilterSequencesWS {

    /**
     * Web service operation, That filters sequences passed to it as CSV based on cutoff values for score and expectation Value
     */
    @WebMethod(operationName = "filterByEvalScoreCSV")
    @WebResult(name="FilteredSequences")
    public String filterByEvalScoreCSV(
            @WebParam(name = "evaluesCSV"   ) String evalue , 
            @WebParam(name = "evalueCutoff" ) Double evalCutoff, 
            @WebParam(name = "scoresCSV"    ) String score , 
            @WebParam(name = "scoreCutoff"  ) Double scoreCutoff,             
            @WebParam(name = "sequences"    ) String sequences
            )
            throws ImproperInputsFault 
            
    { 
        if (evalue == null || evalCutoff == null || sequences == null)
            throw new ImproperInputsFault("Either of evalue, evalueCutoff and sequences cannot be null..!",new Fault("ImproperInputs"));
        else if (evalue.equals("") || evalCutoff.doubleValue() <= 0 || sequences.equals(""))
            throw new ImproperInputsFault("Either of evalue, evalueCutoff and sequences cannot be empty or null..!",new Fault("ImproperInputs"));
        else
        {
            if(scoreCutoff == null) 
                scoreCutoff = 0.00;
            return FilterSequencesImpl.filterCSV(evalCutoff, scoreCutoff, evalue, score, sequences);
        }//else
        
    }//filterByEvalScoreCSV
    
    /**
     * Web service operation, That filters sequences passed to it as an Array based on cutoff values for score and expectation Value
     */
        
    @WebMethod(operationName = "filterByEvalScore")
    @WebResult(name="FilteredSequences")
    public List<String> filterByEvalScore(
            @WebParam(name = "evaluesArray" ) double[] evalue , 
            @WebParam(name = "evalueCutoff" ) Double evalCutoff, 
            @WebParam(name = "scoresArray"  ) double[] score , 
            @WebParam(name = "scoreCutoff"  ) Double scoreCutoff,             
            @WebParam(name = "sequences"    ) String[] sequences
            )
            throws ImproperInputsFault
    {
        if (evalue == null || evalCutoff == null || sequences == null)
            throw new ImproperInputsFault("Either of evalue, evalueCutoff and sequences cannot be null..!",new Fault("ImproperInputs"));
        else if (evalue.length == 0 || evalCutoff.doubleValue() <= 0 || sequences.length == 0)
            throw new ImproperInputsFault("Either of evalue, evalueCutoff and sequences cannot be empty or null..!",new Fault("ImproperInputs"));
        else
        {
            if(scoreCutoff == null) 
                scoreCutoff = 0.00;
            return FilterSequencesImpl.filterArray(evalCutoff, scoreCutoff, evalue, score, sequences);
        }//else
        
    }//filterByEvalScore
        
}//FilterSequences
