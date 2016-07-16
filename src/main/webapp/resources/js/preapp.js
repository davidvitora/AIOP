var myApp = angular.module('myApp',['ngRoute']);

myApp.controller('loginController', ["$scope", "$timeout", "$http", function($scope, $timeout, $http){
   $( function() {
    $( "#datepicker" ).datepicker({
      changeMonth: true,
      changeYear: true
    });
    $( "#datepicker" ).datepicker( "option", "dateFormat", "yy-mm-dd");
  } );     
        
   $scope.response;
   $scope.login;
   $scope.password;

   $scope.tryLogin = function(){
       $http.post(
       '/app/session/login'
       ,{"login": $scope.login, "password" : $scope.password})
       .success(function(data){
           window.location.href = "/Faces/Welcome.jsp";
       })
       .error(function(data){
           $scope.response = data;
       });
   };

   $scope._login = "";
   $scope._password = "";
   $scope._name = "";
   $scope._birthDay = "1000-01-01";
   $scope._contact = "";
   $scope._email = "";
   $scope.retornos;
   
   $scope.cadastrarUsuario = function(projeto){
       $http.post('/app/usuario', {id: "0", login: $scope._login, password: $scope._password, name: $scope._name,  birthDay: $scope._birthDay + "T00:00:00-00:00" , contact: $scope._contact, email: $scope._email, created : "1000-01-01"  })
       .success(function(data){
           tryLogin({login: $scope._login, password: $scope._password});
       })
       .error(function(data){
           $scope.retornos = data;    
           $("#Validacao").show();
       });
   };

}]);

myApp.controller('welcomeController', ["$scope", "$timeout", "$http", function($scope, $timeout, $http){
        
      $( function() {
        $( "#criar-projeto" ).dialog({
          autoOpen: true
        });

        $( "#opener" ).on( "click", function() {
          $( "criar-projeto" ).dialog( "open" );
        });
      } );
      
      $scope._nomeProjeto;
      $scope._descricaoProjeto;
      
    //Meus projeto
    $scope.meusProjetos;
    $http.get('/app/projetos/dono')
       .success(function(data){
           $scope.meusProjetos = data;
       })
       .error(function(data){

    });
        
        
    $scope.courierModal;
    $scope.acessarProjeto = function(projeto){
       $http.post('/app/projetos/acessar', projeto)
       .success(function(data){
           window.location.href = "/Faces/Painel/Master.jsp";
       })
       .error(function(data){
           $scope.courierModal = data;
           $("#modalMensagem").show();
       });
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


}]);