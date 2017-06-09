(function() {
	var app = angular.module("user", []);
	
	app.directive("user", function(){
		return {
			restrict: 'E',
			templateUrl: "user.html",
			controller: 
				function($http) {
				var self = this;
				
				self.users = [];
				self.user = null;
				
				self.list = function() {
					$http({
						method : 'GET',
						url : 'services/user/'
					}).then(function successCallback(response) {
						self.users = response.data;
					}, function errorCallback(response) {

					});
				};
				
				self.add = function() {
					self.user={};
					console.log("Ajout");
					
					$http({
						method : 'GET',
						url : 'services/user/'
					}).then(function successCallback(response) {
						self.users = response.data;
						console.log(self.users);
					}, function errorCallback(response) {

					});
					
					$http({
						method : 'GET',
						url : 'services/types/'
					}).then(function successCallback(response) {
						self.types = response.data;
						console.log(self.types);
					}, function errorCallback(response) {

					});
				}; 
				
				self.save = function() {
					if(self.user.id) {
						$http({
							method : 'PUT',
							url : 'services/user/' + self.user.id,
							data : self.user
						}).then(function successCallback(response) {
							self.list();
							self.user=null;
						}, function errorCallback(response) {
			
						});
					} else {
						$http({
							method : 'POST',
							url : 'services/user/',
							data : self.user
						}).then(function successCallback(response) {
							self.list();
							self.user=null;
						}, function errorCallback(response) {
			
						});
					}
					
					
				}; 
				
				self.edit = function(id) {
					console.log("edition");
					
					$http({
						method : 'GET',
						url : 'services/user/' + id 
					}).then(function successCallback(response) {
						self.user = response.data;
						
					}, function errorCallback(response) {

					});
					
					$http({
						method : 'GET',
						url : 'services/types/'
					}).then(function successCallback(response) {
						self.types = response.data;
						console.log(self.types);
					}, function errorCallback(response) {

					});
					
					$http({
						method : 'GET',
						url : 'services/user/'
					}).then(function successCallback(response) {
						self.users = response.data;
						console.log(self.users);
					}, function errorCallback(response) {

					});
					
				}; 
				
				self.remove = function(id) {
					$http({
						method : 'DELETE',
						url : 'services/user/' + id
					}).then(function successCallback(response) {
						self.list();
					}, function errorCallback(response) {

					});
				}; 
				
				self.list();
			}
				,
			controllerAs: 'userCtrl'
		};
	});
})(); 