<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
<title>${siteName!""}|Category management-Edit category</title>
<#include "../common/header.ftl"/>

</head>
  
<body>
<div class="lyear-layout-web">
  <div class="lyear-layout-container">
    <!--Left-side navigation-->
    <aside class="lyear-layout-sidebar">
      
      <!-- logo -->
      <div id="logo" class="sidebar-header">
        <a href="/home/index/index"><img src="/admin/images/logo-sidebar.png" title="${siteName!""}" alt="${siteName!""}" /></a>
      </div>
      <div class="lyear-layout-sidebar-scroll"> 
        <#include "../common/left-menu.ftl"/>
      </div>
      
    </aside>
    <!--End Left-side navigation-->
    
    <#include "../common/header-menu.ftl"/>
    
     <!--Main content-->
    <main class="lyear-layout-content">
      
      <div class="container-fluid">
        
        <div class="row">
          <div class="col-lg-12">
            <div class="card">
              <div class="card-header"><h4>Edit item category</h4></div>
              <div class="card-body">
                <form action="add" id="category-add-form" method="post" class="row">
                  <input type="hidden" name="id" value="${adsCategory.id}">
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Category icon</span>
                    <input type="file" id="select-file" style="display:none;" onchange="upload('show-picture-img','icon')">
                    <input type="text" readonly="readonly" class="form-control required" id="icon" name="icon" value="${adsCategory.icon}" placeholder="Upload icon" tips="Upload icon" />
                  	<span class="input-group-btn">
                      <button class="btn btn-primary" id="add-pic-btn" type="button">Click to upload</button>
                    </span>
                  </div>
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Category name</span>
                    <input type="text" class="form-control required" id="name" name="name" value="${adsCategory.name}" placeholder="Enter category name" tips="Enter category name" />
                  </div>
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Parent category</span>
                    <select name="parent.id" class="form-control" id="category">
                    	<#if adsCategorys??>
                    		<option value="">Select parent category</option>
                    		<#list adsCategorys as gc>
                    		<option value="${gc.id}" <#if adsCategory.parent??><#if gc.id == adsCategory.parent.id>selected</#if></#if>>${gc.name}</option>
                    		</#list>
                    	</#if>
                    </select>
                  </div>
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Sort category</span>
                    <input type="number" class="form-control" id="sort" name="sort" value="${adsCategory.sort}" />
                  </div>
                  <div class="form-group col-md-12">
                    <button type="button" class="btn btn-primary ajax-post" id="edit-form-submit-btn">Confirm</button>
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
	//Submit button listen event
	$("#edit-form-submit-btn").click(function(){
		if(!checkForm("category-add-form")){
			return;
		}
		var data = $("#category-add-form").serialize();
		$.ajax({
			url:'edit',
			type:'POST',
			data:data,
			dataType:'json',
			success:function(data){
				if(data.code == 0){
					showSuccessMsg('Category edited!',function(){
						window.location.href = 'list';
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
	//Listening to the Upload Image Button
	$("#add-pic-btn").click(function(){
		$("#select-file").click();
	});
});

</script>
</body>
</html>