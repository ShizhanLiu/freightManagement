<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="../../baselist.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <style type="text/css">
        span {
            display: inline-block;
            width: 200px
        }
    </style>
</head>

<body>
<form name="icform" method="post">
    <input type="hidden" name="id" value="${userInfoP.userInfoId}"/>

    <div id="menubar">
        <div id="middleMenubar">
            <div id="innerMenubar">
                <div id="navMenubar">
                    <ul>
                        <li id="save"><a href="#" onclick="formSubmit('/user/assignRole','_self');this.blur();">保存</a>
                        </li>
                        <li id="back"><a href="#" onclick="formSubmit('/user/list','_self');this.blur();">返回</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="textbox" id="centerTextbox">
        <div class="textbox-header">
            <div class="textbox-inner-header">
                <div class="textbox-title">
                    用户 [${userInfoP.name}] 角色列表
                </div>
            </div>
        </div>
    </div>
    <div>


        <div style="text-align:left">
            <c:forEach items="${rs}" var="o">
		<span style="padding:3px;">
		<input type="checkbox" name="roleIds" value="${o.roleId}" class="input"
               <c:if test="${fn:contains(roleIds,o.roleId)}">checked</c:if>
        >
		${o.name}
		</span>
            </c:forEach>

        </div>

    </div>


</form>
</body>
</html>

