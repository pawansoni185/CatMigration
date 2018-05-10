 var myapp = angular.module("myapp", []);

 myapp.controller("MyController1", function($scope){

 $scope.topics=[{id:'1', name:'.net'}, 
              {id:'2', name:'java'}, 
              {id:'3', name:'vb'},
              {id:'4', name:'C'},
              {id:'5', name:'C++'}]; 
});