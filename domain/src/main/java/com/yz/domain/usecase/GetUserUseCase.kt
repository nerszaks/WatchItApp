package com.yz.domain.usecase

import com.yz.domain.model.User
import com.yz.domain.repository.UsersRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {

    suspend operator fun invoke(userName: String): Result<User> {
        return usersRepository.getUserByName(userName)
    }

}