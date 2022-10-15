package com.compose.whatsapp_clone.ui.homescreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.whatsapp_clone.R
import com.compose.whatsapp_clone.ui.components.AppToolBar
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch


@ExperimentalAnimationApi
@ExperimentalPagerApi
@Preview
@Composable
fun ContactsHomeScreen() {
    val pagerState = rememberPagerState(pageCount = 3)
    var visible by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            AppToolBar(stringResource(R.string.app_title))
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .background(color = Color(0xFF008065))
            )
            TabScreen(pagerState)
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Bottom
        ) {

            AnimatedVisibility(visible = visible) {
                FloatingActionButton(
                    modifier = Modifier
                        .size(50.dp), backgroundColor = Color(0xFFADAFAF),
                    onClick = { /*do something*/
                    }) {
                    FloatingIcon(drawerId = R.drawable.ic_baseline_edit_24)
                }
            }

            FloatingActionButton(
                modifier = Modifier
                    .wrapContentHeight()
                    .wrapContentWidth()
                    .padding(vertical = 10.dp), backgroundColor = Color(0xFF00AB88),
                onClick = { /*do something*/

                }) {

                when (pagerState.currentPage) {
                    0 -> {
                        visible = false
                        FloatingIcon(drawerId = R.drawable.ic_baseline_message_24)
                    }
                    1 -> {
                        visible = true
                        FloatingIcon(drawerId = R.drawable.ic_baseline_photo_camera_24)

                    }
                    2 -> {
                        visible = false
                        FloatingIcon(drawerId = R.drawable.ic_baseline_add_ic_call_24)

                    }
                    else -> {
                        visible = false
                        FloatingIcon(drawerId = R.drawable.ic_baseline_add_ic_call_24)
                    }
                }
            }


        }
    }
}

@Composable
fun FloatingIcon(drawerId: Int) {
    Icon(
        painter = painterResource(
            id = drawerId
        ),
        tint = Color.White,
        contentDescription = "Localized description"
    )
}

@ExperimentalPagerApi
@Composable
fun TabScreen(pagerState: PagerState) {

    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.White)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color(0xFF008065))
        ) {

            Tabs(pagerState)
        }
        TabsContent(pagerState)
    }
}


@ExperimentalPagerApi
@Composable
fun Tabs(pagerState: PagerState) {
    Icon(
        painter = painterResource(id = R.drawable.ic_baseline_add_ic_call_24),
        contentDescription = "",
        modifier = Modifier
            .size(40.dp)
            .wrapContentHeight()
            .padding(5.dp),
        tint = Color.White
    )
    val tabData = listOf(
        stringResource(id = R.string.chats),
        stringResource(id = R.string.status),
        stringResource(id = R.string.calls)
    )
    val scope = rememberCoroutineScope()
    TabRow(
        backgroundColor = Color(0xFF008065),
        modifier = Modifier
            .fillMaxWidth(),
        selectedTabIndex = pagerState.currentPage,
        contentColor = Color(0xFF008065),
        divider = { TabRowDefaults.Divider(thickness = 3.dp, color = Color(0xFF008065)) },
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 3.dp,
                color = Color.White
            )
        }
    )
    {
        tabData.forEachIndexed { index, text ->
            Tab(
                selected = pagerState.currentPage == index,
                selectedContentColor = Color(0xFFCED6D4),
                unselectedContentColor = Color(0xFF007F6F),
                onClick = {

                    scope.launch { pagerState.animateScrollToPage(index) }
                },
                text = {
                    Row(horizontalArrangement = Arrangement.Center) {
                        Text(
                            fontSize = 13.sp,
                            modifier = Modifier.align(Alignment.CenterVertically),
                            color = if (pagerState.currentPage == index) Color.White else Color(
                                0xFF8BB8B2
                            ),
                            text = text
                        )
                        Spacer(modifier = Modifier.padding(4.dp))

                        Text(
                            modifier = Modifier
                                .padding(2.dp)
                                .drawBehind {
                                    drawCircle(
                                        color = Color.White,
                                        radius = 30f
                                    )
                                },
                            text = "6",
                            color = Color(0xFF008065)
                        )

                    }

                })
        }
    }
}


@ExperimentalPagerApi
@Composable
fun TabsContent(pagerState: PagerState) {
    HorizontalPager(state = pagerState) { page ->
        when (page) {
            0 -> ChatTabContent()
            1 -> StatusTabContent()
            2 -> CallTabContent()
        }

    }
}