function validate(numberField){
	var valid=true;
	
		console.log("TEST");
		var numberformat = /^[0-9]+$/;
		if(!numberField.value.match(numberformat))
		{
			console.log(numberField.value.match(numberformat));
			valid=false;
			alert("Wrong ISBN number! Remember - only numbers");
		}
		
	return valid;
}