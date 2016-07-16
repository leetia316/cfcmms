package com.jfok.cfcmms.util.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
/**
 * 定义在模块bean类上的标注，用来描述该模块的各种属性。
 * 
 * (在该bean属性导入到数据库以后，标注的信息将会失效,最终的各种属性值以数据库中的为准)
 * 
 * 系统中存放所有模块信息的表为 _Module(所有以_开头的表都是系统表，请业务表不要加此下划线)
 * 
 * @author jiangfeng
 *
 */
public @interface TableDefine {

	/**
	 * 
	 * 模块的唯一标识符，默认是该类的类名，在将模块属性导入到数据库中时自动使用类名。
	 * 
	 * (整个系统中不允许有相同的hibernate bean类名，哪怕是在不同的package中也不能有相同的类名。
	 * 
	 * moduleName
	 * 
	 */

	/**
	 * 模块的id号,为一个6位数字,用来代表一个唯一的模块,在生成查询语句的时候，用 _t{id} 来表示此表的别名
	 * 
	 * @return
	 */
	int id();

	/**
	 * 如果在数据库中使用的表或视图和此bean的名称不一致，需要设置tableName ，在生成sql语句的时候，会用此
	 * tableName的值来作为table名。
	 * 
	 * 如果一致，则不用设置此字段。
	 * 
	 * @return
	 */
	String tableName() default "";

	/**
	 * 此模块的具体名称描述，用来显示在window，panel中的title部分
	 * 
	 * @return
	 */
	String title();

	/**
	 * 此模块的中文简称。在有些地方，比如说模块的按钮，tab中显示模块名称的时候，可以使用简称来减少字符长度
	 * 
	 * @return
	 */
	String shortName() default "";

	/**
	 * 模块的英文简称，一般为几个字母，如果该模块有编码字段，可以以这几个英文字母开头加上时间序号自动生成一个编码
	 * 
	 * @return
	 */
	String englishName() default "";

	/**
	 * 模块分组编码，如果指定了分组编码和分组名称，那么如果没找到该分组编码，则自动创建一个分组编码
	 * 
	 * @return
	 */
	String groupId() default "";
	
	/**
	 * 模块分组名称。模块属于哪个分组，在导入模块时如果没有找到该分组，会在分组中自动加入当前的分组名称， 需要人工去修改分组的编号
	 * 
	 * @return
	 */
	String group();

	/**
	 * 模块的主键定义，自动从 bean 中获取。主键可以是整型，字符型，也可以增量型、UUID等类型。
	 * 
	 * 注意：主键不可以是复合主键。如果有复合主键也必须加一个独立的主键，把复合主键作为唯一索引。这是不可更改的设置。
	 * 对于某些查询量如果没有主键的话，也应该想办法生成一个只有一个字段的唯一的标识
	 * 
	 * @return
	 * 
	 *         String primaryKey() default ""
	 */

	/**
	 * 模块的显示标志字段，自动从 bean 中获取。该字段的值可以用来描述该记录。主要用在删除时会显示此字段的值来确认删除。
	 * 
	 * 有时候会有模块没有此字段，或者一个字段不能完全描述该记录，这时可以设置titleTpl的值。
	 * 
	 * @return
	 * 
	 *         String namefields() default ""
	 */

	/**
	 * 当一个字段不能描述该记录时，使用titleTpl来进行描述。
	 * 
	 * tpl的格式如 ‘{year}年的{name}, 这样在删除记录的时候会显示“2005年的某某值”
	 * 
	 * @return
	 */
	String titleTpl() default "";

	/**
	 * 该模块的编码字段，例如对于合同来说就是：合同编号这个字段。这个号码不是primaryKey
	 * 
	 * 在新增一个新记录的时候，如果有编码字段，那么还可以设置编码的生成方式，可以设置若干种生成方式，
	 * 选择一个自动生成，这个功能现在还未完成，现在是在业务逻辑里面来放入新增时候的默认编码的
	 * 
	 * @return
	 */
	String codeField() default "";

	/**
	 * 此模块中的记录的顺序号的字段，在对于顺序敏感的模块中需要使用，比如说菜单的顺序号用来排列各个顺序位置。
	 * 
	 * 主要用途：用来排列记录的顺序，可以在程序中使用拖动功能来改变记录的顺序号
	 * 
	 * 一般的使用中，会在某个父模块的记录的关联下，子模块中的记录按此顺序号排列，并非是表中所有的记录。
	 * 当可以在程序中拖动改变顺序号的时候，必须限定在某父模块的导航之下才可以进行， 不然的话就会乱掉。
	 * 
	 * @return
	 */
	String orderField() default "";

