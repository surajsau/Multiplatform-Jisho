package `in`.surajsau.jisho.viewmodel

import `in`.surajsau.jisho.model.Bucket
import `in`.surajsau.jisho.viewmodel.expected.BaseViewModel
import `in`.surajsau.jisho.viewmodel.expected.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

public class TagsViewModel : BaseViewModel<TagsUiState>() {

    private val _tags = MutableStateFlow<List<Tag>>(emptyList())

    override val state: StateFlow<TagsUiState>
        get() = _tags
            .map { TagsUiState(tags = it) }
            .stateIn(scope, SharingStarted.WhileSubscribed(), TagsUiState())

    public fun onNewTagConfirmed(tagName: String) {

    }

}

public data class TagsUiState(
    val tags: List<Tag> = emptyList()
): UiState

public data class Tag(
    val bucket: Bucket,
    val color: Int,
    val count: Int
)