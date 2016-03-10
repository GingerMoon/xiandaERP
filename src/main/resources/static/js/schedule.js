var model_table;

function receiveData(data) {
	model_table = data.Records;
	
	pageTotalCount = data.TotalRecordCount == 0 ? 0 : (Math.floor(data.TotalRecordCount/11)+1);
	StartIndex = data.TotalRecordCount == 0 ? 0 : data.StartIndex+1;
	$("#element-selector-page")[0].max = pageTotalCount;
	$("#element-selector-page")[0].value = (Math.floor(data.StartIndex/11)+1);;
	$("#currentPageIndex")[0].textContent= "显示第" + StartIndex + "页，总共有" + pageTotalCount + "页。";
	$("#currentPageIndex1")[0].textContent= "显示第";
	$("#currentPageIndex2")[0].textContent= "页，总共有" + pageTotalCount + "页。";

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
		$("#form-modify-element")[0].reset();
		$("#modify-element-id")[0].value = model_table[$(this).parent().parent().attr("rowId")].id;
		$("#modify-element-date")[0].value = model_table[$(this).parent().parent().attr("rowId")].date;
		$("#modify-element-projectId")[0].value = model_table[$(this).parent().parent().attr("rowId")].project.id;
		$("#modify-element-projectCustomerName")[0].value = model_table[$(this).parent().parent().attr("rowId")].project.customer.name;
		$("#modify-element-projectName")[0].value = model_table[$(this).parent().parent().attr("rowId")].project.name;
		$("#modify-element-volumn")[0].value = model_table[$(this).parent().parent().attr("rowId")].volumn;
		$("#modify-element-truckId")[0].value = model_table[$(this).parent().parent().attr("rowId")].truck.id;
		$("#modify-element-truckName")[0].value = model_table[$(this).parent().parent().attr("rowId")].truck.name;
		$("#modify-element-employee1Id")[0].value = model_table[$(this).parent().parent().attr("rowId")].employees[0].id;
		$("#modify-element-employee1Name")[0].value = model_table[$(this).parent().parent().attr("rowId")].employees[0].name;
		$("#modify-element-employee2Id")[0].value = model_table[$(this).parent().parent().attr("rowId")].employees[1].id;
		$("#modify-element-employee2Name")[0].value = model_table[$(this).parent().parent().attr("rowId")].employees[1].name;
		$("#modify-element-description")[0].value = model_table[$(this).parent().parent().attr("rowId")].description;
		$("#modify-element-state")[0].value = model_table[$(this).parent().parent().attr("rowId")].state;
		$("#dialog-element-modify").popup("open");
    });
    
    $(".btn-delete").on("click", function(event) {
    	$("#delete-element-id")[0].value = model_table[$(this).parent().parent().attr("rowId")].id;
		$("#delete-element-date")[0].value = model_table[$(this).parent().parent().attr("rowId")].date;
		$("#delete-element-projectId")[0].value = model_table[$(this).parent().parent().attr("rowId")].project.id;
		$("#delete-element-projectCustomerName")[0].value = model_table[$(this).parent().parent().attr("rowId")].project.customer.name;
		$("#delete-element-projectName")[0].value = model_table[$(this).parent().parent().attr("rowId")].project.name;
		$("#delete-element-volumn")[0].value = model_table[$(this).parent().parent().attr("rowId")].volumn;
		$("#delete-element-truckId")[0].value = model_table[$(this).parent().parent().attr("rowId")].truck.id;
		$("#delete-element-truckName")[0].value = model_table[$(this).parent().parent().attr("rowId")].truck.name;
		$("#delete-element-employee1Id")[0].value = model_table[$(this).parent().parent().attr("rowId")].employees[0].id;
		$("#delete-element-employee1Name")[0].value = model_table[$(this).parent().parent().attr("rowId")].employees[0].name;
		$("#delete-element-employee2Id")[0].value = model_table[$(this).parent().parent().attr("rowId")].employees[1].id;
		$("#delete-element-employee2Name")[0].value = model_table[$(this).parent().parent().attr("rowId")].employees[1].name;
		$("#delete-element-description")[0].value = model_table[$(this).parent().parent().attr("rowId")].description;
		$("#delete-element-state")[0].value = model_table[$(this).parent().parent().attr("rowId")].state;
		$("#dialog-element-delete").popup("open");
    });
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
            	html += "<li btnID='" + val.id + "' btnCustomerName='" + val.customer.name + "' btnName='" + val.name + "'>" + val.id + "-" + val.customer.name + "-" + val.name + "</li>";
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
		$(this).parent().parent().parent().find('#create-element-projectId').val($(this).attr("btnID"));   
		$(this).parent().parent().parent().find('#create-element-projectCustomerName').val($(this).attr("btnCustomerName"));   
		$(this).parent().parent().parent().find('#create-element-projectName').val($(this).attr("btnName"));   
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
		$(this).parent().parent().parent().find('#create-element-truckId').val($(this).attr("btnID"));   
		$(this).parent().parent().parent().find('#create-element-truckName').val($(this).attr("btnName"));   
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
		$(this).parent().parent().parent().find('#create-element-employee1Id').val($(this).attr("btnID"));   
		$(this).parent().parent().parent().find('#create-element-employee1Name').val($(this).attr("btnName"));   
		$('.autocomplete_searchEmployeeByName1').hide();     
    });
    
    // click to select value of auto-complete
    $( document).on( "click", ".autocomplete_searchEmployeeByName2 li", function() {
    	var selectedItem = $(this).html();
    	$(this).parent().parent().find('input').val(selectedItem);
		$(this).parent().parent().parent().find('#create-element-employee2Id').val($(this).attr("btnID"));   
		$(this).parent().parent().parent().find('#create-element-employee2Name').val($(this).attr("btnName"));   
		$('.autocomplete_searchEmployeeByName2').hide();     
    });
};

