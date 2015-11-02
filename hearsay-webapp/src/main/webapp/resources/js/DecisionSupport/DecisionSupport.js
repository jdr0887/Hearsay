// logged in user name
var userName = "";

// init the recommendation
var recommendation = "Unknown significance";

// Evidence collection status
var EVIDENCE_STATUS = {
  COMPLETE : 0,
  PARTIAL : 1,
  NO_DATA : 2
};

// rule determination status
var RULE_STATUS = {
  FALSE : 0,
  TRUE : 1,
  UNKNOWN : 2
};

// ACMG rule types
var ACMG_RULE_TYPE = {
  PVS1 : 0,
  PS1 : 1,
  PS2 : 2,
  PS3 : 3,
  PS4 : 4,
  PM1 : 5,
  PM2 : 6,
  PM3 : 7,
  PM4 : 8,
  PM5 : 9,
  PM6 : 10,
  PP1 : 11,
  PP2 : 12,
  PP3 : 13,
  PP4 : 14,
  PP5 : 15,
  BA1 : 16,
  BS1 : 17,
  BS2 : 18,
  BS3 : 19,
  BS4 : 20,
  BP1 : 21,
  BP2 : 22,
  BP3 : 23,
  BP4 : 24,
  BP5 : 25,
  BP6 : 26,
  BP7 : 27
};

var ACMG_SHORT_NAME = {
  PVS1 : "LOF",
  PS1 : "",
  PS2 : "De novo",
  PS3 : "",
  PS4 : "",
  PM1 : "",
  PM2 : "",
  PM3 : "",
  PM4 : "",
  PM5 : "",
  PM6 : "",
  PP1 : "",
  PP2 : "",
  PP3 : "",
  PP4 : "",
  PP5 : "",
  BA1 : "Freq > 5%",
  BS1 : "",
  BS2 : "",
  BS3 : "",
  BS4 : "",
  BP1 : "",
  BP2 : "",
  BP3 : "",
  BP4 : "",
  BP5 : "",
  BP6 : "",
  BP7 : ""
}

/**
 * Evidence object
 * 
 * @param status
 * @param type
 * @param value
 * @returns
 */
function Evidence(name, value, type) {
  // member variables
  this.Name = name;
  this.Value = value;
  this.Type = type;

  // setters
  this.setName = function(val) {
    this.Name = val;
  };
  this.setValue = function(val) {
    this.Value = val;
  };
  this.setType = function(val) {
    this.Type = val;
  };

  // getters
  this.getName = function() {
    return this.Name;
  };
  this.getValue = function() {
    return this.Value;
  };
  this.getType = function() {
    return this.Type;
  };
}

/**
 * ACMG rule/data association
 * 
 */
function ACMG_Rule(status, name, type) {
  // member variables
  this.Status = status;
  this.Name = name;
  this.Type = type;
  this.CompleteMsg = "";
  this.PartialMsg = "";
  this.RuleDetermination = RULE_STATUS.UNKNOWN;

  // setters
  this.setStatus = function(val) {
    this.Status = val;
  };
  this.setName = function(val) {
    this.Name = val;
  };
  this.setType = function(val) {
    this.Type = val;
  };
  this.setPartialMsg = function(val) {
    this.PartialMsg = val;
  };
  this.setCompleteMsg = function(val) {
    this.CompleteMsg = val;
  };
  this.setRuleDetermination = function(val) {
    this.RuleDetermination = val;
  };

  // getters
  this.getStatus = function() {
    return this.Status;
  };
  this.getName = function() {
    return this.Name;
  };
  this.getType = function() {
    return this.Type;
  };
  this.getPartialMsg = function() {
    return this.PartialMsg;
  };
  this.getCompleteMsg = function() {
    return this.CompleteMsg;
  };
  this.getRuleDetermination = function() {
    return this.RuleDetermination;
  };
}

// array for the storage of evidence
// the array elements should be of type Evidence
var evidence = [];
var ACMGRule = [];

/**
 * main entry point for rendering this area
 */
