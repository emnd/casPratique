(function() {
	var app = angular.module("produit", []);
	
	app.directive("produit", function(){
		return {
			restrict: 'E',
			templateUrl: "produit.html",
			controller: 
				function($http) {
				var self = this;
				
				self.produits = [];
				self.produit = null;
				self.categories=[];
				
				self.list = function() {
					$http({
						method : 'GET',
						url : 'services/produit/'
					}).then(function successCallback(response) {
						self.produits = response.data;
					}, function errorCallback(response) {

					});
				};
				
				self.add = function() {
					self.produit={};
					console.log("Ajout");
					
					$http({
						method : 'GET',
						url : 'services/produit/'
					}).then(function successCallback(response) {
						self.produits = response.data;
						console.log(self.produits);
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
				
				self.save = function() {
					if(self.produit.id) {
						$http({
							method : 'PUT',
							url : 'services/produit/' + self.produit.id,
							data : self.produit
						}).then(function successCallback(response) {
							self.list();
							self.produit=null;
						}, function errorCallback(response) {
			
						});
					} else {
						$http({
							method : 'POST',
							url : 'services/produit/',
							data : self.vol
						}).then(function successCallback(response) {
							self.list();
							self.produit=null;
						}, function errorCallback(response) {
			
						});
					}
					
					
				}; 
				
				self.edit = function(id) {
					console.log("edition");
					
					$http({
						method : 'GET',
						url : 'services/produit/' + id 
					}).then(function successCallback(response) {
						self.produit = response.data;
						
					}, function errorCallback(response) {

					});
					
					
					$http({
						method : 'GET',
						url : 'services/produit/'
					}).then(function successCallback(response) {
						self.produits = response.data;
						console.log(self.produits);
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
						url : 'services/produit/' + id
					}).then(function successCallback(response) {
						self.list();
					}, function errorCallback(response) {

					});
				}; 
				
				self.list();
			}
				,
			controllerAs: 'produitCtrl'
		};
	});
})(); 