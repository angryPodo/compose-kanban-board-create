package woowacourse.kanban.board.component.board

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import woowacourse.kanban.board.component.board.component.KanbanBoardContent
import woowacourse.kanban.board.component.dialog.TaskDialog

@Composable
fun KanbanBoardScreen(
    onShowSnackbar: (String) -> Unit,
    modifier: Modifier = Modifier,
    boardState: KanbanBoardState = rememberKanbanBoardState(),
) {
    KanbanBoardContent(
        modifier = modifier,
        kanbanBoard = boardState.kanbanBoard,
        onTaskCreateClick = boardState::showTaskDialog,
    )

    if (boardState.isTaskDialogVisible) {
        TaskDialog(
            onCreateClick = { result ->
                boardState.addTask(result)
                onShowSnackbar("새로운 태스크가 추가되었습니다.")
            },
            onDismissClick = boardState::hideTaskDialog,
        )
    }
}