function renderDecisionSupport(decisionSupportingEvidenceTab, inUserName)// ,
// decisionSupportACMGGridTab
{
  userName = inUserName;

  // render the supporting evidence area
  renderDecisionSupportingEvidence(decisionSupportingEvidenceTab);

  // render the ACMG grid area
  // renderDecisionSupportACMGGrid(decisionSupportACMGGridTab);
};

/**
 * renders the Supporting Evidence region
 */
function renderDecisionSupportingEvidence(container) {
  // get the canvas area
  var c = d3.select(container);

  // render when a variant is selected
  dispatch.on("select_gene.supportingEvidence", selectVariant);
}

/**
 * handler for when a user selects a variant
 * 
 * @param dataSet
 */
function selectVariant(dataSet) {
  // no data then return
  if (dataSet == '')
    return;

  // did we get a data error
  if (typeof dataSet["error"] != 'undefined')
    displayError(dataSet["error"]);
  else {
    // reset the recommendation flag
    recommendation = "Unknown significance";

    // load the evidence
    loadEvidence(dataSet);

    // draw the title area
    drawTitle(dataSet);

    // draw the rule status area
    drawRuleStatus(dataSet);

    // draw the analysis area
    drawAnalysis(dataSet);

    // draw the data to rule application area
    drawDataRuleApplication(dataSet);

    // draw data entry area
    drawDataEntry(dataSet);
  }
}

/**
 * loads the evidence it can from the data set
 * 
 */
function loadEvidence(dataSet) {
  // clear the arrays
  ACMGRule = [];
  evidence = [];

  // update the 1000 genome pop frequency
  evidence.push(new Evidence("1000 Genome max allele frequency", dataSet.selectedVariant.tgFreq, ACMG_RULE_TYPE.BA1));

  // update the variant effect and allele type
  evidence.push(new Evidence("Variant effect", dataSet.selectedVariant.variantEffect, ACMG_RULE_TYPE.PVS1));

  // update allele type
  evidence.push(new Evidence("Allele type", dataSet.selectedVariant.type));

  // update the NCG pop frequency
  evidence.push(new Evidence("NCGENES allele frequency", dataSet.selectedVariant.ncgFreq));

  // get evidence collected by the system
  var geneEvidence = getEvidenceData(dataSet.selectedVariant.geneName);

  // update the rule status
  applyRulesToEvidence();

  // make the recommendation
  makeRecommendation();
}

/**
 * gets the evidence data for the locvarid
 * 
 */
function getEvidenceData(geneName) {
  // init the return value
  var retVal = null;

  // get the loc var evidence data
  retVal = launchDataRequest("getEvidenceData", "&geneName=" + geneName);

  // did we get a transcript data error
  if (retVal["error"] != undefined) {
    // show the error
    errorDisplay("getEvidenceData() - " + retVal["error"], 1);
  } else {
    // save it
    evidence.push(new Evidence(retVal.data.name, retVal.data.value, retVal.data.type));
  }
}

/**
 * Sets the evidence data for the locvarid
 * 
 */
function setEvidenceData(geneName, name, value, type) {
  // init the return value
  var retVal = null;

  // set the loc var evidence data and get it back
  retVal = launchDataRequest("setEvidenceData", "&geneName=" + geneName + "&name=" + name + "&type=" + type + "&value=" + value);

  // did we get a transcript data error
  if (typeof retVal["error"] != 'undefined') {
    // show the error
    errorDisplay("setEvidenceData() - " + retVal["error"], 1);
  } else {
    // save it
    evidence.push(new Evidence(name, value, type));
  }
}

/**
 * applies the rules to the evidence
 * 
 */
