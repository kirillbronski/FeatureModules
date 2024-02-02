package com.kbcoding.featuremodules.glue.orders.mappers

import com.kbcoding.orders.domain.entities.Recipient
import com.kbcoding.data.orders.entities.RecipientDataEntity
import javax.inject.Inject

class RecipientMapper @Inject constructor() {

    fun toRecipientDataEntity(recipient: Recipient): RecipientDataEntity {
        return RecipientDataEntity(
            firstName = recipient.firstName,
            lastName = recipient.lastName,
            address = recipient.address,
        )
    }
}