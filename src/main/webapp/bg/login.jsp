<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="frontPath"/>
<html>
<head>
    <title>中轻门户后台管理登陆</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style type="text/css">
        <!--
        .label {
            font-size: 24px;
            width: 180px;
            height: 70px;
            font-weight: bolder;
            color: #888888;
            text-align: right;
            padding-right: 20px;
        }

        .ui {
            height: 50px;
            margin-top: 3px;
            margin-left: 4px;
            width: 300px;
            font-size: 24px;
            color: #666666;
            border: none;
            border-radius: 10px;
            border-color: #1DBEF4;
            border-width: thin;
            border-radius: 10px;
        }

        .btn {
            width: 160px;
            height: 50px;
            color: #ffffff;
            font-size: 24px;
            font-weight: bolder;
            background-color: #0BB1E8;
            border: none;
            font-size: 24px;
            font-weight: bolder;
            background-color: #0BB1E8;
            border-radius: 10px;
            cursor: pointer;
        }

        .select1 {
            width: 306px;
            height: 60px;
            margin-left: 2px;
            display: block;
            overflow: hidden;
        }

        .select2 {
            width: 346px;
            height: 60px;
            margin: 2px;
            border: 0px solid #000;
            background: url('images/login_form_lang.png');
        }

        select {
            width: 326px;
            height: 56px;
            margin-top: 4px;
            margin-left: 2px;
            border: 1px solid #D3D3D3;
            border-radius: 10px;
            FONT-SIZE: 24px;
            position: relative;
            left: -2px;
            top: -2px;
            color: #888888;
            cursor: pointer;
        }

        -->
    </style>
    <script type="text/javascript" src="${frontPath}/js/jquery.js"></script>
    <script type="text/javascript">
	    var  frontPath='${frontPath}';
        function login() {
            var username = $('#adminUserName').val();
            if (username == "") {
                alert("请填写用户名！");
                return false;
            }
            var password = $('#adminPassword').val();
            if (password == "") {
                alert("请输入密码！");
                return false;
            }
            $.post(frontPath+'/bg/admin/login.html', {username: username, password: password}, function (txt, success, response) {
                var result = response.responseText;
                if (result == 'success') {
                    window.location.href =frontPath+ '/bg/index.jsp';
                } else {
                    alert("用户名或密码错误！");
                }
            });
        }
        $(function () {
            document.onkeydown = function (e) {
                var ev = document.all ? window.event : e;
                if (ev.keyCode == 13) {
                    login();
                }
            }
        });

    </script>
</head>
<body scroll="no">
<div style="height: 100px;"></div>
<table width="660px" height="430px" border="0" cellspacing="0"
       cellpadding="0"
       style="background-image: url('images/login_form.png'); text-align: center;"
       align="center">
    <tr>
        <td height="94px">
            <div style="font-size: 36px; color: #ffffff; font-weight: bolder; margin-top:20px">
                登陆
            </div>
        </td>
    </tr>
    <tr>
        <td style="vertical-align:top; ">
            <table align="center" border='0' style="margin-top:40px; width:100%">
                <tr>
                    <td class="label">登陆名</td>
                    <td style="height:70px; text-align:left; vertical-align:middle">
                        <div style="background-image: url('images/login_form_name.png'); width: 344px; height:60px; vertical-align:middle">
                            <input type="text" id="adminUserName" class="ui"/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="label">密码</td>
                    <td style="height:70px; text-align:left; vertical-align:middle">
                        <div style="background-image: url('images/login_form_passwd.png'); width: 344px; height:60px; vertical-align:middle">
                            <input type="password" id="adminPassword" class="ui"/>
                        </div>
                    </td>
                </tr>
            </table>
            <div style="text-align: center; margin-top:30px">
                <input type="button" name="btn" class="btn"
                       value='登陆'
                       onclick="login()"/>
            </div>
        </td>
    </tr>
</table>

</body>
</html>