<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
  <head>
      <meta charset="utf-8">
       <meta name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    
      <title>Pick a slot from TimeTable</title>



<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>

      <script type="text/javascript">
      $(function(){
    	  $(".open-AddBookDialog").click(function(){
    	     $('#bookId').val($(this).data('id'));
    	     
    	   
    	     
    	    $("#addBookDialog").modal("show");
    	  });
    	});
      
      
      $( "#target" ).click(function() {
    	  alert( "Handler for .click() called." );
    	  
    	  fire_ajax_submit();
    	});
      
      
      function myFunction() {
    	 
    	  fire_ajax_submit();
    	}
      
      $('#my_modal').on('show.bs.modal', function(e) {
    	    var bookId = $(e.relatedTarget).data('book-id');
    	    $(e.currentTarget).find('input[name="bookId"]').val(bookId);
    	});
     
      
      
      function fire_ajax_submit() {

    	    var search = {}
    	    
    	    
    	    search["slotId"] = $("#bookId").val();
			search["personName"] = $("#personName").val();
			
    	    $("#btn-search").prop("disabled", true);
			
			
    	    $.ajax({
    	        type: "POST",
    	        contentType: "application/json",
    	        url: "/timeTable",
    	        data: JSON.stringify(search),
    	        dataType: 'json',
    	        cache: false,
    	        timeout: 600000,
    	        success: function (data) {

    	            var json = "<h4>Ajax Response</h4>&lt;pre&gt;"
    	                + JSON.stringify(data, null, 4) + "&lt;/pre&gt;";
    	            $('#feedback').html(json);

    	            console.log("SUCCESS : ", data);
    	            $("#btn-search").prop("disabled", false);

    	        },
    	        error: function (e) {

    	            var json = "<h4>Ajax Response</h4>&lt;pre&gt;"
    	                + e.responseText + "&lt;/pre&gt;";
    	            $('#feedback').html(json);

    	            console.log("ERROR : ", e);
    	            $("#btn-search").prop("disabled", false);

    	        }
    	    });

    	}
      
  </script>
     
  </head>

  <body>

<div id="feedback"></div>


<div id="addBookDialog" class="modal fade" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
<div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
          <h4 class="modal-title">Modal header</h4>
      </div>
      <div class="modal-body">

     <input type="hidden" name="bookId" id="bookId" value=""/>
     <p> Enter Name</p>
     <input type="text" name="personName" id="personName" value=""/>
     
      </div>
      <div class="modal-footer">
          <button type="button" onclick="myFunction()" class="btn btn-default" data-dismiss="modal">Submit</button>
    
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>


<div>
	<jsp:include page="menu.jsp" />
	     
<table border="1">

  <thead>
    <tr>
      <th scope="col">Time</th>
      <th scope="col">Name</th>
     
      <th scope="col">Booked or not</th>
    </tr>
  </thead>
  <tbody>

		<c:forEach var="listValue" items="${slotList}">
		
		<tr>
		<td>${listValue.bookedTime.date}-${listValue.bookedTime.month}-${listValue.bookedTime.year}  ${listValue.bookedTime.hours}:${listValue.bookedTime.minutes}
		
		<input type="hidden" id="slotId" name="slotId" value="${listValue.id}" />
		
		</td>
		
		
		<td>
		${listValue.bookedBy}
		</td>
		
		
	
		<td>
		<c:if test="${not listValue.isBooked}">
		
		
		<a data-id="${listValue.id}" title="Add this item" class="open-AddBookDialog">Book Slot</a>
		
		
		</c:if>
		
		<c:if test="${listValue.isBooked}">
		
		Slot Booked
		
		</c:if>
		
		</td>
		</tr>
		
		</c:forEach>
		</tbody>
	</table>
	
</div>

</body>

 

  </body>
</html>