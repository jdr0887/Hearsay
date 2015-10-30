<?php
/*
 * Copyright (c) 2012  Renaissance Computing Institute. All rights reserved.
 *
 * This software is open source. See the bottom of this file for the license.
 *
 * Renaissance Computing Institute,
 * (A Joint Institute between the University of North Carolina at Chapel Hill,
 * North Carolina State University, and Duke University)
 * http://www.renci.org
 *
 * For questions, comments please contact software@renci.org
 *
 */

/**
	Description: This file contains server-side functionality for using AJAX to query databases
	To use this file, make a POST request to this file (urlencoded) and set function=function_name where
	fnname is the name that represents the functionality desired.
	Here are the current possible function names:
*/

	// get the user session helper object
	include_once('UserHelper.php');

	// get the user info object from the session
	$userObj = getUserSessionObj();

	// define a class for the evidence data
	class Evidence
	{
		var $geneName;
		var $name;
		var $value;
		var $type;
		
		// setter
		function setData($_geneName, $_name, $_value, $_type)
		{
			$this->geneName = $_geneName;
			$this->name = $_name;
			$this->value = $_value;
			$this->type = $_type;
		}
	}
	
	// is the user logged in
	if(!isset($userObj))
		header('Location: ../../Login/Login.php');

	// if this page was accessed via a post
	if($_SERVER['REQUEST_METHOD'] == "POST")
		$req = $_POST;
	else
		$req = $_GET;

	// retrieve the function name
	$function = $req["function"];

	////////////////////////////////////////////////
	// decide what to do based on the function name
	//
	// NCGENES activities
	////////////////////////////////////////////////

	// get the site config file
	if($function == "getConfig")
	{
		// get the site config file
		doGetConfig();
	}
	// get the participant demographics from NCGENES
	// ex: http://localhost/NCGENES_WF/MolecularAnalysis/CARNAC/utils/ajaxdata.php?function=getParticipant&DonorCode=NCG_00020
	else if($function == "getParticipant")
	{
		// retrieve donor code from post data
		$DonorCode = $req["DonorCode"];

		// get a JSON encoded list of participant details
		doGetParticipant($DonorCode);
	}
	// get the participant transcripts for a NCGENES participant
	// ex: http://localhost/NCGENES_WF/MolecularAnalysis/CARNAC/utils/ajaxdata.php?function=getParticipantTranscripts&DonorCode=NCG_00020
	else if($function == "getParticipantTranscripts")
	{
		// retrieve the posted data
		$DonorCode = $req["DonorCode"];

		// get a JSON encoded list of transcripts for a gene on the genome
		doGetParticipantTranscripts($DonorCode);
	}
	// get the participant variants from NCGENES
	// ex: http://localhost/NCGENES_WF/MolecularAnalysis/CARNAC/utils/ajaxdata.php?function=getParticipantVariants&DonorCode=NCG_00020
	else if($function == "getParticipantVariants")
	{
		// retrieve the posted data
		$DonorCode = $req["DonorCode"];

		// get a JSON encoded list of participant variants
		doGetParticipantVariants($DonorCode);
	}
	// get the gene allele frequency details from CANVAS
	// ex: http://localhost/NCGENES_WF/MolecularAnalysis/CARNAC/utils/ajaxdata.php?function=getCANVASGeneAlleleFreq&geneName=NCG_00020
	else if($function == "getCANVASGeneAlleleFreq")
	{
		// retrieve the posted data
		$geneName = $req["geneName"];

		// get a JSON encoded list of participant variants
		doGetCANVASGeneAlleleFreq($geneName);
	}
	// get the NCGENES notes for this allele
	// ex: http://localhost/NCGENES_WF/MolecularAnalysis/CARNAC/utils/ajaxdata.php?function=getAlleleNotes&locvarid=401392034
	else if($function == "getAlleleNotes")
	{
		// retrieve the posted data
		$locvarid = $req["locvarid"];
	
		// get a JSON encoded list of participant variants
		doGetAlleleNotes($locvarid);
	}
	
	
	////////////////////////
	// Hearsay activities
	////////////////////////

	// get the gene interval from hearsay
	// ex: http://localhost/NCGENES_WF/MolecularAnalysis/CARNAC/utils/ajaxdata.php?function=getGeneInterval&geneName=BRCA1
	else if($function == "getGeneInterval")
	{
		// retrieve the posted data
		$geneName = $req["geneName"];

		// get a JSON encoded endopints for a gene on the genome
		doGetGeneInterval($geneName);
	}
	// get the description of a gene from hearsay
	// ex: http://localhost/NCGENES_WF/MolecularAnalysis/CARNAC/utils/ajaxdata.php?function=getGeneDescription&geneName=BRCA1
	else if($function == "getGeneDescription")
	{
		// retrieve the posted data
		$geneName = $req["geneName"];

		// get a JSON encoded list of the gene description
		doGetGeneDescription($geneName);
	}
	// get the gene provenance information from hearsay
	// ex: http://localhost/NCGENES_WF/MolecularAnalysis/CARNAC/utils/ajaxdata.php?function=getGeneProvenance&geneName=BRCA1
	else if($function == "getGeneProvenance")
	{
		// retrieve the posted data
		$geneName = $req["geneName"];

		// get a JSON encoded list of gene provenance information
		doGetGeneProvenance($geneName);
	}
	// the the transcripts for a gene from hearsay
	// ex: http://localhost/NCGENES_WF/MolecularAnalysis/CARNAC/utils/ajaxdata.php?function=getGeneMappedTranscripts&geneName=BRCA1
	else if($function == "getGeneMappedTranscripts")
	{
		// retrieve the posted data
		$geneName = $req["geneName"];

		// get a JSON encoded list of transcripts for a gene on the genome
		doGetGeneMappedTranscripts($geneName);
	}
	// the the transcripts for a gene from hearsay
	// ex: http://localhost/NCGENES_WF/MolecularAnalysis/CARNAC/utils/ajaxdata.php?function=getGeneTranscripts&geneName=BRCA1
	else if($function == "getGeneTranscripts")
	{
		// retrieve the posted data
		$geneName = $req["geneName"];

		// get a JSON encoded list of transcripts for a gene on the genome
		doGetGeneTranscripts($geneName);
	}
	// the the transcripts for a gene from hearsay
	// ex: http://localhost/NCGENES_WF/MolecularAnalysis/CARNAC/utils/ajaxdata.php?function=getCanonicalGeneData&geneName=BRCA1
	else if($function == "getCanonicalGeneData")
	{
		// retrieve the posted data
		$geneName = $req["geneName"];

		// get a JSON encoded list of gene data which includes allele frequencies
		doGetCanonicalGeneData($geneName);
	}
	
	////////////////////////
	// Hearsay activities - evidence collection 
	////////////////////////
	
	// get the evidence data for the locvar
	// ex: http://localhost/NCGENES_WF/MolecularAnalysis/CARNAC/utils/ajaxdata.php?function=getEvidenceData&geneName=BRCA1
	else if($function == "getEvidenceData")
	{
		// retrieve the posted data
		$geneName = $req["geneName"];
		
		// get a JSON encoded list of evidence data for the loc var id
		doGetEvidence($geneName);
	}
	// set the evidence data for the locvar
	// ex: http://localhost/NCGENES_WF/MolecularAnalysis/CARNAC/utils/ajaxdata.php?function=setEvidenceData&name=LOF&type=0&geneName=BRCA1&value=true
	else if($function == "setEvidenceData")
	{
		// retrieve the posted data
		$geneName = $req["geneName"];
				
		// retrieve the posted data
		$name = $req["name"];
		
		// retrieve the posted data
		$type = $req["type"];
		
		// retrieve the posted data
		$value = $req["value"];
	
		// set a JSON encoded list of evidence data for the loc var id
		doSetEvidence($geneName, $name, $type, $value);
	}
	
	// else catch all. unrecognixed command
	else
	{
		// if we get here we got an unknown request
		// send the data to the page
		echo '{"error": "Unrecognized data request."}';

		// discontinue processing
		die();
	}

	/**
	 * gets the notes for this allele
	 * 
	 */
	function doGetAlleleNotes($locvarid)
	{
		// include the data access class
		include_once("DAO.php");
		
		// create a new data access object
		$db = new DAO();
				
		// create the SQL
		$sql = "SELECT DISTINCT Note FROM AnalysisNote WHERE loc_var_id=" . $locvarid;
		
		// assign the SQL
		$db->setSQL($sql);
		
		// preset the return value
		$retVal = NULL;
		
		// execute the SQL
		if($db->execute())
		{
			// did we get some rows
			if(sqlsrv_has_rows($db->getResultSet()))
			{
				// init a counter
				$i = 0;
		
				// output the table rows
				while($row = sqlsrv_fetch_array($db->getResultSet(), SQLSRV_FETCH_ASSOC))
				{
					// add the row to the output array
					$data[$i] = $row;
		
					// next row
					$i++;
				}
		
				// return the data JSON formatted
				$retVal = '{"data":' . json_encode($data) . '}';
			}
			else
				$retVal = '{"warning": "doGetAlleleNotes() - No data"}';
		}
		else
			$retVal = '{"error": "doGetAlleleNotes() - Error getting data"}';
		
		//error_log(print_r($data, true));
		
		// return the data to the caller
		echo $retVal;
		
		// terminate the data stream
		die();		
	}
	
	/**
	 * gets the evidence data for the passed in loc var ID
	 *  
	 */
	function doGetEvidence($geneName)
	{
		// init a evidence data element
		$evidence = null;
		
		// get the user info object from the session
		$userObj = getUserSessionObj();
		
		// do we have any evidence
		if(isset($userObj) && !empty($userObj) && isset($userObj->Evidence) && !empty($userObj->Evidence))
		{
			// find the evidence if it exists
			foreach ($userObj->Evidence as $item)
			{
				// did we get a match
				if($item->geneName == $geneName)
				{
					// save the evidence
					$evidence = $item;
			
					// no need to continue
					break;
				}
			}
		}
			
		// does the evidence already exist
		if(isset($evidence))
		{
			// encode and send the data
		 	echo '{"data":' . json_encode($evidence) . '}';
		}
		else
		{
			// return no data
			echo '{"error": "doGetEvidence(' . $geneName . ') - No data"}';				
		}

		// terminate the data stream
	 	die();
	}
	
	/**
	 * Sets the evidence data for the passed in loc var ID
	 *
	 */
	function doSetEvidence($geneName, $name, $type, $value)
	{
		// init a evidence data element
		$evidence = null;
		
		// init a index
		$i = 0;
		
		// get the user info object from the session
		$userObj = getUserSessionObj();
		
		// do we have a evidence object
		if(isset($userObj->Evidence))
		{
			// find the evidence if it exists
			foreach ($userObj->Evidence as $item)	
			{
				// is this the one we are looking for
				if($item->geneName == $geneName)
				{
					// save the evidence
					$evidence = $item;
					
					// no need to continue
					break;
				}
				
				//increment the index counter
				$i++;
			}
		}
				
		// does the evidence already exist
		if(isset($evidence))
		{
			// overwrite the current value
			$userObj->Evidence[$i]->value = $value;
			
			echo '{"error": "doSetEvidence(' . $geneName . ') - Data overwritten"}';				
		}
		// else attach the evidence data to the user object
		else
		{
			// create a new evidence object
			$evidence = new Evidence();
			
			// save the data in the object
			$evidence->setData($geneName, $name, $value, $type);
			
			// and save it to the user object
			$userObj->Evidence[] = $evidence;
			
			// encode and send the data
		 	echo '{"data":' . json_encode($evidence) . '}';
		}

		// terminate the data stream
	 	die();	
	}
	
	/**
	 * Gets the site config items
	 *
	 */
	function doGetConfig()
	{
		// parse the config file
		$config = parse_ini_file("Config.ini", 1);

		// encode and send the data
	 	echo '{"data":' . json_encode($config) . '}';

		// terminate the data stream
	 	die();
	}
	
	/**
	 * gets the participant details from NCGENES
	 *
	 *
	 * @param string $DonorCode
	 */
	function doGetParticipant($DonorCode)
	{
		// include the site constants
		include_once('Constants.php');

		// include the participant object
		include_once("Participant.php");

		// create a new participant object
		$p = new Participant();

		// construct values for participant
		$p->load($DonorCode);

		// did we find the donor
		$foundDonor = $p->getDONOR_CODE();

		// if we got the donor loaded
		if(isset($foundDonor) && !empty($foundDonor))
			echo json_encode($p->getParticipantDetails());
		else
			echo '{"error": "Donor ' . $DonorCode. ' not found"}';

		// terminate the data stream
		die();
	}

	/**
	 * gets the participant gene transcript details from NCGenes
	 *
	 * @param string $DonorCode
	 */
	function doGetParticipantTranscripts($DonorCode)
	{
		// include the data access class
		include_once("DAO.php");

		// create a new data access object
		$db = new DAO();

		// set the request params
		$AnalysisType = 2;		// the type of analysis results (Dx=2 vs. incidental=1)
		$roleID = 22;			// the role of the user (22 is admin/everything)
		$type = 2;				// the type of results (parent rows=1 vs transcript rows=2)
		$geneID = 'No filter';	// filter on a specific gene in the results
		$FilterID = -1;			// the type of specific bin analysis result (specific Dx code vs. specific incidental code)

		// create the SQL
		$sql = "EXEC dbo.GetAnalysisResults @DONOR_CODE = '" . $DonorCode . "', @AnalysisType=" . $AnalysisType . ", @Role=" . $roleID . ", @type=". $type . ", @geneID='". $geneID . "', @FilterID=" . $FilterID;

		// assign the SQL
		$db->setSQL($sql);

		// preset the return value
		$retVal = NULL;

		// execute the SQL
		if($db->execute())
		{
			// did we get some rows
			if(sqlsrv_has_rows($db->getResultSet()))
			{
				// init a counter
				$i = 0;

				// output the table rows
				while($row = sqlsrv_fetch_array($db->getResultSet(), SQLSRV_FETCH_ASSOC))
				{
					// add the row to the output array
					$data[$i] = $row;

					// next row
					$i++;
				}

				// return the data JSON formatted
				$retVal = '{"data":' . json_encode($data) . '}';
			}
			else
				$retVal = '{"error": "doGetParticipantTranscripts() - No data"}';
		}
		else
			$retVal = '{"error": "doGetParticipantTranscripts() - Error getting data"}';

		//error_log(print_r($data, true));
		
		// return the data to the caller
		echo $retVal;

		// terminate the data stream
		die();
	}

	/**
	 * gets the participant variants from NCGENES
	 *
	 * @param string $DonorCode
	 */
	function doGetParticipantVariants($DonorCode)
	{
		// include the data access class
		include_once("DAO.php");

		// create a new data access object
		$db = new DAO();

		// set the request params
		$AnalysisType = 2;		// the type of analysis results (Dx=2 vs. incidental=1)
		$roleID = 22;			// the role of the user (22 is admin/everything)
		$type = 1;				// the type of results (parent rows=1 vs transcript rows=2)
		$geneID = 'No filter';	// filter on a specific gene in the results
		$FilterID = -1;			// the type of specific bin analysis result (specific Dx code vs. specific incidental code)

		// create the SQL
		$sql = "EXEC dbo.GetAnalysisResults @DONOR_CODE = '" . $DonorCode . "', @AnalysisType=" . $AnalysisType . ", @Role=" . $roleID . ", @type=". $type . ", @geneID='". $geneID . "', @FilterID=" . $FilterID;

		// assign the SQL
		$db->setSQL($sql);

		// preset the return value
		$retVal = NULL;

		// execute the SQL
		if($db->execute())
		{
			// did we get some rows
			if(sqlsrv_has_rows($db->getResultSet()))
			{
				// init a counter
				$i = 0;

				// output the table rows
				while($row = sqlsrv_fetch_array($db->getResultSet(), SQLSRV_FETCH_ASSOC))
				{
					// add the row to the output array
					$data[$i] = $row;

					// next row
					$i++;
				}

				// return the data JSON formatted
				$retVal = '{"data":' . json_encode($data) . '}';
			}
			else
				$retVal = '{"error": "doGetParticipantVariants() - No data"}';
		}
		else
			$retVal = '{"error": "doGetParticipantVariants() - Error getting data"}';

		//error_log(print_r($data, true));
		
		// return the data to the caller
		echo $retVal;

		// terminate the data stream
		die();
	}

	/**
	 * gets the gene provenance from the hearsay database
	 *
	 * @param string $geneName
	 */
	function doGetGeneProvenance($geneName)
	{
		// parse the config file
		$config = parse_ini_file("Config.ini", 1);

		// generate the servie URL with the function name and params
	 	$serviceURL = $config['HearSayREST'][''] . '/' . $geneName;

	 	// make the REST call
	 	$data = doRESTCall("doGetGeneProvenance()", $serviceURL);

	 	//error_log($serviceURL . ", " . print_r(json_decode($data), true));
	 	
	 	// send the data to the page
		echo $data;

		// terminate the data stream
		die();
	}

	/**
	 * gets the gene Interval on the gemome from the hearsay database
	 *
	 * @param string $geneName
	 */
	function doGetGeneInterval($geneName)
	{
		// parse the config file
		$config = parse_ini_file("Config.ini", 1);

		// generate the servie URL with the function name and params
		$serviceURL = $config['HearSayREST'][''] . '/' . $geneName;

	 	// make the REST call
	 	$data = doRESTCall($serviceURL);

	 	//error_log($serviceURL . ", " . print_r(json_decode($data), true));
	 	
	 	// send the data to the page
		echo $data;

		// terminate the data stream
		die();
	}

	/**
	 * gets the gene description from the hearsay database
	 *
	 * @param string $geneName
	 */
	function doGetGeneDescription($geneName)
	{
		// parse the config file
		$config = parse_ini_file("Config.ini", 1);

		// generate the servie URL with the function name and params
		$serviceURL = $config['HearSayREST']['CARNACGeneServiceHost'] . 'findByName/' . $geneName;

	 	// make the REST call
	 	$data = doRESTCall($serviceURL);

	 	//error_log($serviceURL . ", " . print_r(json_decode($data), true));
	 	
	 	// send the data to the page
		echo $data;

		// terminate the data stream
		die();
	}

	/**
	 * gets the mapped transcript details for a gene
	 *
	 * @param string $geneName
	 */
	function doGetGeneMappedTranscripts($geneName)
	{
		// parse the config file
		$config = parse_ini_file("Config.ini", 1);

		// generate the servie URL with the function name and params
		$serviceURL = $config['HearSayREST']['CARNACMappedTranscriptServiceHost'] . 'findByGeneName/' . $geneName;

	 	// make the REST call
	 	$data = doRESTCall($serviceURL);

	 	//error_log($serviceURL . ", " . print_r(json_decode($data), true));
	 		 
		// send the data to the page
		echo $data;

		// terminate the data stream
		die();
	}

	/**
	 * gets the canonical details for a gene
	 *
	 * @param string $geneName
	 */
	function doGetCanonicalGeneData($geneName)
	{
		//$geneName = 'A1BG';
		
		// parse the config file
		$config = parse_ini_file("Config.ini", 1);

		// generate the servie URL with the function name and params
		$serviceURL = $config['HearSayREST']['CARNACCanonicalServiceHost'] . 'findByGeneName/' . $geneName;

	 	// make the REST call
	 	$data = doRESTCall($serviceURL);

	 	//error_log($serviceURL . ", " . print_r(json_decode($data), true));
	 	
		// send the data to the page
		echo $data;

		// terminate the data stream
		die();
	}

	/**
	 * gets the transcript details for a gene
	 *
	 * @param string $geneName
	 */
	function doGetGeneTranscripts($geneName)
	{
		// parse the config file
		$config = parse_ini_file("Config.ini", 1);

		// generate the servie URL with the function name and params
		$serviceURL = $config['HearSayREST']['CARNACTranscriptServiceHost'] . 'findByGeneName/' . $geneName;

	 	// make the REST call
	 	$data = doRESTCall($serviceURL);

	 	//error_log($serviceURL . ", " . print_r(json_decode($data), true));
	 	
		// send the data to the page
		echo $data;

		// terminate the data stream
		die();
	}

	/**
	 * Makes a REST data request
	 *
	 * @param string $serviceURL
	 */
	function doRESTCall($serviceURL)
	{
		try
		{
			// init the curl call
			$curl = curl_init($serviceURL);

			// set up for an expected return
	        curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);

	        // initate and gt the response
	       	$data = curl_exec($curl);

	       	// was it successful
			if ($data === false)
			{
				// get the results of the call
				$info = curl_getinfo($curl);

				// create a error statement
				$err = 'Error occured during doRESTCall exec. ' . var_export($info);

				// log the error
				error_log($err);

				// set the error in the data returned
				$retVal = '{"error": "' . $err . '"}';
			}
			else if($data == "[]" || empty($data))
				$retVal = '{"error": "No data"}';
			else
				$retVal = '{"data": ' . $data . '}';

			// close the connection
			curl_close($curl);
		}
		catch(Exception $e)
		{
			// log the error
			error_log($e->getMessage());

			// return the exception
			$retVal = '{"error": "' . $e->getMessage() . '"}';
		}

		// return to the caller
		return $retVal;
	}

