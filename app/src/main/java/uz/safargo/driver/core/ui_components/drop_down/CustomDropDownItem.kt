package uz.safargo.driver.core.ui_components.drop_down

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import uz.safargo.driver.R
import uz.safargo.driver.core.theme.AppColors

@Composable
fun CustomDropDownItem(
    item: DropDownItemData,
    onClick: (isSelected: Boolean, item: DropDownItemData) -> Unit,
    isSelected: Boolean = false,
    modifier: Modifier = Modifier,
) {


    val isDropDownSelected = remember { mutableStateOf(isSelected) }
    DropdownMenuItem(
        onClick = {
            isDropDownSelected.value = !isDropDownSelected.value
            onClick(
                isDropDownSelected.value, item
            )
        },

        text = {
            Row(
                modifier = Modifier
                    .padding(
                        horizontal = 12.dp,
                        vertical = 6.dp,
                    )
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(text = item.name)
                if (isDropDownSelected.value) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_check),
                        contentDescription = "",
                        modifier = Modifier.size(16.dp),
                        tint = AppColors.primary,
                    )
                }


            }
        },
        modifier = modifier,
        colors = MenuDefaults.itemColors(
            textColor = Color.Black,


            )

    )
}