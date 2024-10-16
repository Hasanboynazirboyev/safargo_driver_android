package uz.safargo.driver.core.ui_components.drop_down

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastForEachIndexed
import androidx.compose.ui.window.PopupProperties

import uz.safargo.driver.R
import uz.safargo.driver.core.theme.AppColors
import uz.safargo.driver.core.ui_components.CustomHeightSpacer


@Composable

fun CustomDropDownMenu(
    modifier: Modifier = Modifier,
    items: List<DropDownItemData> = emptyList(),
    title: String = "",
    labelText: String = "",
    onValueChange: (selectedItems: List<DropDownItemData>) -> Unit = {},
    isSingleSelection: Boolean = false,
    titleColor: Color = Color(0xFF909090),
    selectedDropDownItems: List<DropDownItemData> = emptyList(),
    contentPadding: Int = 20
) {

    val expanded = remember { mutableStateOf(false) }
    val selectedItems = remember { mutableListOf<DropDownItemData>() }

    LaunchedEffect(Unit) {
        selectedItems.addAll(selectedDropDownItems)
    }

    val dropDownTitle = remember {
        if (selectedDropDownItems.isEmpty()) {
            mutableStateOf(title)
        } else {
            mutableStateOf(selectedDropDownItems.joinToString(", ") { it.name })
        }
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
    ) {
        if (labelText.isNotEmpty()) {
            Text(
                text = labelText,
                style = MaterialTheme.typography.titleSmall.copy(
                    color = Color.White,
                    fontSize = 10.sp
                ),
                textAlign = TextAlign.Start,
                modifier = Modifier
            )
            CustomHeightSpacer(size = 6)
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clip(RoundedCornerShape(15.dp))
                .clickable {
                    expanded.value = !expanded.value
                }

                .fillMaxWidth()

                .background(AppColors.backgroundGrey)
                .padding(contentPadding.dp)
        ) {
            Text(
                text = dropDownTitle.value,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontSize = 12.sp,
                    color = titleColor,
                )
            )
            Icon(
                painter = painterResource(id = if (!expanded.value) R.drawable.ic_arrow_down else R.drawable.ic_arrow_up),
                contentDescription = "",
                modifier = Modifier.size(16.dp),
                tint = AppColors.primary,
            )
        }



        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = {  },
            offset = DpOffset(x = (0).dp, y = 10.dp),
            modifier = Modifier
                .fillMaxWidth(.9f)
                .background(Color.White),
            properties = PopupProperties(focusable = false),
            shape = RoundedCornerShape(16.dp),
        ) {
            items.fastForEachIndexed { i, item ->
                CustomDropDownItem(
                    item = item.copy(index = i),
                    isSelected = selectedItems.contains(item.copy(index = i)),
                    modifier = Modifier,
                    onClick = { isSelected, selectedItem ->
                        if (isSelected) {
                            if (isSingleSelection) {
                                selectedItems.clear()
                                selectedItems.add(selectedItem)
                                expanded.value = false
                                dropDownTitle.value =
                                    selectedItems.joinToString(",") { it.name }
                            } else {
                                selectedItems.add(selectedItem)
                                dropDownTitle.value =
                                    selectedItems.joinToString(",") { it.name }
                            }
                        } else {
                            selectedItems.remove(selectedItem)
                            dropDownTitle.value = selectedItems.joinToString(",") { it.name }

                            if (isSingleSelection) {
                                expanded.value = false
                            }
                            if (selectedItems.isEmpty()) {
                                dropDownTitle.value = title
                            }
                        }
                        onValueChange(selectedItems)
                    },
                )
            }
        }

    }
}


data class DropDownItemData(
    val name: String,
    val index: Int = 0,
    val id: String = "",
)