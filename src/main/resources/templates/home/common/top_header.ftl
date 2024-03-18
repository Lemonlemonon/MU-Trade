<style>
.loginandrigist {
    margin-left: 1000px;
    margin-top: -66px;
}

#person_info {
    height: 100%;
    cursor: pointer;
    position: relative;
}
.clearfix {
    zoom: 1;
}



.informationCueTip:after {
	position: absolute;
	content: '';
	width: 10px;
	height: 10px;
	background: red;
	border-radius: 100%;
	right: 0;
	top: 0;
}
.informationCue {
	margin-right: 20px;
	position: relative;
	cursor: pointer;
}
.NoticeList {
	position: fixed;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	z-index: 998;
	display: none;
	/*display: flex;*/
	/*justify-content: flex-end;*/
}
.NoticeListShow {
	display: block;

}
.NoticeListBox {
	position: fixed;
	width: 400px;
	height: 100%;
	top: 0;
	bottom: 0;
	right: -500px;
	transition: all 0.3s;
	background: #FFF;
	box-shadow: -5px 0 30px -10px rgba(0, 0, 0, 0.5);
	display: flex;
	flex-direction: column;
	padding: 20px 0 20px 20px;
	box-sizing: border-box;
	z-index: 999;
}
.NoticeListBoxShow {
	right: 0px;
}
.NoticeListBox .Title {
	font-size: 20px;
	text-align: center;
	margin-bottom: 20px;
}
.NoticeListBox ul li {
	display: flex;
	flex-direction: column;
	font-size: 15px;
	padding: 10px 0;
	border-bottom: 1px solid #EEE;
}
.readBtn {
	align-self: flex-end;
	padding: 5px 10px;
	border-radius: 6px;
	background: #c8c8ce;
	font-size: 13px;
	cursor: pointer;
	color: #FFF;
	margin-top: 5px;
}
.NoticeListBox ul {
	overflow-y: scroll;
	padding-right: 20px;
}
.NoticeListBox ul::-webkit-scrollbar {
	width: 4px;
	height: 5px;
}
.NoticeListBox ul::-webkit-scrollbar-thumb {
	background-color: #0003;
	border-radius: 3px;
	transition: all .2s ease-in-out;
}
.NoticeListBox ul::-webkit-scrollbar-track {
	border-radius: 3px;
}
.NoticeListBox ul::-webkit-scrollbar-thumb:hover {
	background: #B4B4B4;
}
.NoticeListBox ul li .unreadBtn {
	background: rgb(255, 180, 0);
}
.InitiatePrivateMessages {
	position: absolute;
	right: 20px;
	top: 20px;
	cursor: pointer;
}
.PrivateMessages {
	display: none;
	position: fixed;
	top: 20%;
	left: 50%;
	margin-left: -250px;
	width: 500px;
	height: 300px;
	flex-direction: column;
	background: #FFF;
	box-shadow: 0px 10px 20px #a19a9a;
	padding: 20px;
	z-index: 999;
}
.PrivateMessagesClose {
	position: absolute;
	right: 20px;
	top: 20px;
	font-size: 15px;
	cursor: pointer;
}
.PrivateMessagesCloseShow {
	display: flex;
}
.PrivateMessages .title {
	text-align: center;
	font-size: 20px;
}

