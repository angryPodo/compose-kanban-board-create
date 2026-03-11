package woowacourse.kanban.board.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import woowacourse.kanban.board.component.dialog.component.StatusOptionCard
import woowacourse.kanban.board.component.dialog.component.TaskDialogButton
import woowacourse.kanban.board.component.dialog.component.TaskDialogTextField
import woowacourse.kanban.board.component.dialog.component.TaskDialogTopAppBar
import woowacourse.kanban.board.component.dialog.component.TaskFieldLabel

@Composable
fun TaskDialog(
    onCreateClick: () -> Unit,
    onDismissClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var titleValue by remember { mutableStateOf("") }
    var isTitleError by remember { mutableStateOf(false) }

    var descriptionValue by remember { mutableStateOf("") }

    var tagValue by remember { mutableStateOf("") }
    var isTagError by remember { mutableStateOf(false) }
    var isTagLengthError by remember { mutableStateOf(false) }
    var isTagCountError by remember { mutableStateOf(false) }
    val tagErrorMessage = when {
        isTagCountError -> "태그는 5자 이내로 5개까지만 등록할 수 있습니다."
        isTagLengthError -> "태그 형식이 올바르지 않습니다."
        else -> "태그를 쉼표로 구분하여 입력하세요. (예: 버그,긴급)"
    }

    val statusList = listOf("To Do", "In Progress", "Done")
    var selectedStatusIndex by remember { mutableIntStateOf(0) }

    val assigneeList = listOf("다이노", "페임스")
    var selectedAssigneeIndex by remember { mutableIntStateOf(0) }

    var enabled by remember { mutableStateOf(false) }

    Dialog(
        onDismissRequest = onDismissClick,
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false,
        ),
    ) {
        Column(
            modifier = modifier
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .padding(vertical = 28.dp, horizontal = 24.dp)
                .width(672.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            TaskDialogTopAppBar(
                title = "새 태스크 생성",
                onClick = onDismissClick,
                modifier = Modifier.padding(bottom = 4.dp),
            )

            HorizontalDivider(
                color = Color.Black,
                thickness = Dp.Hairline,
            )

            TaskLabelLayout(
                label = "제목",
                isRequired = true,
            ) {
                TaskDialogTextField(
                    value = titleValue,
                    onValueChanged = {
                        titleValue = it
                        isTitleError = titleValue.isBlank()
                        enabled = !isTagError && !isTitleError
                    },
                    isError = isTitleError,
                    placeholder = "태스크 제목을 입력하세요.",
                    maxLines = 1,
                )

                if (isTitleError) {
                    Text(
                        text = "제목을 입력해 주세요.",
                        fontSize = 12.sp,
                        color = Color.Red,
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .padding(horizontal = 16.dp),
                        maxLines = 1,
                    )
                }
            }

            TaskLabelLayout(label = "설명") {
                TaskDialogTextField(
                    value = descriptionValue,
                    onValueChanged = { descriptionValue = it },
                    placeholder = "태스크에 대한 자세한 설명을 입력하세요.",
                    modifier = Modifier.height(116.dp),
                )
            }

            TaskLabelLayout(label = "태그") {
                TaskDialogTextField(
                    value = tagValue,
                    onValueChanged = { value ->
                        tagValue = value
                        val splitTags = tagValue.split(",")
                        isTagCountError = splitTags.size > 5
                        isTagLengthError = (tagValue.isNotBlank() && splitTags.any { it.length !in 1..5 })
                        isTagError = isTagCountError || isTagLengthError
                    },
                    placeholder = "태그를 쉼표로 구분하여 입력하세요 (예: 버그, 긴급)",
                    isError = isTagError,
                    modifier = Modifier.padding(bottom = 4.dp),
                    maxLines = 1,
                )
                Text(
                    text = tagErrorMessage,
                    fontSize = 12.sp,
                    color = if (isTagError) Color.Red else Color.Black,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    maxLines = 1,
                )
            }

            TaskLabelLayout(
                label = "상태",
                isRequired = true,
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    statusList.forEachIndexed { index, string ->
                        StatusOptionCard(
                            text = string,
                            isSelected = selectedStatusIndex == index,
                            onClick = { selectedStatusIndex = index },
                        )
                    }
                }
            }

            TaskLabelLayout(
                label = "담당자",
                isRequired = true,
            ) {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    assigneeList.forEachIndexed { index, string ->
                        StatusOptionCard(
                            text = string,
                            isSelected = selectedAssigneeIndex == index,
                            onClick = { selectedAssigneeIndex = index },
                        )
                    }
                }
            }

            HorizontalDivider(
                color = Color.Black,
                thickness = Dp.Hairline,
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                TaskDialogButton(
                    text = "취소",
                    onClick = onDismissClick,
                )
                Spacer(Modifier.width(12.dp))
                TaskDialogButton(
                    text = "생성",
                    onClick = onCreateClick,
                    enabled = enabled,
                    contentColor = Color.White,
                    containerColor = Color.Blue,
                )
            }
        }
    }
}

@Composable
private fun TaskLabelLayout(
    label: String,
    modifier: Modifier = Modifier,
    isRequired: Boolean = false,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        TaskFieldLabel(
            label = label,
            isRequired = isRequired,
        )

        Spacer(Modifier.height(8.dp))

        content()
    }
}
