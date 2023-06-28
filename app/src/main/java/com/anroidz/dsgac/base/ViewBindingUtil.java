package com.anroidz.dsgac.base;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewbinding.ViewBinding;



import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 作用描述: ViewBinding反射工具
 * 组件描述: #基础组件 #UI组件 （ViewBinding）
 * 创建人 rentl
 * 创建日期 2022/10/9
 * 修改日期 2022/10/9
 * 版权
 */
public class ViewBindingUtil {
    public static final String TAG = "ViewBindingUtil";

    public static <VB extends ViewBinding> VB create(Class<?> clazz, LayoutInflater inflater) {
        return create(clazz, inflater, null);
    }

    public static <VB extends ViewBinding> VB create(Class<?> clazz, LayoutInflater inflater, ViewGroup root) {
        return create(clazz, inflater, root, false);
    }

    @SuppressWarnings("unchecked")
    public static <VB extends ViewBinding> VB create(Class<?> clazz, LayoutInflater inflater, ViewGroup root, boolean attachToRoot) {
        Class<?> bindingClass = getBindingClass(clazz);
        VB binding = null;
        if (bindingClass != null) {
            try {
                Method method = bindingClass.getMethod("inflate", LayoutInflater.class, ViewGroup.class, boolean.class);
                binding = (VB) method.invoke(null, inflater, root, attachToRoot);
            } catch (InvocationTargetException e) {
                Log.e(TAG, "create err! maybe layoutXml inflater: ", e);
            } catch (Exception e) {
                Log.e(TAG, "create err: ", e);
            }
        }

        DebugRunner.run(() -> {
            if (bindingClass == null) {
                Log.d(TAG, "create fail");
            }
        });
        return binding;
    }

    private static Class<?> getBindingClass(Class<?> clazz) {
        if (clazz == null) {
            return null;
        }
        Class<?> superclass = clazz.getSuperclass(); //父类
        Type genericSuperclass = clazz.getGenericSuperclass(); //泛型父类
        while (superclass != null) {
            if (genericSuperclass instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
                Type[] types = parameterizedType.getActualTypeArguments();
                if (types == null || types.length == 0) {
                    return null;
                }
                Class<?> bindingClass = null;
                for (Type type : types) {
                    if (type instanceof Class<?>) {
                        Class<?> temp = (Class<?>) type;
                        if (ViewBinding.class.isAssignableFrom(temp)) {
                            bindingClass = temp;
                            Log.d(TAG, "getBindingClass: " + temp.getName());
                            // RTL: 2021/11/29 暂时用于Fragment 泛型参数只有one
                        }
                    }
                }
                if (bindingClass != null) {
                    return bindingClass;
                }
            }
            //找不到当前的类的泛型参数, 继续找上一个父类
            genericSuperclass = superclass.getGenericSuperclass();
            superclass = superclass.getSuperclass(); //父类
        }
        DebugRunner.run(() -> Log.w(TAG, "getBindingClass fail clazz: " + clazz.getCanonicalName()));
        return null;
    }


    @SuppressWarnings("unchecked")
    public static <VB extends ViewBinding> VB bind(Class<? extends ViewBinding> bindingClass, View view) {
        VB binding = null;

        if (bindingClass != null) {
            try {
                Method method = bindingClass.getMethod("bind", View.class);
                binding = (VB) method.invoke(null, view);
            } catch (InvocationTargetException e) {
                Log.e(TAG, "bind err! maybe layoutXml inflater: ", e);
            } catch (Exception e) {
                Log.e(TAG, "bind err: ", e);
            }
        }
        DebugRunner.run(() -> {
            if (bindingClass == null) {
                Log.d(TAG, "bind fail");
            }
        });
        return binding;
    }


}
