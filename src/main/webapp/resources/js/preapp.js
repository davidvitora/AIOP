var myApp = angular.module('myApp',['ngRoute']);

myApp.controller('loginController', ["$scope", "$timeout", "$http", function($scope, $timeout, $http){
   $scope.response;
   $scope.login;
   $scope.password;

   $scope.tryLogin = function(){
       $http.post(
       '/app/session/login'
       ,{"login": $scope.login, "password" : $scope.password})
       .success(function(data){
           window.location.href = "/aiop/Faces/Welcome.jsp";
       })
       .error(function(data){
           $scope.response = data;
       });
   };

   $scope._login;
   $scope._password;
   $scope._name;
   $scope._birthDay;
   $scope._contact;
   $scope._email;
   $scope.retorno;
   
   $scope.cadastrarUsuario = function(projeto){
       $http.post('/app/usuario', {login: _login, password: _password, name: _name, birthDay: _birthDay, contact: _contact, email: _email  })
       .success(function(data){
           tryLogin();
       })
       .error(function(data){
           $scope.retorno = data;
       });
   };

}]);

myApp.controller('welcomeController', ["$scope", "$timeout", "$http", function($scope, $timeout, $http){
        
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
           window.location.href = "/aiop/Faces/Painel/Master.jsp";
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
            window.location.href = "/aiop/index.jsp";
        })
        .error(function(data){

        });
    };

}]);