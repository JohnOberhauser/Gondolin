package ober.gondolin.common.ui.start

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import ober.gondolin.common.ui.common.textStyle.TextStyles

@Composable
fun NewUserScreen() {
    val typography = MaterialTheme.typography
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        EncryptionTextField()
        GenerateButton()
    }
}

@Composable
private fun EncryptionTextField() {
    val textValue = remember { mutableStateOf(TextFieldValue()) }

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                all = Dp(16F)
            ),
        value = textValue.value,
        onValueChange = { textValue.value = it },
        label = {
            Text(
                text = "Encryption Key",
                style = TextStyles.commonTextStyle()
            )
        },
        backgroundColor = Color.White,
        textStyle = TextStyles.commonTextStyle()
    )
}

@Composable
private fun GenerateButton() {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                all = Dp(16F)
            ),
        onClick = {},
        content = {
            Text(
                text = "Generate",
                style = TextStyles.commonTextStyle()
            )
        }
    )
}