<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-COMPATIBLE" content="IE=Edge">
    <meta http-equiv="Content-Type" charset="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <title>Thank You</title>
</head>
<body>
<section>
    <div class="jumbotron">
        <div class="container">
            <h1 class="alert alert-danger">Thank You</h1>
            <p>Thanks for the order. Your order will be delivered to you on <fmt:formatDate value="${order.shippingDetail.shippingDate}" type="date"/>!</p>
            <p>Your Order Number is ${order.orderId}</p>
        </div>
    </div>
</section>
<section>
    <div class="container">
        <p>
            <a href="<spring:url value="/market/products"/>" class="btn btn-primary"><span class="glyphicon glyphicon-hand-left"></span> Products</a>
        </p>
    </div>
</section>
</body>
</html>