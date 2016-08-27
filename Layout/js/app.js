var myApp = angular.module('myApp',['ngRoute']);
myApp.config(['$routeProvider', 
    function($routeProvider){
        $routeProvider

        .when("/",{
            templateUrl: '',
            controller: 'mainController'

        })
        .when("/membros",{
            templateUrl: 'members.html',
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
    $scope.menu_view = false;
    $scope.menu_view_action = function(){
        if($scope.menu_view = false){
            $scope.menu_view = true;
            $(".menu-coluna1").show();
        }else if($scope.menu_view = true){
            $scope.menu_view = false;
            $(".menu-coluna1").hide();
        }
    };
}]);



