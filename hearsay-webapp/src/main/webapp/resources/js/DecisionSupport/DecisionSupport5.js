/**
 * main entry point for rendering this area
 * 
 * @param decisionSupportingEvidenceTab
 * @param decisionSupportACMGCriteriaEvalTab
 * @param inUserName
 */
function renderDecisionSupportEvidence(decisionSupportingEvidenceTab, inUserName) {
  // save the user name
  userName = inUserName;

  // render when a variant is selected
  dispatch.on("select_gene.ACMGEvidence", ACMGEvidenceHandler);
};

/**
 * event handler for when a user selects a variant. calls sub functions to draw
 * all areas
 * 
 * @param dataSet
 */
function ACMGEvidenceHandler(dataSet) {
  // no data then return
  if (dataSet == '')
    return;

  // did we get a data error
  if (typeof dataSet["error"] != 'undefined')
    displayError(dataSet["error"]);
  else {
    // draw the title
    drawEvidenceTitle(dataSet.selectedVariant.geneName, dataSet.selectedVariant.hgvsName);

    // draw the data areas retrieved from abroad (NCGENES, CANVAS, etc.)
    drawGeneVariantDataEntry(dataSet);

    // draw the gene curation area
    drawGeneCurationAreaExpandable(dataSet.selectedVariant.geneName);

    // draw the targeted evidence data entry area
    drawMoreEvidenceDataEntry(dataSet.selectedVariant.hgvsName);

    // draws the recomendation after criteria analysis
    drawCriteriaEvaluationAnalysis(dataSet.selectedVariant.hgvsName);

    // draw the ACMG Criteria Assessment area
    drawACMGCriteriaCollectionAssessmentTable(dataSet.selectedVariant.hgvsName);

    // draw the ACMG Criteria Evidence area
    drawACMGCriteriaEvidenceExpandable(dataSet.selectedVariant.hgvsName);
  }
}

/**
 * draws the title area
 * 
 * @param dataSet
 */
function drawEvidenceTitle(geneName, hgvsName) {
  // get a reference to the
  var container = d3.select("#DecisionSupportEvidenceTitle");

  // remove the svg rendered area if it exists
  container.select("svg").remove();

  // get a svg region for the title
  var titleArea = container.append("svg").attr("width", "100%").attr("height", "45px");

  // create a group for the box and the text
  var grp = titleArea.append("g");

  // create a line for section separation
  var rectangle = grp.append("rect").attr("x", 2).attr("y", 20).attr("width", "100%").attr("height", 1).attr("stroke-width", 1).attr(
      "stroke", "#C0C0C0");

  // some title text
  var textArea = grp.append("text").attr("x", 0).attr("y", 12).attr("fill", "black").attr("font-style", "bold").attr("font-size", "16")
      .text("Gene: " + geneName + ", " + hgvsName);

  // even more title text
  var textArea = grp.append("text").attr("x", 700).attr("y", 12).attr("fill", "black").attr("font-style", "bold").attr("font-size", "14")
      .text("Analyst: " + userName + " | UNC-CH");
}

/**
 * renders the Gene/Variant data entry area
 * 
 * @param dataSet
 */
function drawGeneVariantDataEntry(dataSet) {
  // get a reference to the
  var container = d3.select("#DecisionSupportGeneVariantDataEntry");

  // remove the svg rendered area if it exists
  container.select("div").remove();

  // get a reference to the area we are going to render in
  var drawArea = container.append("div").attr("width", "100%");

  // create the table titles
  var tableProps = [
    "Variant IDs and info"
  ];

  // create the column headers array
  var colHeader = [
    [
        [
            "HGVS Name", true, "width: 100px;"
        ], [
            "Location", true, "width: 100px;"
        ], [
            "Loc Var ID", true, "width: 100px;"
        ], [
            "ACC Number", true, "width: 100px;"
        ], [
            "ClinVar Accession", true, "width: 100px;"
        ], [
            "rs ID", true, "width: 100px;"
        ], [
            "Effect", true, "width: 100px;"
        ], [
            "Intersecting transcripts", true, "width: 400px;"
        ], [
            "ClinGen Classification", false, "width: 100px;"
        ], [
            "Comment", false, "width: 300px;"
        ]
    ]
  ];

  // get the raw evidence data
  var retVal = launchDataRequest("getEvidenceData", "VariantID=" + dataSet.selectedVariant.hgvsName);

  // did we get a data error
  if (retVal["error"] == undefined && retVal != "") {
    // get a ref to the array
    var baseArr = retVal["data"][0];

    // load the rows with the retrieved data
    var rowData = [
      [
          dataSet.selectedVariant.hgvsName, dataSet.selectedVariant.gPos, dataSet.selectedVariant.locvarid, dataSet.selectedVariant.ACCNum,
          "", dataSet.selectedVariant.rsID, dataSet.selectedVariant.variantEffect, dataSet.selectedVariant.transcripts,
          baseArr["ClinGenClassification"], baseArr["Comment"]
      ]
    ];

    // now save the collected information to the DB for this variant
    persistAsMoreEvidence(dataSet.selectedVariant.hgvsName, EVIDENCE_TYPE.Variant_Class, dataSet.selectedVariant.variantEffect, "HearSay",
        "Automatically entered", true);
    persistAsMoreEvidence(dataSet.selectedVariant.hgvsName, EVIDENCE_TYPE.NCGENES_frequency, dataSet.selectedVariant.ncgFreq, "HearSay",
        "Automatically entered", true);
    persistAsMoreEvidence(dataSet.selectedVariant.hgvsName, EVIDENCE_TYPE.dbSNP_frequency, baseArr["dbSNP"], "HearSay",
        "Automatically entered", true);
    persistAsMoreEvidence(dataSet.selectedVariant.hgvsName, EVIDENCE_TYPE.ThousandG_frequency, baseArr["ThousandG"], "HearSay",
        "Automatically entered", true);
    persistAsMoreEvidence(dataSet.selectedVariant.hgvsName, EVIDENCE_TYPE.ExAC_frequency, baseArr["ExACFreq"], "HearSay",
        "Automatically entered", true);
  } else {
    // was there no data
    if (retVal["error"] == "No data") {
      // create the rows with just the stuff we know
      var rowData = [
        [
            dataSet.selectedVariant.hgvsName, dataSet.selectedVariant.gPos, dataSet.selectedVariant.locvarid,
            dataSet.selectedVariant.ACCNum, "", dataSet.selectedVariant.rsID, dataSet.selectedVariant.variantEffect,
            dataSet.selectedVariant.transcripts, "", ""
        ]
      ];

      // now save the collected information to the DB for this variant
      persistAsMoreEvidence(dataSet.selectedVariant.hgvsName, EVIDENCE_TYPE.Variant_Class, dataSet.selectedVariant.variantEffect,
          "HearSay", "Automatically entered", true);
      persistAsMoreEvidence(dataSet.selectedVariant.hgvsName, EVIDENCE_TYPE.NCGENES_frequency, dataSet.selectedVariant.ncgFreq, "HearSay",
          "Automatically entered", true);
    } else {
      // show the error
      errorDisplay("drawGeneVariantDataEntry - getEvidenceData() - " + retVal["error"], 1);

      // display the error on the page
      drawArea.attr("class", "err").text("Error: " + retVal["error"]);

      // no need to continue
      return;
    }
  }

  // create the form tag
  var form = drawArea.append("form").style("margin-top", "-20px").attr("id", "geneVariantDataEntryForm")

  // save the key in the form
  form.append("input").attr("type", "hidden").attr("name", "VariantID").attr("id", "VariantID").attr("value",
      dataSet.selectedVariant.hgvsName);

  // draw the table for each set of data
  for (var j = 0; j < tableProps.length; j++)
    drawGeneVariantDataTable(form, tableProps[j], colHeader[j], rowData[j])

  // create a span for the button andd error text
  var formSpan = form.append("div").style("margin-top", "15px").append("span");

  // create the button
  formSpan.append("input").attr("type", "button").attr("value", "Save").on("click", function(d) {
    submitGeneVariantDataForm(formSpan, dataSet);
  });
}

/**
 * function to draw the Gene/Variant data HTML table
 * 
 * @param c -
 *          d3 container
 * @param tableProps -
 *          properties of the table like name and editable
 * @param colHeader -
 *          the array of column names
 * @param rowData -
 *          the data for the row
 */
function drawGeneVariantDataTable(c, tableProps, colHeader, rowData) {
  // label the new table if we have one
  if (tableProps != "")
    c.append("div").style("margin-top", "10px").style("margin-bottom", "5px").text(tableProps);

  // create a table, header and body
  var table = c.append("table").attr("class", "standard");
  var thead = table.append("thead");
  var tbody = table.append("tbody");

  // populate the header
  thead.append("tr").selectAll("th").data(colHeader).enter().append("th").attr("class", "dataentry").text(function(d) {
    return d[0];
  });

  // create all the rows and columns in the table
  var tb = tbody.append("tr").selectAll("td").data(rowData).enter().append("td").html(
      function(d, i) {
        // if this is a read only cell
        if (colHeader[i][1] == true) {
          // is this an array of values. right now only happens with transcripts
          if (Object.prototype.toString.call(d) === '[object Array]') {
            // start a final string
            var str = "";

            // for each array element
            for (var j = 0; j < d.length; j++)
              str += d[j].transcript + ", ";

            // return to the caller
            return str.substring(0, str.length - 2);
          }
          // else just return whats there
          else
            return d;
        }
        // else return a text box for input
        else
          return "<input style=\"" + colHeader[i][2] + "\" type=\"text\" value=\"" + d + "\" id=\"" + colHeader[i][0].replace(/\s+/g, '')
              + "\" " + (colHeader[i][1] ? "readonly" : "") + "/>";
      });
}

/**
 * function to submit data on the Gene/Variant data form
 * 
 * @param spn
 */