var StartIndex = 1;
var pageTotalCount = 1;
var beginDate = "";
var endDate = "";

$( document ).on( "pageinit", "#myPage", function() {
	$("#btn-filter-by-date").on( "click", function( event ) {
		beginDate = $("#search-date-begin").val();
		endDate = $("#search-date-end").val();
		$.get("/schedule/get?beginDate=" + beginDate + "&endDate=" + endDate + "&tbStartIndex=0&tbPageSize=10", null, receiveData);
	});
	
    
    $( "#element-btn-display-page" ).on( "click", function( event ) {
		var page = $("#element-selector-page").val();
		if(page > pageTotalCount) {
			page = pageTotalCount;
		}
		StartIndex = page;
		$.get("/schedule/get?beginDate=" + beginDate + "&endDate=" + endDate + "&tbStartIndex=" + (page-1) + "&tbPageSize=10", null, receiveData);
		$(this).parent().popup("close");
	});
    
    $( "#btn-create-element" ).on( "click", function( event ) {
    	$.post("/schedule/add?" + $("#form-create-element").serialize(), null, receiveData);
    	$("#form-create-element")[0].reset();
		$(this).parent().parent().parent().parent().popup("close");
	});
    
    $( "#btn-modify-element" ).on( "click", function( event ) {
    	$.post("/schedule/update?" + $('#form-modify-element').serialize(), null, receiveData);
    	$(this).parent().parent().parent().parent().popup("close");
	});
    
    $( "#btn-delete-element" ).on( "click", function( event ) {
		$.post("/schedule/remove?" + $('#form-delete-element').serialize(), null, receiveData);
		$(this).parent().parent().parent().parent().popup("close");
	});
    
    // export to a excel file
    $( "#pop-up-btn-export-excel" ).on( "click", function( event ) {
    	document.location.href='./schedule/exportExcel';	
	});
	
	$(".btn-searchTrigger").on("click", function() {
		$(".autocomplete_searchProjectByName").on("listviewbeforefilter", searchProjectByName);
		$(".autocomplete_searchTruckByName").on("listviewbeforefilter", searchTruckByName);
		$(".autocomplete_searchEmployeeByName1").on("listviewbeforefilter", searchEmployeeByName);
		$(".autocomplete_searchEmployeeByName2").on("listviewbeforefilter", searchEmployeeByName);
	});
});