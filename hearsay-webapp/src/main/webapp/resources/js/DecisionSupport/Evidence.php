<?php

// define a class for the evidence data
class Evidence
{
	var $geneName;
	var $name;
	var $value;
	var $type;

	// setter
	function setData($_geneName, $_name, $_value, $_type)
	{
		$this->geneName = $_geneName;
		$this->name = $_name;
		$this->value = $_value;
		$this->type = $_type;
	}
}

?>
