
/**
 * @Description 对应SeminarMessage.html的JS脚本
 * @author Weilun Zhang
 * @version v1.0
 * @date 2018/12/19
 */

$(document).ready(function() {

    var xmlhttp1, xmlhttp2;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp1=new XMLHttpRequest();
        xmlhttp2=new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp1=new ActiveXObject("Microsoft.XMLHTTP");
        xmlhttp2=new ActiveXObject("Microsoft.XMLHTTP");
    }
    var url1 = "/course/" + courseId + "/round";
    var url2 = "/round/" + roundId + "/seminar";

    xmlhttp1.open("GET",url1,true);
    xmlhttp2.open("GET",url2,true);

    xmlhttp1.send();
    xmlhttp2.send();

    xmlhttp1.onreadystatechange=function()
    {
        if (xmlhttp1.readyState==4 && xmlhttp1.status==200)
        {
            var round=JSON.parse(xmlhttp1.responseText);
            $("#roundId").text(round.id);
            $("#roundOrder").text(round.order);
        }
    }

    xmlhttp2.onreadystatechange=function()
    {
        if (xmlhttp2.readyState==4 && xmlhttp2.status==200)
        {
            var seminar=JSON.parse(xmlhttp2.responseText);
            $("#seminarId").text(seminar.id);
            $("#topic").text(seminar.topic);
            $("#seminarOrder").text(seminar.order);
        }
    }


    $("#top3").click(function() {
        $(".dropdown-content").slideToggle("slow");
    });
    $(".seminar_all").click(function() {
        var Name = "." + this.id;
        $(Name).slideToggle("slow");
    });
    $("#a2").click(function() {
        window.location.href = "StudentMain.html";
    });
    $("#a3").click(function() {
        window.location.href = "SeminarMessage.html";
    });
    $("#yonli").click(function() {
        window.location.href = "SeminarNoStart1.html";
    });
    $("#top1").click(function() {
        window.history.back();
    });
});