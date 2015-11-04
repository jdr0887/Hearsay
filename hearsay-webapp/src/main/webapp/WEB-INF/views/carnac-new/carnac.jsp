<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Hearsay CARNAC</title>
<meta charset="utf-8" />
<link rel="stylesheet" media="all" type="text/css" href="<c:url value="/resources/css/jquery-ui-1.8.16.custom.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/bower_components/tipsy/src/stylesheets/tipsy.css" />" />
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/dojo/1.10.2/dijit/themes/claro/claro.css" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/CANVAS.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/d3.geneBrowser.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/Master.css"/>" />

<script type="text/javascript" src="<c:url value="/resources/bower_components/d3/d3.min.js" />" charset="utf-8"></script>
<script type="text/javascript" src="<c:url value="/resources/bower_components/colorbrewer/colorbrewer.js" />" charset="utf-8"></script>
<script type="text/javascript" src="<c:url value="/resources/bower_components/jquery/dist/jquery.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/bower_components/tipsy/src/javascripts/jquery.tipsy.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/ErrorDisplay/ErrorDisplay.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/Demographics/Demographics.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/VariantSelection/VariantSelection.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/VariantSelection/d3.variantTable.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/VariantSelection/d3.variantList.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/VariantSelection/d3.variantScatterPlot.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/VariantSelection/d3.variantLegend.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/Genome/Genome.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/Genome/d3.genomeBrowser.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/Genome/d3.geneBrowser.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/DecisionSupport/Common.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/DecisionSupport/ACMGCriteriaEvaluators.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/DecisionSupport/ACMGCriteriaEngine.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/DecisionSupport/DecisionSupport5.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/Provenance/Provenance.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/Context/Context.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/Globals/DataDepot.js"/>"></script>
<script type="text/javascript">
  dojoConfig = {
    parseOnLoad : true,
    asynch : true
  };
</script>
<script type="text/javascript" src="<c:url value="/resources/bower_components/dojo/dojo.js" />" data-dojo-config="async: true"></script>
<script type="text/javascript">
  require([
      "dojo/ready", "dojo/parser", "dojo/aspect", "dijit/registry", "dijit/layout/ContentPane", "dijit/layout/BorderContainer",
      "dijit/layout/TabContainer", "dijit/layout/AccordionContainer", "dijit/layout/AccordionPane", "dijit/form/Select",
      "dijit/form/HorizontalSlider", "dijit/form/CheckBox", "dijit/form/Button",
  ],

  function(ready, parser, aspect, registry) {
    ready(function() {
      // get the donor code
      var donorCode = window.location.hash.substring(1);

      // did we get a donor code
      if (donorCode == undefined || donorCode == "") {
        // get a reference to the newly created svg region
        var demographics = d3.select("#DemographicsPane");

        // show an error
        demographics.append("div").style("color", "red").style("font-style", "bold").style("font-size", "16").text(
            "Error: Please select a participant");

        // no need to continue;
        return;
      }

      // load the demographics section
      // get a reference to the section area
      var demographicsPane = registry.byId("DemographicsPane");

      // load the demographic data
      var demographics = renderDemographics(donorCode, demographicsPane.domNode);

      // load the variant table section
      // get a reference to the section area
      var variantTablePane = registry.byId("VariantTablePane");

      // render the variant table region
      var variantTable = renderVariantTable(donorCode, variantTablePane.domNode);

      // resize callback
      aspect.after(variantTablePane, "resize", function() {
        // Having border variable here is a hack.
        var border = 18;

        variantTable.width(variantTablePane.domNode.clientWidth - border);
      });

      // load the genome section
      // get a reference to the section area
      var genomePane = registry.byId("GenomePane");

      // Set up gene browser
      var genome = renderGene(genomePane.domNode);

      // resize callback
      aspect.after(genomePane, "resize", function() {
        // Having border variable here is a hack.
        var border = 18;

        genome.width(genomePane.domNode.clientWidth - border);
      });

      // load the Decision support section
      // get a reference to the section area and tabs
      var decisionSupportACMGEvidenceTab = registry.byId("DecisionSupportACMGEvidenceTab");

      // Set up decision support tabs
      var decisionSupportACMGEvidence = renderDecisionSupportEvidence(decisionSupportACMGEvidenceTab.domNode, "Dr. Anonymous");

      // load the context section
      // get a reference to the section areas
      //var contextPaneGene = registry.byId("ContextPaneGene");

      // load the selected variant and gene context data
      //var contextGene = renderContextGene(contextPaneGene.domNode);

      // Gene browser GUI
      // register the region mapping selection event
      registry.byId("RegionMappingSelect").on("change", function(value) {
        genome.regionMapping(value);
      });

      // register the gene height slider event
      registry.byId("GeneHeightSlider").set("value", genome.geneHeight()).on("change", function(value) {
        genome.geneHeight(value);
      });

      // register the scale slider event
      registry.byId("CollapsibleScaleSlider").set("value", Math.pow(genome.collapsibleScale(), 0.2) * 100).on("change", function(value) {
        genome.collapsibleScale(Math.pow(value / 100, 5));
      });

      // register the border slider event
      registry.byId("IntronBorderSlider").set("value", genome.intronBorder()).on("change", function(value) {
        genome.intronBorder(value);
      });

      // register the variant size slider event
      registry.byId("VariantSizeSlider").set("value", genome.variantSize()).on("change", function(value) {
        genome.variantSize(value);
      });

      // register the transcript sorting event
      registry.byId("TranscriptSortSelect").on("change", function(value) {
        genome.sortBy(value);
      });

      // register the transcript height slider event
      registry.byId("TranscriptHeightSlider").set("value", genome.transcriptHeight()).on("change", function(value) {
        genome.transcriptHeight(value);
      });

      // register the transcript spacing slider event
      registry.byId("TranscriptSpacingSlider").set("value", genome.transcriptSpacing()).on("change", function(value) {
        genome.transcriptSpacing(value);
      });

      // register the transcript button event
      registry.byId("ShowAllTranscriptsButton").on("click", function() {
        genome.showAllTranscripts();
      });

      // register the feature opacity slider event
      registry.byId("FeatureOpacitySlider").set("value", genome.featureOpacity()).on("change", function(value) {
        genome.featureOpacity(value);
      });
    });
  });
