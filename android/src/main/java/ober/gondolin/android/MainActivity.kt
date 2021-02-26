package ober.gondolin.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.graphics.toArgb
import ober.gondolin.common.ui.screens.Root
import ober.gondolin.common.ui.theme.ComposeAppTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeAppTheme {
                window.statusBarColor = MaterialTheme.colors.primary.toArgb()
                Root().Create()
            }
        }
    }
}
