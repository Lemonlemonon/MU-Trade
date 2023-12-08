<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
<title>${siteName!""}|Site setting</title>
<#include "../common/header.ftl"/>

</head>
  
<body>
<div class="lyear-layout-web">
  <div class="lyear-layout-container">
    <!---Left-side navigation-->
    <aside class="lyear-layout-sidebar">
      
      <!-- logo -->
      <div id="logo" class="sidebar-header">
        <a href="index.html"><img src="/admin/images/logo-sidebar.png" title="${siteName!""}" alt="${siteName!""}" /></a>
      </div>
      <div class="lyear-layout-sidebar-scroll"> 
        <#include "../common/left-menu.ftl"/>
      </div>
      
    </aside>
    <!--End -Left-side navigation-->
    
    <#include "../common/header-menu.ftl"/>
    
     <!--Main content-->
    <main class="lyear-layout-content">
      
      <div class="container-fluid">
        
        <div class="row">
          <div class="col-lg-12">
            <div class="card">
              <div class="card-header"><h4>Site config</h4></div>
              <div class="card-body">
                <form action="add" id="site-setting-form" method="post" class="row">
                  <input type="hidden" name="id" value="<#if siteSetting??>${siteSetting.id}</#if>">
                  <div class="form-group col-md-12">
                    <label>LOGO1 Upload</label>
                    <div class="form-controls">
                      <ul class="list-inline clearfix lyear-uploads-pic">
                        <li class="col-xs-4 col-sm-3 col-md-2">
                          <figure>
                            <#if siteSetting??>
                            <#if siteSetting.logo1??>
                            <img src="/photo/view?filename=${siteSetting.logo1}" id="show-logo1-img" >
                          	<#else>
                            <img src="/home/imgs/index_logo.png" id="show-logo1-img" alt="Default logo1">
                          	</#if>
                          	<#else>
                            <img src="/home/imgs/index_logo.png" id="show-logo1-img" alt="Default logo1">
                          	</#if>
                          </figure>
                        </li>
                        <input type="hidden" name="logo1" id="logo1" value="<#if siteSetting??><#if siteSetting.logo1??>${siteSetting.logo1}</#if></#if>">
                        <input type="file" id="select-file1" style="display:none;" onchange="uploadMuilt('show-logo1-img','logo1','select-file1')">
                        <li class="col-xs-4 col-sm-3 col-md-2">
                          <a class="pic-add" id="add-pic-btn1" href="javascript:void(0)" title="Click to upload"></a>
                        </li>
                      </ul>
                    </div>
                  </div>
                  <div class="form-group col-md-12">
                    <label>LOGO2 Upload</label>
                    <div class="form-controls">
                      <ul class="list-inline clearfix lyear-uploads-pic">
                        <li class="col-xs-4 col-sm-3 col-md-2">
                          <figure>
                            <#if siteSetting??>
                            <#if siteSetting.logo1??>
                            <img src="/photo/view?filename=${siteSetting.logo2}" id="show-logo2-img" >
                          	<#else>
                            <img src="/home/imgs/2shoujie_web_title.png" id="show-logo2-img" alt="Default logo2">
                          	</#if>
                          	<#else>
                            <img src="/home/imgs/2shoujie_web_title.png" id="show-logo2-img" alt="Default logo2">
                          	</#if>
                          </figure>
                        </li>
                        <input type="hidden" name="logo2" id="logo2" value="<#if siteSetting??><#if siteSetting.logo2??>${siteSetting.logo2}</#if></#if>">
                        <input type="file" id="select-file2" style="display:none;" onchange="uploadMuilt('show-logo2-img','logo2','select-file2')">
                        <li class="col-xs-4 col-sm-3 col-md-2">
                          <a class="pic-add" id="add-pic-btn2" href="javascript:void(0)" title="Click to upload"></a>
                        </li>
                      </ul>
                    </div>
                  </div>
                  <div class="form-group col-md-12">
                    <label>QR code upload</label>
                    <div class="form-controls">
                      <ul class="list-inline clearfix lyear-uploads-pic">
                        <li class="col-xs-4 col-sm-3 col-md-2">
                          <figure>
                            <#if siteSetting??>
                            <#if siteSetting.logo1??>
                            <img src="/photo/view?filename=${siteSetting.qrcode}" id="show-qrcode-img" >
                          	<#else>
                            <img src="/home/imgs/wx-fl-qrcode.png" id="show-qrcode-img" alt="Default qr">
                          	</#if>
                          	<#else>
                            <img src="/home/imgs/wx-fl-qrcode.png" id="show-qrcode-img" alt="Default qr">
                          	</#if>
                          </figure>
                        </li>
                        <input type="hidden" name="qrcode" id="qrcode" value="<#if siteSetting??><#if siteSetting.qrcode??>${siteSetting.qrcode}</#if></#if>">
                        <input type="file" id="select-file3" style="display:none;" onchange="uploadMuilt('show-qrcode-img','qrcode','select-file3')">
                        <li class="col-xs-4 col-sm-3 col-md-2">
                          <a class="pic-add" id="add-pic-btn3" href="javascript:void(0)" title="Click to upload"></a>
                        </li>
                      </ul>
                    </div>
                  </div>
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Site name</span>
                    <input type="text" class="form-control required" id="siteName" name="siteName" value="<#if siteSetting??><#if siteSetting.siteName??>${siteSetting.siteName}</#if></#if>" placeholder="Please enter Site name" tips="Please enterSite name" />
                  </div>
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Site URL</span>
                    <input type="text" class="form-control required" id="siteUrl" name="siteUrl" value="<#if siteSetting??><#if siteSetting.siteUrl??>${siteSetting.siteUrl}</#if></#if>" placeholder="Please enter Site URL" tips="Please enterSite URL" />
                  </div>
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Site right</span>
                    <input type="text" class="form-control required" id="allRights" name="allRights" value="<#if siteSetting??><#if siteSetting.allRights??>${siteSetting.allRights}</#if></#if>" placeholder="Please enter Site right" tips="Please enterSite right"/>
                  </div>
                  
                  <div class="form-group col-md-12">
                    <button type="button" class="btn btn-primary ajax-post" id="add-form-submit-btn">Confirm</button>
                    <button type="button" class="btn btn-default" onclick="javascript:history.back(-1);return false;">Cancel</button>
                  </div>
                </form>
       
              </div>
            </div>
          </div>
          
        </div>
        
      </div>
      
    </main>
    <!--End Main content-->
  </div>
</div>
<#include "../common/footer.ftl"/>
<script type="text/javascript" src="/admin/js/perfect-scrollbar.min.js"></script>
<script type="text/javascript" src="/admin/js/main.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	//Submit button listening event
	$("#add-form-submit-btn").click(function(){
		if(!checkForm("site-setting-form")){
			return;
		}
		var data = $("#site-setting-form").serialize();
		$.ajax({
			url:'save_setting',
			type:'POST',
			data:data,
			dataType:'json',
			success:function(data){
				if(data.code == 0){
					showSuccessMsg('Site setting updated!',function(){
						window.location.reload();
					})
				}else{
					showErrorMsg(data.msg);
				}
			},
			error:function(data){
				alert('Network error!');
			}
		});
	});
	//Image upload button listening event
	$("#add-pic-btn1").click(function(){
		$("#select-file1").click();
	});
	$("#add-pic-btn2").click(function(){
		$("#select-file2").click();
	});
	$("#add-pic-btn3").click(function(){
		$("#select-file3").click();
	});
});

</script>
</body>
</html>