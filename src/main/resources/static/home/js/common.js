var zb_cookie = {
    setCookie:function(name,value,iTime){
        if(arguments.length==2){
            var iTime = 300;
        }
        var oDate = new Date();
        //Set cookie out date time
        oDate.setTime(oDate.getTime()+iTime*24*3600*1000);
        document.cookie = name+'='+value+';path=/;expires='+oDate.toGMTString();
    },
    getCookie:function(name){
        var arr = document.cookie.split("; ");
        for(var i=0; i<arr.length; i++){
            if(arr[i].split("=")[0] == name){
                return arr[i].split("=")[1];
            }
        }
        return '';
    },
    removeCookie:function(name){
        if(name.indexOf(",")==-1){
            this.setCookie(name,1,-1);
        }else{
            var arr = name.split(",");
            for(var i=0; i<arr.length; i++){
                setCookie(arr[i],1,-1);
            }
        }
    }
};
String.prototype.right = function(i) { //Add a Right method for String object
    return this.slice(this.length - i,this.length);
};

(function() {
    var windowScroll = function() {//Page scrolling
        var state = false,
            header = $("header"),
            nav = $("nav");
        function _dealScroll() {
            //alert(this.scrollY);
            var scroll_top;
            if(this.scrollY !== undefined){
                scroll_top = this.scrollY;
            }else{
                scroll_top = document.documentElement.scrollTop;
            }
            if(!state && scroll_top > 0) {
                state = true;
                $(header).addClass("scroll");
                $(nav).addClass("scroll");

                    $(".nav-icons").addClass("hidden");

            }
            else if(state && scroll_top <= 0) {
                state = false;
                $(header).removeClass("scroll");
                $(nav).removeClass("scroll");
                setTimeout(function(){
                    $(".nav-icons").removeClass("hidden");
                },150);
            }
        }
        function _bind() {
            $(window).on("scroll", _dealScroll);
        }
        return {
            bind: _bind
        };
    }();

    var searchBox = function() { //Search
        var form = $(".search-box"),
            input = $("#keyword");

        function _validInput() {
            if($(input).val() == "")
                return false;
            return true;
        }

        function _bind() {
            $(form).on("submit", function(e) {
                return _validInput();
            });
        }

        return {
            bind: _bind
        };
    }();

    function validText(value) {
        if( value == "" || value == null ) return 0;
        return 1;
    }

    function validEmail(value) {
        if(value == "" || value == null) return 0;
        return /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/.test(value);
    }

    function formValid(type, value) {
        switch(type) {
            case "text":
            case "password":
                return validText(value);
            case "email":
                return validEmail(value);
            case "tel":
                return validTel(value);
            default: return false;
        }
    }
    //login_init();

    var lr = function() {
        var el = $(".log-reg"),
            state = "n",
            log = $(".login-cover"),
            reg = $(".reg-cover"),
            alog = $(".reg-cover a.log"),
            areg = $(".login-cover a.reg"),
            lc = $(".login-cover a.close"),
            rc = $(".reg-cover a.close"),
            lInputWrs = $(".login-form-container .input-name"),
            rInputWrs = $(".reg-form-container .input-name"),
            lsub = $(".login-form-container .submit"),
            rsub = $(".reg-form-container .submit"),
            inputL = [];

        function placeholder(el) {
            this.el = el;
            this.input = $(this.el).children("input").get(0);
            this.inputFocus = false;
            this.parent = $(this.el).parent().get(0);
            this.state = 0;  //0 stand for null， 1 stand for correct， -1 stand for error
        }
        placeholder.prototype.binded = [];
        placeholder.prototype.className = "input-name";
        placeholder.prototype.fire = function() {
            $(this.input).on("focus", this.handleFocus);
            var p = this.parent;
            if( $.inArray(p, this.binded) >= 0 ) return ;
            this.binded.push(p);
            $(p).on("click", "." + this.className, this.handleClick);
        };
        placeholder.prototype.handleFocus = function(e) {
            var that = inputL[ $.inArray( e.currentTarget, $(inputL).map(function(index, el) {
                return el.input;
            }) ) ];
            that.inputFocus = true;
            that.dealInputF();
        };
        placeholder.prototype.handleBlur = function(e) {
            var that = e.data.that;
            that.dealInputB.call(this, that);
        }
        placeholder.prototype.handleClick = function(e) {
            var that = inputL[ $.inArray( e.currentTarget, $(inputL).map(function(index, el) {
                return el.el;
            }) ) ];
            that.dealInputF();
        };
        placeholder.prototype.dealInputF = function() {
            if( !this.inputFocus ) {
                $(this.input).focus();
                this.inputFocus = true;
            }
            $(this.el).addClass("focus");
            $(this.input).one("blur", {that: this}, this.handleBlur);
        };
        placeholder.prototype.dealInputB = function(that) {
            var note = formValid(this.type, this.value);
            that.inputFocus = false;
            if( note === 0 ) {
                $(that.el).removeClass("focus");
                that.state = 0;
            }
            else if( !note ) {
                alert( $(that.el).data("value") + "Invaild format!");
                that.state = -1;
            } else {
                that.state = 1;
            }
        }

        function firePlaceholder(arr) {
            inputL = $(arr).map(function(index, el) {
                return new placeholder(el);
            });
            $(inputL).each(function(index, el) {
                el.fire();
            });
        }

        function _show(type) {
            if( type === "l" ) {
                state = "l";
                $(log).fadeIn();
                firePlaceholder(lInputWrs);
                $(log).on("click", function(e) {
                    if(e.target === log.get(0))
                    _hide("l");
                });
                $(areg).one("click", function(e) {
                    _hide("l");
                    _show("r");
                });
            }
            else {
                state = "r";
                $(reg).fadeIn();
                firePlaceholder(rInputWrs);
                $(reg).on("click", function(e) {
                    if(e.target === reg.get(0))
                        _hide("r");
                });
                $(alog).one("click", function(e) {
                    _hide("r");
                    _show("l");
                });
            }
        }

        function _hide(type) {
            type === "l" ? $(log).fadeOut() : $(reg).fadeOut();
            state = "n";
        }

        function _togglePopup(type) {
            state === "n" ? _show(type) : _hide(type);
        }

        function _bind() {
            $(el).on("click", ".button", function() {
                zb_cookie.removeCookie("cur_url");
                _togglePopup( $(this).data("type") );
            });
        }

        return {
            bind: _bind
        };
    }();
    lr.bind();
    windowScroll.bind();
    searchBox.bind();
    //Retrieve total item sold
    ajaxRequest('/home/ads/get_sold_total','post',{},function(rst){
    	$("#order-count").text(rst.data);
	});
})();

