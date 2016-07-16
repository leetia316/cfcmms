<%@page import="com.jfok.cfcmms.service.SystemInfoService"%>
<!DOCTYPE HTML>
<html manifest="">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<script type="text/javascript" src="gojs/go-debug.js"></script>

<title><%=SystemInfoService.getSysteminfo().getTf_systemName()%></title>


<script type="text/javascript">
	var Ext = Ext || {}; // Ext namespace won't be defined yet...

	// This function is called by the Microloader after it has performed basic
	// device detection. The results are provided in the "tags" object. You can
	// use these tags here or even add custom tags. These can be used by platform
	// filters in your manifest or by platformConfig expressions in your app.
	//
	Ext.beforeLoad = function(tags) {
		var s = location.search, // the query string (ex "?foo=1&bar")
		profile;

		var theme = location.href.match(/theme=([\w-]+)/), locale = location.href
				.match(/locale=([\w-]+)/);

		theme = (theme && theme[1]);
		locale = (locale && locale[1]) || 'en';
		if (!theme)
			theme = getCookie('theme','triton');
		console.log('加载系统主题方案:' +theme);
		Ext.manifest = theme+'.json'; // this name must match a build profile name

	};
	
	
	function getCookie(name, defaultValue) {
		var cookies = document.cookie.split('; ');
		var i = cookies.length, cookie, value = null;
		while (i--) {
			cookie = cookies[i].split('=');
			if (cookie[0] === name) {
				value = unescape(cookie[1]);
			}
		}
		return value || defaultValue;
	};
	
	
</script>



<!-- The line below must be kept intact for Sencha Cmd to build your application -->
<link type="text/css" rel="stylesheet" href="css/all.css">

<script id="microloader" data-app="8f39b16c-5533-4a1b-9af6-560268917065"
	type="text/javascript" src="bootstrap.js"></script>

</head>
<body>
	<div id="loading">
		<div class="loading-indicator">
			<img src="images/system/large-loading.gif" width="32" height="32" />
			<%=SystemInfoService.getSysteminfo().getTf_systemName()%>
			<br /> <span id="loading-msg">Loading...</span>
		</div>
	</div>
	<a target="_blank" href="" id="__qq__"></a>
</body>
</html>
