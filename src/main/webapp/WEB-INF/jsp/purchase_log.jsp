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
            <li><a href="<%=request.getContextPath()%>/g/index" title="货物清单">货物清单</a></li>
            <li class="pure-menu-selected"><a href="#" title="交易记录">交易记录</a></li>
            <li><a href="#">Sign Up</a></li>
        </ul>
    </div>
</div>

<div class="content-wrapper">
    <div class="content">
        <h2 class="content-head is-center">Genius only means hard-working all one's life.</h2>
        <div class="pure-g">
            <div class="l-box-lrg pure-u-1-1">
                <h4>交易记录</h4>
                <table class="pure-table pure-table-bordered" id="purchase-list">
                    <thead><tr><th>名称</th><th>单价(元)</th><th>数量</th><th>总额(元)</th><th>时间</th></tr></thead>
                </table>
            </div>
        </div>
        <div class="pure-g">
            <div class="l-box-lrg pure-u-1-1">
                <h4>交易记录</h4>
                <table class="pure-table pure-table-bordered" id="trade-list">
                    <thead><tr><th>食堂</th><th>名称</th><th>单价(元)</th><th>数量</th><th>总额(元)</th><th>时间</th></tr></thead>
                </table>
            </div>
        </div>
    </div>
    <%--<div class="footer l-box is-center">
        View the source of this layout to learn more. Made with love by the YUI Team.
    </div>--%>
</div>

<script type="text/javascript">
    function g(){
        $.get("<%=request.getContextPath()%>/p/get")
                .done(function(data) {
                    $("#purchase-list tbody tr").remove();
                    var html = "";
                    $.each(data, function(i, e){
                        html += "<tr id='" + e.id + "'>"
                                + "<td>" + e.gid + "</td>"
                                + "<td>" + e.price + "</td>"
                                + "<td>" + e.amount + "</td>"
                                + "<td>" + e.subtotal + "</td>"
                                + "<td>" + e.daytime + "</td></tr>";
                    });
                    $("#purchase-list").append(html);
                });
    }

    function u(){
        $.get("<%=request.getContextPath()%>/p/getT")
                .done(function(data) {
                    $("#trade-list tbody tr").remove();
                    var html = "";
                    $.each(data, function(i, e){
                        html += "<tr id='" + e.id + "'>"
                                + "<td>" + e.uid + "</td>"
                                + "<td>" + e.gid + "</td>"
                                + "<td>" + e.price + "</td>"
                                + "<td>" + e.amount + "</td>"
                                + "<td>" + e.subtotal + "</td>"
                                + "<td>" + e.daytime + "</td></tr>";
                    });
                    $("#trade-list").append(html);
                });
    }

    function refresh(){
        g();
        u();
    }

    $(document).ready(function(){
        refresh();
        /*$("#amount, #price").blur(function(){
            var amount = $("#amount").val(), price = $("#price").val();
            if(/^\d{1,8}$/.test(amount)
                    && /^\d{1,10}(\.\d{1,2})?$/.test(price)){
                $("#subtotal").val((parseInt(amount) * parseFloat(price)).toFixed(2));
            }
        })*/
    })
</script>
</body>
</html>