function is_empty(str){
    return $.trim(str).length == 0;
}

//Login
function login(){
    var username = $("#user-name").val();
    var password = $("#pw").val();

    if (username.length == 0
        || password.length == 0)
    {
        alert("Username and password cannot be null!");
        return;
    }
    $.post(
        '/login',
        {username : username, password : password},
        function(res) {
            res = $.parseJSON(res);
            if (res.code == 0) {
                if(zb_cookie.getCookie("cur_url")==''){
                    window.location.reload();
                }else{
                    window.location.href=zb_cookie.getCookie("cur_url");
                }
            } else {
                alert(res.msg);
            }
        }
    );
}

function register(){//Register
    var email = $("#email").val();
    var nickname = $("#uname").val();
    var password = $("#passw").val();
    var password_repeat = $("#cpassw").val();

    if (is_empty(email) || is_empty(nickname)
        || is_empty(password) || is_empty(password_repeat))
    {
        alert('Please fill in all boxes!');
        return;
    }
    $(".load-tip").text('Loading!');
    $("#circular-loading").removeClass('hidden');
    $.post(
        '/login/register',
        {email : email, nickname : nickname, password : password,
            password_repeat : password_repeat},
        function(res) {
            $("#circular-loading").addClass('hidden');
            res = $.parseJSON(res);
            if (res.code == 0) {
                //location.reload();
                $(".reg-cover").css("display","none");
                var school_area='<div id="department-container" class="hidden"><div id="department-wrapper"><div class="sel-head"><h2>Select you college</h2></div>'+
                    '<div id="sel-d2" class="sel-d2 hidden"><div class="sel-school"><div class="sel-hd"><h3>学校</h3><input type="text" value="" class="department-item" disabled="true">'+
                    '<a href="javascript:;"><img src="http://www.2shoujie.com/resource/image/arr-right.png"></a></div>'+
                    '<div class="sel-content"><div class="school-search"><input placeholder="college name" type="text" value="" class=""><ul class="search-down hidden"></ul></div>'+
                    '<ul class="sel-items"></ul></div></div><div class="sel-academy"><div class="sel-hd"><h3>Course</h3><input type="text" value="" class="department-item" disabled="true">'+
                    '<a href="javascript:;"><img src="http://www.2shoujie.com/resource/image/arr-right.png"></a></div><div class="sel-content">'+
                    '<ul class="sel-items"></ul></div></div><div class="sel-adyear"><div class="sel-hd"><h3>Starting year</h3><input type="text" value="" class="department-item" disabled="true">'+
                    '<a href="javascript:;"><img src="http://www.2shoujie.com/resource/image/arr-right.png"></a></div><div class="sel-content"><ul class="sel-items">';
                var years = "";
                var cur_year = parseInt(new Date().getFullYear(),10);
                for(var i=0; i<10; i++){
                    years+='<li><a value="'+(cur_year-i)+'" href="javascript:;">'+(cur_year-i)+'</a></li>';
                }
                school_area+=years;
                school_area+='</ul></div></div><div class="optpk-container hidden"><div class="optpk-btn"><a href="javascript:;"></a></div></div></div></div></div>';
                $("body").append($(school_area));
                $.ajax({
                    url:"/school",
                    type:"get",
                    success:function(data){
                        var data = $.parseJSON(data);
                        if(data.code==0){
                            var schools = data.data.current;
                            var hot_schools = data.data.hot;
                            $(".sel-school").attr("pk",schools.school_id);
                            $(".sel-school .sel-hd").children("input").val(schools.school_name);
                            var hots = "";
                            for(var i=0; i<hot_schools.length; i++){
                                hots+='<li><a href="javascript:;" value="'+hot_schools[i].school_id+'">'+hot_schools[i].school_name+'</a></li>';
                            }
                            $(".sel-school .sel-items").append($(hots));
                            $("#sel-d2").removeClass("hidden");
                            $("#department-container").removeClass("hidden");
                            selSchool();
                        }else{
                            alert(data.msg);
                        }
                    }
                });

            } else {
                alert(res.msg);
            }
        }
    );
}

