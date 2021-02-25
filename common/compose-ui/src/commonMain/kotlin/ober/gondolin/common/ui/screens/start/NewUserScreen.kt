package ober.gondolin.common.ui.screens.start

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
            EncryptionTextField()
            GenerateButton()
            Spacer(modifier = Modifier.padding(16.dp))
            PinTextField()
            DoneButton()
            Spacer(modifier = Modifier.padding(16.dp))
            ExplanationText()
        }
    }

    @Composable
    private fun EncryptionTextField() {
        CyberTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    all = 16.dp
                ),
            value = viewModel.encryptionKey.collectAsState().value,
            onValueChange = { viewModel.encryptionKey.value = it },
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
        CyberTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    all = 16.dp
                ),
            value = viewModel.pin.collectAsState().value,
            onValueChange = { viewModel.pin.value = it },
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
            onClick = { viewModel.onDoneClicked() },
            content = {
                Text(
                    text = "DONE",
                    style = TextStyles.monospaceBold
                )
            },
            enabled = viewModel.doneButtonEnabled.collectAsState().value
        )
    }

    @Composable
    private fun ExplanationText() {
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
