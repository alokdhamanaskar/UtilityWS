
package filterSeq;

import static java.lang.System.out;

/**
 * @author Alok Dhamanaskar (alokd@uga.edu)
 * @see LICENSE (MIT style license file). 
 *
 * Class to represent ImproperInputs Exception, which will be converted to a fault Message for the Web service
 */
public class ImproperInputsFault extends java.io.IOException {
    
    ImproperInputsFault(String errMsg)
    {
        super(errMsg);
    }//ImproperInputsFault
    
    public static void main(String[] args) throws ImproperInputsFault
    {
        //Test code
        out.println("Exception will occur");
        throw new ImproperInputsFault("a,s,d cannot be null..!");
    
    }//main

}//ImproperInputsFault
