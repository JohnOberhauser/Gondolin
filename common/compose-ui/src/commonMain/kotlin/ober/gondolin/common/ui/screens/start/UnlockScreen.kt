package ober.gondolin.common.ui.screens.start

import androidx.compose.animation.Animatable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ober.gondolin.common.ui.common.textfield.CyberTextField
import ober.gondolin.common.ui.textStyle.TextStyles
import ober.gondolin.common.viewmodel.start.UnlockViewModel

class UnlockScreen {
    private lateinit var viewModel: UnlockViewModel

    @Composable
    fun Create() {
        viewModel = UnlockViewModel(rememberCoroutineScope())

        Content()
    }

    @Composable
    fun Content() {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            color = MaterialTheme.colors.background
        ) {
            Column {
                PinTextField()
                UnlockButton()
            }
        }
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
    private fun UnlockButton() {

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    end = 16.dp
                ),
            onClick = {
                viewModel.onUnlockClicked()
            },
            content = {
                Text(
                    text = "UNLOCK",
                    style = TextStyles.monospaceBold
                )
            },
            enabled = viewModel.unlockButtonEnabled.collectAsState().value
        )
    }
}