package com.ronjunevaldoz.geoexam.ui.component.screen

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ronjunevaldoz.geoexam.R

@Composable
fun JobFormTopAppBar(
    onCreateDone : () -> Unit,
    onNavigateBack : () -> Unit = {},
) {
    TopAppBar(
        elevation = 4.dp,
        title = {
            Text(stringResource(R.string.screen_title_job_creation))
        },
        backgroundColor = MaterialTheme.colors.primarySurface,
        navigationIcon = {
            IconButton(onClick = { onNavigateBack() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    modifier = Modifier,
                    contentDescription = "Back"
                )
            }
        }, actions = {
            IconButton(onClick = { onCreateDone() }) {
                Icon(
                    imageVector = Icons.Filled.Done,
                    modifier = Modifier,
                    contentDescription = "Done"
                )
            }
        })
}