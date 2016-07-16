<%@page import="com.jfok.cfcmms.hibernate.system.setting._SystemsetAddition"%>
<%@page import="com.jfok.cfcmms.hibernate.system.setting._Systeminfo"%>
<%@page import="com.jfok.cfcmms.controller.LoginController"%>
<%@page import="org.apache.commons.lang.BooleanUtils"%>
<%@page import="com.jfok.cfcmms.util.TypeChange"%>
<%@page import="com.jfok.cfcmms.service.SystemInfoService"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%
	// 是否允许二周内自动登录
		_Systeminfo si = SystemInfoService.getSysteminfo();
		_SystemsetAddition sa = SystemInfoService.getSystemsetAddition();
		Boolean allowTwoWeeks = BooleanUtils.isTrue(si.getTf_allowautoLoginInTwoWeeks());
		// 是否需要验证码
		Boolean needValidateCode = BooleanUtils.isTrue(si.getTf_needIdentifingCode());
		// 是否一直需要验证码，如果这个为false,上一个为true,那么在输入错一次密码之后才启用验证码
		Boolean alwaysNeedCode = BooleanUtils.isTrue(si.getTf_alwaysNeedIdentifingCode());
		// 总是需要验证码，或者是需要验证码，并且录入一次密码错误以后
		Boolean needCode = alwaysNeedCode
				|| (LoginController.getLoginTimes(request.getSession()) >= 1 && needValidateCode);
%>
<!-- index.jsp -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="Shortcut Icon" href="images/system/favicon.png"
	type="image/png" />
<title><%=SystemInfoService.getSysteminfo().getTf_systemName()%>登录</title>
<script type="text/javascript">
	function keypress(e) {
		e = e || event;
		var keycode = e.keyCode;
		if (keycode == 13) {
			if (e.target.id == "n") {
				$("p").focus();
				return false;
			} else if (e.target.id == "p") {
				if ($("cg").className == "")
					$("c").focus();
				else {
					$("b").focus();
					login();
					}
				return false;
			} else if (e.target.id == "c") {
				$("b").focus();
				return false;
			}
		}
	}
	document.onkeypress = keypress;

	function reloadValidateCode() {
		var img = $("cImg");
		img.src = "login/validateCode.do?_dc=" + new Date().getTime();
		$("c").value = "";
		$("c").focus();
		validateok = null;
		addClass($("vr"), "loginhidden");
	};

	function checkloginname(notshowerror) {
		var loginname = $("n").value;
		if (loginname || notshowerror) {
			removeClass($("ng"), "has-error");
			addClass($("ne"), "loginhidden");
		} else {
			addClass($("ng"), "has-error");
			removeClass($("ne"), "loginhidden");
		}
		return loginname;
	}

	function checkpassword(notshowerror) {
		var pd = $("p").value;
		if (pd || notshowerror) {
			removeClass($("pg"), "has-error");
			addClass($("pe"), "loginhidden");
		} else {
			addClass($("pg"), "has-error");
			removeClass($("pe"), "loginhidden");
		}
		return pd;
	}

	var lastcode;
	var validateok = null;
	function codeValidate() {
		var code = $("c").value;
		if (code.length == 4) {
			if (lastcode != code) {
				lastcode = code;
				ajaxrequest("login/checkValidateCode.do", "post", false, "code="
						+ $("c").value, cvcallback);
			}
			;
		} else {
			lastcode = code;
			validateok = null;
			addClass($("vr"), "loginhidden");
		}
		;
	}

	function cvcallback(s) {
		var i = $("vr").getElementsByTagName("i")[0];
		if (s.responseText == 1) {
			validateok = true;
			i.className = "fa fa-smile-o fa-fw";
		} else {
			validateok = false;
			i.className = "fa fa-frown-o fa-fw";
		}
		removeClass($("vr"), "loginhidden");
	}

	function check() {
		clearErrorMsg();
		var loginname = checkloginname();
		var password = checkpassword();
		if (loginname) {
			if (!password)
				$("p").focus();
		} else
			$("n").focus();
		if ($("cg").className == "") {
			if (validateok === null)
				updateErrorMsg("请输入验证码！");
			if (validateok === false)
				updateErrorMsg("验证码错误！");
		}
		return loginname
				&& password
				&& (($("cg").className != "") || ($("cg").className == "")
						&& validateok);
	};

	function login() {
		if (check()) {
			clearErrorMsg();
			var str = "loginname=" + encodeURIComponent($("n").value) + "&password="
					+ encodeURIComponent($("p").value) + "&validateCode=" + $("c").value
					+ "&saveloginname=" + savename +
<%if (allowTwoWeeks) {%>
	"&twoweek=" + twoweek +
<%}%>
	"&_dc=" + new Date().getTime();
			ajaxrequest("login/login.do", "get", false, str, logincallback, document);
		}
		;
	}

	function logincallback(s) {
		var result = eval("\u0028" + s.responseText + "\u0029");
		if (result.success) {
			window.location.href = "index.jsp";
		} else {
<%if (needValidateCode) {%>
	$("cg").className = "";
<%}%>
	updateErrorMsg(result.msg);
		}
		;
	}

	function bodyonload() {
		var n = getCookies("loginname", "");
		$("n").value = n;
		if (n)
			$("p").focus();
		else
			$("n").focus();
		$("c").value = "";
		var scook = getCookies("savename");
		if (typeof (scook) == 'undefined')
			savename = false;
		else
			savename = !(scook == 'true');
		togglesavename();
<%if (allowTwoWeeks) {%>
	twoweek = !(getCookies("twoweek") == "true");
		toggletwoweek();
<%}%>
	}

	var savename;
	function togglesavename() {
		savename = !savename;
		$("savename").className = "fa fa" + (savename ? "-check" : "") + "-square"
				+ (savename ? "" : "-o") + " fa-fw";
	}
