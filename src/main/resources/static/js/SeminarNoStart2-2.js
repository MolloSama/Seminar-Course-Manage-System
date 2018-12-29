
/**
 * @Description 对应SeminarNoStart2-2.html的JS脚本,GET1未完成!
 * @author Weilun Zhang
 * @version v1.0
 * @date 2018/12/16
 */

$(document).ready(function() {

    var xmlhttp;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.open("GET","/seminar/{seminarId}/class/{classId}/presentation",true);
    xmlhttp.send();
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {
            var presentation=JSON.parse(xmlhttp.responseText);
            $("#id").text(presentation.id);
            $("#presentationScore").text(presentation.presentationScore);
            $("#reportScore").text(presentation.reportScore);
            $("#presentationFileName").text(presentation.presentationFileName);
            $("#reportFileName").text(presentation.reportFileName);
            $("#presentationUrl").text(presentation.presentationUrl);
            $("#reportUrl").text(presentation.reportUrl);
            $("#presentationOrder").text(presentation.presentationOrder);

        }
    }


    $("#a2").click(function() {
        window.location.href = "StudentMain.html";
    });
    $("#a3").click(function() {
        window.location.href = "SeminarMessage.html";
    });
    $("#top1").click(function() {
        window.history.back();
    });
    $("#top3").click(function() {
        $(".dropdown-content").slideToggle("slow");
    });
});