'use strict';

// Declare app level module which depends on views, and components
angular.module('myApp', [
    'ngRoute',
    'ngAnimate',
    'angular-jwt',
    'ui.bootstrap',
    'myApp.security',
    'myApp.view1',
    'myApp.view2',
    'myApp.view3',
    'myApp.view4',
    'myApp.view5',
    'myApp.view6',
    'myApp.filters',
    'myApp.directives',
    'myApp.factories',
    'myApp.services'
]).
        config(['$routeProvider', function ($routeProvider) {
                $routeProvider.otherwise({redirectTo: '/view1'});
            }]).
        config(function ($httpProvider) {
            $httpProvider.interceptors.push('AuthInterceptor');
        }).
        controller("myCtrl", ["$http", "$scope", function ($http, $scope) {
                var self = this;
                self.user = {userName: "",
                    passwordHash: ""}

                self.create = function () {
                    console.log(self.user.userName + self.user.passwordHash + "fix plx");
                    $http.post('api/demoall/user', self.user)
                            .success(function (data, status, headers, config) {
                                console.log("Workingggggggggg");
                            })
                            .error(function (data, status, headers, config) {
                                console.log("doesn't worrrrkkkkkingggsssss");
                            })
                };
                self.showpu = false;
                self.unit;
                self.unitKeys = [];
                self.unitArray = [];
                self.search = "";
                self.data = {};
                self.keys = [];
                self.array = Object.keys(self.data).map(function (key){ return self.data[key];});
                
                self.testArray = [{desc:"dkk",rate:1},{desc:"euro",rate:7.44}]   
                self.from;
                self.to;
                self.amount;
                
                self.calc = function(){
                    self.result = self.from/self.to*self.amount;
                    self.result = self.result.toFixed(2);
                } 
                
                self.showPU = function(nr){
                    console.log(nr);
                    self.showpu = true;
                    self.unit = nr;
                    self.unitKeys = Object.keys(self.data.productionunits[nr])
                    self.unitArray = Object.keys(self.data.productionunits[nr]).map(function (key){ return self.data.productionunits[nr][key];});
                    console.log(self.unitKeys)
                    console.log(self.data.productionunits)
                }
                
                self.return = function(){
                    self.showpu = false;
                }
                
                self.getData = function getData() {
                    $http({url: 'http://cvrapi.dk/api?search=' + self.search + '&country=dk',
                        skipAuthorization: true,
                        method: 'GET'
                    })
                            .success(function (data, status, headers, config) {
                                self.data = data;
                                self.keys = Object.keys(data);
                                self.array = Object.keys(self.data).map(function (key){ return self.data[key];});
                                console.log(data);
                            })
                            .error(function (data, status, headers, config) {
                                console.log("nope");
                            })
                };

            }]);


