package com.jfok.cfcmms.util.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface FieldDefine {

	/**
	 * 字段的中文描述内容
	 */
	String title();

	/**
	 * 字段的顺序号，在grid中显示字段列表的时候默认按照顺序号排序，生成模块的form和grid字段的时候也是按顺序号来确定顺序
	 */
	int number() default 0;

	/**
	 * 字段的备注，可以显示在form中字段的label的tooltip上
	 */
	String remark() default "";

	/**
	 * 是否是此模块的名称字段。名称字段即可以用此名称来描述此条记录的字段，例如一个合同的合同名称，或一份订单的订单号，
	 * 在对记录作某些操作的时候的堤示信息中会加入这个字段的信息，在form窗口中，会将名称字段的内容显示在title处；
	 * 在grid中会将选中的记录的名称字段内容显示在title中。
	 */
	boolean nameField() default false;

	/**
	 * 是否是隐藏字段。如果是的话，在form中会创建一个hiddenfield,在grid和查询的时候都不会加入此字段。
	 */
	boolean hidden() default false;

	/**
	 * 字段的分组。在生成默认的form和grid的时候，会根据fieldGroup来进行分组。
	 */
	String fieldGroup() default "默认组";

	/**
	 * 如果定义了，将会在sql中使用相应的表达式(某些数据库不能使用计算字段，只能用这个代替了)
	 */
	String formula() default ""; // 如果是自定义的字段，将sql中能用的表达式写于此处

	/**
	 * 此字段是否可以汇总，数值字段和有分子分母的除法的字段可以设置
	 * 
	 * @return
	 */
	boolean allowSummary() default false;

	/**
	 * 可以进行分组
	 * 
	 * @return
	 */
	boolean allowGroup() default false;

	/**
	 * 计量单位
	 * 
	 * @return
	 */
	String unitText() default "";

	/**
	 * 对字段值设置的一个tooltip的tpl表达式。例如 合同名称{agreementname},签订于{signDate:}
	 * 
	 * @return
	 */
	String tooltipTpl() default "";

	/**
	 * 如果是一个比率值，那么需要分子和分母，这样在分类汇总和总计的时候，分子分母分别相加再除，即为加权平均
	 */

	String divisor() default "";

	String denominator() default "";

	/**
	 * 是否可以用个，千，万，百万，亿的数值单位来显示在grid中，在form中显示原值
	 * 
	 * @return
	 */
	boolean isMonetary() default false;

	int maxValue() default 0;
	int minValue() default 0;
	
	
	/**
	 * 一些附加设置，如
	 * formfield:{comboThisField:true},
	 * field : {searchCondOrder :41 }
	 * @return
	 */
	String otherSetting() default "";
	
}
