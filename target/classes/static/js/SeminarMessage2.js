var seminarList;
var item;
var roundId = 1;
var seminarId = 1;
var classId = 1;
window.onload = function() {
  localStorage.setItem("");
  roundId = localStorage.getItem("roundId");
  $.ajax({
    type: "GET",
    url: "/round/" + roundId + "/seminar",
    async: false,
    success: function(response) {
      seminarList = response;
      if (seminarList.length == 0) $("#seminar").html("该轮次下讨论课列表为空");

      for (var i in seminarList) {
        item = seminarList[i];
        seminarId = item.id;

        $("#seminar").append(
          '<div><p id="' +
            seminarId +
            '">&nbsp;&nbsp;&nbsp;&nbsp;' +
            item.topic +
            "</p>" +
            '<ul class="' +
            seminarId +
            '" "style="display: block;" style="display: none;"></ul></div><br/>'
        );
        solve(seminarId);
        $.ajax({
          type: "GET",
          url: "/seminar/" + seminarId + "/class",
          async: false,
          success: function(response2) {
            classList = response2;
            for (var i in classList) {
              var item2 = classList[i];
              classId = item2.id;
              className = item2.name;
              $("." + seminarId).append(
                "<li id=" +
                  classId +
                  ' onclick="chooseClass(' +
                  classId +
                  "," +
                  className +
                  ')">' +
                  className +
                  "</li>"
              );
            }
          },
          error: function() {
            alert("获取该讨论课所属班级信息失败！");
          }
        });
      }
    },
    error: function() {
      alert("获取该轮次下讨论课信息失败！");
    }
  });
};
//点击弹出列表
function solve(id) {
  $("#" + id).click(function() {
    $("." + id).slideToggle("slow");
    window.localStorage.setItem("seminarId", id);
  });
}
//点击班级跳转
function chooseClass(id, name) {
  window.localStorage.setItem("classId", id); //讨论课班级
  window.localStorage.setItem("className", name);
  window.location.href = "seminarStart.html";
}
//设置讨论课轮次
function setRound() {
  var round = $("#round").val();
  $.ajax({
    type: "PUT",
    url: "/seminar/" + seminarId + "/round",
    data: JSON.stringify(round),
    contentType: "application/json;charest=utf-8",
    success: function(data) {
      alert("修改成功！");
    },
    error: function() {
      alert("修改失败!");
    }
  });
}
//设置本轮次成绩计算规则
function setRoundMethod() {}
