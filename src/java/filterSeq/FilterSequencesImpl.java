
package filterSeq;

import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alok Dhamanaskar (alokd@uga.edu)
 * @see LICENSE (MIT style license file). 
 * 
 * <br/><br/>This class Implements methods exposed through FilterSequences Web service, along with required supporting methods.
 *
 */
public class FilterSequencesImpl { 

    /**
     * Filters sequences passed to it as an array, on the basis to expectation value cut off.
     * @param evalCutoff The Expectation value cutoff, only sequences with lower exp values will pass
     * @param exp An array Expectation values that correspond to each sequence in the sequence array
     * @param sequences An array of sequences / sequence Ids
     * @return  Filtered sequences
     * @throws ImproperInputsFault 
     */
    public static List<String> filterByEval
            (double evalCutoff, double[] exp, String[] seqID) throws ImproperInputsFault
    {
        List<String> filteredSequences = new ArrayList<String>();
       
        if (exp.length == seqID.length)
        {
            for (int i = 0; i < seqID.length; i++)
            {
                if (exp[i] < evalCutoff )
                   filteredSequences.add(seqID[i]);
            }//for

        }//if
        else
        {
            throw new ImproperInputsFault("The arrays for exp, score and sequences should be of same length..!",new Fault("ImproperInputs"));
        }//else
        return filteredSequences;

    }//filterByEval
    
    /**
     * Filters sequences passed to it as an array, on the basis to expectation and score value a cut off.
     * @param evalCutoff The Expectation value cutoff, only sequences with lower exp values will pass
     * @param exp An array Expectation values that correspond to each sequence in the sequence array
     * @param sequences An array of sequences / sequence Ids
     * @param scoreCutoff A cutoff vale for score, such that sequence with score higher that it shall only pass
     * @param score An array of scores that correspond to each sequence in the sequence array
     * @return  Filtered sequences
     * @throws ImproperInputsFault 
     */
    public static List<String> filterByScoreEval
            (double evalCutoff, double scoreCutoff, double[] exp, double[] score, String[] seqID) throws ImproperInputsFault
    {
        List<String> filteredSequences = new ArrayList<String>();
       
        if (score.length == exp.length && exp.length == seqID.length)
        {
            for (int i = 0; i < seqID.length; i++)
            {
                if (exp[i] < evalCutoff && score[i] > scoreCutoff)
                   filteredSequences.add(seqID[i]);
            }//for
        }//if
        else
        {
            throw new ImproperInputsFault("The arrays for exp, score and sequences should be of same length..!",new Fault("ImproperInputs"));
        }//else
        
        return filteredSequences;

    }//filterByScoreEval
    
    /**
     * Filters sequences passed to it as an array, on the basis to expectation and score value a cut off.
     * @param evalCutoff The Expectation value cutoff, only sequences with lower exp values will pass
     * @param exp An array Expectation values that correspond to each sequence in the sequence array
     * @param sequences An array of sequences / sequence Ids
     * @param scoreCutoff A cutoff value for score, such that sequence with score higher that it shall only pass
     * @param score An array of scores that correspond to each sequence in the sequence array
     * @return Filtered sequences
     * @throws ImproperInputsFault 
     */
    public static List<String> filterArray
            (double evalCutoff, double scoreCutoff, double[] exp, double[] score, String[] seqID) throws ImproperInputsFault
    {
        if (score == null || score.length == 0 )
            return filterByEval(evalCutoff, exp, seqID);
        else
        return filterByScoreEval(evalCutoff, scoreCutoff, exp, score, seqID);

    }//filterArray

        
    /**
     * Filters sequences passed to it as CSV, on the basis to expectation and score value a cut off.
     * @param evalCutoff The Expectation value cutoff, only sequences with lower exp values will pass
     * @param exp Comma separated Expectation values that correspond to each sequence in the sequence CSV
     * @param sequences Comma separated sequences / sequence Ids
     * @param scoreCutoff A cutoff value for score, such that sequence with score higher that it shall only pass
     * @param score Comma separated scores that correspond to each sequence in the sequence CSV
     * @return Filtered sequences
     * @throws ImproperInputsFault 
     */
    public static String filterCSV
            (double evalCutoff, double scoreCutoff, String expCSV, String scoreCSV, String seqCSV) throws ImproperInputsFault
    {
        String[] expArr = expCSV.split(",");
        double[] expDoubleArr = new double[expArr.length];
        List<String> filteredSeq;
        int i= 0 ;
        
        for (String s : expArr)  expDoubleArr[i++] = Double.parseDouble(s); 
        
        if (scoreCSV == null || scoreCSV.equals("") || !scoreCSV.contains(","))
            filteredSeq = filterByEval(evalCutoff, expDoubleArr, seqCSV.split(","));
        else
        {
            String[] scoreArr = scoreCSV.split(",");
            double[] scoreDoubleArr = new double[scoreArr.length];
            i = 0 ;
            for (String s : scoreArr)  scoreDoubleArr[i++] = Double.parseDouble(s); 
            filteredSeq = filterByScoreEval(evalCutoff, scoreCutoff, expDoubleArr, scoreDoubleArr, seqCSV.split(","));
        }
        
        String seq = "";
        for (String s : filteredSeq)
        {
            seq += s + ",";
        }
        
        return seq;

    }//filtercsv
    

    public static void main(String[] args) throws Exception
    {
        //Test for array inputs
        out.println(filterArray(0.12, 10.0 , new double[]{0.003,0.03,0.3},new double[]{10,11,33}, new String[]{"cc","dd","ff"}));
        out.println();
        out.println(filterArray(0.12, 10.0 , new double[]{0.003,0.03,0.3},new double[]{}, new String[]{"cc","dd","ff"}));
        out.println();
        out.println(filterArray(0.12, 10.0 , new double[]{0.003,0.03,0.3},null, new String[]{"cc","dd","ff"}));
        out.println();
        
        //Test for csv Inputs
        out.println(filterCSV(0.03, 10, "0.1,0.01,0.009", "" , "aa,dd,ff"));
        out.println();
        out.println(filterCSV(0.03, 10, "0.1,0.01,0.009", "10,2,20" , "aa,dd,ff"));
        out.println();
        out.println(filterCSV(0.03, 10, "0.1,0.01,0.009", null, "aa,dd,ff"));

    }//main

}//FilterSequencesImpl
