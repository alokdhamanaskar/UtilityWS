UtilityWS
=========
@author Alok Dhamanaskar alokdhamanaskar@gmail.com @see LICENSE (MIT style license file).

The Project hosts the code and othe artifacts required for deploying two Web services, WSConverters and Filter Sequences on Apache Axis2

 * WSConverters : A utility Web service, that provides converters typically required when constructing Web service Workflows.
 * Supported Operations
	* decode : Decodes the data from a Base64 encoded string 
	* encode : Encodes the source data to a Base64 encoded string
	* arraytoCSV : Converts an array of Whatever (e.g. Sequence Ids, Sequences ) to Comma Separated Values
	* csvtoArray : Converts Comma separated values of whatever (e.g. Sequence Ids, Sequences ) to an Array 
 * Web service Description Document : http://mango.ctegd.uga.edu/jkissingLab/SWS/webservices/converters.wsdl



 * filter Sequences :  A utility Web service, to filter sequences (from output of some alignment) based on cutoff vales for expectation value and score.
 * Supported Operations
	* filterByEvalScore : Filters Sequences based on cutoff vales for expectation value and score passed to it as an Array 
	* filterByEvalScoreCSV : Filters sequences based on cutoff vales for expectation value and score passed to it as Comma separated values
 * Web service Description Document : http://mango.ctegd.uga.edu/jkissingLab/SWS/webservices/filterSequences.wsdl
