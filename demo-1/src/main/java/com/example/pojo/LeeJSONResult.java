package com.example.pojo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.List;
import java.io.DataInput;

import org.apache.logging.log4j.message.MapMessage;

import com.fasterxml.jackson.databind.JsonNode;
/**
    * @ClassName: LeeJSONResult
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author lynn
    * @date 2018年12月30日
    *
    */

public class LeeJSONResult {
	//响应业务状态
	private Integer status;
	//响应消息
	private String msg;
	//响应数据
	private Object data;
	//不使用
	private String ok;
	//定义JACKSON对象
	private static final ObjectMapper MAPPER = new ObjectMapper();

	public LeeJSONResult() {}
	
	public LeeJSONResult(Integer status, String msg, Object data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
	public LeeJSONResult(Object data) {
		this.data = data;
	}
	public LeeJSONResult(String msg){
		this.msg = msg;
	}
	public static LeeJSONResult build(Integer status,String msg,Object data) {
		return new LeeJSONResult(status,msg,data);
	}
	public static LeeJSONResult ok(Object data) {
		return new LeeJSONResult(data);
	}
	public static LeeJSONResult ok(){
		return new LeeJSONResult();
	}
	public static LeeJSONResult errorMsg(String msg){
		return new LeeJSONResult(500,msg,null);
	}
	
	public static LeeJSONResult errorTokenMsg(String msg){
		return new LeeJSONResult(502,msg,null);
	}
	
	public static LeeJSONResult errorMap(Object data){
		return new LeeJSONResult(501,"error",data);
	}
	
	public static LeeJSONResult errorException(String msg) {
		return new LeeJSONResult(555,msg,null);
		
	}
	
	public boolean isOk() {
		return this.status == 200;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
		
	
	
	    /**
	    * @Title: formatToPoJo
	    * @Description: 将json结果集转换为LeeJsonResult对象
	    * @param @param jsonData
	    * @param @param clazz
	    * @param @return    参数
	    * @return LeeJSONResult    返回类型
	    * @throws
	    */
	    
	public static LeeJSONResult formatToPoJo(String jsonData ,Class<?> clazz) {
		try {
			if (clazz == null) {
				return MAPPER.readValue(jsonData,LeeJSONResult.class);
			}
			JsonNode jsonNode = MAPPER.readTree(jsonData);
			JsonNode data = jsonNode.get("data");
			Object obj = null;
			if (clazz != null) {
				if (data.isObject()) {
					obj = MAPPER.readValue(data.traverse(),clazz);
				}else if (data.isTextual()) {
					obj = MAPPER.readValue(data.asText(), clazz);
				}
			}
			return build(jsonNode.get("status").intValue(),jsonNode.get("msg").asText(),obj);
		} catch (Exception e) {		
			return null;
		}	
	}
	
	
	    /**
	    * @Title: format
	    * @Description: 没有Object的转化
	    * @param @param json
	    * @param @return    参数
	    * @return LeeJSONResult    返回类型
	    * @throws
	    */
	    
	public static LeeJSONResult format (String json) {
		try {
			return MAPPER.readValue(json, LeeJSONResult.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return  null;
	}
	
	
	    /**
	    * @Title: formatToList
	    * @Description: List对象集合转化
	    * @param @param jsonData
	    * @param @param clazz
	    * @param @return    参数
	    * @return LeeJSONResult    返回类型
	    * @throws
	    */
	    
	public static LeeJSONResult formatToList(String jsonData,Class<?> clazz) {
		try {
			JsonNode jsonNode = MAPPER.readTree(jsonData);
			JsonNode data = jsonNode.get("data");
			Object obj = null;
			if (data.isArray() && data.size()>0) {
				obj = MAPPER.readValue(data.traverse(),
						   MAPPER.getTypeFactory().constructCollectionLikeType(List.class, clazz));
			}
			return build(jsonNode.get("status").intValue(),jsonNode.get("msg").asText(),obj);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	public String getOk() {
		return ok;
	}
	
	public void setOk(String ok) {
		this.ok = ok;
	}
}
