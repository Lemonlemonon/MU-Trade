$(document).ready(function(){
    //login_slider();
    // discriber();
    //login_close();
    //ershou_photo_slide();
    //wave_move();
    edit_info();
    change_photo();
    /*choose_sch();*/
    //student_id();
    if($("#user_msg").length>0){
        grade_value_slide();
    }
    if($("#intergral-wrapper").length>0){
        scoreRotate();
    }
    if($(".return-to-top").length>0){
        returnToTop();
    }
    $(".close-head-img,.btn-ok,.btn-cancel").click(function(){
    	$("#head-img-box").addClass('hide');
    })
    $("#upload-person-img").click(function(){
    	$("#selected-file").click();
    });
})
function uploadImg(){
	$('.loading').removeClass("hide").css({'margin-top':'0px'});
	uploadPhoto('origin_ph','');
}
//Photo uploading method
function uploadPhoto(showPictureImg,input){
	//formdata
	var formData = new FormData();
	formData.append('photo',document.getElementById('selected-file').files[0]);
	$.ajax({
		url:'/home/upload/upload_photo',
		contentType:false,
		processData:false,
		data:formData,
		type:'POST',
		success:function(data){
				$('.loading').addClass("hide")
				if(data.code == 0){
					$("#head-img-box").addClass('hide');
					$(".pop-tip-txt").text('Image uploaded!');
					$(".pop-tip").removeClass('hide');
					$("#"+showPictureImg).attr('src','/photo/view?filename=' + data.data);
					$("#"+showPictureImg).attr('old-src','/photo/view?filename=' + data.data);
					//Send requests to back-end, change user avatar from data base
					$.ajax({
						url:'update_head_pic',
						type:'post',
						dataType:'json',
						data:{headPic:data.data},
						success:function(rst){
							if(rst.code != 0){
								alert(rst.msg);
							}
						},
						error:function(rst){
							alert('Network error!');
						}
					});
				}else{
					data = $.parseJSON(data);
					$(".pop-tip-txt").text(data.msg);
					$(".pop-tip").removeClass('hide');
				}
				setTimeout(function(){
					$(".pop-tip").addClass('hide');
				},2000);
			},
			error:function(data){
				alert('Network error!');
			}
	});
}
function grade_value_slide(){
    if($("#need_value").length==0) return;
    var value_got = parseInt($("#value_box li:eq(1) span").html());
    var value_need = parseInt($("#value_box li:eq(2) span").html());
    var total_value = $("#need_value")[0];
    var total_width = parseInt(document.defaultView.getComputedStyle(total_value, null).width);
    var current_width = value_got/(value_got+value_need)*total_width;
    $("#got_value")[0].style.width = current_width + "px";
    $("#grade_value_btn")[0].style.left = current_width -15+ "px";
    $("#value_box")[0].style.left = current_width-70+ "px";
}
function student_id(){
    $("#student_id .id_it").bind('click',function(){
        $(".back_layer,.std_id_box").fadeIn();
    });
    $("#id_close").bind('click',function(){
        $(".back_layer,.std_id_box").fadeOut();
    });
}
function edit_info(){
    $("#edit_info").bind('click',function(){
        $(this).css({
            display: "none"
        })
        $("#save_info").css({
            display: "block"
        })
        $(".right_info span.baseinfo").css({
            display: "none"
        })
        $(".right_info input.baseinfo").css({
            display: "inline"
        })
    });
    $("#save_info").bind('click',function(){
        var nickname = $("#nickname").val(),
            mobile = $("#mobile").val(),
            academy=$("#academy").val(),
            grade=$("#grade").val(),
            school=$("#school").val()
        
            $.ajax({
				type: "POST" ,
				url: "edit_info",
				data: {
	            	nickname : nickname,
	                mobile : mobile,
	                academy:academy,
	                grade:grade,
	                school:school
	            },
				dataType:'json',
				success: function(res) {
						//alert("res:"+res+"\n"+"resLength:"+res.length)
						if(res.code == 0){
			                $("#phone_span").text(mobile);
			                $("#nickname_span").text(nickname);
			                $("#college_span").text(academy);
			                $("#grade_span").text(grade);
			                $("#area_span").text(school);
			                $("#save_info").css({
			                    display: "none"
			                });
			                $("#edit_info").css({
			                    display: "block"
			                });
			                $(".right_info input.baseinfo").css({
			                    display: "none"
			                });
			                $(".right_info span.baseinfo").css({
			                    display: "inline"
			                });
							return;
						}
						alert(res.msg);
						return false;
					}
			});
           
    });
}
function change_photo(){
    $("#user_photo").bind('mouseenter',function(){
    	$("#origin_ph").attr('src',$("#change_photo").attr('src'));
    });
    $("#user_photo").bind('mouseleave',function(){
    	$("#origin_ph").attr('src',$("#origin_ph").attr('old-src'));
    	$("#change_photo").css({
            display: "none"
        })
    });
}
function openUploadPanel(){
	$("#head-img-box").removeClass('hide');
}

