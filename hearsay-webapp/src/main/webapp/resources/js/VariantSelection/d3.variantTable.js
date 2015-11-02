(function() {
  d3.variantTable = function() {
    // Size
    var width = 200,

    // Data
    data = [], variants = [], selectedVariant, geneName,

    // Parameters
    sortBy = "hgvsName", order = [],

    // Scales
    effectMap = d3.map(), effectColor = d3.scale.ordinal().domain(d3.range(1, 7)).range(colorbrewer.RdBu[7]),
    // .range(colorbrewer.Reds[7].reverse()),
    computedClassColor = d3.scale.ordinal().domain([
        "A", "B", "C", "D", "E", "F"
    ]).range(colorbrewer.RdBu[6]), frequencyColor = d3.scale.threshold().domain([
        0.0001, 0.001, 0.01, 0.1
    ]).range(colorbrewer.RdBu[5]),
    // .range(colorbrewer.Reds[5].reverse()),

    // Layout
    columnWidths = [],

    // Start with empty selections
    table = d3.select();

    // Create a closure
    function vt(selection) {
      selection.each(function(d) {
        // Set the data
        data = d;
        variants = data.slice();

        // Set effect map
        effectMap.set("nonsense", 1);
        effectMap.set("stoploss", 1);
        effectMap.set("splice-site", 1);
        effectMap.set("splice-site-UTR", 1);
        effectMap.set("nonsense indel", 1);
        effectMap.set("boundary-crossing indel", 1);

        effectMap.set("missense", 2);
        effectMap.set("non-frameshifting indel", 2);

        effectMap.set("splice-site-UTR-3", 3);
        effectMap.set("splice-site-UTR-5", 3);
        effectMap.set("splice-site-UTR", 3);

        effectMap.set("synonymous indel", 4);
        effectMap.set("synonymous", 4);

        effectMap.set("UTR-3", 5);
        effectMap.set("UTR-5", 5);
        effectMap.set("UTR", 5);

        effectMap.set("intron", 6);

        effectMap.set("potential RNA-editing site", 7);

        // Select the div element, if it exists
        table = d3.select(this).selectAll("div").data([
          0
        ]).enter().append("div").append("table").style("width", "100%").style("font-size", "x-small").style("empty-cell", "show").style(
            "border-spacing", "0px 2px").style("cursor", "default");
        // .style("table-layout", "fixed");

        // Sort variants
        sortVariants();

        // Draw
        drawHeader();
        drawVariants();

        // Get column widths to keep constant when filtering
        table.select("tr").selectAll("td").each(function() {
          columnWidths.push(this.offsetWidth);
        });

        // Register callback
        dispatch.on("highlight_variant.variantTable", highlightVariant)
        dispatch.on("unhighlight_variant.variantTable", unhighlightVariant)
        dispatch.on("select_variant.variantTable", selectVariant);
        dispatch.on("select_gene.variantTable", selectGene);
      });
    }

    function highlightVariant(variant) {
      // Highlight
      table.selectAll("tr").filter(function(d) {
        return d === variant;
      }).select("td").style("background-color", function(d) {
        return d === selectedVariant ? "grey" : "lightgrey";
      });
    }

    function unhighlightVariant(variant) {
      // Unhighlight
      table.selectAll("tr").filter(function(d) {
        return d === variant;
      }).select("td").style("background-color", function(d) {
        return d === selectedVariant ? "grey" : "transparent";
      });
    }

    function selectVariant(selectedVariantData) {
      // Set the selected variant
      selectedVariant = selectedVariantData.selectedVariant;

      // Unhighlight
      table.selectAll("tr").select("td").style("background-color", "transparent");

      // Highlight
      table.selectAll("tr").filter(function(d) {
        return d === selectedVariant;
      }).select("td").style("background-color", "grey");
    }

    function selectGene(selectedGeneData) {
      // Set the current gene name
      geneName = selectedGeneData.selectedVariant.geneName;

      // Unhighlight
      table.selectAll("tr").select("td").style("border", "none");

      // Highlight
      table.selectAll("tr").filter(function(d) {
        return d.geneName === geneName;
      }).select("td").style("border", "1px solid grey");
    }

    function filterVariants() {
      // Filter values
      var hgvsNameFilter = d3.select("#HGVSNameFilter").node().value.toLowerCase(), geneNameFilter = d3.select("#GeneNameFilter").node().value
          .toLowerCase(), variantEffectFilter = d3.select("#VariantEffectFilter").node().value.toLowerCase(), computedClassFilter = d3
          .select("#ComputedClassFilter").node().value.toLowerCase(), frequencyFilter = d3.select("#FrequencyFilter").node().value;

      // Filter variants
      variants = data.slice().filter(
          function(d) {
            // Add filters here
            return (hgvsNameFilter === "" || d.hgvsName.toLowerCase().indexOf(hgvsNameFilter) !== -1)
                && (geneNameFilter === "" || d.geneName.toLowerCase().indexOf(geneNameFilter) !== -1)
                && (variantEffectFilter === "" || d.variantEffect.toLowerCase().indexOf(variantEffectFilter) !== -1)
                && (computedClassFilter === "" || d.computedClass.toLowerCase() <= computedClassFilter)
                && (frequencyFilter === "" || d.tgFreq * 100 <= +frequencyFilter);
          });
    }

    function sortVariants() {
      // Sort variants
      switch (sortBy) {
        case "hgvsName":
          variants.sort(function(a, b) {
            return d3.ascending(a.hgvsName.toLowerCase(), b.hgvsName.toLowerCase());
          });
          break;

        case "geneName":
          variants.sort(function(a, b) {
            return d3.ascending(a.geneName.toLowerCase(), b.geneName.toLowerCase());
          });
          break;

        case "variantEffect":
          variants.sort(function(a, b) {
            return d3.ascending(effectMap.get(a.variantEffect), effectMap.get(b.variantEffect));
          });
          break;

        case "computedClass":
          variants.sort(function(a, b) {
            return d3.ascending(a.computedClass, b.computedClass);
          });
          break;

        case "frequency":
          variants.sort(function(a, b) {
            return d3.ascending(a.tgFreq, b.tgFreq);
          });
          break;
      }
    }

    function showVariantAttributes() {
      // Get all selected attributes
      var attributes = [];
      table.selectAll(".checkbox").each(function() {
        if (this.checked)
          attributes.push(this.value);
      });

      var colors = attributes.map(function(d) {
        switch (d) {
          case "variantEffect":
            return function(d) {
              return effectColor(effectMap.get(d.variantEffect));
            };

          case "computedClass":
            return function(d) {
              return computedClassColor(d.computedClass);
            };

          case "frequency":
            return function(d) {
              return frequencyColor(d.tgFreq);
            };
        }
      });

      // Fire callback
      dispatch.show_variant_attributes(colors);
    }

    function drawHeader() {
      var head = table.append("thead");

      // Add column headers
      var h1 = head.append("tr");

      var headers = [
          {
            name : "HGVS name",
            sortBy : "hgvsName",
            align : "left"
          }, {
            name : "Gene",
            sortBy : "geneName",
            align : "left"
          }, {
            name : "Effect",
            sortBy : "variantEffect",
            align : "left"
          }, {
            name : "Computed class",
            sortBy : "computedClass",
            align : "left"
          }, {
            name : "Frequency",
            sortBy : "frequency",
            align : "right"
          }
      ];

      // Placeholder for selection indicator
      h1.append("th");

      // Create headers
      h1.selectAll("header").data(headers).enter().append("th").text(function(d) {
        return d.name;
      }).style("text-align", function(d) {
        return d.align;
      }).style("cursor", "ns-resize").on("mouseover", function() {
        d3.select(this).style("background-color", "lightgrey");
      }).on("mouseout", function() {
        d3.select(this).style("background-color", "transparent")
      }).on("click", function(d) {
        // Sort by this column
        sortBy = d.sortBy;
        sortVariants();
        drawVariants();
      });

      // Column filters
      var h2 = head.append("tr");

      var filters = [
          "HGVSNameFilter", "GeneNameFilter", "VariantEffectFilter", "ComputedClassFilter", "FrequencyFilter"
      ];

      // Placeholder for selection indicator
      h2.append("th");

      // Create filters
      h2.selectAll("filter").data(filters).enter().append("th").style("padding-right", "4px").append("input").attr("id", function(d) {
        return d;
      }).attr("type", "text").attr("autocomplete", "on").style("width", "100%").on("input", function() {
        filterVariants();
        sortVariants();
        drawVariants();
      });

      // Column checkboxes
      var h3 = head.append("tr");

      var checkboxes = [
          "variantEffect", "computedClass", "frequency"
      ];

      // Placeholders for selection indicator, hgvs name, and gene name
      h3.append("th").text("Show in gene browser: ").attr("colspan", 3).style("text-align", "right").style("font-weight", "normal");

      // Create checkboxes
      h3.selectAll("checkbox").data(checkboxes).enter().append("th").append("input").attr("class", "checkbox").attr("type", "checkbox")
          .attr("value", function(d) {
            return d;
          }).attr("checked", function(d, i) {
            return i === 0 ? true : null;
          }).on("click", showVariantAttributes);
    }

    function drawVariants() {
      // Clear table
      table.selectAll("table > tr").remove();

      // Bind variant data
      var variant = table.selectAll("table > tr").data(variants);

      // Add row per variant
      var variantEnter = variant.enter().append("tr").on("mouseover", function(d) {
        dispatch.highlight_variant(d);
      }).on("mouseout", function(d) {
        dispatch.unhighlight_variant(d);
      }).on("click", function(d) {
        // Fire callbacks
        showVariantAttributes();
        dispatch.gene_transcript_data_listener(d);
        dispatch.gene_description_data_listener(d);
      });

      // XXX: Move styling to css file
      var padding = "4px";

      // Split HGVS name into pieces to fit better
      function splitHGVS(hgvs) {
        // Split name by colon
        s = hgvs.split(":");

        // Split genome coordinate from nucleotide changes
        var i;
        for (i = s[1].length - 1; i >= 0; i--) {
          if (!isNaN(+s[1][i]))
            break;
        }

        // Split nucleotide change into multiple lines if too long
        var nc = s[1].slice(i + 1).match(/.{1,10}/g);

        // Return strings
        return [
            s[0] + ":", s[1].slice(0, i + 1)
        ].concat(nc);
      }

      // Format frequency
      function formatFrequency(d) {
        var format = d3.format(".2f");
        return format(d * 100);
        /*
         * // XXX: This keeps all digits...
         *  // Get number of digits var g = d3.format("g"), l = g(d).length;
         *  // Multiply and truncate based on digits var p = d3.format("." + (l -
         * 4) + "%");
         * 
         * return p(d);
         */
      }
      ;

      // Background color for non-color-mapped column
      var background = "#eee";

      // Add columns

      // Selection marker
      variantEnter.append("td").style("padding-left", "10px").style("white-space", "nowrap").style("background-color", function(d) {
        return d === selectedVariant ? "grey" : "transparent";
      }).style("border", function(d) {
        return d.geneName === geneName ? "1px solid grey" : "none";
      });

      // HGVS name
      variantEnter.append("td").style("padding", padding).style("background-color", background).selectAll("div").data(function(d) {
        return splitHGVS(d.hgvsName);
      }).enter().append("div").text(function(d) {
        return d;
      }).style("font-weight", function(d, i) {
        return i === 0 ? "bold" : null;
      });

      // Gene name
      variantEnter.append("td").text(function(d) {
        return d.geneName;
      }).style("padding", padding).style("background-color", background).style("font-weight", "bold");

      var shadow = "-1px -1px 1px " + background + ",1px -1px 1px " + background + ",-1px 1px 1px " + background + ",1px 1px 1px "
          + background;

      // Variant effect
      variantEnter.append("td").text(function(d) {
        return d.variantEffect;
      }).style("padding", padding).style("background-color", function(d) {
        return effectColor(effectMap.get(d.variantEffect));
      }).style("font-weight", "bold").style("text-shadow", shadow);

      // Computed class
      variantEnter.append("td").text(function(d) {
        return d.computedClass;
      }).style("padding", padding).style("background-color", function(d) {
        return computedClassColor(d.computedClass);
      }).style("font-weight", "bold").style("text-shadow", shadow);

      // Frequency
      variantEnter.append("td").text(function(d) {
        return formatFrequency(d.tgFreq)
      }).style("padding", padding).style("background-color", function(d) {
        return frequencyColor(d.tgFreq);
      }).style("font-weight", "bold").style("text-align", "right").style("text-shadow", shadow);

      // Placeholder to take up space at the end
      variantEnter.append("td").style("width", "100%");

      // Set column widths, if already computed
      if (columnWidths.length > 0) {
        // Set the widths for the column headers
        table.select("tr").selectAll("th").style("width", function(d, i) {
          return columnWidths[i] + "px";
        });

        // Use fixed table layout
        table.style("table-layout", "fixed");
      }
    }

    vt.width = function(_) {
      if (!arguments.length)
        return width;
      width = _;
      // table.style("width", width + "px");
      return vt;
    };

    // Return closure
    return vt;
  };
})();
