package com.example.gooru.feature.presentation.tabparsource.parser.adapters.holders

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.gooru.core.ParserButton
import com.example.gooru.databinding.ItemParserBinding
import com.example.gooru.feature.domain.model.parser.Parser

class ParserViewHolder(
    private val binding: ItemParserBinding,
    private val onClickListener: (button: ParserButton) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    private var parser: Parser? = null

    init {
        onClickButton(binding.commentExpandButton, ParserButton.COMMENT_EXPAND)
        onClickButton(binding.description, ParserButton.DESCRIPTIONS_EXPAND)
        onClickButton(binding.saveCommentButton, ParserButton.SAVE_COMMENT)
        onClickButton(binding.saveTextButton, ParserButton.SAVE_ARTICLE)
        onClickButton(binding.favoriteButton, ParserButton.FAVORITE)
        onClickButton(binding.downloadButton, ParserButton.DOWNLOAD)
        onClickButton(binding.editButton, ParserButton.EDIT_ARTICLE)
        onClickButton(binding.shareButton, ParserButton.SHARE)
        onClickButton(binding.linkButton, ParserButton.LINK)
    }

    fun bind(item: Parser) {
        parser = item
        binding.title.text = item.title
        binding.comment.setText(item.comment)
        binding.description.text = item.article
        binding.description.isVisible = true
        binding.favoriteButton.isSelected = item.isFavorite
        binding.commentExpandButton.isSelected = item.isCommentFull
        binding.comment.isVisible = item.isCommentVisibility
        binding.saveCommentButton.isVisible = item.isCommentVisibility
        binding.description.maxLines = item.maxTextLine
        binding.description.isVisible = !item.isEditArticle
        binding.editField.isVisible = item.isEditArticle
        binding.editText.setText(binding.description.text)
    }


    private fun onClickButton(view: View, button: ParserButton) = view.setOnClickListener {
        if (button == ParserButton.SAVE_ARTICLE)
            parser?.article = binding.editText.text.toString()
        if (button == ParserButton.SAVE_COMMENT)
            parser?.comment = binding.comment.text.toString()

        button.item = parser
        onClickListener(button)
    }
}