	/**
	 * 如果有orderField(),那么可以设置此字段为某个父模块的moduleName,用来表示在这个模块的导航，
	 * 或者父模块的限定之下才可以在grid中拖动列的位置来修改 orderField的值。
	 * 
	 * 使用拖动修改记录的orderField要小心一些，因为还有一些未知的因素会造成错误，比如说有筛选，或者
	 * 有分页的话，会造成更新后orderField的混乱。
	 * 
	 * @return
	 */
	String orderFieldControlModule() default "";

	/**
	 * 此模块的主要日期字段，比如说合同的签订日期，订单的销售日期等等
	 * 
	 * 这个日期主要用在综合查询中，在当前模块是基准模块的前提下，设定了日期条件后会直接关联到这个日期字段上面
	 * 
	 * @return
	 */
	String dateField() default "";

	/**
	 * 和上面的日期字段功能类似，在综合查询中还未加入联动机制。
	 * 
	 * 一般情况下，如果你有一个年度字段和一个月度字段，那么可以生成一个计算字段设置成dataField即可。
	 * 
	 * @return
	 */
	String yearField() default "";

	String monthField() default "";

	String seasonField() default "";

	/**
	 * 此记录的可以存放文件信息的一个字段，对于模块来说，有专门的附件模块来处理上传的文件附件。
	 * 
	 * 这个功能可以用来做一些简单的文件操作，比如放置操作员的签名照片，可以在打印的时候打印此签名。
	 * 
	 * 有了这个字段以后，会在toolbar中加入相应的操作，来上传、下载、清除此文件字段中的内容。
	 * 
	 * @return
	 */
	String fileField() default "";

	/**
	 * 此模块是否具有附件，如果有附件，那么就会在toolbar中显示附件操作的按钮，在每条记录的前面会显示记录数， 点击记录数的链接会打开附件显示页面。
	 * 
	 * 各个模块的附件的权限有：可浏览，可新增，可修改，可删除。权限是每个模块独立设置的。
	 * 
	 * 模块的附件是集中统一管理的，有专门的表来管理所有的附件。
	 * 
	 * @return
	 */
	boolean attachment() default false; // 模块是否有附件

	/**
	 * 模块的主键是否可以是分级的，如果是可以分级的，可定义为"2,2,2",表示有三级，每级代码长为2位， 比如会计科目可以这样来定义 10 1001
	 * 100101 100102 100102 1002 ......
	 * 
	 * 如果定义为分组的，那么在做为导航模块时，会按照分级规则自动生成树状排列。
	 * 
	 * 如果此模块的记录上有用户的附加权限设置，那么设置1001,表示包括1001和其所有的子结点都有权限,sql语句里是 fieldname like
	 * '1001%'
	 * 
	 * 在新增、修改和删除数据的时候，会自动判断分级的约束。
	 * 
	 * @return
	 */
	String codeLevel() default "";

	/**
	 * 此模块是不是树形结构，如果是的话，必须指定pid字段
	 * 
	 * @return
	 */
	boolean isTreeModel() default false;

	/**
	 * 对于树形结构模块，指定pid字段，即父节点的id值
	 * 
	 * @return
	 */
	String parentKey() default "";

	/**
	 * 默认的排序字段，设置了以会后会在sql语句中加入作为排序字段
	 * 
	 * @return
	 */
	String defaultOrderField() default "";

	/**
	 * 前台关联的模块。由于前台各个模块的数据是不会及时联动刷新，在子模块的数据改动了以后，父模块中可能会有
	 * 子模块的汇总数据，因此设置此属性。里面包含的所有模块，在此模块修改过后，如果已经在tabPanel中打开了，
	 * 在下次转到此父模块的tabPanel时候，会自动刷新数据。
	 * 
	 * 只是刷新所设置的父模块的数据，并不是刷新所有的父模块的数据
	 * 
	 * @return
	 */
	String linkModule() default "";

	/**
	 * 此模块是否可用，如果设置为false,则此模块立即禁用
	 * 
	 * @return
	 */
	boolean isEnable() default true;

	/**
	 * 此模块是否可以显示列表，如果设置为false,则会在菜单中禁用
	 * 
	 * @return
	 */
	boolean hasBrowse() default true;

	boolean hasInsert() default true;

	boolean hasEdit() default true;

	boolean hasDelete() default true;

