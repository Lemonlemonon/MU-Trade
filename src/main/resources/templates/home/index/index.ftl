<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
<title>${siteName!""}</title>
<link rel="icon" href="/home/imgs/favicon.ico" type="image/x-icon">
<link media="all" href="/home/css/index.css" type="text/css" rel="stylesheet">
</head>
<body>
  <#include "../common/top_header.ftl"/>
	<#include "../common/left_menu.ftl"/> 
    <div class="container">
   		<div class="main center">
                <div class="wrap-site mt20">
            <div class="recom-title"></div>
            <ul class="recom-list group">
                <#if newsList??>
                <#list newsList as news>
                <li><a href="/home/index/news_detail?id=${news.id}" target="_top">${news.title}</a></li>
                </#list>
                </#if>
            </ul>
        </div>
        <div class="label-wr center clearfix">
            <div id="nav-labels">
                <button id="new_pro" class="labels" onclick="" ></button>
            </div>
           
        </div>
        
        <div class="item-list">
            <ul class="items clearfix">
            
            	<#if pageBean.content??>
            	<#list pageBean.content as ads>
            	<li class="item">
                    <a href="../ads/detail?id=${ads.id}" class="img" target="_top">
                    	<img src="/photo/view?filename=${ads.photo}" alt="${ads.name}"></a>
                    <div class="info">
                        <div class="price">${ads.sellPrice}</div>
                        <div class="name">
                            <a href="../ads/detail?id=${ads.id}" target="_top">${ads.name}</a>
                        </div>
                        <div class="department"><span>${ads.student.academy}</span></div>
                        <div class="place"><span>${ads.student.school}</span></div>
                        <#if ads.recommend == 1>
                        <div class="school"><span>Recommendation</span></div>
                        </#if>
                     </div>
                </li>
                </#list>
            	</#if>
            </ul>
        </div>
        <#if pageBean.total gt 0>
        <!-- Page jump Start -->
        <div class="pages">
            <#if pageBean.currentPage == 1>
            <a class="page-arrow arrow-left" href="javascript:void(0)">First</a>
            <#else>
            <a class="page-arrow arrow-left" href="/home/index/index?name=${name!""}&currentPage=1">First</a>
            </#if>
            <#list pageBean.currentShowPage as showPage>
	        <#if pageBean.currentPage == showPage>
	        <a class="page-num cur" href="javascript:void(0)">${showPage}</a>
	        <#else>
            <a class="page-num " href="/home/index/index?name=${name!""}&currentPage=${showPage}">${showPage}</a>
           	</#if>
           	</#list>
           	<#if pageBean.currentPage == pageBean.totalPage>
            <a class="page-arrow arrow-right" href="javascript:void(0)">Last</a>
            <#else>
	        <a class="page-arrow arrow-right" href="/home/index/index?name=${name!""}&currentPage=${pageBean.totalPage}">Last</a>
	        </#if>
        </div>
        <!-- Page jump End -->
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