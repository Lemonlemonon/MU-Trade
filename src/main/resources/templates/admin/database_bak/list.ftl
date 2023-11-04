<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
<title>${siteName!""}|Data backup management-${title!""}</title>
<#include "../common/header.ftl"/>
<style>
td{
	vertical-align:middle;
}
</style>
</head>
  
<body>
<div class="lyear-layout-web">
  <div class="lyear-layout-container">
    <!--Left-side navigation-->
    <aside class="lyear-layout-sidebar">
      
      <!-- logo -->
      <div id="logo" class="sidebar-header">
        <a href="index.html"><img src="/admin/images/logo-sidebar.png" title="${siteName!""}" alt="${siteName!""}" /></a>
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
              <div class="card-toolbar clearfix">
                
                <#include "../common/third-menu.ftl"/>
              </div>
              <div class="card-body">
                
                <div class="table-responsive">
                  <table class="table table-bordered">
                    <thead>
                      <tr>
                        <th>
                          <label class="lyear-checkbox checkbox-primary">
                            <input type="checkbox" id="check-all"><span></span>
                          </label>
                        </th>
                        <th>Backup file</th>
                        <th>Save directory</th>
                        <th>Operation time</th>
                      </tr>
                    </thead>
                    <tbody>
                      <#if pageBean.content?size gt 0>
                      <#list pageBean.content as databaseBak>
                      <tr>
                        <td style="vertical-align:middle;">
                          <label class="lyear-checkbox checkbox-primary">
                            <input type="checkbox" name="ids[]" value="${databaseBak.id}"><span></span>
                          </label>
                        </td>
                        <td style="vertical-align:middle;">${databaseBak.filename}</td>
                        <td align="center">${databaseBak.filepath}</td>
                        <td style="vertical-align:middle;"><font class="text-success">${databaseBak.createTime}</font></td>
                      </tr>
                    </#list>
                    <#else>
                    <tr align="center"><td colspan="4">Here is empty!</td></tr>
					</#if>                      
                    </tbody>
                  </table>
                </div>
                <#if pageBean.total gt 0>
                <ul class="pagination">
                  <#if pageBean.currentPage == 1>
                  <li class="disabled"><span>«</span></li>
                  <#else>
                  <li><a href="list?currentPage=1">«</a></li>
                  </#if>
                  <#list pageBean.currentShowPage as showPage>
                  <#if pageBean.currentPage == showPage>
                  <li class="active"><span>${showPage}</span></li>
                  <#else>
                  <li><a href="list?currentPage=${showPage}">${showPage}</a></li>
                  </#if>
                  </#list>
                  <#if pageBean.currentPage == pageBean.totalPage>
                  <li class="disabled"><span>»</span></li>
                  <#else>
                  <li><a href="list?currentPage=${pageBean.totalPage}">»</a></li>
                  </#if>
                  <li><span>In total ${pageBean.totalPage} pages,${pageBean.total} items</span></li>
                </ul>
                </#if>
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
	
});
function del(url){
	if($("input[type='checkbox']:checked").length < 1){
		showWarningMsg('Please select at least one data to delete!');
		return;
	}
	var ids = '';
	$("input[type='checkbox']:checked").each(function(i,e){
		if($(e).attr('id') != 'check-all'){
			ids += $(e).val() + ',';
		}
	});
	if(ids != ''){
		ids = ids.substring(0,ids.length-1);
	}
	$.confirm({
        title: 'Confirm deletion?',
        content: 'Data is not recoverable after deletion!',
        buttons: {
            confirm: {
                text: 'Confirm',
                action: function(){
                    deleteReq(ids,url);
                }
            },
            cancel: {
                text: 'Cancel',
                action: function(){
                    
                }
            }
        }
    });
}
//Backup operation
function add(url){
	ajaxRequest(url,'post',{},function(rst){
			if(rst.code == 0){
				showSuccessMsg('Backup successfully!',function(){
					window.location.reload();
				});
			}
			
	});
	
}
//Resore opperation
function restore(url){
	if($("input[type='checkbox']:checked").length != 1){
		showWarningMsg('Select at least one data to restore!');
		return;
	}
	var id = $("input[type='checkbox']:checked").val();
	ajaxRequest(url,'post',{id:id},function(rst){
			if(rst.code == 0){
				showSuccessMsg('Restore successfully',function(){
					window.location.reload();
				});
			}
			
	});
	
}
//Invoke the delete method
function deleteReq(ids,url){
	$.ajax({
		url:url,
		type:'POST',
		data:{ids:ids},
		dataType:'json',
		success:function(data){
			if(data.code == 0){
				showSuccessMsg('Backup successfully deleted!',function(){
					window.location.reload();
					//$("input[type='checkbox']:checked").parents("tr").remove();
				})
			}else{
				showErrorMsg(data.msg);
			}
		},
		error:function(data){
			alert('Unknown network error!');
		}
	});
}
</script>
</body>
</html>