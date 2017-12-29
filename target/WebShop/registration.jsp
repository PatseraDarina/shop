<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "cp" uri = "/WEB-INF/tags/captcha.tld"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>

    <meta name="description" content="" />
    <meta name="keywords" content="" />
    <meta name="robots" content="noarchive"/>

    <title>Registration</title>

    <!--[if lte IE 8]><script src="css/ie/html5shiv.js"></script><![endif]-->
    <script src="js/jquery.min.js"></script>
    <script src="js/jquery.dropotron.min.js"></script>
    <script src="js/skel.min.js"></script>
    <script src="js/init.js"></script>

    <noscript>
        <link rel="stylesheet" href="css/skel.css" />
        <link rel="stylesheet" href="css/style.css" />
        <link rel="stylesheet" href="css/style-wide.css" />
    </noscript>

</head>

<body>
    <jsp:include page="/WEB-INF/fragments/header.jsp"/>

    <div id="footer" class="wrapper style2">
    <div class="container">
        <section>
        <ul class="errorMessages">
            <span>
                <c:choose>
                    <c:when test = "${not empty userRegErrors}">
                          <br><c:out value="${userRegErrors['captcha']}"/></br>
                          <c:out value="${userRegErrors['userExist']}"/>
                    </c:when>
                </c:choose>
            </span>
        </ul>
            <header class="major">
                <h2>Registration</h2>
                <span class="byline">Please, enter the required information for registration</span>
            </header>

            <form name="register-form" id="register-form" action="registration" method="post" enctype="multipart/form-data" onsubmit="return validateForm();">
                <div class="row half">
                    <div class="12u">
                        <input class="text" type="text" name="firstName" id="firstName" placeholder="First Name" value="${sessionScope.user.firstName}"/>
                        <span id="fNameErr"><c:out value="${userRegErrors['firstName']}"/></span>
                    </div>
                </div>
                <div class="row half">
                    <div class="12u">
                        <input class="text" type="text" name="lastName" id="lastName" placeholder="Last Name" value="${sessionScope.user.lastName}"/>
                        <span id="lNameErr"><c:out value="${userRegErrors['lastName']}"/></span>
                    </div>
                </div>
                <div class="row half">
                    <div class="12u">
                        <input class="text" type="text" name="email" id="email" placeholder="Email" value="${sessionScope.user.email}"/>
                        <span id="emailErr"><c:out value="${userRegErrors['email']}"/></span>
                    </div>
                </div>
                <div class="row half">
                    <div class="12u">
                        <input class="text" type="password" name="password" id="password" placeholder="Password" />
                        <span id="pwdErr"><c:out value="${userRegErrors['password']}"/></span>
                    </div>
                </div>
                 <div class="row half">
                    <div class="12u">
                        <input class="text" type="password" name="confirmPassword" id="confirmPassword" placeholder="Confirm password" />
                        <span id="confirmErr"><c:out value="${userRegErrors['confirmPassword']}"/></span>
                    </div>
                </div>
                <div class="row half">
                    <div class="12u">
                        Choose your avatar:
                        <input type="file" accept="image/*" name="avatar"/>
                    </div>
                </div>
                <div class="cbAdv">
                    <p></p>
                    <fieldset>
                        <legend>Would you like to get news?</legend>
                        <div>
                            <input type="checkbox" id="news" name="news" >
                            <label for="news">Get news</label>
                        </div>
                        <p></p>
                    </fieldset>
                    <cp:InsertCaptcha/>
                </div>
                <div class="row half">
                    <div class="12u">
                        <ul class="actions">
                              <button type="submit" form="register-form" class="button alt" style = "margin-top: 2%">Sign Up</button>
                        </ul>
                    </div>
                </div>
            </form>
            	<c:remove var="userBean" scope="session"/>
            	<c:remove var="userRegErrors" scope="session"/>
        </section>
    </div>
</div>

<!-- Footer -->
    <jsp:include page="/WEB-INF/fragments/footer.jspf"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="js/validation-jq.js"></script>
</body>
</html>