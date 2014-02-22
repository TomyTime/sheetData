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
      <style type="text/css">
          .table-tr-hover, .table-tr-highlight{
              background: #6FBEF3;
              color: white;
          }
          #capacity-list{ cursor: pointer; }
      </style>
      <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery-1.8.3.js"></script>
  </head>
  <body>
  <div class="header">
      <div class="home-menu pure-menu pure-menu-open pure-menu-horizontal pure-menu-fixed">
          <a class="pure-menu-heading" href="">Your Site</a>
          <ul>
              <li class="pure-menu-selected"><a href="#">Home</a></li>
              <li><a href="<%=request.getContextPath()%>/g/list" title=" 采购 ">采 购</a></li>
              <li><a href="<%=request.getContextPath()%>/g/index" title="货物清单">货物清单</a></li>
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
                  <h4>库存清单</h4>
                  <table class="pure-table pure-table-bordered" id="capacity-list">
                      <thead><tr><th>名称</th><th>单价(元)</th><th>数量</th><th>金额(元)</th></tr></thead>
                      <tbody></tbody>
                  </table>
              </div>
              <div class="l-box-lrg pure-u-3-8">
                  <form id = "purchase_form" method="post" action="<%=request.getContextPath()%>/p/addT" class="pure-form pure-form-stacked">
                      <fieldset>
                          <div class="pure-control-group">
                              <label for="user-select"> 食堂名称 </label>
                              <select readonly required name="uid" id="user-select"></select>
                          </div>
                          <div class="pure-control-group">
                              <label for="goods-name"> 商品名称 </label>
                              <input type="text" readonly required name="goods-name" id="goods-name" placeholder="商品名称" />
                          </div>
                          <div class="pure-control-group">
                              <label for="price"> 单价(元) </label>
                              <input readonly required pattern="\d{1,10}(\.\d{1,2})?" name="price" id="price" type="text" placeholder="商品单价" />
                          </div>
                          <div class="pure-control-group">
                              <label for="amount"> 数量 </label>
                              <input pattern="\d{1,8}" required name="amount" id="amount" type="text" placeholder="分配数量" />
                          </div>
                          <div class="pure-control-group">
                              <label for="subtotal"> 总额(元) </label>
                              <input readonly required name="subtotal" id="subtotal" type="text" placeholder="总金额" />
                          </div>
                          <div class="pure-control-group">
                              <label for="daytime"> 日期 </label>
                              <input required name="daytime" id="daytime" type="date" placeholder="分配日期" />
                          </div>
                          <div class="pure-controls">
                              <input type="hidden" value="" name="gid" id="goods-id" />
                              <button type="submit" class="pure-button pure-button-primary"> 分 配 </button>
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
      function c(){
          $.get("<%=request.getContextPath()%>/g/getC")
                  .done(function(data) {
                      $("#capacity-list tbody tr").remove();
                      $('#goods-select').empty();
                      var html = "";
                      $.each(data, function(i, e){
                          html += "<tr id='" + e.id + "'>"
                                  + "<td>" + e.gid + "</td><td>"
                                  + e.price + "</td><td>" + e.amount
                                  + "</td><td>" + e.subtotal + "</td></tr>";
                      });
                      $("#capacity-list tbody").append(html);
                  });
      }

      function u(){
          $.get("<%=request.getContextPath()%>/g/getU")
                  .done(function(data) {
                      $("#user-list tbody tr").remove();
                      $("#user-select").empty();
                      var select = "";
                      $.each(data, function(i, e){
                        select += "<option value='" + e.id + "'>" + e.name + "</option>"
                      });
                      $("#user-select").append(select);
                      $("#user-select")[0].selectedIndex = 0;
                  });
      }

      function i(){
          $("#capacity-list tbody").on('click', 'tr', function(){
              $("#capacity-list tbody tr.table-tr-highlight").removeClass("table-tr-highlight");
              var $this = $(this);
              if(!$this.hasClass('table-tr-highlight')){
                  $this.addClass("table-tr-highlight");
                  var cells = $this[0].cells;
                  $("#goods-name").val(cells[0].innerText);
                  $("#goods-id").val($this[0].id);
                  $("#price").val(cells[1].innerText);
                  $("#amount").val("");
                  $("#subtotal").val("");
              }
          });
      }

      function refresh(){
          u();
          c();
          i();
      }

      $(document).ready(function(){
          refresh();
          $("#amount").blur(function(){
              var amount = $("#amount").val(),
                  price = $("#price").val();
              if(amount !== ''){
                  $("#subtotal").val((parseFloat(price) * parseInt(amount)).toFixed(2));
              }
          });
      })
  </script>
  </body>
</html>