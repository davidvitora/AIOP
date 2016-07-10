var myApp = angular.module('myApp',['ngRoute']);

myApp.config(['$routeProvider', 
    function($routeProvider){
        $routeProvider

        .when("/",{
            templateUrl: '/aiop/Faces/Painel/Timeline/Timeline.html',
            controller: 'mainController'

        })
        .when("/membros",{
            templateUrl: '/aiop/Faces/Painel/Membros/Membros.html',
            controller: 'mainController'
        })
        .when("/definitions",{
            templateUrl: '/aiop/Faces/Painel/default/default.html',
            controller: 'mainController'
        })
        
        .when("/assignments",{
            templateUrl: '/aiop/Faces/Painel/default/default.html',
            controller: 'mainController'
        })
        
        .when("/archives",{
            templateUrl: '/aiop/Faces/Painel/default/default.html',
            controller: 'mainController'
        })
        .when("/personalization",{
            templateUrl: '/aiop/Faces/Painel/default/default.html',
            controller: 'mainController'
        })
        .when("/planejamento",{
            templateUrl: '/aiop/Faces/Painel/Planejamento/Planejamento.html',
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
    //AUX
        $scope.courierModal;
    //
        
    $scope.projeto ;
    $scope.membros ;
    $scope.planejamento ;
    $scope.timeline;
    
    //Pre inicialização, obtidos os dados do projeto;
    $http.get('/aiop/app/projetos/acessar').success( function(data){
       $scope.projeto = data;
    }).error( function(data){
        $scope.courierModal = data;
    });
    
    //Pega dados dos membros
    $http.get('/aiop/getMembros').success( function(data){
       $scope.membros = data;
    }).error( function(data){
        console.log(data);
    });
    $http.get('/aiop/getPlanejamento').success( function(data){
       $scope.planejamento = data;
    }).error( function(data){
        console.log(data);
    });
    $http.get('/aiop/getTimeline').success( function(data){
       $scope.timeline= data;
    }).error( function(data){
        console.log(data);
    });
    $scope.acessarConfiguracoes = function(data){
     $http.get('/aiop/Faces/Welcome.jsp').success( function(data){
         window.location.href = "/aiop/Faces/Welcome.jsp";
     }).error( function(data){
         console.log(data);
     });
   };
   
   //Comandos da tela de planejamento adicionar
   $scope.titleNewEvent;
   $scope.descriptionNewEvent;
   $scope.dateNewEvent;
   $scope.statsNewEvent;
   $scope.novoEventoOpen = function(){
     $("#novoEvento").show();
   };
   $scope.novoEventoClose = function(){
     $("#novoEvento").hide();
   };
   $scope.novoEventoCadastrar = function(){
        $http.post("/aiop/setPlanejamento", { title : $scope.titleNewEvent , 
            description : $scope.descriptionNewEvent , 
            date : $scope.dateNewEvent, 
            stats :  $scope.statsNewEvent})
        .success(function(result){
            $http.get('/aiop/getPlanejamento')
                    .success( function(data){
                $scope.planejamento = data;
            }).error( function(data){
                console.log(data);
            });
            $("#novoEvento").hide();
            $http.get('/aiop/getTimeline').success( function(data){
                $scope.timeline= data;
            }).error( function(data){
                console.log(data);
            });
        }).error(function(data, stats){
            console.log(stats);
        });
    };
    //
    $scope.item;
    $scope.modifyDataEventoOpen = function(evento){
        $scope.item = evento;
     $("#mudarData").show();
    };
    $scope.modifyDataEventoClose = function(){
      $("#mudarData").hide();
    };
    $scope.modifyDataEventoAtualizar = function(){
        $http.post("/aiop/setEventoNovaData", { oldObject : $scope.item , 
            newDate : $scope.dateNewEvent})
        .success(function(result){
            $http.get('/aiop/getPlanejamento')
                    .success( function(data){
                $scope.planejamento = data;
            }).error( function(data){
                console.log(data);
            });
            $("#novoEvento").hide();
            $http.get('/aiop/getTimeline').success( function(data){
                $scope.timeline= data;
            }).error( function(data){
                console.log(data);
            });
            $scope.modifyDataEventoClose = function(){
                $("#mudarData").hide();
            };
        }).error(function(data, stats){
            console.log(stats);
        });
    };
    
    
   
}]);



