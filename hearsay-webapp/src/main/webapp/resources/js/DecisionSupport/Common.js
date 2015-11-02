// the username
var userName = "";

// for reverting back to the previous pulldown value
var prev_select_val;

/**
 * creates a simple pulldown with the info passed
 * 
 * @param selections
 * @param selectedIndex
 * @param id
 * @returns {String}
 */
function createAPulldown(ctrlID, type, options, selectedIndex, key, handler, rule) {
  // get the default value if not passed
  rule = (typeof rule !== 'undefined') ? rule : "false";

  // start the select control
  var retVal = "<select id=\"" + ctrlID + "\" class=\"normal\" onclick=\"prev_select_val=this.value;\" onchange=\"" + handler
      + "(this.id, '" + type + "', '" + key + "', " + rule + ");\" \>";

  // init the options with the first entry
  var opts = "<option value=\"-1\"" + ((selectedIndex == null) ? " selected" : "") + ">Select a value...</option>";

  // for each option passed
  for (var i = 0; i < options.length; i++) {
    // is this the selected value
    var selected = (i == selectedIndex) ? " selected" : "";

    // create the option
    opts += "<option value=\"" + options[i][1] + "\"" + selected + ">" + options[i][0] + "</option>";
  }

  // return to the caller
  return retVal + opts + "</select>";
}

/**
 * used to create a check box
 * 
 * @param ctrlID
 * @param type
 * @param checked
 * @param key
 * @param handler
 * @param rule
 * @returns
 */
