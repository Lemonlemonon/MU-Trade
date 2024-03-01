<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
<title>${siteName!""}Backend administration dashboard</title>
<#include "../common/header.ftl"/>

</head>
  
<body>
<div class="lyear-layout-web">
  <div class="lyear-layout-container">
    <!--Left-side navigation-->
    <aside class="lyear-layout-sidebar">
      
      <!-- logo -->
      <div id="logo" class="sidebar-header">
        <a href="/system/index"><img src="/admin/images/logo-sidebar.png" title="${siteName!""}" alt="${siteName!""}" /></a>
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
              <div class="card-body">
                
                <div class="edit-avatar">
                  <#if fyp_user.headPic??>
	            		<#if fyp_user.headPic?length gt 0>
	            		<img src="/photo/view?filename=${fyp_user.headPic}" id="show-img" class="img-avatar">
	            		<#else>
	            		<img src="/admin/images/default-head.jpg" id="show-img" class="img-avatar">
	            		</#if>
	              </#if>
                  <input type="file" id="select-file" style="display:none;" onchange="upload('show-img','headPic')">
                  <div class="avatar-divider"></div>
                  <div class="edit-avatar-content">
                    <button class="btn btn-default" id="add-pic-btn">Change avatar</button>
                    <p class="m-0">Select an image, the uploaded image size cannot exceed 1MB.</p>
                  </div>
                </div>
                <hr>
                <form method="post" action="update_userinfo" class="site-form">
                  <input type="hidden" name="headPic" id="headPic" value="${fyp_user.headPic!""}">
                  <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" class="form-control" name="username" id="username" value="${fyp_user.username}" disabled="disabled" />
                  </div>
                  <div class="form-group">
                    <label for="nickname">Telephone</label>
                    <input type="text" class="form-control" name="mobile" id="mobile" placeholder="Enter your phone number" value="${fyp_user.mobile!""}">
                  </div>
                  <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" class="form-control" name="email" id="email" aria-describedby="emailHelp" placeholder="Enter your email" value="${fyp_user.email!""}">
                  </div>
                  <button type="submit" class="btn btn-primary">Save</button>
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
	//Monitor the upload image button
	$("#add-pic-btn").click(function(){
		$("#select-file").click();
	});
});

</script>
</body>
</html>