function applyRulesToEvidence() {
  // reset all rules
  for ( var art in ACMG_RULE_TYPE) {
    // does this value exist
    if (typeof art !== 'undefined') {
      // get the rule type
      var type = ACMG_RULE_TYPE[art];

      // get the name of the rule
      var name = ACMG_SHORT_NAME[art] + " (" + art + ")";

      // create all the types of evidence
      ACMGRule.push(new ACMG_Rule(EVIDENCE_STATUS.NO_DATA, name, type));
    }
  }

  // set up a loop to apply data and messages to the rules
  for (var i = 0; i < ACMGRule.length; i++) {
    // get the evidence for this rule
    data = findEvidence(ACMGRule[i].getType());

    // specific rule checks
    if (ACMGRule[i].getType() == ACMG_RULE_TYPE.PVS1) {
      // is this a null variant. if so it needs the LOF value
      if (data["Variant effect"] == "nonsense" || data["Variant effect"] == "frameshifting indel"
          || data["Variant effect"] == "splice-site") {
        // has anyone reported the LOF on this gene
        if (data["LOF"] !== undefined) {
          // start the output message
          ACMGRule[i].setCompleteMsg(ACMGRule[i].getName() + " - " + data["LOF"] + ", previously declared");

          // rule has been satisfied
          ACMGRule[i].setStatus(EVIDENCE_STATUS.COMPLETE);
        }
        // else we need the LOF value
        else {
          // start the output message
          ACMGRule[i].setPartialMsg(ACMGRule[i].getName() + " - Need LOF value");

          // rule has not been satisfied
          ACMGRule[i].setStatus(EVIDENCE_STATUS.PARTIAL);
        }
      } else {
        // start the output message
        ACMGRule[i].setCompleteMsg(ACMGRule[i].getName() + " - False, not a null variant");

        // rule has been satisfied
        ACMGRule[i].setStatus(EVIDENCE_STATUS.COMPLETE);
      }
    }
    // are we interrogating for rule BA1
    else if (ACMGRule[i].getType() == ACMG_RULE_TYPE.BA1) {
      // pass/fail rule check
      if (data["1000 Genome max allele frequency"] >= .05)
        ACMGRule[i].setCompleteMsg(ACMGRule[i].getName() + " - True, Allele freq >= 5%");
      else
        ACMGRule[i].setCompleteMsg(ACMGRule[i].getName() + " - False, Allele freq < 5%");

      // rule has been satisfied
      ACMGRule[i].setStatus(EVIDENCE_STATUS.COMPLETE);
    }
  }
}

/**
 * determines the recommendation
 * 
 */
function makeRecommendation() {
  // do we have complete PVS1 rule
  if (ACMGRule[ACMG_RULE_TYPE.PVS1].getStatus() == EVIDENCE_STATUS.COMPLETE) {
    // get the evidence and completion info for the PVS1 rule
    pvs1Data = findEvidence(ACMG_RULE_TYPE.PVS1);

    // determine if the data means true/false
    if ((pvs1Data["Variant effect"] == "nonsense" || pvs1Data["Variant effect"] == "frameshifting indel" || pvs1Data["Variant effect"] == "splice-site")
        && (pvs1Data["LOF"] == "True"))
      ACMGRule[ACMG_RULE_TYPE.PVS1].setRuleDetermination(RULE_STATUS.TRUE);
    else
      ACMGRule[ACMG_RULE_TYPE.PVS1].setRuleDetermination(RULE_STATUS.FALSE);
  }

  // do we have complete BA1 rule
  if (ACMGRule[ACMG_RULE_TYPE.BA1].getStatus() == EVIDENCE_STATUS.COMPLETE) {
    // get the evidence and completion info for the BA1 rule
    ba1Data = findEvidence(ACMG_RULE_TYPE.BA1);

    // determine if the data means true/false
    if (ba1Data["1000 Genome max allele frequency"] >= .05)
      ACMGRule[ACMG_RULE_TYPE.BA1].setRuleDetermination(RULE_STATUS.TRUE);
    else if (ba1Data["1000 Genome max allele frequency"] < .05)
      ACMGRule[ACMG_RULE_TYPE.BA1].setRuleDetermination(RULE_STATUS.FALSE);
  }

  // set the recommendation
  if (ACMGRule[ACMG_RULE_TYPE.PVS1].getRuleDetermination() == RULE_STATUS.TRUE) {
    if (ACMGRule[ACMG_RULE_TYPE.BA1].getRuleDetermination() == RULE_STATUS.TRUE)
      recommendation = "VUS";
    else
      recommendation = "VUS";
  } else {
    if (ACMGRule[ACMG_RULE_TYPE.BA1].getRuleDetermination() == RULE_STATUS.TRUE)
      recommendation = "Benign";
    else
      recommendation = "VUS";
  }
}

