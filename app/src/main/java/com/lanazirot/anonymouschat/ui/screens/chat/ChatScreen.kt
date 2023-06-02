package com.lanazirot.anonymouschat.ui.screens.chat

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.lanazirot.anonymouschat.ui.navigator.routes.DrawerScreens
import com.lanazirot.anonymouschat.ui.providers.GlobalProvider
import com.lanazirot.anonymouschat.ui.screens.chat.components.ChatHeader
import com.lanazirot.anonymouschat.ui.screens.chat.components.MessageMaker
import io.getstream.chat.android.common.state.MessageFooterVisibility
import io.getstream.chat.android.common.state.MessageMode
import io.getstream.chat.android.compose.ui.messages.list.MessageList
import io.getstream.chat.android.compose.ui.theme.ChatTheme
import io.getstream.chat.android.compose.viewmodel.messages.AttachmentsPickerViewModel
import io.getstream.chat.android.compose.viewmodel.messages.MessageComposerViewModel
import io.getstream.chat.android.compose.viewmodel.messages.MessageListViewModel
import io.getstream.chat.android.compose.viewmodel.messages.MessagesViewModelFactory

@Composable
fun ChatScreen(channelId : String) {
    val context = LocalContext.current
    val navController = GlobalProvider.current.navController
    val chatViewModel: ChatViewModel = hiltViewModel()

    val chatState = chatViewModel.chatState.collectAsState().value

    LaunchedEffect(Unit) {
        chatViewModel.initChat(channelId)
    }

    LaunchedEffect(chatState) {
        if (!chatState.alive) {
            navController.popBackStack()
        }
    }

    val factory by lazy {
        MessagesViewModelFactory(
            context = context,
            channelId = channelId,
            showDateSeparators = false,
            showSystemMessages = false,
            messageFooterVisibility = MessageFooterVisibility.LastInGroup
        )
    }

    val listViewModel = viewModel(MessageListViewModel::class.java, factory = factory)
    val composerViewModel = viewModel(MessageComposerViewModel::class.java, factory = factory)
    val attachmentsPickerViewModel =
        viewModel(AttachmentsPickerViewModel::class.java, factory = factory)

    val backAction = {
        val isInThread = listViewModel.isInThread
        val isShowingOverlay = listViewModel.isShowingOverlay

        when {
            attachmentsPickerViewModel.isShowingAttachments -> attachmentsPickerViewModel.changeAttachmentState(
                false
            )
            isShowingOverlay -> listViewModel.selectMessage(null)
            isInThread -> {
                listViewModel.leaveThread()
                composerViewModel.leaveThread()
            }
            else -> onBackPressed(navController = navController)
        }
    }

    ChatTheme {
        Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.primaryVariant)) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    ChatHeader(
                        channel = listViewModel.channel,
                        onBack = {
                            backAction()
                        },
                    )
                },
                bottomBar = {
                    MessageMaker(
                        composerViewModel = composerViewModel,
                        listViewModel = listViewModel,
                        attachmentsPickerViewModel = attachmentsPickerViewModel
                    )
                },
            ) {
                MessageList(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colors.primaryVariant)
                        .padding(it),
                    viewModel = listViewModel,
                    onThreadClick = { message ->
                        composerViewModel.setMessageMode(MessageMode.MessageThread(message))
                        listViewModel.openMessageThread(message)
                    },
                )
            }
        }
    }

    BackHandler(enabled = true, onBack = backAction)
}

private fun onBackPressed(navController : NavHostController) {
    navController.popBackStack()
    navController.navigate(DrawerScreens.Main.route)
}