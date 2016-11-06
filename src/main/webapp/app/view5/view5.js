'use strict';

angular.module('myApp.view5', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/view5', {
    templateUrl: 'app/view5/view5.html',

  })
}])
 .controller('View5Ctrl', function($http,$scope, $location) {
      var self = this;
      self.data = {};
      
  $http.get('api/admin/users')
            .success(function (data, status, headers, config) {
              self.data = data;
            })
            .error(function (data, status, headers, config) {
              $location('/view1');
             });
             
});