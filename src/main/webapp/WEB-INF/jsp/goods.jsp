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
      <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/pure-min.css">
      <!--[if lte IE 8]>
      <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/grid-old-ie.css">
      <![endif]-->
      <!--[if gt IE 8]><!-->
      <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/grid.css">
      <!--<![endif]-->
      <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.8.3.js"></script>
  </head>
  <body>
  <div class="header">
      <div class="home-menu pure-menu pure-menu-open pure-menu-horizontal pure-menu-fixed">
          <a class="pure-menu-heading" href="">Your Site</a>
          <ul>
              <li><a href="<%=request.getContextPath()%>">Home</a></li>
              <li><a href="<%=request.getContextPath()%>/g/list" title=" 采购 ">采 购</a></li>
              <li class="pure-menu-selected"><a href="#" title="货物清单">货物清单</a></li>
              <li><a href="<%=request.getContextPath()%>/p/index" title="交易记录">交易记录</a></li>
              <li><a href="#">Sign Up</a></li>
          </ul>
      </div>
  </div>

  <div class="content-wrapper">
      <div class="content">
          <h2 class="content-head is-center">Genius only means hard-working all one's life.</h2>
          <div class="pure-g">
              <div class="l-box-lrg pure-u-1-2">
                  <h4>货品清单</h4>
                  <table class="pure-table pure-table-bordered" id="goods-list">
                      <thead><tr><th>名称</th><th>操作</th></tr></thead>
                  </table>
              </div>
              <div class="l-box-lrg pure-u-3-8">
                  <form id = "goods_form" method="post" action="<%=request.getContextPath()%>/g/add" class="pure-form pure-form-stacked">
                      <fieldset>
                          <div class="pure-control-group">
                              <label for="goods-name"> 名称 </label>
                              <input type="text" maxlength="100" required name="name" id="goods-name" placeholder="商品名称" />
                          </div>
                          <div class="pure-control-group">
                              <label for="model">  规格 </label>
                              <input type="text" maxlength="80" required name="model" id="model" placeholder="商品规格" />
                          </div>
                          <%--<div class="pure-control-group">
                              <label for="type"> 分类 </label>
                              <select readonly required name="type" id="type">
                                  <option value='0'> 面 </option>
                                  <option value='0'> 油 </option>
                                  <option value='0'> 其他 </option>
                              </select>
                          </div>--%>
                          <div class="pure-controls">
                              <input type="hidden" value="0" name="type" />
                              <button type="submit" class="pure-button pure-button-primary"> 采 购 </button>
                          </div>
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

      function g(){
          $.get("<%=request.getContextPath()%>/g/get")
                  .done(function(data) {
                      $("#goods-list tbody tr").remove();
                      var html = "";
                      $.each(data, function(i, e){
                        html += "<tr id='" + e.id + "'>"
                             + "<td>" + e.name + "_" + e.model
                             + "</td><td> <a class='pure-button pure-button-small' href='<%=request.getContextPath()%>/g/delete/"
                             + e.id +"'> 删除 </a></td></tr>";
                      });
                      $("#goods-list").append(html);
                  });
      }

      $(document).ready(function(){
          g();
      })
  </script>
  </body>
</html>