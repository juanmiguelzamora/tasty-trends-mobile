package com.roydev.tastytrends

data class RegisterReq(
    val username: String,
    val email: String,
    val password: String,
)

data class RegisterRes(
    val message: String
)

data class LoginReq(
    val email: String,
    val password: String
)

data class LoginRes(
    val token: String,
    val message: String
)

data class TicketReq(
    val shop_id: String,
    val buyer_id: String,
)

data class TicketRes(
    val message: String
)