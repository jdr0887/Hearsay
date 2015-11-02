// init the criteria counters
var CriteriaSatisfied = 0;
var CriteriaIncomplete = 0;

// storage for the criteria info
var ACMGCriteriaInfo = [];

/**
 * object to store and persist the classification results
 * 
 */
function ACMG_Classification(inVariantID, DonorCode) {
  // save the donor code
  this.DONOR_CODE = DonorCode;

  // save the variant ID
  this.VariantID = inVariantID;

  // declare the pathogenic counters
  this.cntPathVeryStrong = 0;
  this.cntPathStrong = 0;
  this.cntPathModerate = 0;
  this.cntPathSupporting = 0;

  // declare the benign counters
  this.cntBenStandAlone = 0;
  this.cntBenStrong = 0;
  this.cntBenSupporting = 0;

  // declare final classification by type
  this.FinalPathogenicClassification = VARIANT_CLASSIFICATION.UNCERTAIN;
  this.FinalBenignClassification = VARIANT_CLASSIFICATION.UNCERTAIN;

  // declare what test evaluated to true
  this.PathoEvaluatedTest = "";
  this.BenignEvaluatedTest = "";
  this.UncertainEvaluatedTest = "";

  // declare overall classification
  this.FinalClassification = VARIANT_CLASSIFICATION.UNCERTAIN;
  this.OverrideClassification = VARIANT_CLASSIFICATION.AUTO;

  this.getACMGClassification = function() {
    // init the query string
    var queryString = "VariantID=" + this.VariantID + "&DONOR_CODE=" + this.DONOR_CODE;

    // make the request to update the data
    var retVal = launchDataRequest("getACMGClassificationData", queryString);

    // did it go ok
    if (retVal["error"] != undefined) {
      // show the error
      errorDisplay("persistClassification - " + retVal["error"], 1);

      // return the error flag
      return retVal["error"];
    } else {
      // save a reference to the row data
      var rowData = retVal["data"][0];
      /*
       * // load the pathogenic counters this.cntPathVeryStrong =
       * rowData["cntPathVeryStrong"]; this.cntPathStrong =
       * rowData["cntPathStrong"]; this.cntPathModerate =
       * rowData["cntPathModerate"]; this.cntPathSupporting =
       * rowData["cntPathSupporting"]; // load the benign counters
       * this.cntBenStandAlone = rowData["cntBenStandAlone"]; this.cntBenStrong =
       * rowData["cntBenStrong"]; this.cntBenSupporting =
       * rowData["cntBenSupporting"]; // deloadclare final classification by
       * type this.FinalPathogenicClassification =
       * rowData["FinalPathogenicClassification"];
       * this.FinalBenignClassification = rowData["FinalBenignClassification"]; //
       * load what test evaluated to true this.PathoEvaluatedTest =
       * rowData["PathoEvaluatedTest"]; this.BenignEvaluatedTest =
       * rowData["BenignEvaluatedTest"]; this.UncertainEvaluatedTest =
       * rowData["UncertainEvaluatedTest"]; // load overall classification
       * this.FinalClassification = rowData["FinalClassification"];
       */
      this.OverrideClassification = rowData.overrideClassification;

      return "";
    }
  }

  /**
   * sends this info to the DB
   * 
   */
  this.persistACMGClassification = function() {
    // init the query string
    var queryString = "VariantID=" + this.VariantID + "&DONOR_CODE=" + this.DONOR_CODE + "&cntPathogenicVeryStrong="
        + this.cntPathVeryStrong + "&cntPathogenicStrong=" + this.cntPathStrong + "&cntPathogenicModerate=" + this.cntPathModerate
        + "&cntPathogenicSupporting=" + this.cntPathSupporting + "&cntBenignStandAlone=" + this.cntBenStandAlone + "&cntBenignStrong="
        + this.cntBenStrong + "&cntBenignSupporting=" + this.cntBenSupporting + "&finalPathogenicClassification="
        + this.FinalPathogenicClassification + "&finalBenignClassification=" + this.FinalBenignClassification + "&finalClassification="
        + this.FinalClassification + "&overrideClassification=" + this.OverrideClassification;

    // make the request to update the data
    var retVal = launchDataRequest("updateACMGClassificationData", queryString);

    // did it go ok
    if (retVal["error"] != undefined) {
      // show the error
      errorDisplay("persistClassification - " + retVal["error"], 1);

      // return the error flag
      return retVal["error"];
    } else
      return "";
  }
}

