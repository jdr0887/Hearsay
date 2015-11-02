/**
 * does the evaluation for PVS1
 * 
 */
function evalPVS1(i, data) {
  // do we have evidence
  if (data[EVIDENCE_CLASS.Variant_Class] == undefined || data[EVIDENCE_CLASS.Variant_Class]["Value"] == null) {
    // set the message viewable for the criteria
    ACMGCriteriaInfo[i].setViewMsg("Need variant class information.");

    // rule has been satisfied
    ACMGCriteriaInfo[i].setStatus(CRITERIA_STATUS.ERROR);

    // set the evaluation status
    ACMGCriteriaInfo[i].setCriteriaEvaluation(CRITERIA_EVALUATION_STATUS.UNKNOWN);

    // set the pathogenicity status
    ACMGCriteriaInfo[i].setEvidenceOfPathogenicity(EVIDENCE_OF_PATHOGENICITY.UNKNOWN);
  }
  // can we use this evidence
  else if (data[EVIDENCE_CLASS.Variant_Class]["UseEvidence"] != 1 && data[EVIDENCE_CLASS.Variant_Class]["Required"] == 'Yes') {
    // set the error message
    ACMGCriteriaInfo[i].setViewMsg("Required evidence declared unusable or not selected.");

    // rule has been satisfied
    ACMGCriteriaInfo[i].setStatus(CRITERIA_STATUS.ERROR);

    // set the evaluation status
    ACMGCriteriaInfo[i].setCriteriaEvaluation(CRITERIA_EVALUATION_STATUS.UNKNOWN);

    // set the pathogenicity status
    ACMGCriteriaInfo[i].setEvidenceOfPathogenicity(EVIDENCE_OF_PATHOGENICITY.UNKNOWN);
  }
  // is this a null variant. if so it needs the LOF value
  else if (data[EVIDENCE_CLASS.Variant_Class]["Value"] == "nonsense"
      || data[EVIDENCE_CLASS.Variant_Class]["Value"] == "frameshifting indel"
      || data[EVIDENCE_CLASS.Variant_Class]["Value"] == "splice-site") {
    // has anyone reported the LOF on this gene
    if (data[EVIDENCE_CLASS.Class_of_disease_causing_variants] == undefined
        || data[EVIDENCE_CLASS.Class_of_disease_causing_variants]["Value"] == null) {
      // start the output message
      ACMGCriteriaInfo[i].setViewMsg("Need to know if disease causing LOF.");

      // rule has not been satisfied
      ACMGCriteriaInfo[i].setStatus(CRITERIA_STATUS.PARTIAL);

      // set the evaluation status
      ACMGCriteriaInfo[i].setCriteriaEvaluation(CRITERIA_EVALUATION_STATUS.UKNOWN);

      // set the pathogenicity status
      ACMGCriteriaInfo[i].setEvidenceOfPathogenicity(EVIDENCE_OF_PATHOGENICITY.UNKNOWN);
    }
    // else we need the LOF value
    else if (data[EVIDENCE_CLASS.Class_of_disease_causing_variants]["Value"] == 'true') {
      // set the message viewable for the criteria
      ACMGCriteriaInfo[i].setViewMsg("True, disease causing LOF declared.");

      // rule has been satisfied
      ACMGCriteriaInfo[i].setStatus(CRITERIA_STATUS.COMPLETE);

      // set the evaluation status
      ACMGCriteriaInfo[i].setCriteriaEvaluation(CRITERIA_EVALUATION_STATUS.TRUE);

      // set the pathogenicity status
      ACMGCriteriaInfo[i].setEvidenceOfPathogenicity(EVIDENCE_OF_PATHOGENICITY.PATHO_VERY_STRONG);
    } else {
      // set the message viewable for the criteria
      ACMGCriteriaInfo[i].setViewMsg("False, disease causing LOF not declared.");

      // rule has been satisfied
      ACMGCriteriaInfo[i].setStatus(CRITERIA_STATUS.COMPLETE);

      // set the evaluation status
      ACMGCriteriaInfo[i].setCriteriaEvaluation(CRITERIA_EVALUATION_STATUS.FALSE);

      // set the pathogenicity status
      ACMGCriteriaInfo[i].setEvidenceOfPathogenicity(EVIDENCE_OF_PATHOGENICITY.UNKNOWN);
    }
  } else {
    // start the output message
    ACMGCriteriaInfo[i].setViewMsg("False, not a null variant.");

    // rule has been satisfied
    ACMGCriteriaInfo[i].setStatus(CRITERIA_STATUS.COMPLETE);

    // set the evaluation status
    ACMGCriteriaInfo[i].setCriteriaEvaluation(CRITERIA_EVALUATION_STATUS.FALSE);

    // set the pathogenicity status
    ACMGCriteriaInfo[i].setEvidenceOfPathogenicity(EVIDENCE_OF_PATHOGENICITY.UNKNOWN);
  }
}

