package com.jhtest.storibanktest.framework.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.jhtest.storibanktest.data.datasources.FirebaseAuthDataSource
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class FirebaseAuthDataSourceImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : FirebaseAuthDataSource {

    private val loginException = Exception("Error al iniciar sesión")
    private val signUpException = Exception("Error al crear el usuario")

    override suspend fun signInUser(email: String, password: String): FirebaseUser =
        suspendCoroutine { continuation ->
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Inicio de sesión exitoso
                        val user = firebaseAuth.currentUser
                        user?.let {
                            continuation.resume(task.result.user!!)
                        } ?: run {
                            continuation.resumeWithException(loginException)
                        }
                    } else {
                        continuation.resumeWithException(loginException)
                    }
                }
        }

    override suspend fun signUpUser(
        email: String,
        password: String
    ): FirebaseUser = suspendCoroutine { continuation ->
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    user?.let {
                        continuation.resume(it)
                    } ?: run {
                        continuation.resumeWithException(signUpException)
                    }
                } else {
                    continuation.resumeWithException(loginException)
                }
            }
    }
}