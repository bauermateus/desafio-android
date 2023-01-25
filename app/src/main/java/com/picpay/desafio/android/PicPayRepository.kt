package com.picpay.desafio.android

import retrofit2.Response

object PicPayRepository: PicPayInterface {
    override suspend fun getAllUsers(): Response<List<User>> {
            return RetrofitService().api.getUsers()
    }
}