/**
 * draws the title area
 * 
 * @param dataSet
 */
function drawTitle(dataSet) {
  // get a reference to the
  var container = d3.select("#DecisionSupportTitle");

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
      .text("Gene: " + dataSet.selectedVariant.geneName + ", " + dataSet.selectedVariant.hgvsName);

  /*
   * some more title text var textArea = grp.append("text") .attr("x", 330)
   * .attr("y", 13) .attr("fill", "black") .attr("font-style", "bold")
   * .attr("font-size", "18") .text("ACMG Guidelines");
   */

  // even more title text
  var textArea = grp.append("text").attr("x", 700).attr("y", 12).attr("fill", "black").attr("font-style", "bold").attr("font-size", "14")
      .text("Analyst: " + userName + " | UNC-CH");

  // create the guideline button
  var rectangle = grp.append("rect").attr("x", 300).attr("y", 22).attr("width", 190).attr("height", 20).attr("fill", "#FF6600").attr(
      "stroke-width", 1).attr("stroke", "#C0C0C0");

  // add some text for the guideline button
  var textArea = grp.append("text").attr("x", 303).attr("y", 37).attr("fill", "white").attr("font-style", "bold").attr("font-size", "14")
      .text("Click to see ACMG Guidelines");

  // create the sources button
  var rectangle = grp.append("rect").attr("x", 710).attr("y", 22).attr("width", 140).attr("height", 20).attr("fill", "#4D94FF").attr(
      "stroke-width", 1).attr("stroke", "#C0C0C0");

  // add some text for the sources button
  var textArea = grp.append("text").attr("x", 717).attr("y", 37).attr("fill", "white").attr("font-style", "bold").attr("font-size", "14")
      .text("Click to see Sources");
}

/**
 * draws the rule status area
 * 
 * @param dataSet
 */
function drawRuleStatus(dataSet) {
  // get a reference to the overview area
  var container = d3.select("#DecisionSupportRuleStatus");

  // remove the rendered svg area if it exists
  container.select("svg").remove();

  // get a reference to the newly created svg region for the evidence overview
  // area
  var overviewArea = container.append("svg").attr("width", "100%").attr("height", "135px");

  // create a group for the title box, encompassing box and the text
  var grp = overviewArea.append("g");

  // title text for the ACMG rules met counts
  grp.append("text").attr("x", 5).attr("y", 35).attr("fill", "black").attr("font-style", "bold").attr("font-size", "18").text(
      "ACMG rule status");

  // create the evidence status count boxes
  drawACMGRuleCount(grp, EVIDENCE_STATUS.COMPLETE);
  drawACMGRuleCount(grp, EVIDENCE_STATUS.PARTIAL);
  drawACMGRuleCount(grp, EVIDENCE_STATUS.NO_DATA);
}

/**
 * draws the box of the rule count
 * 
 * @param count
 */
