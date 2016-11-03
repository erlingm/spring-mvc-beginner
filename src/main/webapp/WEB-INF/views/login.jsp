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
    <title><spring:message code="login.page.title"/></title>
</head>
<body>
<section>
    <div class="jumbotron">
        <div class="container">
            <h1>Welcome to Web Store!</h1>
            <p>The one and only amazing web store</p>
        </div>
    </div>
</section>
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Please sign in</h3>
                </div>
                <div class="panel-body">
                    <c:url var="loginUrl" value="/login"/>
                    <form action="${loginUrl}" method="post" class="form-horizontal">

                        <c:if test="${param.error != null}">
                            <div class="alert alert-danger">
                                <p>Invalid username and/or password</p>
                            </div>
                        </c:if>

                        <c:if test="${param.logout != null}">
                            <div class="alert alert-success">
                                <p>You have been logged out successfully.</p>
                            </div>
                        </c:if>

                        <c:if test="${param.accessDenied != null}">
                            <div class="alert alert-danger">
                                <p>Access denied: you are not authorized.</p>
                            </div>
                        </c:if>

                        <div class="input-group input-sm">
                            <label class="input-group-addon" for="userId"><i class="fa fa-user"></i></label>
                            <input type="text" class="form-control" id="userId" name="userId" placeholder="Enter usename" required="required"/>
                        </div>
                        <div class="input-group input-sm">
                            <label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label>
                            <input type="password" class="form-control" id="password" name="password" placeholder="Enter password" required="required"/>
                        </div>

                        <div class="form-actions">
                            <input type="submit" class="btn btn-block btn-primary btn-default" value="Log in"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>