$(document).ready(function(){
	$.ajax({
		type: "GET",
        url: "http://localhost:7755/monitors",
		success: function(data){
            $.each(data, function(i, val) {
				$('#wybierzMonitor').append($('<option>').text(val.name + " (" + val.address + ")").attr('value', val.id));
			});
        },
        error: function(){
            $("#Warning").html("Błąd serwera.");
        }
    });
});

function enableButton() {
	$('#chooseMonitor').attr("disabled", false);
}

function chooseMonitor(){
	id = $('#wybierzMonitor').val();
	

}