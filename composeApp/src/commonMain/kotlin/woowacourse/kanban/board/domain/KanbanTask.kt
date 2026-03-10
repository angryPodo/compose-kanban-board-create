package woowacourse.kanban.board.domain

data class KanbanTask(val title: String, val description: String? = null, val tags: List<String> = emptyList(), val crewName: String) {
    init {
        require(title.isNotBlank()) { "제목은 비어 있거나 공백만 있을 수 없습니다." }
    }

    val visibleTags: List<String>
        get() = tags.take(5)
}
