package com.etno.microservice.util

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import java.io.FileInputStream
import java.io.IOException

fun firebaseMessaging(): FirebaseMessaging {
    val serviceAccount = FileInputStream(Urls.urlSourceFirebaseCredential)

    try {
        val options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .build()

        if(FirebaseApp.getApps().isEmpty()){
            val app = FirebaseApp.initializeApp(options)
            return FirebaseMessaging.getInstance(app)
        }

    }catch (_: IOException){  }
    return FirebaseMessaging.getInstance()
}
