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
		$("#modify-element-id")[0].value = $(this).parent().parent().children()[0].textContent;
		$("#modify-element-name")[0].value = $(this).parent().parent().children()[1].textContent;
		$("#modify-element-phone")[0].value = $(this).parent().parent().children()[2].textContent;
		$("#modify-element-description")[0].value = $(this).parent().parent().children()[3].textContent;
		$("#modify-element-state")[0].value = $(this).parent().parent().children()[4].textContent;
		$("#dialog-element-modify").popup("open");
    });
    
    $(".btn-delete").on("click", function(event) {
    	$("#delete-element-id")[0].value = $(this).parent().parent().children()[0].textContent;
    	$("#delete-element-name")[0].value = $(this).parent().parent().children()[1].textContent;
    	$("#delete-element-phone")[0].value = $(this).parent().parent().children()[2].textContent;
    	$("#delete-element-description")[0].value = $(this).parent().parent().children()[3].textContent;
    	$("#delete-element-state")[0].value = $(this).parent().parent().children()[4].textContent;;
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


var StartIndex = 1;
var pageTotalCount = 1;
$( document ).on( "pageinit", "#myPage", function() {
$.get("/employee/get?tbStartIndex=0&tbPageSize=10", null, receiveData);
    
    $( "#element-btn-display-page" ).on( "click", function( event ) {
		var page = $("#element-selector-page").val();
		if(page > pageTotalCount) {
			page = pageTotalCount;
		}
		StartIndex = page;
		$.get("/employee/get?tbStartIndex=" + (page-1) + "&tbPageSize=10", null, receiveData);
		$(this).parent().popup("close");
	});
    
    $( "#btn-create-element" ).on( "click", function( event ) {
    	$.post("/employee/add?" + $("#form-create-element").serialize(), null, receiveData);
    	$("#form-create-element")[0].reset();
		$(this).parent().parent().parent().parent().popup("close");
	});
    
    $( "#btn-modify-element" ).on( "click", function( event ) {
    	$.post("/employee/update?" + $('#form-modify-element').serialize(), null, receiveData);
    	$(this).parent().parent().parent().parent().popup("close");
	});
    
    $( "#btn-delete-element" ).on( "click", function( event ) {
		$.post("/employee/remove?" + $('#form-modify-element').serialize(), null, receiveData);
		$(this).parent().parent().parent().parent().popup("close");
	});
    
    // export to a excel file
    $( "#pop-up-btn-export-excel" ).on( "click", function( event ) {
    	document.location.href='./employee/exportExcel';	
	});
    
    // search by company name
	$( "#autocomplete_searchByName" ).on( "listviewbeforefilter", searchByName);
});