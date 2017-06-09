(function() {
	var app = angular.module("commande", []);
	
	app.directive("commande", function(){
		return {
			restrict: 'E',
			templateUrl: "commande.html",
			controller: 
				function($http) {
				var self = this;
				
				self.commandes = [];
				self.commande = null;
				
				self.users = [];
				
				self.list = function() {
					$http({
						method : 'GET',
						url : 'services/commande/'
					}).then(function successCallback(response) {
						self.commandes = response.data;
					}, function errorCallback(response) {

					});
				};
				
				self.add = function() {
					self.commande={};
					$http({
						method : 'GET',
						url : 'services/etats/'
					}).then(function successCallback(response) {
						self.etats = response.data;
						console.log(self.etats);
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
					
					console.log("Ajout");
					
					
					
					$http({
						method : 'GET',
						url : 'services/commande/'
					}).then(function successCallback(response) {
						self.commandes = response.data;
						console.log(self.commandes);
					}, function errorCallback(response) {

					});
				}; 
				
				self.save = function() {
					if(self.commande.id) {
						$http({
							method : 'PUT',
							url : 'services/commande/' + self.commande.id,
							data : self.commande
						}).then(function successCallback(response) {
							self.list();
							self.commande=null;
						}, function errorCallback(response) {
			
						});
					} else {
						$http({
							method : 'POST',
							url : 'services/commande/',
							data : self.vol
						}).then(function successCallback(response) {
							self.list();
							self.commande=null;
						}, function errorCallback(response) {
			
						});
					}
					
					
				}; 
				
				self.edit = function(id) {
					console.log("edition");
					
					$http({
						method : 'GET',
						url : 'services/commande/' + id 
					}).then(function successCallback(response) {
						self.commande = response.data;
						self.commande.dte = new Date(self.commande.dte);
						
					}, function errorCallback(response) {

					});
					
					$http({
						method : 'GET',
						url : 'services/etats/'
					}).then(function successCallback(response) {
						self.etats = response.data;
						console.log(self.etats);
					}, function errorCallback(response) {

					});
					
					$http({
						method : 'GET',
						url : 'services/commande/'
					}).then(function successCallback(response) {
						self.commandes = response.data;
						console.log(self.commandes);
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
						url : 'services/commande/' + id
					}).then(function successCallback(response) {
						self.list();
					}, function errorCallback(response) {

					});
				}; 
				
				self.list();
			}
				,
			controllerAs: 'commandeCtrl'
		};
	});
})(); 