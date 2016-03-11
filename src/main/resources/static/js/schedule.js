var model_table;
var StartIndex = 1;
var beginDate = "";
var endDate = "";
var currentDestination = "";

function btnSearchTriggerOnClickRegister() {
	$(".btn-searchTrigger").on("click", function() {
    	$(".autocomplete_searchProjectByName").on("listviewbeforefilter", searchProjectByName);
		$(".autocomplete_searchTruckByName").on("listviewbeforefilter", searchTruckByName);
		$(".autocomplete_searchEmployeeByName1").on("listviewbeforefilter", searchEmployeeByName);
		$(".autocomplete_searchEmployeeByName2").on("listviewbeforefilter", searchEmployeeByName);
		$(".autocomplete_searchRoute").on("listviewbeforefilter", searchRoute);
	});
}

function receiveData(data) {
	model_table = data.Records;
	if(data.Result != "ok") {
		$("#dialog-error-msg").popup("open");
		$("#error-msg").html(data.Message);
	}
		
	var pageTotalCount = data.Records.length == 0 ? 0 : (Math.floor(data.Records.length/11)+1);
	StartIndex = data.TotalRecordCount == 0 ? 0 : data.StartIndex+1;
	$("#element-selector-page").max = pageTotalCount;
	$("#element-selector-page").value = data.StartIndex + 1;
	if(pageTotalCount != 0)
		$("#msg").html("显示第" + StartIndex + "页，总共有" + pageTotalCount + "页");
	else
		$("#msg").html("没有数据,请重新查询！");

	newBody = "";
	$.each(data.Records, function(i, row) {		
		var newRow = "<tr rowId='" + i + "'>" + 
						"<td>" + row.id + "</td>" + 
						"<td>" + row.date + "</td>" + 
//						"<td>" + row.project.id + "</td>" + 
						"<td>" + row.project.customer.name + "</td>" + 
						"<td>" + row.project.name + "</td>" + 
						"<td>" + row.project.structure + "</td>" + 
						"<td>" + row.project.tongKind + "</td>" + 
//						"<td>" + row.truck.id + "</td>" + 
						"<td>" + row.truck.name + "</td>" + 
						"<td>" + row.volumn + "</td>" + 
//						"<td>" + row.employees[0].id + "</td>" + 
						"<td>" + row.employees[0].name + "-" + row.employees[0].name + "</td>" + 
//						"<td>" + row.route.id + "</td>" + 
						"<td>" + row.route.departure + "</td>" + 
						"<td>" + row.route.destination + "</td>" + 
						"<td>" + row.route.distance + "</td>" + 
						"<td>" + row.description + "</td>" +  
						"<td>" + row.state + "</td>" + 
						"<td><a class='btn-searchTrigger btn-edit ui-btn ui-icon-edit ui-btn-icon-notext ui-corner-all'>No text</a></td>" + 
						"<td><a href='#' class='btn-delete ui-btn ui-icon-delete ui-btn-icon-notext ui-corner-all'>No text</a></td>"
					 "</tr>"
		newBody = newBody + newRow;
	 });
	$("#tbProp").empty().append(newBody);
	$('#table-element').table("refresh");
	
	$(".btn-edit").on("click", function(event) {
		currentDestination = model_table[$(this).parent().parent().attr("rowId")].project.address;
		$("#form-modify-element")[0].reset();
		$("#form-modify-element").find("#form-element-id")[0].value = model_table[$(this).parent().parent().attr("rowId")].id;
		$("#form-modify-element").find("#form-element-date")[0].value = model_table[$(this).parent().parent().attr("rowId")].date;
		$("#form-modify-element").find("#form-element-projectId")[0].value = model_table[$(this).parent().parent().attr("rowId")].project.id;
		$("#form-modify-element").find("#form-element-projectCustomerName")[0].value = model_table[$(this).parent().parent().attr("rowId")].project.customer.name;
		$("#form-modify-element").find("#form-element-projectName")[0].value = model_table[$(this).parent().parent().attr("rowId")].project.name;
		$("#form-modify-element").find("#form-element-volumn")[0].value = model_table[$(this).parent().parent().attr("rowId")].volumn;
		$("#form-modify-element").find("#form-element-truckId")[0].value = model_table[$(this).parent().parent().attr("rowId")].truck.id;
		$("#form-modify-element").find("#form-element-truckName")[0].value = model_table[$(this).parent().parent().attr("rowId")].truck.name;
		$("#form-modify-element").find("#form-element-employee1Id")[0].value = model_table[$(this).parent().parent().attr("rowId")].employees[0].id;
		$("#form-modify-element").find("#form-element-employee1Name")[0].value = model_table[$(this).parent().parent().attr("rowId")].employees[0].name;
		$("#form-modify-element").find("#form-element-employee2Id")[0].value = model_table[$(this).parent().parent().attr("rowId")].employees[1].id;
		$("#form-modify-element").find("#form-element-employee2Name")[0].value = model_table[$(this).parent().parent().attr("rowId")].employees[1].name;
		$("#form-modify-element").find("#form-element-routeId")[0].value = model_table[$(this).parent().parent().attr("rowId")].route.id;
		$("#form-modify-element").find("#form-element-routeDeparture")[0].value = model_table[$(this).parent().parent().attr("rowId")].route.departure;
		$("#form-modify-element").find("#form-element-routeDestination")[0].value = model_table[$(this).parent().parent().attr("rowId")].route.destination;
		$("#form-modify-element").find("#form-element-routeDistance")[0].value = model_table[$(this).parent().parent().attr("rowId")].route.distance;
		$("#form-modify-element").find("#form-element-description")[0].value = model_table[$(this).parent().parent().attr("rowId")].description;
		$("#form-modify-element").find("#form-element-state")[0].value = model_table[$(this).parent().parent().attr("rowId")].state;
		$("#dialog-element-modify").popup("open");
    });
    
    $(".btn-delete").on("click", function(event) {
    	$("#form-delete-element").find("#form-element-id")[0].value = model_table[$(this).parent().parent().attr("rowId")].id;
		$("#form-delete-element").find("#form-element-date")[0].value = model_table[$(this).parent().parent().attr("rowId")].date;
		$("#form-delete-element").find("#form-element-projectId")[0].value = model_table[$(this).parent().parent().attr("rowId")].project.id;
		$("#form-delete-element").find("#form-element-projectCustomerName")[0].value = model_table[$(this).parent().parent().attr("rowId")].project.customer.name;
		$("#form-delete-element").find("#form-element-projectName")[0].value = model_table[$(this).parent().parent().attr("rowId")].project.name;
		$("#form-delete-element").find("#form-element-volumn")[0].value = model_table[$(this).parent().parent().attr("rowId")].volumn;
		$("#form-delete-element").find("#form-element-truckId")[0].value = model_table[$(this).parent().parent().attr("rowId")].truck.id;
		$("#form-delete-element").find("#form-element-truckName")[0].value = model_table[$(this).parent().parent().attr("rowId")].truck.name;
		$("#form-delete-element").find("#form-element-employee1Id")[0].value = model_table[$(this).parent().parent().attr("rowId")].employees[0].id;
		$("#form-delete-element").find("#form-element-employee1Name")[0].value = model_table[$(this).parent().parent().attr("rowId")].employees[0].name;
		$("#form-delete-element").find("#form-element-employee2Id")[0].value = model_table[$(this).parent().parent().attr("rowId")].employees[1].id;
		$("#form-delete-element").find("#form-element-employee2Name")[0].value = model_table[$(this).parent().parent().attr("rowId")].employees[1].name;
		$("#form-delete-element").find("#form-element-routeId")[0].value = model_table[$(this).parent().parent().attr("rowId")].route.id;
		$("#form-delete-element").find("#form-element-routeDeparture")[0].value = model_table[$(this).parent().parent().attr("rowId")].route.departure;
		$("#form-delete-element").find("#form-element-routeDestination")[0].value = model_table[$(this).parent().parent().attr("rowId")].route.destination;
		$("#form-delete-element").find("#form-element-description")[0].value = model_table[$(this).parent().parent().attr("rowId")].description;
		$("#form-delete-element").find("#form-element-state")[0].value = model_table[$(this).parent().parent().attr("rowId")].state;
		$("#dialog-element-delete").popup("open");
    });
    
    btnSearchTriggerOnClickRegister();
}

