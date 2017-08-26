package com.yunpan.manager.bean;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @ClassName: ResponeTableData
 * @Description: jquery lighter ui 所需要的返回数据
 * @author duanjigui
 * @date 2017年2月22日 上午9:20:49
 *
 * @param <T> 返回表格数据的类型
 */
public class ResponeTableData<T> implements Serializable{
	private static final long serialVersionUID = -4371316186668210379L;
	@JsonProperty(value="Rows")
	private List<T> Rows;//表格数据行
	@JsonProperty(value="Total")
	private int Total;//数据总数
	@JsonIgnore
	public List<T> getRows() {
		return Rows;
	}
	@JsonIgnore
	public void setRows(List<T> rows) {
		Rows = rows;
	}
	@JsonIgnore
	public int getTotal() {
		return Total;
	}
	@JsonIgnore
	public void setTotal(int total) {
		Total = total;
	}
	@Override
	public String toString() {
		return "ResponeTableData [Rows=" + Rows + ", Total=" + Total + "]";
	}
	
	
}
