<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<body>
    <div class="page">
        <tiles:insertAttribute name="header" />
        <div class="content">
            <tiles:insertAttribute name="menu" />
            <tiles:insertAttribute name="main-content" />
        </div>
        <tiles:insertAttribute name="footer" />
    </div>
</body>