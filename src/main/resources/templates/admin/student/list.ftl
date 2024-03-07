<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
<title>${siteName!""}|Student management-${title!""}</title>
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
              <div class="card-toolbar clearfix">
                <form class="pull-right search-bar" method="get" action="list" role="form">
                  <div class="input-group">
                    <div class="input-group-btn">
                      <button class="btn btn-default dropdown-toggle" id="search-btn" data-toggle="dropdown" type="button" aria-haspopup="true" aria-expanded="false">
                      Student number<span class="caret"></span>
                      </button>
                      <ul class="dropdown-menu">
                        <li> <a tabindex="-1" href="javascript:void(0)" data-field="title">Student number</a> </li>
                      </ul>
                    </div>
                    <input type="text" class="form-control" value="${sn!""}" name="sn" placeholder="Enter student number">
                  	<span class="input-group-btn">
                      <button class="btn btn-primary" type="submit">Search</button>
                    </span>
                  </div>
                </form>
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
                        <th>Avatar</th>
                        <th>Student number</th>
                        <th>Nickname</th>
                        <th>Password</th>
                        <th>Status</th>
                        <th>Mobile</th>
                        <th>School</th>
                        <th>Academy</th>
                        <th>Year</th>
                        <th>Regist time</th>
                      </tr>
                    </thead>
                    <tbody>
                      <#if pageBean.content?size gt 0>
                      <#list pageBean.content as student>
                      <tr>
                        <td style="vertical-align:middle;">
                          <label class="lyear-checkbox checkbox-primary">
                            <input type="checkbox" name="ids[]" value="${student.id}"><span></span>
                          </label>
                        </td>
                        <td style="vertical-align:middle;">
                    		<#if student.headPic??>
                    		<img src="/photo/view?filename=${student.headPic}" width="60px" height="60px">
                    		<#else>
                    		<img src="/home/imgs/avatar1.png" width="60px" height="60px">
                    		</#if>
                        </td>
                        <td style="vertical-align:middle;">${student.sn}</td>
                        <td style="vertical-align:middle;">${student.nickname!""}</td>
                        <td style="vertical-align:middle;">${student.password}</td>
                        <td style="vertical-align:middle;">
                        	<#if student.status == 1>
                        	<font class="text-success">Enable</font>
                        	<#else>
                        	<font class="text-warning">Freeze</font>
                        	</#if>
                        </td>
                        <td style="vertical-align:middle;">${student.mobile!""}</td>
                        <td style="vertical-align:middle;">${student.school!""}</td>
                        <td style="vertical-align:middle;">${student.academy!""}</td>
                        <td style="vertical-align:middle;">${student.grade!""}</td>
                        <td style="vertical-align:middle;" style="vertical-align:middle;"><font class="text-success">${student.createTime}</font></td>
                      </tr>
                    </#list>
                    <#else>
                    <tr align="center"><td colspan="12">Here is empty!</td></tr>
					</#if>
                    </tbody>
                  </table>
                </div>
                <#if pageBean.total gt 0>
                <ul class="pagination ">
                  <#if pageBean.currentPage == 1>
                  <li class="disabled"><span>«</span></li>
                  <#else>
                  <li><a href="list?sn=${sn!""}&currentPage=1">«</a></li>
                  </#if>
                  <#list pageBean.currentShowPage as showPage>
                  <#if pageBean.currentPage == showPage>
                  <li class="active"><span>${showPage}</span></li>
                  <#else>
                  <li><a href="list?sn=${sn!""}&currentPage=${showPage}">${showPage}</a></li>
                  </#if>
                  </#list>
                  <#if pageBean.currentPage == pageBean.totalPage>
                  <li class="disabled"><span>»</span></li>
                  <#else>
                  <li><a href="list?sn=${sn!""}&currentPage=${pageBean.totalPage}">»</a></li>
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
	if($("input[type='checkbox']:checked").length != 1){
		showWarningMsg('Please select one item to delete!');
		return;
	}
	var id = $("input[type='checkbox']:checked").val();
	$.confirm({
        title: 'Confirm?',
        content: 'Data cannot be restore, be careful!',
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
//Freeze
function freeze(url){
	if($("input[type='checkbox']:checked").length != 1){
		showWarningMsg('Select one item to process!');
		return;
	}
	var id = $("input[type='checkbox']:checked").val();
	ajaxRequest('update_status','post',{id:id,status:0},function(){
		alert('Account freezed!');
		window.location.reload();
	});
}
//Activate
function openStudent(url){
	if($("input[type='checkbox']:checked").length != 1){
		showWarningMsg('Select one item to process!');
		return;
	}
	var id = $("input[type='checkbox']:checked").val();
	ajaxRequest('update_status','post',{id:id,status:1},function(){
		alert('Account activated');
		window.location.reload();
	});
}
//Delete request
function deleteReq(id,url){
	$.ajax({
		url:url,
		type:'POST',
		data:{id:id},
		dataType:'json',
		success:function(data){
			if(data.code == 0){
				showSuccessMsg('Student deleted!',function(){
					$("input[type='checkbox']:checked").parents("tr").remove();
				})
			}else{
				showErrorMsg(data.msg);
			}
		},
		error:function(data){
			alert('Network error!');
		}
	});
}
</script>
</body>
</html>