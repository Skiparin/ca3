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
                            });
                };

                self.search = "";
                self.data = "hey";

                self.getData = function getData() {
                    $http({url: 'http://cvrapi.dk/api?search=' + self.search + '&country=dk',
                        skipAuthorization: true,
                        method: 'GET'
                    })
                            .success(function (data, status, headers, config) {
                                self.data = data;
                                console.log(data);
                            })
                            .error(function (data, status, headers, config) {
                                console.log("nope");
                            })
                };
                self.user = {userName: "user"}
                self.delete = function () {
                    console.log(self.user.userName + " nononono");
                    $http.delete('api/admin/user/user', self.user)
                            .success(function (data, status, headers, config) {
                                console.log(("get offf me turf"));
                            })
                            .error(function (data, status, headers, config) {
                                console.log("an errror occurred pleaase try again");
                            });
                };
            }]);