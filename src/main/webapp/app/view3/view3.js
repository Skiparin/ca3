'use strict';

angular.module('myApp.view3', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view3', {
                    templateUrl: 'app/view3/view3.html',
                    controller: 'View3Ctrl'
                });
            }])

        .controller('View3Ctrl', function ($http, $scope) {
            var self = this;
            $http.get('http://cvrapi.dk/api?search=Hej&country=dk')
                    .success(function (data, status, headers, config) {
                        $scope.data = data;
                    })
                    .error(function (data, status, headers, config) {

                    });

            $scope.data = "data";

//            self.getData = function getData() {
//                $http.get('http://cvrapi.dk/api?search=Hej&country=dk')
//                        .success(function (data, status, headers, config) {
//                            $scope.data = data;
//                            console.log(data);
//                        })
//                        .error(function (data, status, headers, config) {
//                            console.log("nope");
//                        })
//            };
        })
