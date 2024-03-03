<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
<title>${siteName!""}-User Center</title>
<link rel="icon" href="/home/imgs/favicon.ico" type="image/x-icon">
<link media="all" href="/home/css/index.css" type="text/css" rel="stylesheet">
<link media="all" href="/home/css/user_center.css" type="text/css" rel="stylesheet">
</head>
<body>
  <#include "../common/top_header.ftl"/>
	<#include "../common/left_menu.ftl"/> 
    ﻿<div class="container">
    <div class="main center">
    <!-- Avatar setting -->
   <div class="head-img-box hide" id="head-img-box">
    <div class="wrap-head-img">
        <h3 class="head-title">
            <span>Avatar setting</span>
            <a id="close-head-img" class="close-head-img" href="javascript:;">X</a>
        </h3>
        <div class="head-img-area">
            <div class="wrap-img" id="wrap-img" style="position: relative;">
                <a href="javascript:;" id="upload-person-img" class="img-attr btn-upload-img " style="position: relative; z-index: 1;"><i>+</i>Upload</a>
                <p class="img-attr img-limit ">Support only JPG, PNG, and GIF format, Size less than 1MB</p>
                <img class="loading hide" src="/home/imgs/loading.gif" alt="" width="28" height="28">
	            <div id="" class="moxie-shim moxie-shim-html5" style="position: absolute; top: 0px; left: 0px; width: 0px; height: 0px; overflow: hidden; z-index: 0;">
	            	<input id="selected-file" onchange="uploadImg()" style="font-size: 999px; opacity: 0; position: absolute; top: 0px; left: 0px; width: 100%; height: 100%;" accept="image/*" capture="camera" type="file">
	            </div>
            </div>
        </div>
        <div class="head-img-footer">
            <a href="javascript:;" class="btn-ok" id="upload-ok">Save</a>
            <a href="javascript:;" id="cancel-img-box" class="btn-cancel">Cancel</a>
        </div>
    </div>
</div>
<!-- End Avatar setting -->
<div class="top clearfix" id ="user-top">
    <div id="user_msg">
        <div class="name" id="user_big_name">
            
        </div>
        
        <ul class="seller_attr">
            <li>Total Goods: <span id="goodsTotal">45</span></li>
            <li>Sold Goods: <span id = "soldGoodsTotal">4545</span></li>
            <li>Active Goods: <span id = "upGoodsTotal">4545</span></li>
            <li>Withdrawed Goods:<span id = "downGoodsTotal">4545</span></li>
            <li>Requests: <span id = "userpoint">${wantedGoodsList?size}</span></li>
            <li>Reported: <span id = "userpoint">${reportGoodsList?size}</span></li>
        </ul>
    </div>
    <div id="user_photo">
          	<#if fyp_student.headPic??>
          	<img id="origin_ph" onClick="openUploadPanel()" src="/photo/view?filename=${fyp_student.headPic}" old-src="/photo/view?filename=${fyp_student.headPic}" alt="Avatar"> 
			<#else>
			<img id="origin_ph" onClick="openUploadPanel()" src="/home/imgs/avatar1.png" old-src="/home/imgs/avatar1.png">
			</#if>
          <img id="change_photo" src="/home/imgs/person_hover.png" alt="Change avatar" style="display: none;">
    </div>
</div>
<ul id="middle_nav" class="clearfix">
    <li class="on"><a href="#">My Profile</a></li>
</ul>
<div class="pop-tip hide">
    <div class="pop-tip-area">
        <p class="pop-tip-txt">Changes saved!</p>
    </div>
