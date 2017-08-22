package util;

public class GlobalMessage { 
	
	public static final String STAFF_LOGIN_FAILED = "用户名、密码不正确，请重新登录！";
	
	public static final String STAFF_NAME_EXISTS = "用户名已经被占用！";

	public static final String STAFF_OPERATION_FORBIDDEN = "请登录后再进行这个操作！[STAFF]";

	public static final String FILE_TOO_LARGE = "您上传的文件太大了，请不要超过100K";

	public static final String FILE_TYPE_INVALID = "您上传的文件类型不对，请上传图片！";

	public static final String REMOVE_PROJECT_FAILED = "删除团队失败，请先删除团队中的成员！";

	public static final String PROJECT_EMPTY = "团队中没有成员，不能创建，请继续完善！";

	public static final String PROJECT_ORDER_DUPLICAT = "团队序号重复！请重新输入！";

	public static final String REMOVE_AUTH_FAILED = "删除权限失败！请解除关联关系后重试！";

	public static final String REMOVE_ROLE_FAILED = "删除角色失败！请解除关联关系后重试！";

	public static final String HAS_NO_AUTHORITY = "抱歉，您没有权限执行这个操作！";

}
