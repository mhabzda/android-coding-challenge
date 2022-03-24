package com.shiftkey.codingchallenge.ui.item

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.res.use
import com.shiftkey.codingchallenge.R
import com.shiftkey.codingchallenge.databinding.ViewItemBinding

class ItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val binding = ViewItemBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.ItemView, 0, 0).use {
            binding.viewItemLabel.text = it.getString(R.styleable.ItemView_label)
        }
    }

    fun setTitle(text: String) {
        binding.viewItemTitle.text = text
    }
}
