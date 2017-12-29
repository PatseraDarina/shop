<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; />
    <meta name="robots" content="noarchive"/>

    <title>WebShop</title>

    <!--[if lte IE 8]><script src="css/ie/html5shiv.js"></script><![endif]-->
    <script src="js/jquery.min.js"></script>
    <script src="js/jquery.dropotron.min.js"></script>
    <script src="js/skel.min.js"></script>
    <script src="js/init.js"></script>
    <script type="text/javascript" src="/site-script.js?<?php echo time(); ?>"></script>
    <script type="text/javascript" src="/site-script.js?1234567890"></script>

    <noscript>
        <link rel="stylesheet" href="../css/skel.css" />
        <link rel="stylesheet" href="../css/style.css" />
        <link rel="stylesheet" href="../css/style-wide.css" />
    </noscript>
    <!--[if lte IE 8]><link rel="stylesheet" href="css/ie/v8.css" /><![endif]-->

</head>

<body>

    <!-- Wrapper -->
    <div class="wrapper style1">
       <jsp:include page="/WEB-INF/fragments/header.jsp"/>
        <!-- Banner -->
        <div id="banner" class="container">
            <ul>
            <span>
                <c:choose>
                    <c:when test = "${not empty userLoginErrors}">
                        <font color="red">
                          <br><c:out value="${userLoginErrors}"/></br>
                        </font>
                    </c:when>
                </c:choose>
            </span>
            </ul>
            <c:remove var="userLoginErrors" scope="session"/>
            <section>
                <p>Are you interested in household appliances, computers, software or goods for outdoor activities? All this you can buy right now, saving a lot of time! Ours online store will gladly help you avoid the need to visit dozens of shops. You can order any goods without getting up from your chair, and our courier will deliver the purchase to the address indicated on time.</p>
                <a href="#" class="button medium">Read More</a>
            </section>
        </div>

        <!-- Extra -->
        <div id="extra">
            <div class="container">
                <div class="row no-collapse-1">
                    <section class="4u"> <a href="#" class="image featured"><img src="images/pic01.jpg" alt=""></a>
                        <div class="box">
                            <p>Samsung Galaxy<br>14 500 грн.</br></p>
                            <a href="#" class="button">Buy</a> </div>
                    </section>
                    <section class="4u"> <a href="#" class="image featured"><img src="images/pic02.jpg" alt=""></a>
                        <div class="box">
                            <p>Sony Xperia X<br>13 500 грн.</br></p>
                            <a href="#" class="button">Buy</a> </div>
                    </section>
                    <section class="4u"> <a href="#" class="image featured"><img src="images/pic03.jpg" alt=""></a>
                        <div class="box">
                            <p>Apple iPhone<br>8 000 грн.</br></p>
                            <a href="#" class="button">Buy</a> </div>
                    </section>
                </div>
                <div class="row no-collapse-1">
                    <section class="4u"> <a href="#" class="image featured"><img src="images/pic01.jpg" alt=""></a>
                        <div class="box">
                            <p>Lenovo K6 Note<br>22 000 грн.</br></p>
                            <a href="#" class="button">Buy</a> </div>
                    </section>
                    <section class="4u"> <a href="#" class="image featured"><img src="images/pic02.jpg" alt=""></a>
                        <div class="box">
                            <p>Huawei Mate<br>10 000 грн.</br></p>
                            <a href="#" class="button">Buy</a> </div>
                    </section>
                    <section class="4u"> <a href="#" class="image featured"><img src="images/pic03.jpg" alt=""></a>
                        <div class="box">
                            <p>Xiaomi Mi6<br>14 000 грн.</br></p>
                            <a href="#" class="button">Buy</a> </div>
                    </section>
                </div>
            </div>
        </div>

        <!-- Main -->
        <div id="main">
            <div class="container">
                <div class="row">

                    <!-- Content -->
                    <div class="6u">
                        <section>
                            <ul class="style">
                                <li class="fa fa-wrench">
                                    <h3>Integer ultrices</h3>
                                    <span>In posuere eleifend odio. Quisque semper augue mattis wisi. Maecenas ligula. Pellentesque viverra vulputate enim. Aliquam erat volutpat. Maecenas condimentum enim tincidunt risus accumsan.</span> </li>
                                <li class="fa fa-leaf">
                                    <h3>Aliquam luctus</h3>
                                    <span>In posuere eleifend odio. Quisque semper augue mattis wisi. Maecenas ligula. Pellentesque viverra vulputate enim. Aliquam erat volutpat. Maecenas condimentum enim tincidunt risus accumsan.</span> </li>
                            </ul>
                        </section>
                    </div>
                    <div class="6u">
                        <section>
                            <ul class="style">
                                <li class="fa fa-cogs">
                                    <h3>Integer ultrices</h3>
                                    <span>In posuere eleifend odio. Quisque semper augue mattis wisi. Maecenas ligula. Pellentesque viverra vulputate enim. Aliquam erat volutpat. Maecenas condimentum enim tincidunt risus accumsan.</span> </li>
                                <li class="fa fa-road">
                                    <h3>Aliquam luctus</h3>
                                    <span>In posuere eleifend odio. Quisque semper augue mattis wisi. Maecenas ligula. Pellentesque viverra vulputate enim. Aliquam erat volutpat. Maecenas condimentum enim tincidunt risus accumsan.</span> </li>
                            </ul>
                        </section>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Footer -->

    <jsp:include page="/WEB-INF/fragments/footer.jspf"/>
    </body>
</html>
