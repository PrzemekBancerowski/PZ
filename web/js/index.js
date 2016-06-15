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

	//init
	$("#archiveMeasures").toggle();
	$("#odKiedy").datepicker("setDate", new Date(new Date - 12096e5));
	$("#doKiedy").datepicker("setDate", new Date());
    $('#nazwa').keyup(searchFilter);
});

function enableButton() {
	$('#chooseMonitorButton').attr("disabled", false);
}

function chooseMonitor(){
	id = $('#wybierzMonitor').val();
	selectedMonitor = id;
	if(id != null) {
        getListSensors();
        $("#hostsData").fadeIn();
        $("#daneSensora").fadeOut();
        $("#metryki").fadeOut();
    }
}

function getListSensors(){
    selectedMonitor = $('#wybierzMonitor').val();
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
   selectedMonitor = $('#wybierzMonitor').val();
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
function addMetric(){
    $("#dodajMetryke").fadeIn();
    select = document.getElementById("pomiarSel");
    for(metric in tableMetrics){
        if(tableMetrics[metric].metricType != "COMPLEX") {
            select.options[select.options.length] = new Option(tableMetrics[metric].description + " ("+tableMetrics[metric].measure+")",metric);
        }
    }
}

function zapiszMetryke(){
    var interv = $("#interwalInp").val();
    var wind = $("#oknoInp").val();
    var met = $("#pomiarSel").val();
    var name = $("#nazwaMetryki").val();
    data = {};
    data.interval = interv;
    data.windowSize = wind;
    data.description = name;
    data.measure = tableMetrics[met].measure;
    d = JSON.stringify(data);
    if(interv>0 && wind > 0 && met > 0){
        $.ajax({
            url: 'http://localhost:7755/monitors/' +selectedMonitor +"/sensors/"+sensorId+"/metrics",
            type: "POST",
            data: d,
            dataType: "json",
            async:false,
            success: function(){
                getMetrics(sensorId);
                $("#dodajMetryke").fadeOut();
            },
            error: function(){
                ErrorFunction();
            }
        })
    }
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

function ErrorFunction(){
    console.log("cos poslo nie tak");
}
$('input[name=measureType]').change( function() {
   $("#archiveMeasures").toggle();
   $("#liveMeasures").toggle();
});

function showMeasures() {
	param = {};
	param.startTime = new Date($("#odKiedy").val());
	param.endTime = new Date($("#doKiedy").val());
	//param.maxCount = $("#ileOstatnich").val();
	
	if($('#liveMeasuresButton').prop('checked')) {
		czestotliwosc = $("#czestotliwosc").val();
		now = new Date();
		param.startTime = new Date(now.getTime() - czestotliwosc*1000);
		param.endTime = now;
		
		//getting measures in loop...
	} else {
		getSimpleMeasures(param);
	}
	
}

function getSimpleMeasures(param) {
	$.ajax({
        url: 'http://localhost:7755/monitors/' + selectedMonitor + "/sensors/" + sensorId + "/metrics/" + metricId + "/measurements",
        type: "GET",
        dataType: "json",
		data: {fromDate: param.startTime.getTime(), toDate: param.endTime.getTime()},
        async:false,
        success: function(data) {
        	
        },
        error: function () {
        	ErrorFunction();
        }

    });
}
