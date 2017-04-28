<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/pages/layout/head.jsp"%>
<div class="container" ng-app="cart" >
	<div class="row" ng-controller="CartController">
		<div class="col-sm-12 col-md-10 col-md-offset-1">
		<form>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Product</th>
						<th>Quantity</th>
						<th class="text-center">Price</th>
						<th class="text-center">Total</th>
						<th> </th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="line in lines">
						<td class="col-sm-8 col-md-6">
							<div class="media">
								<a class="thumbnail pull-left" href="#"> <img
									class="media-object"
									src="http://icons.iconarchive.com/icons/custom-icon-design/flatastic-2/72/product-icon.png"
									style="width: 72px; height: 72px;">
								</a>
								<div class="media-body">
									<h4 class="media-heading">
									<select class="form-control"  style="color:#428bca;font-weight: bold" ng-model="line.id">
									<c:forEach items="${products}" var="product">
										<option value="${product.id}">${product.name}</option>
									</c:forEach>
									</select>
									</h4>
									<h5 class="media-heading">
										by <a href="#">Brand name</a>
									</h5>
									<span>Status: </span><span class="text-success"><strong>In
											Stock</strong></span>
								</div>
							</div>
						</td>
						<td class="col-sm-1 col-md-1" style="text-align: center" ><input
							type="number"  class="form-control" ng-model="line.quantity"
							></td>
						<td class="col-sm-1 col-md-1 text-center"><strong >$ {{line.price}}</strong></td>
						<td class="col-sm-1 col-md-1 text-center"><strong >$ {{line.sum}}</strong></td>
						<td class="col-sm-1 col-md-1">
							<button type="button" class="btn btn-danger" ng-click="remove(line)">
								<span class="glyphicon glyphicon-remove"></span> Remove
							</button>
						</td>
					</tr>
					<tr>
						<td>
							<button type="button" class="btn btn-primary" ng-click="addLine()">
								Add product <span class="glyphicon glyphicon-plus"></span>
							</button> 
						</td>
						<td> </td>
						<td> </td>
						<td><h3>Total</h3></td>
						<td class="text-right"><h3>
								<strong>{{total}}</strong>
							</h3></td>
					</tr>
					<tr>
						<td> </td>
						<td> </td>
						<td> </td>
						<td>
							<button type="button" class="btn btn-default">
								<span class="glyphicon glyphicon-shopping-cart"></span> Continue
								Shopping
							</button>
						</td>
						<td>
							<button type="button" class="btn btn-success">
								Checkout <span class="glyphicon glyphicon-play"></span>
							</button>
						</td>
					</tr>
				</tbody>
			</table>
			</form>
		</div>
	</div>
</div>
<%@ include file="/WEB-INF/pages/layout/footer.jsp"%>

