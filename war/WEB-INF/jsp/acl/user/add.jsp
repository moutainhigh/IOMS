<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="expires" content="0" />
<link rel="stylesheet" href="${rootUrl}css/window.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="${rootUrl }css/main.css" />
<link rel="stylesheet" type="text/css" href="${rootUrl }css/top_b.css" />
<link rel="stylesheet" type="text/css" href="${rootUrl }css/pager.css" />

<script src="${rootUrl }js/jquery/jquery.js" type="text/javascript"></script>
<script src="${rootUrl }js/jquery/jquery.tablesorter.js"
	type="text/javascript"></script>
<script src="${rootUrl }js/jquery/jquery.pager.js"
	type="text/javascript"></script>
<script src="${rootUrl }js/jquery/jquery.form.js" type="text/javascript"></script>
<script src="${rootUrl }js/jquery/jquery.manage.js"
	type="text/javascript"></script>
<script src="${rootUrl }js/jquery/jquery.nyroModal.js"
	type="text/javascript"></script>
<script src="${rootUrl }js/jquery/jquery.validate.js"
	type="text/javascript"></script>
<script src="${rootUrl }js/jquery/jquery.metadata.js"
	type="text/javascript"></script>
<script src="${rootUrl }js/jquery/jquery.validate.manage.js"
	type="text/javascript"></script>
<script src="${rootUrl }js/jquery/messages_cn.js" type="text/javascript"></script>

<script type="text/javascript">
	
	
function ajaxDept() {
	var deptPar = $("#deptinfoParStr").val();

	if ("" != deptPar) {
		

		var url = "${rootUrl}systemmng/deptinfomng/getDept.dox?id=" + deptPar;
        $.ajax({
            url : url,
            type : "GET",//get请求
            async : false,//同步加载数据
            data : "deptPar=" + deptPar,
            dataType : "json",//返回json格式
            success : function(response) {
            	$("#deptinfoStr").html("<option value='' selected='selected'>请选择</option>");
            	if(response.length > 0){
                    for ( var i = 0; i < response.length; i++) {
                        $("#deptinfoStr").append("<option value='"+response[i].id+"' >" + response[i].deptName + "</option>");

                    }
                }else{
                	$("#deptinfoStr").empty();
               	 	$("#deptinfoStr").append("<option value='' >请选择</option>");
                	
                }
      
            }
        });
    } else {
        $("#pClientID").html("<option value='' selected='selected'>请选择</option>");
    }
}
	$(function() {
		$.ajaxSetup({
			cache : false
		});
		$("#addForm").validate({
			rules : {
				loginName : {
					required : true,
					minlength:3,
					maxlength : 15,
					loginName : true,
					remote : '${rootUrl}acl/user/checkUserName.dox'
				},
				roleIds : {
					required : true
				},
				name : {
					maxlength : 10,
					required : true,
					description : true
				},
				password : {
					required : true,
					password : true
				},
				confirm_pw : {
					required : true,
					equalTo : "#password"
				},
				mobilephone : {
					mobile : true,
					required : true,
					maxlength : 11

				},
				email : {
					//email : true,
					required : true,
					maxlength : 30
				}
				
			},
			submitHandler : function(form) {
				parent.addSubmit(form);
			}
		});
		$("#submit").click(function() {
			$("#addForm").trigger("submit");
		});

		$("#roleReset").click(function() {
			$("#roleIds").val("");
			$("#roleNames").val("");

		});
		$(".close").click(function() {
			parent.$.nyroModalRemove();
		});
		$("#roleButton").nyroModal({
			forceType : "iframe",
			minWidth : 700,
			minHeight : 600,
			closeButton : null
		});//避免冲突
		

	});

	function setRoleInfo(roleId, roleName) {
		var roleIds = $("#roleIds").val();
		if (roleIds.indexOf(roleId) >= 0) {
			return;
		}
		var roleNames = $("#roleNames").val();
		if (roleIds.length > 0) {
			roleIds += "," + roleId;
			roleNames += "," + roleName;
		} else {
			roleIds = roleId;
			roleNames = roleName;
		}
		$("#roleIds").val(roleIds);
		$("#roleNames").val(roleNames);
	}
	
	
</script>
</head>
<body style="overflow-x: hidden">
	<form:form modelAttribute="pageModel" id="addForm" name="addForm"
		action="${rootUrl }acl/user/add.do" method="post">
		<div class="window">
			<dl class="title">
				<dd>用户管理--添加</dd>
				<dt>
					<a href="#none" class="close"><img
						src="${rootUrl }images/close.png" alt="关闭" /> </a>
				</dt>
			</dl>
			<table width="100%">
				<tr>
					<th width="20%"><font color="red">(*)</font>用户名:</th>
					<td id="loginTd"><form:input path="loginName" /></td>
				</tr>
				<tr>
					<th width="20%"><font color="red">(*)</font>密码:</th>
					<td><form:password path="password" /></td>
				</tr>
				<tr>
					<th width="20%"><font color="red">(*)</font>密码确认:</th>
					<td><input id="confirm_pw" name="confirm_pw" type="password" />
					</td>
				</tr>
				<tr>
					<th width="20%"><font color="red">(*)</font>姓名:</th>
					<td><form:input path="name" /></td>
				</tr>
				
				<tr>
					<th width="20%"><font color="red">(*)</font>角色:</th>
					<td><textarea name="roleNames" id="roleNames" rows="6"
							readonly="readonly"></textarea> <a id="roleButton"
						name="roleButton" href="${rootUrl }acl/user/roleSelect.dox"><img
							src="${rootUrl}images/btn/ck.gif" title="选择角色" alt="选择具体角色"
							border="0" /></a> <a id="roleReset" href="#"><img
							src="${rootUrl}images/btn/del.gif" title="清空" alt="清空所有角色"
							border="0" /></a><input type="hidden" name="roleIds" id="roleIds" /></td>
				</tr>
				<tr>
					<th width="20%"><font color="red">(*)</font>电子邮件:</th>
					<td><form:input path="email" maxlength="50" /></td>
				</tr>
				<tr>
					<th width="20%"><font color="red">(*)</font>手机号:</th>
					<td><form:input path="mobilephone" maxlength="50" /></td>
				</tr>
				<tr>
					<th width="20%"><font color="red">(*)</font>部门信息:</th>
					<td>
					
					   <select id="deptinfoParStr" onchange="ajaxDept()" name="deptinfoParStr">
							<option value="">全部</option>
							<c:forEach items="${parents}" var="vo">
								<option value="${vo.id}">${vo.deptName}</option>
							</c:forEach>
							
						</select>
						-
						<select id="deptinfoStr" name="deptinfoStr">
							
						</select>
					</td>
				</tr>

			</table>

			<div class="boxbtn">
				<div class="btn">
					<a href="#none" id="submit">保存</a>
				</div>
				<div class="btn">
					<a href="#none" class="close">取消</a>
				</div>
			</div>
			<br />
		</div>

	</form:form>
</body>
</html>