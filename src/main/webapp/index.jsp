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
              <input type="button" onclick="g()" value=" get() " />
              <button class="pure-button" onclick="add()"> add() </button>
              <a href="g/i/1"> getUser(1) </a>
          </div>
          <div class="pure-g">
              <div class="l-box-lrg pure-u-1 pure-u-med-2-5">
                  <form class="pure-form pure-form-stacked">
                      <fieldset>
                          <label for="email">Your Name</label>
                          <input id="email" type="text" placeholder="Your Name">

                          <label for="password">Your Password</label>
                          <input id="password" type="password" placeholder="Your Password">

                          <button type="submit" class="pure-button">Sign Up</button>
                      </fieldset>
                  </form>
              </div>
              <div class="l-box-lrg pure-u-1 pure-u-med-3-5">
                  <h4>Contact Us</h4>
                  <p>
                      Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                      tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                      quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
                      consequat.
                  </p>
                  <h4>More Information</h4>
                  <p>
                      Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                      tempor incididunt ut labore et dolore magna aliqua.
                  </p>
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
                      console.log(data.id);
                  });
      }

      function add(){
          $.ajax({
              type: "POST",
              url: "g/add",
              dataType: "json",
              contentType:'application/json',
              data: JSON.stringify({"id": "1", "name": "狄安娜", "model": "本科", "type": "女生"})
          }).done(function(result) {
                      console.log(result);
                  })
                  .error(function(){
                      console.log("something wrong with ajax");
                  });
      }

      $(document).ready(function(){

      })
  </script>
  </body>
</html>