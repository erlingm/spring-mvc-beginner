<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-COMPATIBLE" content="IE=Edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <title>Product details</title>
</head>
<body>
<section>
    <div class="container">
        <div class="jumbotron">
            <h1>Product details</h1>
            <p>All the available products in our store</p>
        </div>
    </div>
</section>

<section class="container">
    <div class="row">
        <div class="col-md-5"><img src="<c:url value="/img/${product.productId}.png"/>" alt="image" style="width: 100%"/></div>
        <div class="col-md-5">
            <h3>${product.name}</h3>
            <p>${product.description}</p>
            <p>
                <strong>Item code : </strong><span class="label label-warning">${product.productId}</span>
            </p>
            <p>
                <strong>Manufacturer : </strong> ${product.manufacturer}
            </p>
            <p>
                <strong>Category : </strong> ${product.category}
            </p>
            <p>
                <strong>Available units in stock : </strong> ${product.unitsInStock}
            </p>
            <h4>${product.unitPrice} USD</h4>
            <p>
                <a href="<spring:url value="/market/products"/>" class="btn btn-default"><span class="glyphicon glyphicon-hand-left"></span> Back </a>
                <a href="#" class="btn btn-warning btn-large">
                    <span class="glyphicon glyphicon-shopping-cart"></span> Order Now
                </a>
            </p>
            <c:if test="${hasManual}">
                <p>Download <a href="<c:url value="/pdf/manual-${product.productId}.pdf"/>">manual</a></p>
            </c:if>
        </div>
    </div>
</section>
</body>
</html>