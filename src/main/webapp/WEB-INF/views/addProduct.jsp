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
    <title><spring:message code="addProduct.page.title"/></title>
</head>
<body>
<section>
    <div class="container">
        <div class="jumbotron">
            <h1><spring:message code="addProduct.body.title"/></h1>
            <p><spring:message code="addProduct.body.subtitle"/></p>
        </div>
    </div>
</section>

<section class="container">
    <form:form method="post" modelAttribute="newProduct" class="form-horizontal" enctype="multipart/form-data">
        <fieldset>
            <legend><spring:message code="addProduct.form.legend"/></legend>
            <div class="form-group form-group-lg">
                <label class="control-label col-lg-2" for="productId"><spring:message code="addProduct.form.productId.label" /></label>
                <div class="col-lg-10">
                    <form:input path="productId" id="productId" class="form-control"/>
                </div>
            </div>
            <div class="form-group form-group-lg">
                <label class="control-label col-lg-2" for="name"><spring:message code="addProduct.form.name.label"/></label>
                <div class="col-lg-10">
                    <form:input path="name" id="name" cssClass="form-control"/>
                </div>
            </div>
            <div class="form-group form-group-lg">
                <label class="control-label col-lg-2" for="unitPrice"><spring:message code="addProduct.form.unitPrice.label"/></label>
                <div class="col-lg-10">
                    <form:input path="unitPrice" id="unitPrice" cssClass="form-control"/>
                </div>
            </div>
            <div class="form-group form-group-lg">
                <label class="control-label col-lg-2" for="manufacturer"><spring:message code="addProduct.form.manufacturer.label"/></label>
                <div class="col-lg-10">
                    <form:input path="manufacturer" id="manufacturer" cssClass="form-control"/>
                </div>
            </div>
            <div class="form-group form-group-lg">
                <label class="control-label col-lg-2" for="category"><spring:message code="addProduct.form.category.label"/></label>
                <div class="col-lg-10">
                    <form:input path="category" id="category" cssClass="form-control"/>
                </div>
            </div>
            <div class="form-group form-group-lg">
                <label class="control-label col-lg-2" for="unitsInStock"><spring:message code="addProduct.form.unitsInStock.label"/></label>
                <div class="col-lg-10">
                    <form:input path="unitsInStock" id="unitsInStock" cssClass="form-control"/>
                </div>
            </div>
            <div class="form-group form-group-lg">
                <label class="control-label col-lg-2" for="description"><spring:message code="addProduct.form.description.label"/></label>
                <div class="col-lg-10">
                    <form:textarea path="description" id="description" rows="2" cssClass="form-control"/>
                </div>
            </div>
            <div class="form-group form-group-lg">
                <label class="control-label col-lg-2"><spring:message code="addProduct.form.condition.label"/></label>
                <div class="col-lg-10">
                    <label class="control-label"><form:radiobutton path="condition" id="condition" value="New"/> <spring:message code="product.condition.new"/></label>
                    <label class="control-label"><form:radiobutton path="condition" id="condition" value="Old"/> <spring:message code="product.condition.old"/></label>
                    <label class="control-label"><form:radiobutton path="condition" id="condition" value="Refurbished"/> <spring:message code="product.condition.refurbished"/></label>
                </div>
            </div>

            <div class="form-group form-group-lg">
                <label class="control-label col-lg-2" for="productImage"><spring:message code="addProduct.form.productImage.label"/></label>
                <div class="col-lg-10">
                    <input type="file" name="productImage" id="productImage" class="form-control input-lg"/>
                </div>
            </div>

            <div class="form-group form-group-lg">
                <label class="control-label col-lg-2" for="productManual"><spring:message code="addProduct.form.productManual.label"/></label>
                <div class="col-lg-10">
                    <input type="file" name="productManual" id="productManual" class="form-control input-lg"/>
                </div>
            </div>

            <div class="form-group" form-group-lg>
                <div class="col-lg-offset-2 col-lg-10">
                    <input type="submit" id="btnAdd" class="btn btn-primary" value="<spring:message code="addProduct.form.submit.label"/>"/>
                </div>
            </div>
        </fieldset>
    </form:form>
</section>
</body>
</html>