function submitGeneVariantDataForm(spn, dataSet) {
  // remove the old error msg
  spn.select("#EvidenceEntryErr").remove();

  // clear the span for the errors
  spn.select("#GeneVariantEntryErr").remove();

  // create a new span for the errors
  var errSpn = spn.append("span").attr("id", "GeneVariantEntryErr").attr("style", "margin-left: 20px;");

  // init the query string
  var queryString = "";

  // get all the form controls
  var formCtrls = $("#geneVariantDataEntryForm :input").each(
      function(index, element) {
        // build up the query string on the fly
        queryString = queryString + ((index == 0) ? "" : "&") + ((element.id == "1000G frequency") ? "ThousandG" : element.id) + "="
            + element.value;
      });

  // make the request to update with all form controls
  var retVal = launchDataRequest("updateEvidenceData", queryString);

  // did it go ok
  if (retVal["error"] == undefined) {
    // persist these in case they have changed
    persistAsMoreEvidence(dataSet.selectedVariant.hgvsName, EVIDENCE_TYPE.dbSNP_frequency, $("#dbSNPfrequency").val(), "User", false);
    persistAsMoreEvidence(dataSet.selectedVariant.hgvsName, EVIDENCE_TYPE.ThousandG_frequency, $("#1000Gfrequency").val(), "User", false);
    persistAsMoreEvidence(dataSet.selectedVariant.hgvsName, EVIDENCE_TYPE.ExAC_frequency, $("#ExACfrequency").val(), "User", false);

    // draws the recomendation after criteria analysis
    drawCriteriaEvaluationAnalysis(dataSet.selectedVariant.hgvsName);

    // draw the collected evidence area
    drawMoreEvidenceDataEntry(dataSet.selectedVariant.hgvsName);

    // draw the ACMG Criteria Evidence area
    drawACMGCriteriaEvidenceExpandable(dataSet.selectedVariant.hgvsName)

    // draw the Criteria Assessment area
    drawACMGCriteriaCollectionAssessmentTable(dataSet.selectedVariant.hgvsName);

    // a little something for the user
    errSpn.attr("class", "success").append("text").text("Update successful.");
  } else {
    // show the error
    errorDisplay("submitGeneVariantDataForm - " + retVal["error"], 1);

    // display the error
    errSpn.attr("class", "err").append("text").text("Error: Unable to update.");
  }
}

/**
 * draws the area for gene curation data collection
 * 
 */
function drawGeneCurationAreaExpandable(geneName) {
  // get a reference to the canvas
  var container = d3.select("#DecisionSupportGeneCurationDataEntry");

  // remove the svg rendered area if it exists
  container.select("svg").remove();

  // get a svg region for the title
  var titleArea = container.append("svg").attr("width", "100%").attr("height", "45px");

  // create a group for the box and the text
  var grp = titleArea.append("g");

  // create a line for section separation
  var rectangle = grp.append("rect").attr("x", 2).attr("y", 20).attr("width", "100%").attr("height", 1).attr("stroke-width", 1).attr(
      "stroke", "#C0C0C0");

  // some title text
  var textArea = grp.append("text").attr("x", 0).attr("y", 40).attr("fill", "black").attr("font-style", "bold").attr("font-size", "16")
      .text("Gene curation - Manual data entry");

  // remove the div rendered area if it exists
  container.select("#divGeneCurationExpand").remove();

  // get a reference to the area we are going to render in
  var drawArea = container.append("div").attr("id", "divGeneCurationExpand").attr("width", "300px");

  // remove any grid that may already be there
  drawArea.select("#divGeneCurationExpandTable").remove();

  // make the request to get the evidence data
  var retVal = launchDataRequest("getGeneCurationData", "GeneName=" + geneName);

  // did we get a data error
  if (retVal["error"] == undefined) {
    // create an area for the already collected data for this gene
    var c = drawArea.append("div").attr("id", "divGeneCurationExpandTable").attr("style", "margin-top: 10px");

    // define the table column names
    var colHeader = [
      [
        "Phenotype disease modes for " + geneName
      ]
    ];

    // define the data column names
    var colData = [
      [
        "PhenoType"
      ]
    ];

    // create a table, header and body
    var table = c.append("table").attr("class", "standard").attr("width", "600px");
    var thead = table.append("thead");
    var tbody = table.append("tbody");

    // populate the header
    thead.append("tr").selectAll("th").data(colHeader).enter().append("th").attr("class", "dataentry").attr("style", "width: 300px;").text(
        function(d) {
          return d[0];
        });

    // create a row for each object in the data
    var rows = tbody.selectAll("tr").data(retVal["data"]).enter().append("tr").attr("style", function(d, i) {
      return (i % 2) ? "background-color: #F0F5FF;" : "";
    });

    // create a cell in each row for each column
    var cells = rows.selectAll("td").data(function(row, i) {
      // map the column to the incoming data
      return colData.map(function(column) {
        return {
          column : column,
          value : row[column],
          rowdata : row
        };
      });
    }).enter().append("td").attr("id", function(d) {
      return "GeneCuration_" + d.value.replace(" ", "-");
    }).append("a").on("click", function(d) {
      drawGenePhenoType(d.rowdata["GeneName"], d.value);
      return false;
    }).attr("href", "#").attr("style", "text-decoration: none;").html(function(d) {
      return "<img id=\"phenotypeimg_" + d.value.replace(" ", "-") + "\" src=\"../../images/sorted_pt_rt.gif\"/> Phenotype:" + d.value;
    });
  }
  // else just warn the user
  else {
    // show the error
    errorDisplay("drawGeneCurationAreaExpandable - getGeneDiseaseModes() - Error invalid selection.", 1);

    // show the user the error
    drawArea.append("div").attr("class", "err").text("No data");
  }

  // get the pulldown data based on the selected value
  var retVal = launchDataRequest("getDiseaseModeTypes", "");

  // did we get a data error
  if (retVal["error"] == undefined) {
    // add in the first value to the pulldown
    retVal["data"].unshift({
      "ID" : -1,
      "Name" : "Select a disease mode"
    });

    // create the frm tag
    var form = drawArea.append("div").attr("width", "300px").append("div").attr("style",
        "margin-top: 10px; font-size: 15px; font-family: arial;").append("form").style("margin-top", "10px").attr("id",
        "GeneCurationDataEntryForm");

    // create the scope level pulldown
    var controlArea = form.append("text").text("Add Phenotype:").append("input").attr("id", "PhenoType").attr("type", "text").attr("style",
        "margin-left: 5px; size: 100;")

    // add a hidden field for the gene
    form.append("input").attr("type", "hidden").attr("name", "geneName").attr("id", "geneName").attr("value", geneName);

    // create the pulldown with a label
    form.append("text").text("Disease mode:").attr("style", "margin-left: 15px;").append("select").attr("id", "GeneDiseaseModeID").attr(
        "style", "margin-left: 5px;").selectAll('option').data(retVal["data"]).enter().append("option").attr("value", function(d) {
      return d.ID;
    }).text(function(d) {
      return d.Name;
    });

    // create a textbox for the comment
    form.append("text").text("Comment:").attr("style", "margin-left: 15px;").append("input").attr("id", "PhenoComment")
        .attr("type", "text").attr("style", "margin-left: 5px; size: 100;")

    // create the submit button
    form.append("input").attr("id", "btnAdd").attr("style", "margin-left: 15px;").attr("type", "button").attr("value", "Add").on("click",
        function(d) {
          submitGeneCurationPhenoTypeForm(geneName, form);
        });
  }
}

/**
 * perfoms the form submission for the data entry area
 * 
 */
function submitGeneCurationPhenoTypeForm(geneName, spn) {
  // remove the old error msg
  spn.select("#GeneCurationPhenoEntryErr").remove();

  // create a new span for the errors
  var errSpn = spn.append("span").attr("id", "GeneCurationPhenoEntryErr").attr("style", "margin-left: 20px;");

  // get the name and value of the scope selection
  var selValue = $("#GeneDiseaseModeID option:selected").val();

  // did we get a valid selection
  if (selValue > 0) {
    // did we get valid data to add
    if (($("#PhenoType").val() != "") && ($("#PhenoComment").val() != "")) {
      // init the query string
      var queryString = "";

      // get all the form controls
      var formCtrls = $("#GeneCurationDataEntryForm :input").each(function(index, element) {
        // build the query string on the fly
        queryString = queryString + ((index == 0) ? "" : "&") + element.id + "=" + element.value;
      });

      // make the request to update with all form controls
      var retVal = launchDataRequest("updateGenePhenoTypeData", queryString);

      // did it go ok
      if (retVal["error"] == undefined) {
        // redraw the area
        drawGeneCurationAreaExpandable(geneName)

        // a little something for the user
        errSpn.attr("class", "success").append("text").text("Addition successful.");
      } else {
        // show the error
        errorDisplay("submitGeneCurationPhenoTypeForm - " + retVal["error"], 1);

        // display the error
        errSpn.attr("class", "err").append("text").text("Error: " + retVal["error"]);
      }
    }
    // display the error
    else
      errSpn.attr("class", "err").append("text").text("Error: Missing phenotype or comments.");
  }
  // display the error
  else
    errSpn.attr("class", "err").append("text").text("Error: Invalid disease mode selection.");
}

/**
 * draw the table that has the gene disease details.
 * 
 * @param geneName
 * @param phenoType
 */
function drawGenePhenoType(geneName, phenoType) {
  // get a reference to the canvas
  var container = d3.select("#GeneCuration_" + phenoType.replace(" ", "-"));

  // get the div rendered area if it exists
  var tableExists = container.selectAll("div").attr("id", "divGenePhenoTypeCuration");

  // only create the data collection area if it wasnt there previously
  if (tableExists[0][0] == null) {
    // make the request to get the evidence data
    var retVal = launchDataRequest("getGeneCurationDataByNameType", "geneName=" + geneName + "&phenoType=" + phenoType);

    // did we get a data error
    if (retVal["error"] == undefined) {
      // change the arrow image
      d3.select("#phenotypeimg_" + phenoType.replace(" ", "-")).attr("src", "../../images/sorted_up.gif");

      // get a reference to the area we are going to render in
      var drawArea = container.append("div").attr("id", "divGenePhenoTypeCuration").attr("style", "margin-left: 5px;").attr("width",
          "1000px");

      // create the form tag
      var form = drawArea.append("form").style("margin-top", "10px").attr("id", "GenePhenoTypeForm" + phenoType.replace(" ", "-"));

      // create an area for the already collected data for this disease
      var c = form.append("div").attr("id", "divGeneCurationTable" + phenoType.replace(" ", "-")).attr("width", "1000px").attr("style",
          "margin-top: 10px");

      // define the table column names
      var colHeader = [
          [
            "Disease mode"
          ], [
            "Comment"
          ]
      ];

      // define the data column names
      var colData = [
          [
            "Name"
          ], [
            "Comment"
          ]
      ];

      // create a table, header and body
      var table = c.append("table").attr("class", "standard");
      var thead = table.append("thead");
      var tbody = table.append("tbody");

      // populate the header
      thead.append("tr").selectAll("th").data(colHeader).enter().append("th").attr("class", "dataentry").attr("style", function(d, i) {
        return ((i > 0) ? "width:400px;" : "width:150px;");
      }).text(function(d) {
        return d[0];
      });

      // create a row for each object in the data
      var rows = tbody.selectAll("tr").data(retVal["data"]).enter().append("tr");

      // create a cell in each row for each column
      var cells = rows.selectAll("td").data(function(row, i) {
        // map the column to the incoming data
        return colData.map(function(column) {
          return {
            column : column,
            value : row[column],
            rowdata : row
          };
        });
      }).enter().append("td").attr("style", function(d, i) {
        return ((i > 0) ? "text-align: leftcenter;" : "text-align: center;");
      }).html(function(d, i) {
        return d.value;
      });
    }
  } else {
    // change the arrow image
    d3.select("#phenotypeimg_" + phenoType.replace(" ", "-")).attr("src", "../../images/sorted_pt_rt.gif");

    // remove the area
    tableExists.remove();
  }
}

