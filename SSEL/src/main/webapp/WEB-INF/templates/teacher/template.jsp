<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertAttribute name="header" />
<body>
    <div class="page">
        <div class="content">
            <tiles:insertAttribute name="menu" />
            <tiles:insertAttribute name="main-content" />
        </div>
        <tiles:insertAttribute name="footer" />
    </div>
</body>