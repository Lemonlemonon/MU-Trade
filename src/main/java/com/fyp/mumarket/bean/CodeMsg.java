package com.fyp.mumarket.bean;
/**
 * error code processing class. All error codes are defined here.
 * @author Administrator
 *
 */
public class CodeMsg {

	private int code;//error code
	
	private String msg;//error msg
	
	/**
	 * Using Singleton pattern with private constructor
	 * @param code
	 * @param msg
	 */
	private CodeMsg(int code,String msg){
		this.code = code;
		this.msg = msg;
	}
	
	public int getCode() {
		return code;
	}



	public void setCode(int code) {
		this.code = code;
	}



	public String getMsg() {
		return msg;
	}



	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	//通用错误码定义
	//处理成功消息码
	public static CodeMsg SUCCESS = new CodeMsg(0, "success");
	//非法数据错误码
	public static CodeMsg DATA_ERROR = new CodeMsg(-1, "Illegal value！");
	public static CodeMsg CPACHA_EMPTY = new CodeMsg(-2, "varification code can't be null!！");
	public static CodeMsg VALIDATE_ENTITY_ERROR = new CodeMsg(-3, "");
	public static CodeMsg SESSION_EXPIRED = new CodeMsg(-4, "Session has expired, please refresh the page and try again!");
	public static CodeMsg CPACHA_ERROR = new CodeMsg(-5, "invaild code");
	public static CodeMsg USER_SESSION_EXPIRED = new CodeMsg(-6, "Session has expired, please login again!");
	public static CodeMsg UPLOAD_PHOTO_SUFFIX_ERROR = new CodeMsg(-7, "Invaild image format!");
	public static CodeMsg UPLOAD_PHOTO_ERROR = new CodeMsg(-8, "Error uploading file!");
	
	
	//后台管理类错误码
	//用户管理类错误
	public static CodeMsg ADMIN_USERNAME_EMPTY = new CodeMsg(-2000, "User name can not be null!");
	public static CodeMsg ADMIN_PASSWORD_EMPTY = new CodeMsg(-2001, "Password can not be null!");
	public static CodeMsg ADMIN_NO_RIGHT = new CodeMsg(-2002, "No permission for current user!");
	
	//登录类错误码
	public static CodeMsg ADMIN_USERNAME_NO_EXIST = new CodeMsg(-3000, "User name not exsist!");
	public static CodeMsg ADMIN_PASSWORD_ERROR = new CodeMsg(-3001, "Wrong password!");
	public static CodeMsg ADMIN_USER_UNABLE = new CodeMsg(-3002, "This user has been frozen/suspended, please contact the administrator!");
	public static CodeMsg ADMIN_USER_ROLE_UNABLE = new CodeMsg(-3003, "The status of the role belonging to this user is unavailable, please contact the administrator!");
	public static CodeMsg ADMIN_USER_ROLE_AUTHORITES_EMPTY = new CodeMsg(-3004, "The role belonging to this user has no available permissions, please contact the administrator!");
	
	//后台菜单管理类错误码
	public static CodeMsg ADMIN_MENU_ADD_ERROR = new CodeMsg(-4000, "Failed to add the menu, please contact the administrator!");
	public static CodeMsg ADMIN_MENU_EDIT_ERROR = new CodeMsg(-4001, "Failed to edit the menu, please contact the administrator!");
	public static CodeMsg ADMIN_MENU_ID_EMPTY = new CodeMsg(-4002, "Menu id can not be null!");
	public static CodeMsg ADMIN_MENU_ID_ERROR = new CodeMsg(-4003, "Menu id error!");
	public static CodeMsg ADMIN_MENU_DELETE_ERROR = new CodeMsg(-4004, "There are sub-menus under this menu, deletion is not allowed!");
	//后台角色管理类错误码
	public static CodeMsg ADMIN_ROLE_ADD_ERROR = new CodeMsg(-5000, "Failed to add the role, please contact the administrator!");
	public static CodeMsg ADMIN_ROLE_NO_EXIST = new CodeMsg(-5001, "The role is not exsist!");
	public static CodeMsg ADMIN_ROLE_EDIT_ERROR = new CodeMsg(-5002, "Failed to edit the role, please contact the administrator!");
	public static CodeMsg ADMIN_ROLE_DELETE_ERROR = new CodeMsg(-5003, "There are user information under this role, deletion is not allowed!");
	//后台用户管理类错误码
	public static CodeMsg ADMIN_USER_ROLE_EMPTY = new CodeMsg(-6000, "Select the role for the user!");
	public static CodeMsg ADMIN_USERNAME_EXIST = new CodeMsg(-6001, "Username already exsist, please try another one!");
	public static CodeMsg ADMIN_USE_ADD_ERROR = new CodeMsg(-6002, "Failed to add the user, please contact the administrator!");
	public static CodeMsg ADMIN_USE_NO_EXIST = new CodeMsg(-6003, "User not exisit!");
	public static CodeMsg ADMIN_USE_EDIT_ERROR = new CodeMsg(-6004, "Failed to edit the user, please contact the administrator!");
	public static CodeMsg ADMIN_USE_DELETE_ERROR = new CodeMsg(-6005, "There are user information under this role, deletion is not allowed!");
	
	//后台用户修改密码类错误码
	public static CodeMsg ADMIN_USER_UPDATE_PWD_ERROR = new CodeMsg(-7000, "Old password wrong!");
	public static CodeMsg ADMIN_USER_UPDATE_PWD_EMPTY = new CodeMsg(-7001, "New password can not be null!");
	
	//后台用户修改密码类错误码
	public static CodeMsg ADMIN_DATABASE_BACKUP_NO_EXIST = new CodeMsg(-8000, "Backup record does not exisit!");
}