/**
 * Draws an area with controls to capture gene/variant evidence.
 * 
 * @param dataSet
 */
function drawMoreEvidenceDataEntry(variantID) {
  // get a reference to the canvas
  var container = d3.select("#DecisionSupportMoreEvidenceDataEntry");

  // remove the svg rendered area if it exists
  container.select("svg").remove();

  // get a svg region for the title
  var titleArea = container.append("svg").attr("width", "100%").attr("height", "45px");

  // create a group for the box and the text
  var grp = titleArea.append("g");

  // create a line for section separation
  var rectangle = grp.append("rect").attr("x", 2).attr("y", 20).attr("width", "100%").attr("height", 1).attr("stroke-width", 1).attr(
      "stroke", "#C0C0C0");

  // some title text
  var textArea = grp.append("text").attr("x", 0).attr("y", 40).attr("fill", "black").attr("font-style", "bold").attr("font-size", "16")
      .text("ACMG Evidence - Manual data entry");

  // remove the svg rendered area if it exists
  container.select("div").remove();

  // get a reference to the area we are going to render in
  var drawArea = container.append("div").attr("width", "1000px").append("div").attr("style",
      "margin-top: 10px; font-size: 15px; font-family: arial;");

  // make the request to get the evidence data
  var retVal = launchDataRequest("getMoreEvidenceData", "VariantID=" + variantID);

  // did we get a data error
  if (retVal["error"] == undefined) {
    // define the table column names
    var colHeader = [
        [
          "Type"
        ], [
          "Value"
        ], [
          "Source"
        ], [
          "Comment"
        ], [
          "Relevant to ACMG Criteria"
        ]
    ];

    // define the data column names
    var colData = [
        [
          "Type"
        ], [
          "Value"
        ], [
          "Source"
        ], [
          "Comment"
        ], [
          "ACMGCriteria"
        ]
    ];

    // create a table, header and body
    var table = drawArea.append("table").attr("class", "standard");
    var thead = table.append("thead");
    var tbody = table.append("tbody");

    // populate the header
    thead.append("tr").selectAll("th").data(colHeader).enter().append("th").attr("class", "dataentry").attr("width", "250px").text(
        function(d) {
          return d[0];
        });

    // create a row for each object in the data
    var rows = tbody.selectAll("tr").data(retVal["data"]).enter().append("tr").attr("style", function(d, i) {
      return (i % 2) ? "background-color: #F0F5FF" : "";
    });

    // create a cell in each row for each column
    var cells = rows.selectAll("td").data(function(row) {
      // map the column to the incoming data
      return colData.map(function(column) {
        return {
          column : column,
          value : row[column]
        };
      });
    }).enter().append("td").attr("style", "text-align: left;").html(function(d) {
      return d.value;
    });
  }
  // else just warn the user
  else {
    // show the error
    errorDisplay("drawMoreEvidenceDataEntry() - getMoreEvidenceData: Error invalid selection.", 1);
  }

  // create the frm tag
  var form = drawArea.append("form").style("margin-top", "10px").attr("id", "moreEvidenceDataEntryForm");

  // save the key
  form.append("input").attr("type", "hidden").attr("name", "VariantID").attr("id", "VariantID").attr("value", variantID);

  // create the form area
  var controlArea = form.append("text");

  // get the pulldown data based on the selected value
  var retVal = launchDataRequest("getEvidenceTypes");

  // did we get a data error
  if (retVal["error"] == undefined) {
    // add in the first value to the pulldown
    retVal["data"].unshift({
      "ID" : -1,
      "Name" : "Select a evidence type",
      "EvidenceType" : -1
    });

    // create the pulldown with a label
    controlArea.append("text").text("Add evidence:").attr("style", "margin-left: 15px;").append("select").attr("id", "EvidenceTypeID")
        .attr("style", "margin-left: 5px;").on("change", function() {
          $("#inDataValue").val('');
          $("#inDataSource").val('');
        }).selectAll('option').data(retVal["data"]).enter().append("option").attr("value", function(d) {
          return d.ID;
        }).text(function(d) {
          return d.Name;
        });

    // create a textbox for the data to enter
    controlArea.append("text").text("Value:").attr("style", "margin-left: 15px;").append("input").attr("id", "inDataValue").attr("type",
        "text").attr("style", "margin-left: 5px; size: 100;")

    // create a textbox for the source of the data entered
    controlArea.append("text").text("Source:").attr("style", "margin-left: 15px;").append("input").attr("id", "inDataSource").attr("type",
        "text").attr("style", "margin-left: 5px; size: 100;")

    // create a textbox for the comment of the data entered
    controlArea.append("text").text("Comment:").attr("style", "margin-left: 15px;").append("input").attr("id", "inDataComment").attr(
        "type", "text").attr("style", "margin-left: 5px; size: 100;")

    // create the submit button
    controlArea.append("input").attr("id", "btnAdd").attr("style", "margin-left: 15px;").attr("type", "button").attr("value", "Add").on(
        "click", function(d) {
          submitMoreEvidenceForm(controlArea, variantID);
        });
  }
  // else report the error
  else {
    // show the error
    errorDisplay("drawMoreEvidenceDataEntry - getEvidenceTypes() - " + retVal["error"], 1);

    // show the user the error
    controlArea.append("span").attr("Class", "err").text("Error:" + retVal["error"]);
  }
}

/**
 * adds or updates the data passed from the upper evidence table to the
 * additional data table.
 * 
 * @param variantID
 * @param evidenceTypeID
 * @param dataValue
 * @param dataSource
 */
function persistAsMoreEvidence(variantID, evidenceTypeID, dataValue, dataSource, dataComment, tryUpdate) {
  // did we get valid data
  if (dataValue != "" && dataSource != "") {
    // check the try to update (rather than insert new) flag
    if (tryUpdate == undefined)
      tryUpdate = 0;

    // init the query string
    var queryString = "VariantID=" + variantID + "&EvidenceTypeID=" + evidenceTypeID + "&inDataValue=" + dataValue + "&inDataSource="
        + dataSource + "&inDataComment=" + dataComment + "&tryUpdate=" + tryUpdate;

    // make the request to update the data
    var retVal = launchDataRequest("updateMoreEvidenceData", queryString);

    // did it go ok
    if (retVal["error"] != undefined) {
      // show the error
      errorDisplay("persistAsMoreEvidence - " + retVal["error"], 1);

      // return the error flag
      return retVal["error"];
    } else
      return "";
  }
}

/**
 * perfoms the form submission for the data entry area
 * 
 */
function submitMoreEvidenceForm(spn, variantID) {
  // get the name and value of the scope selection
  var selValue = $("#EvidenceTypeID option:selected").val();

  // remove the old error msg
  spn.select("#EvidenceEntryErr").remove();

  // create a new span for the errors
  var errSpn = spn.append("span").attr("id", "EvidenceEntryErr").attr("style", "margin-left: 20px;");

  // did we get a valid selection
  if (selValue > 0) {
    // did we get valid data to add
    if (($("#inDataValue").val() != "") && ($("#inDataSource").val() != "")) {
      // init the query string
      var queryString = "";

      // get all the form controls
      var formCtrls = $("#moreEvidenceDataEntryForm :input").each(function(index, element) {
        // build the query string on the fly
        queryString = queryString + ((index == 0) ? "" : "&") + element.id + "=" + element.value;
      });

      // append try update flag (in this case false)
      queryString = queryString + "&tryUpdate=0";

      // make the request to update with all form controls
      var retVal = launchDataRequest("updateMoreEvidenceData", queryString);

      // did it go ok
      if (retVal["error"] == undefined) {
        // draws the recomendation after criteria analysis
        drawCriteriaEvaluationAnalysis(variantID);

        // redraw the ACMG Criteria Evidence data area
        drawACMGCriteriaEvidenceExpandable(variantID);

        // draw the Criteria Assessment area
        drawACMGCriteriaCollectionAssessmentTable(variantID);

        // draw the collected evidence area
        drawMoreEvidenceDataEntry(variantID);

        // a little something for the user
        errSpn.attr("class", "success").append("text").text("Addition successful.");
      } else {
        // show the error
        errorDisplay("submitMoreEvidenceForm - " + retVal["error"], 1);

        // display the error
        errSpn.attr("class", "err").append("text").text("Error: " + retVal["error"]);
      }
    }
    // display the error
    else
      errSpn.attr("class", "err").append("text").text("Error: Missing data value or source.");
  }
  // display the error
  else
    errSpn.attr("class", "err").append("text").text("Error: Invalid type selection.");
}

/**
 * perfoms the form submission for the data entry area
 * 
 */
