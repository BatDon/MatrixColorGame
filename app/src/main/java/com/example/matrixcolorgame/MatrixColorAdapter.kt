package com.example.matrixcolorgame


import android.content.Context
import android.graphics.Color
import android.graphics.Color.BLACK
import android.graphics.Color.WHITE
import android.graphics.Color.*
import android.util.Log
//import android.graphics.Color.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.matrixcolorgame.databinding.ColorItemBinding
import kotlinx.android.synthetic.main.color_item.view.*
import kotlin.random.Random


class MatrixColorAdapter(private val colorList: ArrayList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {



    var onColorColumn1Click: ((String) -> Unit)? = null
    var onChooseRandomColor: (() -> Int)? = null

    override fun getItemCount(): Int {
        return colorList.size
    }

    inner class ItemViewHolder(var viewBinding: ColorItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        init {
            viewBinding.root.color1Button.setOnClickListener {
                colorList[adapterPosition]?.let { color -> onColorColumn1Click?.invoke(color) }
            }
            viewBinding.root.color2Button.setOnClickListener {
                colorList[adapterPosition]?.let { color -> onColorColumn1Click?.invoke(color) }
            }
            viewBinding.root.color3Button.setOnClickListener {
                colorList[adapterPosition]?.let { color -> onColorColumn1Click?.invoke(color) }
            }
            viewBinding.root.color4Button.setOnClickListener {
                colorList[adapterPosition]?.let { color -> onColorColumn1Click?.invoke(color) }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val binding = ColorItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemViewHolder = holder as MatrixColorAdapter.ItemViewHolder
        onChooseRandomColor?.invoke()?.let {
            Log.i("random color","$it")
            itemViewHolder.viewBinding.color1Button.setBackgroundColor(
                it
            )
        }
        onChooseRandomColor?.invoke()?.let {
            Log.i("random color","$it")
            itemViewHolder.viewBinding.color2Button.setBackgroundColor(
                it
            )
        }
        onChooseRandomColor?.invoke()?.let {
            Log.i("random color","$it")
            itemViewHolder.viewBinding.color3Button.setBackgroundColor(
                it
            )
        }
        onChooseRandomColor?.invoke()?.let {
            Log.i("random color","$it")
            itemViewHolder.viewBinding.color4Button.setBackgroundColor(
                it
            )
        }
    }

    fun updateColorList(newList: ArrayList<String>) {
        colorList.clear()
        colorList.addAll(newList)
        notifyDataSetChanged()
    }

}