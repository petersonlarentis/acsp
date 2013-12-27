'use strict';

var app = angular.module("app", ['ngRoute', 'restangular', 'ui.bootstrap']);

app.config( function($routeProvider, RestangularProvider){
    RestangularProvider.setBaseUrl('/acsp/');
});

app.controller('GenericController', function($scope, $http, $modal, Restangular){

    $scope.orderProp = 'certificadoMatricula';
    $scope.entityType =  null;//type will be aeronave, for example
    $scope.entitiesType = null;//type will be aeronaves, for example

    $scope.setEntitiesType = function(entitiesType){
        $scope.entitiesType = entitiesType;
    }

    $scope.setEntityType = function(entityType){
        $scope.entityType = entityType;
    }

    $scope.list = function(){
        $http.get('/acsp/' + $scope.entitiesType).success(function(data) {
            $scope.entities = data;
        });
    }

    $scope.disable = function(id){
        Restangular.one($scope.entityType, id).remove();
    }

    //TODO: create directive
    $scope.style = function(stat){
        if(stat) {
            return "fa fa-check-circle-o fa-lg";
        }{
            return "fa fa-circle-o fa-lg";
        }
    };

    $scope.newEntity = function () {
        $scope.entity = null;
        var modalInstance = $modal.open({
            templateUrl: 'myModalContent.html',
            controller: ModalInstanceCtrl,
            resolve: {
                entity: function () {
                    return $scope.entity;
                },
                save: function(){
                    return $scope.save;
                },
                entityType: function(){
                    return $scope.entityType;
                }
            }
        });

        modalInstance.result.then(function (entity) {
            $scope.list();
        }, function () {
            $scope.entity = null;
        });
    };

    $scope.load = function (id) {

        Restangular.one($scope.entityType, id).get().then(function(entity) {
            $scope.entity = entity;

            var modalInstance = $modal.open({
                templateUrl: 'myModalContent.html',
                controller: ModalInstanceCtrl,
                resolve: {
                    entity: function () {
                        return entity;
                    },
                    save: function(){
                        return $scope.save;
                    },
                    entityType: function(){
                        return $scope.entityType;
                    }
                }
            });

            modalInstance.result.then(function (entity) {//When the modal closes with SAVE
                $scope.list();
            }, function () {//on dismiss
                $scope.entity = null;
            });
        });
    };

    var ModalInstanceCtrl = function ($scope, $modalInstance, entity, entityType) {

        $scope.entity = entity;

        $scope.saveAndClose = function (anEntity) {
            $scope.entity = anEntity;
            Restangular.all(entityType).post(entityType, anEntity).then(function(){
                $modalInstance.close(anEntity);
            }, function(){
                console.log("There was an error saving " + anEntity);
            });
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    };

});

app.controller('InstrutoresController', function($scope, $http, $modal, Restangular){

    $scope.list = function(){
        $http.get('/acsp/instrutores').success(function(data) {
            $scope.instrutores = data;
        });
    }

    $scope.save = function(){
        Restangular.all('instrutor').post("instrutor", $scope.instrutor).then(function(){
            console.log("Object saved OK");
        }, function(){
            console.log("There was an error saving");
        });
        $scope.list();
    };

    //TODO: create directive
    $scope.style = function(stat){
        if(stat) {
            return "fa fa-check-circle-o fa-lg";
        }{
            return "fa fa-circle-o fa-lg";
        }
    };

    $scope.load = function(id){
        Restangular.one("instrutor", id).get().then(function(instrutor){
            $scope.instrutor = instrutor;
        });
    };

    $scope.novoInstrutor = function () {
        $scope.instrutor = null;
    };

});

app.controller('SociosController', function($scope, $http, $modal, Restangular){

    $scope.orderProp = 'nome';


    $scope.list = function(){
        $http.get('/acsp/socios').success(function(data) {
            $scope.socios = data;
        });
    }

    $scope.save = function(){
        Restangular.all('socio').post("socio", $scope.socio).then(function(){
            console.log("Object saved OK");
        }, function(){
            console.log("There was an error saving");
        });
        $scope.list();
    };

    $scope.disable = function(id){
        console.log("Disabling " + id);
        Restangular.one("socio", id).remove();
    };

    $scope.load = function(id){
        Restangular.one("socio", id).get().then(function(socio) {
            $scope.socio = socio;
        });

    };

    //TODO: create directive
    $scope.style = function(stat){
        if(stat) {
            return "fa fa-check-circle-o fa-lg";
        }{
            return "fa fa-circle-o fa-lg";
        }
    };

    $scope.novoSocio = function () {
        $scope.socio = null;
    };
});

app.controller('AtendentesController', function($scope, $http, $modal, Restangular){

    $scope.orderProp = 'nome';

    $scope.list = function(){
        $http.get('/acsp/atendentes').success(function(data) {
            $scope.atendentes = data;
        });
    }

    $scope.save = function(){
        Restangular.all('atendente').post("atendente", $scope.atendente).then(function(){
            console.log("Object saved OK");
            angular.element("body").clearAttributes();
            $scope.validation = null;
        }, function(resp){
            console.log("There was an error saving" + resp);
            $scope.$broadcast('click.dismiss.modal');
            $scope.validation = resp;
        });
        $scope.list();
    };

    $scope.disable = function(id){
        console.log("Disabling " + id);
        Restangular.one("atendente", id).remove();
        $scope.validation = null;
    };

    $scope.load = function(id){
        Restangular.one("atendente", id).get().then(function(atendente) {
            $scope.atendente = atendente;
            $scope.validation = null;
        });

    };

    //TODO: create directive
    $scope.style = function(stat){
        if(stat) {
            return "fa fa-check-circle-o fa-lg";
        }{
            return "fa fa-circle-o fa-lg";
        }
    };

    $scope.novoAtendente = function () {
        $scope.atendente = null;
    };
});

app.controller('AulasController', function($scope, $http, $modal, Restangular){

    $scope.orderProp = 'materia';

    $scope.list = function(){
        $http.get('/acsp/aulas').success(function(data) {
            $scope.aulas = data;
        });
    }

    $scope.save = function(){
        Restangular.all('aula').post("aula", $scope.aula).then(function(){
            console.log("Object saved OK");
        }, function(){
            console.log("There was an error saving");
        });
        $scope.list();
    };

    $scope.disable = function(id){
        console.log("Disabling " + id);
        Restangular.one("aula", id).remove();
    };

    $scope.load = function(id){
        Restangular.one("aula", id).get().then(function(aula) {
            $scope.aula = aula;
        });

    };

    //TODO: create directive
    $scope.style = function(stat){
        if(stat) {
            return "fa fa-check-circle-o fa-lg";
        }{
            return "fa fa-circle-o fa-lg";
        }
    };

    $scope.novaAula = function () {
        $scope.aula = null;
    };

});