function submitAdhocEvidenceForm(spn, variantID, ACMGCriteriaID) {
  // remove the old error msg
  spn.select("#EvidenceEntryErr" + ACMGCriteriaID).remove();

  // create a new span for the errors
  var errSpn = spn.append("span").attr("id", "EvidenceEntryErr" + ACMGCriteriaID).attr("style", "margin-left: 20px;");

  // get the form children
  var formChildren = $("#moreEvidenceDataEntryForm_" + ACMGCriteriaID + " :input");

  // did we get a valid selection
  if (formChildren[1].value > 0) {
    // did we get valid data to add
    if ((formChildren[2].value != "") && (formChildren[3].value != "") && (formChildren[4].value != "")) {
      // init the query string
      var queryString = "";

      // get all the form controls
      var formCtrls = $("#moreEvidenceDataEntryForm_" + ACMGCriteriaID + " :input").each(function(index, element) {
        // build the query string on the fly
        queryString = queryString + ((index == 0) ? "" : "&") + element.id + "=" + element.value;
      });

      // append try update flag (in this case false)
      queryString = queryString + "&tryUpdate=0";

      // make the request to update with all form controls
      var retVal = launchDataRequest("updateMoreEvidenceData", queryString);

      // did it go ok
      if (retVal["error"] == undefined) {
        // draws the recomendation after criteria analysis
        drawCriteriaEvaluationAnalysis(variantID);

        // redraw the ACMG Criteria Evidence data area
        drawACMGCriteriaEvidenceExpandable(variantID);

        // draw the Criteria Assessment area
        drawACMGCriteriaCollectionAssessmentTable(variantID);

        // draw the collected evidence area
        drawMoreEvidenceDataEntry(variantID);

        // a little something for the user
        errSpn.attr("class", "success").append("text").text("Addition successful.");
      } else {
        // show the error
        errorDisplay("submitAdhocEvidenceForm - " + retVal["error"], 1);

        // display the error
        errSpn.attr("class", "err").append("text").text("Error: " + retVal["error"]);
      }
    }
    // display the error
    else
      errSpn.attr("class", "err").append("text").text("Error: Missing data value or source.");
  }
  // display the error
  else
    errSpn.attr("class", "err").append("text").text("Error: Invalid type selection.");
}

/**
 * draws the criteria analysis area
 * 
 * @param variantID
 */
function drawCriteriaEvaluationAnalysis(variantID) {
  // init the criteria evaluation storage
  initACMGCriteriaEvaluationInfo(variantID, d3.select("#DonorCode").html());

  // get a reference to the newly created svg region for the analysis area
  var container = d3.select("#DecisionSupportCriteriaAnalysis");

  // remove the svg rendered area if it exists
  container.select("svg").remove();

  // get a svg region for the title
  var titleArea = container.append("svg").attr("width", "100%").attr("height", "45px");

  // create a group for the box and the text
  var grp = titleArea.append("g");

  // create a line for section separation
  var rectangle = grp.append("rect").attr("x", 2).attr("y", 20).attr("width", "100%").attr("height", 1).attr("stroke-width", 1).attr(
      "stroke", "#C0C0C0");

  // some title text
  var textArea = grp.append("text").attr("x", 0).attr("y", 40).attr("fill", "black").attr("font-style", "bold").attr("font-size", "16")
      .text("ACMG Criteria evaluation analysis");

  // remove the overall rendered criteria evaluation area if it exists
  container.select("#divCriteriaEvaluationAnalysisArea").remove();

  // start a new area for the evaluation grid
  c = container.append("div").attr("id", "divCriteriaEvaluationAnalysisArea").attr("style", "margin-top: 10px");

  // add in a override pulldown

  // define the table column names
  var colHeader = [
    [
      "Calculated sequence variant classification"
    ]
  ];

  // get the classification of the variant
  var variantClassification = getSequenceVariantClassification();

  // get the override classification of the variant
  var overrideClassification = getOverrideSequenceVariantClassification();

  // define the data column names
  var rowData = [
    [
      variantClassification
    ]
  ];

  // create a table, header and body
  var table = c.append("table").attr("border", "1");
  var thead = table.append("thead");
  var tbody = table.append("tbody");

  // populate the header
  thead.append("tr").selectAll("th").data(colHeader).enter().append("th").attr("height", "30px").attr("width", "400px").attr("class",
      "dataentry").style("font-weight", "bold").style("font-size", "16px").style("text-shadow",
      "-2px -2px 2px #111, 2px -2px 2px #111, -2px 2px 2px #111, 2px 2px 2px #111").text(function(d) {
    return d[0];
  });

  // create all the rows and columns in the table
  var tb = tbody.append("tr").selectAll("td").data(rowData).enter().append("td").attr("height", "30px").style("color", "white").style(
      "background-color", "white").style("font-weight", "bold").style("text-align", "center").style("text-shadow",
      "-2px -2px 2px #111, 2px -2px 2px #111, -2px 2px 2px #111, 2px 2px 2px #111").transition().duration(750).style("background-color",
      function(d) {
        // init the return value
        var retVal = "";

        // determine the text output of the evaluation
        switch (d[0]) {
          case VARIANT_CLASSIFICATION.PATHOGENIC:
            retVal = "FireBrick";
            break;
          case VARIANT_CLASSIFICATION.LIKELY_PATHOGENIC:
            retVal = "#E65C5C";
            break;
          case VARIANT_CLASSIFICATION.UNCERTAIN:
            retVal = "GoldenRod";
            break;
          case VARIANT_CLASSIFICATION.LIKELY_BENIGN:
            retVal = "#D4D464";
            break;
          case VARIANT_CLASSIFICATION.BENIGN:
            retVal = "Green";
            break;
          default:
            retVal = "#3E80BD";
            break;
        }

        // return to the caller
        return retVal;
      }).text(function(d) {
    // init the return value
    var retVal = "";

    // determine the text output of the evaluation
    switch (d[0]) {
      case VARIANT_CLASSIFICATION.PATHOGENIC:
        retVal = "Pathogenic";
        break;
      case VARIANT_CLASSIFICATION.LIKELY_PATHOGENIC:
        retVal = "Likely pathogenic";
        break;
      case VARIANT_CLASSIFICATION.UNCERTAIN:
        retVal = "Uncertain";
        break;
      case VARIANT_CLASSIFICATION.LIKELY_BENIGN:
        retVal = "Likely benign";
        break;
      case VARIANT_CLASSIFICATION.BENIGN:
        retVal = "Benign";
        break;
      case VARIANT_CLASSIFICATION.NOT_MET:
        retVal = "Not met";
        break;
      case VARIANT_CLASSIFICATION.AUTO:
        retVal = "Automatic";
        break;
      default:
        retVal = "Error";
        break;
    }

    // return to the caller
    return retVal + " " + getOverridenText();
  });

  // init the key
  var key = d3.select("#DonorCode").html() + "~" + variantID;
  var ctrlID = "ClassificationOverride";

  // create an array for the selections
  var arr = [
      [
          [
            "Automatic"
          ], [
            VARIANT_CLASSIFICATION.AUTO
          ]
      ], [
          [
            "Not met"
          ], [
            VARIANT_CLASSIFICATION.NOT_MET
          ]
      ], [
          [
            "Pathogenic"
          ], [
            VARIANT_CLASSIFICATION.PATHOGENIC
          ]
      ], [
          [
            "Likely pathogenic"
          ], [
            EVIDENCE_OF_PATHOGENICITY.PATHO_STRONG
          ]
      ], [
          [
            "Uncertain"
          ], [
            VARIANT_CLASSIFICATION.UNCERTAIN
          ]
      ], [
          [
            "Likely benign"
          ], [
            VARIANT_CLASSIFICATION.LIKELY_BENIGN
          ]
      ], [
          [
            "Benign"
          ], [
            VARIANT_CLASSIFICATION.BENIGN
          ]
      ]
  ];

  // tack on the Criteria assessment pulldown
  var pd = c.append("div").attr("style", "margin-top: 10px; margin-left: 20px; margin-bottom: 10px;").html(
      "Override ACMG classification: "
          + createAPulldown(ctrlID, "ClassificationOverride", arr, overrideClassification, key, "submitACMGOverridePulldown"));

  // add in a area for errors
  c.append("span").attr("id", "ACMGOverride_Err");

  // create a link to show/hide the evaluation plan
  c
      .append("div")
      .attr("id", "divCriteriaEvaluationAnalysisPlan")
      .attr("style", "margin-top: 15px")
      .append("a")
      .on("click", function(d) {
        drawCriteriaEvaluationAnalysisPlan();
        return false;
      })
      .attr("href", "#")
      .attr("style", "text-decoration: none;")
      .html(
          function(d) {
            return "<img id=\"CriteriaEvalPlanImg\" src=\"../../images/sorted_pt_rt.gif\"/>&nbsp;Click to view the ACMG Criteria classification plan for "
                + variantID;
          });
}

/**
 * draws the Criteria filfillment table for this variant
 * 
 * @param variantID
 * 
 */
function drawACMGCriteriaCollectionAssessmentTable(variantID) {
  // get a reference to the canvas
  var container = d3.select("#DecisionSupportCriteriaAssessment");

  // remove the svg rendered area if it exists
  container.select("svg").remove();

  // get a svg region for the title
  var titleArea = container.append("svg").attr("width", "100%").attr("height", "45px");

  // create a group for the box and the text
  var grp = titleArea.append("g");

  // create a line for section separation
  var rectangle = grp.append("rect").attr("x", 2).attr("y", 20).attr("width", "100%").attr("height", 1).attr("stroke-width", 1).attr(
      "stroke", "#C0C0C0");

  // some title text
  var textArea = grp.append("text").attr("x", 0).attr("y", 40).attr("fill", "black").attr("font-style", "bold").attr("font-size", "16")
      .text("ACMG Criteria evidence collection assessment");

  // remove anything that may already be there
  container.select("#divCriteriaAssessmentFulfilledArea").remove();

  // create an area for the Criteria status area
  container.append("div").attr("id", "divCriteriaAssessmentFulfilledArea").attr("width", "1800px").attr("style", "margin-top: 10px");

  // draws the criteria satisfied area
  drawACMGCriteriaAssessmentSatisfied(container, variantID);

  // draws the criteria fulfilled area
  drawACMGCriteriaAssessmentFulfilled();
}

/**
 * draws the criteria satisfied area
 * 
 * @param container
 * @param variantID
 */
