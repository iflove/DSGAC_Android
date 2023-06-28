package com.anroidz.dsgac

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.anroidz.dsgac.base.VbBaseFragment
import com.anroidz.dsgac.databinding.FragmentFeedbackBinding

/**
 * 作用描述: 组件描述
 * 组件描述: #基础组件 #组件名 （子组件）
 * 组件版本: v1
 * 创建人 rentl
 * 创建日期 2023/6/27 20:48
 * 修改日期 2023/6/27 20:48
 * 版权 pub
 */
class FeedbackFragment : VbBaseFragment<FragmentFeedbackBinding>() {
    companion object{
        private const val TAG = "FeedbackFragment"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}