/**
 * ACMG rule/data association
 * 
 */
function ACMG_Criteria(status, name, type) {
  // member variables
  this.Status = status;
  this.Name = name;
  this.Type = type;
  this.ViewMsg = "";
  this.CriteriaEvaluation = CRITERIA_EVALUATION_STATUS.UNKNOWN;
  this.EvidenceOfPathogenicity = EVIDENCE_OF_PATHOGENICITY.UNKNOWN;
  this.EvidenceOfBenignImpact = EVIDENCE_OF_PATHOGENICITY.UNKNOWN;

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
  this.setViewMsg = function(val) {
    this.ViewMsg = val;
  };
  this.setCriteriaEvaluation = function(val) {
    this.CriteriaEvaluation = val;
  };
  this.setEvidenceOfPathogenicity = function(val) {
    this.EvidenceOfPathogenicity = val;
  };
  this.setEvidenceOfBenignImpact = function(val) {
    this.EvidenceOfBenignImpact = val;
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
  this.getViewMsg = function() {
    return this.ViewMsg;
  };
  this.getCriteriaEvaluation = function() {
    return this.CriteriaEvaluation;
  };
  this.getEvidenceOfPathogenicity = function() {
    return this.EvidenceOfPathogenicity;
  };
  this.getEvidenceOfBenignImpact = function() {
    return this.EvidenceOfBenignImpact;
  };
}

/**
 * Inits the ACMG criteria info storage for evaluation processing
 * 
 */
function initACMGCriteriaEvaluationInfo(variantID, DonorCode) {
  // init the array. first item is the overall classification result
  ACMGCriteriaInfo = [
    new ACMG_Classification(variantID, DonorCode)
  ];

  // reset all rules
  for ( var act in ACMG_CRITERIA_TYPE) {
    // does this value exist
    if (typeof act !== 'undefined') {
      // get the rule type
      var type = ACMG_CRITERIA_TYPE[act];

      // create all the types of evidence
      ACMGCriteriaInfo.push(new ACMG_Criteria(CRITERIA_STATUS.NO_DATA, act, type));
    }
  }

  // make the request to get all the evidence data for each criteria
  var retVal = launchDataRequest("getACMGEvidenceReviewData", "VariantID=" + variantID + "&DONOR_CODE=" + DonorCode);

  // did we get a data error
  if (retVal["error"] == undefined) {
    // set up a loop to apply data and messages to the rules
    // note the start at index 1. index 0 is for the overall classification
    // result
    for (var i = 1; i < ACMGCriteriaInfo.length; i++) {
      // get the evidence for this rule
      var data = findEvidence(retVal["data"], ACMGCriteriaInfo[i].getType());

      // did we find something
      if (data != null) {
        // is the criteria to be used
        if (data["CriteriaUsage"] != 1) {
          // start the output message
          ACMGCriteriaInfo[i].setViewMsg("Criteria declared unusable.");

          // rule has not been satisfied
          ACMGCriteriaInfo[i].setStatus(CRITERIA_STATUS.NO_DATA);

          // set the evaluation status
          ACMGCriteriaInfo[i].setCriteriaEvaluation(CRITERIA_EVALUATION_STATUS.UKNOWN);

          // set the pathogenicity status
          ACMGCriteriaInfo[i].setEvidenceOfPathogenicity(EVIDENCE_OF_PATHOGENICITY.UNKNOWN);
        }
        // is the evaluation to be forced
        else if (data["CriteriaOverride"] != null && data["CriteriaOverride"] != EVIDENCE_OF_PATHOGENICITY.AUTO) {
          // start the output message
          ACMGCriteriaInfo[i].setViewMsg("Criteria forced to: " + pathogenicityClassification(data["CriteriaOverride"]) + ".");

          // rule has not been satisfied
          ACMGCriteriaInfo[i].setStatus(CRITERIA_STATUS.COMPLETE);

          // set the evaluation status
          ACMGCriteriaInfo[i].setCriteriaEvaluation(CRITERIA_EVALUATION_STATUS.TRUE);

          // is this a benign
          if (data["CriteriaOverride"] >= EVIDENCE_OF_PATHOGENICITY.BENIGN_STAND_ALONE)
            ACMGCriteriaInfo[i].setEvidenceOfBenignImpact(data["CriteriaOverride"]);
          // set the pathogenicity status
          else
            ACMGCriteriaInfo[i].setEvidenceOfPathogenicity(data["CriteriaOverride"]);
        } else {
          // interrogate criteria based on type
          switch (ACMGCriteriaInfo[i].getType()) {
            case ACMG_CRITERIA_TYPE.PVS1:
              evalPVS1(i, data);
              break;
            case ACMG_CRITERIA_TYPE.PS1:
              evalPS1(i, data);
              break;
            case ACMG_CRITERIA_TYPE.PS2:
              evalPS2(i, data);
              break;
            case ACMG_CRITERIA_TYPE.PS3:
              evalPS3(i, data);
              break;
            case ACMG_CRITERIA_TYPE.PS4:
              evalPS4(i, data);
              break;
            case ACMG_CRITERIA_TYPE.PM1:
              evalPM1(i, data);
              break;
            case ACMG_CRITERIA_TYPE.PM2:
              evalPM2(i, data);
              break;
            case ACMG_CRITERIA_TYPE.PM3:
              evalPM3(i, data);
              break;
            case ACMG_CRITERIA_TYPE.PM4:
              evalPM4(i, data);
              break;
            case ACMG_CRITERIA_TYPE.PM5:
              evalPM5(i, data);
              break;
            case ACMG_CRITERIA_TYPE.PM6:
              evalPM6(i, data);
              break;
            case ACMG_CRITERIA_TYPE.PP1:
              evalPP1(i, data);
              break;
            case ACMG_CRITERIA_TYPE.PP2:
              evalPP2(i, data);
              break;
            case ACMG_CRITERIA_TYPE.PP3:
              evalPP3(i, data);
              break;
            case ACMG_CRITERIA_TYPE.PP4:
              evalPP4(i, data);
              break;
            case ACMG_CRITERIA_TYPE.PP5:
              evalPP5(i, data);
              break;
            case ACMG_CRITERIA_TYPE.BA1:
              evalBA1(i, data);
              break;
            case ACMG_CRITERIA_TYPE.BS1:
              evalBS1(i, data);
              break;
            case ACMG_CRITERIA_TYPE.BS2:
              evalBS2(i, data);
              break;
            case ACMG_CRITERIA_TYPE.BS3:
              evalBS3(i, data);
              break;
            case ACMG_CRITERIA_TYPE.BS4:
              evalBS4(i, data);
              break;
            case ACMG_CRITERIA_TYPE.BP1:
              evalBP1(i, data);
              break;
            case ACMG_CRITERIA_TYPE.BP2:
              evalBP2(i, data);
              break;
            case ACMG_CRITERIA_TYPE.BP3:
              evalBP3(i, data);
              break;
            case ACMG_CRITERIA_TYPE.BP4:
              evalBP4(i, data);
              break;
            case ACMG_CRITERIA_TYPE.BP5:
              evalBP5(i, data);
              break;
            case ACMG_CRITERIA_TYPE.BP6:
              evalBP6(i, data);
              break;
            case ACMG_CRITERIA_TYPE.BP7:
              evalBP7(i, data);
              break;
            default:
              break;
          }
        }
      }
    }

    // load up the patho/benign counters
    getEvidenceCounts();
  }
}

/**
 * get the evidence of pathogenicity counts
 * 
 */
function getEvidenceCounts() {
  // set up a loop to apply data and messages to the rules
  for (var i = 1; i < ACMGCriteriaInfo.length; i++) {
    // get the evidence values
    var patho = ACMGCriteriaInfo[i].getEvidenceOfPathogenicity();
    var benign = ACMGCriteriaInfo[i].getEvidenceOfBenignImpact();

    // check for pathogenic
    if (patho == EVIDENCE_OF_PATHOGENICITY.PATHO_VERY_STRONG)
      ACMGCriteriaInfo[0].cntPathVeryStrong++;
    else if (patho == EVIDENCE_OF_PATHOGENICITY.PATHO_STRONG)
      ACMGCriteriaInfo[0].cntPathStrong++;
    else if (patho == EVIDENCE_OF_PATHOGENICITY.PATHO_MODERATE)
      ACMGCriteriaInfo[0].cntPathModerate++;
    else if (patho == EVIDENCE_OF_PATHOGENICITY.PATHO_SUPPORTING)
      ACMGCriteriaInfo[0].cntPathSupporting++;

    // check for benign impact
    if (benign == EVIDENCE_OF_PATHOGENICITY.BENIGN_STAND_ALONE)
      ACMGCriteriaInfo[0].cntBenStandAlone++;
    else if (benign == EVIDENCE_OF_PATHOGENICITY.BENIGN_STRONG)
      ACMGCriteriaInfo[0].cntBenStrong++;
    else if (benign == EVIDENCE_OF_PATHOGENICITY.BENIGN_SUPPORTING)
      ACMGCriteriaInfo[0].cntBenSupporting++;
  }
}

/**
 * returns text to indicate classification has been overridden
 * 
 * @returns {String}
 */
function getOverridenText() {
  if (ACMGCriteriaInfo[0].OverrideClassification != VARIANT_CLASSIFICATION.AUTO)
    return " (overridden)";
  else
    return "";
}

/**
 * returns the current override classification
 * 
 * @returns
 */
function getOverrideSequenceVariantClassification() {
  return ACMGCriteriaInfo[0].OverrideClassification;
}

/**
 * gets the final classification of the variant using ACMG rules
 * 
 */
function getSequenceVariantClassification() {
  // get the latest one for this donor/variant from the DB
  ACMGCriteriaInfo[0].getACMGClassification();

  // init the final markers
  ACMGCriteriaInfo[0].FinalClassification = VARIANT_CLASSIFICATION.UNCERTAIN;
  ACMGCriteriaInfo[0].FinalPathogenicClassification = VARIANT_CLASSIFICATION.NOT_MET;
  ACMGCriteriaInfo[0].FinalBenignClassification = VARIANT_CLASSIFICATION.NOT_MET;
  ACMGCriteriaInfo[0].PathoEvaluatedTest = "Did not meet any criteria evaluation standards";
  ACMGCriteriaInfo[0].BenignEvaluatedTest = "Did not meet any criteria evaluation standards";
  ACMGCriteriaInfo[0].UncertainEvaluatedTest = "Did not meet any criteria evaluation standards";

  // perform the pathogenic tests
  if (ACMGCriteriaInfo[0].cntPathVeryStrong == 1 && ACMGCriteriaInfo[0].cntPathStrong >= 1) {
    ACMGCriteriaInfo[0].PathoEvaluatedTest = "Count of pathogenic very strong is 1 and Count of pathogenic strong greater than or equal to 1";
    ACMGCriteriaInfo[0].FinalPathogenicClassification = VARIANT_CLASSIFICATION.PATHOGENIC;
  } else if (ACMGCriteriaInfo[0].cntPathVeryStrong == 1 && ACMGCriteriaInfo[0].cntPathModerate >= 2) {
    ACMGCriteriaInfo[0].PathoEvaluatedTest = "Count of pathogenic very strong is 1 and Count of pathogenic moderate greater than or equal to 2";
    ACMGCriteriaInfo[0].FinalPathogenicClassification = VARIANT_CLASSIFICATION.PATHOGENIC;
  } else if (ACMGCriteriaInfo[0].cntPathVeryStrong == 1 && ACMGCriteriaInfo[0].cntPathModerate == 1
      && ACMGCriteriaInfo[0].cntPathSupporting == 1) {
    ACMGCriteriaInfo[0].PathoEvaluatedTest = "Count of pathogenic strong is 1 and Count of pathogenic moderate is 1 and Count of pathogenic supporting is 1";
    ACMGCriteriaInfo[0].FinalPathogenicClassification = VARIANT_CLASSIFICATION.PATHOGENIC;
  } else if (ACMGCriteriaInfo[0].cntPathVeryStrong == 1 && ACMGCriteriaInfo[0].cntPathSupporting >= 2) {
    ACMGCriteriaInfo[0].PathoEvaluatedTest = "Count of pathogenic very strong is 1 and Count of pathogenic supporting greater than or equal to 2";
    ACMGCriteriaInfo[0].FinalPathogenicClassification = VARIANT_CLASSIFICATION.PATHOGENIC;
  } else if (ACMGCriteriaInfo[0].cntPathStrong >= 2) {
    ACMGCriteriaInfo[0].PathoEvaluatedTest = "Count of pathogenic strong greater than or equal to 2";
    ACMGCriteriaInfo[0].FinalPathogenicClassification = VARIANT_CLASSIFICATION.PATHOGENIC;
  } else if (ACMGCriteriaInfo[0].cntPathStrong == 1 && ACMGCriteriaInfo[0].cntPathModerate >= 3) {
    ACMGCriteriaInfo[0].PathoEvaluatedTest = "Count of pathogenic strong is 1 and Count of pathogenic moderate greater than or equal to 3";
    ACMGCriteriaInfo[0].FinalPathogenicClassification = VARIANT_CLASSIFICATION.PATHOGENIC;
  } else if (ACMGCriteriaInfo[0].cntPathStrong == 1 && ACMGCriteriaInfo[0].cntPathModerate == 2
      && ACMGCriteriaInfo[0].cntPathSupporting >= 2) {
    ACMGCriteriaInfo[0].PathoEvaluatedTest = "Count of pathogenic strong is 1 and Count of pathogenic moderate is 2 and Count of pathogenic supporting greater than or equal to 2";
    ACMGCriteriaInfo[0].FinalPathogenicClassification = VARIANT_CLASSIFICATION.PATHOGENIC;
  } else if (ACMGCriteriaInfo[0].cntPathStrong == 1 && ACMGCriteriaInfo[0].cntPathModerate == 1
      && ACMGCriteriaInfo[0].cntPathSupporting >= 4) {
    ACMGCriteriaInfo[0].PathoEvaluatedTest = "Count of pathogenic strong is 1 and Count of pathogenic moderate is 1 and Count of pathogenic supporting greater than or equal to 4";
    ACMGCriteriaInfo[0].FinalPathogenicClassification = VARIANT_CLASSIFICATION.PATHOGENIC;
  }
  // likely pathogenic tests
  else if (ACMGCriteriaInfo[0].cntPathVeryStrong == 1 && ACMGCriteriaInfo[0].cntPathModerate == 1) {
    ACMGCriteriaInfo[0].PathoEvaluatedTest = "Count of pathogenic strong is 1 and Count of pathogenic moderate is 1 and Count of pathogenic supporting greater than or equal to 4";
    ACMGCriteriaInfo[0].FinalPathogenicClassification = VARIANT_CLASSIFICATION.LIKELY_PATHOGENIC;
  } else if (ACMGCriteriaInfo[0].cntPathStrong == 1
      && (ACMGCriteriaInfo[0].cntPathModerate == 1 || ACMGCriteriaInfo[0].cntPathModerate == 2)) {
    ACMGCriteriaInfo[0].PathoEvaluatedTest = "Count of pathogenic strong is 1 and Count of pathogenic moderate is 1 and Count of pathogenic supporting greater than or equal to 4";
    ACMGCriteriaInfo[0].FinalPathogenicClassification = VARIANT_CLASSIFICATION.LIKELY_PATHOGENIC;
  } else if (ACMGCriteriaInfo[0].cntPathStrong == 1 && ACMGCriteriaInfo[0].cntPathSupporting >= 2) {
    ACMGCriteriaInfo[0].PathoEvaluatedTest = "Count of pathogenic strong is 1 and Count of pathogenic moderate is 1 and Count of pathogenic supporting greater than or equal to 4";
    ACMGCriteriaInfo[0].FinalPathogenicClassification = VARIANT_CLASSIFICATION.LIKELY_PATHOGENIC;
  } else if (ACMGCriteriaInfo[0].cntPathModerate >= 3) {
    ACMGCriteriaInfo[0].PathoEvaluatedTest = "Count of pathogenic strong is 1 and Count of pathogenic moderate is 1 and Count of pathogenic supporting greater than or equal to 4";
    ACMGCriteriaInfo[0].FinalPathogenicClassification = VARIANT_CLASSIFICATION.LIKELY_PATHOGENIC;
  } else if (ACMGCriteriaInfo[0].cntPathModerate == 2 && ACMGCriteriaInfo[0].cntPathSupporting >= 2) {
    ACMGCriteriaInfo[0].PathoEvaluatedTest = "Count of pathogenic strong is 1 and Count of pathogenic moderate is 1 and Count of pathogenic supporting greater than or equal to 4";
    ACMGCriteriaInfo[0].FinalPathogenicClassification = VARIANT_CLASSIFICATION.LIKELY_PATHOGENIC;
  } else if (ACMGCriteriaInfo[0].cntPathModerate == 1 && ACMGCriteriaInfo[0].cntPathSupporting >= 4) {
    ACMGCriteriaInfo[0].PathoEvaluatedTest = "Count of pathogenic strong is 1 and Count of pathogenic moderate is 1 and Count of pathogenic supporting greater than or equal to 4";
    ACMGCriteriaInfo[0].FinalPathogenicClassification = VARIANT_CLASSIFICATION.LIKELY_PATHOGENIC;
  }

  // benign tests
  if (ACMGCriteriaInfo[0].cntBenStandAlone == 1 || ACMGCriteriaInfo[0].cntBenStrong >= 2) {
    ACMGCriteriaInfo[0].BenignEvaluatedTest = "Count of benign standAlone is 1 or Count of benign strong greater than or equal to 2";
    ACMGCriteriaInfo[0].FinalBenignClassification = VARIANT_CLASSIFICATION.BENIGN;
  }
  // likely benign tests
  else if (ACMGCriteriaInfo[0].cntBenStrong == 2 && ACMGCriteriaInfo[0].cntBenSupporting == 1) {
    ACMGCriteriaInfo[0].BenignEvaluatedTest = "Count of benign strong is 2 and Count of benign supporting is 1";
    ACMGCriteriaInfo[0].FinalBenignClassification = VARIANT_CLASSIFICATION.LIKELY_BENIGN;
  } else if (ACMGCriteriaInfo[0].cntBenSupporting >= 2) {
    ACMGCriteriaInfo[0].BenignEvaluatedTest = "Count of benign supporting greater than or equal to 2";
    ACMGCriteriaInfo[0].FinalBenignClassification = VARIANT_CLASSIFICATION.LIKELY_BENIGN;
  }

  // uncertain checks
  if (ACMGCriteriaInfo[0].FinalPathogenicClassification == VARIANT_CLASSIFICATION.NOT_MET
      && ACMGCriteriaInfo[0].FinalBenignClassification == VARIANT_CLASSIFICATION.NOT_MET) {
    ACMGCriteriaInfo[0].UncertainEvaluatedTest = "Final pathogenic classification is not met and Final benign classification is not met which resulted in an uncertain final classification";
    ACMGCriteriaInfo[0].FinalClassification = VARIANT_CLASSIFICATION.UNCERTAIN;
  } else if (ACMGCriteriaInfo[0].FinalPathogenicClassification == VARIANT_CLASSIFICATION.NOT_MET
      && ACMGCriteriaInfo[0].FinalBenignClassification != VARIANT_CLASSIFICATION.NOT_MET) {
    ACMGCriteriaInfo[0].UncertainEvaluatedTest = "Final pathogenic classification is not met and Final benign classification is met which resulted in an benign final classification";
    ACMGCriteriaInfo[0].FinalClassification = ACMGCriteriaInfo[0].FinalBenignClassification;
  } else if (ACMGCriteriaInfo[0].FinalPathogenicClassification != VARIANT_CLASSIFICATION.NOT_MET
      && ACMGCriteriaInfo[0].FinalBenignClassification == VARIANT_CLASSIFICATION.NOT_MET) {
    ACMGCriteriaInfo[0].UncertainEvaluatedTest = "Final pathogenic classification is met and Final benign classification is not met which resulted in an pathogenic final classification";
    ACMGCriteriaInfo[0].FinalClassification = ACMGCriteriaInfo[0].FinalPathogenicClassification;
  }

  // scribble it to the DB
  ACMGCriteriaInfo[0].persistACMGClassification();

  // return the final result to the caller
  if (ACMGCriteriaInfo[0].OverrideClassification != VARIANT_CLASSIFICATION.AUTO)
    return ACMGCriteriaInfo[0].OverrideClassification;
  else
    return ACMGCriteriaInfo[0].FinalClassification;
}

/**
 * finds the associated evidence data with the criteria
 * 
 */
function findEvidence(evidence, ACMGCriteriaID) {
  // init the return value
  var retVal = {};

  // for each peice of evidence
  for (var i = 0; i < evidence.length; i++) {
    // save the data point
    var dataPt = evidence[i];

    // is this the one
    if (dataPt["ACMGCriteriaID"] == ACMGCriteriaID) {
      // save it if there is something there
      if (dataPt["Value"] != null) {
        // save the evidence data
        retVal[dataPt["EvidenceClassID"]] = dataPt;

        // save the criteria data
        retVal["CriteriaUsage"] = dataPt["CriteriaUsage"];
        retVal["CriteriaOverride"] = dataPt["CriteriaOverride"];
      }
    }
  }

  // return to the caller
  return retVal;
}

function drawCriteriaEvaluationAnalysisPlan() {
  // get a reference to the newly created svg region for the analysis area
  var container = d3.select("#divCriteriaEvaluationAnalysisArea");

  // get the div rendered area if it exists
  var areaExists = container.select("#divCriteriaEvaluationAnalysisPlanArea");

  // only create the eval plan area if it wasnt there previously
  if (areaExists[0][0] == null) {
    // change the arrow image
    d3.select("#CriteriaEvalPlanImg").attr("src", "../../images/sorted_up.gif");

    // remove the criteria evaluation plan area if it exists
    container.select("#divCriteriaEvaluationAnalysisPlanArea").remove();

    // start a new area for the evaluation grid
    c = container.append("div").attr("id", "divCriteriaEvaluationAnalysisPlanArea").attr("style", "margin-top: 10px");

    // define the table column names
    var colHeader = [
        [
          "Classification element"
        ], [
          "Value"
        ]
    ];

    // define the table column data
    var colData = [
        [
          "Element"
        ], [
          "Value"
        ]
    ];

    // define the data rows using the first object of the evaluation output
    var rowData = [
        {
          Element : "Count of very strong pathogenic",
          Value : ACMGCriteriaInfo[0].cntPathVeryStrong
        }, {
          Element : "Count of strong pathogenic",
          Value : ACMGCriteriaInfo[0].cntPathStrong
        }, {
          Element : "Count of moderate pathogenic",
          Value : ACMGCriteriaInfo[0].cntPathModerate
        }, {
          Element : "Count of supporting pathogenic",
          Value : ACMGCriteriaInfo[0].cntPathSupporting
        }, {
          Element : "Count of stand alone benign",
          Value : ACMGCriteriaInfo[0].cntBenStandAlone
        }, {
          Element : "Count of strong benign",
          Value : ACMGCriteriaInfo[0].cntBenStrong
        }, {
          Element : "Count of supporting benign",
          Value : ACMGCriteriaInfo[0].cntBenSupporting
        }, {
          Element : "Final pathogenic classification",
          Value : variantClassification(ACMGCriteriaInfo[0].FinalPathogenicClassification)
        }, {
          Element : "Final benign classification",
          Value : variantClassification(ACMGCriteriaInfo[0].FinalBenignClassification)
        }, {
          Element : "Winning pathogenic evaluation test",
          Value : ACMGCriteriaInfo[0].PathoEvaluatedTest
        }, {
          Element : "Winning benign evaluation test",
          Value : ACMGCriteriaInfo[0].BenignEvaluatedTest
        }, {
          Element : "Winning uncertain evaluation test",
          Value : ACMGCriteriaInfo[0].UncertainEvaluatedTest
        }, {
          Element : "Final classification",
          Value : variantClassification(ACMGCriteriaInfo[0].FinalClassification)
        }, {
          Element : "Override classification",
          Value : variantClassification(ACMGCriteriaInfo[0].OverrideClassification)
        }
    ];

    // create a table, header and body
    var table = c.append("table").attr("class", "standard");
    var thead = table.append("thead");
    var tbody = table.append("tbody");

    // populate the header
    thead.append("tr").selectAll("th").data(colHeader).enter().append("th").attr("class", "dataentry").style("text-align", "center").style(
        "font-weight", "bold").style("font-size", "16px").style("text-shadow",
        "-2px -2px 2px #111, 2px -2px 2px #111, -2px 2px 2px #111, 2px 2px 2px #111").text(function(d, i) {
      return d;
    });

    // create a row for each object in the data
    var rows = tbody.selectAll("tr").data(rowData).enter().append("tr");

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
    }).enter().append("td").style("background-color", "white").attr("width", function(d, i) {
      if (i == 0)
        return "210px";
      else
        return "800px";
    }).style("text-align", "left").text(function(d) {
      return d.value;
    });

    // start a new area for the criteria grid
    c = c.append("div").attr("style", "margin-top: 20px");

    // define the table column names
    var colHeader = [
        [
          "Criteria element"
        ], [
          "Evaluation"
        ]
    ];

    // define the table column data
    var colData = [
        [
          "Name"
        ], [
          "ViewMsg"
        ]
    ];

    // new array to skip the first item
    var newArr = ACMGCriteriaInfo.slice(1);

    // create a table, header and body
    var table = c.append("table").attr("class", "standard");
    var thead = table.append("thead");
    var tbody = table.append("tbody");

    // populate the header
    thead.append("tr").selectAll("th").data(colHeader).enter().append("th").attr("class", "dataentry").style("text-align", "center").style(
        "font-weight", "bold").style("font-size", "16px").style("text-shadow",
        "-2px -2px 2px #111, 2px -2px 2px #111, -2px 2px 2px #111, 2px 2px 2px #111").text(function(d, i) {
      return d;
    });

    // create a row for each object in the data
    var rows = tbody.selectAll("tr").data(newArr).enter().append("tr");

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
    }).enter().append("td").style("background-color", "white").attr("width", function(d, i) {
      // size the columns
      if (i == 0)
        return "150px";
      else
        return "400px";
    }).style("text-align", function(d, i) {
      // align the columns
      if (i == 1)
        return "left";
      else
        return "center";
    }).html(function(d, i) {
      // start the return with color
      var msg = "<span style=\"color: ";

      // is there anything there
      if (d.value == "")
        msg += "red\"> no evidence, not evaluated.</span>";
      else {
        // default to green
        var color = "green";

        // check status to determine color
        if (d.rowdata["Status"] == CRITERIA_STATUS.NO_DATA || d.rowdata["Status"] == CRITERIA_STATUS.ERROR)
          color = "red";
        else if (d.rowdata["Status"] == CRITERIA_STATUS.PARTIAL)
          color = "goldenrod";

        // add on the actual message
        msg += color + "\">" + d.value + "</span";
      }

      // return to the caller
      return msg;
    });
  } else {
    // change the arrow image
    d3.select("#CriteriaEvalPlanImg").attr("src", "../../images/sorted_pt_rt.gif");

    // remove the criteria evaluation plan area if it exists
    container.select("#divCriteriaEvaluationAnalysisPlanArea").remove();
  }
}
