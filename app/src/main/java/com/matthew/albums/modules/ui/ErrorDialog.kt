package com.matthew.albums.modules.ui

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.matthew.albums.R
import com.matthew.albums.databinding.DialogErrorBinding
import java.lang.Exception

class ErrorDialog : DialogFragment() {

    companion object{
        const val EXCEPTION = "EXCEPTION"
        fun newInstance(exception : Exception) : ErrorDialog{
            return ErrorDialog().apply {
                arguments =Bundle().apply{
                    putSerializable(EXCEPTION, exception)
                }
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val binding: DialogErrorBinding = DataBindingUtil.inflate(LayoutInflater.from(activity), R.layout.dialog_error, null, false)

        binding.exception = arguments?.getSerializable(EXCEPTION) as Exception

        return AlertDialog.Builder(activity as Context).setView(binding.root).create().apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

}