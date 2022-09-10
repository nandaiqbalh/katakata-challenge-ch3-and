package com.nandaiqbalh.katakata.helper

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nandaiqbalh.katakata.databinding.RowItemHurufBinding
import com.nandaiqbalh.katakata.helper.`interface`.OnItemClickCallback

class KataAdapter: RecyclerView.Adapter<KataAdapter.ViewHolder>(){

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    private val kataList = ArrayList<String>()

    fun submitData(list: List<String>) {
        kataList.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowItemHurufBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val kata = kataList[position]

        with(holder) {
            binding.btnHuruf.text = kata

            binding.btnHuruf.setOnClickListener {
                onItemClickCallback.onItemClicked(kata)
            }
        }
    }

    override fun getItemCount(): Int = kataList.size

    class ViewHolder(val binding: RowItemHurufBinding) : RecyclerView.ViewHolder(binding.root)
}