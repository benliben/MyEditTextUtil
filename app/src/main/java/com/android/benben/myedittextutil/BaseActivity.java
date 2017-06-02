package com.android.benben.myedittextutil;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.android.benben.myedittextutil.utils.KetBoardUtils;

/**
 * Time      2017/6/2 16:09 .
 * Author   : LiYuanXiong.
 * Content  :
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    /**
     * 清除editText的焦点
     * @param v 焦点所在的View
     * @param ids 输入框
     */
    public void clearViewFocus(View v, int... ids) {
        if (null != v && null != ids && ids.length > 0) {
            for (int id : ids) {
                if (v.getId() == id) {
                    v.clearFocus();
                    break;
                }
            }
        }
    }


    /**
     * 隐藏键盘
     * @param v 焦点所在的view
     * @param ids 输入框
     * @return true代表焦点在edit上
     */
    public  boolean isFocusEditText(View v,int... ids){
        if (v instanceof EditText) {
            EditText tmp_et = (EditText) v;
            for (int id : ids) {
                if (tmp_et.getId() == id) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isTouchView(View[] views, MotionEvent ev){
        if (views==null|| views.length==0) return false;
        int[] location = new int[2];
        for (View view : views) {
            view.getLocationOnScreen(location);
            int x = location[0];
            int y = location[1];
            if (ev.getX() > x && ev.getX() < (x + view.getWidth()) && ev.getY() > y && ev.getY() < (y + view.getHeight())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (isTouchView(filterViewByIds(),ev)) return super.dispatchTouchEvent(ev);
            if (hideSoftByEditViewIds()==null||hideSoftByEditViewIds().length==0)
                return super.dispatchTouchEvent(ev);
            View v = getCurrentFocus();
            if (isFocusEditText(v,hideSoftByEditViewIds())){
                /*隐藏键盘*/
                KetBoardUtils.hideInputForce(this);
                clearViewFocus(v,hideSoftByEditViewIds());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 传入EditText的Id
     * 没有传入的EditText不做处理
     * @return id数组
     */
    public int[] hideSoftByEditViewIds(){
        return null;
    }


    /**
     * 传入要过滤的view
     * 过滤之后点击将不会有隐藏软件的操作
     * @return
     */
    public View[] filterViewByIds(){
        return null;
    }
}
