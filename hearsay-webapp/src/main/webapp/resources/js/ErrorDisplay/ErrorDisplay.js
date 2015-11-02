// global location for the y position of the text
var yPos = 2;

// declare minimum height
var yMin = 10;

// declare the max error level to be displayed. 0=info, 1=warnings, 2=error
var maxSeverity = 0;

/**
 * pads a string with a leading 0
 * 
 */
Number.prototype.zeropad = function(n) {
  return (new Array(n + 1).join("0") + this).slice(-n);
};

/**
 * dispatch handler for the error display
 * 
 */
function errorDisplay(errMsg, severity) {
  // get a reference to the newly created svg region
  var errorDisplayArea = d3.select("#errorDisplayArea");

  // init local variables
  var msgType = null;

  // get the text preamble and color
  if (severity === 0 && severity >= maxSeverity)
    msgType = "Info: ";
  else if (severity === 1 && severity >= maxSeverity)
    msgType = "Warning: ";
  else if (severity === 2 || severity === undefined)
    msgType = "Error: ";

  // do we have something to display
  if (errMsg !== undefined) {
    // get the current date
    var now = new Date();

    // compile the final message with date/time
    var msg = now.getFullYear() + "-" + (now.getMonth() + 1).zeropad(2) + "-" + now.getDate().zeropad(2) + " " + now.getHours().zeropad(2)
        + ":" + now.getMinutes().zeropad(2) + ":" + now.getSeconds().zeropad(2) + "." + now.getMilliseconds().zeropad(3) + " " + msgType
        + errMsg;

    console.log(msg);
  }
}
