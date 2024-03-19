function edit_info(){
    $("#edit_info").bind('click',function(){
        $(this).css({
            display: "none"
        })
        $("#save_info").css({
            display: "block"
        })
        $(".right_info span").css({
            display: "none"
        })
        $(".right_info input").css({
            display: "inline"
        })
    });
    $("#save_info").bind('click',function(){
        var nickname = $("#nickname").val(),
            phone = $("#phone").val();
        $.post(
            '/user/modify',
            {
                user_phone_number : phone,
                user_nickname : nickname
            },
            function(res) {
                res = $.parseJSON(res);
                if (res.code != 0) {
                    alert(res.msg);
                    return;
                }
                $("#phone_span").text(phone);
                $("#nickname_span").text(nickname);
                $("#save_info").css({
                    display: "none"
                });
                $("#edit_info").css({
                    display: "block"
                });
                $(".right_info input").css({
                    display: "none"
                });
                $(".right_info span").css({
                    display: "inline"
                });
            }
        );
    });
}
function change_photo(){
    $("#user_photo").bind('mouseenter',function(){
       $("#change_ph").css({
            display: "block"
        })
    });
    $("#user_photo").bind('mouseleave',function(){
       $("#change_ph").css({
            display: "none"
        })
    });
}