/**
 * does the evaluation for PS1
 * 
 */
function evalPS1(i, data) {
  // do we have evidence
  if (data[EVIDENCE_CLASS.Variant_Class] == undefined || data[EVIDENCE_CLASS.Variant_Class]["Value"] == null) {
    // set the message viewable for the criteria
    ACMGCriteriaInfo[i].setViewMsg("Need variant class information.");

    // rule has been satisfied
    ACMGCriteriaInfo[i].setStatus(CRITERIA_STATUS.ERROR);

    // set the evaluation status
    ACMGCriteriaInfo[i].setCriteriaEvaluation(CRITERIA_EVALUATION_STATUS.UNKNOWN);

    // set the pathogenicity status
    ACMGCriteriaInfo[i].setEvidenceOfPathogenicity(EVIDENCE_OF_PATHOGENICITY.UNKNOWN);
  }
  // can we use this evidence
  else if (data[EVIDENCE_CLASS.Variant_Class]["UseEvidence"] != 1 && data[EVIDENCE_CLASS.Variant_Class]["Required"] == 'Yes') {
    // set the error message
    ACMGCriteriaInfo[i].setViewMsg("Required evidence declared unusable or not selected.");

    // rule has been satisfied
    ACMGCriteriaInfo[i].setStatus(CRITERIA_STATUS.ERROR);

    // set the evaluation status
    ACMGCriteriaInfo[i].setCriteriaEvaluation(CRITERIA_EVALUATION_STATUS.UNKNOWN);

    // set the pathogenicity status
    ACMGCriteriaInfo[i].setEvidenceOfPathogenicity(EVIDENCE_OF_PATHOGENICITY.UNKNOWN);
  }
  // is this a null variant. if so it needs the LOF value
  else if (data[EVIDENCE_CLASS.Variant_Class]["Value"] == "nonsense"
      || data[EVIDENCE_CLASS.Variant_Class]["Value"] == "frameshifting indel"
      || data[EVIDENCE_CLASS.Variant_Class]["Value"] == "splice-site") {
    // has anyone reported the LOF on this gene
    if (data[EVIDENCE_CLASS.Known_pathogenic_variants] == undefined || data[EVIDENCE_CLASS.Known_pathogenic_variants]["Value"] == null) {
      // start the output message
      ACMGCriteriaInfo[i].setViewMsg("Need to know if known pathogenic variant.");

      // rule has not been satisfied
      ACMGCriteriaInfo[i].setStatus(CRITERIA_STATUS.PARTIAL);

      // set the evaluation status
      ACMGCriteriaInfo[i].setCriteriaEvaluation(CRITERIA_EVALUATION_STATUS.UKNOWN);

      // set the pathogenicity status
      ACMGCriteriaInfo[i].setEvidenceOfPathogenicity(EVIDENCE_OF_PATHOGENICITY.UNKNOWN);
    }
    // else we need the LOF value
    else if (data[EVIDENCE_CLASS.Known_pathogenic_variants]["Value"] == 'true') {
      // set the message viewable for the criteria
      ACMGCriteriaInfo[i].setViewMsg("True, known pathogenic variant.");

      // rule has been satisfied
      ACMGCriteriaInfo[i].setStatus(CRITERIA_STATUS.COMPLETE);

      // set the evaluation status
      ACMGCriteriaInfo[i].setCriteriaEvaluation(CRITERIA_EVALUATION_STATUS.TRUE);

      // set the pathogenicity status
      ACMGCriteriaInfo[i].setEvidenceOfPathogenicity(EVIDENCE_OF_PATHOGENICITY.PATHO_STRONG);
    } else {
      // set the message viewable for the criteria
      ACMGCriteriaInfo[i].setViewMsg("False, not a pathogenic variant.");

      // rule has been satisfied
      ACMGCriteriaInfo[i].setStatus(CRITERIA_STATUS.COMPLETE);

      // set the evaluation status
      ACMGCriteriaInfo[i].setCriteriaEvaluation(CRITERIA_EVALUATION_STATUS.FALSE);

      // set the pathogenicity status
      ACMGCriteriaInfo[i].setEvidenceOfPathogenicity(EVIDENCE_OF_PATHOGENICITY.UNKNOWN);
    }
  } else {
    // start the output message
    ACMGCriteriaInfo[i].setViewMsg("False, not a null variant.");

    // rule has been satisfied
    ACMGCriteriaInfo[i].setStatus(CRITERIA_STATUS.COMPLETE);

    // set the evaluation status
    ACMGCriteriaInfo[i].setCriteriaEvaluation(CRITERIA_EVALUATION_STATUS.FALSE);

    // set the pathogenicity status
    ACMGCriteriaInfo[i].setEvidenceOfPathogenicity(EVIDENCE_OF_PATHOGENICITY.UNKNOWN);
  }
}

