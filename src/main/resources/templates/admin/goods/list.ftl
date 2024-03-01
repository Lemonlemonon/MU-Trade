<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, goodsCategory-scalable=no" />
<title>${siteName!""}|Item management-${title!""}</title>
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
                <form class="pull-right search-bar" method="get" action="list" role="form">
                  <div class="input-group">
                    <div class="input-group-btn">
                      <button class="btn btn-default dropdown-toggle" id="search-btn" data-toggle="dropdown" type="button" aria-haspopup="true" aria-expanded="false">
                      	<#if goodsCategoryName??>Category name<#elseif sn??>Student number<#elseif name??>Item name<#else>Filter</#if> <span class="caret"></span>
                      </button>
                      <ul class="dropdown-menu">
                        <li> <a tabindex="-1" href="javascript:void(0)" data-field="name">Item name</a> </li>
                        <li> <a tabindex="-1" href="javascript:void(0)" data-field="student.sn">Student number</a> </li>
                        <li> <a tabindex="-1" href="javascript:void(0)" data-field="goodsCategory.name">Category name</a> </li>
                      </ul>
                    </div>
                    <input type="text" class="form-control" value="${name!sn!goodsCategoryName!""}" id="search-value" name="<#if goodsCategoryName??>goodsCategory.name<#elseif sn??>student.sn<#else>name</#if>" placeholder="Search">
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
                        <th>Image</th>
                        <th>Name</th>
                        <th>Seller(Student ID)</th>
                        <th>Category</th>
                        <th>Price</th>
                        <th>Views</th>
                        <th>Status</th>
                        <th>Highlighted</th>
                        <th>Recommended</th>
                        <th>Added time</th>
                      </tr>
                    </thead>
                    <tbody>
                      <#if pageBean.content?size gt 0>
                      <#list pageBean.content as goods>
                      <tr>
                        <td style="vertical-align:middle;">
                          <label class="lyear-checkbox checkbox-primary">
                            <input type="checkbox" name="ids[]" value="${goods.id}"><span></span>
                          </label>
                        </td>
                        <td style="vertical-align:middle;">
                        	<a href="/home/goods/detail?id=${goods.id}" target="_blank">
                        	<img src="/photo/view?filename=${goods.photo}" width="30px" height="30px">
                       		</a>
                        </td>
                        <td style="vertical-align:middle;">
                        	<a href="/home/goods/detail?id=${goods.id}" target="_blank">${goods.name}</a>
                        </td>
                        <td style="vertical-align:middle;">
                        	${goods.student.sn}
                        </td>
                        <td style="vertical-align:middle;">
                        ${goods.goodsCategory.name}
                        </td>
                        <td style="vertical-align:middle;">
                        ${goods.sellPrice}
                        </td>
                        <td style="vertical-align:middle;">
                        ${goods.viewNumber}
                        </td>
                        <td style="vertical-align:middle;">
                        	<#if goods.status == 1>Selling
                        	<#elseif goods.status == 2>Withdrawed
                        	<#else>Sold
                        	</#if>
                        </td>
                        <td style="vertical-align:middle;">
                        	<#if goods.flag == 1><font class="text-success">Yes</font>
                        	<#else>No
                        	</#if>
                        </td>
                        <td style="vertical-align:middle;">
                        	<#if goods.recommend == 1><font class="text-success">Yes</font>
                        	<#else>No
                        	</#if>
                        </td>
                        <td style="vertical-align:middle;" style="vertical-align:middle;"><font class="text-success">${goods.createTime}</font></td>
                      </tr>
                    </#list>
                    <#else>
                    <tr align="center"><td colspan="11">Here is empty!</td></tr>
					</#if>
                    </tbody>
                  </table>
                </div>
                <#if pageBean.total gt 0>
                <ul class="pagination ">
                  <#if pageBean.currentPage == 1>
                  <li class="disabled"><span>«</span></li>
                  <#else>
                  <li><a href="list?<#if goodsCategoryName??>goodsCategory.name<#elseif sn??>student.sn<#else>name</#if>=${name!sn!goodsCategoryName!""}&currentPage=1">«</a></li>
                  </#if>
                  <#list pageBean.currentShowPage as showPage>
                  <#if pageBean.currentPage == showPage>
                  <li class="active"><span>${showPage}</span></li>
                  <#else>
                  <li><a href="list?<#if goodsCategoryName??>goodsCategory.name<#elseif sn??>student.sn<#else>name</#if>=${name!sn!goodsCategoryName!""}&currentPage=${showPage}">${showPage}</a></li>
                  </#if>
                  </#list>
                  <#if pageBean.currentPage == pageBean.totalPage>
                  <li class="disabled"><span>»</span></li>
                  <#else>
                  <li><a href="list?<#if goodsCategoryName??>goodsCategory.name<#elseif sn??>student.sn<#else>name</#if>=${name!sn!goodsCategoryName!""}&currentPage=${pageBean.totalPage}">»</a></li>
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
//Place item on sell
function up(url){
	if($("input[type='checkbox']:checked").length != 1){
		showWarningMsg('Select one item to sell!');
		return;
	}
	var id = $("input[type='checkbox']:checked").val();
	ajaxRequest('up_down','post',{id:id,status:1},function(){
		alert('Item placed');
		window.location.reload();
	});
}
//Withdraw item
function down(url){
	if($("input[type='checkbox']:checked").length != 1){
		showWarningMsg('Select one item to withdraw!');
		return;
	}
	var id = $("input[type='checkbox']:checked").val();
	ajaxRequest('up_down','post',{id:id,status:2},function(){
		alert('Item withdrawed!');
		window.location.reload();
	});
}
//Recommend item
function recommend(url){
	if($("input[type='checkbox']:checked").length != 1){
		showWarningMsg('Please select one item to recommend!');
		return;
	}
	var id = $("input[type='checkbox']:checked").val();
	ajaxRequest('recommend','post',{id:id,recommend:1},function(){
		alert('Item recommended!');
		window.location.reload();
	});
}
//Discard recommend
function unrecommend(url){
	if($("input[type='checkbox']:checked").length != 1){
		showWarningMsg('Select on item to discard recommend!');
		return;
	}
	var id = $("input[type='checkbox']:checked").val();
	ajaxRequest('recommend','post',{id:id,recommend:0},function(){
		alert('Discarded Item recommand!');
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
				showSuccessMsg('Item deleted!',function(){
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