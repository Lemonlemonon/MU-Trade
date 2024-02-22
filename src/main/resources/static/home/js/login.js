$(function(){
	$('#switch_qlogin').click(function(){
		$('#switch_login').removeClass("switch_btn_focus").addClass('switch_btn');
		$('#switch_qlogin').removeClass("switch_btn").addClass('switch_btn_focus');
		$('#switch_bottom').animate({left:'0px',width:'70px'});
		$('#qlogin').css('display','none');
		$('#web_qr_login').css('display','block');
		});
	
		$('#switch_login').click(function(){
		$('#switch_login').removeClass("switch_btn").addClass('switch_btn_focus');
		$('#switch_qlogin').removeClass("switch_btn_focus").addClass('switch_btn');
		$('#switch_bottom').animate({left:'154px',width:'70px'});
		$('#qlogin').css('display','block');
		$('#web_qr_login').css('display','none');
		
		});
		
	if(getParam("a")=='0'){
		$('#switch_login').trigger('click');
	}
	
	});
	

function logintab(){
	scrollTo(0);
	$('#switch_qlogin').removeClass("switch_btn_focus").addClass('switch_btn');
	$('#switch_login').removeClass("switch_btn").addClass('switch_btn_focus');
	$('#switch_bottom').animate({left:'154px',width:'96px'});
	$('#qlogin').css('display','none');
	$('#web_qr_login').css('display','block');
	
}


//get parameter by parameter name
function getParam(pname) { 
    var params = location.search.substr(1); // retrieve all parameter
    var ArrParam = params.split('&'); 
    if (ArrParam.length == 1) { 
        //With only one parameter
        return params.split('=')[1]; 
    } 
    else { 
         //With multiple parameter
        for (var i = 0; i < ArrParam.length; i++) { 
            if (ArrParam[i].split('=')[0] == pname) { 
                return ArrParam[i].split('=')[1]; 
            } 
        } 
    } 
}  


var reMethod = "POST",
	pwdmin = 3;

$(document).ready(function() {
	
	$('#login-sn').focus(function(){
		$('#loginCue').html("<font ><b>Enter Student ID</b></font>");
	}).blur(function(){
		if($('#login-sn').val().length == 0){
			$('#loginCue').html("<font color='red'><b>Student id cannot be empty!</b></font>");
			return false;
		}else{
			$.ajax({
				type: "POST" ,
				url: "check_sn",
				data: "sn=" + $("#login-sn").val() + '&flag=' + 1,
				dataType:'json',
				success: function(res) {
					//alert("res:"+res+"\n"+"resLength:"+res.length)
					if (res.data == true) {
						$('#login-sn').css({
							border: "1px solid red",
							boxShadow: "0 0 2px red"
						});
						$("#loginCue").html("<font color='red'><b>Student ID not exist</b></font>");
						$("#login_button").attr("disabled","true");
						return false;
					} else {
						$('#login-sn').css({
							border: "1px solid #D7D7D7",
							boxShadow: "none"
						});
						$("#loginCue").focus().html("<font ><b>Enter password</b></font>");
						$("#login_button").removeAttr("disabled");
					}}
			});
		}
	})
	
	$('#login_button').click(function(){
		if($('#login-sn').val().length == 0){
			$('#login-sn').focus().css({
				border: "1px solid red",
				boxShadow: "0 0 2px red"
			});
			$('#loginCue').html("<font color='red'><b>Student ID can not be null</b></font>");
			return false;
		}else {
			$('#login-sn').css({
				border: "1px solid #D7D7D7",
				boxShadow: "none"
			});
		}
		
		if($('#pwd').val().length == 0){
			$('#pwd').focus().css({
				border: "1px solid red",
				boxShadow: "0 0 2px red"
			});
			$('#loginCue').html("<font color='red'><b>Password can not be null</b></font>");
			return false;
		}else {
			$('#pwd').css({
				border: "1px solid #D7D7D7",
				boxShadow: "none"
			});
		}
		
		$.ajax({
			type: "POST" ,
			url: "login",
			data: $('#login_form').serialize(),
			dataType:'json',
			success: function(res) {
					//alert("res:"+res+"\n"+"resLength:"+res.length)
					if(res.code == 0){
						window.location.href = 'index';
						return;
					}
					
					$("#loginCue").html("<font color='red'><b>"+res.msg+"</b></font>");
					return false;
				}
		});
	})

//Sign up
	
	$("#user-sn").blur(function(){
		$.ajax({
			type: "POST" ,
			url: "check_sn",
			data: "sn=" + $("#user-sn").val() + '&flag=' + 1,
			dataType:'json',
			success: function(res) {
				//alert("res:"+res+"\n"+"resLength:"+res.length)
				if(res.data == false){
					$('#user-sn').css({
						border: "1px solid red",
						boxShadow: "0 0 2px red"
					});
					$("#userCue").html("<font color='red'><b>Student ID already exist</b></font>");
					return false;
				}else {
					$('#user-sn').css({
						border: "1px solid #D7D7D7",
						boxShadow: "none"
					});
					$("#userCue").html("<font ><b>Enter password</b></font>");
				}}
		});
	})
	
	
	
	$('#reg_button').click(function() {
		var sqq = /^[1-9]{1}[0-9]{4,9}$/;
		regusersn=/^\d{1,}$/;
		if ($('#user-sn').val().length<1 || regusersn.test($('#user-sn').val()) == false) {
			$('#user-sn').focus().css({
				border: "1px solid red",
				boxShadow: "0 0 2px red"
			});
			$('#userCue').html("<font color='red'><b>Invaild student ID</b></font>");
			return false;
		}else {
			$('#user-sn').css({
				border: "1px solid #D7D7D7",
				boxShadow: "none"
			});
		}
		
		
		if ($('#password').val().length < pwdmin) {
			$('#password').focus().css({
				border: "1px solid red",
				boxShadow: "0 0 2px red"
			});
			$('#userCue').html("<font color='red'><b>×Password need to be longer than " + pwdmin + " digits</b></font>");
			return false;
		}else {
			$('#password').css({
				border: "1px solid #D7D7D7",
				boxShadow: "none"
			});
		}

		if ($('#password2').val() != $('#password').val()) {
			$('#password2').focus();
			$('#userCue').html("<font color='red'><b>×Two passwords are different!</b></font>");
			return false;
		}

		$.ajax({
			type: "POST" ,
			url: "register",
			data: $('#regForm').serialize(),
			dataType:'json',
			success: function(res) {
					//alert("res:"+res+"\n"+"resLength:"+res.length)
					if(res.code == 0){
						window.location.href = 'index';
						return;
					}
					$('#user-sn').css({
						border: "1px solid red",
						boxShadow: "0 0 2px red"
					});
					$("#userCue").html("<font color='red'><b>"+res.msg+"</b></font>");
					return false;
				}
		});
	});
	

});