<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<<<<<<< HEAD
<tiles:insertAttribute name="header" />
<body>
    <div class="page">
=======
<body>
    <div class="page">
    <tiles:insertAttribute name="header" />
>>>>>>> c7fc324fe2db3c6fd5d9c9fd97b6622fad8ffd78
        <div class="content">
            <tiles:insertAttribute name="menu" />
            <tiles:insertAttribute name="main-content" />
        </div>
        <tiles:insertAttribute name="footer" />
    </div>
</body>