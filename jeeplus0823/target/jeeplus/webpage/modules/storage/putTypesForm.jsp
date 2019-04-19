<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
    <title>类别管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        var validateForm;
        function doSubmit(){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
            if(validateForm.form()){
                $("#inputForm").submit();
                return true;
            }

            return false;
        }
        $(document).ready(function() {
            validateForm = $("#inputForm").validate({
                submitHandler: function(form){
                    loading('正在提交，请稍等...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function(error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                    if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            });

        });
    </script>
</head>
<body>
<form:form id="inputForm" modelAttribute="putType" action="${ctx}/storage/putType/save" method="post" class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
        <tbody>
        <tr>
            <td class="width-15 active"><label class="pull-right">类别编码：</label></td>
            <td class="width-35">
                <form:input path="num" htmlEscape="false" maxlength="64" class="form-control digits"/>
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">类别名称：</label></td>
            <td class="width-35">
                <form:input path="type" htmlEscape="false" class="form-control"/>
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">出/入库：</label></td>
            <td class="width-35">
                <input name="isOut" type="radio" checked="checked" value="1" />出库
                <input name="isOut" type="radio" value="0"/>入库
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">系统预置：</label></td>
            <td class="width-35">
                <input name="isSys" type="radio" checked="checked" value="1"/>是
                <input name="isSys" type="radio" value="0" />否
            </td>
        </tr>
        </tbody>
    </table>
</form:form>
</body>
</html>