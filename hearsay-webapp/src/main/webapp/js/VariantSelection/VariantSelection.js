// declare the variant selection region on the canvas

// variables for the call to get variant results for a participant
// these should eventually be coming in from the UI somewhere as user selectable filter items

// Moved variant data here so list and scatter plot can access the same array
var patientVariants = [];

/**
 * Renders the variant table region
 */
function renderVariantTable(donorCode, container) {
  // load the variant data for the participant
  patientVariants = loadVariantSelectionData(donorCode);

  // Select the container
  var c = d3.select(container);

  // This is necessary because container.clientWidth is not correct until the
  // svg has already been added
  var width = parseInt(c.style("width"), 10);

  // Still need a border
  var border = 18;

  // Create the variant table
  var variantTable = d3.variantTable().width(width - border);

  // Call with the data loaded
  c.datum(patientVariants).call(variantTable);

  // Return the variantTable
  return variantTable;
}

/**
 * Renders the variant list region
 */
function renderVariantList(container) {
  // load the variant data for the participant
  // patientVariants = loadVariantSelectionData(donorCode);

  // Select the container
  var c = d3.select(container);

  // This is necessary because container.clientWidth is not correct until the
  // svg has already been added
  // var width = parseInt(c.style("width"), 10);

  // Still need a border
  // var border = 18;

  // Create the variant list
  var variantList = d3.variantList();

  // Call with the data loaded
  c.datum(patientVariants).call(variantList);

  // Return the variantList
  return variantList;
}

/**
 * Renders the variant scatter plot region
 */
function renderVariantScatterPlot(container) {
  // Select the container
  var c = d3.select(container);

  // Still need a border
  // var border = 18;

  // Create the variant scatter plot
  var variantScatterPlot = d3.variantScatterPlot();

  // Call with the data loaded
  c.datum(patientVariants).call(variantScatterPlot);

  // Return the variantScatterPlot
  return variantScatterPlot;
}

/**
 * Renders the variant legend region
 */
function renderVariantLegend(container) {
  // Select the container
  var c = d3.select(container);

  // This is necessary because container.clientWidth is not correct until the
  // svg has already been added
  // var width = parseInt(c.style("width"), 10);

  // Still need a border
  // var border = 18;

  // Create the variant legend
  var variantLegend = d3.variantLegend();

  // Call with the data loaded
  c.datum([
    0
  ]).call(variantLegend);

  // Return the variantLegend
  return variantLegend;
}

/**
 * gets the variants and their info from the DB into a smaller object for
 * display
 */
