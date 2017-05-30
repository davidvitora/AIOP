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
        <script src="/resources/js/jquery-3.0.0.min.js"></script>
        <link rel="stylesheet" href="/resources/css/jquery-ui.css">
        <script src="/resources/js/jquery-ui.js"></script>
        <script src="/resources/js/angular.js"></script>
        <script src="/resources/js/angular-route.js"></script>
        <script src="/resources/js/preapp.js"></script>
    </head>
    <body>
        <img id="Loading"  alt="Loading" src="/resources/gif/load.gif" />
        <div id="controller" style="display: none;" ng-controller="welcomeController">
            <div id="criar-projeto" class="ui-dialog" style="display: none">
                <label > Digite os dados do projeto a ser criado: </label>
                <input type="text" ng-model="_name" class="formulario-login form-control" placeholder="Nome do projeto" >
                <input type="text" ng-model="_description" class=" formulario-login form-control" placeholder="Descrição do projeto" >
                <button id="btn_criar_projeto_criar" class="btn btn-default">Criar</button>
                <div id="Validacao" class="alert alert-danger" >
                    <div>
                        <div role="alert" ng-repeat="valida in validacao.messages"><p>{{ valida }}</p></div>
                    </div>
                </div>
            </div>
            <div id="modal-mensagem" class="ui-dialog" style="display: none;">
                <label> Mensagem do sistema: </label>
                {{ mensagemModal.message }}
            </div>
            <div class="container-fluid">
                    
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
                <div class="row" id="projetos">
                    <div class="col-md-12">
                        <div class="meus-projetos-display-item" ng-repeat="projeto in meusProjetos">
                            <div class="tituloProjeto" ng-click="acessarProjeto(projeto)" >
                                    <img src="/resources/images/icones/projeto.png" class="img-circle img-responsive center-block"/>
                                    <h3  class="tituloProjeto">{{ projeto.name }}</h3>
                                    <img src="/resources/images/icones/iconeNovoProjetoLinha.png" class="center-block"/>
                            </div>
                        </div>
                        <div class="meus-projetos-display-item">
                            <div class="tituloProjeto" ng-click="btn_criar_projeto()" >
                                    <img src="/resources/images/icones/iconeNovoProjeto.png" class="img-circle img-responsive center-block"/>
                                    <h3  class="tituloProjeto">Adicionar novo</h3>
                                    <img src="/resources/images/icones/iconeNovoProjetoLinha.png" class="center-block"/>
                            </div>
                        </div>
                    </div>
                </div>                
            </div>
        </div>
    </body>
</html>
<% } %>