</style>
<header class="ease2">
		<div class="NoticeList" id="NoticeList"></div>
		<div class="NoticeListBox" id="NoticeListBox" onclick="event.cancelBubble =true">
			<span class="Title">Notification</span>
			<span class="InitiatePrivateMessages" id="PrivateMessagesShow">Send message</span>
			<div class="NoticeListUl" id="NoticeListUl">
			</div>
		</div>
		<div class="PrivateMessages" id="PrivateMessages">
			<span class="PrivateMessagesClose" id="PrivateMessagesClose">X</span>
			<span class="title">Send message</span>
			<div style="margin-top: 20px">
				<span style="margin-right: 10px;font-size: 15px">Reciever Id：</span>
				<input type="text" placeholder="Please enter Reciever Id!" id="userIDCon">
			</div>
			<textarea style="height: 250px;margin-top: 10px" placeholder="Please enter content!"  id="commentContent"></textarea>
			<span class="readBtn" style="margin-top: 5px" id="SendPrivateMessageBtn">Send</span>
		</div>
	    <a href="/home/index/index">
	        <#if siteSetting.logo1??>
	        <img class="logo ease2" src="/photo/view?filename=${siteSetting.logo1}" alt="${siteSetting.siteName}">
	        <#else>
	        <img class="logo ease2" src="/home/imgs/index_logo.png" alt="${siteSetting.siteName}">
	    	</#if>
	    </a>
	    <div class="header-main center ease2">
	        <a href="/home/index/index" class="slogan">
	            <h1 class="s-main"></h1>
	            <div class="s-submain"></div>
	            <#if siteSetting.logo1??>
		        <img src="/photo/view?filename=${siteSetting.logo2}" alt="${siteSetting.siteName!""}">
		        <#else>
	            <img src="/home/imgs/2shoujie_web_title.png" alt="${siteSetting.siteName!""}">
	        	</#if>
	        </a>
	        <div class="search-box-wr ease2">
	            <div class="search-box center" >
	               <button class="search-submit" id="search-button" onclick="searchPro()">Search</button> 
	                <div class="input-wr">
	                    <img class="search-icon" src="/home/imgs/search-icon.png">
	                    <div class="search-input">
	                    <input name="keyword" id="keyword" x-webkit-speech="" placeholder="What are you looking for?" value="" type="text">
	                    </div>
	                </div>
	            </div>

	            <div class="search-hots center ease2">
	                <span>Trending: </span>
	                <#list goodsCategorys as hotGoodsCategory>
			        <#if hotGoodsCategory.parent??>
			        <#else>
	                <a class="hots" href="/home/goods/list?cid=${hotGoodsCategory.id}" target="_top">${hotGoodsCategory.name}</a>
	                </#if>
	                <#if hotGoodsCategory_index == 20>
	                <#break>
    				</#if>
	                </#list>
	            </div>
	        </div>
	        
	        <div class="ease2 log-reg" id="have-not-login">
	       		<#if fyp_student??>
				<div id="have_login" class="clearfix" style="display: flex;align-items: center;">
					<#--Notification Icon-->
					<div class="informationCue" id="informationCue">
						<img id="NoticePrompt" style="height:40px;width:40px;" title="Notification" alt="Notification" src="/home/imgs/informationCue.png">
						<#--Hide Icon when not logined-->
						<input style="opacity: 0;position: absolute;right: -99999px" disabled value="${fyp_student.id!""}" id="auctionID" type="text">
					</div>

		        <div id="have_login" class="clearfix">
		        	<div id="person_info" class="clearfix">
		        		<a href="../student/index">
		        			<#if fyp_student.headPic??>
		        			<img  class="avatar"  style="height:48px;width:48px;" src="/photo/view?filename=${fyp_student.headPic}">
		        			<#else>
		        			<img  class="avatar"  style="height:48px;width:48px;" src="/home/imgs/avatar1.png">
		        			</#if>
		        		</a>
			        	<div  style="display:inline;"  class="person_name">
			        		<a href="../student/index" id="id-btn">Hi: ${fyp_student.nickname!fyp_student.sn}</a>
			        	</div>
			        	<div  style="display:inline;"  class="person_name">
			        		<a href="../index/logout" id="log-btn">&nbsp;&nbsp;&nbsp;&nbsp;Logout</a>
			        	</div>
			        </div>
		        </div>
		        <#else>
	       		<!-- loginandrigist -->
	            <div class="button" ><a href="../index/login">Login</a></div>
		        </#if>
	        </div>
	    </div>
	</header>
