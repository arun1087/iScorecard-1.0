<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery.1.10.2.min.js" />"></script>

</head>
<body>
    <button id="btnStart">start</button>
    <form action="/iScorecard/displayscore/" method="post">
        <div id="container"></div>
        <button id="submitButton" hidden="true">Submit</button>
        <script type="text/javascript" src="<c:url value="/resources/js/dataTextBoxes.js" />"></script>
        <link rel="stylesheet" href="<c:url value="/resources/css/style.css" />">
    </form>
</body>
</html>