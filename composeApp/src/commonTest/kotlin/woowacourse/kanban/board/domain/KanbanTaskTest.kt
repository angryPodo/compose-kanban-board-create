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
        val task = KanbanTask(title = "제목", tags = tags, crewName = "아키")

        // When
        val visibleTags = task.visibleTags

        // Then
        assertThat(visibleTags).hasSize(5)
        assertThat(visibleTags).containsExactly("1", "2", "3", "4", "5")

        // 추가 검증: 원본 데이터는 6개로 유지된다
        assertThat(task.tags).hasSize(6)
    }
}
