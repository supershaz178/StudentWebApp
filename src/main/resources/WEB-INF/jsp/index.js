$(document).ready(fuction(){
	var allStudentsList = 
	$.each(JSON.parse(allStudentsList), function(index, obj)){
		alert(index + ":" + obj.externalStudentId); 
	}
}); 