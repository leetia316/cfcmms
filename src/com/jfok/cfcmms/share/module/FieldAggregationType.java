package com.jfok.cfcmms.share.module;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 字段的聚合属性
 * 
 * @author jiangfeng
 * 
 */
public enum FieldAggregationType {

	NORMAL("normal"), COUNT("count"), SUM("sum"), AVG("avg"), MAX("max"), MIN("min"),

	VAR("var"), // 方差
	VARP("varp"), // 总体方差
	STDEV("stdev"), // 标准偏差
	STDEVP("stdevp"), // 总体标准偏差

	ADDITIONCOUNT("additioncount");

	private String value;

	public static final Map<FieldAggregationType, String> AGGREGATION = genMapInfo();

	FieldAggregationType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	private static Map<FieldAggregationType, String> genMapInfo() {
		Map<FieldAggregationType, String> result = new LinkedHashMap<FieldAggregationType, String>();

		result.put(COUNT, "计数");
		result.put(SUM, "求和");
		result.put(AVG, "平均值");
		result.put(MAX, "最大值");
		result.put(MIN, "最小值");
		result.put(VAR, "方差");
		result.put(VARP, "总体方差");
		result.put(STDEV, "标准偏差");
		result.put(STDEVP, "总体标准偏差");

		return result;
	}

}

/**
 * --求个数 select count(*) as 行数from [tb]
 * 
 * --求总和 select sum(num) as 总和from [tb]
 * 
 * --求最大值 select max(num) as 最大值from [tb]
 * 
 * --求最小值 select min(num) as 最小值from [tb
 * 
 * --求平均值 select avg(num) as 平均值from [tb]
 * 
 * --求方差 select var(num) as 方差from [tb]
 * 
 * --求总体方差 select varp(num) as 总体方差from [tb]
 * 
 * --求标准偏差 select stdev(num) as 标准偏差from [tb]
 * 
 * --求总体标准偏差 select stdevp(num) as 总体标准偏差from [tb]
 */
