var myApp = angular.module('myApp',['ngRoute']);

myApp.config(['$routeProvider', 
    function($routeProvider){
        $routeProvider

        .when("/",{
            templateUrl: '/Faces/Painel/Timeline/Timeline.html',
            controller: 'mainController'

        })
        .when("/membros",{
            templateUrl: '/Faces/Painel/Membros/Membros.html',
            controller: 'mainController'
        })
        .when("/definitions",{
            templateUrl: '/Faces/Painel/default/default.html',
            controller: 'mainController'
        })
        
        .when("/assignments",{
            templateUrl: '/Faces/Painel/default/default.html',
            controller: 'mainController'
        })
        
        .when("/arquivos",{
            templateUrl: '/Faces/Painel/Arquivos/Arquivos.html',
            controller: 'mainController'
        })
        .when("/personalization",{
            templateUrl: '/Faces/Painel/default/default.html',
            controller: 'mainController'
        })
        .when("/planejamento",{
            templateUrl: '/Faces/Painel/Planejamento/Planejamento.html',
            controller: 'mainController'
        })
        .otherwise({
            redirectTo: '/'
        });
}]);

myApp.filter('trusted', function($sce) {
    return function(ss) {
        return $sce.trustAsHtml(ss);
    };
});

myApp.controller('mainController', ["$scope", "$timeout", "$http", function($scope, $timeout, $http){
        
    $scope.projeto ;
    
    //Pre inicialização, obtidos os dados do projeto;
    $http.get('/app/projetos/acessar').success( function(data){
       $scope.projeto = data;
    }).error( function(data){
        $scope.courier400Modal = data;
    });
    
    //Referente ao menu para telas pequenas
    $scope.menu_view = false;
    $scope.menu_view_action = function(){
        if($scope.menu_view === false){
            $scope.menu_view = true;
            $(".menu-coluna1").show();
        }else if($scope.menu_view === true){
            $scope.menu_view = false;
            $(".menu-coluna1").hide();
        }
    };
    
    //Botão para sair
    $scope.logOff = function(){
        $http.post('/app/session/logOff')
        .success(function(data){
            $scope.response = data;
            window.location.href = "/index.jsp";
        })
        .error(function(data){

        });
    };
    
    //Botão para a tela HOME
    $scope.view_home = function(){
        window.location.href = "/Faces/Welcome.jsp";
    };
    
    //-----------------------TELA DE MEMBROS------------------------------//
    
    $scope.membros;
    $scope.updateMembros = function(){
        $http.get('/app/membro').success( function(data){
           $scope.membros = data;
        }).error( function(data){
            $scope.courier400Modal = data;
        });
    };
    $scope.updateMembros();
    
    //Procura de membro
    $scope._member_search_text;
    $scope._member_search_result;
    $scope.selected_user_add_member;
    $scope.selected_user_permission;
    $scope.btn_procurar_membro = function(){
        $http.get('/app/membro/' + $scope._member_search_text).success( function(data){
            $scope._member_search_result = data;
        }).error( function(data){
            $scope.courier400Modal = data;
        });
    };
    
    $scope.btn_add_membro = function(){
        $( "#adicionar-membro" ).dialog({
            title: "Adicionar membro",
            modal: true,
            autoOpen: true,
            closeOnEscape: true,
            show: { effect: "fade", duration: 200},
            hide: { effect: "fade", duration: 200}
        });
        $( "#adicionar-membro" ).dialog("open");
    };
    
    $scope.btn_add_member_action = function(){
        $scope.selected_user_add_member = $("#member_add_selection").val();
        $scope.selected_user_permission = $("#member_add_permission").val();
        $http.post("/app/membro/add",
        {id: $scope.selected_user_add_member, permission: $scope.selected_user_permission})
        .success(function(data){
            $( "#adicionar-membro" ).dialog("close");
            $scope.updateMembros();
        })
        .error(function(data){
            $scope.courier400Modal = data;
        });
    };
    
    //-----------------------Tela de arquivo----------------------------//
    $scope.arquivos;
    $scope.updateArquivos = function(){
        $http.get('/app/files').success( function(data){
           $scope.arquivos = data;
        }).error( function(data){
            $scope.courier400Modal = data;
        });
    };
    $scope.upload = function(){
        $http.post('/app/files').success( function(data){
           $scope.arquivos = data;
        }).error( function(data){
            $scope.courier400Modal = data;
        });
    };
    $scope.updateArquivos();
    
    $scope.downloadArquivo = function(chave){
         window.open("/app/files/"+chave);
    };
    
    $scope.uploadArquivo = function(){
         window.open("/Faces/Painel/Arquivos/Upload.html", "", "width=400,height=100");
    };
    
}]);