function loadVariantSelectionData(donorCode) {
  // get the participant data
  geneVarDataSet = launchDataRequest("getParticipantVariants", "DonorCode=" + donorCode);

  // init the return dataset
  var geneVarData = [];

  // did we get a transcript data error
  if (typeof geneVarDataSet["error"] == 'undefined') {
    // did we get back JSON data
    if (geneVarDataSet != "") {
      // for each variant record returned
      for (var i = 0; i < geneVarDataSet["data"].length; i++) {
        // parse the HGVS info
        hgvs = parseHGVS(geneVarDataSet["data"][i]["HGVSgenomic"]);

        // reload the data into a smaller object
        geneVarData.push({
          parentID : geneVarDataSet["data"][i]["ParentID"],
          geneName : geneVarDataSet["data"][i]["hgnc_gene_text"],
          computedClass : geneVarDataSet["data"][i]["Class"],
          userClass : geneVarDataSet["data"][i]["NewClass"],
          type : geneVarDataSet["data"][i]["type"],
          ncgFreq : geneVarDataSet["data"][i]["ncg_alt_f"],
          variantEffect : geneVarDataSet["data"][i]["variant_effect_rollup"],
          // geneID: geneVarDataSet["data"][i]["gene_id"],
          tgFreq : geneVarDataSet["data"][i]["max_allele_freq"],
          ucscLink : hgvs.ucscLink,
          chromosome : hgvs.chromosome,
          gPos : hgvs.gPos,
          hgvsName : hgvs.hgvsName,
          transcripts : null,
          locvarid : geneVarDataSet["data"][i]["loc_var_id"],
          selected : false
        });
      }
    }

    // storage for the realigned transcript data
    var tmpTransData = [];

    // get the transcript data
    var transDataSet = launchDataRequest("getParticipantTranscripts", "DonorCode=" + donorCode);

    // did we get back JSON data
    if (transDataSet != "") {
      // for each row in the transcript data returned
      for (var j = 0; j < transDataSet["data"].length - 1; j++) {
        // save the data in a flatter array
        tmpTransData.push({
          subID : transDataSet["data"][j]["SubID"],
          transcript : transDataSet["data"][j]["transcr"],
          loc_var_id : transDataSet["data"][j]["loc_var_id"],
          Class : transDataSet["data"][j]["Class"],
          loc_type : transDataSet["data"][j]["loc_type"],
          strand : transDataSet["data"][j]["strand"],
          intron_exon_dist : transDataSet["data"][j]["intron_exon_dist"],
          variant_effect : transDataSet["data"][j]["variant_effect"]
        });
      }
    }

    // put away the transcripts for each gene data element
    for (i = 0; i < geneVarData.length; i++) {
      // get a filtered list of transcripts for the gene
      geneVarData[i].transcripts = tmpTransData.filter(function(d) {
        return (d.subID === geneVarData[i].parentID);
      });
    }
  }

  // return to the caller
  return geneVarData;
}

/**
 * parses the HGVS information from the link passed
 * 
 * @param hgvs
 * @returns chromosome and position
 */
function parseHGVS(hgvs) {
  var findString = null; // string to seach for

  var chrStart = null; // starting string index for the chr
  var chrEnd = null; // ending string index for the chr

  var varStart = null; // starting string index for the range around the
                        // variant
  var varEnd = null; // ending string indexfor the range around the variant
  var newRange = null; // the new range around the variant

  // Find link to ucsc genome browser for this variant
  var ucscLinkStart = null;
  var ucscLink = null;

  // Find chromosome and position
  var chromosome = null;
  var pos = null;
  var endPos = null;

  // find the hgvs name
  var hgvsStart = null;
  var hgvsEnd = null;
  var hgvsName = null;

  // set the search string
  findString = "position=chr";

  // search for the chr string
  chrStart = hgvs.indexOf(findString);

  // did we find it
  if (chrStart !== -1) {
    // Find string position and value of chromosome number
    chrStart = chrStart + findString.length;
    chrEnd = hgvs.indexOf(":", chrStart);
    chromosome = hgvs.slice(chrStart, chrEnd);

    // Find string position and values of the display range around the variant
    varStart = chrEnd + 1;
    varEnd = hgvs.indexOf("-", varStart);
    pos = +hgvs.slice(varStart, varEnd);
    endPos = +hgvs.slice(varEnd + 1, hgvs.indexOf("\"", varEnd));

    // get the new range string
    newRange = (pos - 25) + "-" + (endPos + 25);

    // set the search string
    findString = "href=\"";

    // find it in the string
    ucscLinkStart = hgvs.indexOf(findString) + findString.length;

    // tack on the new range
    ucscLink = hgvs.slice(ucscLinkStart, chrEnd) + ":" + newRange;

    findString = "_blank\">";

    // find it in the string
    hgvsStart = hgvs.indexOf("_blank\">") + findString.length;

    // find the end of it
    hgvsEnd = hgvs.indexOf("<", hgvsStart);

    // tack on the new range
    hgvsName = "NC_" + hgvs.slice(hgvsStart, hgvsEnd);
  }

  // Return object
  return {
    ucscLink : ucscLink,
    chromosome : chromosome,
    gPos : pos,
    hgvsName : hgvsName
  };
}
