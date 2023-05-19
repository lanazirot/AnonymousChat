package com.lanazirot.anonymouschat.ui.screens.chat.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.getstream.chat.android.compose.viewmodel.messages.AttachmentsPickerViewModel
import io.getstream.chat.android.compose.viewmodel.messages.MessageComposerViewModel
import io.getstream.chat.android.compose.viewmodel.messages.MessageListViewModel

@Composable
fun MessageMaker(
    composerViewModel: MessageComposerViewModel,
    listViewModel: MessageListViewModel,
    attachmentsPickerViewModel: AttachmentsPickerViewModel,
) {
    io.getstream.chat.android.compose.ui.messages.composer.MessageComposer(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        viewModel = composerViewModel,
        onAttachmentsClick = { attachmentsPickerViewModel.changeAttachmentState(true) },
        onCommandsClick = { composerViewModel.toggleCommandsVisibility() },
        onCancelAction = {
            listViewModel.dismissAllMessageActions()
            composerViewModel.dismissMessageActions()
        }
    )
}