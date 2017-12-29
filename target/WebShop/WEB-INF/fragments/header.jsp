<%@ taglib prefix="log" tagdir="/WEB-INF/tags"%>\

<head>
    <meta name="robots" content="noarchive"/>
    <noscript>
        <link rel="stylesheet" href="css/skel.css" />
        <link rel="stylesheet" href="css/style.css" />
        <link rel="stylesheet" href="css/style-wide.css" />
    </noscript>
</head>
<!-- Header -->
<div id="header" class="skel-panels-fixed">
    <div id="logo">
        <h1><a href="index.jsp">Electronics store</a></h1>
        <span class="tag">by Daryna Patsera</span>
    </div>
    <log:login user="${sessionScope.user}"/>

</div>