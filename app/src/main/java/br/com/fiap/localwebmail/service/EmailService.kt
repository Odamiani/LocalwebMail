package br.com.fiap.localwebmail.service

import br.com.fiap.localwebmail.model.Email
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EmailService {

    @GET("emails")
    fun getEmails() : Call<List<Email>>
}


