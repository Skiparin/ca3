'use strict';

angular.module('myApp.view4', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view4', {
                    templateUrl: 'app/view4/view4.html',
                    controller: 'View4Ctrl'
                })
            }])
        .controller('View4Ctrl', function ($http, $scope) {
            var self = this;
            $http.get('api/user')
                    .success(function (data, status, headers, config) {
                        $scope.data = data;
                    })
                    .error(function (data, status, headers, config) {

                    })
            
            self.data = [];

            self.getCur = function () {
                $http.get('api/demoall/rate')
                        .success(function (data, status, headers, config) {
                            self.data = data;
                            console.log(data);
                        })
                        .error(function (data, status, headers, config) {
                            console.log("doesn't worrrrkkkkkingggsssss");
                        });
            };
            
            self.data
            
            self.testArray = [{desc: "dkk", rate: 1}, {desc: "euro", rate: 7.44}]
            self.from;
            self.to;
            self.amount;

            self.calc = function () {
                self.result = self.from / self.to * self.amount;
                self.result = self.result.toFixed(2);
            }
        });
