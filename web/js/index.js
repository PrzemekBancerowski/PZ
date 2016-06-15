var tableSensors = [];
var tableMetrics = [];
var selectedMonitor = null;
var sensorId = null;
var metricId = null;
var intervalHandler = null;
var tableMeasures = [];

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
        url: 'http://localhost:7755/monitors/' + selectedMonitor + '/sensors/' + tableSensors[sensorId].id + '/metrics',
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
    select.selectedIndex = 0;
    metricId = 0;
}


function changeFunc() {
	var obj = document.getElementById("sel2");
	sensorId = obj.options[obj.selectedIndex].value;

    $("#daneSensora").fadeIn();
    $("#metryki").fadeIn();
	$("#opcjeWykresow").fadeIn();

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
        url: 'http://localhost:7755/monitors/' + selectedMonitor + '/sensors/' + tableSensors[sensorId].id + '/metrics/'+tableMetrics[metricId].id,
        type: "DELETE",
        dataType: "json",
        headers:{
            "Content-Type": "application/json;charset=UTF-8"
        },
        async:false,
        success: function () {
            getMetrics(sensorId);
        },
        error: function () {
            ErrorFunction();
            getMetrics(sensorId);
        }

    });
}
function addMetric(){
    $("#opcjeWykresow").fadeOut(200,function(){
        $("#dodajMetryke").fadeIn();
    });
    select = document.getElementById("pomiarSel");
    $(select).html("");
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
    data.metricType = "COMPLEX";
    data.userId = 0; //userID!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    d = JSON.stringify(data);
    if(interv>0 && wind > 0 && met > 0){
        $.ajax({
            url: 'http://localhost:7755/monitors/' +selectedMonitor +"/sensors/"+tableSensors[sensorId].id+"/metrics",
            type: "POST",
            headers:{
                "Content-Type": "application/json;charset=UTF-8"
            },
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
function anulujMetryke(){
    $("#dodajMetryke").fadeOut(200);
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

function wykresBtn(){
    $("#dodajMetryke").fadeOut(200,function(){
        $($("#opcjeWykresow").fadeIn(200));
    });
}
function showMeasures() {
	param = {};
    var d1 = $("#odKiedy").val();
    var d2 = $("#doKiedy").val();
    d1.replace(' ','T');
    d2.replace(' ','T');
	param.startTime = new Date(d1);
	param.endTime = new Date(d2);
	
	if($('#liveMeasuresButton').prop('checked')) {
		param.czestotliwosc = $("#czestotliwosc").val();
		$('#stopLoopBtn').attr("disabled", false);
		intervalHandler = setInterval(liveMeasuresFun, param.czestotliwosc*1000, param);
	} else {
		getSimpleMeasures(param);
	}
	
}

function liveMeasuresFun(param) {
	now = new Date();
	param.startTime = new Date(now.getTime() - param.czestotliwosc*1000);
	param.endTime = now;
	getSimpleMeasures(param);
}

function getSimpleMeasures(param) {
    var data = {};
    data.fromTime = param.startTime.getTime();
    data.toTime = param.endTime.getTime();
    var d = JSON.stringify(data);
	$.ajax({
        url: 'http://localhost:7755/monitors/' + selectedMonitor + "/sensors/" + tableSensors[sensorId].id + "/metrics/" + tableMetrics[metricId].id + "/measurements",
        type: "GET",
        headers:{
            "Content-Type": "application/json;charset=UTF-8"
        },
        data: data,
        dataType: "json",
        success: function(data) {
        	tableMeasures = data;
        },
        error: function () {
        	ErrorFunction();
        }
    });
}

function stopLoop() {
	window.clearInterval(intervalHandler);
	$('#stopLoopBtn').attr("disabled", true);
}
