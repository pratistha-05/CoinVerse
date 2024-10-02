package com.example.coinwave.ui.screen.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController) {
  val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
  var searchQuery by remember { mutableStateOf("") }

  Scaffold(

    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

    topBar = {
      TopAppBar(
        title = {
          OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Search...", color = Color.White) },
            modifier = Modifier
              .fillMaxWidth()
              .padding(end = 8.dp),
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.colors(
              focusedTextColor = Color.White,
            )
          )
        },
        navigationIcon = {
          IconButton(onClick = {
            navController.popBackStack()
          }) {
            Icon(
              imageVector = Icons.Filled.ArrowBack,
              contentDescription = "Back",
              tint = Color.White // White icon color
            )
          }
        },
        actions = {},
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.topAppBarColors(
          containerColor = Color.Black, // Black background for AppBar
          titleContentColor = Color.White, // White title text color
          navigationIconContentColor = Color.White // White icon color
        )
      )
    },
    containerColor = Color.Black,
    contentColor = Color.Black
  ) { innerPadding ->

  }
}
