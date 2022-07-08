package com.example.nicklefox.service

class DataWrapper<out T>(val status: Status, val data: T?, val message: String?, val statusCode:Int=0) {

    enum class Status {
        SUCCESS,
        ERROR
    }

    companion object {
        fun <T> success(data: T? = null): DataWrapper<T> {
            return DataWrapper(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T? = null): DataWrapper<T> {
            return DataWrapper(Status.ERROR, data, message)
        }

        fun <T> error(statusCode: Int,message: String, data: T? = null): DataWrapper<T> {
            return DataWrapper(Status.ERROR, data, message,statusCode)
        }
    }
}