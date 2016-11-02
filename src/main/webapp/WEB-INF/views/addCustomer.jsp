<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
            <h1>Customers</h1>
            <p>Add customer</p>
        </div>
    </div>
</section>

<section class="container">
    <form:form method="post" modelAttribute="newCustomer" class="form-horizontal">
        <fieldset>
            <legend>Add new customer</legend>
            <div class="form-group">
                <label class="control-label col-lg-2" for="customerId">Customer Id</label>
                <div class="col-lg-10">
                    <form:input path="customerId" id="customerId" class="form-input-large"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-lg-2" for="name">Name</label>
                <div class="col-lg-10">
                    <form:input path="name" id="name" cssClass="form-input-large"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-lg-2" for="address">Address</label>
                <div class="col-lg-10">
                    <form:input path="address" id="address" cssClass="form-input-large"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-lg-2" for="noOfOrdersMade">Orders Made</label>
                <div class="col-lg-10">
                    <form:input path="noOfOrdersMade" id="noOfOrdersMade" cssClass="form-input-large"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-lg-offset-2 col-lg-10">
                    <input type="submit" id="btnAdd" class="btn btn-primary" value="Add"/>
                </div>
            </div>
        </fieldset>
    </form:form>
</section>
</body>
</html>