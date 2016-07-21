var myApp = angular.module('myApp',['ngRoute']);

myApp.controller('loginController', ["$scope", "$timeout", "$http", function($scope, $timeout, $http){
   $scope.tryLogin = function(){
       $("#loginButton").hide();
       $("#loginLoading").show();
       $http.post('/app/session/login'
       ,{"login": $scope.login, "password" : $scope.password})
       .then(function sucess(data){
           window.location.href = "/Faces/Welcome.jsp";
       },
        function erro(data){
           $("#loginButton").show();
           $("#loginLoading").hide();
           if(data.status === 401){
                $scope.response = data.data;
           }else{
               $scope.response = "Ocorreu um erro ao realizar o login";
           }   
        });
    };
    
   $scope._login = "";
   $scope._password = "";
   $scope._name = "";
   $scope._birthDay = "1000-01-01";
   $scope._contact = "";
   $scope._email = "";
   $scope.retornos;
   
   $( function(){
    $( "#datepicker" ).datepicker({
      changeMonth: true,
      changeYear: true,
      yearRange: "1950:2012"
    });
    $( "#datepicker" ).datepicker( "option", "dateFormat", "yy-mm-dd");
    });
  
  
    
    
    $scope.btn_criar_usuario_text = function(){
        $("#criar-usuario").dialog({
            title: "Criar conta",
            modal: true,
            autoOpen: true,
            closeOnEscape: true,
            show: { effect: "fade", duration: 200},
            hide: { effect: "fade", duration: 200}
        });
    };
    $scope.response;
    $scope.login;
    $scope.password;
    $("#btn_criar_usuario").click(function(){
       $http.post('/app/usuario', {id: "0", login: $scope._login, password: $scope._password, name: $scope._name,  birthDay: $scope._birthDay + "T00:00:00-00:00" , contact: $scope._contact, email: $scope._email, created : "1000-01-01"  })
       .success(function(data){
           $scope.login = $scope._login;
           $scope.password = $scope._password;
           $("#criar-usuario").dialog("close");
           
       })
       .error(function(data){
           $scope.retornos = data;
           $("#Validacao").show();
       });
    });

}]);

myApp.controller('welcomeController', ["$scope", "$timeout", "$http", function($scope, $timeout, $http){
        
        //Mostra a pagina e esconde o carregamento quando a pagina está pronta
        $("#controller").ready( function(){
            $("#controller").show();
            $('#Loading').hide();
        });
        //-----------------------------------
        
        
        //Modal de mensagens
        $scope.mensagemModal;
        $( function(){
             $( "#modal-mensagem" ).dialog({
                title: "Criar projeto",
                modal: true,
                autoOpen: false,
                closeOnEscape: true,
                show: { effect: "fade", duration: 200},
                hide: { effect: "fade", duration: 200}
            });
        });
        //----------------------------------
        
        //Verifica os projetos do usuário no banco
        $scope.updateProjetos = function(){
            $scope.meusProjetos;
            $http.get('/app/projetos/dono')
               .success(function(data){
                   $scope.meusProjetos = data;
                   $('#Loading').hide();
               })
               .error(function(data){

            });
        };
        $scope.updateProjetos();
        
        //Acessa o projeto
        $scope.acessarProjeto = function(projeto){
           $http.post('/app/projetos/acessar', projeto)
           .success(function(data){
               window.location.href = "/Faces/Painel/Master.jsp";
           })
           .error(function(data){
               $scope.mensagemModal = data;
               $( "#modal-mensagem" ).dialog("open");
           });
        };
        
        //Abre o modal de criação de projeto
        $scope.btn_criar_projeto = function(){
            $( "#criar-projeto" ).dialog({
                title: "Criar projeto",
                modal: true,
                autoOpen: true,
                closeOnEscape: true,
                show: { effect: "fade", duration: 200},
                hide: { effect: "fade", duration: 200}
            });
            $( "#criar-projeto" ).dialog("open");
        };
        
        
      
    
        
    
   
   
   

   $scope.modalMensagemClose = function(){
     $("#modalMensagem").hide();
   };
   
   
   
   
    $scope.logOff = function(){
        $http.post('/app/session/logOff')
        .success(function(data){
            $scope.response = data;
            window.location.href = "/index.jsp";
        })
        .error(function(data){

        });
    };
    
    $scope.user;
    
       $http.get('/app/usuario')
       .success(function(data){
           $scope.user = data;
       })
       .error(function(data){
       });
       
    $scope._name = "";
    $scope._description = "";
    $scope.validacao;

    $("#btn_criar_projeto_criar").click(function(){
        $('#Loading').show();
        $http.post('/app/projetos', {id: "0", idOwner: "0", name: $scope._name , description: $scope._description, created: "1000-01-01T00:00:00-00:00"})
        .success(function(data){
            $scope.updateProjetos();
            $('#Loading').hide();
            $("#criar-projeto").dialog("close");         
        })
        .error(function(data){
            $scope.validacao = data;
            $("#Validacao").show();
            $('#Loading').hide();
        });
        
    });


}]);