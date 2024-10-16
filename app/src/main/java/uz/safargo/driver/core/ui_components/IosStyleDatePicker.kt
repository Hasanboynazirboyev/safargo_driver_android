package uz.safargo.driver.core.ui_components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.Month
import java.time.format.TextStyle
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun IOSStyleDatePicker(
    selectedDate: MutableState<LocalDate> = remember { mutableStateOf(LocalDate.now()) },
    onDateChange: (LocalDate) -> Unit
) {
    val years = (1900..2100).toList()
    val months = (1..12).toList()
    val days = (1..selectedDate.value.lengthOfMonth()).toList()

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Year Picker
        LazyColumn(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {
                items(years.size) { index ->
                    Text(
                        text = years[index].toString(),
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable {
                                selectedDate.value = selectedDate.value.withYear(years[index])
                                onDateChange(selectedDate.value)
                            },
                        color = if (selectedDate.value.year == years[index]) Color.Blue else Color.Black
                    )
                }
            }
        )

        // Month Picker
        LazyColumn(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {
                items(months.size) { index ->
                    val month = months[index]
                    Text(
                        text = Month.of(month).getDisplayName(TextStyle.SHORT, Locale.getDefault()),
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable {
                                selectedDate.value = selectedDate.value.withMonth(month)
                                onDateChange(selectedDate.value)
                            },
                        color = if (selectedDate.value.monthValue == month) Color.Blue else Color.Black
                    )
                }
            }
        )

        // Day Picker
        LazyColumn(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {
                items(days.size) { index ->
                    val day = days[index]
                    Text(
                        text = day.toString(),
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable {
                                selectedDate.value = selectedDate.value.withDayOfMonth(day)
                                onDateChange(selectedDate.value)
                            },
                        color = if (selectedDate.value.dayOfMonth == day) Color.Blue else Color.Black
                    )
                }
            }
        )
    }
}
