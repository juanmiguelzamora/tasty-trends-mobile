package com.roydev.tastytrends

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @Headers("Content-Type: application/json")

    @POST("tasters/register")
    suspend fun register(@Body registerReq: RegisterReq): Response<RegisterRes>

    @POST("tasters/login")
    suspend fun login(@Body request: LoginReq): LoginRes

    @GET("tickets/{id}")
    suspend fun getTicket(@Path("id") ticketId: String): TicketRes

    @GET("items/indexShopItems/{id}")
    suspend fun getShopItems(@Path("id") s: String): Response<List<ShopItem>>







    /*
    // Comment out until I add DeleteRes and UpdateTicketREs
    // Example of a DELETE request
    @DELETE("tickets/{id}")
    suspend fun deleteTicket(@Path("id") ticketId: String): DeleteRes

    // Example of a PUT request
    @PUT("tickets/{id}")
    suspend fun updateTicket(@Path("id") ticketId: String, @Body request: UpdateTicketReq): UpdateTicketRes
     */
}
