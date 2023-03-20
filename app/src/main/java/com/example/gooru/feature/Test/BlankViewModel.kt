package com.example.gooru.feature.Test

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.gooru.core.Test
import com.example.gooru.feature.data.PagingSoursParser
import com.example.gooru.feature.domain.model.parser.Parser
import com.example.gooru.feature.domain.useCase.parser.GetParserUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class TestRepository() {


    class TestPagingSoursParser(
    ) : PagingSource<Int, Parser>() {


        private val parser1 = Parser(
            ",nksasfjkdssadalsjdisufiosdyfisdyfiusdyfuisaydsuidyfuidsfyuidsyfuisdyf]\nsdfiodsufisdufi\nsdiuf" +
                    "sdfksdkfsdkhfjsdhfjsdhfjsdhfjksdhfkjsddfklsdfklsdkflsdkflsdkflsdkflsdkflsdkflsdkflsdkhf",
            "dsksdlfklsdkfl", 1, true, true, 21, "dfklsdkfl0", "dfldskflsdf'", ";fksdlfk", 25,
            "flkdl", 65
        )


        private val parser2 = Parser(
            ",nksasfjkdssadalsjdisufiosdyfisdyfiusdyfuisaydsuidyfuidsfyuidsyfuisdyf]\nsdfiodsufisdufi\nsdiuf" +
                    "sdfksdkfsdkhfjsdhfjsdhfjsdhfjksdhfkjsddfklsdfklsdkflsdkflsdkflsdkflsdkflsdkflsdkflsdkhf",
            "dsksdlfklsdkfl", 5, true, true, 21, "dfklsdkfl0", "dfldskflsdf'", ";fksdlfk", 25,
            "flkdl", 65
        )

        private val parser = Parser(
            ",nksasfjkdssadalsjdisufiosdyfisdyfiusdyfuisaydsuidyfuidsfyuidsyfuisdyf]\nsdfiodsufisdufi\nsdiuf" +
                    "sdfksdkfsdkhfjsdhfjsdhfjsdhfjksdhfkjsddfklsdfklsdkflsdkflsdkflsdkflsdkflsdkflsdkflsdkhf",
            "dsksdlfklsdkfl", 0, true, true, 21, "dfklsdkfl0", "dfldskflsdf'", ";fksdlfk", 25,
            "flkdl", 65
        )

        override fun getRefreshKey(state: PagingState<Int, Parser>) = 1
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Parser> {
            val page = params.key
            delay(100)
            val items = listOf(parser1, parser2, parser)


            return LoadResult.Page(
                prevKey = null,
                data = items,
                nextKey = null
            )

        }

    }

    fun getParserByParSourceId(): Flow<PagingData<Parser>> {

        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = { TestPagingSoursParser() }
        ).flow
    }


}

class BlankViewModel(private val repository: TestRepository) : ViewModel() {


    var _parsers: Flow<PagingData<Parser>>? = null
    private val localChanges = LocalChanges()
    private val localChangesFlow = MutableStateFlow(OnChange(localChanges))

    init {
        getParsers()
    }

    private fun getParsers() {
        _parsers = repository.getParserByParSourceId().cachedIn(viewModelScope)
            .combine(localChangesFlow, this::merge).cachedIn(viewModelScope)
    }


    private fun merge(
        oneParser: PagingData<Parser>,
        localChanges: OnChange<LocalChanges>
    ): PagingData<Parser> {
        return oneParser.map { parser ->
            val maxLine = localChanges.value.maxLine[parser.id]
            val favorite = localChanges.value.isFavorite[parser.id]


//            var mergeParser = if (maxLine == null) parser
//            else parser.copy(maxTextLine = maxLine)

            Log.e("Kart", favorite.toString())

            val mergeParser = if (favorite == null) parser else parser.copy(isFavorite = favorite)

            mergeParser
        }
    }

    fun setArticlesMaxLaine(parser: Parser) {
        val maxTextLine = if (parser.maxTextLine == 3) Int.MAX_VALUE else 3
        localChanges.maxLine[parser.id] = maxTextLine
        localChangesFlow.value = OnChange(localChanges)
    }

    fun setFavorite(parser: Parser) {
        val favorite: Boolean = !parser.isFavorite
        localChanges.isFavorite[parser.id] = favorite
        localChangesFlow.value = OnChange(localChanges)
    }
}

class OnChange<T>(val value: T)

class LocalChanges() {
    val maxLine = mutableMapOf<Int, Int>()
    val isFavorite = mutableMapOf<Int, Boolean>()
    val isEditArticle = mutableMapOf<Int, Boolean>()
}