/**
 * does the evaluation forPS2
 */
function evalPS2(i, data) {
}

/**
 * does the evaluation for PS3
 */
function evalPS3(i, data) {
}

/**
 * does the evaluation for PS4
 * 
 */
function evalPS4(i, data) {
  // did we get some data
  if (data[EVIDENCE_CLASS.Frequency_of_variant_in_affected_samples] != undefined) {
    // do we have evidence
    if (data[EVIDENCE_CLASS.Frequency_of_variant_in_affected_samples]["Value"] == null) {
      // set the message
      ACMGCriteriaInfo[i].setViewMsg("Need frequency of variant in affected samples information.");

      // rule has been satisfied
      ACMGCriteriaInfo[i].setStatus(CRITERIA_STATUS.ERROR);

      // set the evaluation status
      ACMGCriteriaInfo[i].setCriteriaEvaluation(CRITERIA_EVALUATION_STATUS.UNKNOWN);

      // set the pathogenicity status
      ACMGCriteriaInfo[i].setEvidenceOfPathogenicity(EVIDENCE_OF_PATHOGENICITY.UNKNOWN);
    }
    // can we use this evidence
    else if (data[EVIDENCE_CLASS.Frequency_of_variant_in_affected_samples]["UseEvidence"] != 1
        && data[EVIDENCE_CLASS.Frequency_of_variant_in_affected_samples]["Required"] == 'Yes') {
      // set the message
      ACMGCriteriaInfo[i].setViewMsg("Required evidence declared unusable or not selected.");

      // rule has been satisfied
      ACMGCriteriaInfo[i].setStatus(CRITERIA_STATUS.ERROR);

      // set the evaluation status
      ACMGCriteriaInfo[i].setCriteriaEvaluation(CRITERIA_EVALUATION_STATUS.UNKNOWN);

      // set the pathogenicity status
      ACMGCriteriaInfo[i].setEvidenceOfPathogenicity(EVIDENCE_OF_PATHOGENICITY.UNKNOWN);
    }
    // did we get a valid value
    else {
      // pass/fail rule check
      if (parseFloat(data[EVIDENCE_CLASS.Frequency_of_variant_in_affected_samples]["Value"]) <= .05) {
        // set the evaluation status
        ACMGCriteriaInfo[i].setViewMsg("True, Allele freq <= 5%.");

        // set the evaluation status
        ACMGCriteriaInfo[i].setCriteriaEvaluation(CRITERIA_EVALUATION_STATUS.TRUE);

        // set the pathogenicity status
        ACMGCriteriaInfo[i].setEvidenceOfPathogenicity(EVIDENCE_OF_PATHOGENICITY.PATHO_STRONG);
      } else {
        // set the evaluation status
        ACMGCriteriaInfo[i].setViewMsg("False, Allele freq > 5%.");

        // set the evaluation status
        ACMGCriteriaInfo[i].setCriteriaEvaluation(CRITERIA_EVALUATION_STATUS.FALSE);

        // set the pathogenicity status
        ACMGCriteriaInfo[i].setEvidenceOfPathogenicity(EVIDENCE_OF_PATHOGENICITY.UNKNOWN);
      }

      // rule has been satisfied
      ACMGCriteriaInfo[i].setStatus(CRITERIA_STATUS.COMPLETE);
    }
  }
}