</div>
        <div id="my_info">
            <div id="account_info">
            	<h2>Account info</h2>
				<ul class="infos" id="userid">
					<li>Account ID</li><li class="right_info">${fyp_student.id}</li>
				</ul>
			    <ul class="infos" id="userstate">
				    <li>Status</li>
				    <li class="right_info"><#if fyp_student.status == 1>Active<#else>Banned</#if></li>
				</ul>
			</div>
            <div id="account_info">
                <ul class="infos">
                    <li>Student ID</li>
                    <li class="right_info">${fyp_student.sn}</li>
                </ul>
            </div>
            <div id="base_info">
                <h2>Password management <span id="edit_pwd_info">Edit</span><span id="save_pwd_info">Save</span></h2>
                <ul class="infos">
                    <li>Old password</li>
                    <li class="right_info">
                        <span class="editPwd" id="old_pwd_span">******</span>
                        <input class="editPwd" value="" id="old-pwd" type="password">
                    </li> 
                </ul>
                <ul class="infos">
                    <li>New password</li>
                    <li class="right_info">
                        <span class="editPwd" id="new_pwd_span"></span>
                        <input class="editPwd" value="" id="new-pwd" type="password">
                    </li>
                </ul>
                <ul class="infos">
                    <li>Confirm password</li>
                    <li class="right_info">
                        <span class="editPwd" id="re_new_pwd_span"></span>
                        <input class="editPwd" value="" id="re-new-pwd" type="password">
                    </li>
                </ul>
            </div>
            <div id="base_info">
                <h2>Basic info <span id="edit_info">Edit</span><span id="save_info">Save</span></h2>
                <ul class="infos">
                    <li>Username</li>
                    <li class="right_info">
                        <span class="baseinfo" id="nickname_span">${fyp_student.nickname!""}</span>
                        <input class="baseinfo" value="${fyp_student.nickname!""}" id="nickname" type="text">
                    </li> 
                </ul>
                <ul class="infos">
                    <li>TEL</li>
                    <li class="right_info">
                        <span class="baseinfo" id="phone_span">${fyp_student.mobile!""}</span>
                        <input class="baseinfo" value="${fyp_student.mobile!""}" id="mobile" type="text">
                    </li>
                </ul>
                <ul class="infos">
                    <li>Academy</li>
                    <li class="right_info">
                        <span class="baseinfo" id="college_span">${fyp_student.academy!""}</span>
                        <input class="baseinfo" value="${fyp_student.academy!""}" id="academy" type="text">
                    </li>
                </ul>
                <ul class="infos">
                    <li>Year</li>
                    <li class="right_info">
                        <span class="baseinfo" id="grade_span">${fyp_student.grade!""}</span>
                        <input class="baseinfo" value="${fyp_student.grade!""}" id="grade" type="text">
                    </li>
                </ul>
                <ul class="infos">
                    <li>Department</li>
                    <li class="right_info">
                        <span class="baseinfo" id="area_span">${fyp_student.school!""}</span>
                        <input class="baseinfo" value="${fyp_student.school!""}" id="school" type="text">
                    </li>
                </ul>
            </div>
        </div>
        <ul id="middle_nav" class="clearfix">
   		<li class="on"><a href="">My active Goods</a></li>
		</ul>
        <div id="my_products">
              <div id="onsale_pro">
                <#if goodsList??>
                 <#list goodsList as goods>
                 <div class="enshr_each" id="">  
	                    <div class="enshr_info">
	                        <h2><a href="../goods/detail?id=${goods.id}" title="${goods.name}">${goods.name}</a></h2>
	                        <p style="overflow:hidden;">${goods.content}</p>
	                        <div class="enshr_state">
	                        <span id="prostate">
	                        	Status:
	                        	<#if goods.status ==1>
	                        	<font style="color:rgb(75, 192, 165);">Active</font>
	                        	<#elseif goods.status ==2>
	                        	<font style="color:red;">Withdrawed</font>
	                        	<#else>
	                        	<font style="color:#4BC00F;">Sold</font>
	                        	</#if>
	                        </span>|
	                        <span id="prostate">
	                        	If recommended:
	                        	<#if goods.recommend ==1>
	                        	<font style="color:rgb(75, 192, 165);">Yes</font>
	                        	<#else>
	                        	<font style="color:red;">No</font>
	                        	</#if>
	                        </span>
	                        &nbsp;&nbsp;<span id="prostate">Added time:${goods.updateTime}</span>
	                            <#if goods.status == 1>
	                            <span class="enshrine_it" onclick="sellout(${goods.id});">Confirm sold</span>
	                            <#elseif goods.status == 3>
	                            <span class="enshrine_it">Sold</span>
	                            </#if>
	                           	<#if goods.status == 1>
	                            <span class="enshrine_it make_edition" onclick="offshelf(${goods.id});">Withdraw</span>
	                            <#elseif goods.status == 2>
	                            <span class="enshrine_it make_edition" onclick="onshelf(${goods.id});" style="margin-right:30px;">Active</span>
	                            </#if>
	                            <#if goods.flag == 0>
	                            <span class="enshrine_it make_edition" onclick="refresh(${goods.id},1);">Flag</span>
	                            <#else>
	                            <span class="enshrine_it make_edition" onclick="refresh(${goods.id},0);">Cancel flag</span>
	                            </#if>
	                            <a href="edit_goods?id=${goods.id}" target="_top">
	                                <span class="enshrine_it  make_edition">Edit</span>
	                            </a>
	                            
	                        </div>
	                    </div>
	                    <a href="../goods/detail?id=${goods.id}">
	                        <img class="enshr_ph" src="/photo/view?filename=${goods.photo}" alt="${goods.name}">
	                    </a>
                </div>
                </#list>
                </#if>
              </div>
        </div>
        <ul id="middle_nav" class="clearfix">
   		<li class="on"><a href="">My requests</a></li>
		</ul>
        <div id="my_products">
              <div id="onsale_pro">
                <#if wantedGoodsList??>
                 <#list wantedGoodsList as wantedGoods>
                 <div class="enshr_each" id="">  
	                    <div class="enshr_info">
	                        <h2><a href="" title="${wantedGoods.name}">${wantedGoods.name}</a></h2>
	                        <p style="overflow:hidden;">${wantedGoods.content}</p>
	                        <div class="enshr_state">
	                        &nbsp;&nbsp;
	                        <span id="prostate">Publish time: ${wantedGoods.updateTime}</span>
                            <a href="javascript:void(0)" onClick="delWanted(${wantedGoods.id})" target="_top">
                                <span class="enshrine_it  make_edition" style="margin-right:30px;">Delete</span>
                            </a>
                            <a href="edit_wanted_goods?id=${wantedGoods.id}" target="_top">
                                <span class="enshrine_it  make_edition" style="margin-right:30px;">Edit</span>
                            </a>
	                        </div>
	                    </div>
	                    <a href="">
	                        <#if wantedGoods.student.headPic??>
	                        <img class="enshr_ph" src="/photo/view?filename=${wantedGoods.student.headPic}" alt="${wantedGoods.name}">
	                        <#else>
	                        <img class="enshr_ph" src="/home/imgs/avatar1.png" alt="${wantedGoods.name}">
	                        </#if>
	                    </a>
                </div>
                </#list>
                </#if>
              </div>
        </div>
        <ul id="middle_nav" class="clearfix">
   		<li class="on"><a href="">My reports</a></li>
		</ul>
        <div id="my_products">
              <div id="onsale_pro">
                <#if reportGoodsList??>
                 <#list reportGoodsList as reportGoods>
                 <div class="enshr_each" id="">  
	                    <div class="enshr_info">
	                        <h2><a href="../goods/detail?id=${reportGoods.goods.id}" title="${reportGoods.goods.name}">${reportGoods.goods.name}</a></h2>
	                        <p style="overflow:hidden;">${reportGoods.goods.content}</p>
	                        <div class="enshr_state">
	                        &nbsp;&nbsp;<span id="prostate">Report time: ${reportGoods.createTime}</span>
	                        &nbsp;&nbsp;<span id="prostate">Report reason: ${reportGoods.content}</span>
                            <a href="javascript:void(0)" onClick="delReport(${reportGoods.id})" target="_top">
	                            <span class="enshrine_it  make_edition" style="margin-right:30px;">Delete</span>
	                        </a>
	                        </div>
	                    </div>
	                    <a href="../goods/detail?id=${reportGoods.goods.id}">
	                        <img class="enshr_ph" src="/photo/view?filename=${reportGoods.goods.photo}" alt="${reportGoods.goods.name}">
	                    </a>
                </div>
                </#list>
                </#if>
              </div>
        </div>
    </div>