function drawACMGRuleCount(container, status) {
  var xpos = 0;
  var color = "";
  var count = 0;
  var title = "";

  // get the details for the status type
  if (status == EVIDENCE_STATUS.COMPLETE) {
    xpos = 5;
    color = "Green";
    title = "Complete";
  } else if (status == EVIDENCE_STATUS.PARTIAL) {
    xpos = 165;
    color = "GoldenRod";
    title = "Partial";
  } else if (status == EVIDENCE_STATUS.NO_DATA) {
    xpos = 325;
    color = "FireBrick";
    title = "No Data";
  }

  // get the number of status types
  for (var i = 0; i < ACMGRule.length; i++) {
    // is this the status type we are looking for
    if (ACMGRule[i].getStatus() == status)
      count++;
  }

  // create a group for the title box, encompassing box and the text
  var grp = container.append("g");

  // find the center of the title text
  var centerTitlePos = 75 - ((title.length / 2) * 8.5);

  // tack on the units
  var countText = count + " Rule(s)";

  // find the center of the title text
  var centerCountPos = 75 - ((countText.length / 2) * 9.5);

  // create the encompassing box
  var rectangle = grp.append("rect").attr("x", xpos).attr("y", 65).attr("width", 150).attr("height", 70).attr("fill", "white").attr(
      "stroke-width", 1).attr("stroke", "#C0C0C0");

  // create the title box
  var rectangle = grp.append("rect").attr("x", xpos).attr("y", 45).attr("width", 150).attr("height", 30).attr("fill", color).attr(
      "stroke-width", 1).attr("stroke", "#C0C0C0");

  // some title text
  var textArea = grp.append("text").attr("x", xpos + centerTitlePos).attr("y", 65).attr("fill", "white").attr("font-style", "bold").attr(
      "font-size", "18").text(title);

  // some count text
  var textArea = grp.append("text").attr("x", xpos + centerCountPos).attr("y", 115).attr("fill", "black").attr("font-style", "bold").attr(
      "font-size", "24").text(countText);
}

/**
 * draws the data to rule application area
 * 
 * @param dataSet
 */
function drawDataRuleApplication(dataSet) {
  // get a reference to the area
  var container = d3.select("#DecisionSupportRuleApplication");

  // remove the rendered svg area if it exists
  container.select("svg").remove();

  // get a reference to the newly created svg region for the supporting data
  // area
  var supportingDataArea = container.append("svg").attr("width", "100%").attr("height", "200px");

  // create a group for the title box, encompassing box and the text
  var grp = supportingDataArea.append("g");

  // title text for the title line
  grp.append("text").attr("x", 0).attr("y", 15).attr("fill", "black").attr("font-style", "bold").attr("font-size", "18").text(
      "ACMG data to rule application");

  /*
   * create the data entry button var rectangle = grp.append("rect") .attr("x",
   * 500) .attr("y", 0) .attr("width", 200) .attr("height", 21) .attr("fill",
   * "#CDD5EC") .attr("stroke-width", 1) .attr("stroke", "#C0C0C0"); // some
   * button text var textArea = grp.append("text") .attr("x", 505) .attr("y",
   * 17) .attr("fill", "black") .attr("font-style", "bold") .attr("font-size",
   * "18") .text("Click to add data points");
   */

  // create the encompassing box for the satisfied rules
  var rectangle = grp.append("rect").attr("x", 0).attr("y", 25).attr("width", 250).attr("height", 170).attr("fill", "#CCFFCC").attr(
      "stroke-width", 1).attr("stroke", "#C0C0C0");

  // create the title box
  var rectangle = grp.append("rect").attr("x", 0).attr("y", 25).attr("width", 250).attr("height", 25).attr("fill", "green").attr(
      "stroke-width", 1).attr("stroke", "#C0C0C0");

  // some title text
  var textArea = grp.append("text").attr("x", 5).attr("y", 43).attr("fill", "white").attr("font-style", "bold").attr("font-size", "18")
      .text("Satisfied rules");

  // start of test position
  var yPos = 65;

  // set up a loop to display the satisfied rules
  for (var i = 0; i < ACMGRule.length; i++) {
    // was there a msg to report
    if (ACMGRule[i].getStatus() == EVIDENCE_STATUS.COMPLETE && ACMGRule[i].getCompleteMsg() != "") {
      // show the evidence value
      var textArea = grp.append("text").attr("x", 7).attr("y", yPos).attr("fill", "black").attr("font-style", "bold").attr("font-size",
          "12").text(ACMGRule[i].getCompleteMsg());

      // move down a little
      yPos += 17;
    }
  }

  // create the encompassing box for the partially satisfied rules
  var rectangle = grp.append("rect").attr("x", 260).attr("y", 25).attr("width", 240).attr("height", 170).attr("fill", "#F0E0B2").attr(
      "stroke-width", 1).attr("stroke", "#C0C0C0");

  // create the title box
  var rectangle = grp.append("rect").attr("x", 260).attr("y", 25).attr("width", 240).attr("height", 25).attr("fill", "#D1A319").attr(
      "stroke-width", 1).attr("stroke", "#C0C0C0");

  // some title text
  var textArea = grp.append("text").attr("x", 265).attr("y", 43).attr("fill", "white").attr("font-style", "bold").attr("font-size", "18")
      .text("Partially satisfied rules");

  var yPos = 65;

  // set up a loop to display the partially satisfied rules
  for (var i = 0; i < ACMGRule.length; i++) {
    // was there a msg to report
    if (ACMGRule[i].getStatus() == EVIDENCE_STATUS.PARTIAL && ACMGRule[i].getPartialMsg() != "") {
      // show the evidence value
      var textArea = grp.append("text").attr("x", 267).attr("y", yPos).attr("fill", "black").attr("font-style", "bold").attr("font-size",
          "12").text(ACMGRule[i].getPartialMsg());

      // move down a little
      yPos += 17;
    }
  }

  // create the encompassing box for the evidence
  var rectangle = grp.append("rect").attr("x", 510).attr("y", 25).attr("width", 340).attr("height", 170).attr("fill", "#CDD5EC").attr(
      "stroke-width", 1).attr("stroke", "#C0C0C0");

  // create the title box
  var rectangle = grp.append("rect").attr("x", 510).attr("y", 25).attr("width", 340).attr("height", 25).attr("fill", "#4D94FF").attr(
      "stroke-width", 1).attr("stroke", "#C0C0C0");

  // some title text
  var textArea = grp.append("text").attr("x", 515).attr("y", 43).attr("fill", "white").attr("font-style", "bold").attr("font-size", "18")
      .text("Collected data elements");

  var yPos = 65;

  // display the evidence
  for (var i = 0; i < evidence.length; i++) {
    // show the evidence value
    var textArea = grp.append("text").attr("x", 517).attr("y", yPos).attr("fill", "black").attr("font-style", "bold").attr("font-size",
        "12").text(evidence[i].getName() + ": " + evidence[i].getValue());

    // move down a little
    yPos += 17;
  }
}

