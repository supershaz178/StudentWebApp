$(document).ready(fuction(){
	var allStudentsList = ${allStudents};
	$.each(JSON.parse(allStudentsList), function(index, obj)){
		alert(index + ":" + obj.externalStudentId); 
	}
}); 