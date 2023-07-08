package com.anroidz.dsgac

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import com.anroidz.dsgac.adpter.ViewPagerAdapter
import com.anroidz.dsgac.base.ResUtil
import com.anroidz.dsgac.base.VbBaseActivity
import com.anroidz.dsgac.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


//主页
class MainActivity : VbBaseActivity<ActivityMainBinding>() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private var tabIcons = intArrayOf(
        R.drawable.tab_home_icon_selector,
        R.drawable.tab_feedback_icon_selector // 添加更多的图标资源
    )

    private val tabsText = ResUtil.getStringArray(R.array.tabs)

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        setContentView(mViewBinding.root)
        mViewBinding.toolbar.setTitle(R.string.logo_app_name)
        setSupportActionBar(mViewBinding.toolbar)

        val fragments: MutableList<Fragment> = ArrayList()
        fragments.add(HomeFragment())
        fragments.add(FeedbackFragment())

        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle, fragments)
        mViewBinding.container.pager.adapter = adapter

        TabLayoutMediator(mViewBinding.container.tabLayout, mViewBinding.container.pager) { tab: TabLayout.Tab, position: Int ->
            // 设置选项卡标题
            tab.text = tabsText[position]

            // 设置选项卡图标
            tab.setIcon(tabIcons[position])
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}