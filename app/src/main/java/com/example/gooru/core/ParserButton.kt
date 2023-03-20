package com.example.gooru.core

import com.example.gooru.feature.domain.model.parser.Parser

enum class ParserButton(var item: Parser? = null) {
    SHARE,
    EDIT_ARTICLE,
    FAVORITE,
    SAVE_COMMENT,
    COMMENT_EXPAND,
    LINK,
    DOWNLOAD,
    DESCRIPTIONS_EXPAND,
    SAVE_ARTICLE,
}