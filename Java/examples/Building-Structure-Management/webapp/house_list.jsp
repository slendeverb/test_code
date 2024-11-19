<%--
  Created by IntelliJ IDEA.
  User: HYD
  Date: 2024/6/26
  Time: 9:50
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script type="text/javascript" src="scripts/jquery/jquery-1.7.1.js"></script>
    <link href="style/authority/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="style/authority/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="scripts/authority/commonAll.js"></script>
    <script type="text/javascript" src="scripts/fancybox/jquery.fancybox-1.3.4.js"></script>
    <script type="text/javascript" src="scripts/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
    <link rel="stylesheet" href="https://cdn.staticfile.net/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.net/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.net/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="style/authority/jquery.fancybox-1.3.4.css" media="screen"></link>
    <script type="text/javascript" src="scripts/artDialog/artDialog.js?skin=default"></script>
    <title>建筑管理系统</title>

</head>
<body>
<form id="submitForm" name="submitForm" action="<%= request.getContextPath()%>/Building?method=search" method="post">
    <input type="hidden" name="allIDCheck" value="" id="allIDCheck"/>
    <input type="hidden" name="fangyuanEntity.fyXqName" value="" id="fyXqName"/>
    <div id="container">
        <div class="ui_content">
            <div class="ui_text_indent">
                <div id="box_border">
                    <div id="box_top">搜索</div>
                    <div id="box_center">
                        字段
                        <select name="key" id="fyXq" class="ui_select01" required placeholder="" onchange="getFyDhListByFyXqCode();">
                            <option value="1">--默认查询全部--</option>
                            <option value="buildingname">建筑名称</option>
                            <option value="structure">建筑结构</option>
                            <option value="term">设计年限</option>
                        </select>

                        值&nbsp;&nbsp;<input type="text" id="fyZldz" name="value" class="ui_input_txt02" />
                    </div>
                    <div id="box_bottom">
                        <input type="submit" value="查询" class="ui_input_btn01"  />
                        <input type="button" value="新增" class="ui_input_btn01" data-toggle="modal" data-target="#addUserModal"/>
                    </div>
                </div>
            </div>
        </div>
        <div class="ui_content">
            <div class="ui_tb">
                <table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
                    <tr>
                        <th>编号</th>
                        <th>位置</th>
                        <th>建筑名称</th>
                        <th>建筑结构</th>
                        <th>设计年限</th>
                        <th>建筑类型</th>
                        <th>操作</th>
                    </tr>
                    <tbody>
                    <c:forEach items="${list}" var="buildingInfo">
                        <tr>
                            <td>${buildingInfo.id}</td>
                            <td>${buildingInfo.buildingLocation}</td>
                            <td>${buildingInfo.buildingName}</td>
                            <td>${buildingInfo.buildingStructure}</td>
                            <td>${buildingInfo.buildingTerm}</td>
                            <td>${buildingInfo.buildingType}</td>
                            <td><button type="button" class="btn btn-info"
                                        data-Id="${buildingInfo.id}"
                                        data-Location="${buildingInfo.buildingLocation}"
                                        data-Name="${buildingInfo.buildingName}"
                                        data-Structure="${buildingInfo.buildingStructure}"
                                        data-Term="${buildingInfo.buildingTerm}"
                                        data-Type="${buildingInfo.buildingType}"
                                        data-toggle="modal" data-target="#updateUserModal">修改
                                </button><button type="button" class="btn btn-danger"
                                                 data-Id="${buildingInfo.id}"
                                                 data-toggle="modal"
                            data-target="#deleteModal">删除
                            </button></td>

                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
            <div class="ui_tb_h30">
                <div class="ui_frt">
                    <input type="button" value="版本信息" class="ui_input_btn01" onclick='location.href=("introduce.jsp")'/>
                    <input type="button" value="安全退出" onclick='location.href=("login.jsp")' class="ui_input_btn01" />
                </div>
            </div>
    </div>

</form>
</body>

