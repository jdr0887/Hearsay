// create a scale to render the lines of data
var yScale = d3.scale.linear().domain([
    1, 6
]).range([
    20, 100
]);

// declare minimum height
var yMin = 10;

/**
 * renders the variant context region
 */
function renderContextVariant(container) {
  // get the canvas area
  cVariant = d3.select(container);

  // Create a context view
  contextViewVariant = d3.contextViewVariant();

  // add a place for the data
  cVariant.datum(null).call(contextViewVariant);
}

/**
 * renders the variant context region
 */
function renderContextGene(container) {
  // get the canvas area
  cGene = d3.select(container);

  // Create a context view
  contextViewGene = d3.contextViewGene();

  // add a place for the data view
  cGene.datum(null).call(contextViewGene);
}

/**
 * render the variant context information (context view tab 1)
 */
d3.contextViewVariant = function() {
  // Size the region
  margin = {
    top : 10,
    left : 10
  };

  // init the data storage variable
  data = [],

  // Start with empty selections
  svgVariant = d3.select(), gVariant = d3.select();

  /**
   * selection handler for a variant
   */
  function cv(selection) {
    /**
     * selected variant event handler
     */
    selection.each(function(d) {
      // Set the data
      data = d;

      // Select the svg data element, if it exists
      svgVariant = d3.select(this).selectAll("svg").data([
        data
      ]);

      // create and get a reference to the sub area
      gVariant = svgVariant.enter().append("svg");

      // Update the outer dimensions
      svgVariant.attr("width", "100%").attr("height", "100%");

      // Register callbacks
      dispatch.on("select_variant.contextViewVariant", selectVariantDetailsContext);
    });
  }

  /**
   * event handler for variant selection
   */
  function selectVariantDetailsContext(selectedVariantData) {
    // assign the global data variable to the variant data to be rendered
    data = selectedVariantData.selectedVariant;

    // render the variant
    drawVariantDetailsContext();
  }

  /**
   * renders the information for the variant tab
   */
  function drawVariantDetailsContext() {
    // remove any text thats already there
    gVariant.select("text").remove();

    // set the default location of the text
    var x = 10;
    var y = 1;

    // set a text area with the correct properties
    text = gVariant.append("text").datum(data).attr("x", x).attr("font-style", "normal");

    text.append("tspan").attr("x", x).attr("y", yScale(y++)).attr("font-size", "15").text("You have selected a ");

    text.append("a").attr("fill", "blue").attr("xlink:href", function(d) {
      return d.link;
    }).attr("font-size", "15").text("variant");

    text.append("tspan").attr("font-size", "15").text(function(d) {
      return " in gene " + d.geneName;
    });

    text.append("tspan").attr("x", x).attr("y", yScale(y++)).attr("font-size", "12").text(function(d) {
      return "With a computed class of " + (d.computedClass ? d.computedClass + "," : "[Not defined],");
    });

    text.append("tspan").attr("x", x).attr("y", yScale(y++)).attr("font-size", "12").text(function(d) {
      return "With a user defined class of " + (d.userClass ? d.userClass + "," : "[Not defined],");
    });

    text.append("tspan").attr("x", x).attr("y", yScale(y++)).attr("font-size", "12").text(function(d) {
      return "And variant type of " + d.type + ",";
    });

    text.append("tspan").attr("x", x).attr("y", yScale(y++)).attr("font-size", "12").text(function(d) {
      return "With an NCGENES alternate allele frequency of " + d.ncgFreq + ",";
    });

    text.append("tspan").attr("x", x).attr("y", yScale(y++)).attr("font-size", "12").text(function(d) {
      return "And a 1000 Genomes allele frequency of " + d.tgFreq + ",";
    });

    text.append("tspan").text(
        function(d) {
          // were there any transcripts
          if (d.transcripts.length > 0) {
            text.append("tspan").attr("x", x).attr("y", yScale(y++)).attr("font-size", "12").text(
                "Which has intersected with the following gene transcript(s):");

            // for each transcript
            for (var i = 0; i < d.transcripts.length; i++) {
              // create a string of the data
              outStr = "Name: " + d.transcripts[i].transcript + ", Class: " + d.transcripts[i].Class + ", Type: "
                  + d.transcripts[i].loc_type + ", Strand: " + d.transcripts[i].strand + ", intron/exon distance: "
                  + d.transcripts[i].intron_exon_dist + ", Variant effect: " + d.transcripts[i].variant_effect;

              // display the results
              text.append("tspan").attr("x", x + 40).attr("y", yScale(y++)).attr("font-size", "12").text(outStr);
            }
          } else {
            // display the error
            text.append("tspan").attr("x", x).attr("y", yScale(y++)).attr("font-size", "15").attr("fill", "red").text(
                "Intersecting gene transcript(s) not found.");
          }
        });

    // set the height of the area now that we are done with the text
    gVariant.attr("height", yMin + yScale(y) + "px");

    // Add iframe with ucsc genome browser link
    container = d3.select(svgVariant.node().parentNode);

    // remove the frame that displays UCSC data
    container.select("iframe").remove();

    // reload the frame with the targeted variant
    container.append("iframe").datum(data).attr("y", yScale(y++)).attr("width", "100%").attr("height", yMin + yScale(y) + 1000 + "px")
        .attr("src", function(d) {
          return d.ucscLink;
        });
  }

  /**
   * width accessor function
   */
  cv.width = function(_) {
    if (!arguments.length)
      return width;

    width = _;

    return cv;
  };

  /**
   * height accessor function
   */
  cv.height = function(_) {
    if (!arguments.length)
      return height;

    height = _;

    return cv;
  };

  // Return closure
  return cv;
};