/**
 * finds the associated evidence data with the rule
 * 
 */
function findEvidence(ruleID) {
  // init the return vale
  var retVal = {};

  // for each peice of evidence
  for (var i = 0; i < evidence.length; i++) {
    // is this the one
    if (evidence[i].getType() == ruleID) {
      // get the data point
      retVal[evidence[i].getName()] = evidence[i].getValue();
    }
  }

  // return to the caller
  return retVal;
}

/**
 * draws the analysis area
 * 
 * @param dataSet
 */
function drawAnalysis(dataSet) {
  // get a reference to the newly created svg region for the analysis area
  var container = d3.select("#DecisionSupportAnalysis");

  // remove the rendered svg area if it exists
  container.select("svg").remove();

  // get a reference to the newly created svg region for the recommendation
  // area
  var recommendationArea = container.append("svg").attr("width", "100%").attr("height", "130px");

  // create a group for the title box, encompassing box and the text
  var grp = recommendationArea.append("g");

  // title text for the ACMG rules met counts
  grp.append("text").attr("x", 0).attr("y", 15).attr("fill", "black").attr("font-style", "bold").attr("font-size", "18").text(
      "ACMG rule analysis");

  // create the encompassing box
  var rectangle = grp.append("rect").attr("x", 0).attr("y", 25).attr("width", 250).attr("height", 90).attr("fill", "#FFD699").attr(
      "stroke-width", 1).attr("stroke", "#C0C0C0");

  // create the title box
  var rectangle = grp.append("rect").attr("x", 0).attr("y", 25).attr("width", 250).attr("height", 25).attr("fill", "#FF6600").attr(
      "stroke-width", 1).attr("stroke", "#C0C0C0");

  // some title text
  var textArea = grp.append("text").attr("x", 5).attr("y", 44).attr("fill", "white").attr("font-style", "bold").attr("font-size", "18")
      .text("Recommenation");

  // some title text
  var textArea = grp.append("text").attr("x", 10).attr("y", 83).attr("fill", "black").attr("font-style", "bold").attr("font-size", "16")
      .text(recommendation);

  // create the change button
  var rectangle = grp.append("rect").attr("x", 175).attr("y", 53).attr("width", 70).attr("height", 25).attr("fill", "white").attr(
      "stroke-width", 1).attr("stroke", "black");

  // some button text
  var textArea = grp.append("text").attr("x", 190).attr("y", 70).attr("fill", "black").attr("font-size", "12").text("Change");

  // create the submit button
  var rectangle = grp.append("rect").attr("x", 175).attr("y", 86).attr("width", 70).attr("height", 25).attr("fill", "#4D94FF").attr(
      "stroke-width", 1).attr("stroke", "black");

  // some button text
  var textArea = grp.append("text").attr("x", 190).attr("y", 103).attr("fill", "white").attr("font-size", "12").text("Submit");
}

