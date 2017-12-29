<%@tag pageEncoding="UTF-8" %>
<%@tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="user" required="true" type="com.epam.preprod.patsera.shop.bean.User" %>

<c:if test="${empty user}">
       <nav id="nav">
           <ul>
               <li>
                   <form name="login-form" id="login-form" action="login" method="post">
                       <input placeholder="Email" type="text" name="email" id="logIn" value="${loginData.email}"/>
                       <input placeholder="Password" type="password" name="password" id="logIn"/>
                       <input type="submit" value="Log In" form="login-form" id="loginBtn"/>
                    </form>
                </li>
               <li class="active"><a href="index.jsp">Homepage</a></li>
               <li><a href="registration.jsp">Sign Up</a></li>
           </ul>
       </nav>
</c:if>
<c:if test="${not empty user}">
 <nav id="nav">
           <ul>
                <li>Hello, <c:out value="${user.firstName}"/></li>
                <li><a href="index.jsp">Homepage</a></li>
                <li><a href="logout">Logout</a></li>
           </ul>
 </nav>
</c:if>