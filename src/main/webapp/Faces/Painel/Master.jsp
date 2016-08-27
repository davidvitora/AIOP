<%@page import="javax.ejb.EJB"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="br.com.aiop.session.AIOPSession"%>
<%!

@EJB
AIOPSession aiopSession;

public void jspInit() {

    if (aiopSession == null){

        try {
            aiopSession = (AIOPSession) new InitialContext().lookup("java:module/AIOPSession");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

%>
<% if(aiopSession.isLoged() == false){ response.sendRedirect("/index.jsp");
    }else{%>
<!DOCTYPE html>
<html lang="en" ng-app="myApp">
<head>
	<title>AIOP</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="/resources/css/style.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.0.0.min.js"></script>
        
    <script src="/resources/js/angular.js"></script>
    <script src="https://code.angularjs.org/1.5.5/angular-route.js"></script>
    <script src="https://code.angularjs.org/1.5.5/angular-sanitize.js"></script>
    <script src="https://code.jquery.com/jquery-3.0.0.min.js"></script>
    <link rel="stylesheet" href="/resources/css/jquery-ui.css">
    <script src="/resources/js/jquery-ui.js"></script>
    <script src="/resources/js/app.js"></script>
    

</head>
<body ng-controller="mainController">
<div class="top">
	<div class="top-menu-iten">João da silva</div>
	<img id="menu" class="icon-top-menu"  ng-click="menu_view_action()"  src="/resources/icons/icon.svg"/>
	<button class="top-menu-iten" ng-click="logOff()"> Sair </button>
        <button class="top-menu-iten" ng-click="view_home()"> Home </button>
	<img class="icon-top"  alt="notificações" src="/resources/icons/iconnotification.svg"/>
</div>
<div class="coluna3 col-2">
</div>
<div class="coluna2 col-8">
	<div class="row">
		<div ng-view></div>
	</div >
</div>
<div class="menu-coluna1 col-2">
	<div class="menu-itens">
		<div id="perfil">
			<div>
				<img src="/resources/images/perfil.jpg" class="img-reponsiva img-circular"/>
			</div>
			<p style="text-align:center;">{{ projeto.name }}</p>
		</div>
		<div class="menu-opcoes">
		  <div class="menu-block"><a class="menu-block" href="#/membros">HOME</a></div>
		  <div class="menu-block"><a class="menu-block" href="#membros">MEMBROS</a></div>
                  <div class="menu-block"><a class="menu-block" href="planning.html">PLANEJAMENTO</a></div>
		  <div class="menu-block"><a class="menu-block" href="assignments.html">TAREFAS</a></div>
		  <div class="menu-block"><a class="menu-block" href="#/arquivos">ARQUIVOS</a></div>
		</div>
	</div>
</div>
</body>
</html>
<% } %>
