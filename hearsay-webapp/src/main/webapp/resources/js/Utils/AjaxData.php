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
	
	// is the user logged in
	if(!isset($userObj))
		header('Location: ../../Login/Login.php');

	// if this page was accessed via a post
	if($_SERVER['REQUEST_METHOD'] == "POST")
		$req = $_POST;
	else
		$req = $_GET;

	// include the data access class
	include_once("DAO.php");
		
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
	// get the transcripts for a gene from hearsay
	// ex: http://localhost/NCGENES_WF/MolecularAnalysis/CARNAC/utils/ajaxdata.php?function=getGeneMappedTranscripts&geneName=BRCA1
	else if($function == "getGeneMappedTranscripts")
	{
		// retrieve the posted data
		$geneName = $req["geneName"];

		// get a JSON encoded list of transcripts for a gene on the genome
		doGetGeneMappedTranscripts($geneName);
	}
	// get the transcripts for a gene from hearsay
	// ex: http://localhost/NCGENES_WF/MolecularAnalysis/CARNAC/utils/ajaxdata.php?function=getGeneTranscripts&geneName=BRCA1
	else if($function == "getGeneTranscripts")
	{
		// retrieve the posted data
		$geneName = $req["geneName"];

		// get a JSON encoded list of transcripts for a gene on the genome
		doGetGeneTranscripts($geneName);
	}
	// get transcripts for a gene from hearsay
	// ex: http://localhost/NCGENES_WF/MolecularAnalysis/CARNAC/utils/ajaxdata.php?function=getCanonicalGeneData&geneName=BRCA1
	else if($function == "getCanonicalGeneData")
	{
		// retrieve the posted data
		$geneName = $req["geneName"];

		// get a JSON encoded list of gene data which includes allele frequencies
		doGetCanonicalGeneData($geneName);
	}
	// get the transcripts for a gene from hearsay
	// ex: http://localhost/NCGENES_WF/MolecularAnalysis/CARNAC/utils/ajaxdata.php?function=getGenePopFreqData&geneName=BRCA1
	else if($function == "getGenePopFreqData")
	{	
		// get a JSON encoded list of gene data which includes allele frequencies
		doGetGenePopFreqData($req);
	}
	
	////////////////////////
	// NCGENES DB activities - ACMG evidence collection 
	////////////////////////
	
	// get the evidence data for the locvar
	// ex: http://localhost/NCGENES_WF/MolecularAnalysis/CARNAC/utils/ajaxdata.php?function=getEvidenceSessionData&geneName=BRCA1
	else if($function == "getEvidenceSessionData")
	{
		// retrieve the posted data
		$geneName = $req["geneName"];
		
		// get a JSON encoded list of evidence data for the loc var id
		doGetEvidenceFromSession($geneName);
	}
	// set the evidence data for the locvar
	// ex: http://localhost/NCGENES_WF/MolecularAnalysis/CARNAC/utils/ajaxdata.php?function=setEvidenceSessionData&name=LOF&type=0&geneName=BRCA1&value=true
	else if($function == "setEvidenceSessionData")
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
		doSetEvidenceToSession($geneName, $name, $type, $value);
	}
	// gets the evidence from the DB
	else if($function == "getEvidenceData")
	{	
		// get a JSON encoded list of evidence data for the loc var id
		doGetEvidenceData($req["VariantID"]);
	}
	// updates the evidence into the DB
	else if($function == "updateEvidenceData")
	{
		// set evidence data for the variant
		doUpdateEvidenceData($req);
	}
	// gets more evidence from the DB
	else if($function == "getMoreEvidenceData")
	{
		// get a JSON encoded list of evidence data for the loc var id
		doGetMoreEvidenceData($req["VariantID"]);
	}
	// updates more evidence into the DB
	else if($function == "updateMoreEvidenceData")
	{
		// set evidence data for the variant
		doUpdateMoreEvidenceData($req);
	}
	// gets the evidence types
	else if($function == "getEvidenceTypes")
	{
		// get a JSON encoded list of evidence types
		doGetEvidenceTypes($req);
	}
	// gets the ACMG evaluation data
	else if($function == "getACMGEvidenceReviewParentData")
	{
		// get a JSON encoded list of evidence data, parent table rows
		doGetACMGEvidenceReviewParentData($req["VariantID"]);
	}
	// gets the ACMG evaluation data
	else if($function == "getACMGCriteriaEvalParentData")
	{
		// get a JSON encoded list of evidence data, parent table rows
		doGetACMGCriteriaEvalParentData($req["VariantID"]);
	}
	// gets the ACMG evaluation data
	else if($function == "getACMGEvidenceReviewData")
	{
		// do we have the ID
		if(isset($req["ACMGCriteriaID"]))
			$ACMGCriteriaID = $req["ACMGCriteriaID"];
		else
			$ACMGCriteriaID = null;
		
		// get a JSON encoded list of evidence data
		doGetACMGEvidenceReviewData($req["VariantID"], $req["DONOR_CODE"], $ACMGCriteriaID);
	}
	// updates the ACMG evaluation data
	else if($function == "updateACMGEvidenceReviewData")
	{
		// set the evaluation data
		doUpdateACMGEvidenceReviewData($req);
	}
	// gets the ACMG evaluation data
	else if($function == "getACMGEvidenceReviewCriteriaData")
	{
		// get a JSON encoded list of evidence data
		doGetACMGEvidenceReviewCriteriaData($req["VariantID"], $req["DONOR_CODE"], $req["ACMGCriteriaID"]);
	}
	// updates the ACMG evaluation data
	else if($function == "updateACMGEvidenceReviewCriteriaData")
	{
		// set the evaluation data
		doUpdateACMGEvidenceReviewCriteriaData($req);
	}
	else if($function == "getCriteriaAssessmentData")
	{
		// get the fulfillment data
		doGetCriteriaAssessmentData($req["VariantID"]);
	}	
	else if($function == "getGeneCurationData")
	// gets the gene curation data
	{
		// get a JSON encoded list of disease source data
		doGetGeneCurationData($req["GeneName"]);
	}	
	// gets the gene curation data by Name and Type
	else if($function == "getGeneCurationDataByNameType")
	{
		// get a JSON encoded list of disease source data
		doGetGeneCurationDataByNameType($req["geneName"], $req["phenoType"]);
	}	
	// gets the Criteria evaluation result
	else if($function == "getACMGEvaluation")
	{
		// get a JSON encoded list of the Criteria evaluation source data
		doGetACMGEvaluation($req["VariantID"], $req["DONOR_CODE"]);
	}	
	// gets the list of disease modes
	else if($function == "getDiseaseModeTypes")
	{
		// get a JSON encoded list of the disease modes
		doGetDiseaseModeTypes();
	}	
	// updates the gene/phenotype mode
	else if($function == "updateGenePhenoTypeData")
	{		
		// update the gene/phenotype curation data
		doUpdateGenePhenoTypeData($req["geneName"], $req["PhenoType"], $req["GeneDiseaseModeID"], $req["PhenoComment"]);
	}	
	// gets the ACMG rule evaluation data
	else if($function == "getACMGRuleEvaluationData")
	{
		// get a JSON encoded list of evidence data
		doGetACMGEvidenceReviewData($req["VariantID"], $req["DONOR_CODE"], $req["ACMGCriteriaID"]);
	}
	else if($function == "updateACMGClassificationData")
	{
		// get a JSON encoded list of evidence data
		doUpdateACMGClassificationData($req);		
	}
	else if($function == "getACMGClassificationData")
	{
		// get a JSON encoded list of classification data
		doGetACMGClassificationData($req["VariantID"], $req["DONOR_CODE"]);		
	}
	// else catch all. unrecognized command
	else
	{
		// if we get here we got an unknown request
		// send the data to the page
		echo '{"error": "Unrecognized data request."}';

		// put something in the log
		error_log("AjaxUtil.php Error unrecognized data request: " . $function);
		
		// discontinue processing
		die();
	}

	/**
	 * get a JSON encoded list of gene curation data
	 *
	 */
	function doGetGeneCurationData($geneName)
	{
		// did we get a valid gened name
		if(isset($geneName) && !empty($geneName))
		{
			// create the SQL
			$sql = "EXEC dbo.[GetGeneCurationData] @GeneName = ?";
	
			// create a new data access object
			$db = new DAO();
	
			// assign the SQL
			$db->setSQL($sql);
	
			// add in the select params
			$params = array($geneName);
	
			// set the params
			$db->setParams($params);
	
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
	
					do_error_log(print_r($data, true));
	
					// return the data JSON formatted
					echo '{"data":' . json_encode($data) . '}';
				}
				else
					echo '{"error": "No data"}';
			}
			else
				echo '{"error": "doGetGeneCurationData() - SQL execution error"}';
		}
		else
			echo '{"error": "doGetGeneCurationData() - Missing input data"}';
	
		// terminate the data stream
		die();
	}	
	
	/**
	 * get a JSON encoded list of gene curation data by Name and Type
	 *
	 */
	function doGetGeneCurationDataByNameType($geneName, $phenoType)
	{
		// did we get a valid gened name
		if(isset($geneName) && !empty($geneName) && isset($phenoType) && !empty($phenoType))
		{
			// create the SQL
			$sql = "EXEC dbo.[GetGeneCurationDataByNameType] @geneName = ?, @phenoType = ?";
	
			// create a new data access object
			$db = new DAO();
	
			// assign the SQL
			$db->setSQL($sql);
	
			// add in the select params
			$params = array($geneName, $phenoType);
	
			// set the params
			$db->setParams($params);
	
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
	
					do_error_log(print_r($data, true));
	
					// return the data JSON formatted
					echo '{"data":' . json_encode($data) . '}';
				}
				else
					echo '{"error": "No data"}';
			}
			else
				echo '{"error": "doGetGeneCurationDataByNameType() - SQL execution error"}';
		}
		else
			echo '{"error": "doGetGeneCurationDataByNameType() - Missing input data"}';
	
		// terminate the data stream
		die();
	}
	
	/**
	 * get a JSON encoded list of gene disease mode source data
	 *
	 */
	function doGetGeneDiseaseSourceData($geneDiseaseModeID)
	{
		// did we get valid gene name and disease mode
		if(isset($geneDiseaseModeID) && !empty($geneDiseaseModeID))
		{
			// create the SQL
			$sql = "EXEC dbo.[GetGeneDiseaseSourceData] @GeneDiseaseModeID = ?";
	
			// create a new data access object
			$db = new DAO();
	
			// assign the SQL
			$db->setSQL($sql);
	
			// add in the select params
			$params = array($geneDiseaseModeID);
	
			// set the params
			$db->setParams($params);
	
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
	
					do_error_log(print_r($data, true));
	
					// return the data JSON formatted
					echo '{"data":' . json_encode($data) . '}';
				}
				else
					echo '{"error": "No data"}';
			}
			else
				echo '{"error": "doGetGeneDiseaseSourceData() - SQL execution error"}';
		}
		else
			echo '{"error": "doGetGeneDiseaseSourceData() - Missing input data"}';
	
		// terminate the data stream
		die();
	}
	
	/**
	 * gets the notes for this allele
	 * 
	 */
	function doGetAlleleNotes($locvarid)
	{
		// did we get a valid locvar ID
		if(isset($locvarid) && !empty($locvarid) && $locvarid != 'undefined')
		{					
			// create the SQL
			$sql = "SELECT DISTINCT Note FROM AnalysisNote WHERE loc_var_id=" . $locvarid;
			
			// create a new data access object
			$db = new DAO();
	
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
			
					do_error_log(print_r($data, true));
					
					// return the data JSON formatted
					$retVal = '{"data":' . json_encode($data) . '}';
				}
				else
					$retVal = '{"warning": "doGetAlleleNotes() - No data"}';
			}
			else
				$retVal = '{"error": "doGetAlleleNotes() - SQL execution error"}';
		}
		else 
			$retVal = '{"error": "doGetAlleleNotes() - No locvar passed in"}';					
		
		// return the data to the caller
		echo $retVal;
		
		// terminate the data stream
		die();		
	}
	
	function do_error_log($text)
	{
		//error_log($text);
	}
	
	/**
	 * gets the evidence data for the passed in loc var ID
	 *  
	 */
	function doGetEvidenceFromSession($geneName)
	{
		// init a evidence data element
		$evidence = null;
		
		// get the user info object from the session
		$userObj = getUserSessionObj();
		
		// include the evidnce object
		include_once("../DecisionSupport/Evidence.php");
		
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
			echo '{"error": "No data"}';				
		}

		// terminate the data stream
	 	die();
	}
	
	/**
	 * Sets the evidence data for the passed in loc var ID
	 *
	 */
	function doSetEvidenceToSession($geneName, $name, $type, $value)
	{
		// init a evidence data element
		$evidence = null;
		
		// init a index
		$i = 0;
		
		// get the user info object from the session
		$userObj = getUserSessionObj();
		
		// include the evidnce object
		include_once("../DecisionSupport/Evidence.php");
		
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
	 * get a JSON encoded list of evidence data
	 * 
	 */
	function doGetAdhocEvidenceData($VariantID, $ACMGCriteriaTypeID)
	{
		// did we get a valid locvar ID
		if(isset($VariantID) && !empty($VariantID) && isset($ACMGCriteriaTypeID) && !empty($ACMGCriteriaTypeID))
		{
			// create the SQL
			$sql = "EXEC dbo.[GetAdhocEvidenceData] @VariantID = ?, @ACMGCriteriaTypeID= ?";
				
			// create a new data access object
			$db = new DAO();
		
			// assign the SQL
			$db->setSQL($sql);
				
			// add in the select params
			$params = array($VariantID, $ACMGCriteriaTypeID);
				
			// set the params
			$db->setParams($params);
		
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
						
					do_error_log(print_r($data, true));
						
					// return the data JSON formatted
					echo '{"data":' . json_encode($data) . '}';
				}
				else
					echo '{"error": "No data"}';
			}
			else
				echo '{"error": "doGetAdhocEvidenceData() - SQL execution error"}';
		}
		else
			echo '{"error": "doGetAdhocEvidenceData() - Missing input data"}';
		
		// terminate the data stream
		die();
	}
	
	/**
	 * insert a JSON encoded list of evidence data
	 * 
	 */
	function doInsertAdhocEvidenceData($req)
	{
		// did we get a valid locvar ID
		if(isset($req) && !empty($req))
		{
			// create the SQL
			$sql = "EXEC dbo.[InsertAdhocEvidenceData]
						@VariantID = ?,
						@ACMGCriteriaTypeID = ?,
						@Name = ?,
						@Value = ?,
						@Source = ?,
						@Comment = ?";
				
			// create a new data access object
			$db = new DAO();
		
			// assign the SQL
			$db->setSQL($sql);
				
			// set the params
			$params = array($req["VariantID"],
						$req["ACMGCriteriaTypeID"],
						$req["Name"],
						$req["Value"],
						$req["Source"],
						$req["Comment"]
			);

			// set the params
			$db->setParams($params);
				
			// execute the SQL
			if(!$db->execute())
				echo '{"error": "doInsertAdhocEvidenceData() - SQL execution error"}';
		}
		else
			echo '{"error": "doInsertAdhocEvidenceData() - Missing input data"}';
		
		// terminate the data stream
		die();
	}	
	
	/**
	 * insert a JSON encoded list of gene/phenotype data
	 * 
	 */
	function doUpdateGenePhenoTypeData($geneName, $phenoType, $geneDiseaseModeID, $comment)
	{
		// did we get a valid locvar ID
		if(isset($geneName) && !empty($geneName) && isset($phenoType) && !empty($phenoType) && isset($geneDiseaseModeID) && !empty($geneDiseaseModeID) && isset($comment) && !empty($comment))
		{
			// create the SQL
			$sql = "EXEC dbo.[UpdateGenePhenoTypeData]
						@geneName = ?,
						@phenoType = ?,
						@geneDiseaseModeID = ?,
						@comment = ?";
				
			// create a new data access object
			$db = new DAO();
		
			// assign the SQL
			$db->setSQL($sql);
				
			// set the params
			$params = array($geneName, $phenoType, $geneDiseaseModeID, $comment);

			// set the params
			$db->setParams($params);
				
			// execute the SQL
			if(!$db->execute())
				echo '{"error": "doUpdateGenePhenoTypeData() - SQL execution error"}';
		}
		else
			echo '{"error": "doUpdateGenePhenoTypeData() - Missing input data"}';
		
		// terminate the data stream
		die();
	}	
	
	/**
	 * gets the gets the Disease Mode Types
	 *
	 */
	function doGetDiseaseModeTypes()
	{
		// create the SQL
		$sql = "EXEC dbo.[GetDiseaseModeTypes]";
	
		// create a new data access object
		$db = new DAO();
	
		// assign the SQL
		$db->setSQL($sql);
	
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
	
				do_error_log(print_r($data, true));
	
				// return the data JSON formatted
				echo '{"data":' . json_encode($data) . '}';
			}
			else
				echo '{"error": "No data"}';
		}
		else
			echo '{"error": "doGetDiseaseModeTypes() - SQL execution error"}';
	
		// terminate the data stream
		die();
	}
	
	/**
	 * gets the gets the Disease Mode Types
	 * 
	 */
	function doGetEvidenceData($VariantID)
	{
		// did we get a valid locvar ID
		if(isset($VariantID) && !empty($VariantID))
		{
			// create the SQL
			$sql = "EXEC dbo.[GetEvidenceData] @VariantID = ?";
			
			// create a new data access object
			$db = new DAO();
	
			// assign the SQL
			$db->setSQL($sql);
			
			$params = array($VariantID);
			
			// set the params
			$db->setParams($params);
		
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
			
					do_error_log(print_r($data, true));
					
					// return the data JSON formatted
					echo '{"data":' . json_encode($data) . '}';
				}
				else
					echo '{"error": "No data"}';				
			}
			else
				echo '{"error": "doGetEvidenceData() - SQL execution error"}';				
		}
		else
			echo '{"error": "doGetEvidenceData() - Missing input data"}';
		
		// terminate the data stream
		die();
	}	
	
	/**
	 * updates the evidence for the variant/location
	 * 
	 */
	function doUpdateEvidenceData($req)
	{
		// did we get a valid locvar ID
		if(isset($req) && !empty($req))
		{						
			// create the SQL
			$sql = "EXEC dbo.[UpdateEvidenceData]
						@VariantID = ?,
						@dbSNP = ?,
						@ThousandG = ?,
						@ExACFreq = ?,
						@ClinGenClassification = ?,
						@Comment = ?";
			
			// create a new data access object
			$db = new DAO();
	
			// assign the SQL
			$db->setSQL($sql);
			
			$params = array($req["VariantID"], 
						$req["dbSNPfrequency"], 
						$req["1000Gfrequency"], 
						$req["ExACfrequency"], 
						$req["ClinGenClassification"], 
						$req["Comment"]);
			
			// set the params
			$db->setParams($params);
			
			// execute the SQL
			if(!$db->execute())
				echo '{"error": "doUpdateEvidenceData() - SQL execution error"}';
		}
		else
			echo '{"error": "doUpdateEvidenceData() - Missing input data"}';
		
		// terminate the data stream
		die();
	}
	
	/**
	 * gets the ACMG data Criteria satisfied for the particpant
	 * 
	 * @param unknown $VariantID
	 * @param unknown $DONOR_CODE
	 */
	function doGetACMGEvidenceReviewParentData($VariantID)
	{
		// did we get a valid locvar ID
		if(isset($VariantID) && !empty($VariantID))
		{
			// create the SQL
			$sql = "EXEC dbo.[GetACMGEvidenceReviewParentData] @VariantID='" . $VariantID . "'";
		
			// create a new data access object
			$db = new DAO();
	
			// assign the SQL
			$db->setSQL($sql);
		
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
						
					do_error_log(print_r($data, true));
						
					// return the data JSON formatted
					echo '{"data":' . json_encode($data) . '}';
				}
				else
					echo '{"error": "No data"}';
			}
			else
				echo '{"error": "doGetACMGEvidenceReviewParentData() - SQL execution error"}';
		}
		else
			echo '{"error": "doGetACMGEvidenceReviewParentData() - Missing input data"}';
		
		// terminate the data stream
		die();
	}
	
	/**
	 * gets the ACMG Criteria evaluation data for the particpant
	 * 
	 * @param unknown $VariantID
	 */
	function doGetACMGCriteriaEvalParentData($VariantID)
	{
		// did we get a valid locvar ID
		if(isset($VariantID) && !empty($VariantID))
		{
			// create the SQL
			$sql = "EXEC dbo.[GetACMGCriteriaEvalParentData] @VariantID='" . $VariantID . "'";
		
			// create a new data access object
			$db = new DAO();
	
			// assign the SQL
			$db->setSQL($sql);
		
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
						
					do_error_log(print_r($data, true));
						
					// return the data JSON formatted
					echo '{"data":' . json_encode($data) . '}';
				}
				else
					echo '{"error": "No data"}';
			}
			else
				echo '{"error": "doGetACMGCriteriaEvalParentData() - SQL execution error"}';
		}
		else
			echo '{"error": "doGetACMGCriteriaEvalParentData() - Missing input data"}';
		
		// terminate the data stream
		die();
	}
	
	/**
	 * gets the ACMG data Criteria satisfied for the particpant
	 * 
	 * @param unknown $VariantID
	 * @param unknown $DONOR_CODE
	 */
	function doGetACMGEvidenceReviewData($VariantID, $DONOR_CODE, $ACMGCriteriaID=null)
	{
		// did we get a valid locvar ID
		if(isset($VariantID) && !empty($VariantID) && isset($DONOR_CODE) && !empty($DONOR_CODE))
		{
			// create the SQL
			$sql = "EXEC dbo.[GetACMGEvidenceReviewData] @DONOR_CODE='" . $DONOR_CODE . "', @VariantID='" . $VariantID . "'";
			
			// append the ID if we have it
			if(!empty($ACMGCriteriaID))
				$sql .= ", @ACMGCriteriaID=" . $ACMGCriteriaID;
			
			// create a new data access object
			$db = new DAO();
	
			// assign the SQL
			$db->setSQL($sql);
		
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
						
					do_error_log(print_r($data, true));
						
					// return the data JSON formatted
					echo '{"data":' . json_encode($data) . '}';
				}
				else
					echo '{"error": "No data"}';
			}
			else
				echo '{"error": "doGetACMGEvidenceReviewData() - SQL execution error"}';
		}
		else
			echo '{"error": "doGetACMGEvidenceReviewData() - Missing input data"}';
		
		// terminate the data stream
		die();
	}
	
	/**
	 * gets the ACMG classification data for the variant/particpant
	 * 
	 * @param unknown $VariantID
	 * @param unknown $DONOR_CODE
	 */
	function doGetACMGClassificationData($VariantID, $DONOR_CODE)
	{
		// did we get a valid locvar ID
		if(isset($VariantID) && !empty($VariantID) && isset($DONOR_CODE) && !empty($DONOR_CODE))
		{
			// create the SQL
			$sql = "EXEC dbo.[GetACMGClassificationData] @DONOR_CODE='" . $DONOR_CODE . "', @VariantID='" . $VariantID . "'";
						
			// create a new data access object
			$db = new DAO();
	
			// assign the SQL
			$db->setSQL($sql);
		
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
						
					do_error_log(print_r($data, true));
						
					// return the data JSON formatted
					echo '{"data":' . json_encode($data) . '}';
				}
				else
					echo '{"error": "No data"}';
			}
			else
				echo '{"error": "doGetACMGClassificationData() - SQL execution error"}';
		}
		else
			echo '{"error": "doGetACMGClassificationData() - Missing input data"}';
		
		// terminate the data stream
		die();
	}
	
	/**
	 * gets the ACMG data Criteria satisfied for the particpant
	 *
	 * @param unknown $VariantID
	 * @param unknown $DONOR_CODE
	 * @param unknown $ACMGCriteriaID
	 */
	function doGetACMGEvidenceReviewCriteriaData($VariantID, $DONOR_CODE, $ACMGCriteriaID)
	{
		// did we get a valid locvar ID
		if(isset($VariantID) && !empty($VariantID) && isset($DONOR_CODE) && !empty($DONOR_CODE) && isset($ACMGCriteriaID) && !empty($ACMGCriteriaID))
		{
			// create the SQL
			$sql = "EXEC dbo.[GetACMGEvidenceReviewCriteriaData] @DONOR_CODE='" . $DONOR_CODE . "', @VariantID='" . $VariantID . "', @ACMGCriteriaID=" . $ACMGCriteriaID;
	
			// create a new data access object
			$db = new DAO();
	
			// assign the SQL
			$db->setSQL($sql);
	
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
	
					do_error_log(print_r($data, true));
	
					// return the data JSON formatted
					echo '{"data":' . json_encode($data) . '}';
				}
				else
					echo '{"error": "No data"}';
			}
			else
				echo '{"error": "doGetACMGEvidenceReviewCriteraData() - SQL execution error"}';
		}
		else
			echo '{"error": "doGetACMGEvidenceReviewCriteriaData() - Missing input data"}';
	
		// terminate the data stream
		die();
	}
	
	
	/**
	 * gets the ACMG Criteria Assessment for the variant
	 * 
	 * @param unknown $VariantID
	 * @param unknown $DONOR_CODE
	 */
	function doGetCriteriaAssessmentData($VariantID)
	{
		// did we get a valid locvar ID
		if(isset($VariantID) && !empty($VariantID))
		{
			// create the SQL
			$sql = "EXEC dbo.[GetACMGCriteriaAssessment] @VariantID='" . $VariantID . "'";
		
			// create a new data access object
			$db = new DAO();
	
			// assign the SQL
			$db->setSQL($sql);
		
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
						
					do_error_log(print_r($data, true));
						
					// return the data JSON formatted
					echo '{"data":' . json_encode($data) . '}';
				}
				else
					echo '{"error": "No data"}';
			}
			else
				echo '{"error": "doGetCriteriaAssessmentData() - SQL execution error"}';
		}
		else
			echo '{"error": "doGetCriteriaAssessmentData() - Missing input data"}';
		
		// terminate the data stream
		die();
	}	
	
	/**
	 * gets the evidence types
	 *
	 */
	function doGetEvidenceTypes($req)
	{
		// create the SQL
		$sql = "EXEC dbo.[GetEvidenceTypes]";
			
		// did we get a ACMG criteria ID
		if(isset($req["ACMGCriteriaID"]) && !empty($req["ACMGCriteriaID"]))
			$sql .= "@ACMGCriteriaID=" . $req["ACMGCriteriaID"];
		
		// create a new data access object
		$db = new DAO();

		// assign the SQL
		$db->setSQL($sql);
			
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
		
				do_error_log(print_r($data, true));
				
				// return the data JSON formatted
				echo '{"data":' . json_encode($data) . '}';
			}
			else
				echo '{"error": "No data"}';				
		}
		else
			echo '{"error": "doGetEvidenceTypes() - SQL execution error"}';
	
		// terminate the data stream
		die();
	}
	
	/**
	 * gets more evidence for the variant/location
	 *
	 */
	function doGetMoreEvidenceData($VariantID)
	{
		// did we get a valid locvar ID
		if(isset($VariantID) && !empty($VariantID))
		{
			// create the SQL
			$sql = "EXEC dbo.[GetMoreEvidenceData] @VariantID = ?";
				
			// create a new data access object
			$db = new DAO();
	
			// assign the SQL
			$db->setSQL($sql);
				
			$params = array($VariantID);
				
			// set the params
			$db->setParams($params);
	
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
						
					do_error_log(print_r($data, true));
						
					// return the data JSON formatted
					echo '{"data":' . json_encode($data) . '}';
				}
				else
					echo '{"error": "No data"}';
			}
			else 
				echo '{"error": "doGetMoreEvidenceData() - SQL execution error"}';
		}
		else
			echo '{"error": "doGetMoreEvidenceData() - Missing input data"}';
	
		// terminate the data stream
		die();
	}
	
	/**
	 * updates the evidence for the variant/location
	 *
	 */
	function doUpdateMoreEvidenceData($req)
	{
		// did we get a valid locvar ID
		if(isset($req) && !empty($req))
		{			
			// validate the data value
			$validated = validateDataValue($req["EvidenceTypeID"], $req["inDataValue"]);
			
			// did the data validate ok
			if($validated[0])
			{
				// create the SQL
				$sql = "EXEC dbo.[UpdateMoreEvidenceData]
							@VariantID = ?,
							@EvidenceTypeID = ?,
							@Value = ?,
							@Source = ?,
							@Comment = ?,
							@TryUpdate = ?";
					
				// create a new data access object
				$db = new DAO();
		
				// assign the SQL
				$db->setSQL($sql);
	
				// assign the param data
				$params = array($req["VariantID"],
								$req["EvidenceTypeID"],
								$req["inDataValue"],
								$req["inDataSource"],
								$req["inDataComment"],
								$req["tryUpdate"]
				);
					
				// set the params
				$db->setParams($params);
					
				// execute the SQL
				if(!$db->execute())
					echo '{"error": "doUpdateMoreEvidenceData() - SQL execution error"}';
			}
			else
				echo '{"error": "' . $validated[1] . '"}';
		}
		else
			echo '{"error": "doUpdateMoreEvidenceData() - Missing input data"}';
	
		// terminate the data stream
		die();
	}
	
	/**
	 * validates the evience passed in
	 * 
	 * @param unknown $EvidenceTypeID
	 * @param unknown $inDataValue
	 */
	function validateDataValue($EvidenceTypeID, $inDataValue)
	{		
		// do we have data tyo evaluate
		if(isset($inDataValue))
		{
			// create some storage for the validation stuff
			$retVal[0] = false;
			$retVal[1] = "";
			$lowerRange = null;
			$upperRange = null;
			
			// get the validation ifo for the evidence type
			$sql = "EXEC dbo.[GetACMGEvidenceValidationInfo] @EvidenceTypeID=" . $EvidenceTypeID;
				
			// create a new data access object
			$db = new DAO();
	
			// assign the SQL
			$db->setSQL($sql);
	
			// execute the SQL
			if($db->execute())
			{
				// did we get some rows
				if(sqlsrv_has_rows($db->getResultSet()))
				{
					// validate the data against the Criteria(s)
					while($row = sqlsrv_fetch_array($db->getResultSet(), SQLSRV_FETCH_ASSOC))
					{
						switch($row["DataTypeName"])
						{
							// basically anything goes for strings
							case 'String':
								$filter = '';
								$retVal[1] = '';
								break;
									
							case 'Integer':
								$filter = FILTER_VALIDATE_INT;
								$lowerRange = intval($row["LowerRange"]);
								$upperRange = intval($row["UpperRange"]);
								$retVal[1] = 'Not a valid value (integer)';
								break;
									
							case 'Float':
								$filter = FILTER_VALIDATE_FLOAT;
								$lowerRange = floatval($row["LowerRange"]);
								$upperRange = floatval($row["UpperRange"]);								
								$retVal[1] = 'Not a valid value (float)';
								break;
									
							case 'URL':
								$filter = FILTER_VALIDATE_URL;
								$retVal[1] = 'Not a valid value (URL)';
								break;
									
							case 'File':
								$filter = '';
								$retVal[1] = '';
								break;
									
							case 'Boolean':
								$filter = FILTER_VALIDATE_BOOLEAN;
								$retVal[1] = 'Not a valid value (boolean)';
								break;
									
							default:
								$retVal[1] = "Unexpected validation data type";
								return $retVal;
								break;								
						}
						
						// check the validation
						if($filter != '')
						{
							// make the check
							$valCheck = filter_var($inDataValue, $filter, array('flags' => FILTER_NULL_ON_FAILURE));
							
							// if the data is of the correct type
							if(!isset($valCheck))
							{
								// set a failure flag
								$retVal[0] = false;
							}
							// everything else gets a range check
							else
							{
								// is this a boolean
								if($filter == FILTER_VALIDATE_BOOLEAN)
									$retVal[0] = true;
								// do we have range numbers 
								else if(isset($lowerRange) && isset($upperRange))
								{
									// is the value in range
									if($inDataValue < $lowerRange || $inDataValue > $upperRange)
									{
										$retVal[0] = false;
										$retVal[1] = "Value not in range";
									}					
									else 
										$retVal[0] = true;	
								}
								else
									$retVal[0] = true;
							}
						}
						// no validation gets a pass
						else 
							$retVal[0] = true;
					}
				}
				else
				{
					$retVal[1] = "No validation parameters found";
				}
			}	
		}
		else
		{
			$retVal[0] = true;
			$retVal[1] = "No data to validate";				
		}
					
		// return to the caller
		return $retVal;
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
		// did we get a valid donor code
		if(isset($DonorCode) && !empty($DonorCode) && $DonorCode != 'undefined')
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
		}
		else
			$retVal = '{"error": "doGetParticipant() - Invalid donor code passed in"}';
		
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
		// did we get a valid donor code
		if(isset($DonorCode) && !empty($DonorCode) && $DonorCode != 'undefined')
		{
			// set the request params
			$AnalysisType = 2;		// the type of analysis results (Dx=2 vs. incidental=1)
			$roleID = 22;			// the role of the user (22 is admin/everything)
			$type = 2;				// the type of results (parent rows=1 vs transcript rows=2)
			$geneID = 'No filter';	// filter on a specific gene in the results
			$FilterID = -1;			// the type of specific bin analysis result (specific Dx code vs. specific incidental code)
	
			// create the SQL
			$sql = "EXEC dbo.GetAnalysisResults @DONOR_CODE = '" . $DonorCode . "', @AnalysisType=" . $AnalysisType . ", @Role=" . $roleID . ", @type=". $type . ", @geneID='". $geneID . "', @FilterID=" . $FilterID;
	
			// create a new data access object
			$db = new DAO();
	
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
					
					do_error_log(print_r($data, true));
				}
				else
					$retVal = '{"error": "doGetParticipantTranscripts() - No data"}';
			}
			else
				$retVal = '{"error": "doGetParticipantTranscripts() - SQL execution error"}';
		}		
		else
			$retVal = '{"error": "doGetParticipantTranscripts() - Invalid donor code passed in"}';
				
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
		// did we get a valid donor code
		if(isset($DonorCode) && !empty($DonorCode) && $DonorCode != 'undefined')
		{
			// set the request params
			$AnalysisType = 2;		// the type of analysis results (Dx=2 vs. incidental=1)
			$roleID = 22;			// the role of the user (22 is admin/everything)
			$type = 1;				// the type of results (parent rows=1 vs transcript rows=2)
			$geneID = 'No filter';	// filter on a specific gene in the results
			$FilterID = -1;			// the type of specific bin analysis result (specific Dx code vs. specific incidental code)
	
			// create the SQL
			$sql = "EXEC dbo.GetAnalysisResults @DONOR_CODE = '" . $DonorCode . "', @AnalysisType=" . $AnalysisType . ", @Role=" . $roleID . ", @type=". $type . ", @geneID='". $geneID . "', @FilterID=" . $FilterID;
	
			// create a new data access object
			$db = new DAO();
	
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
				$retVal = '{"error": "doGetParticipantVariants() - SQL execution error"}';
				
			//do_error_log(print_r($data, true));
		}		
		else
			$retVal = '{"error": "doGetParticipantVariants() - Invalid donor code passed in"}';
							
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
		// did we get a valid gene name
		if(isset($geneName) && !empty($geneName) && $geneName != 'undefined')
		{
			// parse the config file
			$config = parse_ini_file("Config.ini", 1);
	
			// generate the servie URL with the function name and params
		 	$serviceURL = $config['HearSayREST'][''] . '/' . $geneName;
	
		 	// make the REST call
		 	$data = doRESTCall("doGetGeneProvenance()", $serviceURL);
	
		 	do_error_log($serviceURL . ", " . print_r(json_decode($data), true));
		 	
		 	// send the data to the page
			echo $data;
		}
		else
			echo '{"error": "doGetGeneProvenance() - Invalid gene name passed in"}';
		 		
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
		// did we get a valid gene name
		if(isset($geneName) && !empty($geneName) && $geneName != 'undefined')
		{
			// parse the config file
			$config = parse_ini_file("Config.ini", 1);
	
			// generate the servie URL with the function name and params
			$serviceURL = $config['HearSayREST'][''] . '/' . $geneName;
	
		 	// make the REST call
		 	$data = doRESTCall($serviceURL);
	
		 	do_error_log($serviceURL . ", " . print_r(json_decode($data), true));
		 	
		 	// send the data to the page
			echo $data;
		}
		else
			echo '{"error": "doGetGeneInterval() - Invalid gene name passed in"}';
				
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
		// did we get a valid gene name
		if(isset($geneName) && !empty($geneName) && $geneName != 'undefined')
		{
			// parse the config file
			$config = parse_ini_file("Config.ini", 1);
	
			// generate the servie URL with the function name and params
			$serviceURL = $config['HearSayREST']['CARNACGeneServiceHost'] . 'findByName/' . $geneName;
	
		 	// make the REST call
		 	$data = doRESTCall($serviceURL);
	
		 	do_error_log($serviceURL . ", " . print_r(json_decode($data), true));
		 	
		 	// send the data to the page
			echo $data;
		}
		else
			echo '{"error": "doGetGeneDescription() - Invalid gene name passed in"}';
		
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
		// did we get a valid gene name
		if(isset($geneName) && !empty($geneName) && $geneName != 'undefined')
		{
			// parse the config file
			$config = parse_ini_file("Config.ini", 1);
	
			// generate the servie URL with the function name and params
			$serviceURL = $config['HearSayREST']['CARNACMappedTranscriptServiceHost'] . 'findByGeneName/' . $geneName;
	
		 	// make the REST call
		 	$data = doRESTCall($serviceURL);
	
		 	do_error_log($serviceURL . ", " . print_r(json_decode($data), true));
	 		 
		 	// send the data to the page
			echo $data;
		}
		else
			echo '{"error": "doGetGeneMappedTranscripts() - Invalid gene name passed in"}';
	 	
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
		// did we get a valid gene name
		if(isset($geneName) && !empty($geneName) && $geneName != 'undefined')
		{
			// parse the config file
			$config = parse_ini_file("Config.ini", 1);
	
			// generate the servie URL with the function name and params
			$serviceURL = $config['HearSayREST']['CARNACCanonicalServiceHost'] . 'findByGeneName/' . $geneName;
	
		 	// make the REST call
		 	$data = doRESTCall($serviceURL);
	
		 	do_error_log($serviceURL . ", " . print_r(json_decode($data), true));
		 	
			// send the data to the page
			echo $data;
		}
		else
			echo '{"error": "doGetCanonicalGeneData() - Invalid gene name passed in"}';
							
		// terminate the data stream
		die();
	}

	/**
	 * gets the canonical details for a gene
	 *
	 * @param string $geneName
	 */
	function doGetGenePopFreqData($req)
	{
		// did we get a valid gene name
		if(isset($req["geneName"]) && !empty($req["geneName"]) && $req["geneName"] != 'undefined')
		{			
			// parse the config file
			$config = parse_ini_file("Config.ini", 1);
			
			// init the service url
			$serviceURL = $config['HearSayREST']['CARNACPopFreqHost'];
	
			// no source? use the default
			if(isset($req["source"]) && !empty($req["source"]) && $req["source"] != 'undefined' && isset($req["version"]) && !empty($req["version"]) && $req["version"] != 'undefined')
			{
				// generate the service URL with the function name and params
				$serviceURL .= 'findByGeneNameAndSourceAndVersion/' . $req["geneName"] . '/' . $req["source"] . '/' . $req["version"];
			}
			else 
			{
				// generate the service URL with the function name and params
				$serviceURL .= 'findByGeneName/' . $req["geneName"];
			}

			// make the REST call
		 	$data = doRESTCall($serviceURL);
	
		 	do_error_log($serviceURL . ", " . print_r(json_decode($data), true));
		 	
			// send the data to the page
			echo $data;
		}
		else
			echo '{"error": "doGetGenePopFreqData() - Invalid gene name passed in"}';
							
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
		// did we get a valid gene name
		if(isset($geneName) && !empty($geneName) && $geneName != 'undefined')
		{
			// parse the config file
			$config = parse_ini_file("Config.ini", 1);
	
			// generate the servie URL with the function name and params
			$serviceURL = $config['HearSayREST']['CARNACTranscriptServiceHost'] . 'findByGeneName/' . $geneName;
	
		 	// make the REST call
		 	$data = doRESTCall($serviceURL);
	
		 	do_error_log($serviceURL . ", " . print_r(json_decode($data), true));
	 	
			// send the data to the page
			echo $data;
		}
		else
			echo '{"error": "doGetGeneTranscripts() - Invalid gene name passed in"}';
					 	
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
				do_error_log($err);

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
			do_error_log($e->getMessage());

			// return the exception
			$retVal = '{"error": "' . $e->getMessage() . '"}';
		}

		// return to the caller
		return $retVal;
	}

	/**
	 * updates the Criteria evidence data
	 *
	 */
	function doUpdateACMGEvidenceReviewData($req)
	{
		// did we get a valid locvar ID
		if(isset($req) && !empty($req))
		{
			// create the base SQL
			$sql = "EXEC dbo.[UpdateACMGEvidenceReviewData] @Confidence = ?, @UseEvidence = ?,@DONOR_CODE = ?, @VariantID = ?, @ACMGCriteriaTypeID = ?, @EvidenceTypeID = ?";
				
			// init param variables
			$Confidence = NULL;
			$UseEvidence = NULL;
			$CriteriaAssessment = NULL;
			
			// set the type data and SQL
			if($req["type"] == "Confidence")
				$Confidence = $req["value"];
			else if($req["type"] == "UseEvidence")
				$UseEvidence = $req["value"];
				
			// get the key
			$vals = explode('~', $req["key"]);
			
			// create the params
			$params = array($Confidence, $UseEvidence, $vals[0], $vals[1], $vals[2], $vals[3]);				
							
			// create a new data access object
			$db = new DAO();
	
			// assign the SQL
			$db->setSQL($sql);
								
			// set the params
			$db->setParams($params);
				
			// execute the SQL
			if(!$db->execute())
				echo '{"error": "doUpdateACMGEvidenceReviewData() - SQL execution error"}';
		}
		else
			echo '{"error": "doUpdateACMGEvidenceReviewData() - Missing input data"}';
	
		// terminate the data stream
		die();
	}
	

	/**
	 * updates the AMCG classification data
	 *
	 */
	function doUpdateACMGClassificationData($req)
	{
		// did we get a valid locvar ID
		if(isset($req) && !empty($req))
		{
			// create the base SQL
			$sql = "EXEC dbo.[UpdateACMGClassificationData]
						@VariantID = ?
						,@DONOR_CODE = ?
						,@cntPathogenicVeryStrong = ?
						,@cntPathogenicStrong = ?
						,@cntPathogenicModerate = ?
						,@cntPathogenicSupporting = ?
						,@cntBenignStandAlone = ?
						,@cntBenignStrong = ?
						,@cntBenignSupporting = ?
						,@finalPathogenicClassification = ?
						,@finalBenignClassification = ?
						,@finalClassification = ?
						,@overrideClassification = ?
						";
							
			// create the params
			$params = array(							
					$req["VariantID"]
					,$req["DONOR_CODE"]
					,((isset($req["cntPathogenicVeryStrong"])) ? $req["cntPathogenicVeryStrong"] : NULL)
					,((isset($req["cntPathogenicStrong"])) ? $req["cntPathogenicStrong"] : NULL)
					,((isset($req["cntPathogenicModerate"])) ? $req["cntPathogenicModerate"] : NULL)
					,((isset($req["cntPathogenicSupporting"])) ? $req["cntPathogenicSupporting"] : NULL)
					,((isset($req["cntBenignStandAlone"])) ? $req["cntBenignStandAlone"] : NULL)
					,((isset($req["cntBenignStrong"])) ? $req["cntBenignStrong"] : NULL)
					,((isset($req["cntBenignSupporting"])) ? $req["cntBenignSupporting"] : NULL)
					,((isset($req["finalPathogenicClassification"])) ? $req["finalPathogenicClassification"] : NULL)
					,((isset($req["finalBenignClassification"])) ? $req["finalBenignClassification"] : NULL)
					,((isset($req["finalClassification"])) ? $req["finalClassification"] : NULL)
					,((isset($req["overrideClassification"])) ? $req["overrideClassification"] : NULL)
				);
					
			// create a new data access object
			$db = new DAO();
	
			// assign the SQL
			$db->setSQL($sql);
								
			// set the params
			$db->setParams($params);
				
			// execute the SQL
			if(!$db->execute())
				echo '{"error": "doUpdateACMGClassificationData() - SQL execution error"}';
		}
		else
			echo '{"error": "doUpdateACMGClassificationData() - Missing input data"}';
	
		// terminate the data stream
		die();
	}	
	
	/**
	 * updates the Criteria evidence Criteria data
	 *
	 */
	function doUpdateACMGEvidenceReviewCriteriaData($req)
	{
		// did we get a valid locvar ID
		if(isset($req) && !empty($req))
		{
			// create the base SQL
			$sql = "EXEC dbo.[UpdateACMGEvidenceReviewCriteriaData] @DONOR_CODE = ?, @VariantID = ?, @ACMGCriteriaTypeID = ?";
	
			// check what we are updating
			if($req["type"] == "CriteriaUsage")
				$sql .= ", @CriteriaUsage = ?";
			else
				$sql .= ", @CriteriaOverride = ?";
							
			// get the key
			$vals = explode('~', $req["key"]);
				
			// create the params
			$params = array($vals[0], $vals[1], $vals[2], $req["value"]);
				
			// create a new data access object
			$db = new DAO();
	
			// assign the SQL
			$db->setSQL($sql);
	
			// set the params
			$db->setParams($params);
	
			// execute the SQL
			if(!$db->execute())
				echo '{"error": "doUpdateEvidenceCriteriaData() - SQL execution error"}';
		}
		else
			echo '{"error": "doUpdateEvidenceCriteriaData() - Missing input data"}';
	
		// terminate the data stream
		die();
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
