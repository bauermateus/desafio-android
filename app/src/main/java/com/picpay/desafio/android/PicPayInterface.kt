package com.picpay.desafio.android

import retrofit2.Response

interface PicPayInterface {
    suspend fun getAllUsers(): Response<List<User>>
}
