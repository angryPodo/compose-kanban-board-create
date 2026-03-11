package woowacourse.kanban.board.domain

data class KanbanTask(val title: String, val description: String? = null, val tags: List<String> = emptyList(), val crewName: String) {
    init {
        require(title.isNotBlank()) { "제목은 비어 있거나 공백만 있을 수 없습니다." }
    }

    val visibleTags: List<String>
        get() = tags.take(5)

    companion object {
        private val TAG_REGEX = "^.{1,5}$".toRegex()

        fun isTitleValid(title: String): Boolean = title.isNotBlank()

        fun isTagCountValid(tags: List<String>): Boolean = tags.size <= 5

        fun isTagFormatValid(tags: List<String>): Boolean {
            if (tags.isEmpty()) return true
            return tags.all { it.matches(TAG_REGEX) }
        }
    }
}
