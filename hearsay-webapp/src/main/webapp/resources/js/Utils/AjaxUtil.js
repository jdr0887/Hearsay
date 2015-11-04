/**
 * Launches a data request with params in a query string format.
 * 
 * @param $function
 * @param $queryString
 */
function launchDataRequest(functionName, queryString) {
  // declare the return value
  var retVal = "";

  try {
    // load the data using AJAX
    var httpreq = new XMLHttpRequest();

    // open a connection to the data handler
    httpreq.open("POST", "https://ncgenes.unc.edu/MolecularAnalysis/CARNAC/Utils/AjaxData.php", false);

    // set the request type
    httpreq.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

    // set the params for the data we want
    sendParams = "function=" + functionName;

    // did we get some extra params
    if (queryString != null)
      sendParams += "&" + queryString;

    // send all relevant parameters to AjaxUtil.php. See that file for more
    // details
    httpreq.send(sendParams);

    // did we get anything. length check arbitrarily set to 5 because all
    // returns should at lease start with "{" plus "data" or "error"...
    if (httpreq.responseText.length > 5 && httpreq.responseText[0] == '{') {
      retVal = JSON.parse(httpreq.responseText);
      errorDisplay("launchDataRequest() - Data gathered. Type: " + functionName + "()", 0);
    } else {
      errorDisplay("launchDataRequest() - No data. Type: " + functionName + "()", 1);
    }
  } catch (e) {
    errorDisplay("launchDataRequest() - Exception caught. Type: " + functionName + "(), msg:" + e.message);
  }

  // return the data to the caller
  return retVal;
}
