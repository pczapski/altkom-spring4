<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/pages/layout/head.jsp"%>
<c:if test="${saved}">
		<div class="alert alert-success" role="alert" >
			<span class="glyphicon 	glyphicon glyphicon-ok" aria-hidden="true"></span>
			Zapisano product
		</div>
		</c:if>
 <sec:authorize access="hasAnyRole('ROLE_ADMIN')">
	<a href="new" class="btn btn-primary "> <i
		class=" glyphicon glyphicon-plus"></i> Add new product
	</a>
	</sec:authorize>
	<div class="col-xs-4 pull-right">
		<div class="input-group">
			<input type="text" class="form-control searcher"
				placeholder="Search..."> <span class="input-group-btn">
				<button class="btn btn-default" type="button">
					<i class="glyphicon glyphicon-send" style="height:20px"></i>
				</button>
			</span>
		</div>
	</div>
	
<%@ include file="product-list-prod-table.jsp"%>

<%@ include file="/WEB-INF/pages/layout/footer.jsp"%>

