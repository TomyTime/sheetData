package com.canteen.common.utils;

public class Constants {

	public static int DEFAULT_PAGE_SIZE = 10;
	
	public static int DEFAULT_JG = 6;//监控间隔（小时）
	
	//有效标记
	public static String IS_MARKUP_YES="Y";//有效
	public static String IS_MARKUP_NO="N";//无效

	// 是否警情
	public static String IS_WAMING_YES = "1";// 是警情

	public static String IS_WAMING_NO = "0";// 不是警情

	// 警情状态
	public static String WAMING_STATE_WCL = "0";// 未处理
	public static String WAMING_STATE_YHF = "1";// 已恢复
	public static String WAMING_STATE_YCL = "2";// 已处理

	
	// 监控对象类型
	public static String MONITOR_TYPE_CJRW = "0";// 采集任务
	public static String MONITOR_TYPE_GXFW = "1";// 共享服务
	public static String MONITOR_TYPE_FWQ = "2";// 服务器资源
	
	// 监控对象类型 名称
	public static String MONITOR_NAME_CJRW = "采集任务";// 采集任务
	public static String MONITOR_NAME_GXFW = "共享服务";// 共享服务
	public static String MONITOR_NAME_FWQ = "服务器资源";// 服务器资源
	
	// 采集返回码定义 ,同步数据交换
	/** 采集数据成功 */
	public static final String CLIENT_FHDM_SUCCESS = "BAIC0000"; // 采集数据成功

	/** 系统错误 */
	public static final String CLIENT_FHDM_SYS_ERROR = "CODE0001"; // 系统错误

	/** 执行SQL语句错误 */
	public static final String CLIENT_FHDM_SQL_ERROR = "CODE0002"; // 执行SQL语句错误

	/** 采集表错误 */
	public static final String CLIENT_FHDM_COL_TBL_ERROR = "CODE0003"; // 采集表错误

	/** 采集数据项错误 */
	public static final String CLIENT_FHDM_DATAITEM_ERROR = "CODE0004"; // 采集数据项错误

	/** 采集任务错误 */
	public static final String CLIENT_FHDM_TASK_ERROR = "CODE0005"; // 采集任务错误

	/** webService服务错误 */
	public static final String CLIENT_FHDM_WS_ERROR = "CODE0006"; // webService服务错误

	/** webService服务URL错误 */
	public static final String CLIENT_FHDM_WS_URL_ERROR = "CODE0007"; // webService服务URL错误

	/** 调用服务错误 */
	public static final String CLIENT_FHDM_INVOKE_ERROR = "CODE0008"; // 调用服务错误

	/** 将数据转化成dom格式错误 */
	public static final String CLIENT_FHDM_TODOM_ERROR = "CODE0009"; // 将数据转化成dom格式错误
	
	
	// 服务返回代码声明
		/** 超过最大记录数限制 */
		public static final String	SERVICE_FHDM_OVER_MAX				= "BAIC0020";

		/** 成功返回 */
		public static final String	SERVICE_FHDM_SUCCESS				= "BAIC0000";

		/** 无符合条件的记录 */
		public static final String	SERVICE_FHDM_NO_RESULT				= "BAIC0010";

		/** 用户提供的参数错误 */
		public static final String	SERVICE_FHDM_INPUT_PARAM_ERROR		= "BAIC0030";

		/** 超过最大日期查询限制 */
		public static final String	SERVICE_FHDM_OVER_DATE_RANGE		= "BAIC0040";

		/** 系统错误 */
		public static final String	SERVICE_FHDM_SYSTEM_ERROR			= "BAIC0050";

		/** 连接测试返回代码 */
		public static final String	SERVICE_FHDM_LJ_QUERY				= "BAIC0060";

		/** 登录失败 */
		public static final String	SERVICE_FHDM_LOGIN_FAIL				= "BAIC0070";

		/** 用户名检查失败 */
		public static final String	SERVICE_FHDM_USER_ERROR				= "BAIC0071";

		/** 密码检查失败 */
		public static final String	SERVICE_FHDM_PWD_ERROR				= "BAIC0072";

		/** 未知错误 */
		public static final String	SERVICE_FHDM_UNKNOWN_ERROR			= "BAIC9999";

		/** 服务已暂停 */
		public static final String	SERVICE_FHDM_SERVICE_PAUSE			= "BAIC0200";

		/** 用户IP错误 */
		public static final String	SERVICE_FHDM_ERROR_IP				= "BAIC0101";

		/** 用户某个服务的访问权限信息错误 */
		public static final String	SERVICE_FHDM_ERROR_LIMIT			= "BAIC0100";

		/** 当日用户某个服务访问受限 */
		public static final String	SERVICE_FHDM_LOCK_WEEK				= "BAIC0102";

		/** 当日用户某个服务访问时间受限 */
		public static final String	SERVICE_FHDM_LOCK_TIME				= "BAIC0103";

		/** 当日用户某个服务访问次数受限 */
		public static final String	SERVICE_FHDM_LOCK_NUMBER			= "BAIC0104";

		/** 当日用户某个服务访问数据总条数受限 */
		public static final String	SERVICE_FHDM_LOCK_TOTAL				= "BAIC0105";

		/** 当日用户某个服务访问已加锁 */
		public static final String	SERVICE_FHDM_LOCKED_TODAY			= "BAIC0109";

		/** 服务未找到 */
		public static final String	SERVICE_FHDM_SERVICE_NOT_FOUND		= "BAIC0110";

		/** 单项检查通过 */
		public static final String	SERVICE_FHDM_SERVICE_PASS			= "BAIC0111";
		
		/** 查询SQL错误 */
		public static final String	SERVICE_FHDM_SQL_ERROR			= "BAIC2020";
		
		/** 正常返回数据 */
		public static final String TAX_FHDM_NORMAL = "TAX0000";	
		/** 没有查询结果返回 */
		public static final String TAX_FHDM_NO_RESULT = "TAX0010";	
		/** 查询结果超出限定范围（最多2000条） */
		public static final String TAX_FHDM_MORE_DATA = "TAX0020";	
		/** 入口参数错误 */
		public static final String TAX_FHDM_WRONG_PARAM = "TAX0030";	
		/** 查询日期时间段过长（最长7天） */
		public static final String TAX_FHDM_LONG_DATE = "TAX0040";	
		/** 系统错误 */
		public static final String TAX_FHDM_SYSTEM_WRONG = "TAX0050";	
		/** 连接成功 */
		public static final String TAX_FHDM_SUCCESS = "TAX0060";	
		/** 未知错误 */
		public static final String TAX_FHDM_UNKNOW = "TAX9999";	
}
