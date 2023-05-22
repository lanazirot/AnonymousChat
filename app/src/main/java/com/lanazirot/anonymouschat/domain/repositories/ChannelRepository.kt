package com.lanazirot.anonymouschat.domain.repositories;

import com.lanazirot.anonymouschat.domain.models.api.AddMemberToChannelDTO
import com.lanazirot.anonymouschat.domain.models.api.CreateChannelDTO
import com.lanazirot.anonymouschat.domain.services.interfaces.api.IChannelAPI;
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class ChannelRepository @Inject constructor(
    private val channelAPI: IChannelAPI
) {
    //ES NECESARIO???
    //Crear canal
    //Agregar miembro al canal
    //Eliminar canal
}