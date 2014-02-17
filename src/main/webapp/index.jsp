<%--
  User: lixy
  Mail: admin@TomyTime.com
  Date: 14-2-15 下午10:30 
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
  <head>
    <title>halo</title>
      <link rel="stylesheet" href="static/css/pure-min.css">
      <!--[if lte IE 8]>
      <link rel="stylesheet" href="static/css/grid-old-ie.css">
      <![endif]-->
      <!--[if gt IE 8]><!-->
      <link rel="stylesheet" href="static/css/grid.css">
      <!--<![endif]-->
      <script type="text/javascript" src="/ca/static/js/jquery-1.8.3.js"></script>
  </head>
  <body>   <!--yui3-skin-sam-->

  <div class="header">
      <div class="home-menu pure-menu pure-menu-open pure-menu-horizontal pure-menu-fixed">
          <a class="pure-menu-heading" href="">Your Site</a>

          <ul>
              <li class="pure-menu-selected"><a href="#">Home</a></li>
              <li><a href="#">Tour</a></li>
              <li><a href="#">Sign Up</a></li>
          </ul>
      </div>
  </div>

  <div class="content-wrapper">
      <div class="content">
          <h2 class="content-head is-center">Dolore magna aliqua. Uis aute irure.</h2>
          <div id="layout">
              <input type="button" onclick="p()" value=" post() " />
              <button class="pure-button" onclick="add()"> add() </button>
              <a href="g/i/1"> getUser(1) </a>
          </div>
          <div class="pure-g">
              <div class="l-box-lrg pure-u-1 pure-u-med-2-5">
                  <h4>这是清单1</h4>
                  <table class="pure-table pure-table-bordered" id="goods-list">
                      <thead><tr><th>名称</th><th>规格</th></tr></thead>
                  </table>
              </div>
              <div class="l-box-lrg pure-u-1 pure-u-med-1-5">
                  <h4>这是清单2</h4>
                  <table class="pure-table pure-table-bordered" id="user-list">
                      <thead><tr><th>名称</th></tr></thead>
                  </table>
              </div>
              <div class="l-box-lrg pure-u-1 pure-u-med-2-5">
                  <form class="pure-form pure-form-stacked">
                      <fieldset>
                          <label for="name"> 名称 </label>
                          <input required name="name" id="name" type="text" placeholder="Your Name">
                          <button onclick="add()" type="button" class="pure-button"> Insert  </button>
                      </fieldset>
                  </form>
              </div>
          </div>
      </div>
      <%--<div class="footer l-box is-center">
          View the source of this layout to learn more. Made with love by the YUI Team.
      </div>--%>
  </div>

  <script type="text/javascript">

      function p(){
          $.ajax({
              type: "POST",
              url: "g/p",
              dataType: "json",
              contentType:'application/json',
              data: JSON.stringify({"id": "234"})
          }).done(function(result) {
                      console.log(result);
                  })
                  .error(function(){
                      console.log("something wrong with ajax");
                  });
      }

      function g(){
          $.get("g/get")
                  .done(function(data) {
                      $("#goods-list tbody tr").remove();
                      var html = "";
                      $.each(data, function(i, e){
                        html += "<tr id='" + e.id + "'>"
                             + "<td>" + e.name + "</td>"
                             + "<td>" + e.model + "</td></tr>";
                      });
                      $("#goods-list").append(html);
                  });
      }

      function u(){
          $.get("g/u")
                  .done(function(data) {
                      $("#user-list tbody tr").remove();
                      var html = "";
                      $.each(data, function(i, e){
                          html += "<tr id='" + e.id + "'>"
                                  + "<td>" + e.name + "</td></tr>";
                      });
                      $("#user-list").append(html);
                  });
      }

      function add(){
          $.ajax({
              type: "POST",
              url: "g/add",
              dataType: "json",
              contentType:'application/json',
              data: JSON.stringify({"name": $('#name').val(), "status": "1"})
          }).done(function(result) {
                      refresh();
                  })
                  .error(function(){
                      console.log("something wrong with ajax");
                      refresh();
                  });
      }

      function refresh(){
          g();
          u();
      }

      $(document).ready(function(){
          refresh();
      })
  </script>
  </body>
</html>