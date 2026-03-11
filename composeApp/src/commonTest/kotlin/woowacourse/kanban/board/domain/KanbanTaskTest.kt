package woowacourse.kanban.board.domain

import kotlin.test.Test
import kotlin.test.assertFailsWith
import org.assertj.core.api.Assertions.assertThat

class KanbanTaskTest {

    @Test
    fun `KanbanTask 정상 생성 - 모든 정보가 올바른 경우`() {
        // Given
        val title = "새로운 기능 구현"
        val description = "이 기능은 매우 중요합니다."
        val tags = listOf("긴급", "백엔드")
        val crewName = "아키"

        // When
        val task = KanbanTask(
            title = title,
            description = description,
            tags = tags,
            crewName = crewName,
        )

        // Then
        assertThat(task.title).isEqualTo(title)
        assertThat(task.description).isEqualTo(description)
        assertThat(task.tags).containsExactlyElementsOf(tags)
        assertThat(task.crewName).isEqualTo(crewName)
    }

    @Test
    fun `KanbanTask 생성 실패 - 제목이 비어 있는 경우`() {
        // Given
        val emptyTitle = ""

        // When & Then
        val exception = assertFailsWith<IllegalArgumentException> {
            KanbanTask(title = emptyTitle, crewName = "아키")
        }
        assertThat(exception.message).isEqualTo("제목은 비어 있거나 공백만 있을 수 없습니다.")
    }

    @Test
    fun `KanbanTask 생성 실패 - 제목이 공백만 있는 경우`() {
        // Given
        val blankTitle = "   "

        // When & Then
        val exception = assertFailsWith<IllegalArgumentException> {
            KanbanTask(title = blankTitle, crewName = "아키")
        }
        assertThat(exception.message).isEqualTo("제목은 비어 있거나 공백만 있을 수 없습니다.")
    }

    @Test
    fun `태그 제한 - 6개 이상의 태그를 넣어도 visibleTags는 5개만 반환한다`() {
        // Given
        val tags = listOf("1", "2", "3", "4", "5", "6")
        val task = KanbanTask(title = "제목", tags = tags, crewName = " 아키")

        // When
        val visibleTags = task.visibleTags

        // Then
        assertThat(visibleTags).hasSize(5)
        assertThat(visibleTags).containsExactly("1", "2", "3", "4", "5")

        // 추가 검증: 원본 데이터는 6개로 유지된다
        assertThat(task.tags).hasSize(6)
    }

    @Test
    fun `isTitleValid 검증 - 정상적인 제목이면 true를 반환한다`() {
        assertThat(KanbanTask.isTitleValid("제목")).isTrue()
    }

    @Test
    fun `isTitleValid 검증 - 공백이거나 비어있으면 false를 반환한다`() {
        assertThat(KanbanTask.isTitleValid("")).isFalse()
        assertThat(KanbanTask.isTitleValid("   ")).isFalse()
    }

    @Test
    fun `isTagCountValid 검증 - 태그가 5개 이하이면 true를 반환한다`() {
        assertThat(KanbanTask.isTagCountValid(emptyList())).isTrue()
        assertThat(KanbanTask.isTagCountValid(listOf("1", "2", "3", "4", "5"))).isTrue()
    }

    @Test
    fun `isTagCountValid 검증 - 태그가 6개 이상이면 false를 반환한다`() {
        assertThat(KanbanTask.isTagCountValid(listOf("1", "2", "3", "4", "5", "6"))).isFalse()
    }

    @Test
    fun `isTagFormatValid 검증 - 빈 리스트면 true를 반환한다`() {
        assertThat(KanbanTask.isTagFormatValid(emptyList())).isTrue()
    }

    @Test
    fun `isTagFormatValid 검증 - 모든 태그가 1~5자이면 true를 반환한다`() {
        assertThat(KanbanTask.isTagFormatValid(listOf("1", "12345", "태그임다"))).isTrue()
    }

    @Test
    fun `isTagFormatValid 검증 - 태그 중 하나라도 5자를 초과하면 false를 반환한다`() {
        assertThat(KanbanTask.isTagFormatValid(listOf("정상태그", "여섯글자태그"))).isFalse()
    }

    @Test
    fun `isTagFormatValid 검증 - 태그 중 하나라도 비어있으면 false를 반환한다`() {
        assertThat(KanbanTask.isTagFormatValid(listOf("정상", ""))).isFalse()
    }
}
