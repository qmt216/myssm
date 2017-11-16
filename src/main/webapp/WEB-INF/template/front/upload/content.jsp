<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>${product.name}-${system.companyName}</title>
    <meta name="keyword" content="${product.name},${system.companyName}">
    <meta name="description" content="${product.summary}">

    <link href="${frontPath}/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${frontPath}/css/startstopgallery.css" rel="stylesheet" type="text/css"/>

    <script src="${frontPath}/js/jquery-1.2.2.pack.js" type="text/javascript"></script>
    <script type="text/javascript" src="${frontPath}/js/jquery-1.2.6.js"></script>
    <script type="text/javascript" src="${frontPath}/js/startstop-slider.js"></script>
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
        });
    </script>
</head>

<body>
<form id="form1" name="form1" method="post" action="">
    <div id="wrapper">
    [#include "front/include/top.ftl" /]
        <div id="content"><span class="greytitlebig">${product.name}</span>
            <br/>
            <br/>
         ${product.introduce}
        </div>
    [#include "front/include/bottom.ftl" /]
    </div>
</form>
</body>
</html>
