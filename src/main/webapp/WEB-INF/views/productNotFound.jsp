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
            <h1 class="alert alert-danger">There is no product found with the Product id ${invalidProductId}</h1>
        </div>
    </div>
</section>

<section>
    <div class="container">
        <p>${url}</p>
        <p>${exception}</p>
    </div>
    <div class="container">
        <p>
            <a href="<spring:url value="/market/products"/>" class="btn btn-primary">
                <span class="glyphicon glyphicon-hand-left"></span> products
            </a>
        </p>
    </div>
</section>
</body>
</html>