package com.coding.timeline

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.coding.core.R
import com.coding.post.AsyncImage
import com.coding.timeline_domain.model.Post
import kotlinx.coroutines.flow.toList

@Composable
fun TimeLineScreen(
    restartApp: (String) -> Unit,
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TimeLineViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) { viewModel.initialize(restartApp) }

    val posts by viewModel.posts.collectAsState(emptyList())
    var showExitAppDialog by remember { mutableStateOf(false) }
    var showRemoveAccDialog by remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        TopAppBar(
            title = { Text(stringResource(R.string.timeline_title)) },
            actions = {
                IconButton(onClick = { viewModel.onAddClick(openScreen) }) {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = "Create Post"
                    )
                }
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
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            LazyColumn {
                items(posts, key = { it.id }) { postItem ->
                    PostItem(
                        post = postItem
                    )
                }
            }
        }

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

@Composable
fun PostItem(
    post: Post
) {
    Card(
        modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
        ) {
            Text(
                text = post.timestamp.toDate().toString(),
                modifier = Modifier.padding(2.dp, 12.dp, 12.dp, 2.dp),
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Start
            )
            Text(
                text = "User: ${post.userId.substring(0,8)}",
                modifier = Modifier.padding(2.dp, 2.dp, 12.dp, 2.dp),
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Start
            )
            Text(
                text = post.text,
                modifier = Modifier.padding(12.dp, 12.dp, 12.dp, 5.dp),
                style = MaterialTheme.typography.h6
            )

            if(post.imageUrl != null) {
                displayImage(post)
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 0.dp, 12.dp)
            )
        }
    }
}

@Composable
private fun displayImage(post: Post) {
    Image(
        painter = rememberImagePainter(
            data = post.imageUrl,
            builder = {
                crossfade(true)
            }
        ),
        contentDescription = post.id,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(150.dp)
            .border(BorderStroke(1.dp, Color.Black))
            .aspectRatio(1f)
            .clip(
                RoundedCornerShape(
                    topStart = 15.dp,
                    bottomStart = 15.dp
                )
            )
    )

}