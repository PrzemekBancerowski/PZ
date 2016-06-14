$(document).ready(function(){
    par = decodeURIComponent(window.location.search.substring(1)).split("=");
    if(par[0]=="stat" && par[1]=="created"){
        debugger;
        $("#Warning").html("Konto założone, teraz możesz się zalogować.");
    }
});

function login(){
    $("#Warning").html("");
    d = {};
    d.email = $("#InputLogin").val();
    d.password = $("#InputPassword").val();

    data = JSON.stringify(d);
    $.ajax({
        type: "POST",
        headers:{
            "Content-Type": "application/json;charset=UTF-8"
        },
        url: "http://localhost:7755/users/login",
        data: data,
        success: function(res){
            $("#Warning").html("");
            window.location.replace("index.html");

        },
        error: function(){
            $("#Warning").html("Zły login lub hasło.");
        }
    });
}
$