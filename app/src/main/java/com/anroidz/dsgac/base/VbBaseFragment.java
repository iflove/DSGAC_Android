package com.anroidz.dsgac.base;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;


/**
 * 作用描述: ViewBinding基本碎片
 * 组件描述:
 * 创建人 rentl
 * 创建日期 2022/10/9
 * 修改日期 2022/10/9
 * 版权 zhd
 */
public class VbBaseFragment<VB extends ViewBinding> extends Fragment {
    private static final String TAG = "VbBaseFragment";

    protected VB mViewBinding;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        long constTime = SystemClock.elapsedRealtime();

        mViewBinding = onCreateViewBinding(inflater, container, savedInstanceState);
        DebugRunner.run(() -> {
            //调试各种设备反射ViewBinding的性能,包括加载Layout
            Log.d(ViewBindingUtil.TAG, String.format("onCreateViewBinding const: %s, frag=%s", SystemClock.elapsedRealtime() - constTime, this.getClass().getSimpleName()));
        });
        if (mViewBinding != null) {
            return mViewBinding.getRoot();
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * onCreateView 生命周期绑定所需的 ViewBinding
     * 可以自由覆写,默认通过泛型反射初始化
     *
     * @param inflater           LayoutInflater
     * @param container          ViewGroup
     * @param savedInstanceState Bundle
     * @return a ViewBinding
     */
    @Nullable
    public VB onCreateViewBinding(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //noinspection rawtypes
        Class<? extends VbBaseFragment> clazz = this.getClass();
        if (clazz != VbBaseFragment.class) {
            return ViewBindingUtil.create(clazz, inflater, container, false);
        }
        return null;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    protected boolean isPreventClickFragmentThrough() {
        return true;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (isPreventClickFragmentThrough() && !view.isClickable()) {
            //fragment 层叠防止穿透
            view.setClickable(true);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * onCreateView==>onDestroyView
     *
     * @return ViewBinding
     */
    @Nullable
    public VB getViewBinding() {
        return mViewBinding;
    }

}
