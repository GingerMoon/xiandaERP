function receiveData(data) {
	pageTotalCount = (Math.floor(data.TotalRecordCount/11)+1);
	StartIndex = data.StartIndex+1;
	$("#element-selector-page")[0].max = pageTotalCount;
	$("#element-selector-page")[0].value = (Math.floor(data.StartIndex/11)+1);;
	$("#currentPageIndex")[0].textContent= "显示第" + StartIndex + "页，总共有" + pageTotalCount + "页。";
	$("#currentPageIndex1")[0].textContent= "显示第";
	$("#currentPageIndex2")[0].textContent= "页，总共有" + pageTotalCount + "页。";

	newBody = "";
	$.each(data.Records, function(i, row) {
		var newRow = "<tr>" + 
						"<td>" + row.id + "</td>" + 
						"<td>" + row.name + "</td>" + 
						"<td>" + row.customer.id + "</td>" + 
						"<td>" + row.customer.name + "</td>" + 
						"<td>" + row.responsiblePersonName + "</td>" + 
						"<td>" + row.responsiblePersonPhone + "</td>" + 
						"<td>" + row.address + "</td>" + 
						"<td>" + row.structure + "</td>" + 
						"<td>" + row.tongKind + "</td>" + 
						"<td>" + row.utPrice + "</td>" + 
						"<td>" + row.planVolumn + "</td>" + 
						"<td>" + row.completedVolumn + "</td>" + 
						"<td>" + row.description + "</td>" + 
						"<td>" + row.state + "</td>" + 
						"<td><a customerID='" + row.customer.id + "' class='btn-searchTrigger btn-edit ui-btn ui-icon-edit ui-btn-icon-notext ui-corner-all'>No text</a></td>" + 
						"<td><a customerID='" + row.customer.id + "'  href='#' class='btn-delete ui-btn ui-icon-delete ui-btn-icon-notext ui-corner-all'>No text</a></td>"
					 "</tr>"
		newBody = newBody + newRow;
	 });
	$("#tbProp").empty().append(newBody);
	$('#table-element').table("refresh");
	
	$(".btn-edit").on("click", function(event) {
		$("#modify-element-id")[0].value = $(this).parent().parent().children()[0].textContent;
		$("#modify-element-name")[0].value = $(this).parent().parent().children()[1].textContent;
		$("#modify-element-customerId")[0].value = $(this).attr("customerID");
		$("#modify-element-customerName")[0].value = $(this).parent().parent().children()[3].textContent;
		$("#modify-element-responsiblePersonName")[0].value = $(this).parent().parent().children()[4].textContent;
		$("#modify-element-responsiblePersonPhone")[0].value = $(this).parent().parent().children()[5].textContent;
		$("#modify-element-address")[0].value = $(this).parent().parent().children()[6].textContent;
		$("#modify-element-structure")[0].value = $(this).parent().parent().children()[7].textContent;
		$("#modify-element-tongKind")[0].value = $(this).parent().parent().children()[8].textContent;
		$("#modify-element-utPrice")[0].value = $(this).parent().parent().children()[9].textContent;
		$("#modify-element-planVolumn")[0].value = $(this).parent().parent().children()[10].textContent;
		$("#modify-element-completedVolumn")[0].value = $(this).parent().parent().children()[11].textContent;
		$("#modify-element-description")[0].value = $(this).parent().parent().children()[12].textContent;
		$("#modify-element-state")[0].value = $(this).parent().parent().children()[13].textContent;
		$("#dialog-element-modify").popup("open");
    });
    
    $(".btn-delete").on("click", function(event) {
    	$("#delete-element-id")[0].value = $(this).parent().parent().children()[0].textContent;
    	$("#delete-element-name")[0].value = $(this).parent().parent().children()[1].textContent;
    	$("#delete-element-customerId")[0].value = $(this).attr("customerID");
    	$("#delete-element-customerName")[0].value = $(this).parent().parent().children()[3].textContent;
		$("#delete-element-responsiblePersonName")[0].value = $(this).parent().parent().children()[4].textContent;
		$("#delete-element-responsiblePersonPhone")[0].value = $(this).parent().parent().children()[5].textContent;
		$("#delete-element-address")[0].value = $(this).parent().parent().children()[6].textContent;
		$("#delete-element-structure")[0].value = $(this).parent().parent().children()[7].textContent;
		$("#delete-element-tongKind")[0].value = $(this).parent().parent().children()[8].textContent;
		$("#delete-element-utPrice")[0].value = $(this).parent().parent().children()[9].textContent;
		$("#delete-element-planVolumn")[0].value = $(this).parent().parent().children()[10].textContent;
		$("#delete-element-completedVolumn")[0].value = $(this).parent().parent().children()[11].textContent;
		$("#delete-element-description")[0].value = $(this).parent().parent().children()[12].textContent;
		$("#delete-element-state")[0].value = $(this).parent().parent().children()[13].textContent;
		$("#dialog-element-delete").popup("open");
    });
	
	$(".btn-searchTrigger").on("click", function() {
		$(".autocomplete_searchCustomersByName").on("listviewbeforefilter", searchCustomersByName);
	});
}

