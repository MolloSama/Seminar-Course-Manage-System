var item;
var id;
window.onload=function(){
    id=parseInt(localStorage.getItem("seminarId"));
    $.ajax({
        async: false,
        type: "GET",
        url: "/seminar/"+id,
        success: function (response) {
            item=response;
            console.log(localStorage.getItem("className"));
            $("#seminarClass").text(localStorage.getItem("className"));
            $("#order").html(item.order);
            $("#topic").html(item.topic);
            $("#intro").html(item.intro);
        },
        error: function() {
            alert("获取该轮次下讨论课信息失败！");
        }
    });
}