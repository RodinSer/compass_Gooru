package com.example.gooru.feature.Test

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gooru.R
import com.example.gooru.core.base.BaseFragment
import com.example.gooru.core.extensions.setItemTouchHelper
import com.example.gooru.databinding.FragmentBlankBinding
import com.example.gooru.databinding.ItemParserTestBinding
import com.example.gooru.feature.domain.model.parser.Parser
import org.koin.androidx.viewmodel.ext.android.viewModel

class BlankFragment : BaseFragment<FragmentBlankBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentBlankBinding.inflate(inflater)

    private val viewModel by viewModel<BlankViewModel> (  )

    private val adapter = TEstAdapter(::onClickListener)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(HorizontalDividerDecoration(100f))
        binding.recyclerView.setItemTouchHelper(resources.getDimensionPixelSize(R.dimen.offset240))

        dataObserver(viewModel._parsers) {
            adapter.submitData(it)
        }

    }


    fun onClickListener(parser: Parser) {
        viewModel.setFavorite(parser)
    }

}


class HorizontalDividerDecoration(private val divider: Float) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val horizontalPadding = divider / 2

        val adapter = parent.adapter ?: return
        val currentPosition = parent.getChildAdapterPosition(view)

        outRect.left = horizontalPadding.toInt()
        outRect.right = horizontalPadding.toInt()
        outRect.bottom = horizontalPadding.toInt()
        outRect.right = horizontalPadding.toInt()
    }
}

class TEstAdapter(
    private val onClickListener: (parser: Parser) -> Unit
) : PagingDataAdapter<Parser, TestViewHolder>(TestDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TestViewHolder(
        ItemParserTestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, onClickListener) }
    }
}

class TestViewHolder(val binding: ItemParserTestBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Parser, onClickListener: (parser: Parser) -> Unit) {

        binding.title.text = item.title
        binding.description.text = item.article
        binding.description.maxLines = item.maxTextLine
        binding.favoriteButton.isSelected = item.isFavorite

        binding.description.setOnClickListener {
            onClickListener(item)
        }

        binding.favoriteButton.setOnClickListener {
            onClickListener(item)
        }
    }
}

object TestDiff : DiffUtil.ItemCallback<Parser>() {
    override fun areItemsTheSame(oldItem: Parser, newItem: Parser): Boolean {
        val a= oldItem.id == newItem.id
        Log.d("KArt","Diif item $a ")
        return a
    }

    override fun areContentsTheSame(oldItem: Parser, newItem: Parser): Boolean {
        val a= oldItem == newItem
        Log.d("KArt","Diif content $a ")
        return a
    }

}