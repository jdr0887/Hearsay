(function() {
  d3.variantScatterPlot = function() {
    // Size
    var margin = {
      top : 10,
      left : 10,
      bottom : 10,
      right : 10
    }, width = 100, height = 100, innerWidth = function() {
      return width - margin.left - margin.right;
    }, innerHeight = function() {
      return height - margin.top - margin.bottom;
    },

    // Data
    data = [],

    // Parameters
    glyphSize = 10,

    // Scales
    xScale = d3.scale.log().domain([
        1, 1000
    ]), yScale = d3.scale.log().domain([
        1, 1000
    ]), frequencyToLog = d3.scale.linear().domain([
        0, 1
    ]).range(xScale.domain()),
    // XXX: Should probably share color and shape scales with variant list, etc.
    computedClassScale = d3.scale.ordinal().domain([
        "F", "E", "D", "C", "B", "A"
    ]).range([
        "#2c7bb6", "#abd9e9", "#ffeda0", "#fdae61", "#f46d43", "#d73027"
    ]), userClassScale = d3.scale.ordinal().domain([
        "KB", "LB", "VUS", "LP", "KP"
    ]).range([
        "#2c7bb6", "#abd9e9", "#ffeda0", "#f46d43", "#d73027"
    ]), shapeScale = d3.scale.ordinal().domain([
        "del", "ins", "snp"
    ]).range([
        "circle", "cross", "square"
    ]),

    // Layout
    maxNameLength = 0,

    // Start with empty selections
    svg = d3.select(), g = d3.select();

    // Create a closure
    function vsp(selection) {
      selection.each(function(d) {
        // Set the data
        data = d;

        // Select the svg element, if it exists
        svg = d3.select(this).selectAll("svg").data([
          data
        ]);

        // Otherwise create the skeletal chart
        g = svg.enter().append("svg").append("g").attr("transform", "translate(" + margin.left + "," + margin.top + ")");

        g.append("clipPath").attr("id", "clipLeft").append("rect").attr("x", -glyphSize).attr("y", -glyphSize).attr("width", glyphSize)
            .attr("height", glyphSize * 2);

        g.append("clipPath").attr("id", "clipRight").append("rect").attr("y", -glyphSize).attr("width", glyphSize).attr("height",
            glyphSize * 2);

        // Set scales
        maxNameLength = computeMaxNameLength();

        // Set width and height
        setWidth(width);
        setHeight(height);

        // Draw the variants
        drawVariants();

        // Register callback
        dispatch.on("select_variant.variantScatterPlot", selectVariant);
      });
    }

    function selectVariant(selectedVariantData) {
      // Save the data locally
      var variant = selectedVariantData.selectedVariant;

      // Select variants
      var gVariant = g.selectAll(".variant");

      // Clear previously selected
      gVariant.select("path").style("fill-opacity", 0.5).style("stroke-width", 0.25);

      gVariant.select("text").attr("visibility", "hidden");

      // Highlight
      var gNew = gVariant.filter(function(d) {
        return d === variant;
      });

      gNew.select("path").style("fill-opacity", 1).style("stroke-width", 0.5);

      gNew.select("text").attr("visibility", "visible");

      // Unselect variant
      // XXX: Could improve performance by storing selected index/name...
      data.forEach(function(d) {
        d.selected = false;
      });

      // Save selected data
      variant.selected = true;
    }

    function drawVariants() {
      // Variant glyphs
      var variant = g.selectAll(".variant").data(data);

      var variantEnter = variant.enter().append("g").attr("class", "variant").attr("transform", function(d, i) {
        return "translate(" + xScale(frequencyToLog(d.ncgFreq)) + "," + yScale(frequencyToLog(d.tgFreq)) + ")";
      }).on("mouseover", function(d) {
        if (!d.selected) {
          // Highlight
          var v = d3.select(this);

          // XXX: This is broken on Chrome, works on Firefox...
          v.selectAll("path").style("fill-opacity", 1).style("stroke-width", 0.5);

          v.select("text").attr("visibility", "visible");
        }
      }).on("mouseout", function(d) {
        if (!d.selected) {
          // Normal
          var v = d3.select(this);

          v.selectAll("path").style("fill-opacity", 0.5).style("stroke-width", 0.25);

          v.select("text").attr("visibility", "hidden");
        }
      }).on("click", function(d) {
        // Fire callbacks
        dispatch.gene_transcript_data_listener(d);
        dispatch.gene_description_data_listener(d);
      });

      // Glyph
      variantEnter.append("path").attr("d", d3.svg.symbol().size(Math.pow(glyphSize, 2)).type(function(d) {
        return shapeScale(d.type);
      })).style("fill", function(d) {
        return d.computedClass ? computedClassScale(d.computedClass) : "white";
      }).style("fill-opacity", 0.5).style("stroke", "none").attr("clip-path", "url(#clipLeft)");

      variantEnter.append("path").attr("d", d3.svg.symbol().size(Math.pow(glyphSize, 2)).type(function(d) {
        return shapeScale(d.type);
      })).style("fill", function(d) {
        return d.userClass ? userClassScale(d.userClass) : "white";
      }).style("fill-opacity", 0.5).style("stroke", "none").attr("clip-path", "url(#clipRight)");

      // Need to draw outline separately
      variantEnter.append("path").attr("d", d3.svg.symbol().size(Math.pow(glyphSize, 2)).type(function(d) {
        return shapeScale(d.type);
      })).style("fill", "none").style("stroke", "black").style("stroke-width", 0.25);

      // Label
      variantEnter.append("text").text(function(d) {
        return d.name;
      }).attr("dy", "-0.5em").attr("visibility", "hidden").attr("pointer-events", "none").style("text-anchor", "middle").style("font-size",
          "small");
    }

    // Compute the maximum variant name length
    function computeMaxNameLength() {
      // Create temporary text
      var svgTemp = d3.select("body").append("svg");
      var name = svgTemp.selectAll("text").data(data).enter().append("text").text(function(d) {
        return d.name;
      }).style("font-size", "small");

      // Compute the maximum length
      var maxLength = d3.max(name[0], function(d) {
        return d.getComputedTextLength();
      });

      svgTemp.remove();

      return maxLength;
    }

    function updatePositions() {
      // Move all variants
      var duration = 1000;
      g.selectAll(".variant").transition().duration(duration).attr("transform", function(d, i) {
        return "translate(" + xScale(frequencyToLog(d.ncgFreq)) + "," + yScale(frequencyToLog(d.tgFreq)) + ")";
      });
    }

    function updateSize() {
      // Update all variants
      var duration = 1000;
      g.selectAll(".variant").select("path").transition().duration(duration).attr("d",
          d3.svg.symbol().size(Math.pow(glyphSize, 2)).type(function(d) {
            return shapeScale(d.type);
          }));
    }

    function setWidth(_) {
      width = _;
      svg.attr("width", width);
      xScale.range([
          innerWidth(), 0
      ]);
      updatePositions();
    }

    function setHeight(_) {
      height = _;
      svg.attr("height", height);
      yScale.range([
          0, innerHeight()
      ]);
      updatePositions();
    }

    // Accessor functions
    vsp.width = function(_) {
      if (!arguments.length)
        return width;
      setWidth(_);
      return vsp;
    };

    vsp.height = function(_) {
      if (!arguments.length)
        return height;
      setHeight(_);
      return vsp;
    };

    vsp.glyphSize = function(_) {
      if (!arguments.length)
        return glyphSize;
      glyphSize = _;

      updateSize();

      return vsp;
    };

    // Return closure
    return vsp;
  };
})();
