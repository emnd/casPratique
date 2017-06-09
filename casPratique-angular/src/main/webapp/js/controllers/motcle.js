(function() {
	var app = angular.module("motcle", []);
	
	
	
	app.directive('motcle', function($http){
		return {
			restrict : 'E',
			templateUrl : 'motcle.html',
			controller:function($http){
				var self = this;
				
				self.motcles = [];
				
				self.motcle = null;
				
				self.list = function() {
					$http({
						method : 'GET',
						url : 'services/motcle/'
					}).then(function successCallback(response) {
						self.motcles = response.data;
					}, function errorCallback(response) {

					});
				};
				
				self.add = function() {
					self.motcle={};
					
					
					
				}; 
				
				self.save = function() {
					if(self.motcle.id) {
						$http({
							method : 'PUT',
							url : 'services/motcle/' + self.motcle.id,
							data : self.motcle
						}).then(function successCallback(response) {
							self.list();
							self.motcle=null;
						}, function errorCallback(response) {
			
						});
					} else {
						$http({
							method : 'POST',
							url : 'services/motcle/',
							data : self.motcle
						}).then(function successCallback(response) {
							self.list();
							self.motcle=null;
						}, function errorCallback(response) {
			
						});
					}
					
					
				}; 
				
				self.edit = function(id) {
					
					$http({
						method : 'GET',
						url : 'services/motcle/' + id 
					}).then(function successCallback(response) {
						self.motcle = response.data;
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
						url : 'services/motcle/' + id
					}).then(function successCallback(response) {
						self.list();
					}, function errorCallback(response) {

					});
				}; 
				
				self.list();
			},
			controllerAs:'motcleCtrl'
		};
		
	});

})(); 