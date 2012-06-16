
package filterSeq;

import javax.xml.ws.WebFault;

/**
 * @author Alok Dhamanaskar (alokd@uga.edu)
 * @see LICENSE (MIT style license file). 
 *
 * <br/> Class to represent ImproperInputs Exception, which will be converted to a fault Message for the Web service
 */

@WebFault(
        name = "ImproperInputsFault",
        targetNamespace = "http://wsannotations.ctegd.uga.edu/services/"
        )
public class ImproperInputsFault extends Exception 
{
        
    private Fault faultInfo;
   
    ImproperInputsFault(String errMsg, Fault fi)
    {
        super(errMsg);
        this.faultInfo = fi;
    }//ImproperInputsFault

    ImproperInputsFault(String errMsg, Fault fi, Throwable cause)
    {
        super(errMsg);
        this.faultInfo = fi;
    }//ImproperInputsFault
    
    public Fault getFaultInfo()
    {
        return this.faultInfo;
    }//getFaultInfo
    
    public static void main(String[] args) throws ImproperInputsFault
    {
        //Test code
        System.out.println("Exception will occur");
        throw new ImproperInputsFault("a,s,d cannot be null..!", new Fault("Fault..!!"));
    
    }//main

}//ImproperInputsFault
