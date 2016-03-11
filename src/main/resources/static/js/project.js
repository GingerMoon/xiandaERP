var model_table;
var StartIndex = 1;

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
		$("#modify-element-id")[0].value = model_table[$(this).parent().parent().attr("rowId")].id;
		$("#modify-element-name")[0].value = model_table[$(this).parent().parent().attr("rowId")].name;
		$("#modify-element-customerId")[0].value = model_table[$(this).parent().parent().attr("rowId")].customer.id;
		$("#modify-element-customerName")[0].value = model_table[$(this).parent().parent().attr("rowId")].customer.name;
		$("#modify-element-responsiblePersonName")[0].value = model_table[$(this).parent().parent().attr("rowId")].responsiblePersonName;
		$("#modify-element-responsiblePersonPhone")[0].value = model_table[$(this).parent().parent().attr("rowId")].responsiblePersonPhone;
		$("#modify-element-address")[0].value = model_table[$(this).parent().parent().attr("rowId")].address;
		$("#modify-element-structure")[0].value = model_table[$(this).parent().parent().attr("rowId")].structure;
		$("#modify-element-tongKind")[0].value = model_table[$(this).parent().parent().attr("rowId")].tongKind;
		$("#modify-element-utPrice")[0].value = model_table[$(this).parent().parent().attr("rowId")].utPrice;
		$("#modify-element-planVolumn")[0].value = model_table[$(this).parent().parent().attr("rowId")].planVolumn;
		$("#modify-element-completedVolumn")[0].value = model_table[$(this).parent().parent().attr("rowId")].completedVolumn;
		$("#modify-element-description")[0].value = model_table[$(this).parent().parent().attr("rowId")].description;
		$("#modify-element-state")[0].value = model_table[$(this).parent().parent().attr("rowId")].state;
		$("#dialog-element-modify").popup("open");
    });
    
    $(".btn-delete").on("click", function(event) {
    	$("#delete-element-id")[0].value = model_table[$(this).parent().parent().attr("rowId")].id;
		$("#delete-element-name")[0].value = model_table[$(this).parent().parent().attr("rowId")].name;
		$("#delete-element-customerId")[0].value = model_table[$(this).parent().parent().attr("rowId")].customer.id;
		$("#delete-element-customerName")[0].value = model_table[$(this).parent().parent().attr("rowId")].customer.name;
		$("#delete-element-responsiblePersonName")[0].value = model_table[$(this).parent().parent().attr("rowId")].responsiblePersonName;
		$("#delete-element-responsiblePersonPhone")[0].value = model_table[$(this).parent().parent().attr("rowId")].responsiblePersonPhone;
		$("#delete-element-address")[0].value = model_table[$(this).parent().parent().attr("rowId")].address;
		$("#delete-element-structure")[0].value = model_table[$(this).parent().parent().attr("rowId")].structure;
		$("#delete-element-tongKind")[0].value = model_table[$(this).parent().parent().attr("rowId")].tongKind;
		$("#delete-element-utPrice")[0].value = model_table[$(this).parent().parent().attr("rowId")].utPrice;
		$("#delete-element-planVolumn")[0].value = model_table[$(this).parent().parent().attr("rowId")].planVolumn;
		$("#delete-element-completedVolumn")[0].value = model_table[$(this).parent().parent().attr("rowId")].completedVolumn;
		$("#delete-element-description")[0].value = model_table[$(this).parent().parent().attr("rowId")].description;
		$("#delete-element-state")[0].value = model_table[$(this).parent().parent().attr("rowId")].state;
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

function getData() {
	beginDate = $("#search-date-begin").val();
	endDate = $("#search-date-end").val();
	var tbStartIndex = $("#element-selector-page").val() - 1;
	$.get("/project/get?beginDate=" + beginDate + "&endDate=" + endDate + "&tbStartIndex=" + tbStartIndex + "&tbPageSize=10", null, receiveData);
}

$( document ).on( "pageinit", "#myPage", function() {
    
    $( "#btn-get-data" ).on( "click", function( event ) {
		getData();
	});
    
    $( "#btn-create-element" ).on( "click", function( event ) {
    	$.post("/project/add?" + $("#form-create-element").serialize(), null, function() {getData();});
    	$("#form-create-element")[0].reset();
    	$(this).parent().parent().parent().parent().popup("close");
	});
    
    $( "#btn-modify-element" ).on( "click", function( event ) {
    	$.post("/project/update?" + $('#form-modify-element').serialize(), null, function() {getData();});
		$(this).parent().parent().parent().parent().popup("close");
	});
    
    $( "#btn-delete-element" ).on( "click", function( event ) {
		$.post("/project/remove?" + $('#form-delete-element').serialize(), null, function() {getData();});
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