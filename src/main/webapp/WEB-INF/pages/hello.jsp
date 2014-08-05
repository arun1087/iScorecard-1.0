<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <script src="<c:url value="/resources/js/jquery.1.10.2.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/script.js" />"></script>
    <script type="text/javascript">
        MYLIBRARY.init(["${match_id}", 1, "controlId"]);
        MYLIBRARY.helloWorld();
    </script>
</head>
<body>
<h1>${message}</h1>
</body>
</html>