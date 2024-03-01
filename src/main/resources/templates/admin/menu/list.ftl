<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
<title>${siteName!""}|Menu management-${title!""}</title>
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
              <div class="card-toolbar clearfix">
                <!--
                <form class="pull-right search-bar" method="get" action="#!" role="form">
                  <div class="input-group">
                    <div class="input-group-btn">
                      <input type="hidden" name="search_field" id="search-field" value="title">
                      <button class="btn btn-default dropdown-toggle" id="search-btn" data-toggle="dropdown" type="button" aria-haspopup="true" aria-expanded="false">
                      Name <span class="caret"></span>
                      </button>
                      <ul class="dropdown-menu">
                        <li> <a tabindex="-1" href="javascript:void(0)" data-field="title">Name</a> </li>
                      </ul>
                    </div>
                    <input type="text" class="form-control" value="" name="keyword" placeholder="Please enter name">
                  	<span class="input-group-btn">
                      <button class="btn btn-primary" type="button">Search</button>
                    </span>
                  </div>
                </form>
                -->
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
                        <th>Menu name</th>
                        <th>Menu URL</th>
                        <th>Menu icon</th>
                        <th>Menu order</th>
                        <th>If button</th>
                        <th>If display</th>
                        <th>Created time</th>
                      </tr>
                    </thead>
                    <tbody>
                      <#if topMenus??>
                      <#list topMenus as topMenu>
                      <tr>
                        <td style="vertical-align:middle;">
                          <label class="lyear-checkbox checkbox-primary">
                            <input type="checkbox" name="ids[]" value="${topMenu.id}"><span></span>
                          </label>
                        </td>
                        <td style="vertical-align:middle;"><b>${topMenu.name}</b></td>
                        <td style="vertical-align:middle;">${topMenu.url}</td>
                        <td align="center"><i style="font-size:22px;" class="mdi ${topMenu.icon}" title="${topMenu.icon}" data-toggle="tooltip"></i></td>
                        <td align="center" style="vertical-align:middle;">${topMenu.sort}</td>
                        <td style="vertical-align:middle;"><#if topMenu.button == true><font class="text-success">Yes</font><#else><font class="text-info">No</font></#if></td>
                        <td style="vertical-align:middle;"><#if topMenu.show == true><font class="text-success">Yes</font><#else><font class="text-info">No</font></#if></td>
                        <td style="vertical-align:middle;"><font class="text-success">${topMenu.createTime}</font></td>
                      </tr>
					   <#if secondMenus??>
					   <#list secondMenus as secondMenu>
					   <#if secondMenu.parent.id == topMenu.id>
					   <tr>
                        <td style="vertical-align:middle;">
                          <label class="lyear-checkbox checkbox-primary">
                            <input type="checkbox" name="ids[]" value="${secondMenu.id}"><span></span>
                          </label>
                        </td>
                        <td style="vertical-align:middle;">|-----${secondMenu.name}</td>
                        <td style="vertical-align:middle;">${secondMenu.url}</td>
                        <td align="center"><i style="font-size:22px;" class="mdi ${secondMenu.icon}" title="${secondMenu.icon}" data-toggle="tooltip"></i></td>
                        <td align="center" style="vertical-align:middle;">${secondMenu.sort}</td>
                        <td style="vertical-align:middle;"><#if secondMenu.button == true><font class="text-success">Yes</font><#else><font class="text-info">No</font></#if></td>
                        <td style="vertical-align:middle;"><#if secondMenu.show == true><font class="text-success">Yes</font><#else><font class="text-info">No</font></#if></td>
                        <td style="vertical-align:middle;"><font class="text-success">${secondMenu.createTime}</font></td>
                      </tr>
                      <#if thirdMenus??>
                      <#list thirdMenus as thirdMenu>
                      	<#if thirdMenu.parent.id == secondMenu.id>
					    <tr>
                        <td style="vertical-align:middle;">
                          <label class="lyear-checkbox checkbox-primary">
                            <input type="checkbox" name="ids[]" value="${thirdMenu.id}"><span></span>
                          </label>
                        </td>
                        <td style="padding-left:45px;vertical-align:middle;">|-----${thirdMenu.name}</td>
                        <td style="vertical-align:middle;">${thirdMenu.url}</td>
                        <td align="center"><i style="font-size:22px;" class="mdi ${thirdMenu.icon}" title="${thirdMenu.icon}" data-toggle="tooltip"></i></td>
                        <td align="center" style="vertical-align:middle;">${thirdMenu.sort}</td>
                        <td style="vertical-align:middle;"><#if thirdMenu.button == true><font class="text-success">Yes</font><#else><font class="text-info">No</font></#if></td>
                        <td style="vertical-align:middle;"><#if thirdMenu.show == true><font class="text-success">Yes</font><#else><font class="text-info">No</font></#if></td>
                        <td style="vertical-align:middle;"><font class="text-success">${thirdMenu.createTime}</font></td>
                      </tr>
                      </#if>
                      </#list>
                      </#if>
                      </#if>
                      </#list>
                      </#if>
                    </#list>
					</#if>                      
                    </tbody>
                  </table>
                </div>
                <!--
                <ul class="pagination">
                  <li class="disabled"><span>«</span></li>
                  <li class="active"><span>1</span></li>
                  <li><a href="#1">2</a></li>
                  <li><a href="#1">3</a></li>
                  <li><a href="#1">4</a></li>
                  <li><a href="#1">5</a></li>
                  <li><a href="#!">»</a></li>
                </ul>
       			-->
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
	if($("input[type='checkbox']:checked").length != 1){
		showWarningMsg('Select one item to delete!');
		return;
	}
	var id = $("input[type='checkbox']:checked").val();
	$.confirm({
        title: 'Confirm deletion?',
        content: 'Data is not recoverable after deletion!',
        buttons: {
            confirm: {
                text: 'Confirm',
                action: function(){
                    deleteReq(id,url);
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
//Open edit page
function edit(url){
	if($("input[type='checkbox']:checked").length != 1){
		showWarningMsg('Select one item to edit!');
		return;
	}
	window.location.href = url + '?id=' + $("input[type='checkbox']:checked").val();
}
//Invoke delete method
function deleteReq(id,url){
	$.ajax({
		url:url,
		type:'POST',
		data:{id:id},
		dataType:'json',
		success:function(data){
			if(data.code == 0){
				showSuccessMsg('Menu deleted!',function(){
					$("input[type='checkbox']:checked").parents("tr").remove();
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