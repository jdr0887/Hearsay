(function() {
  d3.variantLegend = function() {
    // Size
    var margin = {
      top : 20,
      left : 5,
      bottom : 10,
      right : 5
    }, width = 100, height = 100, innerWidth = function() {
      return width - margin.left - margin.right;
    },

    // Scales
    // XXX: Copied from d3.variantList.js. This needs to be centralized
    // somewhere...
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
        "#006d2c", "31a354", "#74c476", "#a1d99b", "#c7e9c0"
    ]), frequencyToLog = d3.scale.linear().domain([
        0, 1
    ]).range(frequencyScale.domain()),

    // Layout
    // XXX: This should be passed from variantList...
    maxNameLength = 50,

    // Start with empty selection
    svg = d3.select(), g = d3.select();

    // Create a closure
    function vl(selection) {
      selection.each(function(d) {
        // Select the svg element, if it exists
        svg = d3.select(this).selectAll("svg").data([
          0
        ]);

        // Otherwise create the skeletal chart
        g = svg.enter().append("svg").append("g").attr("transform", "translate(" + margin.left + "," + margin.top + ")");

        svg.attr("width", width).attr("height", height);

        // Draw
        drawLegend();
      });
    }

    function drawLegend() {
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

      // Enter
      var typeEnter = g.selectAll(".type").data([
        0
      ]).enter().append("g").attr("class", "type").attr("transform", "translate(0," + y + ")");

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

      // Enter
      var classEnter = g.selectAll(".class").data([
        0
      ]).enter().append("g").attr("class", "class").attr("transform", "translate(0," + y + ")");

      classEnter.append("text").text("Class:");

      classEnter.append("text").text("computed").attr("y", -8).attr("x", x + gSize * 2 * computedClassScale.domain().length + 5).attr(
          "text-anchor", "left").style("font-size", "x-small");

      classEnter.append("text").text("user").attr("y", 3).attr("x", x + gSize * 2 * computedClassScale.domain().length + 5).attr(
          "text-anchor", "left").style("font-size", "x-small");

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

    function setWidth(_) {
      width = _;
      svg.attr("width", width);

      // Flip range
      // frequencyScale.range([Math.max(innerWidth(), 0), maxNameLength +
      // glyphSize * 2]);
      frequencyScale.range([
          Math.max(innerWidth(), 0), maxNameLength
      ]);
    }

    // Accessor functions
    vl.width = function(_) {
      if (!arguments.length)
        return width;
      setWidth(_);
      drawLegend();
      return vl;
    };

    // Return closure
    return vl;
  };
})();