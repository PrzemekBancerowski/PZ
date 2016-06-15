$(document).ready(function(){
    par = decodeURIComponent(window.location.search.substring(1)).split("=");
    if(par[0]=="stat" && par[1]=="created"){
        $("#Warning").html("Konto założone, teraz możesz się zalogować.");
    }
});

function login(){
    $("#Warning").html("");
    data = {};
    data.email = $("#InputLogin").val();
    data.password = $("#InputPassword").val();

    $.ajax({
        type: "POST",
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