/**
 * does the evaluation for PM1
 */
function evalPM1(i, data) {
}

/**
 * does the evaluation for PM2
 * 
 */
function evalPM2(i, data) {
  // did we get some data
  if (data[EVIDENCE_CLASS.Population_frequency_of_variant] != undefined) {
    // do we have evidence
    if (data[EVIDENCE_CLASS.Population_frequency_of_variant]["Value"] == null) {
      // set the message
      ACMGCriteriaInfo[i].setViewMsg("Need population frequency of variant information.");

      // rule has been satisfied
      ACMGCriteriaInfo[i].setStatus(CRITERIA_STATUS.ERROR);

      // set the evaluation status
      ACMGCriteriaInfo[i].setCriteriaEvaluation(CRITERIA_EVALUATION_STATUS.UNKNOWN);

      // set the pathogenicity status
      ACMGCriteriaInfo[i].setEvidenceOfPathogenicity(EVIDENCE_OF_PATHOGENICITY.UNKNOWN);
    }
    // can we use this evidence
    else if (data[EVIDENCE_CLASS.Population_frequency_of_variant]["UseCriteria"] != 1
        && data[EVIDENCE_CLASS.Population_frequency_of_variant]["Required"] == 'Yes') {
      // set the message
      ACMGCriteriaInfo[i].setViewMsg("Required evidence declared unusable or not selected.");

      // rule has been satisfied
      ACMGCriteriaInfo[i].setStatus(CRITERIA_STATUS.ERROR);

      // set the evaluation status
      ACMGCriteriaInfo[i].setCriteriaEvaluation(CRITERIA_EVALUATION_STATUS.UNKNOWN);

      // set the pathogenicity status
      ACMGCriteriaInfo[i].setEvidenceOfPathogenicity(EVIDENCE_OF_PATHOGENICITY.UNKNOWN);
    }
    // did we get a valid value
    else {
      // pass/fail rule check
      if (parseFloat(data[EVIDENCE_CLASS.Population_frequency_of_variant]["Value"]) <= .05) {
        // set the evaluation status
        ACMGCriteriaInfo[i].setViewMsg("True, allele freq <= 5%.");

        // set the evaluation status
        ACMGCriteriaInfo[i].setCriteriaEvaluation(CRITERIA_EVALUATION_STATUS.TRUE);

        // set the pathogenicity status
        ACMGCriteriaInfo[i].setEvidenceOfPathogenicity(EVIDENCE_OF_PATHOGENICITY.PATHO_STRONG);
      } else {
        // set the evaluation status
        ACMGCriteriaInfo[i].setViewMsg("False, allele freq > 5%.");

        // set the evaluation status
        ACMGCriteriaInfo[i].setCriteriaEvaluation(CRITERIA_EVALUATION_STATUS.FALSE);

        // set the pathogenicity status
        ACMGCriteriaInfo[i].setEvidenceOfPathogenicity(EVIDENCE_OF_PATHOGENICITY.UNKNOWN);
      }

      // rule has been satisfied
      ACMGCriteriaInfo[i].setStatus(CRITERIA_STATUS.COMPLETE);
    }
  }
}

/**
 * does the evaluation for PM3
 */
function evalPM3(i, data) {
}

/**
 * does the evaluation for PM4
 */
function evalPM4(i, data) {
}

/**
 * does the evaluation for PM5
 */
function evalPM5(i, data) {
}

/**
 * does the evaluation for PM6
 */
function evalPM6(i, data) {
}

/**
 * does the evaluation for PP1
 */
function evalPP1(i, data) {
}

/**
 * does the evaluation for PP2
 */
function evalPP2(i, data) {
}

/**
 * does the evaluation for PP3
 */
function evalPP3(i, data) {
}

/**
 * does the evaluation for PP4
 */
function evalPP4(i, data) {
}

/**
 * does the evaluation for PP5
 */