/****************************************************************************
RENCI Open Source Software License
The University of North Carolina at Chapel Hill

The University of North Carolina at Chapel Hill (the "Licensor") through
its Renaissance Computing Institute (RENCI) is making an original work of
authorship (the "Software") available through RENCI upon the terms set
forth in this Open Source Software License (this "License").  This License
applies to any Software that has placed the following notice immediately
following the copyright notice for the Software:  Licensed under the RENCI
Open Source Software License v. 1.0.

Licensor grants You, free of charge, a world-wide, royalty-free,
non-exclusive, perpetual, sublicenseable license to do the following to
deal in the Software without restriction, including without limitation the
rights to use, copy, modify, merge, publish, distribute, sublicense,
and/or sell copies of the Software, and to permit persons to whom the
Software is furnished to do so, subject to the following conditions:

. Redistributions of source code must retain the above copyright notice,
this list of conditions and the following disclaimers.

. Redistributions in binary form must reproduce the above copyright
notice, this list of conditions and the following disclaimers in the
documentation and/or other materials provided with the distribution.

. Neither You nor any sublicensor of the Software may use the names of
Licensor (or any derivative thereof), of RENCI, or of contributors to the
Software without explicit prior written permission.  Nothing in this
License shall be deemed to grant any rights to trademarks, copyrights,
patents, trade secrets or any other intellectual property of Licensor
except as expressly stated herein.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
THE CONTIBUTORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR
OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.

You may use the Software in all ways not otherwise restricted or
conditioned by this License or by law, and Licensor promises not to
interfere with or be responsible for such uses by You.  This Software may
be subject to U.S. law dealing with export controls.  If you are in the
U.S., please do not mirror this Software unless you fully understand the
U.S. export regulations.  Licensees in other countries may face similar
restrictions.  In all cases, it is licensee's responsibility to comply
with any export regulations applicable in licensee's jurisdiction.
****************************************************************************/
?>
