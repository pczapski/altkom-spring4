$(document).ready(function() {
	// console.log('dzialam')
	//	
	// $.get('http://localhost:8080/shop/api/products?query=sa',
	// function(data){
	// $("table tbody").empty();
	// console.log(data)
	// for(let el of data){
	// $("table tbody").append(`<tr>
	// <td>${el.name}</td>
	// <td>${el.quantity}</td>
	// <td>${el.price}</td>
	// </tr>`);
	// }
	// })

	// $.get('http://localhost:8080/shop/product/list-as-rows',
	// function(data) {
	// $("table tbody").empty();
	// $("table tbody").append(data);
	// })

});

var phonecatApp = angular.module('cart', []);
phonecatApp.service('ProductsService',  function ($http) {

        var urlBase = 'api/products';

        this.getProducts = function () {
            return $http.get(urlBase);
        };
 });
phonecatApp.controller('CartController', function CartController($scope,ProductsService) {
	$scope.lines = []
	$scope.total = 0;
	
	ProductsService.getProducts().then(function(response){
		$scope.products = response.data;
	
	

	$scope.$watch('lines', function(newV, old) {
		var total = 0;
		newV.forEach(function(element) {
			var product = $scope.products.find(function(el){
				return element.id==el.id;
			})
			element.price = product.price;
			element.sum = Number((element.quantity * element.price).toFixed(2));
			total += element.sum;
		});
		$scope.total = total.toFixed(2);
	}, true);
	})
	
	$scope.addLine = function() {
		$scope.lines.push({
			"quantity" : 1
		})
	}

	$scope.remove = function(line) {
		var index = $scope.lines.indexOf(line);
		$scope.lines.splice(index, 1);
	}

});