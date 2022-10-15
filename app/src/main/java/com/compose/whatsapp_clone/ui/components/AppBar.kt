package com.compose.whatsapp_clone.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.whatsapp_clone.R

/*
 -Creating TopAppBar.
 -Adding icons on th top bar.
 -adding menu and actions.
 */
@Preview
@Composable
fun ShowAppToolBar() {
    AppToolBar(title = "WhatsApp")
}

@Composable
fun AppToolBar(title: String) {
    var expanded by remember { mutableStateOf(false) }

    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = Color(0xFF008065),
        contentColor = Color.White,
        elevation = 0.dp

    ) {
        Text(
            text = title,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp),
            fontSize = 18.sp
        )

        IconButton(
            onClick = {},
            modifier = Modifier.size(24.dp),
            enabled = true,
        ) {
            Icon(Icons.Default.Search, contentDescription = "search")
        }
        Box(
            contentAlignment = Alignment.TopEnd,
            modifier = Modifier.wrapContentSize(Alignment.TopEnd)
        ) {
            IconButton(onClick = { expanded = true }) {
                Icon(Icons.Default.MoreVert, contentDescription = "Localized description")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(onClick = { /* Handle new group! */ }) {
                    Text(stringResource(id = R.string.new_group))
                }
                DropdownMenuItem(onClick = { /* Handle new broadcast! */ }) {
                    Text(stringResource(id = R.string.new_broadcast))
                }
                DropdownMenuItem(onClick = { /* Handle linked devices! */ }) {
                    Text(stringResource(id = R.string.linked_devices))
                }
                DropdownMenuItem(onClick = { /* Handle sehared messages! */ }) {
                    Text(stringResource(id = R.string.shared_messages))
                }
                DropdownMenuItem(onClick = { /* Handle settings! */ }) {
                    Text(stringResource(id = R.string.settings))
                }
            }
        }
    }
}
