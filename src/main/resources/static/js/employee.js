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
						"<td>" + row.phone + "</td>" + 
						"<td>" + row.description + "</td>" + 
						"<td>" + row.state + "</td>" + 
						"<td><a class='btn-edit ui-btn ui-icon-edit ui-btn-icon-notext ui-corner-all'>No text</a></td>" + 
						"<td><a href='#' class='btn-delete ui-btn ui-icon-delete ui-btn-icon-notext ui-corner-all'>No text</a></td>"
					 "</tr>"
		newBody = newBody + newRow;
	 });
	$("#tbProp").empty().append(newBody);
	$('#table-element').table("refresh");
	
	$(".btn-edit").on("click", function(event) {
		$("#modify-element-id")[0].value = model_table[$(this).parent().parent().attr("rowId")].id;
		$("#modify-element-name")[0].value = model_table[$(this).parent().parent().attr("rowId")].name;
		$("#modify-element-phone")[0].value = model_table[$(this).parent().parent().attr("rowId")].phone;
		$("#modify-element-description")[0].value = model_table[$(this).parent().parent().attr("rowId")].description;
		$("#modify-element-state")[0].value = model_table[$(this).parent().parent().attr("rowId")].state;
		$("#dialog-element-modify").popup("open");
    });
    
    $(".btn-delete").on("click", function(event) {
    	$("#delete-element-id")[0].value = model_table[$(this).parent().parent().attr("rowId")].id;
    	$("#delete-element-name")[0].value = model_table[$(this).parent().parent().attr("rowId")].name;
    	$("#delete-element-phone")[0].value = model_table[$(this).parent().parent().attr("rowId")].phone;
    	$("#delete-element-description")[0].value = model_table[$(this).parent().parent().attr("rowId")].description;
    	$("#delete-element-state")[0].value = model_table[$(this).parent().parent().attr("rowId")].state;
		$("#dialog-element-delete").popup("open");
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
        $.get("/employee/searchByName?name=" + $input.val(), null, function ( response ) {
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
		$.get("/employee/getById?id="+id, null, receiveData);
		$(this).parent().parent().find('input').val(selectedItem);   
		$('#autocomplete_searchByName').hide();     
    });
};

function getData() {
	var tbStartIndex = $("#element-selector-page").val() - 1;
	$.get("/employee/get?tbStartIndex=" + tbStartIndex + "&tbPageSize=10", null, receiveData);
}

$( document ).on( "pageinit", "#myPage", function() {
    
    $( "#btn-get-data" ).on( "click", function( event ) {
		getData();
	});
    
    $( "#btn-create-element" ).on( "click", function( event ) {
    	$.post("/employee/add?" + $("#form-create-element").serialize(), null, function() {getData();});
    	$("#form-create-element")[0].reset();
		$(this).parent().parent().parent().parent().popup("close");
	});
    
    $( "#btn-modify-element" ).on( "click", function( event ) {
    	$.post("/employee/update?" + $('#form-modify-element').serialize(), null, function() {getData();});
    	$(this).parent().parent().parent().parent().popup("close");
	});
    
    $( "#btn-delete-element" ).on( "click", function( event ) {
		$.post("/employee/remove?" + $('#form-delete-element').serialize(), null, function() {getData();});
		$(this).parent().parent().parent().parent().popup("close");
	});
    
    // export to a excel file
    $( "#pop-up-btn-export-excel" ).on( "click", function( event ) {
    	document.location.href='./employee/exportExcel';	
	});
    
    // search by company name
	$( "#autocomplete_searchByName" ).on( "listviewbeforefilter", searchByName);
});