function createACheckBox(ctrlID, type, checked, key, handler, rule) {
  // get the default value if not passed
  rule = (typeof rule !== 'undefined') ? rule : "false";

  // start the check box control
  var retVal = "<input type=\"checkbox\" id=\"" + ctrlID + "\" onchange=\"" + handler + "(this.id, '" + type + "', '" + key + "', " + rule
      + ");\" " + ((checked == 1) ? "checked=\"checked\"" : "") + "\>";

  // return to the caller
  return retVal;
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

/**
 * start of constant enums
 */

// variant classification
var VARIANT_CLASSIFICATION = {
  AUTO : 0,
  NOT_MET : 1,
  PATHOGENIC : 2,
  LIKELY_PATHOGENIC : 3,
  UNCERTAIN : 4,
  LIKELY_BENIGN : 5,
  BENIGN : 6,
};

// evidence of pathogenicity
var EVIDENCE_OF_PATHOGENICITY = {
  AUTO : 0,
  UNKNOWN : 1,
  PATHO_VERY_STRONG : 2,
  PATHO_STRONG : 3,
  PATHO_MODERATE : 4,
  PATHO_SUPPORTING : 5,
  BENIGN_STAND_ALONE : 6,
  BENIGN_STRONG : 7,
  BENIGN_SUPPORTING : 8
};

// rule determination status
var CRITERIA_EVALUATION_STATUS = {
  FALSE : 0,
  TRUE : 1,
  UNKNOWN : 2
};

// criteria collection status
var CRITERIA_STATUS = {
  COMPLETE : 0,
  PARTIAL : 1,
  NO_DATA : 2,
  ERROR : 3
};

// evidence Class types
var EVIDENCE_CLASS = {
  Population_frequency_of_variant : 1,
  Disease_frequency : 2,
  Disease_penetrance : 3,
  Disease_inheritance : 4,
  Frequency_of_variant_in_affected_samples : 5,
  Frequency_of_variant_in_controls : 6,
  Variant_Class : 7,
  Class_of_disease_causing_variants : 8,
  Known_pathogenic_variants : 9,
  Local_sequence_is_repeating : 10,
  Functional_data : 11,
  Functional_regions : 12,
  Known_benign_variants : 13,
  Mutational_hot_spots : 14,
  Functional_domains : 15,
  Family_segregation_data : 16,
  Inheritance_pattern : 17,
  Parental_genotypes : 18,
  Family_history : 19,
  Parental_confirmation : 20,
  Variants_in_same_individual : 21,
  Inheritance_Mode : 22,
  Variant_Phase : 23,
  Penetrance : 24,
  Value_from_a_reputable_database : 25,
  Genotypes_from_cases : 26,
  Positive_results_from_the_cases : 27,
  Prediction_of_gene_product_impact : 28,
  Gene_or_disease_eitiology : 29,
  Patients_detailed_phenotype : 30,
  Bioinformatics_algoritms : 31,
  Generic_gene_evidence_class : 999
};

// evidence types
var EVIDENCE_TYPE = {
  Population_frequency_of_variant : 1,
  Polyphen2 : 2,
  dbSNP_frequency : 3,
  ThousandG_frequency : 4,
  ExAC_frequency : 5,
  Disease_frequency : 6,
  Disease_penetrance : 7,
  Disease_inheritance : 8,
  Frequency_of_variant_in_affected_samples : 9,
  Frequency_of_variant_in_controls : 10,
  NCGENES_frequency : 11,
  Variant_Class : 12,
  Disease_causing_variant : 13,
  Known_pathogenic_variant : 14,
  Local_sequence_is_repeating : 15,
  Functional_data : 16,
  Functional_regions : 17,
  Known_benign_variant : 18,
  Mutational_hot_spot : 19,
  Functional_domains : 20,
  Family_segregation_data : 21,
  Inheritance_pattern : 22,
  Parental_genotypes : 23,
  Family_history : 24,
  Parental_confirmation : 25,
  Variants_in_same_individual : 26,
  Inheritance_Mode : 27,
  Variant_Phase : 28,
  Penetrance : 29,
  Value_from_a_reputable_database : 30,
  Genotypes_from_cases : 31,
  Positive_results_from_the_cases : 32,
  SIFT : 33,
  Prediction_of_gene_product_impact : 34,
  Gene_or_disease_eitiology : 35,
  Patients_detailed_phenotype : 36,
  MutTaster : 37,
  GerpRS : 38,
  Conservation : 39,
  Interp_Domain : 40
};

// ACMG Criteria Types
var ACMG_CRITERIA_TYPE = {
  PVS1 : 1,
  PS1 : 2,
  PS2 : 3,
  PS3 : 4,
  PS4 : 5,
  PM1 : 6,
  PM2 : 7,
  PM3 : 8,
  PM4 : 9,
  PM5 : 10,
  PM6 : 11,
  PP1 : 12,
  PP2 : 13,
  PP3 : 14,
  PP4 : 15,
  PP5 : 16,
  BA1 : 17,
  BS1 : 18,
  BS2 : 19,
  BS3 : 20,
  BS4 : 21,
  BP1 : 22,
  BP2 : 23,
  BP3 : 24,
  BP4 : 25,
  BP5 : 26,
  BP6 : 27,
  BP7 : 28
};

/**
 * converts the variant class enum to the string representation
 * 
 */
function variantClassification(inVal) {
  // init the return
  var retVal = "";

  // on the type
  switch (inVal) {
    case VARIANT_CLASSIFICATION.NOT_MET:
      retVal = "Not met";
      break;
    case VARIANT_CLASSIFICATION.PATHOGENIC:
      retVal = "Pathogenic";
      break;
    case VARIANT_CLASSIFICATION.LIKELY_PATHOGENIC:
      retVal = "Likely Pathogenic";
      break;
    case VARIANT_CLASSIFICATION.UNCERTAIN:
      retVal = "Uncertain";
      break;
    case VARIANT_CLASSIFICATION.LIKELY_BENIGN:
      retVal = "Likely benign";
      break;
    case VARIANT_CLASSIFICATION.BENIGN:
      retVal = "Likely benign";
      break;
    case VARIANT_CLASSIFICATION.UNKNOWN:
      retVal = "Automatic";
      break;
    case VARIANT_CLASSIFICATION.AUTO:
      retVal = "Automatic";
      break;
    default:
      retVal = "Error: defaulted uncertain";
      break;
  }

  // return to the caller
  return retVal;
}

/**
 * converts the evidence of pathogenicity enum to a string representation
 * 
 */
function pathogenicityClassification(inVal) {
  // init the return
  var retVal = "";

  // on the type
  switch (inVal) {
    case EVIDENCE_OF_PATHOGENICITY.PATHO_VERY_STRONG:
      retVal = "Very strong pathogenic";
      break;
    case EVIDENCE_OF_PATHOGENICITY.PATHO_STRONG:
      retVal = "Strong pathogenic";
      break;
    case EVIDENCE_OF_PATHOGENICITY.PATHO_MODERATE:
      retVal = "Moderate pathogenic";
      break;
    case EVIDENCE_OF_PATHOGENICITY.PATHO_SUPPORTING:
      retVal = "Supporting pathogenic";
      break;
    case EVIDENCE_OF_PATHOGENICITY.STAND_ALONE:
      retVal = "Stand alone benign";
      break;
    case EVIDENCE_OF_PATHOGENICITY.BENIGN_STRONG:
      retVal = "Strong benign";
      break;
    case EVIDENCE_OF_PATHOGENICITY.BENIGN_SUPPORTING:
      retVal = "Ssupporting benign";
      break;
    case EVIDENCE_OF_PATHOGENICITY.UNKNOWN:
      retVal = "Unknown";
      break;
    case EVIDENCE_OF_PATHOGENICITY.AUTO:
      retVal = "Automatic";
      break;
    default:
      retVal = "Error: defaulted unknown";
      break;
  }

  // return to the caller
  return retVal;
}