// http://view.jquerymobile.com/1.3.1/demos/widgets/autocomplete/autocomplete-remote.php
// http://itpscan.info/blog/jquery_mobile/example5.php
function searchByName( e, data ) {
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
            	html += "<li btnID='" + val.id + "'>" + val.id + "-" + val.name + "</li>";
            });
            $ul.html( html );
            $ul.listview( "refresh" );
            $ul.trigger( "updatelayout");
        });
    }
    
    // click to select value of auto-complete
    $( document).on( "click", "#autocomplete_searchByName li", function() {
		var id = $(this).attr("btnID");
		var selectedItem = $(this).html();
		$.get("/project/getById?id="+id, null, receiveData);
		$(this).parent().parent().find('input').val(selectedItem);   
		$('#autocomplete_searchByName').hide();     
    });
};

function searchCustomersByName( e, data ) {
    var $ul = $( this ),
        $input = $( data.input ),
        value = $input.val(),
        html = "";
    $ul.html( "" );
    if ( value && value.length > 1 ) {
    	$ul.show();
        $ul.html( "<li><div class='ui-loader'><span class='ui-icon ui-icon-loading'></span></div></li>" );
        $ul.listview( "refresh" );
        $.get("/customer/searchByName?name=" + $input.val(), null, function ( response ) {
            $.each( response.Records, function ( i, val ) {
            	html += "<li btnID='" + val.id + "' btnName='" + val.name + "'>" + val.id + "-" + val.name + "</li>";
            });
            $ul.html( html );
            $ul.listview( "refresh" );
            $ul.trigger( "updatelayout");
        });
    }
    
    // click to select value of auto-complete
    $( document).on( "click", ".autocomplete_searchCustomersByName li", function() {
    	var selectedItem = $(this).html();
    	$(this).parent().parent().find('input').val(selectedItem);
		var id = $(this).attr("btnID");
		var name = $(this).attr("btnName");
		$(this).parent().parent().parent().find('#create-element-customerId').val(id);   
		$(this).parent().parent().parent().find('#create-element-customerName').val(name);   
		$('.autocomplete_searchCustomersByName').hide();     
    });
};

var StartIndex = 1;
var pageTotalCount = 1;
$( document ).on( "pageinit", "#myPage", function() {
$.get("/project/get?tbStartIndex=0&tbPageSize=10", null, receiveData);
    
    $( "#element-btn-display-page" ).on( "click", function( event ) {
		var page = $("#element-selector-page").val();
		if(page > pageTotalCount) {
			page = pageTotalCount;
		}
		StartIndex = page;
		$.get("/project/get?tbStartIndex=" + (page-1) + "&tbPageSize=10", null, receiveData);
		$(this).parent().popup("close");
	});
    
    $( "#btn-create-element" ).on( "click", function( event ) {
    	$.post("/project/add?" + $("#form-create-element").serialize(), null, receiveData);
    	$("#form-create-element")[0].reset();
    	$(this).parent().parent().parent().parent().popup("close");
	});
    
    $( "#btn-modify-element" ).on( "click", function( event ) {
		$.post("/project/update?" + $('#form-modify-element').serialize(), null, receiveData);
		$(this).parent().parent().parent().parent().popup("close");
	});
    
    $( "#btn-delete-element" ).on( "click", function( event ) {
    	$.post("/project/remove?" + $('#form-delete-element').serialize(), null, receiveData);
		$(this).parent().parent().parent().parent().popup("close");
	});
    
    // export to a excel file
    $( "#pop-up-btn-export-excel" ).on( "click", function( event ) {
    	document.location.href='./project/exportExcel';	
	});

    // search by project name
	$( "#autocomplete_searchByName" ).on( "listviewbeforefilter", searchByName);
	
	$(".btn-searchTrigger").on("click", function() {
		$(".autocomplete_searchCustomersByName").on("listviewbeforefilter", searchCustomersByName);
	});
});