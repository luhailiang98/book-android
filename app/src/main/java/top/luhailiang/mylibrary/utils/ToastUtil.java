package top.luhailiang.mylibrary.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {

    public static void showShortToast(Context context, String text) {
        Toast mToast = null;
        if (mToast == null) {
            mToast = Toast.makeText(context, null, Toast.LENGTH_SHORT);
            mToast.setText(text);
        } else {
            //如果当前Toast没有消失， 直接显示内容，不需要重新设置
            mToast.setText(text);
        }
        mToast.show();
    }

}
