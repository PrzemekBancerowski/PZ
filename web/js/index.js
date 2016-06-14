
var tableSensors = [];
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

    $('#nazwa').keyup(searchFilter);
});

function enableButton() {
	$('#chooseMonitorButton').attr("disabled", false);
}

function chooseMonitor(){
	id = $('#wybierzMonitor').val();
	
	if(id != null)
		getListSensors();
}

function getListSensors(){
    var selectedMonitor = $('#wybierzMonitor').val();
       $.ajax({

    url: 'http://localhost:7755//monitors/' + selectedMonitor + '/sensors',
    type: "GET",
    dataType: "json",
    async:false,
     success: function (msg) {
         tableSensors = msg;
         addSensorsToList(msg);
    },
    error: function () {
        ErrorFunction();
    }

    });
}


function getMetrics(sensorId){
    var tableMetrics = [];
    var selectedMonitor = $('#wybierzMonitor').val();
       $.ajax({

    url: 'http://localhost:7755//monitors/' + selectedMonitor + '/sensors/' + sensorId + '/metrics',
    type: "GET",
    dataType: "json",
    async:false,
     success: function (msg) {
         tableMetrics = msg;
    },
    error: function () {
        ErrorFunction();
    }

    });
}


function addSensorsToList(listOfSensors){

    var select = document.getElementById("sel2");

    for(sensor in listOfSensors) {
        select.options[select.options.length] = new Option(listOfSensors[sensor].hostName, sensor);
    }
}

function displayTextAboutSensor(id){

	$("#daneSensora").append( "<p>Test</p>" );

}

function changeFunc() {

	$("#daneSensora").html("");
	var obj = document.getElementById("sel2");
	var sensorId = obj.options[obj.selectedIndex].value;

	$("#daneSensora").append( "<p> id: " + tableSensors[sensorId].id + "</p>" );
	$("#daneSensora").append( "<p> hostName: " + tableSensors[sensorId].hostName + "</p>" );
	$("#daneSensora").append( "<p> cpu: " + tableSensors[sensorId].cpu + "</p>" );
	$("#daneSensora").append( "<p> cpuVendor: " + tableSensors[sensorId].cpuVendor + "</p>" );
	$("#daneSensora").append( "<p> cpuCoreCount: " + tableSensors[sensorId].cpuCoreCount + "</p>" );
	$("#daneSensora").append( "<p> memorySize: " + tableSensors[sensorId].memorySize + "</p>" );
	$("#daneSensora").append( "<p> systemName: " + tableSensors[sensorId].systemName + "</p>" );
	$("#daneSensora").append( "<p> systemArch: " + tableSensors[sensorId].systemArch + "</p>" );
	$("#daneSensora").append( "<p> systemVendor: " + tableSensors[sensorId].systemVendor + "</p>" );
	$("#daneSensora").append( "<p> systemVersion: " + tableSensors[sensorId].systemVersion + "</p>" );

	getMetrics(sensorId);
}

function searchFilter(){
	var listOfSensors = document.getElementById("sel2");
	var valThis = $('#nazwa').val().toLowerCase();
    if(valThis == ""){
         for(i=0; i < listOfSensors.length; ++i)
         	listOfSensors.options[i].style.display = 'block';

    } else {
        for(i=0; i < listOfSensors.length; ++i) {

            var text = listOfSensors.options[i].text.toLowerCase();
            var match = text.indexOf(valThis);
            if (match >= 0) {
                listOfSensors.options[i].style.display = 'block';
            } else {
                listOfSensors.options[i].style.display = 'none';
            }
        }
   };
}