<%if (allowTwoWeeks) {%>
	var twoweek = false;
	function toggletwoweek() {
		var tid = $("twoweek");
		if (tid) {
			twoweek = !twoweek;
			tid.className = "fa fa" + (twoweek ? "-check" : "") + "-square"
					+ (twoweek ? "" : "-o") + " fa-fw";
		}
		;
	}
<%}%>

	function fg(){
		updateErrorMsg("<%out.print(sa.getTf_userforgetPassword().replaceAll("\"", "'"));%>");
	}

	function clearErrorMsg() {
		updateErrorMsg("");
	}

	function updateErrorMsg(mess) {
		var em = $("em");
		em.innerText = mess;
		em.textContent = mess;
	}

	function hasClass(obj, cls) {
		return obj.className.match(new RegExp("(\\s|^)" + cls + "(\\s|$)"));
	}

	function addClass(obj, cls) {
		if (!this.hasClass(obj, cls))
			obj.className += " " + cls;
	}

	function removeClass(obj, cls) {
		if (hasClass(obj, cls)) {
			var reg = new RegExp("(\\s|^)" + cls + "(\\s|$)");
			obj.className = obj.className.replace(reg, " ");
		}
	}

	function $(id) {
		return document.getElementById(id);
	}

	function getCookies(name, defaultValue) {
		var cookies = document.cookie.split('; ');
		var i = cookies.length, cookie, value = null;
		while (i--) {
			cookie = cookies[i].split('=');
			if (cookie[0] === name) {
				value = unescape(cookie[1]);
			}
			;
		}
		return value || defaultValue;
	};

	/**
	 * 得到ajax对象
	 */
	function getajaxHttp() {
		var xmlHttp;
		try {
			xmlHttp = new XMLHttpRequest();
		} catch (e) {
			try {
				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {
					return false;
				}
			}
		}
		return xmlHttp;
	}
<%/**
			 * 发送ajax请求
			 * url--url
			 * methodtype(post/get)
			 * con (true(异步)|false(同步))
			 * parameter(参数)
			 * functionName(回调方法名，不需要引号,这里只有成功的时候才调用)
			 * (注意：这方法有二个参数，一个就是xmlhttp,一个就是要处理的对象)
			 * obj需要到回调方法中处理的对象
			 */%>
	function ajaxrequest(url, methodtype, con, parameter, functionName, obj) {
		var xmlhttp = getajaxHttp();
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4) {
				if (xmlhttp.status == 200)
					functionName(xmlhttp, obj);
			}
		};
		xmlhttp.open(methodtype, url + "?" + parameter, con);
		try {
			xmlhttp.send();
		} catch (e) {
			updateErrorMsg("网络故障或服务器未响应，请刷新网页重试！");
		}

	}
</script>

<link type="text/css" rel="stylesheet" href="css/login.css">
<!-- 引入Font Awesome的css文件 -->
<link type="text/css" rel="stylesheet" href="css/font-awesome.css">

</head>