/**
 * renders the data entry area
 * 
 */
function drawDataEntry(dataSet) {
  // get a reference to the newly created svg region for the analysis area
  var container = d3.select("#DecisionSupportDataEntry");

  // remove the rendered svg area if it exists
  container.select("svg").remove();

  // get a reference to the newly created svg region for the recommendation
  // area
  var dataEntryArea = container.append("svg").attr("width", "100%");
  // .attr("height", "125px")

  // create a group for the title box, encompassing box and the text
  var grp = dataEntryArea.append("g");

  // title text for the ACMG data entry area
  grp.append("text").attr("x", 0).attr("y", 15).attr("fill", "black").attr("font-style", "bold").attr("font-size", "18").text("Data entry");

  // create the encompassing box
  grp.append("rect").attr("x", 0).attr("y", 25).attr("width", 850).attr("height", 500).attr("fill", "#CECDCC").attr("stroke-width", 1)
      .attr("stroke", "#C0C0C0");

  // create the title box
  grp.append("rect").attr("x", 0).attr("y", 25).attr("width", 850).attr("height", 25).attr("fill", "#0E0D0C").attr("stroke-width", 1).attr(
      "stroke", "#C0C0C0");

  // some title text
  grp.append("text").attr("x", 5).attr("y", 44).attr("fill", "white").attr("font-style", "bold").attr("font-size", "18").text("Data Entry");

  // only show these buttons if the variant effect is the correct type
  if (dataSet.selectedVariant.variantEffect == "nonsense" || dataSet.selectedVariant.variantEffect == "frameshifting indel"
      || dataSet.selectedVariant.variantEffect == "splice-site") {
    grp.append("text").attr("x", 10).attr("y", 70).attr("fill", "black").attr("font-style", "bold").attr("font-size", "14").text(
        "Are LOF alleles a known mechanism of disease for " + dataSet.selectedVariant.geneName + "?");

    grp.append("rect").attr("x", 390).attr("y", 55).attr("width", 45).attr("height", 20).attr("fill", "#CCFFCC").attr("stroke-width", 1)
        .attr("stroke", "#C0C0C0");

    grp.append("text").attr("x", 397).attr("y", 70).attr("fill", "black").attr("font-style", "bold").attr("font-size", "14").text("True");

    grp.append("rect").attr("x", 390).attr("y", 55).attr("width", 45).attr("height", 20).attr("stroke-width", 1).attr("stroke", "black")
        .style("opacity", 0).on("click", function() {
          // set the evidence
          setEvidenceData(dataSet.selectedVariant.geneName, "LOF", "True", ACMG_RULE_TYPE.PVS1);

          // redraw everything
          selectVariant(dataSet);
        });

    grp.append("rect").attr("x", 440).attr("y", 55).attr("width", 45).attr("height", 20).attr("fill", "#FF9494").attr("stroke-width", 1)
        .attr("stroke", "#C0C0C0");

    grp.append("text").attr("x", 446).attr("y", 70).attr("fill", "black").attr("font-style", "bold").attr("font-size", "14").text("False");

    grp.append("rect").attr("x", 440).attr("y", 55).attr("width", 45).attr("height", 20).attr("stroke-width", 1).attr("stroke", "black")
        .style("opacity", 0).on("click", function() {
          // set the evidence
          setEvidenceData(dataSet.selectedVariant.geneName, "LOF", "False", ACMG_RULE_TYPE.PVS1);

          // redraw everything
          selectVariant(dataSet);
        });
  }
}

