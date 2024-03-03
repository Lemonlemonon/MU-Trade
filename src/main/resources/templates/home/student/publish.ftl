<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
<title>${siteName!""}-Publish Goods</title>
<link rel="icon" href="/home/imgs/favicon.ico" type="image/x-icon">
<link media="all" href="/home/css/release_product.css" type="text/css" rel="stylesheet">
<link media="all" href="/home/css/index.css" type="text/css" rel="stylesheet">
</head>
<body>
  <#include "../common/top_header.ftl"/>
   <div class="container">
            <div class="main center">
                <img class="release-icon-main" src="/home/imgs/release-icon.png" alt="">
                <div class="wave-fluid"></div>
                <div class="release-title">Publish Goods</div>
                <form action="publish" id="publish-form" method="post">
                <div class="form-wr">
                    <div class="form-must-wr">
                    	
                    	<input id="photo" type="hidden" name="photo" value="" class="required" tips="Please upload images!">
                    	
                    	<div id="show-img" class="form-item l goods-title" style="height:100px;display:none;">
                            <div class="form-key">
                                <span>Image uploaded</span></div>
                            <div class="form-value">
                                <div class="form-input-wr">
                                	<img id="uploaded-img" src="/home/imgs/release-icon.png" width="100px" height="100px">
                                </div>
                            </div>
                        </div>
                    	
                    	<div class="form-item l goods-title">
                            <div class="form-key">
                                <span>Upload images</span></div>
                            <div class="form-value">
                                <div class="form-input-wr">
                                	<input type="file" id="uploadFile" />
                                </div>
                            </div>
                        </div>
                    	
                        <div class="form-item l goods-title">
                            <div class="form-key">
                                <span>Goods title</span></div>
                            <div class="form-value">
                                <div class="form-input-wr">
                                    <input id="name" name="name" placeholder="32 charaters max" maxlength="32" value="" type="text" class="required" tips="Please enter Goods title"></div>
                            </div>
                        </div>
                        
                        <div class="form-item xl goods-desc">
                            <div class="form-key">
                                <span>Goods detail</span></div>
                            <div class="form-value">
                                <div class="form-input-wr">
                                    <textarea name="content" id="desc" placeholder="Description, condition, selling reason, etc." class="required" tips="Please enter Goods detail "></textarea>
                                </div>
                            </div>
                        </div>
                        
                        
                        <div class="form-item l goods-price">
                            <div class="form-key">
                                <span>Original price</span></div>
                            <div class="form-value">
                                <div class="form-input-wr">
                                    <input class="price required" id="buyPrice" name="buyPrice" value="" type="number" tips="Please enter original price"></div>
                            </div>
                        </div>
                        <div class="form-item l goods-price">
                            <div class="form-key">
                                <span>Price now</span></div>
                            <div class="form-value">
                                <div class="form-input-wr">
                                    <input class="price required" id="sellPrice" name="sellPrice" value="" type="number" tips="Please enter price now "></div>
                            </div>
                        </div>
                        
                          

                        <!--Category info -->
                        <div class="form-item l goods-cat">
                            <div class="form-key">
                                <span>Category</span>
                            </div>
                             <div class="form-value">
                                <div class="form-input-wr">
								    <select id="cid" style="width: 40%;height: 28px;color: rgb(68, 68, 68);font-size: 1.17em;line-height: 28px;background-color: transparent;"> 
										<option>---Main category----</option> 
										<#if goodsCategorys??>
										<#list goodsCategorys as goodsCategory>
											<#if goodsCategory.parent??>
											<#else>
											<option value="${goodsCategory.id}">${goodsCategory.name}</option> 
											</#if>
										</#list>
										</#if>
								   </select> 
								   <select id="cid2" name="goodsCategory.id" style="width: 40%;height: 28px;color: rgb(68, 68, 68);font-size: 1.17em;line-height: 28px;background-color: transparent;"> 
								   		<option value="-1">----Sub-category----</option>
								   		<#if goodsCategorys??>
										<#list goodsCategorys as goodsCategory>
								   		<#if goodsCategory.parent??>
										<option style="display:none;" value="${goodsCategory.id}" pid="${goodsCategory.parent.id}">${goodsCategory.name}</option> 
										</#if>
										</#list>
										</#if>
								   </select> 
							    </div>
                            </div>
                        </div> 
                        
                        
                        
                    </div>
                    
                   	<input class="form-submit" id="submit-btn" type="button" value="Continue" />
                  </div>
                  </form>
            </div>
        </div>
 	<#include "../common/right_menu.ftl"/>
	<#include "../common/bottom_footer.ftl"/> 
<script  src="/home/js/jquery-3.1.1.min.js"></script>
<script src="/home/js/common.js"></script>
<script src="/home/js/add.js"></script>
<script type="text/javascript">
$(document).ready(function(){
   $("#cid").change(function(){
	    var cid = $(this).val();
	    $("#cid2 option[value='-1']").prop("selected", true);
		$("#cid2 option[value!='-1']").css({'display':'none'});
		$("#cid2 option").each(function(i,e){
			if($(e).attr('pid') == cid){
				$(e).css({'display':'block'})
			}
		});
   });
   $("#submit-btn").click(function(){
   		var flag = true;
   		$(".required").each(function(i,e){
   			if($(e).val() == ''){
   				alert($(e).attr('tips'));
   				flag = false;
   				return false;
   			}
   		});
   		if(flag){
   			if($("#desc").val().length < 15){
	   			alert('Goods detail can not be less than 15 charaters!');
	   			return;
	   		}
	   		if(parseFloat($("#buyPrice").val()) == 'NaN'){
	   			alert('Price can only contain numbers!');
	   			return;
	   		}
	   		if(parseFloat($("#sellPrice").val()) == 'NaN'){
	   			alert('price now can only contain numbers!');
	   			return;
	   		}
	   		//Check category
	   		if($("#cid2").val() == '' || $("#cid2").val() == '-1'){
	   			alert('Please select category!');
	   			return;
	   		}
	   		//All passed , ready to submit form
	   		ajaxRequest('publish','post',$("#publish-form").serialize(),function(){
	   			window.location.href="index";
	   		});
   		}
   });
   //File uploading listening button
   $("#uploadFile").change(function(){
   		uploadPhoto('uploaded-img','photo');
   })

});
function uploadPhoto(showPictureImg,input){
	//formdata
	var formData = new FormData();
	formData.append('photo',document.getElementById('uploadFile').files[0]);
	$.ajax({
		url:'/home/upload/upload_photo',
		contentType:false,
		processData:false,
		data:formData,
		type:'POST',
		success:function(data){
				$('.loading').addClass("hide")
				if(data.code == 0){
					$("#show-img").show();
					$("#"+showPictureImg).attr('src','/photo/view?filename=' + data.data);
					$("#"+input).val(data.data);
					
				}else{
					data = $.parseJSON(data);
					alert(data.msg);
				}
			},
			error:function(data){
				alert('Network error!');
			}
	});
}
</script>	
</html>