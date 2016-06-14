var tableSensors = [];
var tableMetrics = [];
var selectedMonitor = null;
var sensorId = null;
var metricId = null;

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
	$('#chooseMonitorButton').attr("disabled", false);
}

function chooseMonitor(){
	id = $('#wybierzMonitor').val();
	
	if(id != null) {
        getListSensors();
        $("#hostsData").fadeIn();
        $("#daneSensora").fadeOut();
        $("#metryki").fadeOut();
    }
}


function getListSensors(){
    var selectedMonitor = $('#wybierzMonitor').val();
    $.ajax({
        url: 'http://localhost:7755/monitors/' + selectedMonitor + '/sensors',
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
    var selectedMonitor = $('#wybierzMonitor').val();
   $.ajax({
        url: 'http://localhost:7755/monitors/' + selectedMonitor + '/sensors/' + sensorId + '/metrics',
        type: "GET",
        dataType: "json",
        async:false,
         success: function (msg) {
             tableMetrics = msg;
             addMetricsToList(msg);
        },
        error: function () {
            ErrorFunction();
        }

    });
}



function addSensorsToList(listOfSensors){
    var select = document.getElementById("sel2");
    $(select).empty();
    for(sensor in listOfSensors) {
        select.options[select.options.length] = new Option(listOfSensors[sensor].hostName, sensor);
    }
}

function addMetricsToList(listOfMetrics){
    var select = document.getElementById("sel3");
    $(select).empty();
    for(metric in listOfMetrics){
        var str = listOfMetrics[metric].description+" ("+listOfMetrics[metric].measure;
        if(listOfMetrics[metric].metricType == "COMPLEX") {
            var str = str + ", interwał = "+listOfMetrics[metric].interval + ", okno = " + listOfMetrics[metric].windowSize;
        }
        var str = str + ")";
        select.options[select.options.length] = new Option(str, metric);
    }
}


function changeFunc() {
	var obj = document.getElementById("sel2");
	sensorId = obj.options[obj.selectedIndex].value;


    $("#daneSensora").fadeIn();
    $("#metryki").fadeIn();

    var tab = $("#daneSensoraTab");
    tab.html("<tr><td colspan='2'><b>Metadane</b></td></tr>");
	//$("#daneSensora").append( "<td> id: </td><td>" + tableSensors[sensorId].id + "</td>" );
	tab.append( "<tr><td> Nazwa hosta: </td><td>" + tableSensors[sensorId].nostName+ "</td></tr>" );
    tab.append( "<tr><td> CPU: </td><td>" + tableSensors[sensorId].cpu + "</td></tr>" );
    tab.append( "<tr><td> Producent CPU: </td><td>" + tableSensors[sensorId].cpuVendor + "</td></tr>" );
    tab.append( "<tr><td> Taktowanie: </td><td>" + tableSensors[sensorId].cpuCoreCount + "</td></tr>" );
    tab.append( "<tr><td> Pamięć: </td><td>" + tableSensors[sensorId].memorySize + "</td></tr>" );
    tab.append( "<tr><td> System: </td><td>" + tableSensors[sensorId].systemName + "</td></tr>" );
    tab.append( "<tr><td> Architektura: </td><td>" + tableSensors[sensorId].systemArch + "</td></tr>" );
    tab.append( "<tr><td> Producent systemu: </td><td>" + tableSensors[sensorId].systemVendor + "</td></tr>" );
    tab.append( "<tr><td> Wersja systemu: </td><td>" + tableSensors[sensorId].systemVersion+ "</td></tr>" );

/*	$("#daneSensora").append( "<p> hostName: " + tableSensors[sensorId].hostName + "</p>" );
	$("#daneSensora").append( "<p> cpu: " + tableSensors[sensorId].cpu + "</p>" );
	$("#daneSensora").append( "<p> cpuVendor: " + tableSensors[sensorId].cpuVendor + "</p>" );
	$("#daneSensora").append( "<p> cpuCoreCount: " + tableSensors[sensorId].cpuCoreCount + "</p>" );
	$("#daneSensora").append( "<p> memorySize: " + tableSensors[sensorId].memorySize + "</p>" );
	$("#daneSensora").append( "<p> systemName: " + tableSensors[sensorId].systemName + "</p>" );
	$("#daneSensora").append( "<p> systemArch: " + tableSensors[sensorId].systemArch + "</p>" );
	$("#daneSensora").append( "<p> systemVendor: " + tableSensors[sensorId].systemVendor + "</p>" );
	$("#daneSensora").append( "<p> systemVersion: " + tableSensors[sensorId].systemVersion + "</p>" );
*/
	getMetrics(sensorId);
}

function changeFunc2(){
    var obj = $("#sel3");
    metricId = obj.val();

    if(tableMetrics[metricId].metricType == "COMPLEX"){
        $("#delMetricBtn").attr("disabled", false);
    }
    else{
        $("#delMetricBtn").attr("disabled", true);
    }
    $("#daneMetryki").fadeIn();
}

function delMetric(){
    $.ajax({
        url: 'http://localhost:7755/monitors/' + selectedMonitor + '/sensors/' + sensorId + '/metrics/'+ metricId,
        type: "DELETE",
        dataType: "json",
        async:false,
        success: function () {
            addMetricsToList(tableMetrics);
        },
        error: function () {
            ErrorFunction();
        }

    });
}
