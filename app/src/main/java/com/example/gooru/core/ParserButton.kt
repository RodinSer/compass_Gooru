package com.example.gooru.core

import com.example.gooru.feature.domain.model.parser.Parser

enum class ParserButton(var item: Parser? = null) {
    SHARE, EDIT, FAVORITE, COMMENT,LINK,DOWNLOAD
}