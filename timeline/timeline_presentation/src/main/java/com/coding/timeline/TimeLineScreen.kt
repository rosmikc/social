package com.coding.timeline

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.coding.core.R

@Composable
fun TimeLineScreen(
    restartApp: (String) -> Unit,
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TimeLineViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) { viewModel.initialize(restartApp) }

    var showExitAppDialog by remember { mutableStateOf(false) }
    var showRemoveAccDialog by remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        TopAppBar(
            title = { Text(stringResource(R.string.app_name)) },
            actions = {
                IconButton(onClick = { showExitAppDialog = true }) {
                    Icon(Icons.Filled.ExitToApp, "Exit app")
                }
                IconButton(onClick = { showRemoveAccDialog = true }) {
                    Icon(
                        Icons.Filled.Delete,
                        contentDescription = "Remove account"
                    )
                }
            }
        )

        if (showExitAppDialog) {
            AlertDialog(
                title = { Text(stringResource(R.string.sign_out_title)) },
                text = { Text(stringResource(R.string.sign_out_description)) },
                dismissButton = {
                    Button(onClick = { showExitAppDialog = false }) {
                        Text(text = stringResource(R.string.cancel))
                    }
                },
                confirmButton = {
                    Button(onClick = {
                        viewModel.onSignOutClick()
                        showExitAppDialog = false
                    }) {
                        Text(text = stringResource(R.string.sign_out))
                    }
                },
                onDismissRequest = { showExitAppDialog = false }
            )
        }

        if (showRemoveAccDialog) {
            AlertDialog(
                title = { Text(stringResource(R.string.delete_account_title)) },
                text = { Text(stringResource(R.string.delete_account_description)) },
                dismissButton = {
                    Button(onClick = { showRemoveAccDialog = false }) {
                        Text(text = stringResource(R.string.cancel))
                    }
                },
                confirmButton = {
                    Button(onClick = {
                        viewModel.onDeleteAccountClick()
                        showRemoveAccDialog = false
                    }) {
                        Text(text = stringResource(R.string.delete_account))
                    }
                },
                onDismissRequest = { showRemoveAccDialog = false }
            )
        }
    }
}