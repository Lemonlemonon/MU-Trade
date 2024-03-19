<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
<title>${siteName!""}-Sell ads</title>
<link rel="icon" href="/home/imgs/favicon.ico" type="image/x-icon">
<link media="all" href="/home/css/product_detail.css" type="text/css" rel="stylesheet">
<link media="all" href="/home/css/index.css" type="text/css" rel="stylesheet">
</head>

<style>
	/*bidding button styling*/
	.auction {
		/*display: flex;*/
		/*flex-direction: column;*/
		margin-top: 20px;
		padding: 30px 90px;
		background-color: rgb(255, 255, 255);
	}
	.auction>span {
		margin-right: 20px;
	}
	.auction >.auctionBtn {
		padding: 10px 20px;
		background: #ffb300;
		color: #FFF;
		border-radius: 5px;
		cursor: pointer;
	}
	.auction >.auctionBtn:hover {
		background: rgb(207 148 6);
	}
</style>

<body>
  <#include "../common/top_header.ftl"/>
  <#include "../common/left_menu.ftl"/>
   <div class="container">
        <div class="main center clearfix">
            <div class="ershou-details">
                <div class="ershou-photos-wr">
                       <!-- Play images-->
                        <div class="bigger-photo-box">
                        	<a class="bigger-photo hide show" rel="img_group" href="">
                        		<img class="bigger" src="/photo/view?filename=${ads.photo}"  alt="${ads.name}">
                        	</a>
                        </div>
                </div>
                <div class="ershou-info">
                    <div class="ershou-hd">
                        <p class="ershou-title">${ads.name}</p>
                        <div class=" discount">
                        	<span class="buy-price">Original price: €${ads.buyPrice}    </span>
                        	<span class="ershou-price">Price now: €${ads.sellPrice}</span>
                        </div>
                        <p class="bro-counts"><span>${ads.viewNumber}</span> views</p>
                    </div>
                    <ul class="ershou-detail">
                    	<li class="ershou-place" id="pid">
                    		<div class="name">
                    			<span>Ads id</span>
                    		</div>
                    		<div class="value">
                    			<span id="pid">#${ads.id}</span>
                    		</div>
                        </li>
                        <li class="ershou-time">
                        	<div class="name">
                        		<span>Publish time</span>
                        	</div>
                        	<div class="value">
                        		<span class="real-time"id="creat_time">${ads.createTime!""}</span>
                        	</div>
                        </li>
                        <li class="ershou-place">
                        	<div class="name">
                        		<span>Trade location</span>
                        	</div>
                        	<div class="value">${ads.student.school!"Invaild University"}</div>
                        </li>
                        <li class="ershou-seller">
                        	<div class="name">
                        		<span>Seller</span>
                        	</div>
                        	<div class="value">
                        		<span class="value-name" id="userid">${ads.student.nickname!ads.student.sn}</span>
                        	</div>
                        </li>
                    </ul>
                    <div id="buy-button" >
            			<a style="color: white;cursor:pointer;" > Contact seller</a>
       	 			</div>
                    <div class="complain">
                    	<a href="javascript:void(0);" onclick="report(${ads.id});">•&nbsp;&nbsp;&nbsp;&nbsp;Report&nbsp;&nbsp;&nbsp;&nbsp;•</a>
                    </div>
                </div>
            </div>
            <div class="ershou-desc">
            	<div class="desc clearfix">
            		<a href=""target="_top">
            			<#if ads.student.headPic??>
            			<img id="user_ph" src="/photo/view?filename=${ads.student.headPic}">
            			<#else>
            			<img id="user_ph" src="/home/imgs/avatar1.png">
            			</#if>
            		</a>
            		<p id="user_cmt">${ads.content}</p>
            	</div>
            </div>
			<!--Bidding section-->
			<#if ads.adsBiddingId?? && fyp_student??>
				<div class="auction">
					<span>Current bid: €<span id="CurrentAuctionPrice">--</span></span>
					<span>From user: <span id="CurrentBidder">--</span></span>
					<input id="auctionInput" type="number" placeholder="Your max bid(€):" border: 10px>
					<a href="javascript:void(0);" class="auctionBtn" onclick="auctionInitiate(${ads.adsBiddingId});">Place your bid</a>
					<input style="opacity: 0" disabled value="${fyp_student.nickname!""}" id="auctionNickname" type="text">
					<input style="opacity: 0;width: 20px" disabled value="${ads.adsBiddingId}" id="auctionadsBiddingId" type="text">
				</div>
			</#if>

            <!--Comment section-->
            <div class="comments want-comments" style="width:98.5%">
				<div class="comments-wr" style="border-left:0px;">
					<div class="comment-wr">
						<div class="post-comment clearfix">
								<!--For login user-->
								<#if fyp_student??>
								<#if fyp_student.headPic??>
								<img class="avatar" src="/photo/view?filename=${fyp_student.headPic}" alt="avatar"/>
								<#else>
								<img class="avatar" src="/home/imgs/avatar1.png" alt="avatar"/>
								</#if>
								<div class="commenting want-commenting clearfix" >
									<div class="comment-input-wr-wr" style="margin-right:56px;">
										<div class="comment-input-wr" >
											<textarea class="comment-input-wr" name="comment-input" id="comment-content"></textarea>
										</div>
									</div>
									<button class="sub-comment" type="button" id="submit-comment-btn" data-reply="0" data-reply-content="0">Comment</button>
								</div>
								<#else>
								<!--For unlogin user-->
								<img class="avatar" src="/home/imgs/avatar-unlogin.png" alt="avatar"/>
								<div class="commenting-unlogin want-commenting clearfix">
									<div class="comment-input-wr-wr">
										<div class="comment-input-wr">
											<span class="tips">Login to leave comment!</span>
										</div>
									</div>
									<button class="comment-login" data-type="l" id="to-login-btn">Login</button>
								</div>
								</#if>
								<!--Comment list-->
								<#if commentList??>
								<div class="comments want-comments" style="width:98.5%;margin-top:0px;padding-top:0px;">
									<div class="comments-wr" style="border:0px;">
										<div class="comment-wr">
											<#list commentList as comment>
											<div class="comment" style="margin-top:25px;">
												<#if comment.student.headPic??>
												<img class="avatar" src="/photo/view?filename=${comment.student.headPic}" alt="Avatar"/>
												<#else>
												<img class="avatar" src="/home/imgs/avatar1.png" alt="Avatar"/>
												</#if>
												<div class="commentator" style="padding-left:55px;padding-bottom:5px;color:rgb(0, 120, 139);border-bottom: 1px dashed rgb(0, 120, 139);">
													<b>${comment.student.nickname!comment.student.sn}</b>
													<#if comment.replyTo??><span class="rpy-to">${comment.replyTo}</span></#if>
												</div>
												<p class="comment" style="padding-left:55px;padding-bottom:5px;padding-top:5px;">${comment.content}<font style="float:right;">${comment.createTime}</font></p>
												<div class="man" style="padding-left:55px;padding-bottom:5px;">
													<a class="rpl" href="#comment-content" data-reply="${comment.content}" data-name="${comment.student.nickname!comment.student.sn}">Reply</a>
												</div>
											</div>
											</#list>
										</div>
									</div>
								</div>
								</#if>
						</div>
					</div>
				</div>
			</div>
            <!--End comment section-->
        </div>
    </div>
 	<#include "../common/right_menu.ftl"/>
	<#include "../common/bottom_footer.ftl"/> 
