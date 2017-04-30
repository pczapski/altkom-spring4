$(document).ajaxStart(function() {
	NProgress.start()
});
$(document).ajaxComplete(function() {
	NProgress.done()
});
$(document).ready(function() {
	$(".searcher").keyup(function() {
		console.log($(".searcher").val())
		$.get('list/productsTable', function(data) {
			$("table tbody").empty();
			var tbody = $(data).find("tbody tr")
			$("table tbody").append(tbody);
		})
	})
	$("input[name='file']").change(function(){
	    readURL(this);
	});
});

function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#preview').attr('src', e.target.result).show();
        }

        reader.readAsDataURL(input.files[0]);
        console.log(input.files[0]);
    }
}



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
phonecatApp.controller('CartController', function CartController($scope,
		ProductsService) {
	$scope.lines = []
	$scope.total = 0;

	ProductsService.getProducts().then(function(response) {
		$scope.products = response.data;

		$scope.$watch('lines', function(newV, old) {
			calculateCart($scope, newV, $scope.products);
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

function calculateCart(cart, lines, products) {
	var netto = 0;
	lines
			.forEach(function(element) {
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