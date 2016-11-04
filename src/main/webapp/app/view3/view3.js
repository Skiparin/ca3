'use strict';

angular.module('myApp.view3', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view3', {
                    templateUrl: 'app/view3/view3.html',
                    controller: 'View3Ctrl'
                });
            }])

<<<<<<< HEAD
        .controller('View3Ctrl', function ($http, $scope) {
            var self = this;
            $http.get('api/user')
                    .success(function (data, status, headers, config) {
                        $scope.data = data;
                    })
                    .error(function (data, status, headers, config) {

                    })

                    $scope.data = "data";

            self.getData = function getData() {
                $http.get('http://cvrapi.dk/api?search=Hej&country=dk')
                        .success(function (data, status, headers, config) {
                            $scope.data = data;
                            console.log(data);
                        })
                        .error(function (data, status, headers, config) {
                            console.log("nope");
                        })
            };
        })
=======
.controller('View3Ctrl', function($http,$scope, $location) {
  $http.get('api/user')
            .success(function (data, status, headers, config) {
              $scope.data = data;
            })
            .error(function (data, status, headers, config) {
              $location('/view1');
             });
});
function getData() {
  $http.get('http://cvrapi.dk/api?vat=3167%208021&country=dk')
            .success(function (data, status, headers, config) {
              $scope.data = data;
              console.log(data[0])
            })
            .error(function (data, status, headers, config) {
              console.log("nope")
             })
};
>>>>>>> 018e7a8ebbe72a9bd06ca34ed8bee3184433141b