// http://view.jquerymobile.com/1.3.1/demos/widgets/autocomplete/autocomplete-remote.php
// http://itpscan.info/blog/jquery_mobile/example5.php
function searchProjectByName( e, data ) {
    var $ul = $( this ),
        $input = $( data.input ),
        value = $input.val(),
        html = "";
    $ul.html( "" );
    if ( value && value.length > 1 ) {
    	$ul.show();
        $ul.html( "<li><div class='ui-loader'><span class='ui-icon ui-icon-loading'></span></div></li>" );
        $ul.listview( "refresh" );
        $.get("/project/searchByName?name=" + $input.val(), null, function ( response ) {
            $.each( response.Records, function ( i, val ) {
            	html += "<li btnID='" + val.id + "' btnCustomerName='" + val.customer.name + "' btnName='" + val.name + "' btnAddress='" + val.address + "'>" 
            				+ val.id + "-" + val.customer.name + "-" + val.name + "-" + val.address 
            			+ "</li>";
            });
            $ul.html( html );
            $ul.listview( "refresh" );
            $ul.trigger( "updatelayout");
        });
    }
    
    // click to select value of auto-complete
    $( document).on( "click", ".autocomplete_searchProjectByName li", function() {
    	var selectedItem = $(this).html();
    	$(this).parent().parent().find('input').val(selectedItem);
		$(this).parent().parent().parent().find('#form-element-projectId').val($(this).attr("btnID"));   
		$(this).parent().parent().parent().find('#form-element-projectCustomerName').val($(this).attr("btnCustomerName"));   
		$(this).parent().parent().parent().find('#form-element-projectName').val($(this).attr("btnName"));   
		
		$(this).parent().parent().parent().find('#form-element-routeDestination').val($(this).attr("btnAddress"));  
		currentDestination = $(this).attr("btnAddress");
		$('.autocomplete_searchProjectByName').hide();     
    });
};


