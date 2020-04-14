/*!
 * Bootstrap v3.3.5 (http://getbootstrap.com)
 * Copyright 2011-2015 Twitter, Inc.
 * Licensed under the MIT license
 */
		document.addEventListener("DOMContentLoaded", function(event){
		console.log("abc");
		
		var sHTML = "<table class='table table-striped'><thead><tr><th scope='col'>#</th><th scope='col'>handle</th><th scope='col'>comment</th><th scope='col'>Posted</th><th scope='col'>Likes</th></tr></thead>";
		
		var comment=document.getElementById("comment").value;
		var student=document.getElementById("handle").value;
		commentBoxArray.push({student:student, handle:handle, commentTime:Date.now()});
		
		sHTML += "<tbody>";
		for(var i=0; i<commentBoxArray.length;i++)
		{
		
		sHTML+= "<tr><th <scope='row'>"+ (i+1) +"</th>";
		sHTML += "<td>" +commentBoxArray[i].handle
		+ "</td>" + "<td>" + commentBoxArray[i].comment+ " </td>"
		+"<td>" + getAge(commentBoxArray[i].commentTime)+ " </td>"
		+"<td> <button type='button' onclick='addLike()'>Like</button></td>"
		"</tr>";
		
		}
		
		sHTML+= "</tbody></table>";
		document.getElementById("sent").innerHTML=sHTML;
		document.getElementById("comment").value="";
		document.getElementById("handle").value="";
		}
		
		function  getAge (t){
		console.log(t);
		console.log(Date.now());
		
		var difference=(Date.now() - t)/1000;
		
		if (difference<60){
		return "Posted " + Math.floor(difference) + " seconds ago ";
		}
		if (difference>60 && difference<3600){
		return "Posted " + Math.floor(difference/60) + " minutes ago ";
		}
		if (difference>3600) {
		return "Posted " + Math.floor(difference/(60*60)) + " hours ago";
		}
		}


	})
	function jsFunction(){
	console.log("abc");
	
	var sHTML = "<table class='table table-striped'><thead><tr><th scope='col'>#</th><th scope='col'>handle</th><th scope='col'>comment</th><th scope='col'>Posted</th><th scope='col'>Likes</th></tr></thead>";
	
	var comment=document.getElementById("comment").value;
	var student=document.getElementById("handle").value;
	commentBoxArray.push({student:student, handle:handle, commentTime:Date.now()});
	
	sHTML += "<tbody>";
	for(var i=0; i<commentBoxArray.length;i++)
	{
	
	sHTML+= "<tr><th <scope='row'>"+ (i+1) +"</th>";
	sHTML += "<td>" +commentBoxArray[i].handle
	+ "</td>" + "<td>" + commentBoxArray[i].comment+ " </td>"
	+"<td>" + getAge(commentBoxArray[i].commentTime)+ " </td>"
	+"<td> <button type='button' onclick='addLike()'>Like</button></td>"
	"</tr>";
	
	}
	
	sHTML+= "</tbody></table>";
	document.getElementById("sent").innerHTML=sHTML;
	document.getElementById("comment").value="";
	document.getElementById("handle").value="";
	}
	
	function  getAge (t){
	console.log(t);
	console.log(Date.now());
	
	var difference=(Date.now() - t)/1000;
	
	if (difference<60){
	return "Posted " + Math.floor(difference) + " seconds ago ";
	}
	if (difference>60 && difference<3600){
	return "Posted " + Math.floor(difference/60) + " minutes ago ";
	}
	if (difference>3600) {
	return "Posted " + Math.floor(difference/(60*60)) + " hours ago";
	}
	});

