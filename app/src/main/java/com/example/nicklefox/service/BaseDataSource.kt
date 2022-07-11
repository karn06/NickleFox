package com.example.nicklefox.service

import android.util.Log
import com.example.nicklefox.data_holder.ErrorResponse
import com.google.gson.Gson
import retrofit2.HttpException
import retrofit2.Response
import java.io.EOFException
import java.net.HttpURLConnection
import javax.net.ssl.HttpsURLConnection

abstract class BaseDataSource {

    protected suspend fun <T> execute(call: suspend () -> Response<T>): DataWrapper<T> {
        val response: Response<T>?
        try {
            response = call()
            if (response.isSuccessful) {
                val body = response.body()
                return DataWrapper.success(body)
            }

            response.errorBody()?.let {
                when (response.code()) {
                    HttpsURLConnection.HTTP_UNAUTHORIZED -> {
                        return error(response.code(), "Access Denied")
                    }
                    HttpsURLConnection.HTTP_INTERNAL_ERROR -> {
                        val errorResponse: ErrorResponse?
                        return try {
                            errorResponse =
                                Gson().fromJson(it.charStream(), ErrorResponse::class.java)
                            error(response.code(), errorResponse.error.message)
                        } catch (e: java.lang.Exception) {
                            e.printStackTrace()
                            error(response.code(), "Internal Server Error")
                        }
                    }
                    HttpsURLConnection.HTTP_BAD_REQUEST -> {
                        val errorResponse: ErrorResponse?
                        return try {
                            errorResponse =
                                Gson().fromJson(it.charStream(), ErrorResponse::class.java)
                            // Send specific information about the error
                            error(
                                response.code(),
                                "Bad Request: ${errorResponse.error.errors[0].message}"
                            )
                        } catch (e: java.lang.Exception) {
                            error(response.code(), "Bad Request")
                        }
                    }
                    HttpsURLConnection.HTTP_CLIENT_TIMEOUT -> {
                        return error(response.code(), "Client Timeout")
                    }
                    HttpsURLConnection.HTTP_FORBIDDEN -> {
                        return error(response.code(), "Forbidden")
                    }
                    HttpsURLConnection.HTTP_GATEWAY_TIMEOUT -> {
                        return error(response.code(), "Gateway Timeout")
                    }
                    HttpsURLConnection.HTTP_CONFLICT -> {
                        return error(response.code(), "Conflict")
                    }
                    else -> {
                        return error(response.code(), "Unknown Error")
                    }
                }
            }
            return error(response.code(), response.message())
        } catch (e: HttpException) {
            e.printStackTrace()
            Log.e("BaseDataSource", "HttpException occurred")
            return error(HttpURLConnection.HTTP_VERSION, e.message ?: e.toString())
        } catch (e: EOFException) {
            e.printStackTrace()
            Log.e("BaseDataSource", "Exception occurred")
            return error(HttpURLConnection.HTTP_NO_CONTENT, e.message ?: e.toString())
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("BaseDataSource", "Exception occurred")
            return error(HttpURLConnection.HTTP_INTERNAL_ERROR, e.message ?: e.toString())
        }

    }

    /**
     * Wrap the error to be sent into [DataWrapper].
     */
    private fun <T> error(statusCode: Int, message: String): DataWrapper<T> {
        return DataWrapper.error(statusCode, message)
    }

    /**
     * Wrap the error to be sent into [DataWrapper].
     * This method is used to send data along with the error message.
     * This is an overloaded version of method [error].
     */
    private fun <T> error(statusCode: Int, message: String, data: T?): DataWrapper<T> {
        return DataWrapper.error(statusCode, message, data)
    }

}