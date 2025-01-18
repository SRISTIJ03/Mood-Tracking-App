//package com.chelseatroy.canary.api
//
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.http.Body
//import retrofit2.http.POST
//
//class MockyAPIImplementation {
//
//    private val service: LoginService
//
//    companion object {
//        const val BASE_URL = "http://www.mocky.io/"
//    }
//
//    init {
//        val retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//        service = retrofit.create(LoginService::class.java)
//    }
//
//    suspend fun authenticate(email: String, password: String): CanarySession? {
//        // Prepare the login request
//        val loginRequest = LoginRequest(email, password)
//
//        // Make the network call and return the result
//        return try {
//            service.authenticate(loginRequest)  // Authenticate with the email and password
//        } catch (e: Exception) {
//            // Handle the error if needed, for now returning null
//            e.printStackTrace()
//            null
//        }
//    }
//}