</script>
</head>

<body class="claro">
  <div data-dojo-type="dijit/layout/BorderContainer" data-dojo-props="liveSplitters:true" id="OuterContainer" style="width: 100%; font-family: Tahoma">
    <div data-dojo-type="dijit/layout/BorderContainer" data-dojo-props="region:'top', splitter:true" style="height: 38px;">
      <div data-dojo-type="dijit/layout/ContentPane" data-dojo-props="region:'left'" id="DemographicsPane" style="width: 100%;"></div>
    </div>

    <div data-dojo-type="dijit/layout/ContentPane" data-dojo-props="region:'left', splitter:true, style:{width:'425px', border:'none'}">
      <div data-dojo-type="dijit/layout/TabContainer">
        <div data-dojo-type="dijit/layout/ContentPane" data-dojo-props="region:'center'" title="Variant table" id="VariantTablePane"></div>
        <!--
	      <div data-dojo-type="dijit/layout/BorderContainer" title="Variant list" data-dojo-props="">
	      <div data-dojo-type="dijit/layout/ContentPane" data-dojo-props="region:'top', splitter:true" id="VariantGUIPane" style="height: 170px;">
	      Sort by <select id="VariantSortSelect" data-dojo-type="dijit/form/Select">
	      <option value="frequency">Frequency</option>
	      <option value="computedClass">Computed class</option>
	      <option value="userClass">User class</option>
	      <option value="type">Type</option>
	      <option value="geneName">Gene name</option>
	      <option value="position">Position</option>
	      </select>
	      <div>
	      <input id="VariantFilterGeneCheckBox" data-dojo-type="dijit/form/CheckBox"/>
	      <label for="VariantFilterGeneCheckBox">Filter by gene</label>
	      </div>
	      <div id="GlyphSizeSlider" style="width:200px; margin-top:10px;" data-dojo-type="dijit/form/HorizontalSlider" data-dojo-props="minimum:5, maximum:20, intermediateChanges:true,  showButtons:false">Glyph size</div>
	      </div>
	      <div data-dojo-type="dijit/layout/ContentPane" data-dojo-props="region:'center'" id="VariantListPane" style="height: 100%;">
	      </div>
	      </div>
	      <div data-dojo-type="dijit/layout/BorderContainer" title="Variant scatter plot" data-dojo-props="">
	      <div data-dojo-type="dijit/layout/ContentPane" data-dojo-props="region:'top', splitter:true" style="height: 50px;">
	      <div id="GlyphSizeSlider2" style="width:200px; margin-top:10px;" data-dojo-type="dijit/form/HorizontalSlider" data-dojo-props="minimum:5, maximum:20, intermediateChanges:false, showButtons:false">Glyph size</div>
	      </div>
	      <div data-dojo-type="dijit/layout/ContentPane" data-dojo-props="region:'center'" id="VariantScatterPlotPane">
	      </div>
	      </div>
	  -->
      </div>
    </div>

    <div data-dojo-type="dijit/layout/BorderContainer" data-dojo-props="region:'center', style:{border:'none'}">
      <div data-dojo-type="dijit/layout/ContentPane" data-dojo-props="region:'top', splitter:true, style:{border:'none'}" style="height: 100%">
        <div data-dojo-type="dijit/layout/TabContainer" data-dojo-props="tabStrip:true">
          <div data-dojo-type="dijit/layout/BorderContainer" title="Gene browser" data-dojo-props="style:{border:'none'}">
            <div data-dojo-type="dijit/layout/ContentPane" data-dojo-props="region:'top'" style="height: 75px;">
              <div>
                <div style="width: 250px; display: inline-block">
                  Region mapping <select id="RegionMappingSelect" data-dojo-type="dijit/form/Select">
                    <option value="height">Height</option>
                    <option value="color">Color</option>
                    <option value="combo">Combo</option>
                  </select>
                </div>
                <div id="GeneHeightSlider" style="width: 200px; margin-bottom: -15px; display: inline-block" data-dojo-type="dijit/form/HorizontalSlider"
                  data-dojo-props="minimum:10, maximum:50, intermediateChanges:true, showButtons:false">Gene height</div>
                <div id="CollapsibleScaleSlider" style="width: 200px; margin-bottom: -15px; display: inline-block" data-dojo-type="dijit/form/HorizontalSlider"
                  data-dojo-props="minimum:0, maximum:100, intermediateChanges:true, showButtons:false">Intron scale</div>
                <div id="IntronBorderSlider" style="width: 200px; margin-bottom: -15px; display: inline-block" data-dojo-type="dijit/form/HorizontalSlider"
                  data-dojo-props="minimum:2, maximum:100, intermediateChanges:true, showButtons:false">Intron border</div>
                <div id="VariantSizeSlider" style="width: 200px; margin-bottom: -15px; display: inline-block" data-dojo-type="dijit/form/HorizontalSlider"
                  data-dojo-props="minimum:10, maximum:30, intermediateChanges:true, showButtons:false">Variant size</div>
              </div>
              <div style="margin-top: 10px;">
                <div style="width: 250px; display: inline-block">
                  Sort by <select id="TranscriptSortSelect" data-dojo-type="dijit/form/Select">
                    <option value="accessionNumber">Accession number</option>
                    <option value="exonLength">Exon length</option>
                  </select>
                </div>
                <div id="TranscriptHeightSlider" style="width: 200px; margin-bottom: -15px; display: inline-block" data-dojo-type="dijit/form/HorizontalSlider"
                  data-dojo-props="minimum:10, maximum:25, intermediateChanges:true, showButtons:false">Transcript height</div>
                <div id="TranscriptSpacingSlider" style="width: 200px; margin-bottom: -15px; display: inline-block" data-dojo-type="dijit/form/HorizontalSlider"
                  data-dojo-props="minimum:0, maximum:25, intermediateChanges:true, showButtons:false">Transcript spacing</div>
                <div id="FeatureOpacitySlider" style="width: 200px; margin-bottom: -15px; display: inline-block" data-dojo-type="dijit/form/HorizontalSlider"
                  data-dojo-props="minimum:0, maximum:1, intermediateChanges:true, showButtons:false">Feature opacity</div>
                <button id="ShowAllTranscriptsButton" data-dojo-type="dijit/form/Button" type="button">Show all transcripts</button>
              </div>
            </div>
            <div data-dojo-type="dijit/layout/ContentPane" data-dojo-props="region:'center'" style="height: 100%" id="GenomePane"></div>
          </div>
          <!-- div data-dojo-type="dijit/layout/ContentPane" title="Gene details" id="ContextPaneGene"></div>  -->
          <div data-dojo-type="dijit/layout/ContentPane" title="Decision support view" data-dojo-props="">
            <div data-dojo-type="dijit/layout/ContentPane" title="ACMG Supporting evidence" style="width: 99%;" id="DecisionSupportACMGEvidenceTab">
              <div style="width: 100%; margin-left: auto; margin-right: auto">
                <div data-dojo-type="dijit/layout/ContentPane" data-dojo-props="region:'top'" id="DecisionSupportEvidenceTitle"></div>
                <div data-dojo-type="dijit/layout/ContentPane" data-dojo-props="region:'middle'" style="margin-top: -10px;"
                  id="DecisionSupportGeneVariantDataEntry"></div>
                <div data-dojo-type="dijit/layout/ContentPane" data-dojo-props="region:'middle'" style="margin-top: -10px;" id="DecisionSupportCriteriaAnalysis">
                </div>
                <div data-dojo-type="dijit/layout/ContentPane" data-dojo-props="region:'middle'" style="margin-top: -10px;"
                  id="DecisionSupportCriteriaAssessment"></div>
                <div data-dojo-type="dijit/layout/ContentPane" data-dojo-props="region:'middle'" style="margin-top: -10px;"
                  id="DecisionSupportGeneCurationDataEntry"></div>
                <div data-dojo-type="dijit/layout/ContentPane" data-dojo-props="region:'middle'" style="margin-top: -10px;"
                  id="DecisionSupportMoreEvidenceDataEntry"></div>
                <div data-dojo-type="dijit/layout/ContentPane" data-dojo-props="region:'middle'" style="margin-top: -10px;"
                  id="DecisionSupportACMGCriteriaEvidence"></div>
              </div>
            </div>
          </div>
        </div>
      </div>

    </div>
  </div>

</body>
</html>
