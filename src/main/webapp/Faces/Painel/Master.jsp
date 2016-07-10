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
<% if(aiopSession.isLoged() == false){ response.sendRedirect("/aiop/index.jsp");
    }else{%>
<!DOCTYPE html>
<html lang="en" ng-app="myApp">
    <head>
        <title>AIOP</title>
        <meta charset="utf-8">
        <link href="/aiop/resources/css/bootstrap.min.css" rel="stylesheet">
        <link id="Style" href="/aiop/resources/css/style.css" rel="stylesheet">
        <script src="https://code.jquery.com/jquery-3.0.0.min.js"></script>
        <script src="/aiop/resources/js/angular.js"></script>
        <script src="https://code.angularjs.org/1.5.5/angular-route.js"></script>
        <script src="https://code.angularjs.org/1.5.5/angular-sanitize.js"></script>
        <script src="/aiop/resources/js/app.js"></script>
        <jsp:useBean id="user" class="br.com.aiop.persistencia.entidades.User" scope="session">
        </jsp:useBean>
    </head>
    <body>
        <div class="container-fluid" ng-controller="mainController" >
            <div class="row">
                <div class="painelEsquerdo col-md-2 " id="menu">
                    <div class="esquerda" >
                                <div id="perfil">
                                        <div id="fotoPerfil">
                                                <img src="/aiop/resources/images/icones/projeto.png"  class="img-circle img-responsive center-block"/>
                                        </div>
                                    <h4> {{ projeto.name }} </h4>
                                        <p style="text-align:center;"> </p>
                                        
                                </div>
                                <ul class="nav nav-pills nav-stacked itemMenu">
                                  <li><a class="itemMenu" href="#/">HOME</a></li>
                                  <li><a class="itemMenu" href="#/membros">MEMBROS</a></li>
                                  <li><a class="itemMenu" href="#/definitions">DEFINIÇÕES</a></li>
                                  <li><a class="itemMenu" href="#/assignments">TAREFAS</a></li>
                                  <li><a class="itemMenu" href="#/archives">ARQUIVOS</a></li>
                                  <li><a class="itemMenu" href="#/personalization">PERSONALIZAÇÃO</a></li>
                                  <li><a class="itemMenu" href="#/planejamento">PLANEJAMENTO</a></li>
                                </ul>
                        </div>
                </div>
                <div class="col-md-8">
                    <div ng-view></div>
                </div>
                <div class="col-md-2">
                        <div class="btn-group" id="botoesDireita">
                          <button type="button" class="btn btn-default">Notificações</button>
                          <button type="button" class="btn btn-default" ng-click="acessarConfiguracoes()" >Configurações</button>
                        </div>
                </div>
        </div>
    </div>
    </body>
</html>
<% } %>
