<%--
  Created by IntelliJ IDEA.
  User: mandeepsingh
  Date: 06/07/18
  Time: 6:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<body>

<div ng-app="myApp" ng-controller="myCtrl">

    <p>Today's welcome message is:</p>

    <h1>{{myWelcome}}</h1>
    <p>{{myWelcome[0]}}</p>
    <p>{{myWelcome[1]}}</p>
    <p>{{myWelcome[2]}}</p>

</div>

<p>The $http service requests a page on the server, and the response is set as the value of the "myWelcome" variable.</p>

<script >
    var app = angular.module('myApp', []);
    app.controller('myCtrl', function($scope, $http) {
        $http({
            method : "GET",
            url : "https://learnwebcode.github.io/json-example/animals-1.json"
        }).then(function mySuccess(response) {
            $scope.myWelcome = response.data;
        }, function myError(response) {
            $scope.myWelcome = "error";
        });
    });

</script>

</body>
</html>

