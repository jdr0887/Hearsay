<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<title>Hearsay :: CARNAC</title>
<meta charset="utf-8" />
<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/dojo/1.10.2/dijit/themes/claro/claro.css" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/CANVAS.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/d3.geneBrowser.css" />" />

<script type="text/javascript" src="<c:url value="/resources/bower_components/d3/d3.min.js" />" charset="utf-8"></script>
<script type="text/javascript" src="<c:url value="/resources/bower_components/colorbrewer/colorbrewer.js" />" charset="utf-8"></script>
<script type="text/javascript" src="<c:url value="/resources/bower_components/jquery/dist/jquery.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/bower_components/dijit/dijit.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.tipsy.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/ErrorDisplay/ErrorDisplay.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/Demographics/Demographics.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/VariantSelection/VariantSelection.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/VariantSelection/d3.variantTable.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/VariantSelection/d3.variantList.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/VariantSelection/d3.variantScatterPlot.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/VariantSelection/d3.variantLegend.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/Genome/Genome.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/Genome/d3.genomeBrowser.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/Genome/d3.geneBrowser.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/DecisionSupport/DecisionSupport.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/Provenance/Provenance.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/Context/Context.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/Utils/AjaxUtil.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/Globals/DataDepot.js" />"></script>
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
      var donorCode = d3.select("#DonorCode").html();
      var demographicsPane = registry.byId("DemographicsPane");

      var demographics = renderDemographics(donorCode, demographicsPane.domNode);

      var variantTablePane = registry.byId("VariantTablePane");

      var variantTable = renderVariantTable(donorCode, variantTablePane.domNode);

      aspect.after(variantTablePane, "resize", function() {
        var border = 18;
        variantTable.width(variantTablePane.domNode.clientWidth - border);
      });

      var genomePane = registry.byId("GenomePane");
      var genome = renderGene(genomePane.domNode);
      aspect.after(genomePane, "resize", function() {
        var border = 18;
        genome.width(genomePane.domNode.clientWidth - border);
      });

      var decisionSupportingEvidenceTab = registry.byId("DecisionSupportingEvidenceTab");

      var userName = "asdf asdf";

      var decisionSupport = renderDecisionSupport(decisionSupportingEvidenceTab.domNode, userName);

      var contextPaneGene = registry.byId("ContextPaneGene");

      var contextGene = renderContextGene(contextPaneGene.domNode);

      registry.byId("RegionMappingSelect").on("change", function(value) {
        genome.regionMapping(value);
      });

      registry.byId("GeneHeightSlider").set("value", genome.geneHeight()).on("change", function(value) {
        genome.geneHeight(value);
      });

      registry.byId("CollapsibleScaleSlider").set("value", Math.pow(genome.collapsibleScale(), 0.2) * 100).on("change", function(value) {
        genome.collapsibleScale(Math.pow(value / 100, 5));
      });

      registry.byId("IntronBorderSlider").set("value", genome.intronBorder()).on("change", function(value) {
        genome.intronBorder(value);
      });

      registry.byId("VariantSizeSlider").set("value", genome.variantSize()).on("change", function(value) {
        genome.variantSize(value);
      });

      registry.byId("TranscriptSortSelect").on("change", function(value) {
        genome.sortBy(value);
      });

      registry.byId("TranscriptHeightSlider").set("value", genome.transcriptHeight()).on("change", function(value) {
        genome.transcriptHeight(value);
      });

      registry.byId("TranscriptSpacingSlider").set("value", genome.transcriptSpacing()).on("change", function(value) {
        genome.transcriptSpacing(value);
      });

      registry.byId("ShowAllTranscriptsButton").on("click", function() {
        genome.showAllTranscripts();
      });

      registry.byId("FeatureOpacitySlider").set("value", genome.featureOpacity()).on("change", function(value) {
        genome.featureOpacity(value);
      });
    });
  });
</script>
</head>

<body class="claro">
  <div data-dojo-type="dijit/layout/BorderContainer" data-dojo-props="liveSplitters:true" id="OuterContainer" style="width: 100%; font-family: Tahoma">
    <div data-dojo-type="dijit/layout/BorderContainer" data-dojo-props="region:'top', splitter:true" style="height: 35px;">
      <div data-dojo-type="dijit/layout/ContentPane" data-dojo-props="region:'left'" id="DemographicsPane" style="width: 100%;"></div>
    </div>

    <div data-dojo-type="dijit/layout/ContentPane" data-dojo-props="region:'left', splitter:true, style:{width:'425px', border:'none'}">
      <div data-dojo-type="dijit/layout/TabContainer">
        <div data-dojo-type="dijit/layout/ContentPane" data-dojo-props="region:'center'" title="Variant table" id="VariantTablePane"></div>
      </div>
    </div>

    <div data-dojo-type="dijit/layout/BorderContainer" data-dojo-props="region:'center', style:{border:'none'}">
      <div data-dojo-type="dijit/layout/ContentPane" data-dojo-props="region:'top', splitter:true, style:{border:'none'}" style="height: 40%">
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
          <div data-dojo-type="dijit/layout/ContentPane" title="Gene details" id="ContextPaneGene"></div>
        </div>
      </div>

      <div data-dojo-type="dijit/layout/ContentPane" data-dojo-props="region:'center', splitter:true, style:{border:'none'}" style="height: 100%">
        <div data-dojo-type="dijit/layout/ContentPane" title="Decision support view" data-dojo-props="">
          <div data-dojo-type="dijit/layout/TabContainer" data-dojo-props="tabStrip:true">
            <div data-dojo-type="dijit/layout/ContentPane" title="Supporting evidence" style="width: 99%" id="DecisionSupportingEvidenceTab">
              <div style="width: 1000px; margin-left: auto; margin-right: auto">
                <div data-dojo-type="dijit/layout/ContentPane" data-dojo-props="region:'top'" id="DecisionSupportTitle"></div>
                <div data-dojo-type="dijit/layout/ContentPane" data-dojo-props="region:'middle'">
                  <div data-dojo-type="dijit/layout/BorderContainer" data-dojo-props="style:{border:'none'}"
                    style="width: 99%; margin-bottom: -30px; margin-left: -20px; height: 150px;">
                    <div data-dojo-type="dijit/layout/ContentPane" data-dojo-props="region:'left', style:{border:'none'}"
                      style="width: 485px; margin-top: -30px" id="DecisionSupportRuleStatus"></div>
                    <div data-dojo-type="dijit/layout/ContentPane" data-dojo-props="region:'middle', style:{border:'none'}"
                      style="width: 250px; margin-top: -10px" id="DecisionSupportAnalysis"></div>
                  </div>
                </div>
                <div data-dojo-type="dijit/layout/ContentPane" data-dojo-props="region:'middle'" style="margin-top: -10px;" id="DecisionSupportRuleApplication"></div>
                <div data-dojo-type="dijit/layout/ContentPane" data-dojo-props="region:'bottom'" style="margin-top: -10px;" id="DecisionSupportDataEntry"></div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

</body>
</html>
