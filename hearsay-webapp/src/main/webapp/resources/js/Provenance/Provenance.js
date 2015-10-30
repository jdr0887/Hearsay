/**
 * renders the provenance region
 */
function renderProvenance(donorCode, container)
{
	// get the canvas area
	var c = d3.select(container);
	
	// create a svg region for the provenance area
	c.append("svg")
		.attr("id", "provenance")
//	    .attr("width", c.node().clientWidth - 50)
//	    .attr("height", c.node().clientHeight - 50);
      .attr("width", 500)
      .attr("height", 500);

	// get a reference to the newly created svg region
	var provenance = d3.select("#provenance");
			
	// throw down some text
	var textArea = provenance.append("text")
	    .attr("x", 50)
	    .attr("y", 50)
	    .attr("fill", "black")
	    .attr("font-family", "Arial Black")
	    .attr("font-size", "14")
	    .attr("font-style", "italic")
	    .text("Provenance Selection area");
}

