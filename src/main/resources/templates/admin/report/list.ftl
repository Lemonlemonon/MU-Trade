<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, goodsCategory-scalable=no" />
<title>${siteName!""}|Report management-${title!""}</title>
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
    <!--Left navigate bar-->
    <aside class="lyear-layout-sidebar">
      
      <!-- logo -->
      <div id="logo" class="sidebar-header">
        <a href="index.html"><img src="/admin/images/logo-sidebar.png" title="${siteName!""}" alt="${siteName!""}" /></a>
      </div>
      <div class="lyear-layout-sidebar-scroll"> 
        <#include "../common/left-menu.ftl"/>
      </div>
      
    </aside>
    <!--End Left navigate bar-->
    
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
                      	<#if name??>Goods name<#elseif sn??>Student ID<#elseif content??>Reason<#else>Filter</#if> <span class="caret"></span>
                      </button>
                      <ul class="dropdown-menu">
                        <li> <a tabindex="-1" href="javascript:void(0)" data-field="content">Reason</a> </li>
                        <li> <a tabindex="-1" href="javascript:void(0)" data-field="student.sn">Student ID</a> </li>
                        <li> <a tabindex="-1" href="javascript:void(0)" data-field="goods.name">Goods name</a> </li>
                      </ul>
                    </div>
                    <input type="text" class="form-control" value="${content!sn!name!""}" id="search-value" name="<#if name??>goods.name<#elseif sn??>student.sn<#else>content</#if>" placeholder="Search..">
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
                        <th>Item image</th>
                        <th>Goods name</th>
                        <th>Reporter(student ID)</th>
                        <th style="width:256px;">Reason</th>
                        <th>Time</th>
                      </tr>
                    </thead>
                    <tbody>
                      <#if pageBean.content?size gt 0>
                      <#list pageBean.content as reportGoods>
                      <tr>
                        <td style="vertical-align:middle;">
                          <label class="lyear-checkbox checkbox-primary">
                            <input type="checkbox" name="ids[]" value="${reportGoods.id}"><span></span>
                          </label>
                        </td>
                        <td style="vertical-align:middle;">
                        	<a href="/home/goods/detail?id=${reportGoods.goods.id}" target="_blank">
                        	<img src="/photo/view?filename=${reportGoods.goods.photo}" width="30px" height="30px">
                       		</a>
                        </td>
                        <td style="vertical-align:middle;">
                        	<a href="/home/goods/detail?id=${reportGoods.goods.id}" target="_blank">${reportGoods.goods.name}</a>
                        </td>
                        <td style="vertical-align:middle;">
                        	${reportGoods.student.sn}
                        </td>
                        <td style="vertical-align:middle;">
                        ${reportGoods.content}
                        </td>
                        <td style="vertical-align:middle;" style="vertical-align:middle;"><font class="text-success">${reportGoods.createTime}</font></td>
                      </tr>
                    </#list>
                    <#else>
                    <tr align="center"><td colspan="6">Here is empty!</td></tr>
					</#if>
                    </tbody>
                  </table>
                </div>
                <#if pageBean.total gt 0>
                <ul class="pagination ">
                  <#if pageBean.currentPage == 1>
                  <li class="disabled"><span>«</span></li>
                  <#else>
                  <li><a href="list?<#if name??>goods.name<#elseif sn??>student.sn<#else>content</#if>=${name!sn!content!""}&currentPage=1">«</a></li>
                  </#if>
                  <#list pageBean.currentShowPage as showPage>
                  <#if pageBean.currentPage == showPage>
                  <li class="active"><span>${showPage}</span></li>
                  <#else>
                  <li><a href="list?<#if name??>goods.name<#elseif sn??>student.sn<#else>content</#if>=${name!sn!content!""}&currentPage=${showPage}">${showPage}</a></li>
                  </#if>
                  </#list>
                  <#if pageBean.currentPage == pageBean.totalPage>
                  <li class="disabled"><span>»</span></li>
                  <#else>
                  <li><a href="list?<#if name??>goods.name<#elseif sn??>student.sn<#else>content</#if>=${name!sn!content!""}&currentPage=${pageBean.totalPage}">»</a></li>
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
	$(".dropdown-menu li a").click(function(){
		$("#search-btn").html($(this).text() + '<span class="caret"></span>');
		$("#search-value").attr('name',$(this).attr('data-field'));
	});
});
function del(url){
	if($("input[type='checkbox']:checked").length != 1){
		showWarningMsg('Please select a data to edit!');
		return;
	}
	var id = $("input[type='checkbox']:checked").val();
	$.confirm({
        title: 'Confirm?',
        content: 'Data can not be retrieve!',
        buttons: {
            confirm: {
                text: 'Yes',
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

//Invoke delete method
function deleteReq(id,url){
	$.ajax({
		url:url,
		type:'POST',
		data:{id:id},
		dataType:'json',
		success:function(data){
			if(data.code == 0){
				showSuccessMsg('Report is deleted!',function(){
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