<script  src="/home/js/jquery-3.1.1.min.js"></script>
<script src="/home/js/common.js"></script>
<script src="/home/js/add.js"></script>
<script type="text/javascript">
$(document).ready(function(){
   $("#buy-button").click(function(){
		<#if fyp_student??>
		alert("Please contact seller for more information. "+"\n"+"Tel:${ads.student.mobile!""}\n");
		<#else>
		alert("Please login first");
		window.location.href="/home/index/login";
		</#if>
	})
   $("#to-login-btn").click(function(){
   		window.location.href="/home/index/login";
   });

	$("#submit-comment-btn").click(function(){
		var content = $("#comment-content").val();
		if(content == ''){
			alert('Please enter your comment!');g
			return;
		}
		var data = {'ads.id':${ads.id},content:content};
		if($("#submit-comment-btn").attr('data-reply') != '0'){
			data.replyTo = $("#submit-comment-btn").attr('data-reply');
			data.content = data.content.replace('Replys: ' + data.replyTo + ':','')
			data.content = 'Replys: “' + $("#submit-comment-btn").attr('data-reply-content') + '”<br>' + data.content;
		}
		ajaxRequest('/home/student/comment','post',data,function(){
			alert('Comment posted!');
			//window.location.reload();
		});
	});
	
	$(".rpl").click(function(){
		$("#comment-content").val('Replys: '+$(this).attr('data-name') + ':')
		$("#submit-comment-btn").attr('data-reply',$(this).attr('data-name'))
		$("#submit-comment-btn").attr('data-reply-content',$(this).attr('data-reply'))
	});

	// Get the current bidder of the Ads
	var adsBiddingsId = $("#auctionadsBiddingId").val();
	var fyp_studentName = $("#auctionNickname").val();
	if (adsBiddingsId && fyp_studentName) {
		console.log("test123")
		GetAuctionInformation(1)
	} else {
		console.log("test321")
	}

});
$(window).on('unload', function(){
	GetAuctionInformation(0)
})
function report(id){
	var content = prompt("Please enter report reason");
	if(content == null || content == ''){
		alert('Please enter report reason');
		return;
	}
	ajaxRequest('/home/student/report_ads','post',{'ads.id':id,content:content},function(){
		alert('Report has been sent!');
	});
};
// Get bidding information
function GetAuctionInformation (id) {
	var setIntervals = null
	if (id == 0) {
		clearInterval(setIntervals)
		return
	}
	setIntervals = setInterval(() => {
		$.ajax({
			url:'/bidding/top_price',
			type:'get',
			data: {"biddingId":${ads.adsBiddingId}},
			dataType:'json',
			success:function(res){
				if (res.code == 400) {
					return
				}
				$("#CurrentAuctionPrice").html(res.data.price)
				$("#CurrentBidder").html(res.data.userName)
			}
		});
	}, 3000)
};
// Place bidding
function auctionInitiate (id) {
	let price = $("#auctionInput").val();
	let name = $("#auctionNickname").val(); // User nick name
	let CurrentBidder = $("#CurrentBidder").text() // Current bidder name
	let userid = $("#userid").text() // Seller id
	if (price == '' || price == 0) {
		alert('Please enter your max bidding!');
		return
	}
	if (name == userid) {
		alert('Sorry, you CANNOT bid on your own Ads');
		$("#auctionInput").val('')
		return
	}
	if (name == CurrentBidder) {
		alert('Sorry, you are already the current bidder!');
		$("#auctionInput").val('')
		return
	}
	$.ajax({
		type: "POST" ,
		url: "/bidding/create-bidding",
		data: JSON.stringify({"userId":${fyp_student.id},"biddingId": id,"biddingPrice": price*100,"userName": name}),
		contentType: "application/json",
		dataType:'json',
		success: function(res) {
			if (res.code == 0) {
				alert('Your have successfully placed your bidding!');
				$("#auctionInput").val('')
				return
			}
			alert(res.data?res.data:res.msg);
		}
	});
	// $("#auctionInput").val('')
	// console.log(id, price)
};
</script>	
</html>