function drawACMGCriteriaAssessmentSatisfied(container, variantID) {
  // remove any anything that may already be there
  container.select("#divAssessmentData").remove();

  // create an area for the already collected data for this variant
  var c = container.append("div").attr("id", "divAssessmentData").attr("width", "1800px").attr("style", "margin-top: 10px");

  // make the request to get the evidence data
  var retVal = launchDataRequest("getCriteriaAssessmentData", "VariantID=" + variantID);

  // did we get a data error
  if (retVal["error"] == undefined) {
    // start a new area for the Assessment grid
    c.append("div").attr("style", "margin-top: 10px");

    // define the table column names
    var colHeader = [
        [
          "Type"
        ], [
          "PVS1"
        ], [
          "PS4"
        ], [
          "PS3"
        ], [
          "PS2"
        ], [
          "PS1"
        ], [
          "PP5"
        ], [
          "PP4"
        ], [
          "PP3"
        ], [
          "PP2"
        ], [
          "PP1"
        ], [
          "PM6"
        ], [
          "PM5"
        ], [
          "PM4"
        ], [
          "PM3"
        ], [
          "PM2"
        ], [
          "PM1"
        ], [
          "BS4"
        ], [
          "BS3"
        ], [
          "BS2"
        ], [
          "BS1"
        ], [
          "BP7"
        ], [
          "BP6"
        ], [
          "BP5"
        ], [
          "BP4"
        ], [
          "BP3"
        ], [
          "BP2"
        ], [
          "BP1"
        ], [
          "BA1"
        ]
    ];

    // define the data column names
    var colData = [
        [
          "Type"
        ], [
          "PVS1"
        ], [
          "PS4"
        ], [
          "PS3"
        ], [
          "PS2"
        ], [
          "PS1"
        ], [
          "PP5"
        ], [
          "PP4"
        ], [
          "PP3"
        ], [
          "PP2"
        ], [
          "PP1"
        ], [
          "PM6"
        ], [
          "PM5"
        ], [
          "PM4"
        ], [
          "PM3"
        ], [
          "PM2"
        ], [
          "PM1"
        ], [
          "BS4"
        ], [
          "BS3"
        ], [
          "BS2"
        ], [
          "BS1"
        ], [
          "BP7"
        ], [
          "BP6"
        ], [
          "BP5"
        ], [
          "BP4"
        ], [
          "BP3"
        ], [
          "BP2"
        ], [
          "BP1"
        ], [
          "BA1"
        ]
    ];

    // create a table, header and body
    var table = c.append("table").attr("class", "standard");
    var thead = table.append("thead");
    var tbody = table.append("tbody");

    // populate the header
    thead.append("tr").selectAll("th").data(colHeader).enter().append("th").attr("class", "dataentry").attr("width", "50px").text(
        function(d) {
          return d[0];
        });

    // create a row for each object in the data
    var rows = tbody.selectAll("tr").data(retVal["data"]).enter().append("tr");

    // init the Criteria counters
    CriteriaSatisfied = 0;
    CriteriaIncomplete = 0;

    // create a cell in each row for each column
    var cells = rows.selectAll("td").data(function(row) {
      // map the column to the incoming data
      return colData.map(function(column) {
        return {
          column : column,
          value : row[column],
          rowdata : row
        };
      });
    }).enter().append("td").style("color", "white").style("background-color", "white").style("font-weight", "bold").style("text-align",
        "center").style("text-shadow", "-2px -2px 2px #111, 2px -2px 2px #111, -2px 2px 2px #111, 2px 2px 2px #111").transition().duration(
        750).style("background-color", function(d) {
      // init the return value
      retVal = "";

      // is the value empty
      if (d.value == null)
        retVal = "FireBrick"; // return red
      else if (d.value != 'Required' && d.value != 'Optional') {
        // split the string
        var arr = d.value.split(" ");

        // the first element is the count
        var cnt = parseInt(arr[0]);

        // the third is the total possible for the Criteria
        var total = parseInt(arr[2]);

        // if we satisfied the count make it green
        if (cnt >= total) {
          // is this a required Criteria
          if (d.rowdata["Type"] == "Required")
            CriteriaSatisfied++;

          // return green
          retVal = "Green";
        }
        // else make it yellow
        else {
          // is this a require Criteria
          if (d.rowdata["Type"] == "Required")
            CriteriaIncomplete++;

          // return yellow
          retVal = "GoldenRod";
        }
      } else
        retVal = "#3E80BD";

      // return to the caller
      return retVal;
    }).style("", "").text(function(d) {
      return d.value;
    });
  }
  // else just warn the user
  else {
    // show the error
    errorDisplay("drawACMGCriteriaCollectionAssessmentTable() - getMoreEvidenceData: Error invalid selection.", 1);

    // show the user the error
    container.append("div").attr("id", "divAssessmentData").attr("class", "err").attr("style", "margin-top: 10px;").text("No data");
  }
}

/**
 * draws the manually collected evidence data for this variant an ACMG guideline
 * Criteria
 * 
 * @param variantID
 * 
 */
function drawACMGCriteriaEvidenceExpandable(variantID) {
  // get a reference to the canvas
  var container = d3.select("#DecisionSupportACMGCriteriaEvidence");

  // remove the svg rendered area if it exists
  container.select("svg").remove();

  // get a svg region for the title
  var titleArea = container.append("svg").attr("width", "100%").attr("height", "45px");

  // create a group for the box and the text
  var grp = titleArea.append("g");

  // create a line for section separation
  var rectangle = grp.append("rect").attr("x", 2).attr("y", 20).attr("width", "100%").attr("height", 1).attr("stroke-width", 1).attr(
      "stroke", "#C0C0C0");

  // some title text
  var textArea = grp.append("text").attr("x", 0).attr("y", 40).attr("fill", "black").attr("font-style", "bold").attr("font-size", "16px")
      .text("ACMG Criteria evidence");

  // remove the div rendered area if it exists
  container.select("#divCriteriaEvidenceExpand").remove();

  // get a reference to the area we are going to render in
  var drawArea = container.append("div").attr("id", "divCriteriaEvidenceExpand").attr("width", "100%");

  // remove any grid that may already be there
  drawArea.select("#divACMGCriteriaEvidenceExpandTable").remove();

  // make the request to get the evidence data
  var retVal = launchDataRequest("getACMGEvidenceReviewParentData", "VariantID=" + variantID);

  // did we get a data error
  if (retVal["error"] == undefined) {
    // create an area for the already collected data for this variant
    var c = drawArea.append("div").attr("id", "divACMGCriteriaEvidenceExpandTable").attr("style", "margin-top: 10px");

    // define the table column names
    var colHeader = [
      [
        "ACMG Evidence by Criteria for " + variantID
      ]
    ];

    // define the data column names
    var colData = [
      [
        "ACMGCriteria"
      ]
    ];

    // create a table, header and body
    var table = c.append("table").attr("class", "standard").attr("width", "100%");
    var thead = table.append("thead");
    var tbody = table.append("tbody");

    // populate the header
    thead.append("tr").selectAll("th").data(colHeader).enter().append("th").attr("class", "dataentry").attr("style", "width: 100%").text(
        function(d) {
          return d[0];
        });

    // create a row for each object in the data
    var rows = tbody.selectAll("tr").data(retVal["data"]).enter().append("tr").attr("style", function(d, i) {
      return (i % 2) ? "background-color: #F0F5FF;" : "";
    });

    // create a cell in each row for each column
    var cells = rows.selectAll("td").data(function(row, i) {
      // map the column to the incoming data
      return colData.map(function(column) {
        return {
          column : column,
          value : row[column],
          rowdata : row
        };
      });
    }).enter().append("td").attr("id", function(d) {
      return "ACMGCriteriaID_" + d.rowdata["ACMGCriteriaID"];
    }).append("a").on("click", function(d) {
      drawACMGCriteriaEvidence(variantID, d.rowdata["ACMGCriteriaID"]);
      return false;
    }).attr("href", "#").attr("style", "text-decoration: none;").html(function(d) {
      return "<img id=\"Criteriaimg_" + d.rowdata["ACMGCriteriaID"] + "\" src=\"../../images/sorted_pt_rt.gif\"/>&nbsp;" + d.value;
    });
  }
  // else just warn the user
  else {
    // show the error
    errorDisplay("drawACMGCriteriaEvidenceExpandable - getACMGEvidenceReviewParentData() - Error invalid selection.", 1);

    // show the user the error
    drawArea.append("div").attr("id", "divACMGCriteriaEvidenceExpandTable").attr("class", "err").attr("style", "margin-top: 10px;").text(
        "No data");
  }
}

/**
 * draws the evidence data for this gene/variant an ACMG guideline Criteria
 * 
 * @param variantID
 * 
 */
function drawACMGCriteriaEvidence(variantID, ACMGCriteriaID) {
  // get a reference to the canvas
  var container = d3.select("#ACMGCriteriaID_" + ACMGCriteriaID);

  // get the div rendered area if it exists
  var tableExists = container.selectAll("div").attr("id", "divCriteriaEvidence");

  // only create the data collection area if it wasnt there previously
  if (tableExists[0][0] == null) {
    // get a reference to the area we are going to render in
    var drawArea = container.append("div").attr("id", "divCriteriaEvidence").attr("style", "margin-left: 5px;").attr("width", "1000px");

    // change the arrow image
    d3.select("#Criteriaimg_" + ACMGCriteriaID).attr("src", "../../images/sorted_up.gif");

    // create the form tag
    var form = drawArea.append("form").style("margin-top", "10px").attr("id", "ACMGCriteriaEvidenceForm" + ACMGCriteriaID);

    // init the key
    form.append("input").attr("type", "hidden").attr("name", "VariantID").attr("id", "VariantID").attr("value", variantID);

    // init the dirty flag
    form.append("input").attr("type", "hidden").attr("name", "dirtyFlag" + ACMGCriteriaID).attr("id", "dirtyFlag" + ACMGCriteriaID).attr(
        "value", false);

    // get the donor code
    var DonorCode = d3.select("#DonorCode").html();

    // draw the criteria assessment pulldown area
    var c = drawACMGCriteriaAssessmentArea(form, variantID, DonorCode, ACMGCriteriaID);

    // did the rendering go ok
    if (c != null) {
      // draw the defined evidence area for gene diseases
      c = drawACMGDefinedGeneEvidenceTable(c, form, variantID, DonorCode, ACMGCriteriaID);

      // did it render ok
      if (c != null) {
        // draw the defined evidence area for allele/transcripts
        c = drawACMGDefinedTranscriptEvidenceTable(c, form, variantID, DonorCode, ACMGCriteriaID);

        // add a save button
        c.append("input").attr("type", "button").attr("style", "margin-left: 10px; margin-top: 10px; margin-bottom: 5px;").attr("value",
            "Save").on("click", function(d) {
          submitACMGCriteriaEvidenceForm(variantID, ACMGCriteriaID);
        });

        // add in a area for errors
        c.append("span").attr("id", "ACMGCriteriaID_" + ACMGCriteriaID + "_Err");

        // draw the adhoc data entry area
        c = drawAdhocEvidenceDataEntryTable(c, variantID, ACMGCriteriaID);

        // TODO draw the pathogenicity evaluation
        drawACMGPathogenicityEvaluation(c, variantID, DonorCode, ACMGCriteriaID);
      }
      // else just warn the user
      else {
        // show the error
        errorDisplay("drawACMGCriteriaEvidence - drawAdhocEvidenceArea() - Error getting adhoc evidence data.", 1);

        // show the user the error
        drawArea.append("div").attr("class", "err").text("Error getting adhoc evidence data");
      }

    }
    // else just warn the user
    else {
      // show the error
      errorDisplay("drawACMGCriteriaEvidence - drawACMGCriteriaAssessmentArea() - Error getting evidence data.", 1);

      // show the user the error
      drawArea.append("div").attr("class", "err").text("Error getting evidence data");
    }
  } else {
    // get the state of the dirty flag
    var dirtyFlag = $("#dirtyFlag" + ACMGCriteriaID).val();

    // is the form dirty
    if (dirtyFlag == "false") {
      // change the arrow image
      d3.select("#Criteriaimg_" + ACMGCriteriaID).attr("src", "../../images/sorted_pt_rt.gif");

      // remove the area
      tableExists.remove();
    } else {
      // prompt the user to be sure
      var confResp = confirm('You have unsaved changes and collapsing this area will result in a loss of data.\n\nAre you sure you want to do this?');

      // if they are sure go ahead and collapse
      if (confResp == true) {
        // change the arrow image
        d3.select("#Criteriaimg_" + ACMGCriteriaID).attr("src", "../../images/sorted_pt_rt.gif");

        // remove the area
        tableExists.remove();
      }
    }
  }
}

