package ober.gondolin.common.ui.screens.start

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import ober.gondolin.common.ui.common.textfield.CyberTextField
import ober.gondolin.common.ui.textStyle.TextStyles

@Composable
fun NewUserScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        EncryptionTextField()
        GenerateButton()
        Spacer(modifier = Modifier.padding(16.dp))
        PinTextField()
        DoneButton()
        Spacer(modifier = Modifier.padding(16.dp))
        ExplainationText()
    }
}

@Composable
private fun EncryptionTextField() {
    val textValue = remember { mutableStateOf(TextFieldValue()) }

    CyberTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                all = 16.dp
            ),
        value = textValue.value,
        onValueChange = { textValue.value = it },
        label = {
            Text(
                text = "Encryption Key",
                style = TextStyles.monospaceBold
            )
        },
        textStyle = TextStyles.monospaceBold
    )
}

@Composable
private fun GenerateButton() {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp
            ),
        onClick = {},
        content = {
            Text(
                text = "GENERATE",
                style = TextStyles.monospaceBold
            )
        }
    )
}

@Composable
private fun PinTextField() {
    val textValue = remember { mutableStateOf(TextFieldValue()) }

    CyberTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                all = 16.dp
            ),
        value = textValue.value,
        onValueChange = { textValue.value = it },
        label = {
            Text(
                text = "PIN",
                style = TextStyles.monospaceBold
            )
        },
        textStyle = TextStyles.monospaceBold
    )
}

@Composable
private fun DoneButton() {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp
            ),
        onClick = {},
        content = {
            Text(
                text = "DONE",
                style = TextStyles.monospaceBold
            )
        },
        enabled = false
    )
}

@Composable
private fun ExplainationText() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                all = 16.dp
            ),
        text = "The encryption key is required when decrypting backup files on a new device, or decrypting your files if you forgot your PIN.  " +
                "You should write it down and keep it in a safe place"
    )
}