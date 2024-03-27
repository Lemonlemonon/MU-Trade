package com.fyp.mutrade.bean;
/**
 * Result class for AJAX submissions
 * @author Administrator
 *
 */
public class Result<T> {
	
	private int code;//error code
	
	@Override
	public String toString() {
		return "Result{" +
				"code=" + code +
				", msg='" + msg + '\'' +
				", data=" + data +
				'}';
	}
	
	private String msg;//msg return
	
	private T data;//define return data
	
	private Result(){}//Private constructor, restrict object creation
	
	/**
	 * Define a private constructor that takes codemsg as a parameter
	 * @param codeMsg
	 */
	private Result(CodeMsg codeMsg){
		if(codeMsg != null){
			this.code = codeMsg.getCode();
			this.msg = codeMsg.getMsg();
		}
	}
	
	/**
	 * Define a private constructor that takes codemsg and date as parameters
	 * @param data
	 * @param codeMsg
	 * @return
	 */
	private Result(T data,CodeMsg codeMsg){
		if(codeMsg != null){
			this.code = codeMsg.getCode();
			this.msg = codeMsg.getMsg();
		}
		this.data = data;
	} 
	
	/**
	 * Define a unified success return function
	 * @param data
	 * @return
	 */
	public static <T>Result<T> success(T data){
		return new Result<T>(data,CodeMsg.SUCCESS);
	}

	/**
	 * Error returning method. All errors call this method.
	 * @param codeMsg
	 * @return
	 */
	public static <T>Result<T> error(CodeMsg codeMsg){
		return new Result<T>(codeMsg);
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

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	
}
