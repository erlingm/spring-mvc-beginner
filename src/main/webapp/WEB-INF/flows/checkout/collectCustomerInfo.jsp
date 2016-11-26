<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-COMPATIBLE" content="IE=Edge">
    <meta http-equiv="Content-Type" charset="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <title>Customer</title>
</head>
<body>
<section>
    <div class="jumbotron">
        <div class="container">
            <h1>Customer</h1>
            <p>Customer details</p>
        </div>
    </div>
</section>
<section class="container">
    <form:form modelAttribute="order.customer" class="form-horizontal">
        <fieldset>
            <legend>Customer Details</legend>

            <div class="form-group">
                <label class="control-label col-lg-2" for="name">Name</label>
                <div class="col-lg-10">
                    <form:input path="name" id="name" cssClass="form-control input-lg"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2" for="doorNo">Door No</label>
                <div class="col-lg-10">
                    <form:input path="billingAddress.doorNo" id="doorNo" cssClass="form-control input-lg"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2" for="streetName">Street Name</label>
                <div class="col-lg-10">
                    <form:input path="billingAddress.streetName" id="streetName" cssClass="form-control input-lg"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2" for="areaName">Area Name</label>
                <div class="col-lg-10">
                    <form:input path="billingAddress.areaName" id="areaName" cssClass="form-control input-lg"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2" for="state">State</label>
                <div class="col-lg-10">
                    <form:input path="billingAddress.state" id="state" cssClass="form-control input-lg"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2" for="country">Country</label>
                <div class="col-lg-10">
                    <form:input path="billingAddress.country" id="country" cssClass="form-control input-lg"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2" for="zipCode">Zip Code</label>
                <div class="col-lg-10">
                    <form:input path="billingAddress.zipCode" id="zipCode" cssClass="form-control input-lg"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2" for="phoneNumber">Phone Number</label>
                <div class="col-lg-10">
                    <form:input path="phoneNumber" id="phoneNumber" cssClass="form-control input-lg"/>
                </div>
            </div>

            <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}">

            <div class="form-group">
                <div class="col-lg-offset-2 col-lg-10">
                    <input type="submit" id="btnAdd" class="btn btn-primary" value="Add" name="_eventId_customerInfoCollected">
                    <button id="btnCancel" class="btn btn-default" name="_eventId_cancel">Cancel</button>
                </div>
            </div>

        </fieldset>
    </form:form>
</section>
</body>
</html>