	/**
	 * 是否可以执行附件的按钮功能，如果可以的话，将当当前用户具有权限的附加功能的按钮加到toolbar中
	 * 
	 * 会有一个专门的模块 _ModuleAdditionFunction来专门管理附加的按钮事件，即用户自定义的按钮。在此系统中除了标准的功能
	 * 之外，用户只能通过附加按钮来加入自己的对模块和记录的附加操作。
	 * 
	 * @return
	 */
	boolean hasExec() default false;

	/**
	 * 是否具有审核功能，内置的一个审核流程，需要在数据表中加入4个字段，并且bean继续于一个审核的基本类即可。
	 * 
	 * 可以设置具有审核的人员的权限，可以批量审核，取消审核。
	 * 
	 * 如果一个用户可以审核此模块，那对于所有的可视记录，该用户具有相同的权限，现在不支持部分可审核，部分不可审核。
	 * 
	 * 具有审核功能的模块的列表的最前面会加一个审核标志的字段，用来显示此模块是否已经审核完成
	 * 
	 * @return
	 */
	boolean hasAuditing() default false;

	/**
	 * 是否具有审批功能。内置的一个简易的审批流程，可以设置最多6级进行审批，会根据审批权限设置来自动判断到哪一级审批了，
	 * 会在主页中显示该用户可以审批的记录数 。需要在数据表中加入近30个字段，并且bean继续于一个审批的基本类即可。
	 * 
	 * 用户可以在下一级未审批的情况下取消审批。并且支持并级审批，比如说刚开始有二个人可以无序审批，第三个人必须在前二个人审批好之后才审批。
	 * 
	 * 具有审批功能的模块的列表的最前面会加一个审批标志的字段，用来显示此模块已经审批完成，或者已经审批了几级了。
	 * 
	 * @return
	 */
	boolean hasApprove() default false;

	/**
	 * 该模块具有支付功能，比如说一张请款单，在审批完成之后，可以进行支付。
	 * 
	 * @return
	 */
	boolean hasPayment() default false;

	/**
	 * 该模块是否具有图表分析
	 * 
	 * @return
	 */
	boolean hasChart() default false;

	/**
	 * 是否是系统模块
	 * 
	 * @return
	 */
	boolean isSystem() default false;

	/**
	 * 是否可以在此模块上设置用户的权限。此权限主要是记录的可视范围权限
	 * 
	 * 专门有用户的附加权限来管理该用户的权限，权限可以设置在所有的模块上，设置好权限后，用户对于该模块和所有该模块的子模块都有相同的可视权限。
	 * 
	 * 比如说有一个模块是省份，如果某个人只能看到华东区的数据，那么就将对应的几个省份选择起来，作为一个权限角色，当给某个用户设置了这个权限角色之后，
	 * 他将只能看到这几个省份，并且所有的省份下的子模块都限定在这几个省之下。
	 * 
	 * 如果你的省份下有二个模块，你一个只要看到部分，另一个要看到全部的话，你必须做一个省份的副本当作一个新的块。比如说省份的名称可以定义为：客户省份，
	 * 采购原材料省份 ，这样你在设置好客户省份的权限后，才不会影响原材料省份数据的可视范围。你的数据库结构还是不用改变，
	 * 只是要新建一个模块，使用一个新的查询来取得数据即可。
	 * 
	 * @return
	 */
	boolean canLimit() default false;

	/**
	 * 是否仅仅用于综合查询，如果设置此值为true,则此模块将不出现在权限设置和菜单中
	 * 
	 * @return
	 */
	boolean isOnlyUsedSearch() default false;

	/**
	 * 此模块没有具体的bean文件，如果是一个视图，没有修改操作，那么确实是可以省略bean文件。
	 * 
	 * 在省略了bean文件以后，该模块的所有信息将要手工在前台自己录入。在纯的综合查询系统中是可以用这样的模式
	 * 
	 * @return
	 */
	boolean isNotBean() default false;

	/**
	 * 允许使用有excel新增插入数据
	 * 
	 * @return
	 */
	boolean allowInsertExcel() default false;

	/**
	 * 允许将数据从excel导出，修改后再导回(尚未完成)
	 * 
	 * @return
	 */
	boolean allowEditExcel() default false;

	/**
	 * 模块的具体描术信息,最长50个字符
	 * 
	 * @return
	 */
	String description() default "";

	/**
	 * 模块的备注信息
	 * 
	 * @return
	 */
	String remark() default "";



}
