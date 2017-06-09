(function() {
	var app = angular.module("lignecommande", []);
	
	
	
	app.directive('lignecommande', function($http){
		return {
			restrict : 'E',
			templateUrl : 'lignecommande.html',
			controller:function($http){
				var self = this;
				
				self.lignecommandes = [];
				
				self.lignecommande = null;
				
				self.commandes=[];
				self.produits =[];
				
				
				self.list = function() {
					$http({
						method : 'GET',
						url : 'services/lignecommande/'
					}).then(function successCallback(response) {
						self.lignecommandes = response.data;
					}, function errorCallback(response) {

					});
				};
				
				self.add = function() {
					self.lignecommande={};
					
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
						url : 'services/commande/'
					}).then(function successCallback(response) {
						self.commandes = response.data;
						console.log(self.commandes);
					}, function errorCallback(response) {

					});
					
					
					
				}; 
				
				self.save = function() {
					if(self.lignecommande.id) {
						$http({
							method : 'PUT',
							url : 'services/lignecommande/' + self.lignecommande.id,
							data : self.lignecommande
						}).then(function successCallback(response) {
							self.list();
							self.lignecommande=null;
						}, function errorCallback(response) {
			
						});
					} else {
						$http({
							method : 'POST',
							url : 'services/lignecommande/',
							data : self.lignecommande
						}).then(function successCallback(response) {
							self.list();
							self.lignecommande=null;
						}, function errorCallback(response) {
			
						});
					}
					
					
				}; 
				
				self.edit = function(id) {
					
					$http({
						method : 'GET',
						url : 'services/lignecommande/'
					}).then(function successCallback(response) {
						self.lignecommandes = response.data;
					}, function errorCallback(response) {

					});
					
					$http({
						method : 'GET',
						url : 'services/lignecommande/' + id 
					}).then(function successCallback(response) {
						self.lignecommande = response.data;
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
						url : 'services/commande/'
					}).then(function successCallback(response) {
						self.commandes = response.data;
						console.log(self.commandes);
					}, function errorCallback(response) {

					});
				
					
				}; 
				
				self.remove = function(id) {
					$http({
						method : 'DELETE',
						url : 'services/lignecommande/' + id
					}).then(function successCallback(response) {
						self.list();
					}, function errorCallback(response) {

					});
				}; 
				
				self.list();
			},
			controllerAs:'lignecommandeCtrl'
		};
		
	});

})(); 