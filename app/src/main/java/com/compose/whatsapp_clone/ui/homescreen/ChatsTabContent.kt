package com.compose.whatsapp_clone.ui.homescreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.compose.whatsapp_clone.ui.components.ContactItem
import com.compose.whatsapp_clone.ui.data.ContactList.contactsList

@Preview
@Composable
fun ChatTabContent() {

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(contactsList) { contactsList ->
            ContactItem(contactsList)
        }
    }
}