<script src="/home/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
function searchPro(){
	var keyword=$("#keyword").val();
	window.location.href="/home/index/index?name="+keyword;
}


$(document).ready(function(){
	let name = $("#auctionID").val();
	if (name == undefined) {
		console.log(111)
	}else {
		console.log(22)
		GetListNotifications()
		// Open/Close notification pannel
		$("#NoticePrompt").click(function(){
			$('#NoticeList').addClass('NoticeListShow');
			$('#NoticeListBox').addClass('NoticeListBoxShow');
		})
		$("#NoticeList").click(function(){
			$('#NoticeList').removeClass('NoticeListShow');
			$('#NoticeListBox').removeClass('NoticeListBoxShow');
		})
		// Get user notifications
		function GetListNotifications () {
			let name = $("#auctionID").val();
			$.ajax({
				url:'/notice',
				type:'get',
				data: {"userId":name},
				dataType:'json',
				success:function(res){
					if (res.code == 0) {
						NoticeList = res.data.data
						console.log(NoticeList)
						let liItem = "<ul class='ListItems'>"
						let HaveNotRead = false
						for(let i=0; i< NoticeList.length; i++){
							if (NoticeList[i].isRead == 0) {
								HaveNotRead = true
							}
							var className = NoticeList[i].isRead == 1? 'readBtn': 'readBtn unreadBtn'
							var className2 = NoticeList[i].isRead == 1? 'Read': 'New'
							var mypro1 = '<li><span>' + "Content: " + NoticeList[i].content+ '</span><span>' + "Time: " + NoticeList[i].createTime + '</span><span>' + "From UserId: " + NoticeList[i].sendId  +'</span><span class="'+className+'">'+ className2 + '</span> </li>'
							liItem += mypro1
						}
						if (HaveNotRead) {
							$('#informationCue').addClass('informationCueTip')
						}else {
							$('#informationCue').removeClass('informationCueTip')
						}
						liItem += "</ul>";
						$("#NoticeListUl")[0].innerHTML = liItem

						let lis = $('.ListItems').children()
						for (let j = 0; j < NoticeList.length; j++) {
							lis[j].index = j
							lis[j].onclick = function () {
								if (NoticeList[this.index].isRead == 1) {
									alert('This message has already read!')
									return
								}
								$.ajax({
									url: '/notice',
									type: 'put',
									data: {"noticeId": NoticeList[this.index].id},
									dataType: 'json',
									success: function (res) {
										if (res.code == 0) {
											alert(res.data.data)
											GetListNotifications()
										}
									}
								})
							}
						}
						return
					}
				}
			});
		}

		// Open/Close Private Message window
		$("#PrivateMessagesClose").click(function(){
			$('#PrivateMessages').removeClass('PrivateMessagesCloseShow');
		})
		$("#PrivateMessagesShow").click(function(){
			$('#PrivateMessages').addClass('PrivateMessagesCloseShow');
		})
		$("#SendPrivateMessageBtn").click(function(){
			let nameID = $("#auctionID").val();
			let userid = $("#userIDCon").val();
			let Con = $("#commentContent").val();
			if (userid == '') {
				alert('Please enter reciever ID!');
				return
			}
			if (userid == nameID) {
				alert('You cannot send message to yourself!');
				return
			}
			if (Con == '') {
				alert('Please fill in the message content!');
				return
			}
			$.ajax({
				type: "POST" ,
				url: "/notice",
				data: JSON.stringify({"receiverId":userid,"content": Con, "sendId": nameID}),
				contentType: "application/json",
				dataType:'json',
				success: function(res) {
					if (res.code == 0) {
						$("#commentContent").val('')
						$("#userIDCon").val('')
						$('#PrivateMessages').removeClass('PrivateMessagesCloseShow');
						GetListNotifications()
						alert('Your message has been sent!');
						return
					}
					alert(res.data?res.data:res.msg);
				}
			});
		})
	}
});
</script>