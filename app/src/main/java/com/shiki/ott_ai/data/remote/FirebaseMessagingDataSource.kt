package com.shiki.ott_ai.data.remote


import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume


class FirebaseMessagingDataSource @Inject constructor() {
    suspend fun getToken(): Result<String> = suspendCancellableCoroutine { cont ->
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    cont.resume(Result.success(task.result))
                } else {
                    cont.resume(Result.failure(task.exception ?: Exception("Unknown error")))
                }
            }
    }

    fun onNewToken(callback: (String) -> Unit){

    }
}