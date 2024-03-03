<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
<title>${siteName!""}-Post request</title>
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
                <div class="wantInfo-release-title">Post request</div>
                <form action="publish" id="publish-form" method="post">
                <div class="form-wr">
                    <div class="form-must-wr">
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
                        
                        
                        <div class="form-item l goods-title">
                            <div class="form-key">
                                <span>Trade location</span></div>
                            <div class="form-value">
                                <div class="form-input-wr">
                                    <input class="required" id="tradePlace" name="tradePlace" value="" type="text" tips="Please enter Trade location"></div>
                            </div>
                        </div>
                        <div class="form-item l goods-price">
                            <div class="form-key">
                                <span>Expecting price</span></div>
                            <div class="form-value">
                                <div class="form-input-wr">
                                    <input class="price required" id="sellPrice" name="sellPrice" value="" type="number" tips="Please enter Expecting price"></div>
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
	   		if(parseFloat($("#sellPrice").val()) == 'NaN'){
	   			alert('Expecting pricecan only contain numbers!');
	   			return;
	   		}
	   		//All passed , ready to submit form
	   		ajaxRequest('publish_wanted','post',$("#publish-form").serialize(),function(){
	   			window.location.href="index";
	   		});
   		}
   });

});

</script>	
</html>