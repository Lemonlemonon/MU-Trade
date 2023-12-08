//Show success msg
function showSuccessMsg(msg,callback){
	$.confirm({
        title: 'Success',
        content: msg,
        type: 'green',
        typeAnimated: false,
        buttons: {
            omg: {
                text: 'Yes',
                btnClass: 'btn-green',
                action: function(){
                	callback();
        		}
            }
        }
    });
}
//Show error msg
function showErrorMsg(msg){
	$.confirm({
        title: 'Error',
        content: msg,
        type: 'red',
        typeAnimated: false,
        buttons: {
            omg: {
                text: 'Yes',
                btnClass: 'btn-red',
                action: function(){
                	
        		}
            }
        }
    });
}
//Show warning msg
function showWarningMsg(msg){
	$.confirm({
        title: 'Warning',
        content: msg,
        type: 'red',
        typeAnimated: false,
        buttons: {
            omg: {
                text: 'Yes',
                btnClass: 'btn-red',
                action: function(){
                	
        		}
            }
        }
    });
}
//Public method for validating form using form id
function checkForm(formId){
	var flag = true;
	$("#"+formId).find(".required").each(function(i,e){
		if($(e).val() == ''){
			showWarningMsg($(e).attr('tips'));
			flag = false;
			return false;
		}
	});
	return flag;
}
//Image upload method
function upload(showPictureImg,input){
	//formdata
	var formData = new FormData();
	formData.append('photo',document.getElementById('select-file').files[0]);
	$.ajax({
		url:'/admin/upload/upload_photo',
		contentType:false,
		processData:false,
		data:formData,
		type:'POST',
		success:function(data){
				if(data.code == 0){
					showSuccessMsg('Image uploaded!',function(){
						$("#"+showPictureImg).attr('src','/photo/view?filename=' + data.data);
						$("#"+input).val(data.data);
					})
				}else{
					data = $.parseJSON(data);
					showErrorMsg(data.msg);
				}
			},
			error:function(data){
				alert('Network error!');
			}
	});
}
//Multi image upload method
function uploadMuilt(showPictureImg,input,selectFile){
	//formdata
	var formData = new FormData();
	formData.append('photo',document.getElementById(selectFile).files[0]);
	$.ajax({
		url:'/admin/upload/upload_photo',
		contentType:false,
		processData:false,
		data:formData,
		type:'POST',
		success:function(data){
				if(data.code == 0){
					showSuccessMsg('Image uploaded!',function(){
						$("#"+showPictureImg).attr('src','/photo/view?filename=' + data.data);
						$("#"+input).val(data.data);
					})
				}else{
					data = $.parseJSON(data);
					showErrorMsg(data.msg);
				}
			},
			error:function(data){
				alert('Network error!');
			}
	});
}
//Global ajax request
function ajaxRequest(url,requestType,data,callback){
	$.ajax({
		url:url,
		type:requestType,
		data:data,
		dataType:'json',
		success:function(rst){
			if(rst.code == 0){
				callback(rst);
			}else{
				showErrorMsg(rst.msg);
			}
		},
		error:function(data){
			alert('Network error!');
		}
	});
}