/**
 * renders the ACMG grid region
 */
function renderDecisionSupportACMGGrid(container) {
  // get the canvas area
  var c = d3.select(container);

  // create an control for the grid
  var svgContainer = c.append("svg").attr("width", "100%").attr("height", "100%");

  // list of the colors
  var colors = [
      "black", "purple", "blue", "green", "orange", "salmon", "red"
  ];

  // list of the row headers. nothing in (N, 0)
  var rowText = [
      "", "Population data", "Computational data", "Functional data", "Segregation data", "De novo data", "Allelic data", "Other database",
      "Other data"
  ];

  // list of the column headers. nothing in the (0, N)
  var colText = [
      "Gene: PRSS1", "Known benign", "Likely benign", "VUS", "Possibly pathenogenic ", "Likely pathenogenic", "Known pathogenic"
  ];

  var cellText = [
      [
          "", "", "", "", "", "", ""
      ],

      // row 1
      [
          "", "Disease/Gene Inheritance", "", "", "1000G, ESP", "1000G, ESP, clinical", ""
      ],

      // row 2
      [
          "", "", "Polyphen, CADD,etc", "", "Local sequence", "", ""
      ],

      // row 3
      [
          "", "Literature", "Canvas, refseq", "", "Canvas, refseq", "", ""
      ],

      // row 4
      [
          "", "Clinical", "", "Clinical", "", "", ""
      ],

      // row 5
      [
          "", "", "", "", "Clinical", "Clinical", ""
      ],

      // row 6
      [
          "", "", "OMIM, Text, ClinVar, HGMD", "", "OMIM, Text, ClinVar, HGMD", "", ""
      ],

      // row 7
      [
          "", "DB", "DB", "", "", "", ""
      ],

      // row 8
      [
          "", "", "Clinical", "Clinical", "", "", ""
      ],
  ];

  // padding to start
  var x = 2;
  var y = 10;

  // the height/width of each row/column
  var width = 170;
  var height = 90;

  // for each ACMG row
  for (var row = 0; row < 9; row++) {
    // create a group for the box and the text
    var grp = svgContainer.append("g");

    // for each ACMG columnn
    for (var col = 0; col < 7; col++) {
      // create a rectangle
      var rectangle = grp.append("rect").attr("x", x).attr("y", y).attr("width", width).attr("height", height).attr("fill", "white").attr(
          "stroke-width", 1).attr("fill", "white");

      // after the first col/row gets a colored rectangle
      if (col > 0 && row > 0)
        rectangle.attr("stroke", colors[col]);

      // create text control
      var textArea = grp.append("text").attr("x", x + 5).attr("y", y + 20).attr("fill", colors[col]);

      // first row only gets column headers
      if (row == 0)
        textArea.attr("font-size", "14").text(colText[col]);
      // first column after the first row gets evidence categories
      else if (col == 0)
        textArea.attr("font-size", "12").attr("font-style", "italic").text(rowText[row]);
      // render the cell text
      else
        textArea.attr("font-size", "12").text(cellText[row][col]);

      // first column is a little narrower
      if (col == 0)
        x += 120;
      else
        x += width + 3;
    }

    // first row is a little narrower
    if (row == 0)
      y = 40;
    else
      y += height + 3;

    // reset the starting row x-postion
    x = 2;
  }
}

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
