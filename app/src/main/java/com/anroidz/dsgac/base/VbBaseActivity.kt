package com.anroidz.dsgac.base;

import android.os.Bundle
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding


/**
 * 作用描述: ViewBinding基本活动
 * 组件描述:
 * 创建人 rentl
 * 创建日期 2022/11/23
 * 修改日期 2022/11/23
 * 版权 zhd
 */
open class VbBaseActivity<VB : ViewBinding> : AppCompatActivity() {
    companion object {
        private const val TAG = "VbBaseActivity"
    }

    protected lateinit var mViewBinding: VB


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val clazz: Class<out VbBaseActivity<*>?> = this.javaClass
        if (clazz != VbBaseActivity::class.java) {
            onCreateViewBindingBefore(this)
            val vb = ViewBindingUtil.create<VB>(clazz, layoutInflater, null, false)
            if (vb != null) {
                mViewBinding = vb
                setContentView(mViewBinding.root)
            }
        }
        //hideNav()
    }

    /**
     * 全屏显示
     * 隐藏navigation bar
     */
    private fun hideNav() {
        val window = window
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LOW_PROFILE
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }

    override fun setContentView(view: View?) {
        super.setContentView(view)
    }

    /**
     * 在创建视图绑定之前
     */
    open fun onCreateViewBindingBefore(vbBaseActivity: VbBaseActivity<VB>) {

    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN) {
            //点击外区域隐藏键盘
            val v = currentFocus
            if (isShouldHideKeyboard(v, ev)) {
                //KeyboardUtils.hideSoftInput(this)
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    // Return whether touch the view.
    private fun isShouldHideKeyboard(v: View?, event: MotionEvent): Boolean {
        if (v is EditText) {
            val l = intArrayOf(0, 0)
            v.getLocationOnScreen(l)
            val left = l[0]
            val top = l[1]
            val bottom = top + v.height
            val right = left + v.width
            return !(event.rawX > left && event.rawX < right
                    && event.rawY > top && event.rawY < bottom)
        }
        return false
    }

    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        //log.d(TAG, "dispatchKeyEvent: " + event.keyCode)
        return super.dispatchKeyEvent(event)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}