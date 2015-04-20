(function() {
  d3.variantList = function() {
    // Size
    var margin = {
      top : 10,
      left : 5,
      bottom : 10,
      right : 5
    }, width = 200, height = 200, innerWidth = function() {
      return width - margin.left - margin.right;
    }, innerHeight = function() {
      return height - margin.top - margin.bottom;
    },

    // Data
    data = [], activeVariants = [], geneName = "",

    // Parameters
    glyphSize = 10, glyphSpacing = 8,
    // legendHeight = 130,
    legendHeight = 30, frequencies = [
      "tgFreq"
    ], sortBy = "frequency", filterByGene = false, transitionDuration = 500,

    // Scales
    yScale = d3.scale.ordinal(),
    // Color scales taken from a combination of 5- and 6-valued diverging and
    // sequential scales from colorbrewer
    computedClassScale = d3.scale.ordinal().domain([
        "F", "E", "D", "C", "B", "A"
    ]).range([
        "#2c7bb6", "#abd9e9", "#ffeda0", "#fdae61", "#f46d43", "#d73027"
    ]), userClassScale = d3.scale.ordinal().domain([
        "KB", "LB", "VUS", "LP", "KP"
    ]).range([
        "#2c7bb6", "#abd9e9", "#ffeda0", "#f46d43", "#d73027"
    ]), shapeScale = d3.scale.ordinal().domain([
        "snp", "del", "ins"
    ]).range([
        "square", "circle", "cross"
    ]), frequencyScale = d3.scale.log().domain([
        1, 100000
    ]), frequencyColor = d3.scale.threshold().domain([
        0.0001, 0.001, 0.01, 0.1
    ]).range([
        "#006d2c", "#31a354", "#74c476", "#a1d99b", "#c7e9c0"
    ]), frequencyToLog = d3.scale.linear().domain([
        0, 1
    ]).range(frequencyScale.domain()),

    // Layout
    maxNameLength = 0,

    // Start with empty selections
    svg = d3.select(), g = d3.select();

    // Create a closure
    function vl(selection) {
      selection.each(function(d) {
        // Set the data
        data = d;
        activeVariants = data;

        // Select the svg element, if they exist
        svg = d3.select(this).selectAll("svg").data([
          0
        ]);

        // Otherwise create the skeletal chart
        g = svg.enter().append("svg").append("g").attr("transform", "translate(" + margin.left + "," + margin.top + ")");

        // Add group for variants
        g.append("g").attr("class", "variant").append("clipPath").attr("id", "clipRight").append("rect").attr("y", -100).attr("width", 200)
            .attr("height", 200);

        // Add group for legend
        g.append("g").attr("class", "legend");

        maxNameLength = computeMaxNameLength();

        // Set the width and height
        setWidth(width);
        computeHeight();

        // Sort
        sortVariants();

        // Draw
        // drawLegend();
        drawVariants();

        // Register callback
        dispatch.on("select_variant.variantList", selectVariant);
        dispatch.on("select_gene.variantList", selectGene);
      });
    }

    function sortVariants() {
      // Set the active variants
      activeVariants = (filterByGene && geneName != "") ? data.filter(function(d) {
        return d.geneName === geneName;
      }) : data;

      var n = activeVariants.length;

      var order = [];
      switch (sortBy) {
        case "frequency":
          order = d3.range(n).sort(function(a, b) {
            return frequencyToLog(activeVariants[a][frequencies[0]]) - frequencyToLog(activeVariants[b][frequencies[0]]);
          });
          break;

        case "computedClass":
          order = d3.range(n).sort(
              function(a, b) {
                return computedClassScale.domain().indexOf(activeVariants[b].computedClass)
                    - computedClassScale.domain().indexOf(activeVariants[a].computedClass);
              });
          break;

        case "userClass":
          order = d3.range(n).sort(
              function(a, b) {
                return userClassScale.domain().indexOf(activeVariants[b].userClass)
                    - userClassScale.domain().indexOf(activeVariants[a].userClass);
              });
          break;

        case "type":
          order = d3.range(n).sort(function(a, b) {
            return shapeScale.domain().indexOf(activeVariants[b].type) - shapeScale.domain().indexOf(activeVariants[a].type);
          });
          break;

        case "hgvsName":
          order = d3.range(n).sort(function(a, b) {
            return d3.ascending(activeVariants[a].hgvsName.toLowerCase(), activeVariants[b].hgvsName.toLowerCase());
          });
          break;

        case "position":
          order = d3.range(n).sort(
              function(a, b) {
                var aNum = +activeVariants[a].chromosome;
                var bNum = +activeVariants[b].chromosome;

                if (aNum && bNum) {
                  // Sort numerically
                  return aNum < bNum ? -1 : aNum > bNum ? 1 : d3.ascending(activeVariants[a].gPos, activeVariants[b].gPos);
                } else if (aNum) {
                  // Number before text
                  return -1;
                } else if (bNum) {
                  // Number before text
                  return 1;
                } else {
                  // Sort alphabetically
                  return activeVariants[a].chromosome.toLowerCase() < activeVariants[b].chromosome.toLowerCase() ? -1
                      : activeVariants[a].chromosome.toLowerCase() > activeVariants[b].chromosome.toLowerCase() ? 1 : d3.ascending(
                          activeVariants[a].gPos, activeVariants[b].gPos);
                }
              });
          break;

        default:
          order = d3.range(n);
      }

      yScale.domain(order);
    }

    function drawLegend() {
      var legend = g.select(".legend");

      var maxW = 200;
      var minW = 50;
      var duration = 1000;
      var gSize = 10;

      // Add type to legend
      var x = 55;
      var y = 5;
      var typeTransform = function(d, i) {
        return "translate(" + glyphX(i) + "," + (-gSize / 2) + ")";
      };

      var glyphX = d3.scale.linear().domain([
          0, shapeScale.domain().length - 1
      ]).range([
          x, x + gSize * 1.5 * shapeScale.domain().length
      ]);

      var t = legend.selectAll(".type").data([
        0
      ]);

      // Update
      typeUpdate = t.transition().duration(duration).attr("transform", "translate(0," + y + ")");

      typeUpdate.selectAll("g").attr("transform", typeTransform);

      // Enter
      typeEnter = t.enter().append("g").attr("class", "type").attr("transform", "translate(0," + y + ")");

      typeEnter.append("text").text("Type:");

      gTypeEnter = typeEnter.selectAll("g").data(shapeScale.domain()).enter().append("g").attr("transform", typeTransform);

      // Draw a symbol for each type
      gTypeEnter.append("path").attr("d", d3.svg.symbol().size(Math.pow(gSize, 2)).type(function(d) {
        return shapeScale(d);
      })).style("fill", "none").style("stroke", "black").style("stroke-width", 0.25);

      // Draw text for each type
      gTypeEnter.append("text").text(function(d) {
        return d;
      }).attr("dy", gSize + 5).attr("text-anchor", "middle").style("font-size", "x-small");

      // Add computed class to legend
      x -= gSize / 2;
      y += gSize * 4.5;

      var computedTransform = function(d) {
        return "translate(" + computedX(d) + "," + (-gSize * 1.5) + ")";
      }, userTransform = function(d) {
        return "translate(" + userX(d) + "," + (-gSize / 2) + ")";
      }, computedX = d3.scale.ordinal().domain(computedClassScale.domain()).rangeBands([
          x, x + gSize * 2 * computedClassScale.domain().length
      ]), userX = d3.scale.ordinal().domain(userClassScale.domain()).rangeBands([
          x, x + gSize * 2 * computedClassScale.domain().length
      ]), computedTitle = d3.scale.ordinal().domain(computedClassScale.domain()).range(
          [
              "Almost certainly benign", "Likely benign", "Variant of uncertain significance", "Possibly pathogenic", "Likely pathogenic",
              "Known pathogenic"
          ]), userTitle = d3.scale.ordinal().domain(userClassScale.domain()).range([
          "Known benign", "Likely benign", "Variant of uncertain significance", "Likely pathogenic", "Known pathogenic"
      ]);

      var classEnter = legend.selectAll(".class").data([
        0
      ]).enter().append("g").attr("class", "class").attr("transform", "translate(0," + y + ")");

      classEnter.append("text").text("Class:");

      var computedEnter = classEnter.append("g").attr("class", "computed").selectAll("g").data(computedX.domain()).enter().append("g")
          .attr("transform", computedTransform);

      computedEnter.append("rect").attr("width", computedX.rangeBand()).attr("height", gSize).style("fill", function(d) {
        return computedClassScale(d);
      });

      computedEnter.append("text").text(function(d) {
        return d;
      }).attr("x", computedX.rangeBand() / 2).attr("y", -gSize / 2 + 3).attr("text-anchor", "middle").style("font-size", "x-small");

      computedEnter.append("title").text(function(d) {
        return computedTitle(d);
      });

      var userEnter = classEnter.append("g").attr("class", "user").selectAll("g").data(userX.domain()).enter().append("g").attr(
          "transform", userTransform);

      userEnter.append("rect").attr("width", userX.rangeBand()).attr("height", gSize).style("fill", function(d) {
        return userClassScale(d);
      });

      userEnter.append("text").text(function(d) {
        return d;
      }).attr("x", userX.rangeBand() / 2).attr("y", gSize * 1.5 + 5).attr("text-anchor", "middle").style("font-size", "x-small");

      userEnter.append("title").text(function(d) {
        return userTitle(d);
      });
    }

    function drawVariants(duration) {
      duration = !arguments.length ? 0 : duration;

      // Bind variant data
      var variant = g.select(".variant").selectAll(".variant > g").data(activeVariants, function(d) {
        return d.parentID;
      });

      // Update

      // Variant group
      var variantUpdate = variant.transition().duration(duration).attr("transform", function(d, i) {
        return "translate(0," + yScale(i) + ")";
      });

      // Background rectangle
      variantUpdate.selectAll(".background").attr("y", -(glyphSize + glyphSpacing) / 2).attr("height", glyphSize + glyphSpacing).attr(
          "width", width - 2);

      // Glyph
      var glyphUpdate = variantUpdate.selectAll(".glyph").attr("transform", "translate(" + (maxNameLength + glyphSize) + ",0)");

      glyphUpdate.selectAll("path").attr("d", d3.svg.symbol().size(Math.pow(glyphSize, 2)).type(function(d) {
        return shapeScale(d.type);
      }));

      glyphUpdate.select("circle").attr("r", glyphSize / 4);

      // Frequency bars
      // XXX: Keeping this in case we want to switch back to using
      var frequencyYScale = d3.scale.ordinal().domain(d3.range(frequencies.length)).rangeBands([
          -glyphSize / 2, glyphSize / 2
      ], 0.2);

      variantUpdate.selectAll(".frequency").attr("transform", function(d, i) {
        return "translate(0," + frequencyYScale(i) + ")";
      }).selectAll("rect").attr("x", function(d) {
        return frequencyScale(frequencyToLog(d.v1));
      }).attr("width", function(d) {
        return frequencyScale(frequencyToLog(d.v2)) - frequencyScale(frequencyToLog(d.v1));
      }).attr("height", frequencyYScale.rangeBand());

      // Enter

      // Variant group
      var variantEnter = variant.enter().append("g").attr("transform", function(d, i) {
        return "translate(0," + yScale(i) + ")";
      }).on("mouseover", function(d) {
        if (!d.selected) {
          // Highlight rectangle
          d3.select(this).select("rect").attr("visibility", "visible");
        }
      }).on("mouseout", function(d) {
        if (d.geneName !== geneName) {
          // Hide rectangle
          d3.select(this).select("rect").attr("visibility", "hidden");
        }
      }).on("click", function(d) {
        ClearErrorDisplay();

        errorDisplay("Gene " + d.geneName + " selected.", 0);

        // Fire callbacks
        dispatch.gene_transcript_data_listener(d);
        dispatch.gene_description_data_listener(d);
      });

      // Background rectangle
      variantEnter.append("rect").attr("class", "background").attr("x", -margin.left + 1).attr("y", -(glyphSize + glyphSpacing) / 2).attr(
          "height", glyphSize + glyphSpacing).attr("width", width - 2).attr("visibility", "hidden").attr("pointer-events", "fill").style(
          "fill", "#bbb").style("stroke", "none");

      // Label
      variantEnter.append("text").text(function(d) {
        return d.hgvsName.split(":")[0];
      }).attr("dy", "0.35em").style("font-size", "small");

      // Glyph
      var glyphEnter = variantEnter.append("g").attr("class", "glyph")
          .attr("transform", "translate(" + (maxNameLength + glyphSize) + ",0)");

      glyphEnter.append("path").attr("d", d3.svg.symbol().size(Math.pow(glyphSize, 2)).type(function(d) {
        return shapeScale(d.type);
      })).style("fill", function(d) {
        return d.computedClass ? computedClassScale(d.computedClass) : "white";
      });

      glyphEnter.append("path").attr("d", d3.svg.symbol().size(Math.pow(glyphSize, 2)).type(function(d) {
        return shapeScale(d.type);
      })).style("fill", function(d) {
        return d.userClass ? userClassScale(d.userClass) : "white";
      }).attr("clip-path", "url(#clipRight)");

      // Need to draw outline separately
      glyphEnter.append("path").attr("d", d3.svg.symbol().size(Math.pow(glyphSize, 2)).type(function(d) {
        return shapeScale(d.type);
      })).style("fill", "none").style("stroke", "black").style("stroke-width", 0.25);

      // Frequency bar
      variantEnter.selectAll(".frequency").data(function(d) {
        return frequencies.map(function(e) {
          return d[e];
        });
      }).enter().append("g").attr("class", "frequency").attr("transform", function(d, i) {
        return "translate(0," + frequencyYScale(i) + ")";
      }).selectAll("rect").data(function(d) {
        // Create data for individual rectangles
        var v = [
          d
        ].concat(frequencyColor.domain().filter(function(e) {
          return e > d;
        })).concat(1.0), data = [];

        for (var i = 0; i < v.length - 1; i++) {
          data.push({
            v1 : v[i + 1],
            v2 : v[i]
          });
        }

        return data;
      }).enter().append("rect").attr("x", function(d) {
        return frequencyScale(frequencyToLog(d.v1));
      }).attr("width", function(d) {
        return frequencyScale(frequencyToLog(d.v2)) - frequencyScale(frequencyToLog(d.v1));
      }).attr("height", frequencyYScale.rangeBand()).style("fill", function(d) {
        return frequencyColor(d.v2);
      });

      // Exit

      variant.exit().transition().duration(duration).attr("fill-opacity", 0).attr("stroke-opacity", 0).remove();

      // Selected border, last so always on top
      // XXX: This would be easier if a selected index was maintained...
      var selected = activeVariants.map(function(d) {
        return d.selected;
      }).indexOf(true);
      selected = selected === -1 ? 0 : selected;

      // XXX: Move this to create on selection?
      var selectBorder = g.select(".variant").selectAll(".selectBorder").data([
        0
      ]);

      // Update
      selectBorder.transition().duration(duration).attr("width", width - 2).attr("y", yScale(selected) - (glyphSize + glyphSpacing) / 2)
          .attr("height", glyphSize + glyphSpacing);

      // Enter
      selectBorder.enter().append("rect").attr("class", "selectBorder").attr("x", -margin.left + 1)
          .attr("height", glyphSize + glyphSpacing).attr("width", width - 2).attr("visibility", "hidden").attr("pointer-events", "none")
          .style("fill", "none").style("stroke", "black");

      // Freqency axis
      var x = (frequencyScale.range()[0] + frequencyScale.range()[1]) / 2;
      var transform = function(d) {
        return "translate(" + frequencyScale(frequencyToLog(d)) + "," + 0 + ")";
      };

      var axis = g.selectAll(".frequencyAxis").data([
        0
      ]);

      // Update
      axisUpdate = axis.transition().duration(duration).attr("transform", "translate(0," + (yScale.range()[0] - glyphSize / 2) + ")");

      axisUpdate.select("text").attr("x", x);

      axisUpdate.selectAll("g").attr("transform", transform).select("line").attr("y2", glyphSize);

      // Enter
      var axisEnter = axis.enter().append("g").attr("class", "frequencyAxis").attr("transform",
          "translate(0," + (yScale.range()[0] - glyphSize / 2) + ")");

      axisEnter.append("text").attr("x", x).attr("y", -20).text("Variant frequency").attr("text-anchor", "middle");

      var tickEnter = axisEnter.selectAll("g").data([
        0
      ].concat(frequencyColor.domain())).enter().append("g").attr("transform", transform);

      // tickEnter.append("line")
      // .attr("y2", glyphSize)
      // .style("stroke", "black");

      function formatNumber(d, i) {
        var format = d3.format("g");
        var n = format(d * 100);
        n = n < 1 && n > 0 ? n.slice(1) : n; // Remove leading zero
        n = i === 0 ? n + "%" : n; // Add percent
        return n;
      }

      tickEnter.append("text").text(formatNumber).attr("dy", "-.35em").attr("dx", function(d, i) {
        return i === 0 ? "-.15em" : 0;
      }) // To account for % label
      .attr("text-anchor", "middle").style("font-size", "x-small");
    }

    function selectVariant(selectedVariantData) {
      // Save the data locally
      var variant = selectedVariantData.selectedVariant;

      // / Show this rectangle
      g.selectAll(".variant").filter(function(d) {
        return d === variant;
      }).select("rect").attr("visibility", "visible");

      // Make selected border visible and move it
      g.select(".selectBorder").transition() // Cancel any existing transitions
      .duration(0).attr("visibility", "visible").attr("y", yScale(activeVariants.indexOf(variant)) - (glyphSize + glyphSpacing) / 2);

      // Unselect variant
      // XXX: Could improve performance by storing selected index/name...
      activeVariants.forEach(function(d) {
        d.selected = false;
      });

      // Save selected data
      variant.selected = true;
    }

    function selectGene(selectedGeneData) {
      // Set the current gene name
      geneName = selectedGeneData.selectedVariant.geneName;

      sortVariants();
      computeHeight();
      drawVariants(transitionDuration);

      /*
       * var computedClass = d3.scale.ordinal() .domain(["F", "E", "D", "C",
       * "B", "A"]) .range([0, 1, 2, 3, 4, 5]), userClass = d3.scale.ordinal()
       * .domain(["KB", "LB", "VUS", "LP", "KP"]) .range([0, 1, 2, 3, 4]), shape =
       * d3.scale.ordinal() .domain(["snp", "del", "ins"]) .range([0, 1, 2]);
       * 
       * console.log("["); data.forEach(function(d) { console.log("{");
       * console.log("\"tgFreq\": \"" + d.tgFreq + "\",");
       * console.log("\"ncgFreq\": \"" + d.ncgFreq + "\",");
       * console.log("\"type\": \"" + shape(d.type) + "\",");
       * console.log("\"computedClass\": \"" + computedClass(d.computedClass) +
       * "\","); console.log("\"userClass\": \"" + userClass(d.userClass) +
       * "\""); console.log("},"); }); console.log("]");
       */

      // XXX: MOVE TO drawVariants?
      g.selectAll(".background").attr("visibility", function(d) {
        return d.geneName === geneName ? "visible" : "hidden";
      });

      g.select(".selectBorder").attr("visibility", "hidden");
    }

    // Compute the maximum variant name length
    function computeMaxNameLength() {
      // Create temporary text
      var svgTemp = d3.select("body").append("svg");
      var name = svgTemp.selectAll("text").data(data).enter().append("text").text(function(d) {
        return d.hgvsName.split(":")[0];
      }).style("font-size", "small");

      // Compute the maximum length
      var maxLength = d3.max(name[0], function(d) {
        return d.getComputedTextLength();
      });

      svgTemp.remove();

      return maxLength + glyphSize;
    }

    function setWidth(_) {
      width = _;
      svg.attr("width", width);

      // Flip range
      frequencyScale.range([
          Math.max(innerWidth(), 0), maxNameLength + glyphSize * 2
      ]);
    }

    function computeHeight() {
      height = legendHeight + (activeVariants.length) * (glyphSize + glyphSpacing) + glyphSpacing;

      svg.transition().duration(1000).attr("height", height);

      yScale.rangeBands([
          legendHeight, height - glyphSpacing
      ]);
    }

    // Accessor functions
    vl.width = function(_) {
      if (!arguments.length)
        return width;
      setWidth(_);
      // drawLegend();
      drawVariants(transitionDuration);
      return vl;
    };

    vl.glyphSize = function(_) {
      if (!arguments.length)
        return glyphSize;
      glyphSize = _;
      setWidth(width);
      computeHeight();
      drawVariants();
      return vl;
    };

    vl.sortBy = function(_) {
      if (!arguments.length)
        return sortBy;
      sortBy = _;
      sortVariants();
      drawVariants(transitionDuration);
    };

    vl.filterByGene = function(_) {
      if (!arguments.length)
        return filterByGene;
      filterByGene = _;
      sortVariants();
      computeHeight();
      drawVariants(transitionDuration);
    };

    vl.frequencies = function(_) {
      if (!arguments.length)
        return frequencies;
      frequencies = _.slice();
      sortVariants();
      drawVariants(transitionDuration);
    };

    // Return closure
    return vl;
  };
})();
