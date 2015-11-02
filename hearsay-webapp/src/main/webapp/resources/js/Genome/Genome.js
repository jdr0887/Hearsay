/**
 * renders the genome region
 */
function renderGenome(donorCode, container) {
  // Select the container
  var c = d3.select(container);

  // This is necessary because container.clientWidth is not correct until the
  // svg has already been added
  var width = parseInt(c.style("width"), 10);

  // Still need a border
  var border = 18;

  // Create the genome browser
  var genomeBrowser = d3.genomeBrowser().width(width - border)
  // XXX: Accessing data variable from VariantSelection.js here, which is pretty
  // ugly...
  .maxPosition(d3.max(data, function(d) {
    return d.gPos;
  }));

  // Call with the data loaded
  c.datum(data).call(genomeBrowser);

  // Return the genomeBrowser
  return genomeBrowser;
}

/**
 * Renders the currently selected gene
 */
function renderGene(container) {
  // Select the container
  var c = d3.select(container);

  // This is necessary because container.clientWidth is not correct until the
  // svg has already been added
  var width = parseInt(c.style("width"), 10);

  // Still need a border
  var border = 18;

  // Create the gene browser
  var geneBrowser = d3.geneBrowser().width(width - border);

  // Call with null data
  // XXX: Hack using patientVariants list defined in VariantSelection. Need to
  // change
  // to use dispatcher and callbacks.
  c.datum(patientVariants).call(geneBrowser);

  // Return the geneBrowser
  return geneBrowser;
}