function evalPP5(i, data) {
  // do we have evidence
  if (data[EVIDENCE_CLASS.Value_from_a_reputable_database] == undefined
      || data[EVIDENCE_CLASS.Value_from_a_reputable_database]["Value"] == null
      || data[EVIDENCE_CLASS.Value_from_a_reputable_database]["Value"] == '') {
    // set the message viewable for the criteria
    ACMGCriteriaInfo[i].setViewMsg("Need data from a reputable source.");

    // rule has been satisfied
    ACMGCriteriaInfo[i].setStatus(CRITERIA_STATUS.ERROR);

    // set the evaluation status
    ACMGCriteriaInfo[i].setCriteriaEvaluation(CRITERIA_EVALUATION_STATUS.UNKNOWN);

    // set the pathogenicity status
    ACMGCriteriaInfo[i].setEvidenceOfPathogenicity(EVIDENCE_OF_PATHOGENICITY.UNKNOWN);
  }
  // can we use this evidence
  else if (data[EVIDENCE_CLASS.Value_from_a_reputable_database]["UseEvidence"] != 1
      && data[EVIDENCE_CLASS.Value_from_a_reputable_database]["Required"] == 'Yes') {
    // set the error message
    ACMGCriteriaInfo[i].setViewMsg("Required evidence declared unusable or not selected.");

    // rule has been satisfied
    ACMGCriteriaInfo[i].setStatus(CRITERIA_STATUS.ERROR);

    // set the evaluation status
    ACMGCriteriaInfo[i].setCriteriaEvaluation(CRITERIA_EVALUATION_STATUS.UNKNOWN);

    // set the pathogenicity status
    ACMGCriteriaInfo[i].setEvidenceOfPathogenicity(EVIDENCE_OF_PATHOGENICITY.UNKNOWN);
  }
  // there is data from a reputable source
  else {
    // start the output message
    ACMGCriteriaInfo[i].setViewMsg("True, There is data from a reputable source.");

    // rule has been satisfied
    ACMGCriteriaInfo[i].setStatus(CRITERIA_STATUS.COMPLETE);

    // set the evaluation status
    ACMGCriteriaInfo[i].setCriteriaEvaluation(CRITERIA_EVALUATION_STATUS.TRUE);

    // set the pathogenicity status
    ACMGCriteriaInfo[i].setEvidenceOfPathogenicity(EVIDENCE_OF_PATHOGENICITY.PATHO_SUPPORTING);
  }
}

/**
 * does the evaluation for BA1
 * 
 */
function evalBA1(i, data) {
  // did we get some data
  if (data[EVIDENCE_CLASS.Population_frequency_of_variant] != undefined) {
    // do we have evidence
    if (data[EVIDENCE_CLASS.Population_frequency_of_variant]["Value"] == null) {
      // set the message
      ACMGCriteriaInfo[i].setViewMsg("Need population frequency information.");

      // rule has been satisfied
      ACMGCriteriaInfo[i].setStatus(CRITERIA_STATUS.ERROR);

      // set the evaluation status
      ACMGCriteriaInfo[i].setCriteriaEvaluation(CRITERIA_EVALUATION_STATUS.UNKNOWN);

      // set the pathogenicity status
      ACMGCriteriaInfo[i].setEvidenceOfBenignImpact(EVIDENCE_OF_PATHOGENICITY.UNKNOWN);
    }
    // can we use this evidence
    else if (data[EVIDENCE_CLASS.Population_frequency_of_variant]["UseEvidence"] != 1
        && data[EVIDENCE_CLASS.Population_frequency_of_variant]["Required"] == 'Yes') {
      // set the message
      ACMGCriteriaInfo[i].setViewMsg("Required evidence declared unusable or not selected.");

      // rule has been satisfied
      ACMGCriteriaInfo[i].setStatus(CRITERIA_STATUS.ERROR);

      // set the evaluation status
      ACMGCriteriaInfo[i].setCriteriaEvaluation(CRITERIA_EVALUATION_STATUS.UNKNOWN);

      // set the pathogenicity status
      ACMGCriteriaInfo[i].setEvidenceOfBenignImpact(EVIDENCE_OF_PATHOGENICITY.UNKNOWN);
    }
    // did we get a valid value
    else {
      // pass/fail rule check
      if (parseFloat(data[EVIDENCE_CLASS.Population_frequency_of_variant]["Value"]) >= .05) {
        // set the evaluation status
        ACMGCriteriaInfo[i].setViewMsg("True, Allele freq >= 5%");

        // set the evaluation status
        ACMGCriteriaInfo[i].setCriteriaEvaluation(CRITERIA_EVALUATION_STATUS.TRUE);

        // set the pathogenicity status
        ACMGCriteriaInfo[i].setEvidenceOfBenignImpact(EVIDENCE_OF_PATHOGENICITY.BENIGN_STAND_ALONE);
      } else {
        // set the evaluation status
        ACMGCriteriaInfo[i].setViewMsg("False, Allele freq < 5%");

        // set the evaluation status
        ACMGCriteriaInfo[i].setCriteriaEvaluation(CRITERIA_EVALUATION_STATUS.FALSE);

        // set the pathogenicity status
        ACMGCriteriaInfo[i].setEvidenceOfBenignImpact(EVIDENCE_OF_PATHOGENICITY.UNKNOWN);
      }

      // rule has been satisfied
      ACMGCriteriaInfo[i].setStatus(CRITERIA_STATUS.COMPLETE);
    }
  }
}

