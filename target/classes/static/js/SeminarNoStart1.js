
/**
 * @Description 对应SeminarNoStart1.html的JS脚本
 * @author Weilun Zhang
 * @version v1.0
 * @date 2018/12/15
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
    xmlhttp.open("GET","/seminar/23",true);
    xmlhttp.send();
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {
            var seminar=JSON.parse(xmlhttp.responseText);
            $("#id").text(seminar.id);
            $("#topic").text(seminar.topic);
            $("#intro").text(seminar.intro);
            $("#status").text(seminar.status);
            $("#order").text(seminar.order);
        }
    }


    $("#a2").click(function() {
        window.location.href = "StudentMain.html";
    });
    $("#a3").click(function() {
        window.location.href = "SeminarMessage.html";
    });
    $("#sub").click(function() {
        window.location.href = "SeminarEnroll.html";
    });
});