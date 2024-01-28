package com.kbcoding.featuremodules.glue.signUp.repositories

import com.kbcoding.data.AccountsDataRepository
import com.kbcoding.data.accounts.entities.SignUpDataEntity
import com.kbcoding.data.accounts.exceptions.AccountAlreadyExistsDataException
import com.kbcoding.signUp.presentation.domain.entities.SignUpData
import com.kbcoding.signUp.presentation.domain.exceptions.AccountAlreadyExistsException
import com.kbcoding.signUp.presentation.domain.repositories.SignUpRepository
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
    private val accountsRepository: AccountsDataRepository
) : SignUpRepository {

    override suspend fun signUp(signUpData: SignUpData) {
        try {
            accountsRepository.signUp(
                SignUpDataEntity(
                    email = signUpData.email,
                    username = signUpData.username,
                    password = signUpData.password,
                )
            )
        } catch (e: AccountAlreadyExistsDataException) {
            throw AccountAlreadyExistsException(e)
        }
    }

}