/**
 * draws the ACMG defined gene level evidence area
 * 
 * @param c
 * @param form
 * @param variantID
 * @param DonorCode
 * @param ACMGCriteriaID
 */
function drawACMGDefinedGeneEvidenceTable(c, form, variantID, DonorCode, ACMGCriteriaID) {
  // make the request to get the evidence data
  var retVal = launchDataRequest("getACMGEvidenceReviewData", "VariantID=" + variantID + "&DONOR_CODE=" + DonorCode + "&ACMGCriteriaID="
      + ACMGCriteriaID);

  // did we get a data error
  if (retVal["error"] == undefined) {
    // define the table column names
    var colHeader = [
        [
          "Evidence name"
        ], [
          "Value"
        ], [
          "Range"
        ], [
          "Source"
        ], [
          "Comment"
        ], [
          "Required"
        ], [
          "Evidence evaluation"
        ], [
          "Confidence"
        ], [
          "Use evidence"
        ]
    ];

    // define the data column names
    var colData = [
        [
          "Name"
        ], [
          "Value"
        ], [
          "Range"
        ], [
          "Source"
        ], [
          "Comment"
        ], [
          "Required"
        ], [
          "Evaluation"
        ], [
          "Confidence"
        ], [
          "UseEvidence"
        ]
    ];

    // throw down a table title
    c.append("div").attr("style", "margin-top: 10px; font-size: 15px;").text("Gene / Disease evidence");

    // create a table, header and body
    var table = c.append("table").attr("class", "standard").attr("width", "100%");
    var thead = table.append("thead");
    var tbody = table.append("tbody");

    // populate the header
    thead.append("tr").selectAll("th").data(colHeader).enter().append("th").attr("class", "dataentry").attr("style", function(d, i) {
      if (i == 0)
        return "width: 200px;";
      else if (i == 5 || i == 7 || i == 8)
        return "width: 50px;";
      else
        return "width: 100px;";
    }).text(function(d) {
      return d[0];
    });

    // create a row for each object in the data
    var rows = tbody.selectAll("tr").data(retVal["data"]).enter().append("tr");

    // create a cell in each row for each column
    var cells = rows.selectAll("td").data(function(row, i) {
      // map the column to the incoming data
      return colData.map(function(column) {
        return {
          column : column,
          value : row[column],
          rowdata : row
        };
      });
    }).enter().append("td").attr("style", function(d, i) {
      return ((i == 0 || i > 3) ? "text-align: center;" : "text-align: left;");
    }).html(
        function(d, i) {
          // these columns are read only
          if (i == 0 || i == 2 || i == 5)
            return d.value;
          // show the evidence evaluation
          else if (i == 6)
            return evaluateEvidenceView(d.rowdata);
          // editable fields
          else {
            // these colummns potentially have edit boxes
            if (i == 1 || i == 3 || i == 4) {
              // if there is no data give them the opportunity to enter it
              if (d.value == null) {
                // init the key
                var key = variantID + "~" + d.rowdata["ACMGCriteriaID"] + "~" + d.rowdata["EvidenceTypeID"];

                // init a ctrl ID
                var ctrlID = "";

                // is this the value column
                if (i == 2)
                  ctrlID = "inDataValue";
                // else the source column
                else if (i == 4)
                  ctrlID = "inDataSource";
                else
                  ctrlID + "inDataComment";

                return "<input type=\"text\" style=\"margin-left: 5px; size: 100;\" id=\"" + ctrlID + "_" + d.rowdata["EvidenceTypeID"]
                    + "_" + i + "\" onblur=\"$('#dirtyFlag" + ACMGCriteriaID + "').val('true');\"/>";
              } else
                return d.value;
            }
            // last 2 columns are pulldowns or check boxes
            else {
              // a value means we have some data to evaluate
              if (d.rowdata["Value"] != null) {
                // init the key
                var key = DonorCode + "~" + variantID + "~" + d.rowdata["ACMGCriteriaID"] + "~" + d.rowdata["EvidenceTypeID"];

                // create the control ID
                var ctrlID = DonorCode + "-" + d.rowdata["ACMGCriteriaID"] + "-" + d.rowdata["EvidenceTypeID"];

                // init the type and optons array
                var type = "";
                var arr;

                // the confidence field
                if (i == 7) {
                  // create an array for the selections
                  arr = [
                      [
                          [
                            "Low"
                          ], [
                            0
                          ]
                      ], [
                          [
                            "Medium"
                          ], [
                            1
                          ]
                      ], [
                          [
                            "High"
                          ], [
                            2
                          ]
                      ]
                  ];

                  // save the type
                  type = "Confidence";

                  // turn it into a pulldown
                  return createAPulldown(ctrlID + type, type, arr, d.value, key, "submitACMGCriteriaEvidencePulldown");
                }
                // the use the Criteria field
                else if (i == 8) {
                  // create an array for the selections
                  arr = [
                      [
                          [
                            "False"
                          ], [
                            0
                          ]
                      ], [
                          [
                            "True"
                          ], [
                            1
                          ]
                      ]
                  ];

                  // save the type
                  type = "UseEvidence";

                  // turn it into a pulldown
                  return createACheckBox(ctrlID + type, type, d.value, key, "submitACMGCriteriaEvidencePulldown");
                }
              } else
                return "<span style=\"color: red;\">Not available</span>";
            }
          }
        });
  }

  // return the container to the caller
  return c;
}

/**
 * draws the ACMG defined transcript level evidence area
 * 
 * @param c
 * @param form
 * @param variantID
 * @param DonorCode
 * @param ACMGCriteriaID
 */
function drawACMGDefinedTranscriptEvidenceTable(c, form, variantID, DonorCode, ACMGCriteriaID) {
  // make the request to get the evidence data
  var retVal = launchDataRequest("getACMGEvidenceReviewData", "VariantID=" + variantID + "&DONOR_CODE=" + DonorCode + "&ACMGCriteriaID="
      + ACMGCriteriaID);

  // did we get a data error
  if (retVal["error"] == undefined) {
    // define the table column names
    var colHeader = [
        [
          "Evidence name"
        ], [
          "Transcript"
        ], [
          "Value"
        ], [
          "Range"
        ], [
          "Source"
        ], [
          "Comment"
        ], [
          "Required"
        ], [
          "Evidence evaluation"
        ], [
          "Confidence"
        ], [
          "Use evidence"
        ]
    ];

    // define the data column names
    var colData = [
        [
          "Name"
        ], [
          "TranscriptID"
        ], [
          "Value"
        ], [
          "Range"
        ], [
          "Source"
        ], [
          "Comment"
        ], [
          "Required"
        ], [
          "Evaluation"
        ], [
          "Confidence"
        ], [
          "UseEvidence"
        ]
    ];

    // throw down a table title
    c.append("div").attr("style", "margin-top: 10px; font-size: 15px;").text("Allele / Transcript evidence");

    // create a table, header and body
    var table = c.append("table").attr("class", "standard").attr("width", "100%");
    var thead = table.append("thead");
    var tbody = table.append("tbody");

    // populate the header
    thead.append("tr").selectAll("th").data(colHeader).enter().append("th").attr("class", "dataentry").attr("style", function(d, i) {
      if (i == 0)
        return "width: 200px;";
      else if (i == 6 || i == 8 || i == 9)
        return "width: 50px;";
      else
        return "width: 100px;";
    }).text(function(d) {
      return d[0];
    });

    // create a row for each object in the data
    var rows = tbody.selectAll("tr").data(retVal["data"]).enter().append("tr");

    // create a cell in each row for each column
    var cells = rows.selectAll("td").data(function(row, i) {
      // map the column to the incoming data
      return colData.map(function(column) {
        return {
          column : column,
          value : row[column],
          rowdata : row
        };
      });
    }).enter().append("td").attr("style", function(d, i) {
      return ((i == 0 || i > 3) ? "text-align: center;" : "text-align: left;");
    }).html(
        function(d, i) {
          // these columns are read only
          if (i == 0 || i == 1 || i == 3 || i == 6)
            return d.value;
          // show the evidence evaluation
          else if (i == 7)
            return evaluateEvidenceView(d.rowdata);
          // editable fields
          else {
            // these colummns potentially have edit boxes
            if (i == 2 || i == 4 || i == 5) {
              // if there is no data give them the opportunity to enter it
              if (d.value == null) {
                // init the key
                var key = variantID + "~" + d.rowdata["ACMGCriteriaID"] + "~" + d.rowdata["EvidenceTypeID"];

                // init a ctrl ID
                var ctrlID = "";

                // is this the value column
                if (i == 2)
                  ctrlID = "inDataValue";
                // else the source column
                else if (i == 4)
                  ctrlID = "inDataSource";
                else
                  ctrlID + "inDataComment";

                return "<input type=\"text\" style=\"margin-left: 5px; size: 100;\" id=\"" + ctrlID + "_" + d.rowdata["EvidenceTypeID"]
                    + "_" + i + "\" onblur=\"$('#dirtyFlag" + ACMGCriteriaID + "').val('true');\"/>";
              } else
                return d.value;
            }
            // last 2 columns are pulldowns or check boxes
            else {
              // a value means we have some data to evaluate
              if (d.rowdata["Value"] != null) {
                // init the key
                var key = DonorCode + "~" + variantID + "~" + d.rowdata["ACMGCriteriaID"] + "~" + d.rowdata["EvidenceTypeID"];

                // create the control ID
                var ctrlID = DonorCode + "-" + d.rowdata["ACMGCriteriaID"] + "-" + d.rowdata["EvidenceTypeID"];

                // init the type and optons array
                var type = "";
                var arr;

                // the confidence field
                if (i == 8) {
                  // create an array for the selections
                  arr = [
                      [
                          [
                            "Low"
                          ], [
                            0
                          ]
                      ], [
                          [
                            "Medium"
                          ], [
                            1
                          ]
                      ], [
                          [
                            "High"
                          ], [
                            2
                          ]
                      ]
                  ];

                  // save the type
                  type = "Confidence";

                  // turn it into a pulldown
                  return createAPulldown(ctrlID + type, type, arr, d.value, key, "submitACMGCriteriaEvidencePulldown");
                }
                // the use the Criteria field
                else if (i == 9) {
                  // create an array for the selections
                  arr = [
                      [
                          [
                            "False"
                          ], [
                            0
                          ]
                      ], [
                          [
                            "True"
                          ], [
                            1
                          ]
                      ]
                  ];

                  // save the type
                  type = "UseEvidence";

                  // turn it into a pulldown
                  return createACheckBox(ctrlID + type, type, d.value, key, "submitACMGCriteriaEvidencePulldown");
                }
              } else
                return "<span style=\"color: red;\">Not available</span>";
            }
          }
        });
  }

  // return the container to the caller
  return c;
}

