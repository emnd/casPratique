(function() {
	var app = angular.module("categorie", []);
	
	app.directive("categorie", function(){
		return {
			restrict: 'E',
			templateUrl: "categorie.html",
			controller: 
				function($http) {
				var self = this;
				
				self.categories = [];
				self.categorie = null;
				
				self.list = function() {
					$http({
						method : 'GET',
						url : 'services/categorie/'
					}).then(function successCallback(response) {
						self.categories = response.data;
					}, function errorCallback(response) {

					});
				};
				
				self.add = function() {
					self.categorie={};
					console.log("Ajout");
					
					
					
					$http({
						method : 'GET',
						url : 'services/categorie/'
					}).then(function successCallback(response) {
						self.categories = response.data;
						console.log(self.categories);
					}, function errorCallback(response) {

					});
				}; 
				
				self.save = function() {
					if(self.categorie.id) {
						$http({
							method : 'PUT',
							url : 'services/categorie/' + self.categorie.id,
							data : self.categorie
						}).then(function successCallback(response) {
							self.list();
							self.categorie=null;
						}, function errorCallback(response) {
			
						});
					} else {
						$http({
							method : 'POST',
							url : 'services/categorie/',
							data : self.vol
						}).then(function successCallback(response) {
							self.list();
							self.categorie=null;
						}, function errorCallback(response) {
			
						});
					}
					
					
				}; 
				
				self.edit = function(id) {
					console.log("edition");
					
					$http({
						method : 'GET',
						url : 'services/categorie/' + id 
					}).then(function successCallback(response) {
						self.categorie = response.data;
						
					}, function errorCallback(response) {

					});
					
					
					$http({
						method : 'GET',
						url : 'services/categorie/'
					}).then(function successCallback(response) {
						self.categories = response.data;
						console.log(self.categories);
					}, function errorCallback(response) {

					});
				}; 
				
				self.remove = function(id) {
					$http({
						method : 'DELETE',
						url : 'services/categorie/' + id
					}).then(function successCallback(response) {
						self.list();
					}, function errorCallback(response) {

					});
				}; 
				
				self.list();
			}
				,
			controllerAs: 'categorieCtrl'
		};
	});
})(); 