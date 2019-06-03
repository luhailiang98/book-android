package top.luhailiang.mylibrary.utils;

/**
 * 请求接口动态配置
 */
public class CONTENST {

    /**
     * 接口服务器地址
     */
    public static final String HOST = "http://library.luhailiang.top";

    /**
     * 用户登录接口[1](POST)
     */
    public static final String USER_LOGIN = HOST + "/api/user/login";

    /**
     * 获取图书信息接口[2](GET)
     * /api/books  获取所有图书信息
     * /api/books/{bookId} 获取单本图书信息(拼接图书编号)
     */
    public static final String BOOK_LIST = HOST + "/api/books";

    /**
     * 获取用户借阅记录[1](GET)
     * /api/lendReturnRecords/{userId} 获取用户借阅记录(拼接用户编号)
     */
    public static final String LEND_RETURN_RECORD = HOST + "/api/lendReturnRecords";
}
