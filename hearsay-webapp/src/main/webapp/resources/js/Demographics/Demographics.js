/**
 * renders the demographics region
 */
function renderDemographics(donorCode, container) {
  // get the canvas area
  var c = d3.select(container);

  // create a svg region for the demographics area
  c.append("svg").attr("id", "demographics").attr("width", "100%").attr("height", "100%");

  // Register callbacks
  dispatch.on("render_demographics", renderDemographicData);

  // initiate the retrieval from the DB
  dispatch.demographics_data_listener(donorCode);
}

/**
 * renders the demographic area when data is ready
 * 
 * @param dataSet
 */
function renderDemographicData(dataSet) {
  // no data then return
  if (dataSet == '')
    return;

  // did we get a data error
  if (typeof dataSet["error"] != 'undefined')
    displayError(dataSet["error"]);
  else {
    // get a reference to the newly created svg region
    var demographics = d3.select("#demographics");

    // throw down some text
    var textArea = demographics.append("text").attr("fill", "black");

    // display the donor code
    textArea.append("tspan").attr("x", 5).attr("y", 14).attr("font-size", "18").attr("font-style", "bold").text(
        "Participant: " + dataSet.DONOR_CODE);

    // display the Dx List Names
    textArea.append("tspan").attr("x", 250).attr("font-size", "15").attr("font-style", "normal").text(
        "Gender: " + dataSet.Gender + ", Diagnostic Lists: " + dataSet.DxListNames + ", Incidental Lists: " + dataSet.IncidentalListNames);
  }
}