function searchTruckByName( e, data ) {
    var $ul = $( this ),
        $input = $( data.input ),
        value = $input.val(),
        html = "";
    $ul.html( "" );
    if ( value && value.length > 1 ) {
    	$ul.show();
        $ul.html( "<li><div class='ui-loader'><span class='ui-icon ui-icon-loading'></span></div></li>" );
        $ul.listview( "refresh" );
        $.get("/truck/searchByName?name=" + $input.val(), null, function ( response ) {
            $.each( response.Records, function ( i, val ) {
            	html += "<li btnID='" + val.id + "' btnName='" + val.name + "'>" + val.id + "-" + val.name + "</li>";
            });
            $ul.html( html );
            $ul.listview( "refresh" );
            $ul.trigger( "updatelayout");
        });
    }
    
    // click to select value of auto-complete
    $( document).on( "click", ".autocomplete_searchTruckByName li", function() {
    	var selectedItem = $(this).html();
    	$(this).parent().parent().find('input').val(selectedItem);
		$(this).parent().parent().parent().find('#form-element-truckId').val($(this).attr("btnID"));   
		$(this).parent().parent().parent().find('#form-element-truckName').val($(this).attr("btnName"));   
		$('.autocomplete_searchTruckByName').hide();     
    });
};


function searchEmployeeByName( e, data ) {
    var $ul = $( this ),
        $input = $( data.input ),
        value = $input.val(),
        html = "";
    $ul.html( "" );
    if ( value && value.length > 1 ) {
    	$ul.show();
        $ul.html( "<li><div class='ui-loader'><span class='ui-icon ui-icon-loading'></span></div></li>" );
        $ul.listview( "refresh" );
        $.get("/employee/searchByName?name=" + $input.val(), null, function ( response ) {
            $.each( response.Records, function ( i, val ) {
            	html += "<li btnID='" + val.id + "' btnName='" + val.name + "'>" + val.id + "-" + val.name + "</li>";
            });
            $ul.html( html );
            $ul.listview( "refresh" );
            $ul.trigger( "updatelayout");
        });
    }
    
    // click to select value of auto-complete
    $( document).on( "click", ".autocomplete_searchEmployeeByName1 li", function() {
    	var selectedItem = $(this).html();
    	$(this).parent().parent().find('input').val(selectedItem);
		$(this).parent().parent().parent().find('#form-element-employee1Id').val($(this).attr("btnID"));   
		$(this).parent().parent().parent().find('#form-element-employee1Name').val($(this).attr("btnName"));   
		$('.autocomplete_searchEmployeeByName1').hide();     
    });
    
    // click to select value of auto-complete
    $( document).on( "click", ".autocomplete_searchEmployeeByName2 li", function() {
    	var selectedItem = $(this).html();
    	$(this).parent().parent().find('input').val(selectedItem);
		$(this).parent().parent().parent().find('#form-element-employee2Id').val($(this).attr("btnID"));   
		$(this).parent().parent().parent().find('#form-element-employee2Name').val($(this).attr("btnName"));   
		$('.autocomplete_searchEmployeeByName2').hide();     
    });
};