<body onload="javascript:bodyonload()">

	<table width="100%" height="100%" border="0">

		<tr height="20%">
			<td>
				<table>
					<tr>
						<td width="50%"></td>
						<td class="logintitle"><img src="images/system/favicon36.png">
							<%=SystemInfoService.getSysteminfo().getTf_systemName()%></td>
						<td width="50%" class="loginver">(Ver:<%=SystemInfoService.getSysteminfo().getTf_systemVersion()%>)
						</td>
					</tr>
				</table>

			</td>
		</tr>
		<tr>
			<td>
				<div class="loginform">

					<form name="loginForm" onsubmit="return check();" method="post">
						<div style="padding-left: 30px;">
							<div class="margin-bottom-lg">
								<span style="font-size: large;">系统登录</span>
							</div>

							<div class="input-group margin-bottom-sm" id="ng">
								<span class="input-group-addon l"><i
									class="fa fa-user fa-fw"></i></span> <input style="width: 180px;"
									class="form-control r" name="loginname" id="n" type="text"
									maxlength="20" placeholder="" onkeyup="checkloginname(true);">
								<span id="ne" class="loginhidden"><div
										class="control-label" style="margin-top: 10px;">
										<i class="fa fa-info-circle fa-fw"></i>用户名
									</div></span>

							</div>

							<div class="input-group margin-bottom-sm" id="pg">
								<span class="input-group-addon l"><i
									class="fa fa-key fa-fw"></i></span> <input style="width: 180px;"
									class="form-control r" name="password" id="p" type="password"
									maxlength="20" placeholder="" onkeyup="checkpassword(true);">
								<span id="pe" class="loginhidden"><div
										class="control-label" style="margin-top: 10px;">
										<i class="fa fa-info-circle fa-fw"></i>密码
									</div></span>
							</div>

							<div class="<%if (!needCode)
		out.print("loginhidden");%>"
								id="cg">
								<div class="input-group margin-bottom-sm">
									<span class="input-group-addon l"><i
										class="fa fa-chain fa-fw"></i></span> <input style="width: 80px;"
										class="form-control r" maxlength="4" name="validateCode"
										id="c" type="text" placeholder="" onkeyup="codeValidate()">
									<span class="loginhidden" id="vr"> <i
										class="fa fa-smile-o fa-fw"></i>
									</span> <img style="margin: 2px;" id="cImg"
										src="login/validateCode.do" />&nbsp;&nbsp;<a href="#"
										onclick="javascript:reloadValidateCode();"><span
										style="font-size: small;">看不清?</span></a>
								</div>
							</div>

							<div class="input-group margin-bottom-sm"
								style="margin-top: 10px; margin-left: 40px;">

								<span> <i id="savename" class="fa fa-square-o fa-fw"
									onclick="togglesavename();"></i>保存用户名
								</span>
							</div>
							<%
								if (allowTwoWeeks) {
							%>
							<div class="input-group margin-bottom-sm"
								style="margin-top: 10px; margin-left: 40px;">
								<span> <i id="twoweek" class="fa fa-square-o fa-fw"
									onclick="toggletwoweek();"></i>二周内自动登录
								</span>
							</div>
							<%
								}
							%>
						</div>
					</form>
					<div
						style="width: 280px; margin-top: 20px; margin-right: auto; margin-left: auto; text-align: center;">
						<button style="width: 160px;" class="btn btn-lg btn-primary"
							onclick="javascript:login();" id="b">
							<i class="fa fa-sign-in"></i>&nbsp;&nbsp;&nbsp;登&nbsp;&nbsp;&nbsp;录
						</button>
					</div>
					<div class="fogretpassword">
						<a href="#" onclick="fg();">忘记密码?</a>
					</div>
					<div class="loginerrormess" id="em">&nbsp;</div>
				</div>
			</td>
		</tr>
		<tr height="80%">
			<td>
				<table border="0" height="100%" width="100%">
					<tr height="100%">
						<td width="100%"></td>
					</tr>
					<tr>
						<td class="loginbottominfo"><span class="section">
								使用单位：<%=SystemInfoService.getSystemset().getTf_userdwmc()%></span><span
							class="section"> 开始使用时间：<%=TypeChange.DateToString(SystemInfoService.getSystemset().getTf_userStartdate())%></span></td>
					</tr>
					<tr height="10px">
						<td></td>
					</tr>
					<tr>
						<td class="loginbottominfo"><span class="section">服务单位：<%=SystemInfoService.getSystemset().getTf_serviceDepartment()%></span>
							<span class="section">服务人员：<%=SystemInfoService.getSystemset().getTf_serviceMen()%>
								&nbsp;&nbsp; <%=SystemInfoService.getSystemset().getTf_serviceTelnumber()%>
						</span> <%
 	String email = SystemInfoService.getSystemset().getTf_serviceEmail();
 	if (email != null && email.length() > 1) {
 	out.println(
 		"<span class=\"section\">eMail：<a href=\"mailto:" + email + "\">" + email + "</span>");
 	}
 %></td>
					</tr>
					<tr height="10px">
						<td></td>
					</tr>
					<tr>
						<td class="loginbottominfo"><%=SystemInfoService.getSysteminfo().getTf_copyrightInfo()%></td>
					</tr>
					<tr height="40px">
						<td></td>
					</tr>
				</table>
			</td>
		</tr>

	</table>



</body>
</html>
