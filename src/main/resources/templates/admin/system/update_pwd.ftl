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
                
                <form method="post" action="update_pwd" class="site-form" id="edit-pwd-form">
                  <div class="form-group">
                    <label for="old-password">Old password</label>
                    <input type="password" class="form-control required" name="oldpwd" id="old-password" placeholder="Enter old password" tips="Enter old password!">
                  </div>
                  <div class="form-group">
                    <label for="new-password">New password</label>
                    <input type="password" class="form-control required" name="newpwd" id="new-password" placeholder="Enter new password" tips="Enter new password!">
                  </div>
                  <div class="form-group">
                    <label for="confirm-password">Re-enter new password</label>
                    <input type="password" class="form-control required" name="confirmpwd" id="confirm-password" placeholder="Re-enter new password" tips="Re-enter new password!">
                  </div>
                  <button type="button" class="btn btn-primary" id="submit-btn">Change password</button>
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
	$("#submit-btn").click(function(){
		if(!checkForm("edit-pwd-form")){
			return;
		}
		var oldPwd = $("#old-password").val();
		var newPwd = $("#new-password").val();
		var reNewPwd = $("#confirm-password").val();
		if(newPwd != reNewPwd){
			showErrorMsg('The two password entries do not match!');
			return;
		}
		//Send request to back-end
		var data = {oldPwd:oldPwd,newPwd:newPwd};
		ajaxRequest('/system/update_pwd','post',data,function(rst){
			showSuccessMsg('Password changed!',function(){});
		});
	});
});

</script>
</body>
</html>