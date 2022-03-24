function loginAction() {
     var username = document.getElementById("username").value;
     var password = document.getElementById("paasword").value;
     var formData = {};
     formData.userName = username;
     formData.password = password;

     $.ajax({
        type: 'POST',
        url: "/api/user-service/v1/user/login",
        data: formData,
        success: function (data) {
            console.log(data);
            setCookie("session-id", data.sessiondId, 1);
            window.location = "/ui/upload.html";
        },
        error: function(data) {
            console.log(data);
            var error = document.getElementById("error");
            error.textContent="Login failed";
            error.hidden=false;
        },
        async: true
    });
}

function setCookie(name,value,days) {
    var expires = "";
    if (days) {
        var date = new Date();
        date.setTime(date.getTime() + (days*24*60*60*1000));
        expires = "; expires=" + date.toUTCString();
    }
    document.cookie = name + "=" + (value || "")  + expires + "; path=/";
}