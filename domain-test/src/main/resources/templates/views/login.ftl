<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script type="text/javascript" src="/js/lib/jquery/jquery-3.4.1.min.js"></script>
</head>
<body>
<div>
    <label>username</label>
    <input id="username" value="${username}">
    <input id="password" type="password" value="123456"/>
    <button id="login">Login</button>
</div>
<script type="text/javascript">
    $(document.body).ready(function () {

        $("#login").click(function () {
            var username = $("#username").val();
            var password = $("#password").val();

            var url = "/user/login?username=" + username + "&password=" + password;
            console.log(url);
            $.ajax({
                url: url,
                type: "GET",
                success: function (result, status, xhr) {
                    console.log(result);
                    if (result && result.sessionid) {
                        console.log("ok");
                        location.href = "/welcome";
                    }
                }
            })
        });
        /*var url = "http://www.example.com/post";
        //var url = "http://www.izhishi.site/admin/getLogin";
        $.ajax({
            url: url,
            type: "POST",
            dataType: "application/json",
            data: {
                "userName": "jiangli",
                "userPwd": "jiangli"
            },
            success: function (result, status, xhr) {
                console.log(result);
                console.log(status);
                console.log(xhr);
            }
        })*/
    });
</script>
</body>
</html>