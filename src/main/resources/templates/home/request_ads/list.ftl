<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
<title>${siteName!""}--Requests Lobby</title>
<link rel="icon" href="/home/imgs/favicon.ico" type="image/x-icon">
<link media="all" href="/home/css/want_list.css" type="text/css" rel="stylesheet">
<link media="all" href="/home/css/index.css" type="text/css" rel="stylesheet">
</head>
<body>
  <#include "../common/top_header.ftl"/>
	<#include "../common/left_menu.ftl"/> 
    <div class="container">
            <div class="main center clearfix">
                <div class="want-title"></div>
                <div class="wrap-want-list">
                    <ul class="want-list" id="want-list">
                        <#if pageBean.content??>
                        <#list pageBean.content as requestAds>
                        <li class="want-item">
                        	<div class="want-li clearfix">
                        		<div class="left">
                        			<a href="">
                        				<#if requestAds.student.headPic??>
                        				<img src="/photo/view?filename=${requestAds.student.headPic}" alt="Avatar">
                        				<#else>
                        				<img src="/home/imgs/avatar1.png" alt="Avatar">
                        				</#if>
                        			</a>
                        		</div>
                        		<div class="right">
                        			<h4 class="want-name">[Requests]
                        				<span>${requestAds.name}</span>
                        			</h4>
                        			<p class="want-detail">${requestAds.content}</p>
                        			<p class="want-attr">
                        				<span>Expecting price</span>
                        				<span class="want-price">€${requestAds.sellPrice}</span>
                        				<span>Trade location: </span>
                        				<span class="want-add">${requestAds.tradePlace}</span>
                        				<span>${requestAds.createTime}</span>
                        			</p>
                        			<p class="want-contact">
                        				<p class="want-person">${requestAds.student.nickname!""}</p>
                        				<span class="mr10">TEL：${requestAds.student.mobile!""}</span>
                        			</p>
                        		</div>
                        	</div>
                        </li>
                        </#list>
                        </#if>
                    </ul>
                </div>
                <#if pageBean.total gt 0>
		        <!-- Pagination -->
		        <div class="pages">
		            <#if pageBean.currentPage == 1>
		            <a class="page-arrow arrow-left" href="javascript:void(0)">First</a>
		            <#else>
		            <a class="page-arrow arrow-left" href="list?currentPage=1">First</a>
		            </#if>
		            <#list pageBean.currentShowPage as showPage>
			        <#if pageBean.currentPage == showPage>
			        <a class="page-num cur" href="javascript:void(0)">${showPage}</a>
			        <#else>
		            <a class="page-num " href="list?currentPage=${showPage}">${showPage}</a>
		           	</#if>
		           	</#list>
		           	<#if pageBean.currentPage == pageBean.totalPage>
		            <a class="page-arrow arrow-right" href="javascript:void(0)">Last</a>
		            <#else>
			        <a class="page-arrow arrow-right" href="list?currentPage=${pageBean.totalPage}">Last</a>
			        </#if>
		        </div>
		        <!-- End pagination -->
		        </#if>
            </div>
        </div>
<div class="return-to-top"><a href="#"></a></div><!--Back to top-->
 	<#include "../common/right_menu.ftl"/>
	<#include "../common/bottom_footer.ftl"/> 
<script  src="/home/js/jquery-3.1.1.min.js"></script>
<script src="/home/js/common.js"></script>
<script src="/home/js/index.js"></script>
</html>