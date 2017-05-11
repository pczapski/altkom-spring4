$(document).ajaxStart(function() {
	NProgress.start()
});
$(document).ajaxComplete(function() {
	NProgress.done()
});

var phonecatApp = angular.module('cart', []);
phonecatApp.service('ProductsService', function($http) {
	var urlBase = 'api/products';

	this.getProducts = function() {
		return $http({
			url : urlBase,
			params : {
				query : 'qwe'
			},
			method : "GET",
		});
	};
});

phonecatApp.service('CartService', function($http) {
	var urlBase = 'cart';

	this.process = function(cart) {
		return $http({
			url : urlBase+'/process',
			data:cart,
			method : "POST",
		});
	};
});

phonecatApp.controller('CartController', function CartController($scope,
		ProductsService, CartService) {
	$scope.items = []
	$scope.total = 0;

	ProductsService.getProducts().then(function(response) {
		$scope.products = response.data;

		$scope.$watch('items', function(newV, old) {
			calculateCart($scope, newV, $scope.products);
		}, true);
	})

});

function calculateCart(cart, items, products) {
	var netto = 0;
	items.forEach(function(element) {
				var product = products.find(function(el) {
					return element.id == el.id;
				})
				if (product) {
					element.price = product.price;
					element.avaliable = product.quantity;
					element.sum = element.quantity * element.price;
					netto += element.sum;
				}
			});
	cart.netto = netto;
	cart.vat = netto * 0.23;
	cart.total = cart.netto + cart.vat;
}