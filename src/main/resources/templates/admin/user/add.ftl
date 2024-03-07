<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
<title>${siteName!""}|User management-Create user</title>
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
              <div class="card-header"><h4>Create user</h4></div>
              <div class="card-body">
                <form action="add" id="user-add-form" method="post" class="row">
                  <div class="form-group col-md-12">
                    <label>Upload avatar</label>
                    <div class="form-controls">
                      <ul class="list-inline clearfix lyear-uploads-pic">
                        <li class="col-xs-4 col-sm-3 col-md-2">
                          <figure>
                            <img src="/admin/images/default-head.jpg" id="show-picture-img" alt="Default avatar">
                          </figure>
                        </li>
                        <input type="hidden" name="headPic" id="headPic">
                        <input type="file" id="select-file" style="display:none;" onchange="upload('show-picture-img','headPic')">
                        <li class="col-xs-4 col-sm-3 col-md-2">
                          <a class="pic-add" id="add-pic-btn" href="javascript:void(0)" title="Click to upload"></a>
                        </li>
                      </ul>
                    </div>
                  </div>
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">User name</span>
                    <input type="text" class="form-control required" id="username" name="username" value="" placeholder="Enter your username" tips="Enter your username" />
                  </div>
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Password</span>
                    <input type="password" class="form-control required" id="password" name="password" value="" placeholder="Enter a password" tips="Enter a password" />
                  </div>
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Current role</span>
                    <select name="role.id" class="form-control" id="role">
                    	<#list roles as role>
                    	<option value="${role.id}">${role.name}</option>
                    	</#list>
                    </select>
                  </div>
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Phone number</span>
                    <input type="tel" class="form-control" id="mobile" name="mobile" value="" />
                  </div>
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Email</span>
                    <input type="email" class="form-control" id="email" name="email" value="" />
                  </div>
                  <div class="input-group" style="margin-top:15px;margin-bottom:15px;padding-left:25px;">
                    Gender :
                    <label class="lyear-radio radio-inline radio-primary" style="margin-left:30px;">
                    <input type="radio" name="sex" value="1" checked="" >
                    <span>Male</span>
                    <label class="lyear-radio radio-inline radio-primary">
                    <input type="radio" name="sex" value="2">
                    <span>Female</span>
                    <label class="lyear-radio radio-inline radio-primary">
                    <input type="radio" name="sex" value="0">
                    <span>Other</span>
                  </label>
                  </div>
                  <div class="input-group" style="margin-top:15px;margin-bottom:15px;padding-left:25px;">
                    Status:
                    <label class="lyear-radio radio-inline radio-primary" style="margin-left:30px;">
                    <input type="radio" name="status" value="1" checked="">
                    <span>Available</span>
                    <label class="lyear-radio radio-inline radio-primary">
                    <input type="radio" name="status" value="0">
                    <span>Baned</span>
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
<script type="text/javascript" src="/admin/js/perfect-scrollbar.min.js"></script>
<script type="text/javascript" src="/admin/js/main.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	//Submit button event handler
	$("#add-form-submit-btn").click(function(){
		if(!checkForm("user-add-form")){
			return;
		}
		var data = $("#user-add-form").serialize();
		$.ajax({
			url:'add',
			type:'POST',
			data:data,
			dataType:'json',
			success:function(data){
				if(data.code == 0){
					showSuccessMsg('User created!',function(){
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
	//Monitor the upload image button
	$("#add-pic-btn").click(function(){
		$("#select-file").click();
	});
});

</script>
</body>
</html>