<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>

<html>
<head>
    <title>商品单位管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function() {
        });
    </script>
</head>
<body class="gray-bg">
<%@ include file="/webpage/include/echarts.jsp"%>
<div class="wrapper wrapper-content">
    <div class="ibox">
        <div class="ibox-title">
            <h5>商品单位列表 </h5>
            <div class="ibox-tools">
                <a class="collapse-link">
                    <i class="fa fa-chevron-up"></i>
                </a>
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-wrench"></i>
                </a>
                <ul class="dropdown-menu dropdown-user">
                    <li><a href="#">选项1</a>
                    </li>
                    <li><a href="#">选项2</a>
                    </li>
                </ul>
                <a class="close-link">
                    <i class="fa fa-times"></i>
                </a>
            </div>
        </div>

        <div class="ibox-content">
            <sys:message content="${message}"/>
            <!--查询条件-->
            <div class="row">
                <div class="col-sm-12">
                    <form:form id="searchForm" modelAttribute="goodUnites" action="${ctx}/storage/goodUnites/list" method="post" class="form-inline">
                        <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
                        <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
                        <table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
                    </form:form>
                    <br/>
                </div>
            </div>
            <!-- 工具栏 -->
            <div class="row">
                <div class="col-sm-12">
                    <div class="pull-left">
                        <shiro:hasPermission name="storage:goodUnites:add">
                            <table:addRow url="${ctx}/storage/goodUnites/form" title="商品单位"></table:addRow><!-- 增加按钮 -->
                        </shiro:hasPermission>
                        <shiro:hasPermission name="storage:goodUnites:edit">
                            <table:editRow url="${ctx}/storage/goodUnites/form" title="商品单位" id="contentTable"></table:editRow><!-- 编辑按钮 -->
                        </shiro:hasPermission>
                        <shiro:hasPermission name="storage:goodUnites:del">
                            <table:delRow url="${ctx}/storage/goodUnites/deleteAll" id="contentTable"></table:delRow><!-- 删除按钮 -->
                        </shiro:hasPermission>
                        <button class="btn btn-white btn-sm " data-toggle="tooltip" data-placement="left" onclick="sortOrRefresh()" title="刷新"><i class="glyphicon glyphicon-repeat"></i> 刷新</button>

                    </div>
                </div>
            </div>

            <!-- 表格 -->
            <table id="contentTable" class="table table-striped table-bordered table-hover table-condensed dataTables-example dataTable">
                <thead>
                <tr>
                    <th> <input type="checkbox" class="i-checks"></th>
                    <th>序号</th>
                    <th  class="sort-column unitName">单位组名称</th>
                    <th  class="sort-column unit">主计量单位</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${list}" var="goodUnites" varStatus="starNum">
                    <tr>
                        <td> <input type="checkbox" id="${goodUnites.id}" class="i-checks"></td>
                        <td>${starNum.count+(page.pageNo-1)*page.pageSize}</td>
                        <td><a  href="#" onclick="openDialogView('查看计量单位', '${ctx}/storage/goodUnites/form?id=${goodUnites.id}','800px', '500px')">
                                ${goodUnites.unitName}
                        </a>
                        </td>
                        <td>
                                ${goodUnites.unit}
                        </td>
                        <td>
                            <shiro:hasPermission name="storage:goodUnites:view">
                                <a href="#" onclick="openDialogView('查看计量单位', '${ctx}/storage/goodUnites/form?id=${goodUnites.id}','800px', '500px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看</a>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="storage:goodUnites:edit">
                                <a href="#" onclick="openDialog('修改计量单位', '${ctx}/storage/goodUnites/form?id=${goodUnites.id}','800px', '500px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> 修改</a>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="storage:goodUnites:del">
                                <a href="${ctx}/storage/goodUnites/delete?id=${goodUnites.id}" onclick="return confirmx('确认要删除该类别吗？', this.href)"   class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 删除</a>
                            </shiro:hasPermission>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <!-- 分页代码 -->
            <table:page page="${page}"></table:page>
            <br/>
            <br/>
        </div>
    </div>
</div>
</body>
</html>