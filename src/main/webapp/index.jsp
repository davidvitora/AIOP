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
    <script src="/resources/js/bootstrap.min.js"></script>
    <script src="/resources/js/angular.js"></script>
    <script src="https://code.angularjs.org/1.5.5/angular-route.js"></script>
    <script src="/resources/js/preapp.js"></script>
</head>
<body>
    <div class="Logindiv" ng-controller="loginController" >
        <img class="img-responsive center-block" src="/resources/images/Logo.png" />
        <form>
            <input id="login" type="text" ng-model="login" class="formulario-login form-control" placeholder="Login">
            <input id="senha" type="text" ng-model="password" class=" formulario-login form-control" placeholder="Password">
            <button class=" formulario-login btn btn-default"  ng-click="tryLogin()" >Logar</button>
        </form>
        <div>{{ response }}</div>
    </div>
    <footer>
        <div> Não pussui conta? <button type="button" class="btn-txt" data-toggle="modal" data-target="#Modal-cadastro-conta"> Criar conta </button> </div>
        <div> Esqueceu sua senha ? <button class="btn-txt"> Redefinir senha </button> </div>
    </footer>
    
    
    
    <div class="modal fade" id="Modal-cadastro-conta" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
              <h4 class="modal-title" id="myModalLabel">Crie sua conta</h4>
            </div>
            <div class="modal-body">
                <form id="formulario-cadastro-conta">
                    <input type="text" ng-model="_login" class="formulario-login form-control" placeholder="Login de usuário" >
                    <input type="password" ng-model="_password" class=" formulario-login form-control" placeholder="Senha" >
                    <input type="text" ng-model="_name" class="formulario-login form-control" placeholder="Nome" >
                    <input type="text" ng-model="_birthDay" class="formulario-login form-control" placeholder="Data de nascimento" >
                    <input type="text" ng-model="_email" class="formulario-login form-control" placeholder="E-mail" >
                    <input type="text" ng-model="_contact" class="formulario-login form-control" placeholder="Contato" >
                </form>
                <div class="alert alert-danger" ng-repeat="retorno in retornosCadastroUsuario">
                    <div>
                        <div role="alert"></div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
              <button type="button" class="btn btn-primary" >Criar conta</button>
            </div>
          </div>
        </div>
    </div>
    
</body>
</html>
<% } %>

