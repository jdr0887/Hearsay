(function() {
  /**
   * Main entry point for the rendering of the gene on the surface.
   * 
   * Variables are initialized, ranges are specified at the top.
   * 
   */
  d3.geneBrowser = function() {
    // Size
    var margin = {
      top : 10,
      left : 5,
      bottom : 80,
      right : 5
    }, width = 200, height = 200, innerWidth = function() {
      return width - margin.left - margin.right;
    }, innerHeight = function() {
      return height - margin.top - margin.bottom;
    },

    // Data
    geneName = "", geneDescription = "", geneRegions = [], transcripts = [], activeTranscripts = [], patientVariants = [], geneVariants = [], populationVariants = [],

    // Parameters
    regionMapping = "height", geneOffset = 100, geneHeight = 45, transcriptHeight = 15, collapsibleScale = 1, transcriptSpacing = 10, geneTranscriptSpacing = geneHeight / 2, variantSize = 15, transitionDuration = 500, intronBorder = 20, featureOpacity = 1,

    // Scales
    xScale = d3.scale.linear(), geneMin, geneMax, viewMin, viewMax, yScale = d3.scale.ordinal(), sortOrders, sortBy = "accessionNumber", geneHeightScale = d3.scale
        .linear(), geneColorScale = d3.scale.linear(), transcriptHeightScale = d3.scale.ordinal().domain([
        "UTR5", "UTR3", "UTR", "INTRON", "INTRON_BORDER", "EXON"
    ]), transcriptColorScale = d3.scale.ordinal().domain([
        "UTR5", "UTR3", "UTR", "INTRON", "INTRON_BORDER", "EXON"
    ]),
    /*
     * variantShapeScale = d3.scale.ordinal() .domain(["del", "ins", "snp"])
     * .range(["circle", "cross", "square" ]), // XXX: Copied from
     * d3.variantList, should be shared, or a parameter passed in
     * variantComputedClassScale = d3.scale.ordinal() .domain(["F", "E", "D",
     * "C", "B", "A"]) .range(["#2c7bb6", "#abd9e9", "#ffeda0", "#fdae61",
     * "#f46d43", "#d73027"]), // XXX: Copied from d3.variantList, should be
     * shared, or a parameter passed in variantUserClassScale =
     * d3.scale.ordinal() .domain(["KB", "LB", "VUS", "LP", "KP"])
     * .range(["#2c7bb6", "#abd9e9", "#ffeda0", "#f46d43", "#d73027"]), // XXX:
     * Copied from d3.variantList, should be shared, or a parameter passed in
     * computedClassColor = d3.scale.ordinal() .domain(["A", "B", "C", "D", "E",
     * "F"]) .range(colorbrewer.RdBu[6]),
     */
    variantAttributes = [],

    // Miscellaneous
    maxLabelLength = 0, zoom = d3.behavior.zoom().scaleExtent([
        1, 100
    ]).on("zoom", function() {
      // Constrain panning
      if (xScale.domain()[0] < geneMin) {
        zoom.translate([
            zoom.translate()[0] - xScale(geneMin) + xScale.range()[0], zoom.translate()[1]
        ]);
      } else if (xScale.domain()[1] > geneMax) {
        zoom.translate([
            zoom.translate()[0] - xScale(geneMax) + xScale.range()[1], zoom.translate()[1]
        ]);
      }

      // Update view extent
      updateViewExtent();

      // Draw gene
      drawGene();
    }),

    // Start with empty selections
    svg = d3.select(), svgGene = d3.select();

    /**
     * Create a closure for the gene browser
     */
    function gb(selection) {
      // For each participant in the current selection (typically only one)
      selection.each(function(d) {
        // Copy the variant data
        patientVariants = d;

        // Select the svg element, if it exists
        svg = d3.select(this).selectAll("svg").data([
          0
        ]);

        // Otherwise create the skeletal chart
        var g = svg.enter().append("svg").attr("class", "geneBrowser").append("g").attr("transform",
            "translate(" + margin.left + "," + margin.top + ")");

        // Add child svg for clipping of gene summary and transcript
        svgGene = g.append("svg").attr("x", 200).call(zoom);

        // Add background to capture zoom events
        svgGene.append("rect").attr("width", "100%").attr("height", "100%").style("visibility", "hidden").style("pointer-events", "all");

        // Add groups for correct z order
        var geneSummary = svgGene.append("g").attr("class", "geneSummary");

        geneSummary.append("g").attr("class", "region").attr("clip-path", "url(#clipGene2)");

        geneSummary.append("g").attr("class", "intronCollapse");

        svgGene.append("g").attr("class", "transcript");

        svgGene.append("g").attr("class", "populationVariant");

        var variant = svgGene.append("g").attr("class", "variant").attr("clip-path", "url(#clipGene)");

        variant.append("clipPath").attr("id", "clipRight").append("rect").attr("y", -variantSize).attr("width", variantSize).attr("height",
            variantSize * 2);

        variant.append("g").attr("class", "line");

        variant.append("g").attr("class", "glyph");

        g.append("g").attr("class", "geneSummaryLabel");

        g.append("g").attr("class", "transcriptLabel");

        var geneInfo = g.append("g").attr("class", "geneInfo");

        geneInfo.append("text").attr("class", "name").attr("dy", "0.35em");

        geneInfo.append("polygon").attr("class", "direction");

        geneInfo.append("text").attr("class", "description").attr("x", "48").attr("dy", "2.0em");

        var position = geneInfo.selectAll(".position").data([
            "start", "stop"
        ]).enter().append("g").attr("class", "position").attr("visibility", "hidden");

        position.append("line").attr("class", "line").attr("y2", geneOffset / 2);

        position.append("text").attr("class", "label").attr("dy", "0.5em").attr("text-anchor", function(d) {
          return d === "start" ? "start" : "end";
        });

        geneInfo.append("g").attr("class", "range").attr("visibility", "hidden").append("text").attr("class", "label").attr("dy", "0.5em")
            .attr("text-anchor", "middle");

        // Set the width
        setWidth(width);

        // Register user selection call backs
        dispatch.on("highlight_variant.geneBrowser", highlightVariant);
        dispatch.on("unhighlight_variant.geneBrowser", unhighlightVariant);
        dispatch.on("select_variant.geneBrowser", selectVariant);
        dispatch.on("select_gene.geneBrowser", selectGene);
        dispatch.on("show_variant_attributes.geneBrowser", showVariantAttributes);
      });
    }

    /**
     * Event handler for showing variant attributes
     * 
     */
    function showVariantAttributes(attributes) {
      variantAttributes = attributes;
      drawGene();
    }

    /**
     * Event handler for gene selection
     * 
     */
    function selectGene(selectedVariantData) {
      // Save the transcript info locally
      var transcriptData = selectedVariantData.transcriptData;

      // Did we get an error?
      if (selectedVariantData.geneData == '' || selectedVariantData.transcriptData == '' || typeof transcriptData["error"] != 'undefined'
          || typeof transcriptData["warning"] != 'undefined') {
        return;
      }

      // Set the gene name
      geneName = selectedVariantData.selectedVariant.geneName;

      // Set the gene description
      geneDescription = selectedVariantData.geneData.data[0].description;

      // Get all variants on this gene
      geneVariants = patientVariants.filter(function(d) {
        return d.geneName === geneName;
      });
      geneVariants.sort(function(a, b) {
        return d3.ascending(a.gPos, b.gPos);
      });

      // Create array of mapped transcripts
      createTranscripts(transcriptData.data);

      // Copy to active transcripts
      activeTranscripts = transcripts.slice();

      // Do we have frequency data
      if (selectedVariantData.popFreqData["error"] == undefined && selectedVariantData.popFreqData != '') {
        console.log(selectedVariantData.popFreqData);

        // load up the population variant data
        populationVariants = selectedVariantData.popFreqData.data.map(function(d) {
          // save the data in the expected format
          return {
            gStart : d.position.start,
            gStop : d.position.stop,
            frequency : d.frequency
          };
        });

        // Sort for correct draw order
        populationVariants.sort(function(a, b) {
          return d3.ascending(a.frequency, b.frequency);
        });
      } else {
        errorDisplay("geneBrowser.popFreqData(): Error no frequency data", 1);
        populationVariants = [];
      }

      // Update gene regions
      updateGeneRegions();

      // Remove svg elements
      svg.select(".geneSummary").select(".region").selectAll("rect").remove();
      svg.select(".geneSummary").select(".intronCollapse").selectAll("circle").remove();
      svg.select(".transcript").selectAll("g").remove();
      svg.select(".variant").selectAll(".variant > g").remove();

      // Reset zoom
      zoom.translate([
          0, 0
      ]).scale(1).center(null);

      // Draw
      drawGene();
    }

    /**
     * Event handlers for variant highlighting
     */
    function highlightPolygon(d) {
      // XXX: Copied from drawVariants
      var glyphScale = d3.scale.threshold().domain([
        1
      ]).range([
          variantSize * 0.6, variantSize
      ]);

      var w = glyphScale(d.exonCount + d.intronBorderCount);

      var x1 = -w / 2, x2 = w / 2, y1 = variantSize * 0.75, y2 = y1 + variantSize / 4, y3 = y2 + variantSize / 4;

      var p1 = "0," + y1, p2 = x1 + "," + y2, p3 = x1 + "," + y3, p4 = x2 + "," + y3, p5 = x2 + "," + y2;

      return p1 + " " + p2 + " " + p3 + " " + p4 + " " + p5;
    }

    // Get nucleotide change from HGVS
    function hgvsNucleotideChange(hgvs) {
      // Split genome coordinate from nucleotide changes
      for (var i = hgvs.length - 1; i >= 0; i--) {
        if (!isNaN(+hgvs[i]))
          return hgvs.slice(i + 1);
      }
      return "";
    }

    function highlightVariant(variant) {
      // Select variant
      var highlight = svgGene.select(".variant").selectAll(".variant > g").filter(function(d) {
        return d === variant;
      }).select("g").selectAll(".highlight").data(function(d) {
        return [
          d
        ];
      }).enter().append("g").attr("class", "highlight");

      // Draw polygon
      highlight.append("polygon").attr("points", highlightPolygon).style("fill", "lightgrey").style("stroke", "grey");

      // Draw text
      highlight.append("text").text(function(d) {
        return hgvsNucleotideChange(d.hgvsName);
      }).attr("y", variantSize * 1.25 + 20).style("font-size", "x-small").style("text-anchor", "middle");
    }

    function unhighlightVariant(variant) {
      svgGene.select(".variant").selectAll(".variant > g").filter(function(d) {
        return !d.selected;
      }).select(".highlight").remove();
    }

    /**
     * Event handler for variant selection
     */
    function selectVariant(selectedVariantData) {
      // Save the data locally
      var variant = selectedVariantData.selectedVariant;

      // Unselect variant
      // XXX: Could improve performance by storing selected index/name...
      geneVariants.forEach(function(d) {
        d.selected = false;
      });

      // Save selected data
      variant.selected = true;

      // Remove current selected polygon
      svg.select(".variant").selectAll(".highlight").remove();

      // XXX: Copied from drawVariants
      var glyphScale = d3.scale.threshold().domain([
        1
      ]).range([
          variantSize * 0.6, variantSize
      ]);

      // Select variant
      var highlight = svgGene.select(".variant").selectAll(".variant > g").filter(function(d) {
        return d.selected;
      }).select("g").selectAll(".highlight").data(function(d) {
        return [
          d
        ];
      }).enter().append("g").attr("class", "highlight");

      // Draw polygon
      highlight.append("polygon").attr("points", highlightPolygon).style("fill", "grey").style("stroke", "grey");

      // Draw text
      highlight.append("text").text(function(d) {
        return hgvsNucleotideChange(d.hgvsName);
      }).attr("y", variantSize * 1.25 + 10).style("font-size", "x-small").style("text-anchor", "middle");
    }

    /**
     * Compute the maximum transcript name length
     */
    function computeMaxLabelLength() {
      // Create temporary text
      var gTemp = svg.append("g");

      // Gene summary label
      gTemp.append("g").attr("class", "geneSummaryLabel").append("text").text("Gene summary");

      // Transcripts
      gTemp.append("g").attr("class", "transcriptLabel").selectAll("text").data(activeTranscripts).enter().append("text").text(function(d) {
        return d.name;
      }).attr("class", "label");

      // Compute the maximum length
      var maxLength = d3.max(gTemp.selectAll("text")[0], function(d) {
        return d.getComputedTextLength();
      });

      gTemp.remove();

      return maxLength;
    }

    /**
     * Updates the regions on the gene summary and transcripts
     * 
     */
    function updateGeneRegions() {
      // Refresh transcript data
      refreshTranscripts();

      // Create combined gene regions based on active transcripts
      createGeneRegions();

      // Modify transcripts to add collapsible transcript regions based on gene
      // regions
      addCollapsibleRegions();

      // Update variants for this gene
      updateVariants();

      // Set x scale
      updateXScale();

      // Set height and color scales
      geneHeightScale.domain([
          0, activeTranscripts.length
      ]);
      geneColorScale.domain([
          0, activeTranscripts.length
      ]);

      // Set maximum label length
      maxLabelLength = computeMaxLabelLength();

      // Set the width and height
      yScale.domain(activeTranscripts.map(function(d) {
        return d.name;
      }));

      // compute the height of the region
      computeHeight();

      // set the width of the region
      setWidth(width);
    }

    /**
     * Creates the transcripts
     * 
     */
    function createTranscripts(transcriptData) {
      // Declare a new array for the transcripts
      transcripts = [];

      // Check for transcript data
      if (transcriptData !== undefined && transcriptData.length > 0) {
        // For each transcript
        transcriptData.forEach(function(d) {
          // Did we get any valid alignments
          if (d.alignments === undefined || d.alignments.length == 0) {
            return;
          } else if (d.alignments.length > 1) {
            // console.log("Gene " + geneName + ": Extra mapped transcript"); //
            // XXX: Handle this at some point

            errorDisplay("geneBrowser.createTranscripts(): Gene " + geneName + ", Accession " + d.accession
                + " has an extra mapped transcript.", 1);
          }

          // Declare a new transcript to populate
          var transcript = {
            name : d.accession,
            direction : d.alignments[0].strandType,
            originalRegions : [],
            regions : [],
            originalFeatures : d.features
          };

          // For each alignment in the gene
          if (d.alignments[0].regions !== undefined && d.alignments[0].regions.length > 0) {
            d.alignments[0].regions.forEach(function(d) {
              // Save the transcript info
              transcript.originalRegions.push({
                type : d.regionType,
                start : d.regionStart,
                stop : d.regionStop,
                transcriptStart : d.transcriptStart,
                transcriptStop : d.transcriptStop
              });
            });
          } else {
            errorDisplay("geneBrowser.createTranscripts(): No d.alignments[0].regions data.");
          }

          // Check for feature data
          if (transcript.originalFeatures !== undefined && transcript.originalFeatures.length > 0) {
            // Sort the feature regions
            transcript.originalFeatures.sort(function(a, b) {
              return d3.ascending(a.regionStart, b.regionStart);
            });
          } else {
            errorDisplay("geneBrowser.createTranscripts(): No transcript.originalFeatures data for transcript: " + transcript.name, 1);
          }

          // Check for region data
          if (transcript.originalRegions !== undefined && transcript.originalRegions.length > 0) {
            // Sort the transcript regions
            transcript.originalRegions.sort(function(a, b) {
              return d3.ascending(a.start, b.start);
            });

            // Fix overlapping/separated neighboring regions
            for (var i = 1; i < transcript.originalRegions.length; i++) {
              var r1 = transcript.originalRegions[i - 1];
              var r2 = transcript.originalRegions[i];

              if (r2.start !== r1.stop + 1) {
                // console.log("Gene " + geneName + ": Transcript region
                // error");

                errorDisplay("geneBrowser.createTranscripts(): Gene " + geneName + " transcript region error. Start " + r2.start
                    + " != previous end + 1", 1);

                // Try to fix
                r1.stop = r2.start - 1;
              }
            }

            // Add the transcript to the array
            transcripts.push(transcript);
          } else {
            errorDisplay("geneBrowser.createTranscripts(): No transcript.originalRegions data.");
          }

        });
      } else {
        errorDisplay("geneBrowser.createTranscripts(): No transcript data.");
      }
    }

    /**
     * Refreshes the transcripts
     * 
     */
    function refreshTranscripts() {
      // no gene selected. just return as there is nothing to do. commonly
      // happens on initial page load.
      if (geneName === "") {
        return;
      }

      // Check for transcript data
      else if (transcripts !== undefined && transcripts.length > 0) {
        // For each transcript
        transcripts.forEach(function(d) {
          // Load the original regions
          d.regions = d.originalRegions.map(function(d) {
            return {
              type : d.type,
              start : d.start,
              stop : d.stop,
              transcriptStart : d.transcriptStart,
              transcriptStop : d.transcriptStop
            };
          });

          // Check for regions
          if (d.regions !== undefined && d.regions.length > 0) {
            // Add intron borders that cannot be collapsed for each intron
            d.regions.forEach(function(d, i, a) {
              // Insert borders for introns
              if (d.type === "INTRON") {
                if (d.stop - d.start < intronBorder * 2) {
                  d.regionType = "INTRON_BORDER";
                } else if (intronBorder > 0) {
                  var r1 = {
                    type : "INTRON_BORDER",
                    start : d.start,
                    stop : d.start + intronBorder - 1
                  }, r2 = {
                    type : "INTRON",
                    start : d.start + intronBorder,
                    stop : d.stop - intronBorder
                  }, r3 = {
                    type : "INTRON_BORDER",
                    start : d.stop - intronBorder + 1,
                    stop : d.stop
                  };

                  // save the items in an array
                  a[i] = [
                      r1, r2, r3
                  ];
                }
              }
            });
          } else {
            errorDisplay("geneBrowser.refreshTranscripts(): No d.regions data.");
          }

          // Flatten array
          d.regions = d.regions.reduce(function(a, b) {
            return a.concat(b);
          }, []);
        });
      } else {
        errorDisplay("geneBrowser.refreshTranscripts(): No transcript data.");
      }
    }

    /**
     * Creates the gene regions for rendering
     * 
     */
    function createGeneRegions() {
      // Get the start and stop locations for regions in all transcripts
      var geneLocations = [];

      // No gene selected. Just return as there is nothing to do. Commonly
      // happens on initial page load.
      if (geneName === "") {
        return;
      }
      // Check for transcripts
      else if (activeTranscripts !== undefined && activeTranscripts.length > 0) {
        // For each active transcript
        activeTranscripts.forEach(function(d) {
          d.regions.forEach(function(d) {
            var type = d.type.indexOf("UTR") !== -1 ? "UTR" : d.type;
            // var type = d.type.indexOf("UTR") !== -1 ? "INTRON" : d.type;
            geneLocations.push({
              loc : "start",
              pos : d.start,
              type : type
            });
            geneLocations.push({
              loc : "stop",
              pos : d.stop + 1,
              type : type
            });
          });
        });

        // Sort the start and stop locations
        geneLocations.sort(function(a, b) {
          return d3.ascending(a.pos, b.pos);
        });

        // Generate data for rendering
        geneRegions = [];

        var utrCount = 0, intronCount = 0, intronBorderCount = 0, exonCount = 0, p0 = d3.min(activeTranscripts, function(d) {
          return d.regions[0].start;
        });

        // Check for gene locations
        if (geneLocations !== undefined && geneLocations.length > 0) {
          geneLocations.forEach(function(d) {
            var p1 = d.pos;

            if (p1 != p0) {
              geneRegions.push({
                start : p0,
                stop : p1 - 1,
                utrCount : utrCount,
                intronCount : intronCount,
                intronBorderCount : intronBorderCount,
                exonCount : exonCount
              });

              p0 = p1;
            }

            if (d.type === "UTR") {
              d.loc === "start" ? utrCount++ : utrCount--;
            } else if (d.type === "INTRON") {
              d.loc === "start" ? intronCount++ : intronCount--;
            } else if (d.type === "INTRON_BORDER") {
              d.loc === "start" ? intronBorderCount++ : intronBorderCount--;
            } else {
              d.loc === "start" ? exonCount++ : exonCount--;
            }
          });
        } else {
          errorDisplay("geneBrowser.createGeneRegions(): No gene locations data.");
        }

        // Store the sum of collapsible introns before each region
        // XXX: Use a quantize scale???
        geneRegions.reduce(function(p, c, i) {
          c.collapsibleOffset = p;

          if (c.exonCount === 0 && c.intronBorderCount === 0) {
            p += c.stop - c.start + 1;
          }

          return p;
        }, 0);
      } else {
        errorDisplay("geneBrowser.createGeneRegions(): No active transcripts data.");
      }
    }

    /**
     * Adds collapsible regions where non-exonic regions across transcripts
     * overlap
     * 
     * Also loads the features in the exons.
     * 
     */
    function addCollapsibleRegions() {
      // Find collapsible gene regions with exon counts of 0 (no overlapping
      // exons)
      var collapsible = geneRegions.filter(function(d) {
        return d.exonCount === 0 && d.intronBorderCount === 0;
      });

      // No gene selected. just return as there is nothing to do. commonly
      // happens on initial page load.
      if (geneName === "") {
        return;
      }
      // Check for active transcripts
      else if (activeTranscripts !== undefined && activeTranscripts.length > 0) {
        // Split introns and utrs into collapsible and noncollapsible regions
        activeTranscripts.forEach(function(d) {
          // for each region
          for (var i = 0, j = 0; i < d.regions.length; i++) {
            var tr = d.regions[i];

            // Update gene region
            // XXX: Shouldn't need to check j against length, but some
            // transcripts have overlapping regions...
            while (j < collapsible.length && collapsible[j].stop < tr.start) {
              j++;
            }

            if (j === collapsible.length)
              break;

            var gr = collapsible[j];

            // Check for intersection
            if (gr.start <= tr.start && gr.stop >= tr.stop) {
              // Collapsible contains region, no need to split
              tr.collapsible = true;
            } else if (gr.start > tr.start && gr.start < tr.stop) {
              // Collapsible starts in region, split
              d.regions.splice(i, 0, {
                type : tr.type,
                start : tr.start,
                stop : gr.start - 1,
                collapsible : false
              });

              tr.start = gr.start;
            } else if (gr.start <= tr.start) {
              // Collapsible stops in region, split
              d.regions.splice(i, 0, {
                type : tr.type,
                start : tr.start,
                stop : gr.stop,
                collapsible : true
              });

              tr.start = gr.stop + 1;
            }
          }

          // Combine adjacent introns
          // XXX: CURRENTLY ONLY WORKS IF THERE ARE INTRON BORDERS...
          for (var i = 0; i < d.regions.length; i++) {
            var r1 = d.regions[i];

            if (r1.type === "INTRON_BORDER") {
              // Remove all regions between intron borders (should all be
              // introns), keeping track of collapsible and noncollapsible
              // lengths
              var j = i + 1, nonCollapseLength = 0;

              while (j < d.regions.length && d.regions[j].type != "INTRON_BORDER") {
                var r2 = d.regions[j];

                if (!r2.collapsible)
                  nonCollapseLength += r2.stop - r2.start + 1;

                d.regions.splice(j, 1);
              }

              var r2 = d.regions[j];

              // Split non-collapsible between intron borders
              r1.stop += Math.ceil(nonCollapseLength / 2);
              r2.start -= Math.floor(nonCollapseLength / 2);

              // Add single collapsible intron
              d.regions.splice(j, 0, {
                type : "INTRON",
                start : r1.stop + 1,
                stop : r2.start - 1,
                collapsible : true
              });

              i += 2;
            }
          }

          // Get initial collapsible offset
          var i = 0;

          while (geneRegions[i].stop < d.regions[0].start) {
            i++;
          }

          var startOffset = geneRegions[i].collapsibleOffset;

          // Set collapsible offsets
          d.regions.reduce(function(p, c, i) {
            c.collapsibleOffset = p;

            if (c.collapsible) {
              p += c.stop - c.start + 1;
            }

            return p;
          }, startOffset);

          /*
           * // Set collapsible offsets d.regions.reduce(function(p, c, i) {
           * c.collapsibleOffset = p; if (c.collapsible) p += c.stop - c.start +
           * 1; return p; }, 0);
           */
          /*
           * // Copy collapsible offsets from gene regions for (var i = 0, j =
           * 0; i < d.regions.length; i++) { while (geneRegions[j].stop <
           * d.regions[i].start) j++; d.regions[i].collapsibleOffset =
           * geneRegions[j].collapsibleOffset; }
           */

          // Create the exonic features array
          d.features = [];

          /**
           * Gets the features notes for a exon region. Some of the features
           * ovelap so there could be multiple notes at a single point
           * 
           */
          function getFeatureNotes(features, featureStart, featureStop) {
            // Init the note
            var notes = [];

            // For each feature
            for (var i = 0; i < features.length - 1; i++) {
              // Is this feature in range?
              if ((featureStart >= features[i].regionStart && featureStart < features[i].regionStop)
                  && (featureStop >= features[i].regionStart && featureStop <= features[i].regionStop)) {
                // Add note
                notes.push(features[i].note !== undefined ? features[i].note : "Error: missing feature note.");
              }
            }

            return notes;
          }

          /**
           * Translates the point from the transcript coordinate system to gene
           * coordinate system
           * 
           * string direction - the direction of the strand (PLUS, MINUS) int
           * exonStart - the start of the exon (gene coordinate system) int
           * exonStop - the stop of the exon (gene coordinate system) int
           * transcriptStart - the start of the exon (transcript coordinate
           * system) int transcriptStop - the stop of the exon (transcript
           * coordinate system) int point - the point to translate to the gene
           * coordinate system
           */
          function TranslateToGeneCoord(direction, geneCoordStart, geneCoordStop, transcriptCoordStart, transcriptCoordStop, point) {
            // int the translated point variable
            var translatedPoint = 0;

            // if the lengths are not the same in both coordinate systems its an
            // error
            if (Math.abs(geneCoordStart - geneCoordStop) != Math.abs(transcriptCoordStart - transcriptCoordStop)) {
              errorDisplay("TranslateToG_coord() Error: Gene coordinate range length != Transcript coordinate range length", 1);
            } else {
              // make sure the point to convert is within the range of the exon
              // (all in transcript coordinates)

              // if the point is before the exon in transcript coordinates
              if (point < transcriptCoordStart)
                point = transcriptCoordStart; // the start point is the start of
                                              // the exon
              // else if the point is after the exon in transcript coordinates
              else if (point > transcriptCoordStop)
                point = transcriptCoordStop; // the end point is the end of the
                                              // exon

              // get the offset of the point from the start of the exon (in
              // transcript corrdinates)
              var diff = Math.abs(point - transcriptCoordStart);

              // perform the conversion based on strand direction
              if (direction === "PLUS") {
                // reference of start is the gene start corrdinate
                translatedPoint = geneCoordStart + diff;
              }
              // reference start is the gene stop coordinate
              else {
                translatedPoint = geneCoordStop - diff;
              }
            }

            // return to the caller
            return translatedPoint;
          }

          // Check for features
          if (d.originalFeatures !== undefined) {
            // init the exon counter. used for display messages
            // var count = 1;

            // init the variables for rendering of the feature in gene
            // coordinates
            var renderStart = 0;
            var renderStop = 0;

            // init the variables for feature start/stops in transcript
            // coordinates
            var featureStart = 0;
            var featureStop = 0;

            // init variables for polarity based exon start/stops in transcript
            // coordinates
            var exonStart = 0;
            var exonStop = 0;

            // init the variables for transcipt to gene coordinate conversion
            // points
            var translatedStart = 0;
            var translatedStop = 0;

            // errorDisplay("Strand direction:" + d.direction, 0);

            // Filter on all the exons and loop for feature rendering
            d.regions.filter(function(r) {
              return r.type === "EXON";
            }).forEach(function(r) {
              // errorDisplay("Transcript:" + d.name + ", Exon " + count++, 0);

              // rearrange the transcript coordinates of the exon.
              // these values are only used to determine if a feature falls
              // within the transcript.
              if (d.direction === "PLUS") {
                transCoordStart = r.transcriptStart;
                transCoordStop = r.transcriptStop;
              } else {
                transCoordStart = r.transcriptStop;
                transCoordStop = r.transcriptStart;
              }

              // errorDisplay("Exon info - Gene coordinates: start=" + r.start +
              // ", stop=" + r.stop + ", Transcript coordinates: start=" +
              // transCoordStart + ", stop=" + transCoordStop + ", Region
              // length=" + (transCoordStop - transCoordStart), 0);

              // loop through the features and find the ones that land within
              // the transcript region.
              // note the feature regions are using transcript indexing.
              // also note that this array was sorted earlier in the code.
              for (var i = 0; i < d.originalFeatures.length - 1; i++) {
                // init the feature start/stops in transcript coordinates
                featureStart = d.originalFeatures[i].regionStart;
                featureStop = d.originalFeatures[i].regionStop;

                // If this feature stops before we even get to this exon jump to
                // the next potential feature
                if (featureStop < transCoordStart) {
                  continue; // go to the next feature
                }
                // Else if this exon stops before the start of the next feature
                // we have run out of potential features in this exon
                else if (transCoordStop < featureStart) {
                  break; // go to the next exon
                }

                // errorDisplay("Feature in range: - id=" +
                // d.originalFeatures[i].id + ", start=" + featureStart + ",
                // stop=" + featureStop + ", length=" + (featureStop -
                // featureStart), 0);

                // note that feature coordinates use the transcript coordinate
                // system.
                // which means that each feature has to be converted to gene
                // coordinates for rendering.

                // translate the feature start in transcript coordinate system
                // to the gene coordinate system
                translatedStart = TranslateToGeneCoord(d.direction, r.start, r.stop, transCoordStart, transCoordStop, featureStart);

                // Translate the feature stop in transcript coordinate system to
                // the gene coordinate system
                translatedStop = TranslateToGeneCoord(d.direction, r.start, r.stop, transCoordStart, transCoordStop, featureStop);

                // errorDisplay("Feature layout: - translatedStart=" +
                // translatedStart + ", translatedStop=" + translatedStop + ",
                // length=" + Math.abs(translatedStop - translatedStart) , 0);

                // Did we get a start and stop rendering area
                if (translatedStart != 0 && translatedStop != 0) {
                  // make an adjustment for negative strands because of the way
                  // they are flipped in the data object
                  if (d.direction !== "PLUS") {
                    renderStart = translatedStop;
                    renderStop = translatedStart;
                  } else {
                    renderStart = translatedStart;
                    renderStop = translatedStop;
                  }

                  // Add the feature to the list that will be rendered
                  d.features.push({
                    start : renderStart,
                    stop : renderStop,
                    type : r.type,
                    collapsible : false,
                    collapsibleOffset : r.collapsibleOffset,
                    id : d.originalFeatures[i].id,
                    colorID : i,
                    // get all the messages that are at this spot. there may be
                    // multiple due to feature overlaps
                    // description: getFeatureNotes(d.originalFeatures,
                    // featureStart, featureStop),
                    // note: d.originalFeatures[i].note
                    // Get all notes at this spot. There may be multiple due to
                    // feature overlaps
                    notes : getFeatureNotes(d.originalFeatures, featureStart, featureStop)
                  });
                }
              }
            });
            /*
             * // Get a list of the unique feature ids var unique =
             * d3.set(d.features.map(function(d) { return d.id; })).values();
             *  // Set the color scale for each feature id d.featureColorScale =
             * d3.scale.ordinal() .domain(unique)
             * .range(colorbrewer.Paired[12]);
             */
          } else {
            errorDisplay("geneBrowser.addCollapsibleRegions(): No feature data for " + d.name, 1);
          }
        });

        // XXX: Use note instead of id (commented out above) across all
        // transcripts for more consistent coloring

        // Get a list of the unique feature notes
        var unique = d3.set(d3.merge(transcripts.map(function(d) {
          return d.features.map(function(d) {
            return d.notes;
          });
        }))).values();

        // Get ids for each note
        var noteIDScale = d3.scale.ordinal().domain(unique).range(d3.range(unique.length));

        transcripts.forEach(function(d) {
          d.features.forEach(function(d) {
            d.noteIDs = d.notes.map(function(d) {
              return noteIDScale(d);
            });
          });
        });

        // Set the color scale for each feature
        // XXX: Don't need one per transcript now, since all use the same color
        // scale...
        var featureColorScale = d3.scale.ordinal().domain(d3.range(unique)).range(colorbrewer.Paired[12]);

        transcripts.forEach(function(d) {
          d.featureColorScale = featureColorScale;
        });
      } else {
        errorDisplay("geneBrowser.addCollapsibleRegions(): No active transcripts data.");
      }
    }

    /**
     * Update variant offsets after a expand/collapse operation.
     * 
     */
    function updateVariants() {
      // No gene selected. Just return as there is nothing to do. Commonly
      // happens on initial page load.
      if (geneName === "") {
        return;
      }

      if (geneRegions !== undefined && geneRegions.length > 0) {
        // Check for patient variants
        if (geneVariants !== undefined && geneVariants.length > 0) {
          // Copy collapsible offsets from gene regions
          for (var i = 0; i < geneVariants.length; i++) {
            // Variants are not sorted, so need to loop over all gene regions
            var j = 0;

            while (j < geneRegions.length && geneRegions[j].stop < geneVariants[i].gPos) {
              j++;
            }

            if (j === geneRegions.length) {
              j--;
            }

            geneVariants[i].collapsibleOffset = geneRegions[j].collapsibleOffset
                + (geneRegions[j].exonCount === 0 && geneRegions[j].intronBorderCount === 0 ? geneVariants[i].gPos - geneRegions[j].start
                    + 1 : 0);
            geneVariants[i].exonCount = geneRegions[j].exonCount;
            geneVariants[i].intronBorderCount = geneRegions[j].intronBorderCount;
          }
        } else {
          errorDisplay("geneBrowser.updateVariants(): No gene variant data.");
        }

        // Check for population variants
        if (populationVariants !== undefined && populationVariants.length > 0) {
          // Copy collapsible offsets from gene regions
          for (var i = 0; i < populationVariants.length; i++) {
            // Variants are not sorted, so need to loop over all gene regions
            var j = 0;

            while (j < geneRegions.length && geneRegions[j].stop < populationVariants[i].gStart) {
              j++;
            }

            if (j === geneRegions.length) {
              j--;
            }

            populationVariants[i].collapsibleOffset = geneRegions[j].collapsibleOffset
                + (geneRegions[j].exonCount === 0 && geneRegions[j].intronBorderCount === 0 ? populationVariants[i].gStart
                    - geneRegions[j].start + 1 : 0);
          }
        } else {
          errorDisplay("geneBrowser.updateVariants(): No population variant data.");
        }
      } else {
        errorDisplay("geneBrowser.updateVariants(): No gene region data.");
      }
    }

    /**
     * Compute the total height of the gene display and update scales
     * accordingly
     * 
     */
    function computeHeight() {
      geneTranscriptSpacing = geneHeight / 2;

      // Compute height
      var oldHeight = height;

      height = geneOffset + geneHeight + geneTranscriptSpacing + (transcriptHeight + transcriptSpacing) * (activeTranscripts.length - 1)
          + transcriptHeight + variantSize * 2 + margin.top + margin.bottom;

      // Set svg height
      svg.transition().delay(oldHeight > height ? transitionDuration : 0).duration(oldHeight > height ? transitionDuration : 0).attr(
          "height", height);

      // Update scales
      yScale.rangePoints([
          0, (transcriptHeight + transcriptSpacing) * (activeTranscripts.length - 1)
      ]);
      geneHeightScale.range(regionMapping === "color" ? [
          geneHeight, geneHeight
      ] : [
          1, geneHeight
      ]);
      geneColorScale.range(regionMapping === "height" ? [
          "grey", "grey"
      ] : [
          "lightgrey", "grey"
      ]);
      transcriptHeightScale.range(regionMapping === "color" ? [
        transcriptHeight
      ] : [
          transcriptHeight / 2, transcriptHeight / 2, transcriptHeight / 2, 1, 1, transcriptHeight
      ]);
      transcriptColorScale.range(regionMapping === "height" ? [
        "grey"
      ] : [
          "darkgrey", "darkgrey", "darkgrey", "lightgrey", "lightgrey", "grey"
      ]);
    }

    /**
     * Renders the gene
     */
    function drawGene(transitionTime) {
      // No gene selected. Just return as there is nothing to do. Commonly
      // happens on initial page load.
      if (geneName === "") {
        return;
      }
      // Gene selected has no transcipts. This is an error
      else if (!transcripts || transcripts.length === 0) {
        errorDisplay("geneBrowser.drawGene(): No transcripts data.");

        return;
      }

      // Default to zero transition time
      if (!transitionTime) {
        transitionTime = 0;
      }

      // Bind active transcripts
      var transcript = svg.select(".transcript").selectAll(".transcript > g").data(activeTranscripts, function(d) {
        return d.name;
      });

      // Check for update/enter/exit of transcripts
      var update = !transcript.empty(), enter = !transcript.enter().empty(), exit = !transcript.exit().empty();

      // Draw gene information
      drawGeneInfo();

      // Draw the gene summary
      drawGeneSummary();

      // Draw the transcripts
      drawTranscripts();

      // Draw the range labels
      drawLabels();

      // Draw the variant glyphs
      drawVariants();

      // Draw the population variants
      drawPopulationVariants();

      /**
       * Renders the gene information
       * 
       */
      function drawGeneInfo() {
        var gGeneInfo = svg.select(".geneInfo");

        // Gene name
        var name = gGeneInfo.select(".name").text("Gene: " + geneName);

        // Direction arrow
        gGeneInfo.select(".direction").attr("transform", "translate(" + (name[0][0].getComputedTextLength() + 10) + ",0)").attr("points",
            activeTranscripts[0].direction === "PLUS" ? "0,-5 0,5 10,0" : "0,0 10,5 10,-5");

        // Gene description
        gGeneInfo.select(".description").text(geneDescription.split(";")[0]);

        // Position info
        var x = +svgGene.attr("x"), w = +svgGene.attr("width");

        // Label format
        var format = d3.format(",");

        gGeneInfo.selectAll(".position").transition().duration(transitionTime).attr("transform", function(d) {
          return "translate(" + (d === "start" ? x : x + w) + "," + geneOffset / 2 + ")";
        }).attr("visibility", "visible").select(".label").text(function(d) {
          return d === "start" ? format(viewMin) : format(viewMax);
        });

        // Range info
        gGeneInfo.select(".range").transition().duration(transitionTime).attr("transform",
            "translate(" + (x + w / 2) + "," + geneOffset / 2 + ")").attr("visibility", "visible").select("text").text(function(d) {
          return format(viewMax - viewMin) + " base pairs";
        });
      }

      /**
       * Renders the gene summary
       * 
       */
      function drawGeneSummary() {
        // Get a reference to the area
        var geneSummary = svg.select(".geneSummary");

        // Set the transform for the gene summary area
        geneSummary.transition().duration(transitionTime).attr("transform", "translate(0," + (geneOffset + geneHeight / 2) + ")");

        // Bind empty data for border so enter is called the first time her, and
        // update otherwise
        var border = geneSummary.selectAll(".border").data([
          0
        ]);

        // Update
        border.transition().duration(transitionTime).attr("x", -1).attr("y", -geneHeight / 2).attr("width", innerWidth()).attr("height",
            geneHeight).style("stroke-opacity", regionMapping === "color" ? 0 : 1);

        // Enter
        border.enter().append("rect").attr("class", "border").attr("x", xScale.range()[0] - 1).attr("y", -geneHeight / 2).attr("width",
            xScale.range()[1] - xScale.range()[0] + 2).attr("height", geneHeight)
            .style("stroke-opacity", regionMapping === "color" ? 0 : 1);

        // Bind region data
        var region = geneSummary.select(".region").selectAll("rect").data(geneRegions);

        // Functions for region characteristics

        // Compute the x coordinate based on collapsible offset and whether or
        // not the region is collapsible
        function regionX(d) {
          return xScale(d.start - d.collapsibleOffset * (1 - collapsibleScale));
        }

        // Compute the y coordinate
        function regionY(d) {
          return -geneHeightScale(d.exonCount) / 2;
        }

        // Compute the width based on the x coordinate of the start and stop
        // positions
        function regionWidth(d) {
          return (xScale(d.stop) - xScale(d.start - 1)) * (d.exonCount === 0 && d.intronBorderCount === 0 ? collapsibleScale : 1);
        }

        // Compute the height based on the exon count
        function regionHeight(d) {
          return geneHeightScale(d.exonCount);
        }

        // Compute the fill color based on the exon count
        function regionFill(d) {
          return geneColorScale(d.exonCount);
        }

        // Compute the transition delay based on whether transcripts are
        // entering or exiting
        function regionDelay() {
          return enter || exit ? transitionTime : 0;
        }

        // Update
        region.transition().delay(regionDelay).duration(transitionTime).attr("x", regionX).attr("y", regionY).attr("width", regionWidth)
            .attr("height", regionHeight).attr("fill", regionFill);

        // Enter
        region.enter().append("rect").attr("x", regionX).attr("y", regionY).attr("width", regionWidth).attr("height", regionHeight).attr(
            "fill", regionFill);

        // Exit
        region.exit().transition().delay(regionDelay).duration(transitionTime).attr("width", 0).remove();

        // Bind only intron regions for rendering collapsed intron circles
        var intron = geneSummary.select(".intronCollapse").selectAll("circle").data(geneRegions.filter(function(d) {
          return d.exonCount === 0 && d.utrCount === 0 && d.intronBorderCount === 0;
        }));

        // Functions and variables for intron region characteristics
        // XXX: Some duplication with drawTranscripts. Fix this.

        // Is intron or not
        function isIntron(d) {
          return d.type === "INTRON";
        }

        // Region length
        function regionLength(d) {
          return d.stop - d.start + 1;
        }

        // Minimum intron length
        var minIntronLength = d3.min(activeTranscripts, function(d) {
          return d3.min(d.regions.filter(isIntron), regionLength);
        });

        // Maximum intron length
        var maxIntronLength = d3.max(activeTranscripts, function(d) {
          return d3.max(d.regions.filter(isIntron), regionLength);
        });

        // Maximum collapsed intron radius
        var r = transcriptHeightScale("EXON") * 0.25;

        // Scale for collapsed intron radius
        var intronRScale = d3.scale.pow().domain([
            minIntronLength, maxIntronLength
        ]).range([
            r * 0.5, r
        ]).exponent(0.5);

        // Compute x position
        function intronX(d) {
          return regionX(d) + regionWidth(d) / 2;
        }

        // Compute radius
        function intronR(d) {
          return intronRScale(regionLength(d)) * (1.0 - collapsibleScale);
        }

        // Update
        intron.transition().delay(regionDelay).duration(transitionTime).attr("cx", intronX).attr("r", intronR).style("stroke", regionFill);

        // Enter
        intron.enter().append("circle").attr("cx", intronX).attr("r", 0).style("stroke", regionFill).transition().delay(regionDelay)
            .duration(transitionTime).attr("r", intronR);

        // Exit
        intron.exit().transition().delay(regionDelay).duration(transitionTime).attr("r", 0).remove();
      }

      /**
       * Renders the gene transcripts
       * 
       */
      function drawTranscripts() {
        // Get a reference to the transcript group
        var transcriptGroup = svg.select(".transcript");

        // Move transcript group
        transcriptGroup.transition().duration(transitionTime).attr("transform",
            "translate(0," + (geneOffset + geneHeight + geneTranscriptSpacing + transcriptHeight / 2) + ")");

        // Bind transcript data
        var transcript = transcriptGroup.selectAll(".transcript > g").data(activeTranscripts, function(d) {
          return d.name;
        });

        // ///////////////////////////////////////////////////////////
        // Transcript update

        // Update transcript transform
        transcript.transition().delay(exit ? transitionTime : 0).duration(transitionTime).attr("transform", function(d) {
          return "translate(0," + yScale(d.name) + ")";
        });

        // Bind region data
        var region = transcript.select(".region").selectAll("rect").data(function(d) {
          return d.regions;
        });

        // Functions for region characteristics
        // XXX: Some duplication with drawGeneSummary. Should fix this.

        // Compute the x coordinate based on collapsible offset and whether or
        // not the region is collapsible
        function regionX(d) {
          return xScale(d.start - d.collapsibleOffset * (1 - collapsibleScale));
        }

        // Compute y position
        function regionY(d) {
          return -regionHeight(d) / 2;
        }

        // Compute width base on start and stop positions
        function regionWidth(d) {
          return (xScale(d.stop) - xScale(d.start - 1)) * (d.collapsible ? collapsibleScale : 1);
        }

        // Compute height based on region type
        function regionHeight(d) {
          return transcriptHeightScale(d.type);
        }

        // Compute color based on region type
        function regionFill(d) {
          return transcriptColorScale(d.type);
        }

        // Compute transition delay based on whether transcripts are entering or
        // exiting
        function regionDelay() {
          return enter || exit ? transitionTime : 0;
        }

        // Update
        region.transition().delay(regionDelay).duration(transitionTime).attr("x", regionX).attr("y", regionY).attr("width", regionWidth)
            .attr("height", regionHeight).style("fill", regionFill);

        // Enter
        region.enter().append("rect").attr("x", regionX).attr("y", regionY).attr("width", 0).attr("height", regionHeight).style("fill",
            regionFill).transition().delay(regionDelay).duration(transitionTime).attr("width", regionWidth);

        // Exit
        region.exit().transition().delay(regionDelay).duration(transitionTime).attr("width", 0).remove();

        // Bind features
        // Use each to create a closure containing parent data
        transcript.each(function(d) {
          var feature = d3.select(this).select(".feature").selectAll("rect").data(getFeatures(d));

          // Functions for features characteristics
          var heightFraction = 0.6;

          function featureX(d) {
            return regionX(d.feature);
          }

          function featureY(d) {
            var h = transcriptHeightScale("EXON") * heightFraction;
            return -h / 2 + d.index / d.feature.notes.length * h;
          }

          function featureWidth(d) {
            return regionWidth(d.feature);
          }

          function featureHeight(d) {
            return transcriptHeightScale("EXON") * heightFraction / d.feature.notes.length;
          }

          function featureFill(e) {
            return d.featureColorScale(e.feature.noteIDs[e.index]);
          }

          // Update
          feature.transition().delay(regionDelay).duration(transitionTime).attr("x", featureX).attr("y", featureY).attr("width",
              featureWidth).attr("height", featureHeight).style("fill", featureFill).style("fill-opacity", featureOpacity);

          // Enter
          feature.enter().append("rect").attr("x", 0).attr("y", featureY).attr("width", 0).attr("height", featureHeight).style("fill",
              featureFill).style("fill-opacity", featureOpacity).transition().duration(transitionTime).attr("x", featureX).attr("width",
              featureWidth);

          // Exit
          feature.exit().transition().delay(regionDelay).duration(transitionTime).attr("width", 0).style("fill-opacity", 0).remove();
        });

        // Bind only intron regions
        var intron = transcript.select(".intronCollapse").selectAll("circle").data(function(d) {
          return d.regions.filter(function(d) {
            return d.type === "INTRON";
          });
        });

        // Functions and variables for intron region characteristics
        // XXX: Some duplication with drawGeneSummary. Fix this.

        // Intron or not
        function isIntron(d) {
          return d.type === "INTRON";
        }

        // Length of region
        function regionLength(d) {
          return d.stop - d.start + 1;
        }

        // Minimum intron length across all active transcripts
        var minIntronLength = d3.min(activeTranscripts, function(d) {
          return d3.min(d.regions.filter(isIntron), regionLength);
        });

        // Maximum intron length across all active transcripts
        var maxIntronLength = d3.max(activeTranscripts, function(d) {
          return d3.max(d.regions.filter(isIntron), regionLength);
        });

        // Maximum radius for collapsed intron circle
        var r = transcriptHeightScale("EXON") * 0.4;

        // Radius scale for collapsed intron circle
        var intronRScale = d3.scale.pow().domain([
            minIntronLength, maxIntronLength
        ]).range([
            r * 0.5, r
        ]).exponent(0.5);

        // X position for collapsed intron circle
        function intronX(d) {
          return regionX(d) + regionWidth(d) / 2;
        }

        // Radius for collapsed intron circle
        function intronR(d) {
          return intronRScale(regionLength(d)) * (1.0 - collapsibleScale);
        }

        // Update
        intron.transition().delay(regionDelay).duration(transitionTime).attr("cx", intronX).attr("r", intronR).style("stroke", regionFill);

        // Enter
        intron.enter().append("circle").attr("cx", intronX).attr("r", 0).style("stroke", regionFill).transition().delay(regionDelay)
            .duration(transitionTime).attr("r", intronR);

        // Exit
        intron.exit().transition().delay(regionDelay).duration(transitionTime).attr("r", 0).remove();

        // ///////////////////////////////////////////////////////////
        // Transcript enter

        var transcriptEnter = transcript.enter().append("g").attr("transform", function(d) {
          return "translate(0," + yScale(d.name) + ")";
        }).style("fill-opacity", 0);

        transcriptEnter.transition().delay(update ? transitionTime : 0).duration(transitionTime).style("fill-opacity", 1);

        // Bind region data
        region = transcriptEnter.append("g").attr("class", "region").selectAll("rect").data(function(d) {
          return d.regions;
        });

        // Enter
        region.enter().append("rect").attr("x", 0).attr("y", regionY).attr("width", 0).attr("height", regionHeight).style("fill",
            regionFill).transition().duration(transitionTime).attr("x", regionX).attr("width", regionWidth);

        // XXX: A bit of a hack to get stacked features working for demo.
        // Should change how feature data is generated instead.
        function getFeatures(transcript) {
          // Combine all feature notes
          var f = [];

          transcript.features.forEach(function(d) {
            d.notes.forEach(function(e, i) {
              f.push({
                index : i,
                feature : d
              });
            });
          });

          return f.sort(function(a, b) {
            return d3.ascending(a.start, b.start);
          });
        }

        // Use each to create a closure containing parent data
        transcriptEnter.each(function(d) {
          // Bind features
          var feature = d3.select(this).append("g").attr("class", "feature").selectAll("rect").data(getFeatures(d));

          // Functions for features characteristics
          // XXX: Duplicated from above. Fix this.
          var heightFraction = 0.6;

          function featureX(d) {
            return regionX(d.feature);
          }

          function featureY(d) {
            var h = transcriptHeightScale("EXON") * heightFraction;
            return -h / 2 + d.index / d.feature.notes.length * h;
          }

          function featureWidth(d) {
            return regionWidth(d.feature);
          }

          function featureHeight(d) {
            return transcriptHeightScale("EXON") * heightFraction / d.feature.notes.length;
          }

          function featureFill(e) {
            return d.featureColorScale(e.feature.noteIDs[e.index]);
          }

          // Enter
          feature.enter().append("rect").attr("x", 0).attr("y", featureY).attr("width", 0).attr("height", featureHeight).style("fill",
              featureFill).style("fill-opacity", featureOpacity).transition().delay(regionDelay).duration(transitionTime).attr("x",
              featureX).attr("width", featureWidth);
          /*
           * // Add title for hover information feature.append("title")
           * .text(function(d) { return d.description; });
           */

          // Add tool tips
          $(".feature > rect").tipsy({
            gravity : 'sw',
            fade : true,
            fadeInDuration : 0.1,
            fadeOutDuration : 0.1,
            html : true,
            opacity : 0.95,
            title : function() { /*
                                   * var s = this.__data__.notes, r = "";
                                   * 
                                   * s.forEach(function(d) { r = r + "<p>" +
                                   * "Feature" + d; });
                                   * 
                                   * return r;
                                   */
              var d = this.__data__;
              return d.feature.notes[d.index];
            }
          });
        });

        // Bind only intron regions
        intron = transcriptEnter.append("g").attr("class", "intronCollapse").style("stroke-opacity", 0).selectAll("circle").data(
            function(d) {
              return d.regions.filter(function(d) {
                return d.type === "INTRON";
              });
            });

        // Update
        transcriptEnter.select(".intronCollapse").transition().delay(regionDelay).duration(transitionTime).style("stroke-opacity", 1);

        // Enter
        intron.enter().append("circle").attr("cx", 0).attr("r", 0).style("stroke", regionFill).transition().duration(transitionTime).attr(
            "cx", function(d) {
              return regionX(d) + regionWidth(d) / 2;
            }).attr("r", intronR);

        // ///////////////////////////////////////////////////////////
        // Transcript exit

        var transcriptExit = transcript.exit().transition().duration(transitionTime).style("fill-opacity", 0).style("stroke-opacity", 0)
            .remove();

        transcriptExit.select(".region").selectAll("rect").attr("x", 0).attr("width", 0);

        transcriptExit.select(".feature").selectAll("rect").attr("x", 0).attr("width", 0);

        transcriptExit.select(".intronCollapse").selectAll("circle").attr("cx", 0).attr("r", 0);
      }

      /**
       * Renders the axis labels
       * 
       */
      function drawLabels() {
        // Select gene summary group
        var geneSummary = svg.select(".geneSummaryLabel");

        // Move gene group
        geneSummary.transition().duration(transitionTime).attr("transform", "translate(0," + (geneOffset + geneHeight / 2) + ")");

        // Show label. Bind empty data to trigger enter first time through
        geneSummary.selectAll("text").data([
          0
        ]).enter().append("text").text("Gene summary").attr("dy", "0.35em");

        // Select transcript group
        var transcriptGroup = svg.select(".transcriptLabel");

        // Move transcript group
        transcriptGroup.transition().duration(transitionTime).attr("transform",
            "translate(0," + (geneOffset + geneHeight + geneTranscriptSpacing + transcriptHeight / 2) + ")");

        // Bind active transcripts
        var transcript = transcriptGroup.selectAll("g").data(activeTranscripts, function(d) {
          return d.name;
        });

        // Update
        transcript.transition().delay(exit ? transitionTime : 0).duration(transitionTime).attr("transform", function(d) {
          return "translate(0," + yScale(d.name) + ")";
        });

        // Enter
        var transcriptEnter = transcript.enter().append("g").attr("transform", function(d) {
          return "translate(0," + yScale(d.name) + ")";
        }).style("fill-opacity", 0);

        transcriptEnter.transition().delay(update ? transitionTime : 0).duration(transitionTime).style("fill-opacity", 1);

        // Remove symbol
        var remove = d3.svg.symbol().type("cross").size(40);

        // Add remove button
        transcriptEnter.append("path").attr("class", "remove").attr("d", remove).attr("transform", "translate(0,0.5)rotate(45)").on(
            "mouseover", function(d) {
              // Fade out
              d3.select(this.parentNode).style("fill-opacity", 0.25);

              svg.select(".transcript").selectAll(".transcript > g").filter(function(e) {
                return e === d;
              }).style("fill-opacity", 0.25).selectAll(".intronCollapse").style("stroke-opacity", 0.25);
            }).on("mouseout", function(d) {
          // Fade in
          d3.select(this.parentNode).style("fill-opacity", 1);

          svg.select(".transcript").selectAll(".transcript > g").filter(function(e) {
            return e === d;
          }).style("fill-opacity", 1).selectAll(".intronCollapse").style("stroke-opacity", 1);
        }).on("click", function(d) {
          if (activeTranscripts.length === 1) {
            return;
          }

          // Remove transcript
          activeTranscripts = activeTranscripts.filter(function(e) {
            return e.name !== d.name;
          });

          updateGeneRegions();

          drawGene(transitionDuration);
        });

        transcriptEnter.append("text").text(function(d) {
          return d.name;
        }).attr("class", "label").attr("x", 6).attr("dy", "0.35em").on("mousedown", function() {
          // Prevent text highlighting
          d3.event.preventDefault();
        }).call(function() {
          var offset;

          // Add drag behavior for rearranging transcripts
          return d3.behavior.drag().on("dragstart", function(d) {
            // Save offset to parent group
            offset = yScale(d.name) - d3.mouse(svg.select(".transcriptLabel").node())[1];
          }).on("drag", function(d) {
            // Get new position
            var y = d3.mouse(svg.select(".transcriptLabel").node())[1] + offset;
            i = activeTranscripts.indexOf(d);

            // See if order has changed
            if (i < activeTranscripts.length - 1 && y > yScale(activeTranscripts[i + 1].name)) {
              // Switch order
              var t = activeTranscripts[i];
              activeTranscripts[i] = activeTranscripts[i + 1];
              activeTranscripts[i + 1] = t;

              // Update y scale with new order
              yScale.domain(activeTranscripts.map(function(d) {
                return d.name;
              }));

              // Draw with new order
              svg.select(".transcriptLabel").selectAll("g").filter(function(d) {
                return d === activeTranscripts[i];
              }).transition().attr("transform", "translate(0," + yScale(activeTranscripts[i].name) + ")");

              svg.select(".transcript").selectAll(".transcript > g").filter(function(d) {
                return d === activeTranscripts[i];
              }).transition().attr("transform", "translate(0," + yScale(activeTranscripts[i].name) + ")");
            } else if (i > 0 && y < yScale(activeTranscripts[i - 1].name)) {
              // Switch order
              var t = activeTranscripts[i];
              activeTranscripts[i] = activeTranscripts[i - 1];
              activeTranscripts[i - 1] = t;

              // Update y scale with new order
              yScale.domain(activeTranscripts.map(function(d) {
                return d.name;
              }));

              // Draw with new order
              svg.select(".transcriptLabel").selectAll("g").filter(function(d) {
                return d === activeTranscripts[i];
              }).transition().attr("transform", "translate(0," + yScale(activeTranscripts[i].name) + ")");

              svg.select(".transcript").selectAll(".transcript > g").filter(function(d) {
                return d === activeTranscripts[i];
              }).transition().attr("transform", "translate(0," + yScale(activeTranscripts[i].name) + ")");
            }

            // Move this axis
            d3.select(this.parentNode).attr("transform", "translate(0," + y + ")").transition();

            d3.select(".transcript").selectAll(".transcript > g").filter(function(e) {
              return e === d;
            }).attr("transform", "translate(0," + y + ")").transition();
          }).on("dragend", function(d) {
            // Move to correct position
            d3.select(this.parentNode).transition().attr("transform", "translate(0," + yScale(d.name) + ")");

            svg.select(".transcript").selectAll(".transcript > g").filter(function(e) {
              return e === d;
            }).transition().attr("transform", "translate(0," + yScale(d.name) + ")");
          });
        }());

        // Exit
        var transcriptExit = transcript.exit().transition().duration(transitionTime).style("fill-opacity", 0).style("stroke-opacity", 0)
            .remove();
      }

      /**
       * Renders the variants on the gene
       * 
       */
      function drawVariants() {
        // Utility variables and functions
        var s = 15;

        var yPos1 = innerHeight() - s * 2, yPos2 = innerHeight() - s + variantSize / 2;

        delay = exit ? transitionTime : 0;

        // Functions for glyph characteristics

        // X position
        function position(d) {
          return xScale(d.gPos - d.collapsibleOffset * (1.0 - collapsibleScale) + 0.5);
        }

        // Glyph scale
        var glyphScale = d3.scale.threshold().domain([
          1
        ]).range([
            variantSize * 0.6, variantSize
        ]);

        // Glyph size using glyphScale function
        function size(d) {
          return glyphScale(d.exonCount + d.intronBorderCount);
        }

        // Path for curved line
        var curvePath = d3.svg.diagonal().source(function(d) {
          return {
            x : position(d),
            y : yPos1
          };
        }).target(function(d) {
          return {
            x : d.x,
            y : yPos2 - size(d) / 2
          };
        });

        // Path for glyph
        /*
         * function glyphPath(d) { return d3.svg.symbol()
         * .size(Math.pow(size(d), 2)) .type(variantShapeScale(d.type))(); }
         */

        // Layout variants
        geneVariants.forEach(function(d) {
          d.x = position(d);
        });

        // Space overlapping glyphs
        var overlap;

        do {
          overlap = false;

          for (var i = 0; i < geneVariants.length - 1; i++) {
            var v1 = geneVariants[i];
            var v2 = geneVariants[i + 1];

            // XXX: Create function using glyphScale
            if (v2.x - v1.x < size(v1) / 2 + size(v2) / 2 + 2) {
              v1.x -= 1;
              v2.x += 1;
              overlap = true;
            }
          }
        } while (overlap === true);

        // Bind variant data
        var variant = svgGene.select(".variant").selectAll(".variant > g").data(geneVariants);

        // Update
        variantUpdate = variant.transition().delay(delay).duration(transitionTime);

        // Line
        variantUpdate.select(".line").attr("x1", position).attr("x2", position).attr("y1", geneOffset).attr("y2", yPos1);

        // Curve
        variantUpdate.select(".curve").attr("d", curvePath);

        // Glyph
        var glyph = variantUpdate.select(".glyph").attr("transform", function(d) {
          return "translate(" + d.x + "," + yPos2 + ")";
        });

        // Glyph attributes
        glyph.each(function(d) {
          var s = size(d), n = variantAttributes.length;

          var attribute = d3.select(this).selectAll(".attribute").data(variantAttributes);

          // Update
          attribute.attr("x", function(e, i) {
            return -s / 2 + s * i / n;
          }).attr("y", -s / 2).attr("width", s / n).attr("height", s).style("fill", function(e) {
            return e(d);
          });

          // Enter
          attribute.enter().append("rect").attr("class", "attribute").attr("x", function(e, i) {
            return -s / 2 + s * i / n;
          }).attr("y", -s / 2).attr("width", s / n).attr("height", s).style("fill", function(e) {
            return e(d);
          });

          // Exit
          attribute.exit().remove();
        });

        // Glyph outline
        glyph.select(".outline").attr("x", function(d) {
          return -size(d) / 2;
        }).attr("y", function(d) {
          return -size(d) / 2;
        }).attr("width", size).attr("height", size);

        // Glyph highlight
        glyph.select(".highlight").select("polygon").attr("points", highlightPolygon);

        glyph.select(".highlight").select("text").attr("y", function(d) {
          return d.selected ? variantSize * 1.25 + 10 : variantSize * 1.25 + 20;
        });

        // Enter for variants
        variantEnter = variant.enter().append("g");

        variantEnter.append("line").attr("class", "line").attr("y1", geneOffset).attr("y2", yPos1).attr("stroke-dasharray", "2, 2")
            .transition().delay(delay).duration(transitionTime).attr("x1", position).attr("x2", position);

        variantEnter.append("path").attr("class", "curve").attr("d", d3.svg.diagonal().source({
          x : 0,
          y : yPos1
        }).target({
          x : 0,
          y : yPos2
        })).attr("stroke-dasharray", "2, 2").transition().delay(delay).duration(transitionTime).attr("d", curvePath);

        // Enter for glyphs on variant enter
        var glyphEnter = variantEnter.append("g").attr("class", "glyph").attr("transform", function(d) {
          return "translate(" + d.x + "," + yPos2 + ")";
        }).on("mouseover", function(d) {
          // Fire callback
          dispatch.highlight_variant(d);
        }).on("mouseout", function(d) {
          // Fire callback
          dispatch.unhighlight_variant(d);
        }).on("click", function(d) {
          // Fire callbacks
          // XXX: We know this will be the same gene. What actually needs to be
          // called?
          dispatch.gene_transcript_data_listener(d);
          dispatch.gene_description_data_listener(d);
        });

        glyphEnter.transition().delay(delay).duration(transitionTime);

        // Glyph atributes
        glyphEnter.each(function(d) {
          var s = size(d), n = variantAttributes.length;

          d3.select(this).selectAll(".attribute").data(variantAttributes).enter().append("rect").attr("class", "attribute").attr("x",
              function(e, i) {
                return -s / 2 + s * i / n;
              }).attr("y", -s / 2).attr("width", s / n).attr("height", s).style("fill", function(e) {
            return e(d);
          });
        });

        // Glyph outline
        glyphEnter.append("rect").attr("class", "outline").attr("x", function(d) {
          return -size(d) / 2;
        }).attr("y", function(d) {
          return -size(d) / 2;
        }).attr("width", size).attr("height", size);
      }
    }

    /**
     * Renders the population variants
     * 
     */
    function drawPopulationVariants() {
      // Bind variant data
      var variant = svgGene.select(".populationVariant").attr("transform", "translate(0," + geneOffset + ")").selectAll("rect").data(
          populationVariants);

      // Functions for variant characteristics

      // X position
      function variantX(d) {
        return xScale(d.gStart - d.collapsibleOffset * (1.0 - collapsibleScale));
      }

      // Width
      function variantWidth(d) {
        return Math.max(xScale(d.gStop) - xScale(d.gStart), 2);
      }
      ;

      // Height scale
      var epsilon = 0.00001;
      var heightScale = d3.scale.linear().domain([
          Math.log(1 + epsilon), Math.log(epsilon)
      ]).range([
          0, geneOffset / 4
      ]);

      // Height
      function variantHeight(d) {
        return heightScale(Math.log(d.frequency + epsilon));
      }

      // Y position
      function variantY(d) {
        return -variantHeight(d);
      }

      // Color
      // XXX: Copied from variant table
      var colorScale = d3.scale.threshold().domain([
          0.0001, 0.001, 0.01, 0.1
      ]).range(colorbrewer.RdBu[5]);

      function variantColor(d) {
        return colorScale(d.frequency);
      }

      // Format frequency
      function formatFrequency(d) {
        var format = d3.format(".2f");
        return format(d * 100);
      }
      ;

      // Enter
      variant.enter().append("rect");

      // Add tool tips
      $(".populationVariant > rect").tipsy({
        gravity : 'sw',
        fade : true,
        fadeInDuration : 0.1,
        fadeOutDuration : 0.1,
        html : true,
        opacity : 0.95,
        title : function() {
          var d = this.__data__;
          return "Frequency: " + formatFrequency(d.frequency);
        }
      });

      // Enter + update
      variant.attr("x", variantX).attr("y", variantY).attr("width", variantWidth).attr("height", variantHeight).style("fill", variantColor);

      // Exit
      variant.exit().remove();
    }

    /**
     * Updates the width scaling
     * 
     */
    function updateXScale() {
      if (!geneRegions.length)
        return;

      // First and last regions
      var r1 = geneRegions[0], r2 = geneRegions[geneRegions.length - 1];

      // Starting point and width
      var x = maxLabelLength + 20, w = innerWidth() - x;

      // Update svg
      svgGene.attr("x", x).attr("width", w);

      // Update scale
      xScale.domain(
          [
              r1.start,
              r2.start - r2.collapsibleOffset * (1.0 - collapsibleScale) + (r2.stop - r2.start + 1)
                  * (r2.exonCount === 0 && r2.intronBorderCount === 0 ? collapsibleScale : 1)
          ]).range([
          0, w
      ]);

      // Update max and min positions
      geneMin = xScale.domain()[0];
      geneMax = xScale.domain()[1];

      // Store zoom settings
      var translate = zoom.translate(), scale = zoom.scale(), center = zoom.center();

      // Set x scale and restore zoom settings
      zoom.x(xScale).translate(translate).scale(scale).center(center);

      // Update view extent
      updateViewExtent();
    }

    /**
     * Updates the view extent variables for rendering via labels
     * 
     */
    function updateViewExtent() {
      // XXX: These are repeated elsewhere. Fix this.
      function regionX(d) {
        return d.start - d.collapsibleOffset * (1 - collapsibleScale);
      }
      function regionWidth(d) {
        return (d.stop - d.start + 1) * (d.exonCount === 0 && d.intronBorderCount === 0 ? collapsibleScale : 1);
      }

      // Get the x scale domain
      var d0 = xScale.domain()[0], d1 = xScale.domain()[1];

      for (var i = 0; i < geneRegions.length; i++) {
        // Get region information
        var r = geneRegions[i], x = regionX(r), w = regionWidth(r);

        if (x <= d0 && x + w >= d0) {
          // Set the view min
          var offset = w === 0 ? 0 : (d0 - x) / w;
          viewMin = Math.floor(r.start + (r.stop - r.start + 1) * offset);
        }

        if (x <= d1 && x + w >= d1) {
          // Set the view max and exit
          var offset = w === 0 ? 0 : (d1 - x) / w;
          viewMax = Math.floor(r.start + (r.stop - r.start + 1) * offset);

          break;
        }
      }
    }

    /**
     * Set the width of the view
     */
    function setWidth(_, transitionTime) {
      width = _;

      svg.attr("width", width);

      updateXScale();
    }

    // Accessor functions
    gb.width = function(_) {
      if (!arguments.length) {
        return width;
      }

      setWidth(_);
      drawGene(transitionDuration);

      return gb;
    };

    gb.regionMapping = function(_) {
      if (!arguments.length) {
        return regionMapping;
      }

      regionMapping = _;
      computeHeight();
      drawGene(transitionDuration);

      return gb;
    };

    gb.geneHeight = function(_) {
      if (!arguments.length) {
        return geneHeight;
      }

      geneHeight = _;
      computeHeight();
      drawGene();

      return gb;
    };

    gb.transcriptHeight = function(_) {
      if (!arguments.length) {
        return transcriptHeight;
      }

      transcriptHeight = _;
      computeHeight();
      drawGene();

      return gb;
    };

    gb.collapsibleScale = function(_) {
      if (!arguments.length) {
        return collapsibleScale;
      }

      collapsibleScale = _;
      updateXScale();
      drawGene();

      return gb;
    };

    gb.intronBorder = function(_) {
      if (!arguments.length) {
        return intronBorder;
      }

      intronBorder = _;
      updateGeneRegions();
      drawGene();

      return gb;
    };

    gb.variantSize = function(_) {
      if (!arguments.length)
        return variantSize;

      variantSize = _;
      drawGene();

      return gb;
    };

    gb.transcriptSpacing = function(_) {
      if (!arguments.length) {
        return transcriptSpacing;
      }

      transcriptSpacing = _;
      computeHeight();
      drawGene();

      return gb;
    };

    gb.featureOpacity = function(_) {
      if (!arguments.length) {
        return featureOpacity;
      }

      featureOpacity = _;
      drawGene();

      return gb;
    };

    gb.showAllTranscripts = function() {
      activeTranscripts = transcripts.slice();
      updateGeneRegions();
      drawGene(transitionDuration);

      return gb;
    };

    gb.sortBy = function(_) {
      if (!arguments.length) {
        return sortBy;
      }

      sortBy = _;

      function exonLength(d) {
        return d.regions.reduce(function(p, c) {
          return p + (c.type === "EXON" ? c.stop - c.start : 0);
        }, 0);
      }

      activeTranscripts.sort(function(a, b) {
        switch (sortBy) {
          case "accessionNumber":
            return d3.ascending(a.name, b.name);

          case "exonLength":
            return d3.descending(exonLength(a), exonLength(b));
        }
      });

      yScale.domain(activeTranscripts.map(function(d) {
        return d.name;
      }));
      drawGene(transitionDuration);

      return gb;
    };

    gb.transitionDuration = function(_) {
      if (!arguments.length) {
        return transitionDuration;
      }

      transitionDuration = _;

      return gb;
    };

    // Return closure
    return gb;
  };
})();
