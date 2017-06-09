(function() {
	var app = angular.module("produitmotcle", []);
	
	
	
	app.directive('produitmotcle', function($http){
		return {
			restrict : 'E',
			templateUrl : 'produitmotcle.html',
			controller:function($http){
				var self = this;
				
				self.produitmotcles = [];
				
				self.produitmotcle = null;
				
				self.produits = [];
				self.motcles = [];
				
				
				self.list = function() {
					$http({
						method : 'GET',
						url : 'services/produitmotcle/'
					}).then(function successCallback(response) {
						self.produitmotcles = response.data;
						console.log("Mes produits mots cles : "+ self.produitmotcles);
					}, function errorCallback(response) {

					});
				};
				
				self.add = function() {
					
					console.log("Ajout d'un mot clé à un produit : ");
					self.produitmotcle={};
					
					$http({
						method : 'GET',
						url : 'services/produit/'
					}).then(function successCallback(response) {
						self.produits = response.data;
					}, function errorCallback(response) {

					});
					
					$http({
						method : 'GET',
						url : 'services/motcle/' 
					}).then(function successCallback(response) {
						self.motcles = response.data;
					}, function errorCallback(response) {

					});
					
				}; 
				
				self.save = function() {
					if(self.produitmotcle.id) {
						$http({
							method : 'PUT',
							url : 'services/produitmotcle/' + self.produitmotcle.id,
							data : self.produitmotcle
						}).then(function successCallback(response) {
							self.list();
							self.produitmotcle=null;
						}, function errorCallback(response) {
			
						});
					} else {
						$http({
							method : 'POST',
							url : 'services/produitmotcle/',
							data : self.produitmotcle
						}).then(function successCallback(response) {
							self.list();
							self.produitmotcle=null;
						}, function errorCallback(response) {
			
						});
					}
					
					
				}; 
				
				self.edit = function(id) {
					
					$http({
						method : 'GET',
						url : 'services/produitmotcle/'
					}).then(function successCallback(response) {
						self.produitmotcles = response.data;
						console.log("Mes produits mots cles : "+ self.produitmotcles);
					}, function errorCallback(response) {

					});
					
					$http({
						method : 'GET',
						url : 'services/produitmotcle/'+ id
					}).then(function successCallback(response) {
						self.produitmotcle = response.data;
						console.log("Mon produit mot cle : "+ self.produitmotcle);
					}, function errorCallback(response) {

					});
					
					$http({
						method : 'GET',
						url : 'services/produit/'
					}).then(function successCallback(response) {
						self.produits = response.data;
					}, function errorCallback(response) {

					});
					
					$http({
						method : 'GET',
						url : 'services/motcle/' 
					}).then(function successCallback(response) {
						self.motcles = response.data;
					}, function errorCallback(response) {

					});
				
					
				}; 
				
				self.remove = function(id) {
					$http({
						method : 'DELETE',
						url : 'services/produitmotcle/' + id
					}).then(function successCallback(response) {
						self.list();
					}, function errorCallback(response) {

					});
				}; 
				
				self.list();
			},
			controllerAs:'produitmotcleCtrl'
		};
		
	});

})(); 