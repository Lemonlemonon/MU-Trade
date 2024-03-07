<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
<title>${siteName!""}|Menu management-Create menu</title>
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
              <div class="card-header"><h4>Create menu</h4></div>
              <div class="card-body">
                <form action="add" id="menu-add-form" method="post" class="row">
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Parent category</span>
                    <select name="parent.id" class="form-control" id="type">
                        <option value="">Select parent category</option>
                        <#if topMenus??>
                        	<#list topMenus as topMenu>
                        		<option value="${topMenu.id}" style="font-weight:bold;">${topMenu.name}</option>
                        		<#if secondMenus??>
                        			<#list secondMenus as secondMenu>
                        				<#if secondMenu.parent.id == topMenu.id>
                        				<option value="${secondMenu.id}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${secondMenu.name}</option>
                        				</#if>
                        			</#list>
                        		</#if>
                        	</#list>
                        </#if>
                      </select>
                  </div>
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Menu name</span>
                    <input type="text" class="form-control required" id="name" name="name" value="" placeholder="Please enter menu name" tips="Please enter menu name" />
                  </div>
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Menu URL</span>
                    <input type="text" class="form-control" id="url" name="url" value="" placeholder="Please enter menu URL" />
                  </div>
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Menu icon</span>
                    <input type="text" readonly="readonly" class="form-control required" id="icon" name="icon" value="" placeholder="Please enter menu icon" tips="Select the menu icon" />
                  	<span class="input-group-btn">
                      <button class="btn btn-primary" id="show-icons-btn" data-toggle="modal" data-target="#icons-panel" type="button">Select file</button>
                    </span>
                  </div>
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Sort</span>
                    <input type="number" class="form-control" id="sort" name="sort" value="0" />
                  </div>
                  <div class="input-group m-b-10">
                    Yes/No button:
                    <label class="lyear-radio radio-inline radio-primary">
                    <input type="radio" name="button" value="true" >
                    <span>Yes</span>
                    <label class="lyear-radio radio-inline radio-primary">
                    <input type="radio" name="button" value="false" checked="">
                    <span>No</span>
                  </label>
                  </div>
                  <div class="input-group m-b-10">
                    If display:
                    <label class="lyear-radio radio-inline radio-primary">
                    <input type="radio" name="show" value="true" checked="">
                    <span>Yes</span>
                    <label class="lyear-radio radio-inline radio-primary">
                    <input type="radio" name="show" value="false" >
                    <span>No</span>
                  </label>
                  </div>
                  <div class="form-group col-md-12">
                    <button type="button" class="btn btn-primary ajax-post" id="add-form-submit-btn">CONFIRM</button>
                    <button type="button" class="btn btn-default" onclick="javascript:history.back(-1);return false;">CANCEL</button>
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
<#include "../common/icons.ftl"/>
<script type="text/javascript" src="/admin/js/perfect-scrollbar.min.js"></script>
<script type="text/javascript" src="/admin/js/main.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	//Confirmation button event after selecting an icon
	$("#confirm-icon-btn").click(function(){
		getSelectedIcon();
	});
	//Submit button event handler
	$("#add-form-submit-btn").click(function(){
		if(!checkForm("menu-add-form")){
			return;
		}
		var data = $("#menu-add-form").serialize();
		$.ajax({
			url:'add',
			type:'POST',
			data:data,
			dataType:'json',
			success:function(data){
				if(data.code == 0){
					showSuccessMsg('Menu created!',function(){
						window.location.href = 'list';
					})
				}else{
					showErrorMsg(data.msg);
				}
			},
			error:function(data){
				alert('Unknown network error!');
			}
		});
	});
});
function getSelectedIcon(){
	$("#icon").val($(".selected-icon").attr('val'));
	$("#icons-panel").modal('hide');
}
</script>
</body>
</html>