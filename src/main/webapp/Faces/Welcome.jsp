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
        <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
        <link id="Style" href="/resources/css/firstStyle.css" rel="stylesheet">
        <script src="https://code.jquery.com/jquery-3.0.0.min.js"></script>
        <link rel="stylesheet" href="/resources/css/jquery-ui.css">
        <script src="/resources/js/jquery-ui.js"></script>
        <script src="/resources/js/angular.js"></script>
        <script src="https://code.angularjs.org/1.5.5/angular-route.js"></script>
        <script src="/resources/js/preapp.js"></script>
    </head>
    <body>
        <div id="criar-projeto" title="Criar Projeto">
            <label> Digite os dados do projeto a ser criado: </label>
            <input type="text" ng-model="_nomeProjeto" class="formulario-login form-control" placeholder="Nome do projeto" >
            <input type="password" ng-model="_descricaoProjeto" class=" formulario-login form-control" placeholder="Descrição do projeto" >
            <button class="btn btn-primary">Criar</button>
        </div>
        <div class="container-fluid" ng-controller="welcomeController">
                <div id="modalMensagem" class="modalPadrao" style="display: none;">
                    <div>
                    <button type="button" class="close"  ng-click="modalMensagemClose()">×</button>
                    {{ courierModal.message }}
                    </div>
                </div>
                <div style="text-align: center;" class="row">
                    {{ user.name }}
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <button class="btn btn-default pull-right botao">Notificações</button>
                        <button class="btn btn-default pull-right botao">Configurações</button>
                        <button class="btn btn-default pull-right botao" ng-click="logOff()" >Sair</button>
                    </div>
                </div>
                <div id="projetos">
                    <div class="row" ng-repeat="projeto in meusProjetos">
                        <div class="col-md-3 col-md-push-2">
                            <div class="tituloProjeto" ng-click="acessarProjeto(projeto)" >
                                    <img src="/resources/images/icones/projeto.png" class="img-circle img-responsive center-block"/>
                                    <h3  class="tituloProjeto">{{ projeto.name }}</h3>
                                    <img src="/resources/images/icones/iconeNovoProjetoLinha.png" class="center-block"/>
                            </div>
                        </div>
                    </div>
                </div>
        </div>
    </body>
</html>
<% } %>
