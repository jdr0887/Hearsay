
// global location for the y position of the text
var yPos = 2;

// declare minimum height
var yMin = 10;

// declare the max error level to be displayed. 0=info, 1=warnings, 2=error
var maxSeverity = 0;

/**
 * renders the error display region
 */
function renderErrorDisplay(container)
{
	// get the canvas area
	var c = d3.select(container);

	// create a svg region for the error display area
	c.append("svg")
		.attr("width", "100%")
		.attr("id", "errorDisplayArea");
	
	// this seems to reset the area
	ClearErrorDisplay();
}

/**
 * pads a string with a leading 0
 *
 */
Number.prototype.zeropad = function (n) 
{
    return (new Array(n+1).join("0") + this).slice(-n);
};

/**
 * dispatch handler for the error display
 *
 */
function errorDisplay(errMsg, severity)
{
	// get a reference to the newly created svg region
	var errorDisplayArea = d3.select("#errorDisplayArea");
	
	// init local variables
	var msgColor = null;
	var msgType = null;
	
	// get the text preamble and color
	if(severity === 0 && severity >= maxSeverity)
	{
		msgColor = "green";	    
		msgType = "Info: ";
	}
	else if(severity === 1 && severity >= maxSeverity)
	{
		msgColor = "goldenrod";	    
		msgType = "Warning: ";
	}
	else if(severity === 2 || severity === undefined)
	{
		msgColor = "red";		
		msgType = "Error: ";
	}
		
	// do we have something to display
	if(errMsg !== undefined && msgColor !== null)
	{
		// get the current date
		var now = new Date();
		
		// compile the final message with date/time
		var msg = 
				now.getFullYear() + "-" + 
				(now.getMonth()+1).zeropad(2) + "-" + 
				now.getDate().zeropad(2) + " " + 
				now.getHours().zeropad(2) + ":" + 
				now.getMinutes().zeropad(2) + ":" +
				now.getSeconds().zeropad(2) + "." +
				now.getMilliseconds().zeropad(3) + " " +
				msgType +
				errMsg;
		
		// throw down some text
		var textArea = errorDisplayArea.append("text")
		    .attr("dy", ".35em")
	    	.append("tspan")
			    .attr("x", 1)
			    .attr("y", yPos)
			    .attr("font-size", "10")
			    .attr("font-style", "bold")
				.attr("fill", msgColor)
				.text(msg);
		
		// increase the height of the pane
		errorDisplayArea.attr("height", (yMin + yPos) + "px");
		
		// move down a little for the next (potential) msg
		yPos += 10;
	}
}

/**
 * Clear the error display
 *
 */
function ClearErrorDisplay()
{
	// get a reference to the newly created svg region
	var errorDisplayArea = d3.select("#errorDisplayArea");

	// remove the msg if it exists
	errorDisplayArea.selectAll("text").remove();
	
	// reset the start of the text area
	yPos = 2;
}
