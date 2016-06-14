function register(){
    $("#Warning").html("");
    email = $("#InputLogin").val();
    password = $("#InputPassword").val();
    password2 = $("#RepeatPassword").val();
    if (( !validateEmail(email))||(email=="")) {
        $("#Warning").html("Niepoprawny adres e-mail.")
        return;
    }
    if(password.length<3){
        $("#Warning").html("Podane hasło jest zbyt krótkie.")
        return;
    }
    if(password!=password2){
        $("#Warning").html("Podane hasła nie zgadzają się.")
        return;
    }
    d = {};
    d.email = email;
    d.password = password;

    data = JSON.stringify(d);
    $.ajax({
        type: "POST",
        headers:{
            "Content-Type": "application/json;charset=UTF-8"
        },
        url: "http://localhost:7755/users/create",
        data: data,
        success: function(res){
            $("#Warning").html("");
            window.location.replace("login.html?stat=created");

        },
        error: function(){
            $("#Warning").html("Błąd serwera.");
        }
    });
}

function validateEmail(e) {
    var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
    return emailReg.test(e);
}

var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
};


