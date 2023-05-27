package com.lanazirot.anonymouschat.domain.services.interfaces.app

import kotlinx.coroutines.flow.Flow

interface ILocalStoreService {
    val getStreamTokenAuth : Flow<String>
    suspend fun setStreamTokenAuth(token: String)
}