package ober.gondolin.common.ui.screens.start

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ober.gondolin.common.ui.common.textfield.CyberTextField
import ober.gondolin.common.ui.textStyle.TextStyles
import ober.gondolin.common.viewmodel.start.NewUserViewModel

class NewUserScreen {
    private lateinit var viewModel: NewUserViewModel

    @Composable
    fun Create() {
        viewModel = NewUserViewModel(rememberCoroutineScope())

        Content()
    }

    @Composable
    private fun Content() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            EncryptionTextField(viewModel)
            GenerateButton(viewModel)
            Spacer(modifier = Modifier.padding(16.dp))
            PinTextField()
            DoneButton()
            Spacer(modifier = Modifier.padding(16.dp))
            ExplainationText()
        }
    }

    @Composable
    private fun EncryptionTextField(viewModel: NewUserViewModel) {
        val textValue = remember { mutableStateOf(TextFieldValue()) }

        rememberCoroutineScope().launch {
            viewModel.encryptionKey.collect {
                textValue.value = TextFieldValue(it)
            }
        }

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
    private fun GenerateButton(viewModel: NewUserViewModel) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    end = 16.dp
                ),
            onClick = {
                viewModel.onGenerateClicked()
            },
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
}
