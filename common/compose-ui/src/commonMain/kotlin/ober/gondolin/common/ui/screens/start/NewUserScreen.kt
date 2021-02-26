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
    fun Create(viewModel: NewUserViewModel) {
        this.viewModel = viewModel

        Content()
    }

    @Composable
    private fun Content() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 16.dp
                )
        ) {
            Spacer(modifier = Modifier.padding(16.dp))
            EncryptionTextField()
            Spacer(modifier = Modifier.padding(8.dp))
            ReEnterEncryptionTextField()
            Spacer(modifier = Modifier.padding(16.dp))
            GenerateButton()
            Spacer(modifier = Modifier.padding(16.dp))
            PinTextField()
            Spacer(modifier = Modifier.padding(16.dp))
            DoneButton()
            Spacer(modifier = Modifier.padding(16.dp))
            ExplanationText()
        }
    }

    @Composable
    private fun EncryptionTextField() {
        CyberTextField(
            modifier = Modifier
                .fillMaxWidth(),
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
    private fun ReEnterEncryptionTextField() {
        CyberTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = viewModel.reEnteredEncryptionKey.collectAsState().value,
            onValueChange = { viewModel.reEnteredEncryptionKey.value = it },
            label = {
                Text(
                    text = "Re-Enter Encryption Key",
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
                .fillMaxWidth(),
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
                .fillMaxWidth(),
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
                .fillMaxWidth(),
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
                .fillMaxWidth(),
            text = "The encryption key is required when decrypting backup files on a new device, or decrypting your files if you forgot your PIN.  " +
                    "You should write it down and keep it in a safe place"
        )
    }
}
