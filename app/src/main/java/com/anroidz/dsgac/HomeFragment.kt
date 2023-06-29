package com.anroidz.dsgac

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.anroidz.dsgac.algorithm.AuxiliaryAlgorithm
import com.anroidz.dsgac.algorithm.BPDAlgorithm
import com.anroidz.dsgac.algorithm.CRLAlgorithm
import com.anroidz.dsgac.algorithm.IAlgorithm
import com.anroidz.dsgac.algorithm.ReferenceTableAlgorithm
import com.anroidz.dsgac.algorithm.utils.TimeUtils
import com.anroidz.dsgac.base.ResUtil
import com.anroidz.dsgac.base.VbBaseFragment
import com.anroidz.dsgac.base.isVisible
import com.anroidz.dsgac.bean.ConceiveType
import com.anroidz.dsgac.bean.GestationalAgeType
import com.anroidz.dsgac.databinding.FragmentHomeBinding
import java.util.Calendar


/**
 * 作用描述: 组件描述
 * 组件描述: #基础组件 #组件名 （子组件）
 * 组件版本: v1
 * 创建人 rentl
 * 创建日期 2023/6/27 00:37
 * 修改日期 2023/6/27 00:37
 * 版权 pub
 */
class HomeFragment : VbBaseFragment<FragmentHomeBinding>() {
    companion object {
        private const val TAG = "HomeFragment"
    }

    private var gestationalAgeType: GestationalAgeType = GestationalAgeType.BPD
    private var conceiveType: ConceiveType = ConceiveType.NATURE

    private var alg: IAlgorithm = BPDAlgorithm()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewBinding.apply {
            //受孕方式
            conceiveTypeSp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    when (position) {
                        ConceiveType.NATURE.ordinal -> {
                            conceiveType = ConceiveType.NATURE
                            //显示自然受孕UI项
                            gestationalAgeTypeLl.isVisible = true
                            lenLl.isVisible = true
                            //日期
                            when (gestationalAgeType) {
                                GestationalAgeType.BPD -> {
                                    dateTv.text = ResUtil.getString(R.string.bpd_date)
                                    //切换算法
                                    alg = BPDAlgorithm()
                                }

                                GestationalAgeType.CRL -> {
                                    dateTv.text = ResUtil.getString(R.string.crl_date)
                                    //切换算法
                                    alg = CRLAlgorithm()
                                }
                            }

                        }

                        ConceiveType.AUXILIARY.ordinal -> {
                            //切换算法
                            alg = AuxiliaryAlgorithm()

                            conceiveType = ConceiveType.AUXILIARY
                            //显示自然受孕UI项
                            gestationalAgeTypeLl.isVisible = false
                            lenLl.isVisible = false
                            //日期
                            dateTv.text = ResUtil.getString(R.string.to_implant_date)
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

            }
            //孕周类型
            gestationalAgeTypeSp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    when (position) {
                        GestationalAgeType.BPD.ordinal -> {
                            gestationalAgeType = GestationalAgeType.BPD
                            //变化文本
                            lenTv.text = ResUtil.getString(R.string.bpd_len)
                            dateTv.text = ResUtil.getString(R.string.bpd_date)
                        }

                        GestationalAgeType.CRL.ordinal -> {
                            gestationalAgeType = GestationalAgeType.CRL
                            //变化文本
                            lenTv.text = ResUtil.getString(R.string.crl_len)
                            dateTv.text = ResUtil.getString(R.string.crl_date)
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

            }
            selectDialog.setOnClickListener {
                val cale1: Calendar = Calendar.getInstance()
                val pickerDialog = DatePickerDialog(
                    this@HomeFragment.requireContext(),
                    { view, year, monthOfYear, dayOfMonth ->
                        Toast.makeText(
                            requireContext(),
                            "你选择的是 " + year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日",
                            Toast.LENGTH_SHORT
                        ).show()
                        selectDialog.text = "${year}-${monthOfYear + 1}-${dayOfMonth}"
                        alg.setInspectDate(TimeUtils.createDate(year, monthOfYear + 1, dayOfMonth))
                    }, cale1.get(Calendar.YEAR), cale1.get(Calendar.MONTH), cale1.get(Calendar.DAY_OF_MONTH)
                )

                // 设置最大日期为当前日期
                pickerDialog.datePicker.maxDate = cale1.timeInMillis
                pickerDialog.show()
            }

            calBtn.setOnClickListener {
                //计算业务
                if (selectDialog.text.isEmpty()) {
                    Toast.makeText(
                        requireContext(),
                        "请选择日期",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
                when (alg) {
                    is ReferenceTableAlgorithm -> {
                        if (lenEt.text.toString().isEmpty()) {
                            Toast.makeText(
                                requireContext(),
                                "长度必填",
                                Toast.LENGTH_SHORT
                            ).show()
                            return@setOnClickListener
                        }
                        (alg as ReferenceTableAlgorithm).lenSize = lenEt.text.toString().toInt()
                        try {
                            val curGestationalWeek = alg.calCurGestationalWeek()
                            Log.i(TAG, "curGestationalWeek=$curGestationalWeek")
                        } catch (e: Exception) {
                            e.printStackTrace()
                            return@setOnClickListener
                        }
                    }

                    is AuxiliaryAlgorithm -> {
                        try {
                            val curGestationalWeek = alg.calCurGestationalWeek()
                            Log.i(TAG, "curGestationalWeek=$curGestationalWeek")
                        } catch (e: Exception) {
                            e.printStackTrace()
                            return@setOnClickListener
                        }
                    }

                    else -> {}
                }

            }
        }
    }

}