/**
 * Placeholder for a context gene view (tab 2)
 */
d3.contextViewGene = function() {
  // Size
  margin = {
    top : 10,
    left : 10
  };

  // init the data storage variable
  data = [],

  // Start with empty selections
  svgGene = d3.select(), gGene = d3.select();

  // Create a closure
  function cg(selection) {
    /**
     * selected item event handler
     */
    selection.each(function(d) {
      // Set the data
      data = d;

      // Select the svg data element, if it exists
      svgGene = d3.select(this).selectAll("svg").data([
        data
      ]);

      // Otherwise create the skeletal chart
      gGene = svgGene.enter().append("svg").attr("width", "100%");

      // Update the outer dimensions
      svgGene.attr("width", "100%").attr("height", "100%");

      // Register callbacks
      dispatch.on("select_gene.contextViewGene", selectTranscriptDetailsContext);
    });
  }

  /**
   * event handler for variant selection gene tab
   */
  function selectTranscriptDetailsContext(selectedTranscriptData) {
    // assign the global data variable from the gene data
    data = selectedTranscriptData.geneData;

    // did we get an error
    if (typeof data["error"] != 'undefined')
      return;

    // init the location of the text
    var x = 10;
    var y = 1;

    // render the gene
    y = drawGeneDetailsContext(x, y);

    // assign the global data variable from the gene data
    data = selectedTranscriptData.transcriptData;

    // did we get an error
    if (typeof data["error"] != 'undefined')
      return;

    // render the transcripts
    drawTranscriptDetailsContext(selectedTranscriptData, x, y);
  }

  /**
   * renders the gene information
   * 
   */
  function drawGeneDetailsContext(x, y) {
    // remove any existing text from the area
    gGene.select("text").remove();

    // did we get an error
    if (data == '')
      return;

    // create the baseline text region
    text = gGene.append("text").datum(data).attr("x", x).attr("font-style", "normal");

    text.append("tspan").text(
        function(d) {
          // did we get a error
          if (typeof d.data[0].error == 'undefined') {
            // output the description
            text.append("tspan").attr("x", x).attr("y", yScale(y++)).attr("font-size", "15").text("Gene: " + d.data[0].name);

            // output the description
            text.append("tspan").attr("x", x).attr("y", yScale(y++)).attr("font-size", "12").text(
                "Gene description: " + d.data[0].description);
          } else {
            // output the error reason
            text.append("tspan").attr("x", x).attr("y", yScale(y++)).attr("font-size", "15").attr("fill", "red").text(
                "Error getting gene details. Message: " + d.data[0].error);
          }
        });

    // return to the caller
    return y + 1;
  }

  /**
   * draws the textual transcript data
   * 
   */
  function drawTranscriptDetailsContext(selectedTranscriptData, x, y) {
    // output the text
    var c = gGene.append("tspan").datum(data);

    // do we have a location to put this data
    if (y == undefined)
      return;

    c.append("tspan").text(
        function(d) {
          // get some allele note details from NCGENES
          alleleNotes = selectedTranscriptData.noteData;

          // did we get a error
          if (alleleNotes["error"] == undefined && alleleNotes["data"] != undefined) {
            // did we get any data
            if (alleleNotes["data"].length > 0) {
              // display the results
              c.append("tspan").attr("x", x).attr("y", yScale(y++)).attr("font-size", "12").text("NCGENES notes for this allele: ");

              // for each transcript
              for (var i = 0; i < alleleNotes["data"].length; i++) {
                // are there any data elements
                if (alleleNotes["data"][i]["Note"] !== undefined && alleleNotes["data"][i]["Note"].length > 0) {
                  // output the result
                  text.append("tspan").attr("x", x + 20).attr("y", yScale(y++)).attr("font-size", "12").text(
                      "Note " + (i + 1) + ": " + alleleNotes["data"][i]["Note"]);
                }
              }

              // output the result
              c.append("tspan").attr("x", x + 20).attr("y", yScale(y++)).attr("font-size", "12").text("End of note data.");

              y++;
            }
          }

          // get some gene details from Hearsay
          transcriptDesc = selectedTranscriptData.transcriptData;

          // did we get a error
          if (typeof transcriptDesc["error"] == 'undefined' && transcriptDesc != '') {
            // did we get any data
            if (transcriptDesc["data"].length > 0) {
              // for each transcript
              for (var i = 0; i < transcriptDesc["data"].length; i++) {
                // are there any data elements
                if (transcriptDesc["data"][i]["alignments"] !== undefined && transcriptDesc["data"][i]["alignments"].length > 0) {
                  // crete the output string
                  var outStr;

                  // display the results
                  c.append("tspan").attr("x", x).attr("y", yScale(y++)).attr("font-size", "12").text(
                      "Transcript mapping for accession: " + transcriptDesc["data"][i].accession);

                  // get a reference to the data
                  tMapData = transcriptDesc["data"][i]["alignments"];

                  // for each mapped transcript
                  for (var j = 0; j < tMapData.length; j++) {
                    // create a output string with the gene stats
                    outStr = CheckNull("Genomic accession: ", tMapData[j].genomicAccession)
                        + CheckNull("Strand type: ", tMapData[j].strandType) + CheckNull(", Genomic start: ", tMapData[j].genomicStart)
                        + CheckNull(", Genomic stop: ", tMapData[j].genomicStop);

                    // output the result
                    c.append("tspan").attr("x", x + 20).attr("y", yScale(y++)).attr("font-size", "12").text(outStr);

                    // sort the transcripts by the region index on the gene
                    tMapData[j]["regions"].sort(function(a, b) {
                      return d3.ascending(a.regionStart, b.regionStart);
                    });

                    // for each region on the gene
                    for (var k = 0; k < tMapData[j]["regions"].length; k++) {
                      // get a reference to the data
                      var rData = tMapData[j]["regions"][k];

                      // create an output string with the transcript stats
                      outStr = CheckNull("Region Type: ", rData.regionType) + CheckNull(", Region start: ", rData.regionStart)
                          + CheckNull(", Region stop: ", rData.regionStop) + CheckNull(", cds start: ", rData.cdsStart)
                          + CheckNull(", cds stop: ", rData.cdsStop) + CheckNull(", Transcript start: ", rData.transcriptStart)
                          + CheckNull(", Transcript stop: ", rData.transcriptStop);

                      // output the result
                      c.append("tspan").attr("x", x + 30).attr("y", yScale(y++)).attr("font-size", "12").text(outStr);
                    }
                  }
                }
              }
            } else {
              // output the error reason
              c.append("tspan").attr("x", x).attr("y", yScale(y++)).attr("font-size", "15").attr("fill", "red").text(
                  "Error getting transcript details. Message: No data returned");
            }
          } else {
            var msg = '';

            if (transcriptDesc == '')
              msg = 'Unknown error';
            else
              msg = transcriptDesc["error"];

            // output the error reason
            c.append("tspan").attr("x", x).attr("y", yScale(y++)).attr("font-size", "15").attr("fill", "red").text(msg);
          }

          // output the result
          c.append("tspan").attr("x", x + 30).attr("y", yScale(y++)).attr("font-size", "12").text("End of transcript data.");
        });

    // set the height of the area now that we are done with the text
    gGene.attr("height", yMin + yScale(y) + "px");
  }

  /**
   * width accessor function
   */
  cg.width = function(_) {
    if (!arguments.length)
      return width;

    width = _;

    return cg;
  };

  /**
   * height accessor function
   */
  cg.height = function(_) {
    if (!arguments.length)
      return height;

    height = _;

    return cg;
  };

  // Return closure
  return cg;
};

/**
 * Check to see if a param should be displayed;
 * 
 * @param label
 * @param dataElement
 * @returns
 */
function CheckNull(label, dataElement) {
  if (typeof dataElement == 'undefined')
    return '';
  else
    return label + dataElement;
}