/**
 * does the evaluation for BS1
 */
function evalBS1(i, data) {
}

/**
 * does the evaluation for BS2
 */
function evalBS2(i, data) {
}

/**
 * does the evaluation for BS3
 */
function evalBS3(i, data) {
}

/**
 * does the evaluation for BS4
 */
function evalBS4(i, data) {
}

/**
 * does the evaluation for BP1
 */
function evalBP1(i, data) {
}

/**
 * does the evaluation for BP2
 */
function evalBP2(i, data) {
}

/**
 * does the evaluation for BP3
 */
function evalBP3(i, data) {
}

/**
 * does the evaluation for BP4
 */
function evalBP4(i, data) {
}

/**
 * does the evaluation for BP5
 */
function evalBP5(i, data) {
}

/**
 * does the evaluation for BP6
 */
function evalBP6(i, data) {
  // do we have evidence
  if (data[EVIDENCE_CLASS.Value_from_a_reputable_database] == undefined
      || data[EVIDENCE_CLASS.Value_from_a_reputable_database]["Value"] == null
      || data[EVIDENCE_CLASS.Value_from_a_reputable_database]["Value"] == '') {
    // set the message viewable for the criteria
    ACMGCriteriaInfo[i].setViewMsg("Need data from a reputable source.");

    // rule has been satisfied
    ACMGCriteriaInfo[i].setStatus(CRITERIA_STATUS.ERROR);

    // set the evaluation status
    ACMGCriteriaInfo[i].setCriteriaEvaluation(CRITERIA_EVALUATION_STATUS.UNKNOWN);

    // set the pathogenicity status
    ACMGCriteriaInfo[i].setEvidenceOfBenignImpact(EVIDENCE_OF_PATHOGENICITY.UNKNOWN);
  }
  // can we use this evidence
  else if (data[EVIDENCE_CLASS.Value_from_a_reputable_database]["UseEvidence"] != 1
      && data[EVIDENCE_CLASS.Value_from_a_reputable_database]["Required"] == 'Yes') {
    // set the error message
    ACMGCriteriaInfo[i].setViewMsg("Required evidence declared unusable or not selected.");

    // rule has been satisfied
    ACMGCriteriaInfo[i].setStatus(CRITERIA_STATUS.ERROR);

    // set the evaluation status
    ACMGCriteriaInfo[i].setCriteriaEvaluation(CRITERIA_EVALUATION_STATUS.UNKNOWN);

    // set the pathogenicity status
    ACMGCriteriaInfo[i].setEvidenceOfBenignImpact(EVIDENCE_OF_PATHOGENICITY.UNKNOWN);
  }
  // there is data from a reputable source
  else {
    // start the output message
    ACMGCriteriaInfo[i].setViewMsg("True, There is data from a reputable source.");

    // rule has been satisfied
    ACMGCriteriaInfo[i].setStatus(CRITERIA_STATUS.COMPLETE);

    // set the evaluation status
    ACMGCriteriaInfo[i].setCriteriaEvaluation(CRITERIA_EVALUATION_STATUS.TRUE);

    // set the pathogenicity status
    ACMGCriteriaInfo[i].setEvidenceOfBenignImpact(EVIDENCE_OF_PATHOGENICITY.BENIGN_SUPPORTING);
  }
}

