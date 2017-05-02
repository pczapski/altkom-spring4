$(document).ajaxStart(function() {
	NProgress.start()
});
$(document).ajaxComplete(function() {
	NProgress.done()
});
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
$(document).ready(function() {
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
	
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

phonecatApp.service('CartService', function($http) {
	var urlBase = 'cart';

	this.process = function(cart) {
		return $http({
			url : urlBase+'/process',
			 headers: {[header]:token},
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

	$scope.addItem = function() {
		$scope.items.push({
			"quantity" : 1
		})
	}

	$scope.remove = function(item) {
		var index = $scope.items.indexOf(item);
		$scope.items.splice(index, 1);
	}
	
	$scope.process = function(){
		CartService.process({items:$scope.items}).then(function(res){
			$scope.orderNo = res.data;
		})
	}

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