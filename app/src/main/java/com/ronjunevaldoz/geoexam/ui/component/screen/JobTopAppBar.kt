package com.ronjunevaldoz.geoexam.ui.component.screen

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ronjunevaldoz.geoexam.R

@Composable
fun JobTopAppBar(
    onAddJob : () -> Unit
) {
    TopAppBar(
        elevation = 4.dp,
        title = {
            Text(stringResource(R.string.screen_title_job_listing))
        },
        backgroundColor = MaterialTheme.colors.primarySurface,
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Filled.Home,
                    modifier = Modifier,
                    contentDescription = "Home"
                )
            }
        }, actions = {
            IconButton(onClick = { onAddJob() }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    modifier = Modifier,
                    contentDescription = "Add"
                )
            }
        })
}