function enter_login(e)
{
    var key = window.event ? e.keyCode : e.which;
    if (key == 13) {
        login();
    }   
}

$(".login-form-container").find(".submit").on("click", login);
$(".reg-form-container").find(".submit").on("click", register);

function favorites(){//Fav item
    var ads_id = $("#ads_id").val(),
        favorites_num = parseInt($(".ershou-favorite").text()),
        background_image = '';
    $.post('/ads/favorites', {ads_id : ads_id}, function(res){
        res = $.parseJSON(res);
        if (res.code != 0) {
            alert(res.msg);
            return;
        }
        if (res.data.action == 1) {
            favorites_num++;
            $(".ershou-favorite").css('background-image', 'url(/resource/image/heart_full.png)');
        } else {
            favorites_num--;
            $(".ershou-favorite").css('background-image', 'url(/resource/image/heart.png)');
        }
        $(".ershou-favorite").text(favorites_num);
    });
}

function cancel_favorites(ads_id){//Cancel fav
    $.post('/ads/cancel_favorites', {ads_id : ads_id}, function(res){
        res = $.parseJSON(res);
        if (res.code != 0) {
            alert(res.msg);
            return;
        }
        $("#ads"+ads_id).remove();
    });
}

function stu_cert()
{
    var param1 = $("#i1").attr("param"),
        value1 = $("#i1").val(),
        param2 = $("#i2").attr("param"),
        value2 = $("#i2").val();
    $.post(
        '/user/stu_cert', 
        {param1:param1, param2:param2, value1:value1, value2:value2},
        function(res){
            res = $.parseJSON(res);
            if (res.code != 0) {
                $("#not_match").text(res.msg);
                return;
            }
            $(".std_id_box").hide();
            $("#id_succeed").show();
            setTimeout(function(){
               location.reload();
            }, 2000);
        }
    );
}

function off_shelf(pid){
    if (!confirm('Item will be withdraw, are you sure?')) {
        return;
    }
    $.post('productedit', 
    		{"pid" : pid,"flag":1},
    		function(res){
    			res = $.parseJSON(res);
		        if (res.code != 0) {
		        	//Fail
		            alert(res.msg);
		        } else {
		            location.reload();
		        }
    });
}

function on_shelf(ads_id)
{
    $.post('/ads/on_shelf', {ads_id : ads_id}, function(res){
        res = $.parseJSON(res);
        if (res.code != 0) {
            alert(res.msg);
        } else {
            location.reload();
        }
    });
}

function sold(ads_id){
	
	
    if (!confirm('Confirm the item has been sold?')) {
    	alert("Cancel");
        return;
    }
    $.post('/ads/sold', {ads_id : ads_id}, function(res){
        res = $.parseJSON(res);
        if (res.code != 0) {
            alert(res.msg);
        } else {
            location.reload();
        }
    });   
}

function read(message_id, url)
{
    var message_num = parseInt($(".person_message").text());
    $.post('/message/read', {message_id:message_id}, function(res){
        res = $.parseJSON(res);
        if (res.code != 0) {
            alert(res.msg);
        } else {
            $("#message"+message_id).remove();
            if (message_num-1 <= 0) {
                $(".person_message").remove();
            } else {
                $(".person_message").text(message_num-1);
            }
            if (url) {
                window.location.href = url;
            }
        }
    });
}
//Ajax request
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
				alert(rst.msg);
			}
		},
		error:function(data){
			alert('Network error!');
		}
	});
}