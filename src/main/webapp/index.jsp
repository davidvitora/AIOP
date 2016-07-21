<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.ejb.EJB"%>
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
<% if(aiopSession.isLoged()){ response.sendRedirect("/Faces/Welcome.jsp");
    }else{%>
    
<html ng-app="myApp">
<head>
    <title>AIOP</title>
    <meta charset="utf-8">
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <link id="Style" href="/resources/css/firstStyle.css" rel="stylesheet"> 
    <script src="https://code.jquery.com/jquery-3.0.0.min.js"></script>
    <link rel="stylesheet" href="/resources/css/jquery-ui.css">
    <script src="/resources/js/jquery-ui.js"></script>
    <script src="/resources/js/bootstrap.min.js"></script>
    <script src="/resources/js/angular.js"></script>
    <script src="https://code.angularjs.org/1.5.5/angular-route.js"></script>
    
    <script src="/resources/js/preapp.js"></script>

</head>
<body ng-controller="loginController">
    <div  class="Logindiv"  >
        <img class="img-responsive center-block" src="/resources/images/Logo.png" />
        <form class="formulario-login">
            <input id="login" type="text" ng-model="login" class="formulario-login form-control" placeholder="Login">
            <input id="senha" type="text" ng-model="password" class=" formulario-login form-control" placeholder="Password">
            <img id="loginLoading"  src="/resources/gif/load.gif" />
            <button id="loginButton" class=" formulario-login btn btn-default"  ng-click="tryLogin()" >Acessar</button>
        </form>
        <div class="formulario-login" >{{ response }}</div>
    </div>
    <footer class="footer">
        <div> Não pussui conta? <button id="btn_criar_usuario_text" ng-click="btn_criar_usuario_text()" type="button" class="btn-txt"> Criar conta </button> </div>
        <div> Esqueceu sua senha ? <button class="btn-txt"> Redefinir senha </button> </div>
    </footer>
    
    <div id="criar-usuario" class="ui-dialog" style="display: none;">
        <form id="formulario-cadastro-conta">
            <input type="text" ng-model="_login" class="formulario-login form-control" placeholder="Login de usuário" >
            <input type="password" ng-model="_password" class=" formulario-login form-control" placeholder="Senha" >
            <input type="text" ng-model="_name" class="formulario-login form-control" placeholder="Nome" >
            <input id="datepicker" type="text" ng-model="_birthDay" class="formulario-login form-control" placeholder="Data de nascimento" >
            <input type="text" ng-model="_email" class="formulario-login form-control" placeholder="E-mail" >
            <input type="text" ng-model="_contact" class="formulario-login form-control" placeholder="Contato" >
        </form>
        <div id="Validacao" class="alert alert-danger" >
            <div>
                <div role="alert" ng-repeat="retorno in retornos.messages"><p>{{ retorno }}</p></div>
            </div>
        </div>
        <button id="btn_criar_usuario">Criar usuário</button>
    </div>
</body>
</html>
<% } %>

