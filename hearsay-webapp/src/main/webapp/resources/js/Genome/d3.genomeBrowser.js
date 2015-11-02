(function() {
  /**
   * main entry point for the rendering of the chromosome area
   */
  d3.genomeBrowser = function() {
    // Size
    var margin = {
      top : 10,
      left : 20,
      bottom : 10,
      right : 20
    }, width = 400, height = 400, innerWidth = function() {
      return width - margin.left - margin.right;
    }, innerHeight = function() {
      return height - margin.top - margin.bottom;
    },

    // Data
    data = [],

    // Parameters,
    maxPosition = 1,

    // Scales
    xScale = d3.scale.linear().domain([
        0, maxPosition
    ]),

    // Start with empty selections
    svg = d3.select(), g = d3.select();

    // Create a closure
    function gb(selection) {
      selection.each(function(d) {
        // Set the data
        data = d;

        // Select the svg element, if it exists
        svg = d3.select(this).selectAll("svg").data([
          data
        ]);

        // Otherwise create the skeletal chart
        g = svg.enter().append("svg").append("g").attr("transform", "translate(" + margin.left + "," + margin.top + ")");

        // Set width and height
        setWidth(width);
        setHeight(height);

        // Add groups for z order
        g.append("g").attr("id", "geneGroup");

        g.append("line").attr("id", "chromosomeLine").style("stroke", "black");

        g.append("text").attr("id", "chromosomeLabel").attr("y", 8).attr("font-size", "small");

        // Register the call back for the selection of a gene from the list
        dispatch.on("select_variant.genomeBrowser", selectVariant);
      });
    }

    /**
     * scale the x area of the gene view
     */
    function geneWidth(gene) {
      return d3.max([
          xScale(gene.end) - xScale(gene.start), 2
      ]);
    }

    /**
     * scale the y area of the gene view
     */
    function geneHeight(gene) {
      return gene.values.length + 1;
    }

    /**
     * render the chromosome area
     */
    function drawChromosome() {
      var line = g.select("#chromosomeLine");

      line.transition().attr("x1", xScale.range()[0]).attr("y1", 10).attr("x2", xScale.range()[1]).attr("y2", 10);

      g.selectAll(".gene").select("rect").transition().attr("x", function(d) {
        return xScale(d.start);
      }).attr("width", function(d) {
        return geneWidth(d);
      });
    }

    /**
     * handler for the selection of a variant from the gene list pane
     * 
     */
    function selectVariant(variantData) {
      selectGene(variantData);

      /*
       * Show variants on this chromosome var gVariant = g.selectAll(".variant")
       * .data(data.filter(function(d) { return d.chromosome ===
       * variant.chromosome; })) .enter().append("g") .attr("class", "variant")
       * .attr("transform", function(d) { return "translate( " + xScale(d.gPos) +
       * ",10)"; }) .on("click", function(d) { // Fire callbacks
       * dispatch.select_variant(d); });
       * 
       * gVariant.append("circle") .attr("r", 5) .style("stroke", "black")
       * .style("fill", "grey");
       * 
       * gVariant.append("text") .attr("dy", "1.2em") .attr("font-size",
       * "small") .attr("text-anchor", "middle") .text(function(d) { return
       * d.geneName; });
       */
    }

    /**
     * handler for the selection of a gene from the gene list pane
     * 
     */
    function selectGene(selectedVariantData) {
      // get the data for the gene selected
      data = selectedVariantData.selectedVariant;

      // Get the chromosome
      var chromosome = data.filter(function(d) {
        return d.geneName === geneName;
      })[0].chromosome;

      // Get the genes represented on this chromosome
      var genes = d3.nest().key(function(d) {
        return d.geneName;
      }).entries(data.filter(function(d) {
        return d.chromosome === chromosome;
      }));

      // Get the position range for each gene
      genes.forEach(function(d) {
        d.start = d3.min(d.values, function(e) {
          return e.gPos;
        });
        d.end = d3.max(d.values, function(e) {
          return e.gPos;
        });
      });

      // Show the genes
      var gene = g.select("#geneGroup").selectAll(".gene").data(genes, function(d) {
        return d.key;
      });

      var gGene = gene.enter().append("g").attr("class", "gene").attr("transform", "translate(0,10)").on("click", function(d) {
        // Fire callbacks
        dispatch.geneDataListener(d.key);
      });

      gGene.append("rect").attr("x", function(d) {
        return xScale(d.start);
      }).attr("y", 0).attr("width", function(d) {
        return geneWidth(d);
      }).attr("height", function(d) {
        return geneHeight(d);
      }).attr("fill", "darkgrey");

      gGene.append("text").attr("x", function(d) {
        return xScale((d.start + d.end) / 2);
      }).attr("y", function(d) {
        return geneHeight(d);
      }).attr("dy", "1em").attr("font-size", "small").attr("text-anchor", "middle").text(function(d) {
        return d.key;
      });

      gene.select("rect").style("stroke", function(d) {
        return d.key === geneName ? "black" : "none";
      });

      gene.select("text").attr("visibility", function(d) {
        return d.key === geneName ? "visible" : "hidden";
      });

      gene.exit().remove();

      // render the chromosome
      drawChromosome();

      // Change label
      g.select("#chromosomeLabel").text("Chromosome " + chromosome);
    }

    /**
     * sets the width used for rendering the chromosome
     * 
     */
    function setWidth(_) {
      width = _;

      svg.attr("width", width);

      xScale.range([
          0, innerWidth()
      ]);

      drawChromosome();
    }

    /**
     * sets the height used for rendering the chromosome
     */
    function setHeight(_) {
      height = _;
      svg.attr("height", height);
    }

    /**
     * width accessor function
     */
    gb.width = function(_) {
      if (!arguments.length)
        return width;

      setWidth(_);

      return gb;
    };

    /**
     * height accessor function
     */
    gb.height = function(_) {
      if (!arguments.length)
        return height;

      setHeight(_);

      return gb;
    };

    /**
     * max position accessor function
     */
    gb.maxPosition = function(_) {
      if (!arguments.length)
        return maxPosition;

      maxPosition = _;

      xScale.domain([
          0, maxPosition
      ]);

      drawChromosome();

      return gb;
    };

    // Return closure
    return gb;
  };
})();