</div>
 	<#include "../common/right_menu.ftl"/>
	<#include "../common/bottom_footer.ftl"/> 
<script  src="/home/js/jquery-3.1.1.min.js"></script>
<script src="/home/js/common.js"></script>
<script src="/home/js/user_center.js"></script>
<script src="/home/js/add.js"></script>
<script>
function delWanted(id){
	if (!confirm('Are you sure you want to delete')) {
        return;
    }
    ajaxRequest('delete_wanted','post',{id:id},function(){
		alert('Delete successfully');
		window.location.reload();
	});
}
function delReport(id){
	if (!confirm('Are you sure you want to delete?')) {
        return;
    }
    ajaxRequest('delete_report','post',{id:id},function(rst){
		alert('Delete successfully');
		window.location.reload();
	});
}
$(document).ready(function(){
	ajaxRequest('get_stats','post',{},function(rst){
		$("#goodsTotal").text(rst.data.goodsTotal);
		$("#soldGoodsTotal").text(rst.data.soldGoodsTotal);
		$("#downGoodsTotal").text(rst.data.downGoodsTotal);
		$("#upGoodsTotal").text(rst.data.upGoodsTotal);
	});
	$("#edit_pwd_info").bind('click',function(){
        $(this).css({
            display: "none"
        })
        $("#save_pwd_info").css({
            display: "block"
        })
        $(".right_info span.editPwd").css({
            display: "none"
        })
        $(".right_info input.editPwd").css({
            display: "inline"
        })
    });
    //Submit password changing
    $("#save_pwd_info").bind('click',function(){
        var oldPwd = $("#old-pwd").val();
        var newPwd = $("#new-pwd").val();
        var reNewPwd = $("#re-new-pwd").val();
        if(oldPwd == ''){
        	alert('Please enter Old password');
        	return ;
        }
        if(newPwd == ''){
        	alert('Please enter New password');
        	return ;
        }
        if(reNewPwd == ''){
        	alert('Please confirm New password');
        	return ;
        }
        if(reNewPwd != newPwd){
        	alert('Two passwords are different!');
        	return ;
        }
        if(newPwd.length <6 ){
        	alert('Password need to be longer than 6 digits');
        	return ;
        }
        ajaxRequest('edit_pwd','post',{oldPwd:oldPwd,newPwd:newPwd},function(rst){
			alert('Password changed successfully!');
			$("#save_pwd_info").css({
	            display: "none"
	        })
	        $("#edit_pwd_info").css({
	            display: "block"
	        })
	        $(".right_info span.editPwd").css({
	            display: "inline"
	        })
	        $(".right_info input.editPwd").css({
	            display: "none"
	        })
		});
        
    });
});
</script>
</html>