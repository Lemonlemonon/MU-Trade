<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
<title>${siteName!""}|Role management-Edit role</title>
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
              <div class="card-header"><h4>Edit role</h4></div>
              <div class="card-body">
                <form action="add" id="role-add-form" method="post" class="row">
                  <input type="hidden" name="id" value="${role.id!""}">
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Role name</span>
                    <input type="text" class="form-control required" id="name" name="name" value="${role.name!""}" placeholder="Please enter role name" tips="Please enter role name" />
                  </div>
                  <div class="table-responsive">
                  <table class="table table-striped">
                    <thead>
                      <tr>
                        <th>
                          <label class="">
                            	Role permission
                          </label>
                        </th>
                      </tr>
                      <tr>
                        <th>
                          <label class="lyear-checkbox checkbox-primary">
                            <input name="checkbox" type="checkbox" id="check-all">
                            <span> Select All</span>
                          </label>
                        </th>
                      </tr>
                    </thead>
                    <tbody>
                      <#if topMenus??>
                      <#list topMenus as topMenu>
                      <tr>
                        <td>
                          <label class="lyear-checkbox checkbox-primary">
                            <input name="authorities" type="checkbox" class="checkbox-parent" dataid="id-${topMenu.id}" value="${topMenu.id}">
                            <span>${topMenu.name}</span>
                          </label>
                        </td>
                      </tr>
                      <#if secondMenus??>
                      <#list secondMenus as secondMenu>
                      <#if secondMenu.parent.id == topMenu.id>
                      <tr>
                        <td class="p-l-20">
                          <label class="lyear-checkbox checkbox-primary">
                            <input name="authorities" type="checkbox" class="checkbox-parent checkbox-child" dataid="id-${topMenu.id}-${secondMenu.id}" value="${secondMenu.id}">
                            <span>${secondMenu.name}</span>
                          </label>
                        </td>
                      </tr>
                      <#if thirdMenus??>
                     
                      <tr>
                        <td class="p-l-40">
                           <#list thirdMenus as thirdMenu>
                      	   <#if thirdMenu.parent.id == secondMenu.id>
                          <label class="lyear-checkbox checkbox-primary checkbox-inline">
                            <input name="authorities" type="checkbox" class="checkbox-child" dataid="id-${topMenu.id}-${secondMenu.id}-${thirdMenu.id}" value="${thirdMenu.id}">
                            <span>${thirdMenu.name}</span>
                          </label>
                          </#if>
                     	  </#list>
                        </td>
                      </tr>
                      </#if>
                      </#if>
                      </#list>
                      </#if>
                      </#list>
                      </#if>
                    </tbody>
                  </table>
                </div>
                  <div class="input-group m-b-10">
                    Status:
                    <label class="lyear-radio radio-inline radio-primary">
                    <input type="radio" name="status" value="1" <#if role.status == 1>checked</#if>>
                    <span>Enable</span>
                    <label class="lyear-radio radio-inline radio-primary">
                    <input type="radio" name="status" value="0" <#if role.status == 0>checked</#if>>
                    <span>Disable</span>
                  </label>
                  </div>
                  <div class="input-group m-b-10">
                    <span class="input-group-addon">Note</span>
                    <input type="text" class="form-control" id="remark" name="remark" value="${role.remark!""}" />
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
		if(!checkForm("role-add-form")){
			return;
		}
		var data = $("#role-add-form").serialize();
		$.ajax({
			url:'edit',
			type:'POST',
			data:data,
			dataType:'json',
			success:function(data){
				if(data.code == 0){
					showSuccessMsg('Role edited!',function(){
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
  //Dynamic selection box, changes in parent-child selection status
    $('input.checkbox-parent').on('change', function(){
        var dataid = $(this).attr("dataid");
        $('input[dataid^=' + dataid + '-]').prop('checked', $(this).is(':checked'));
    });
    $('input.checkbox-child').on('change', function(){
        var dataid = $(this).attr("dataid");
            dataid = dataid.substring(0, dataid.lastIndexOf("-"));
        var parent = $('input[dataid=' + dataid + ']');
        if($(this).is(':checked')){
            parent.prop('checked', true);
            //Loop to the top-level
            while(dataid.lastIndexOf("-") != 2){
                dataid = dataid.substring(0, dataid.lastIndexOf("-"));
                parent = $('input[dataid=' + dataid + ']');
                parent.prop('checked', true);
            }
        }else{
            //Parent
            if($('input[dataid^=' + dataid + '-]:checked').length == 0){
                parent.prop('checked', false);
                //Loop to the top-level
                while(dataid.lastIndexOf("-") != 2){
                    dataid = dataid.substring(0, dataid.lastIndexOf("-"));
                    parent = $('input[dataid=' + dataid + ']');
                    if($('input[dataid^=' + dataid + '-]:checked').length == 0){
                        parent.prop('checked', false);
                    }
                }
            }
        }
    });
//Roles and permissions of the selected user
var authorities = ${authorities!"[]"};
//Iterate through role permissions and select the ones that are already assigned.
$("input[type='checkbox']").each(function(i,e){
	for(var i=0; i < authorities.length; i++){
		if(authorities[i].id == $(e).val()){
			$(e).prop('checked',true);
		}
	}
});
</script>
</body>
</html>