/**
 * Created by mandeepsingh on 01/07/18.
 */
/*
var thePets = [{
    "name": "cat name",
    "species": "cat",
    "favFood": "palak"
}, {
    "name": "dog name",
    "species": "dog",
    "favFood": "bone"
}, {
    "name": "bat name",
    "species": "bat",
    "favFood": "blood"
}]

var myFavColors = ["blue", "red", "black"];

var ourRequest = new XMLHttpRequest();
ourRequest.open('GET','https://learnwebcode.github.io/json-example/animals-1.json');
ourRequest.onload= function(){
    var ourData = JSON.parse(ourRequest.responseText);
    console.log(ourData[0]);
}*/


var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http) {
    $http({
        method : "GET",
        url : "https://learnwebcode.github.io/json-example/animals-1.json"
    }).then(function mySuccess(response) {
        $scope.myWelcome = JSON.parse(response.data);
    }, function myError(response) {
        $scope.myWelcome = "error";
    });
});