/**
 * does the evaluation for BP7
 */
function evalBP7(i, data) {
}

/**
 * gets the text string evaluation of a criteria
 * 
 * @param dataRow
 * @returns {String}
 */
function evaluateEvidenceView(dataRow) {
  // init the return value
  var retVal = '';

  // do we have evidence
  if (dataRow["Value"] == null)
    retVal = "<span style=\"color: red;\">Not available</span>";
  // can we use this evidence
  else if (dataRow["UseEvidence"] != 1)
    retVal = "<span style=\"color: red;\">Select usable to evaluate</span>";
  // if we have a value evaluate
  else if (dataRow["UseEvidence"] == 1) {
    // are we interrogating for criteria PVS1
    if (dataRow["ACMGCriteriaID"] == ACMG_CRITERIA_TYPE.PVS1) {
      // act on the causes disease evidence class
      if (dataRow["EvidenceClassID"] == EVIDENCE_CLASS.Class_of_disease_causing_variants) {
        if (dataRow["Value"] == 'true' || dataRow["Value"] == '1')
          retVal = 'True, LOF declared.';
        else
          retVal = 'False, LOF not declared.';
      }
      // act on the variant class
      else if (dataRow["EvidenceClassID"] == EVIDENCE_CLASS.Variant_Class) {
        if (dataRow["Value"] == 'nonsense' || dataRow["Value"] == 'frameshifting indel' || dataRow["Value"] == 'splice-site')
          retVal = 'True, Null variant detected.';
        else
          retVal = 'False, Not a null variant.';
      }
    }
    // are we interrogating for criteria PS1
    else if (dataRow["ACMGCriteriaID"] == ACMG_CRITERIA_TYPE.PS1) {
      // act on the causes disease evidence class
      if (dataRow["EvidenceClassID"] == EVIDENCE_CLASS.Known_pathogenic_variants) {
        if (dataRow["Value"] == 'true' || dataRow["Value"] == '1')
          retVal = 'True, Known pathogenic variant.';
        else
          retVal = 'False, Not a known pathogenic variant.';
      }
      // act on the variant class
      else if (dataRow["EvidenceClassID"] == EVIDENCE_CLASS.Variant_Class) {
        if (dataRow["Value"] == 'nonsense' || dataRow["Value"] == 'frameshifting indel' || dataRow["Value"] == 'splice-site')
          retVal = 'True, Null variant detected.';
        else
          retVal = 'False, Not a null variant.';
      }
    }
    // are we interrogating for criteria PS4
    else if (dataRow["ACMGCriteriaID"] == ACMG_CRITERIA_TYPE.PS4) {
      // pass/fail rule check
      if (dataRow["EvidenceClassID"] == EVIDENCE_CLASS.Frequency_of_variant_in_affected_samples) {
        if (parseFloat(dataRow["Value"]) <= .05)
          retVal = 'True, Case-control freq <= 5%.';
        else
          retVal = 'False, Case-control freq > 5%.';
      } else if (dataRow["EvidenceClassID"] == EVIDENCE_CLASS.Population_frequency_of_variant) {
        if (parseFloat(dataRow["Value"]) <= .05)
          retVal = 'True, Allele freq <= 5%.';
        else
          retVal = 'False, Allele freq > 5%.';
      }
    }
    // are we interrogating for criteria PM2
    else if (dataRow["ACMGCriteriaID"] == ACMG_CRITERIA_TYPE.PM2) {
      // pass/fail rule check
      if (dataRow["EvidenceClassID"] == EVIDENCE_CLASS.Population_frequency_of_variant) {
        if (parseFloat(dataRow["Value"]) <= .05)
          retVal = 'True, Allele freq <= 5%.';
        else
          retVal = 'False, Allele freq > 5%.';
      }
    }
    // are we interrogating for criteria BA1
    else if (dataRow["ACMGCriteriaID"] == ACMG_CRITERIA_TYPE.BA1) {
      // pass/fail rule check
      if (dataRow["EvidenceClassID"] == EVIDENCE_CLASS.Population_frequency_of_variant) {
        if (parseFloat(dataRow["Value"]) >= .05)
          retVal = 'True, Allele freq >= 5%.';
        else
          retVal = 'False, Allele freq < 5%.';
      }
    }
  }

  // return the result
  return retVal;
}
