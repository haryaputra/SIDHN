<!DOCTYPE html>
<%@ page contentType="text/html;charset=windows-1252" import="java.sql.ResultSet"%>

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

    <link type="text/css" rel="stylesheet" href="resources/css/bootstrap.min.css"/>
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
<div class="container">
    <div class="row clearfix">
    <%@ include file="header.html" %>
        <div class="col-md-8 column">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <div class="jumbotron">
                        <div style="height:60%;width:100%;border:1px;overflow:auto;"> 
                        <jsp:useBean id="ambildata" class="model.DataAccess" scope="session"/>
                        <jsp:useBean id="pageh" class="model.PageHandler" scope="session"/>
                        <%
                        ResultSet rset;
                        int RowNum = 0;
                        int KolNum = 0;
                        String que = request.getParameter("q");
                        String fr = request.getParameter("fr");
                        String rpp = request.getParameter("rpp");
                        if (que != null && fr !=null && rpp!=null){
                            int firstRow = Integer.parseInt(fr);
                            int recordPerPage = Integer.parseInt(rpp);
                            rset = ambildata.getSelQuery(que, firstRow, recordPerPage);
                            KolNum = ambildata.getColumnCount(rset);
                            pageh.setCurrentPage(firstRow, recordPerPage);
                        %>
                            <table id="table-daftar-bank" class="table table-hover">
                                <tr>
                                    <%
                                    int i = 1;
                                    while(i <= KolNum)
                                    {
                                    %>
                                    <td><strong><%= ambildata.getColumnName(rset, i)%></strong></td>
                                    <%
                                    i++;}
                                    %>
                                </tr>
                                    <%
                                    while(rset.next()){
                                    %>
                                <tr>
                                    <%
                                    int j = 1;
                                    while(j <= KolNum){
                                    %>
                                    <td><%= rset.getString(j)%></td>
                                    <%j++;}%>
                                </tr>
                            <%}%>
                        </table>
                        <!--pagination-->
                        <ul class="pagination">
                            <%
                            RowNum = ambildata.getLastRow(ambildata.getQuery(que));
                            int currentPage = pageh.getCurrentPage();
                            int numberOfPage = pageh.getNumberOfPage();
                            %>
                            <%
                             if (currentPage-1 != 0){
                            %>
				<li>
					<a href="#">Prev</a>
				</li>
                            <%}%>
                            <%
                             if (currentPage+1 != numberOfPage){
                            %>
				<li>
					<a href="#">Next</a>
				</li>
                            <%}%>
			</ul>
                        <!--Pindahin buat baru exception-->
                        <%}%>
                            <form action="main.jsp" method="POST">
                            <table>
                            <tr>
                            <td>Query</td> <td><input type="text" name="q"></input>
                            </tr>
                            <tr>
                            <td>First Row</td> <td><input type="text" name="fr"></input>
                            </tr>
                            <tr>
                            <td>Records Per Page</td> <td><input type="text" name="rpp"></input>
                            </tr>
                            </table>
                            <input type="submit" value="Submit" />
                            </form>
                            
                            </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>