<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" import="java.sql.ResultSet"%>

<html lang="en">

<!-- kepala coy -->
<head>
  <meta http-equiv="Content-Type" content="text/html"; charset="utf-8">
  <title>SIDHN</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">

    <!--link rel="stylesheet/less" href="resources/less/bootstrap.less" type="text/css" /-->
    <!--link rel="stylesheet/less" href="resources/less/responsive.less" type="text/css" /-->
    <!--script src="resources/js/less-1.3.3.min.js"></script-->
    <!--append ‘#!watch’ to the browser URL, then refresh the page. -->

    <link type="text/css" rel="stylesheet" href="resources/css/bootstrap.css"/>
    <link type="text/css" rel="stylesheet" href="resources/css/style.css"/>

  <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
  <!--[if lt IE 9]>
    <script src="resources/js/html5shiv.js"></script>
  <![endif]-->

  <!-- Fav and touch icons -->
  <link rel="apple-touch-icon-precomposed" sizes="144x144" href="resources/img/apple-touch-icon-144-precomposed.png">
  <link rel="apple-touch-icon-precomposed" sizes="114x114" href="resources/img/apple-touch-icon-114-precomposed.png">
  <link rel="apple-touch-icon-precomposed" sizes="72x72" href="resources/img/apple-touch-icon-72-precomposed.png">
  <link rel="apple-touch-icon-precomposed" href="resources/img/apple-touch-icon-57-precomposed.png">
  <link rel="shortcut icon" href="resources/img/favicon.png">
  
    <script type="text/javascript" src="resources/js/jquery.min.js"></script>
    <script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="resources/js/scripts.js"></script>
</head>

    <body>
        <jsp:useBean id="alert" class="controller.Login_c" scope="session"/>
        <%
        String username = request.getParameter("user");
        String password = request.getParameter("pwd");
        %>
        <div class="row clearfix">
        <div class="page-header">
                <center>
                <h1>
                    Sistem Informasi Daftar Hitam Nasional <small>Bank Indonesia</small>
                </h1>
                </center>
            </div>
        </div>
        <div class="row clearfix">
            <div class="col-md-4 column">
                &nbsp;
            </div>
            <div class="col-md-4 column">
            
                <div class="jumbotron">
                    <%
                      if (username!= null && password!=null){
                    %>
                     <div class="alertbox">
                      <center>
                      <p class="alertboxp">alert! : <%= alert.getAlert(username,password)%> </p>
                    </center>
                    </div>
                    <%
                    }
                    %>
                    <form action="login.jsp" method="POST">
                        <table>
                    <tr>
                        <td>Username</td> <td><input type="text" name="user"/></td>
                    </tr>
                    <tr>
                        <td>Password</td> <td><input type="password" name="pwd"/></td>
                    </tr>
                </table>
                <input type="submit" value="Login" />
            </form>
            </div>
             </div>
            <div class="col-md-4 column">
                &nbsp;
            </div>
        </div>
    </body>
</html>