<%--增加信息模态框--%>
<div class="modal fade" id="addUserModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title" id="myModalLabel1">增加信息</h4>
            </div>
            <div class="modal-body">
                <form id="submitForm1" name="submitForm" action="<%= request.getContextPath()%>/Building?method=save" method="post" onsubmit="submitForm()">
                <div id="container1">
                    <div class="ui_content">
                        <table  cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
                            <tr>
                                <td class="ui_text_rt" width="80">位置</td>
                                <td class="ui_text_lt">
                                    <select name="location" id="blocation1" class="ui_select01" required placeholder="位置" onchange="getFyDhListByFyXqCode();">
                                        <option value="" selected="selected">--请选择--</option>
                                        <option value="城区">城区</option>
                                        <option value="郊区">郊区</option>
                                        <option value="乡村">乡村</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td class="ui_text_rt">建筑名称</td>
                                <td class="ui_text_lt">
                                    <input type="text" id="bname1" name="buildingname"  class="ui_input_txt04" required placeholder="例：中北大学8号楼"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="ui_text_rt">设计年限</td>
                                <td class="ui_text_lt">
                                    <input type="text" id="bterm1" name="term"  class="ui_input_txt04" required placeholder="只填数字即可！"/>年
                                </td>
                            </tr>
                            <tr>
                                <td class="ui_text_rt">建筑类型</td>
                                <td class="ui_text_lt">
                                    <select name="type" id="btype1" class="ui_select01" required placeholder="建筑类型">
                                        <option value="" selected="selected">--请选择--</option>
                                        <option value="商用" >商用</option>
                                        <option value="工业">工业</option>
                                        <option value="住宅">住宅</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td class="ui_text_rt">建筑结构</td>
                                <td class="ui_text_lt">
                                    <select name="structure" id="bstructure1" class="ui_select01" required placeholder="建筑结构">
                                        <option value="" selected="selected">--请选择--</option>
                                        <option value="钢筋混凝土结构" >钢筋混凝土结构</option>
                                        <option value="砖混结构" >砖混结构</option>
                                        <option value="砖木结构" >砖木结构</option>
                                        <option value="钢结构" >钢结构</option>
                                        <option value="框架结构" >框架结构</option>
                                    </select>
                                </td>
                            </tr>

                        </table>
                    </div>
                </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button  class="btn btn-primary" >提交更改</button>
                    </div>
            </form>
            </div>

        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<%--更新信息模态框--%>
<div class="modal fade" id="updateUserModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title" id="myModalLabel2">修改信息</h4>
            </div>
            <div class="modal-body">
                <form id="submitForm2" name="submitForm" action="<%= request.getContextPath()%>/Building?method=update" method="post" onsubmit="submitForm()">
                    <div id="container2">
                        <div class="ui_content">
                            <table  cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
                                <tr>
                                    <td class="ui_text_rt">编号</td>
                                    <td class="ui_text_lt">
                                        <input type="text" id="id1" name="id"  class="ui_input_txt01" readonly />
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ui_text_rt" width="80">位置</td>
                                    <td class="ui_text_lt">
                                        <select name="location" id="blocation2" class="ui_select01" required placeholder="位置" onchange="getFyDhListByFyXqCode();">
                                            <option value="" selected="selected">--请选择--</option>
                                            <option value="城区">城区</option>
                                            <option value="郊区">郊区</option>
                                            <option value="乡村">乡村</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ui_text_rt">建筑名称</td>
                                    <td class="ui_text_lt">
                                        <input type="text" id="bname2" name="buildingname"  class="ui_input_txt04" required placeholder="例：中北大学8号楼"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ui_text_rt">设计年限</td>
                                    <td class="ui_text_lt">
                                        <input type="text" id="bterm2" name="term"  class="ui_input_txt04" required placeholder="只填数字即可！"/>年
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ui_text_rt">建筑类型</td>
                                    <td class="ui_text_lt">
                                        <select name="type" id="btype2" class="ui_select01" required placeholder="建筑类型">
                                            <option value="" selected="selected">--请选择--</option>
                                            <option value="商用" >商用</option>
                                            <option value="工业">工业</option>
                                            <option value="住宅">住宅</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="ui_text_rt">建筑结构</td>
                                    <td class="ui_text_lt">
                                        <select name="structure" id="bstructure2" class="ui_select01" required placeholder="建筑结构">
                                            <option value="" selected="selected">--请选择--</option>
                                            <option value="钢筋混凝土结构" >钢筋混凝土结构</option>
                                            <option value="砖混结构" >砖混结构</option>
                                            <option value="砖木结构" >砖木结构</option>
                                            <option value="钢结构" >钢结构</option>
                                            <option value="框架结构" >框架结构</option>
                                        </select>
                                    </td>
                                </tr>

                            </table>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button  class="btn btn-primary" >提交更改</button>
                    </div>
                </form>
            </div>

        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>



<%--删除信息模态框--%>
<form method="post" action="<%= request.getContextPath()%>/Building?method=delete">
    <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="myModalLabel" STYLE="color: #ab0303">WARNING!!!</h2>
            </div>
            <h1 class="modal-body" id="deleteLabel"></h1>
            <input type="hidden" class="form-control" id="id" name="id" placeholder="">
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button  class="btn btn-danger">确定</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
    </div>
</form>




<script>
    $('#updateUserModal').on('show.bs.modal',function (event) {

        var button=$(event.relatedTarget)
        var id=button.data('id')
        var location=button.data('location')
        var name=button.data('name')
        var structure=button.data('structure')
        var term=button.data('term')
        var type=button.data('type')
        var modal=$(this)

        modal.find('#id1').val(id)
        modal.find('#blocation2').val(location)
        modal.find('#bname2').val(name)
        modal.find('#bstructure2').val(structure)
        modal.find('#bterm2').val(term)
        modal.find('#btype2').val(type)

    })

    $('#deleteModal').on('show.bs.modal',function (event) {

        var button=$(event.relatedTarget)
        var id=button.data('id')
        var modal=$(this)

        modal.find('#deleteLabel').text('是否删除为编号为'+id+'的信息?')
        modal.find('#id').val(id)

    })
</script>
</html>

