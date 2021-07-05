package com.example.smsmanager.responses

data class UsersDetails (var details : String)

data class LoginDetails(var details: DetailsData)

data class DetailsData(
    var access_token :String,
    var expires_in :Long,
    var token_type :String,
    var jwt :String
)