package woowacourse.kanban.board.component.board

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import woowacourse.kanban.board.component.board.component.KanbanBoardContent
import woowacourse.kanban.board.component.dialog.TaskDialog

@Composable
fun KanbanBoardScreen(
    modifier: Modifier = Modifier,
    boardState: KanbanBoardState = rememberKanbanBoardState()
) {
    KanbanBoardContent(
        modifier = modifier,
        kanbanBoard = boardState.kanbanBoard,
        onTaskCreateClick = boardState::showTaskDialog,
    )

    if (boardState.isTaskDialogVisible) {
        TaskDialog(
            onCreateClick = boardState::addTask,
            onDismissClick = boardState::hideTaskDialog,
        )
    }
}
