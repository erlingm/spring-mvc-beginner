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
            <h1>Products</h1>
            <p>Add product</p>
        </div>
    </div>
</section>

<section class="container">
    <form:form method="post" modelAttribute="newProduct" class="form-horizontal">
        <fieldset>
            <legend>Add new product</legend>
            <div class="form-group">
                <label class="control-label col-lg-2" for="productId"><spring:message code="addProduct.form.productId.label" /></label>
                <div class="col-lg-10">
                    <form:input path="productId" id="productId" class="form-input-large"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-lg-2" for="name">Name</label>
                <div class="col-lg-10">
                    <form:input path="name" id="name" cssClass="form-input-large"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-lg-2" for="unitPrice">Unit Price</label>
                <div class="col-lg-10">
                    <form:input path="unitPrice" id="unitPrice" cssClass="form-input-large"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-lg-2" for="manufacturer">Manufacturer</label>
                <div class="col-lg-10">
                    <form:input path="manufacturer" id="manufacturer" cssClass="form-input-large"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-lg-2" for="category">Category</label>
                <div class="col-lg-10">
                    <form:input path="category" id="category" cssClass="form-input-large"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-lg-2" for="unitsInStock">Units in Stock</label>
                <div class="col-lg-10">
                    <form:input path="unitsInStock" id="unitsInStock" cssClass="form-input-large"/>
                </div>
            </div>
<%--
            <div class="form-group">
                <label class="control-label col-lg-2" for="unitsInOrder">Units in Order</label>
                <div class="col-lg-10">
                    <form:input path="unitsInOrder" id="unitsInOrder" cssClass="form-input-large"/>
                </div>
            </div>
--%>
            <div class="form-group">
                <label class="control-label col-lg-2" for="description">Description</label>
                <div class="col-lg-10">
                    <form:textarea path="description" id="description" rows="2"/>
                </div>
            </div>
<%--
            <div class="form-group">
                <label class="control-label col-lg-2" for="discontinued">Discontinued</label>
                <div class="col-lg-10">
                    <form:checkbox path="discontinued" id="discontinued"/>
                </div>
            </div>
--%>
            <div class="form-group">
                <label class="control-label col-lg-2" for="condition">Condition</label>
                <div class="col-lg-10">
                    <form:radiobutton path="condition" id="condition" value="New"/>New
                    <form:radiobutton path="condition" id="condition" value="Old"/>Old
                    <form:radiobutton path="condition" id="condition" value="Refurbished"/>Refurbished
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