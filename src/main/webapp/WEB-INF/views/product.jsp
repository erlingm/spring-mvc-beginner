<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<section class="container" ng-app="cartApp">
    <div class="row">
        <div class="col-md-5"><img src="<c:url value="/img/${product.productId}.png"/>" alt="image" style="width: 100%"/></div>
        <div class="col-md-5">
            <h3>${product.name}</h3>
            <p>${product.description}</p>
            <p>
                <strong><spring:message code="product.body.itemCode"/> : </strong><span class="label label-warning">${product.productId}</span>
            </p>
            <p>
                <strong><spring:message code="product.body.manufacturer"/> : </strong> ${product.manufacturer}
            </p>
            <p>
                <strong><spring:message code="product.body.category"/> : </strong> ${product.category}
            </p>
            <p>
                <strong><spring:message code="product.body.unitsInStock"/> : </strong> ${product.unitsInStock}
            </p>
            <h4>${product.unitPrice} <spring:message code="currency.mnemonic"/></h4>
            <p ng-controller="cartCtrl">
                <a href="<spring:url value="/market/products"/>" class="btn btn-default"><span class="glyphicon glyphicon-hand-left"></span> <spring:message code="button.value.back"/> </a>
                <a href="#" class="btn btn-warning btn-large" ng-click="addToCart('${product.productId}')">
                    <span class="glyphicon glyphicon-shopping-cart"></span> <spring:message code="product.button.value.orderNow"/>
                </a>
                <a href="<spring:url value="/cart"/>" class="btn btn-default"><span class="glyphicon glyphicon-hand-right"></span> View Cart</a>
            </p>
            <c:if test="${hasManual}">
                <p><spring:message code="product.body.download"/> <a href="<c:url value="/pdf/manual-${product.productId}.pdf"/>"><spring:message code="product.body.manual"/></a></p>
            </c:if>
        </div>
    </div>
</section>