/**
 * draws the ACMG criteria assessment pulldown area
 * 
 * @param form
 * @param variantID
 * @param DonorCode
 * @param ACMGCriteriaID
 * @returns
 */
function drawACMGCriteriaAssessmentArea(form, variantID, DonorCode, ACMGCriteriaID) {
  // init the return
  var c = null;

  // make the request to get the evidence Criteria assessment data
  var retVal = launchDataRequest("getACMGEvidenceReviewCriteriaData", "VariantID=" + variantID + "&DONOR_CODE=" + DonorCode
      + "&ACMGCriteriaID=" + ACMGCriteriaID);

  // did we get a data error
  if (retVal["error"] == undefined) {
    // create an area for the already collected data for this variant
    var c = form.append("div").attr("id", "divACMGCriteriaEvidenceTable" + ACMGCriteriaID).attr("width", "100%").attr("style",
        "margin-top: 10px");

    // save a reference to the row data
    var rowdata = retVal["data"][0];

    // init the key
    var key = DonorCode + "~" + variantID + "~" + ACMGCriteriaID;
    var ctrlID = DonorCode + "-" + rowdata["ACMGCriteriaID"] + "CriteriaUsage";

    // create an array for the selections
    var arr = [
        [
            [
              "No"
            ], [
              0
            ]
        ], [
            [
              "Yes"
            ], [
              1
            ]
        ]
    ];

    // tack on the Criteria assessment pulldown
    var pd = c.append("div").attr("style", "margin-top: 10px; margin-left: 20px; margin-bottom: 10px;").html(
        createACheckBox(ctrlID, "CriteriaUsage", rowdata["CriteriaUsage"], key, "submitACMGCriteriaEvidencePulldown", true)
            + "Use criteria in evaluation");

    // init the key
    var key = DonorCode + "~" + variantID + "~" + ACMGCriteriaID;
    var ctrlID = DonorCode + "-" + rowdata["ACMGCriteriaID"] + "CriteriaOverride";

    // create an array for the selections
    var arr = [
        [
            [
              "Automatic"
            ], [
              EVIDENCE_OF_PATHOGENICITY.AUTO
            ]
        ], [
            [
              "Unknown"
            ], [
              EVIDENCE_OF_PATHOGENICITY.UNKNOWN
            ]
        ], [
            [
              "Very strong pathogenic"
            ], [
              EVIDENCE_OF_PATHOGENICITY.PATHO_VERY_STRONG
            ]
        ], [
            [
              "Strong pathogenic"
            ], [
              EVIDENCE_OF_PATHOGENICITY.PATHO_STRONG
            ]
        ], [
            [
              "Moderate pathogenic"
            ], [
              EVIDENCE_OF_PATHOGENICITY.PATHO_MODERATE
            ]
        ], [
            [
              "Supporting pathogenic"
            ], [
              EVIDENCE_OF_PATHOGENICITY.PATHO_SUPPORTING
            ]
        ], [
            [
              "Stand-alone benign"
            ], [
              EVIDENCE_OF_PATHOGENICITY.BENIGN_STAND_ALONE
            ]
        ], [
            [
              "Strong benign"
            ], [
              EVIDENCE_OF_PATHOGENICITY.BENIGN_STRONG
            ]
        ], [
            [
              "Supporting benign"
            ], [
              EVIDENCE_OF_PATHOGENICITY.BENIGN_SUPPORTING
            ]
        ]
    ];

    // tack on the Criteria assessment pulldown
    var pd = c.append("div").attr("style", "margin-top: 10px; margin-left: 20px; margin-bottom: 10px;")
        .html(
            "Override criteria classification: "
                + createAPulldown(ctrlID, "CriteriaOverride", arr, rowdata["CriteriaOverride"], key, "submitACMGCriteriaEvidencePulldown",
                    true));
  }
  // else just warn the user
  else {
    // show the error
    errorDisplay("drawACMGCriteriaEvidence - getACMGEvidenceReviewCriteriaData() - Error getting criteria assessment data.", 1);

    // show the user the error
    drawArea.append("div").attr("class", "err").text("Error getting criteria assessment data");
  }

  // return the container to the caller
  return c;
}

/**
 * draws the rule evaluation area
 * 
 */
function drawRuleEvaluationTable(c, form, variantID, DonorCode, ACMGCriteriaID) {
  // return to the caller
  return c;
}

/**
 * draws the adhoc evidence data collection area
 * 
 * @param form
 * @param variantID
 * @param ACMGCriteriaID
 * @returns
 */
function drawAdhocEvidenceDataEntryTable(c, variantID, ACMGCriteriaID) {
  // create the form tag
  var form = c.append("form").style("margin-top", "10px").style("margin-bottom", "10px").attr("id",
      "moreEvidenceDataEntryForm_" + ACMGCriteriaID);

  // save the key
  form.append("input").attr("type", "hidden").attr("name", "VariantID").attr("id", "VariantID").attr("value", variantID);

  // create the form area
  var controlArea = form;

  // get the pulldown data based on the selected value
  var retVal = launchDataRequest("getEvidenceTypes", "ACMGCriteriaID=" + ACMGCriteriaID);

  // did we get a data error
  if (retVal["error"] == undefined && retVal != '') {
    // add in the first value to the pulldown
    retVal["data"].unshift({
      "ID" : -1,
      "Name" : "Select a evidence type",
      "EvidenceType" : -1
    });

    // create the pulldown with a label
    controlArea.append("text").text("Add evidence:").attr("style", "margin-left: 15px;").append("select").attr("id", "EvidenceTypeID")
        .attr("style", "margin-left: 5px;").on("change", function() {
          $("#inDataValue").val('');
          $("#inDataSource").val('');
        }).selectAll('option').data(retVal["data"]).enter().append("option").attr("value", function(d) {
          return d.ID;
        }).text(function(d) {
          return d.Name;
        });

    // create a textbox for the data to enter
    controlArea.append("text").text("Value:").attr("style", "margin-left: 15px;").append("input").attr("id", "inDataValue").attr("type",
        "text").attr("style", "margin-left: 5px; size: 100;")

    // create a textbox for the source of the data entered
    controlArea.append("text").text("Source:").attr("style", "margin-left: 15px;").append("input").attr("id", "inDataSource").attr("type",
        "text").attr("style", "margin-left: 5px; size: 100;")

    // create a textbox for the comment of the data entered
    controlArea.append("text").text("Comment:").attr("style", "margin-left: 15px;").append("input").attr("id", "inDataComment").attr(
        "type", "text").attr("style", "margin-left: 5px; size: 100;")

    // create the submit button
    controlArea.append("input").attr("id", "btnAdd").attr("style", "margin-left: 15px;").attr("type", "button").attr("value", "Add").on(
        "click", function(d) {
          submitAdhocEvidenceForm(controlArea, variantID, ACMGCriteriaID);
        });
  }
  // else report the error
  else {
    // show the error
    errorDisplay("drawMoreEvidenceDataEntry - getEvidenceTypes() - " + retVal["error"], 1);

    // show the user the error
    controlArea.append("span").attr("Class", "err").text("Error:" + retVal["error"]);
  }

  // return to the caller
  return controlArea;
}

/**
 * perfoms the form submission for the Criteria evaluation area
 * 
 */