function searchRoute( e, data ) {
    var $ul = $( this ),
        $input = $( data.input ),
        value = $input.val(),
        html = "";
    $ul.html( "" );
    if ( value && value.length > 1 ) {
    	$ul.show();
        $ul.html( "<li><div class='ui-loader'><span class='ui-icon ui-icon-loading'></span></div></li>" );
        $ul.listview( "refresh" );
        $.get("/route/searchByDepartureDestination?departure=" + $input.val() + "&destination=" + currentDestination, null, function ( response ) {
            $.each( response.Records, function ( i, val ) {
            	html += "<li btnID='" + val.id + "' btnDeparture='" + val.departure + "' btnDestination='" + val.destination + "' btnDistance='" + val.distance + "'>" 
            				+ val.id + "-" + val.departure + "-" + val.destination + "-" + val.distance 
            			+ "</li>";
            });
            $ul.html( html );
            $ul.listview( "refresh" );
            $ul.trigger( "updatelayout");
        });
    }
    
    // click to select value of auto-complete
    $( document).on( "click", ".autocomplete_searchRoute li", function() {
    	var selectedItem = $(this).html();
    	$(this).parent().parent().find('input').val(selectedItem);
		$(this).parent().parent().parent().find('#form-element-routeId').val($(this).attr("btnID"));   
		$(this).parent().parent().parent().find('#form-element-routeDeparture').val($(this).attr("btnDeparture"));  
		$(this).parent().parent().parent().find('#form-element-routeDestination').val($(this).attr("btnDestination"));  
		$(this).parent().parent().parent().find('#form-element-routeDistance').val($(this).attr("btnDistance"));   
		$('.autocomplete_searchRoute').hide();     
    });
};

function getData() {
	beginDate = $("#search-date-begin").val();
	endDate = $("#search-date-end").val();
	var tbStartIndex = $("#element-selector-page").val() - 1;
	$.get("/schedule/get?beginDate=" + beginDate + "&endDate=" + endDate + "&tbStartIndex=" + tbStartIndex + "&tbPageSize=10", null, receiveData);
}

$( document ).on( "pageinit", "#myPage", function() {
	$("#btn-get-data").on( "click", function( event ) {
		getData();
	});
    
    $( "#btn-create-element" ).on( "click", function( event ) {
    	$.post("/schedule/add?" + $("#form-create-element").serialize(), null, function() {getData();});
    	$("#form-create-element")[0].reset();
		$(this).parent().parent().parent().parent().popup("close");
	});
    
    $( "#btn-modify-element" ).on( "click", function( event ) {
    	$.post("/schedule/update?" + $('#form-modify-element').serialize(), null, function() {getData();});
    	$(this).parent().parent().parent().parent().popup("close");
	});
    
    $( "#btn-delete-element" ).on( "click", function( event ) {
		$.post("/schedule/remove?" + $('#form-delete-element').serialize(), null, function() {getData();});
		$(this).parent().parent().parent().parent().popup("close");
	});
    
    // export to a excel file
    $( "#pop-up-btn-export-excel" ).on( "click", function( event ) {
    	document.location.href='./schedule/exportExcel';	
	});
    
    btnSearchTriggerOnClickRegister();
});