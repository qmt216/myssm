<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>联系我们-${system.companyName}</title>
    <meta name="keyword" content="${system.companyName}">
    <meta name="description" content="${system.companySummary}">
    <link href="${frontPath}/css/style.css" rel="stylesheet" type="text/css"/>
    <script src="${frontPath}/js/jquery-1.2.2.pack.js" type="text/javascript"></script>
    <script type="text/javascript" src="${frontPath}/js/jquery-1.2.6.js"></script>
    <script type="text/javascript" src="${frontPath}/js/startstop-slider.js"></script>
    <link href="${frontPath}/css/startstopgallery.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
        $(function () {
            // set opacity to nill on page load
            $("ul#menu span").css("opacity", "0");
            // on mouse over
            $("ul#menu span").hover(function () {
                        // animate opacity to full
                        $(this).stop().animate({
                            opacity: 1
                        }, 'slow');
                    },
                    // on mouse out
                    function () {
                        // animate opacity to nill
                        $(this).stop().animate({
                            opacity: 0
                        }, 'slow');
                    });

            $('#sendBtn').click(function () {
                var linkMan = $('#linkMan').val();
                var linkNum = $('#linkNum').val();
                var email = $('#email').val();
                var address = $('#address').val();
                if (linkMan == "" || linkNum == "") {
                    alert("请填写必要的联系信息.");
                    return false;
                }
                $.ajax({
                    url: '/message/insert.html',
                    type: "POST",
                    data: {
                        linkMan: linkMan,
                        linkNum: linkNum,
                        address: address,
                        email: email
                    },
                    dataType: "json",
                    success: function (message) {
                        alert(message.msg);
                    }
                });
            });
            $('#sendBtn').hover(function () {
                $(this).attr("src", "/images/send.gif");
            }, function () {
                $(this).attr("src", "/images/send_1.png");
            });
        });
    </script>
    <style type="text/css">
        .tip {
            color: red;
        }
    </style>
</head>

<body>
<form id="form1" name="form1" method="post" action="">
    <div id="wrapper">

    [#include "front/include/top.ftl" /]
    [#include "front/include/header.ftl" /]

        <div id="content"><span class="greytitlebig">联系我们</span><br/>
            <br/>
            <strong>联&nbsp;&nbsp;系&nbsp;&nbsp;人：</strong>${system.linkMan}<br/><br/>
            <strong>联系电话：</strong>${system.linkNum}<br/><br/>
            <strong>在&nbsp;线&nbsp;QQ：</strong>${system.qq}<br/><br/>
            <strong>邮箱地址：</strong>${system.email}<br/><br/>
            <strong>地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址：</strong>${system.companyAddress}<br/>
            <br/>

            <div id="contact">
                <label>姓名 <span class="tip">*</span></label>
                <input type="text" name="linkMan" id="linkMan"/> <br/>
                <label>手机号码 <span class="tip">*</span></label>
                <input type="text" name="linkNum" id="linkNum"/> <br/>
                <label>Email</label>
                <input type="text" name="email" id="email"/> <br/>
                <label>地址</label>
                <textarea name="address" rows="5" wrap="virtual" cols="" id="address"></textarea>
                <br/>
                <label><span class="style2 style1 style1 style1">.</span></label>
                <img src="${frontPath}/images/send_1.png" alt="" width="118" height="40" style="cursor: pointer;"
                     id="sendBtn"/><br/>
            </div>
            <br/>
        </div>

    [#include "front/include/bottom.ftl" /]

    </div>
</form>
</body>
</html>
