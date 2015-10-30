/**
 * this file will contain functionality to consolodate data retreival and 
 * dispatching events for the various UI to consume the data asynchronously.
 */

// create the dispatch events
var dispatch = d3.dispatch(
								// listeners activated when user highlight a variant, e.g. mouseover
								"highlight_variant",
								"unhighlight_variant",
			
								// listeners activated when users click a variant
								"select_variant", 
								"select_gene",
								
								// select variant attributes to display
								"show_variant_attributes",
								
								// listeners activated to render donor demographic and variant list views
								"render_demographics", 
								"render_variant_list", 
								"render_variant_scatter",
								
								// data request listeners
								"demographics_data_listener", 
								"variant_data_listener", 
								"gene_transcript_data_listener", 
								"gene_description_data_listener");

	// create a listener for a donors demographic data request
	dispatch.on("demographics_data_listener", donorDemographicsDataListener);

	// create a listener for a donors variant list data request
	dispatch.on("variant_data_listener", donorVariantDataListener);

	// create a listener for a transcript data request
	dispatch.on("gene_transcript_data_listener", geneTranscriptDataListener);
	
	// create a listener for a variant data request
	dispatch.on("gene_description_data_listener", geneDescriptionDataListener);
	
	
	// Cache current gene data so we don't reload unnecessarily
	var geneTranscriptName = "",
			geneDescriptionName = "",
			geneTranscriptData = {},
			geneDescriptionData = {};
	 	  

	/**
	 * listener that loads the donor demographic data from the database
	 * 
	 * @param selectedVariant
	 */
	function donorDemographicsDataListener(donorCode)
	{
		// returned data set
		var retVal = new Object;
		
		errorDisplay("donorDemographicsDataListener() - getParticipant() request for: " + donorCode, 0);

		// get the participant data
		retVal = launchDataRequest("getParticipant", "DonorCode=" + donorCode);

		// did we get a participant data error
		if (typeof retVal["error"] != 'undefined') 
		{
			var errMsg = "donorDemographicsDataListener() - " + retVal["error"];

			errorDisplay(errMsg);
		}
		// let all registered services know that data is ready to display
		else
		{
			// render the donor demographics area
			dispatch.render_demographics(retVal);
		}
	}
	
	/**
	 * listener that loads the donor variant list and scatter data from the database
	 * 
	 * @param selectedVariant
	 */
	function donorVariantDataListener(donorCode)
	{		
		// returned data set
		var retVal = new Object;
		
		errorDisplay("donorVariantDataListener() - getParticipantVariants() request for: " + donorCode, 0);

		// get the variant data
		retVal.variantListData = launchDataRequest("getParticipantVariants", "DonorCode=" + donorCode);
		
		// did we get a variant list data error
		if (typeof retVal.variantListData["error"] != 'undefined') 
		{
			// show the error
			errorDisplay("donorVariantDataListener().variantListData - " + retVal.variantListData["error"]);  
			
			// no need to continue
			return;
		}
		
		errorDisplay("donorVariantDataListener() - getParticipantTranscripts() request for: " + donorCode, 0);

		// get the transcript data
		retVal.transcriptListData = launchDataRequest("getParticipantTranscripts", "DonorCode=" + donorCode);
		
		// did we get a gene data error
		if (typeof retVal.transcriptListData["error"] != 'undefined') 
			errorDisplay("donorVariantDataListener().transcriptListData - " + retVal.transcriptListData["error"]);  
		// let all registered services know that data is ready to display
		else
		{
			dispatch.render_variant_list(retVal);
			dispatch.render_variant_scatter(retVal);
		}
	}
	
	/**
	 * listener that loads the gene transcript data from the database
	 * 
	 * @param selectedVariant
	 */
	function geneTranscriptDataListener(selectedVariant)
	{
		// is this a new selection
		if (selectedVariant.geneName !== geneTranscriptName) 
		{
			// returned data set
			var retVal = new Object;
			
			errorDisplay("geneTranscriptDataListener() - getGeneDescription() request for: " + selectedVariant.geneName, 0);
			
		 	// get the gene description data
			retVal.geneData = launchDataRequest("getGeneDescription", "geneName=" + selectedVariant.geneName);
	
		 	// did we get a gene data error
			if (typeof retVal.geneData["error"] != 'undefined') 
			{
				// show the error
				errorDisplay("geneTranscriptDataListener().geneData - " + retVal.geneData["error"]);
				
				// no need to continue
				return;
			}
			
			errorDisplay("geneTranscriptDataListener() - getGeneTranscripts() request for: " + selectedVariant.geneName, 0);
	
			// get the gene transcript data
			retVal.transcriptData = launchDataRequest("getGeneTranscripts", "geneName=" + selectedVariant.geneName);
	
			// did we get a transcript data error
			if (typeof retVal.transcriptData["error"] != 'undefined') 
			{
				// show the error
				errorDisplay("geneTranscriptDataListener().transcriptData - " + retVal.transcriptData["error"]);
				
				// no need to continue
				return;
			}
			
			errorDisplay("geneTranscriptDataListener() - getAlleleNotes() request for: " + selectedVariant.locvarid, 0);
			
		 	// get the gene description data
			retVal.noteData = launchDataRequest("getAlleleNotes", "locvarid=" + selectedVariant.locvarid);
	
		 	// did we get a gene data error
			if (typeof retVal.noteData["error"] != 'undefined') 
			{
				// show the error
				errorDisplay("geneTranscriptDataListener().noteData(" + selectedVariant.locvarid + ") - " + retVal.noteData["warning"], 1);
			}
		 	
			// save the selected variant data on the return
			retVal.selectedVariant = selectedVariant;
			
			// let all registered services know that data is ready to display
			dispatch.select_gene(retVal);
	   	
			// Save new gene
			geneTranscriptName = selectedVariant.geneName;
			geneTranscriptData = retVal;
		}
		// else use the cached copy
		else 
		{
			// Same gene, replace variant with new variant
			geneTranscriptData.selectedVariant = selectedVariant;
			
			// let all registered services know that data is ready to display
	   		dispatch.select_gene(geneTranscriptData);
		}
	}

	/**
	 * listener that loads the gene/variant data from the database
	 * 
	 * @param selectedVariant
	 */
	function geneDescriptionDataListener(selectedVariant)
	{
		// check to see if we have this gene in cache
		if (selectedVariant.geneName !== geneDescriptionName) 
		{
			// returned data set
			var retVal = new Object;
			
			errorDisplay("geneDescriptionDataListener() - getGeneDescription() request for: " + selectedVariant.geneName, 0);
	
		 	// get the gene description data
			retVal.geneData = launchDataRequest("getGeneDescription", "geneName=" + selectedVariant.geneName);
	
			// save the selected variant data for the return
			retVal.selectedVariant = selectedVariant;
			
			// did we get a gene data error
			if (typeof retVal.geneData["error"] != 'undefined') 
			{
				errorDisplay("geneDescriptionDataListener().geneData - " + retVal.geneData["error"]);
				
				// no need to continue
				return;
			}
			
			//errorDisplay("geneDescriptionDataListener() - getCanonicalGeneData() request for: " + selectedVariant.geneName, 0);
	
		 	// get the gene description data
			//retVal.canonicalGeneData = launchDataRequest("getCanonicalGeneData", "geneName=" + selectedVariant.geneName);
					
			// did we get a gene data error
			//if (typeof retVal.canonicalGeneData["error"] != 'undefined') 
			//{
				// report the erro, but do not stop processing
			//	errorDisplay("geneDescriptionDataListener().canonicalGeneData - " + retVal.canonicalGeneData["error"]);
			//}
			
			// let all registered services know that data is ready to display
			dispatch.select_variant(retVal);
			
			// Save new gene data
			geneDescriptionName = selectedVariant.geneName;
			geneDescriptionData = retVal;
		}
		else 
		{
			// Same gene, replace variant with new variant
			geneDescriptionData.selectedVariant = selectedVariant;
			
			// let all registered services know that data is ready to display
			dispatch.select_variant(geneDescriptionData)
		}
	}

