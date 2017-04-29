<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<table class="table table-hover table-striped">
		<thead>
			<tr>
				<th>Id</th>
				<th><spring:message code="product.name"/></th>
				<th><spring:message code="product.quantity"/></th>
				<th><spring:message code="product.price"/></th>
				<th><spring:message code="product.actions"/></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="p" items="${products}">
				<tr>
					<td>${p.id}<img src="img/${p.id}" style="width:30px;height:30px"/></td>
					<td>${p.name}</td>
					<td>${p.quantity}</td>
					<td>${p.price}</td>
					<td>
						<a href="${p.id}/edit"> <i class=" glyphicon glyphicon-pencil"></i></a>
						<a href="${p.id}/delete"> <i class=" glyphicon glyphicon-remove-circle"></i></a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

