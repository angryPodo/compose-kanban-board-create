package woowacourse.kanban.board.component.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun CreateTaskTitle(
    text: String,
    modifier: Modifier = Modifier,
    isRequired: Boolean = false,
) {
    val text = if (isRequired) "$text *" else text

    Text(
        text = text,
        fontSize = 14.sp,
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
private fun CreateTaskTitlePreview() {
    Column {
        CreateTaskTitle(
            text = "제목",
            isRequired = true,
        )

        CreateTaskTitle(text = "설명")
    }
}
