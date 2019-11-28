package com.webside.util;

import java.io.Serializable;

/**
 * 用于封装AJAX调用以后的JSON返回值
 * 其中正确返回值:
 *  {state:0, data:返回数据, message:错误消息}
 * 错误返回值:
 *  {state:1, data:null, message:错误消息}
 */
public class JsonResult implements Serializable{
    private static final long serialVersionUID = -3644950655568598241L;
 
    public static final boolean SUCCESS=true;
    public static final boolean ERROR=false;
 
    /** 
     * 返回是否成功的状态, 0表示成功, 
     * 1或其他值 表示失败
     */
    private boolean success;
    /**
     * 成功时候,返回的JSON数据
     */
    private Object data;
    /**
     * 是错误时候的错误消息
     */
    private String message;
 
 
    public JsonResult() {
    }
    


	public JsonResult(Boolean success, Object data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }
 
    public JsonResult(Throwable e){
    	success = ERROR;
        data =null;
        message =e.getMessage();
    }
 
    public JsonResult(Object data){
    	success = SUCCESS;
        this.data=data;
        message="";
    }




	public boolean isSuccess() {
		return success;
	}



	public void setSuccess(boolean success) {
		this.success = success;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public Object getData() {
        return data;
    }
 
 
    public void setData(Object data) {
        this.data = data;
    }
 
 
 
 
    @Override
    public String toString() {
        return "JsonResult [success=" + success + ", data=" + data + ", message=" + message + "]";
    }
 
}