function submitACMGCriteriaEvidenceForm(variantID, ACMGCriteriaID) {
  // get a ref to the error control
  var errSpan = d3.select("#ACMGCriteriaID_" + ACMGCriteriaID + "_Err");

  // empty the span
  errSpan.html("");

  // get the form controls for the needed evidence
  var formCtrls = $("#divACMGCriteriaEvidenceTable" + ACMGCriteriaID + " :text");

  // init the return code
  var retVal = "";

  // loop through the controls and pick off the triplets
  for (var i = 0; i < formCtrls.length; i += 3) {
    if ((formCtrls[i].value == "" && formCtrls[i + 1].value != "") || (formCtrls[i].value != "" && formCtrls[i + 1].value == ""))
      retVal = "Please populate both the value and the source for all data types";
    else if (formCtrls[i].value != "" && formCtrls[i + 1].value != "") {
      // split the key to get the ACMG Criteria ID
      var arr = formCtrls[i].id.split("_");

      // initiate the update request
      retVal = persistAsMoreEvidence(variantID, arr[1], formCtrls[i].value, formCtrls[i + 1].value, formCtrls[i + 2].value, false);
    }

    // check the return
    if (retVal != "")
      break;
  }

  // did it go ok
  if (retVal == "") {
    // draws the recomendation after criteria analysis
    drawCriteriaEvaluationAnalysis(variantID);

    // redraw the ACMG Criteria Evidence data area
    drawACMGCriteriaEvidenceExpandable(variantID);

    // draw the Criteria collection assessment area
    drawACMGCriteriaCollectionAssessmentTable(variantID);

    // draw the collected evidence area
    drawMoreEvidenceDataEntry(variantID);

    // a little something for the user
    errSpan.attr("class", "success").append("text").text("Update successful.");

    // set the state of the dirty flag
    $("#dirtyFlag" + ACMGCriteriaID).val("false");
  } else {
    // show the error
    errorDisplay("submitACMGCriteriaEvidenceForm - Error updating form: " + retVal, 1);

    // display the error
    errSpan.attr("class", "err").attr("style", "margin-top: 10px; margin-left: 20px; font-size: 13px;").text(
        "Error updating criteria evidence: " + retVal);
  }
}

/**
 * handles updates for the Criteria evaluation selection area
 * 
 * @param ctrlID
 * @param type
 * @param key
 * @param value
 */
function submitACMGCriteriaEvidencePulldown(ctrlID, type, key, Criteria) {
  // get a ref to the control
  var ctrl = d3.select("#" + ctrlID);

  // init the selected value
  var selValue = '';

  // is this the check box
  if (ctrl[0][0].nodeName == 'INPUT')
    selValue = ($("#" + ctrlID).is(':checked')) ? 1 : 0;
  // get the selected value
  else
    selValue = $("#" + ctrlID + " option:selected").val();

  // split the key to get the ACMG Criteria ID
  var arr = key.split("~");

  // get a ref to the error control and clear it
  var errSpan = d3.select("#ACMGCriteriaID_" + arr[2] + "_Err");

  // empty the span
  errSpan.html("");

  // was a valid selection made
  if (selValue != -1) {
    // init the query string
    var queryString = "type=" + type + "&key=" + key + "&value=" + selValue;

    // init the request type
    var request = "";

    // check to see what the request type will be
    if (Criteria == false)
      request = "updateACMGEvidenceReviewData";
    else
      request = "updateACMGEvidenceReviewCriteriaData";

    // make the request to update with all form controls
    var retVal = launchDataRequest(request, queryString);

    // did it go ok
    if (retVal["error"] == undefined) {
      // change the color of the control to indicate success
      ctrl.style("border-width", "5px").transition().style("border-color", "green").transition().delay(2000).duration(750).style(
          "border-width", "1px").style("border-color", "black");

      // redo the criteria analysis
      drawCriteriaEvaluationAnalysis(arr[1]);
    } else {
      // show the error
      errorDisplay("submitACMGCriteriaEvidencePulldown - " + retVal["error"], 1);

      // change the color of the control to indicate an error
      ctrl.style("border-width", "5px").transition().style("border-color", "red").transition().delay(2000).duration(750).style(
          "border-width", "1px").style("border-color", "black");

      // revert to the previous value
      ctrl[0][0].selectedIndex = parseInt(prev_select_val) + 1;

      // display the error
      errSpan.attr("class", "err").attr("style", "margin-top: 10px; margin-left: 20px; font-size: 13px;").text(
          "Error detected: " + retVal["error"]);
    }
  } else {
    // show the error
    errorDisplay("submitACMGCriteriaEvidencePulldown - Invalid pulldown selection", 1);

    // change the color of the control to indicate an error
    ctrl.style("border-width", "5px").transition().style("border-color", "red").transition().delay(2000).duration(750).style(
        "border-width", "1px").style("border-color", "black");

    // revert to the previous value
    ctrl[0][0].selectedIndex = parseInt(prev_select_val) + 1;

    // display the error
    errSpan.attr("class", "err").attr("style", "margin-top: 10px; margin-left: 20px; font-size: 13px;").text(
        "Error detected: Invalid pulldown selection.");
  }
}

/**
 * handles updates for the Criteria evaluation selection area
 * 
 * @param ctrlID
 * @param type
 * @param key
 * @param value
 */
function submitACMGOverridePulldown(ctrlID, type, key) {
  // get a ref to the control
  var ctrl = d3.select("#" + ctrlID);

  // init the selected value
  var selValue = '';

  // get the selected value
  selValue = $("#" + ctrlID + " option:selected").val();

  // split the key to get the ACMG Criteria ID
  var arr = key.split("~");

  // get a ref to the error control and clear it
  var errSpan = d3.select("#ACMGOverride_Err");

  // empty the span
  errSpan.html("");

  // was a valid selection made
  if (selValue != -1) {
    // init the query string
    var queryString = "DONOR_CODE=" + arr[0] + "&VariantID=" + arr[1] + "&overrideClassification=" + selValue;

    // init the request type
    var request = "updateACMGClassificationData";

    // make the request to update with all form controls
    var retVal = launchDataRequest(request, queryString);

    // did it go ok
    if (retVal["error"] == undefined || retVal == '') {
      // change the color of the control to indicate success
      ctrl.style("border-width", "5px").transition().style("border-color", "green").transition().delay(2000).duration(750).style(
          "border-width", "1px").style("border-color", "black");

      // redo the criteria analysis
      drawCriteriaEvaluationAnalysis(arr[1]);
    } else {
      // show the error
      errorDisplay("submitACMGOverridePulldown - " + retVal["error"], 1);

      // change the color of the control to indicate an error
      ctrl.style("border-width", "5px").transition().style("border-color", "red").transition().delay(2000).duration(750).style(
          "border-width", "1px").style("border-color", "black");

      // revert to the previous value
      ctrl[0][0].selectedIndex = parseInt(prev_select_val) + 1;

      // display the error
      errSpan.attr("class", "err").attr("style", "margin-top: 10px; margin-left: 20px; font-size: 13px;").text(
          "Error detected: " + retVal["error"]);
    }
  } else {
    // show the error
    errorDisplay("submitACMGOverridePulldown - Invalid pulldown selection", 1);

    // change the color of the control to indicate an error
    ctrl.style("border-width", "5px").transition().style("border-color", "red").transition().delay(2000).duration(750).style(
        "border-width", "1px").style("border-color", "black");

    // revert to the previous value
    ctrl[0][0].selectedIndex = parseInt(prev_select_val) + 1;

    // display the error
    errSpan.attr("class", "err").attr("style", "margin-top: 10px; margin-left: 20px; font-size: 13px;").text(
        "Error detected: Invalid pulldown selection.");
  }
}

/**
 * draws the ACMG Criteria fulfilled area
 * 
 * @param dataSet
 */
function drawACMGCriteriaAssessmentFulfilled() {
  // get a reference to the overview area
  var container = d3.select("#divCriteriaAssessmentFulfilledArea");

  // define the data in the cells
  var colData = [
      CriteriaSatisfied + " Criteria satisfied", CriteriaIncomplete + " Criteria incomplete",
      (28 - CriteriaSatisfied - CriteriaIncomplete) + " Criteria with no data"
  ];

  // create a table, header and body
  var table = container.append("table").attr("border", "1").append("tr");

  // create a cell in each row for each column
  var cells = table
      .selectAll("td")
      .data(colData)
      .enter()
      .append("td")
      .attr(
          "style",
          "width: 200px; height:30px; color: white; background-color: white; text-align: center; text-shadow: -2px -2px 2px #111, 2px -2px 2px #111, -2px 2px 2px #111, 2px 2px 2px #111")
      .transition().duration(750).style("background-color", function(d, i) {
        if (i == 0)
          retVal = "Green"; // return green
        else if (i == 1)
          retVal = "GoldenRod"; // yellow
        else
          retVal = "FireBrick"; // red

        // return to the caller
        return retVal;
      }).style("", "").text(function(d) {
        return d;
      });
}

/**
 * draws the rule evaluation table for this rule
 * 
 * @param variantID
 * 
 */
function drawACMGEvidenceEvaluationTable(c, variantID, DonorCode, ACMGCriteriaID) {
  // make the request to get the evidence data
  var retVal = launchDataRequest("getACMGEvidenceReviewData", "VariantID=" + variantID + "&DONOR_CODE=" + DonorCode + "&ACMGCriteriaID="
      + ACMGCriteriaID);

  // did we get a data error
  if (retVal["error"] == undefined) {
    // define the table column names
    var colHeader = [
        [
          "Evidence"
        ], [
          "Value"
        ], [
          "Evaluation"
        ]
    ];

    // define the data column names
    var colData = [
        [
          "Name"
        ], [
          "Value"
        ], [
          "Evaluation"
        ]
    ];

    // throw down a table title
    c.append("div").attr("style", "margin-top: 10px; font-size: 15px;").text("ACMG Evidence evaluation");

    // create a table, header and body
    var table = c.append("table").attr("class", "standard").attr("style", "margin-bottom: 10px");
    var thead = table.append("thead");
    var tbody = table.append("tbody");

    // populate the header
    thead.append("tr").selectAll("th").data(colHeader).enter().append("th").attr("class", "dataentry").attr("style", function(d, i) {
      return ((i > 1) ? "width:450px;" : "width:250px;");
    }).text(function(d) {
      return d[0];
    });

    // create a row for each object in the data
    var rows = tbody.selectAll("tr").data(retVal["data"]).enter().append("tr");

    // create a cell in each row for each column
    var cells = rows.selectAll("td").data(function(row, i) {
      // map the column to the incoming data
      return colData.map(function(column) {
        return {
          column : column,
          value : row[column],
          rowdata : row
        };
      });
    }).enter().append("td").attr("style", function(d, i) {
      return ((i == 0 || i > 2) ? "text-align: center;" : "text-align: left;");
    }).html(function(d, i) {
      // check the evidence
      if (i == 2)
        return evaluateEvidenceView(d.rowdata);
      else
        return d.value;
    });
  }

  // return the container to the caller
  return c;
}

function drawACMGPathogenicityEvaluation() {
}
