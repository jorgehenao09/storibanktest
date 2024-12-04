package com.jhtest.storibanktest.framework.firebase

import com.google.firebase.firestore.FirebaseFirestore
import com.jhtest.storibanktest.data.datasources.FirebaseFirestoreDataSource
import com.jhtest.storibanktest.data.models.UserData
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

private const val USER_COLLECTION = "users"

class FirebaseFirestoreDataSourceImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
) : FirebaseFirestoreDataSource {
    private val userStorageException = Exception("Error al guardar el usuario")
    private val userDataException = Exception("Error al obtener el usuario")

    override suspend fun saveUserData(
        userId: String,
        name: String,
        lastName: String,
        email: String,
        faceId: String
    ) {
        suspendCoroutine { continuation ->
            val userMap = hashMapOf(
                "name" to name,
                "lastName" to lastName,
                "email" to email,
                "faceId" to faceId
            )

            firebaseFirestore.collection(USER_COLLECTION).document(userId).set(userMap)
                .addOnSuccessListener {
                    continuation.resume(Unit)
                }
                .addOnFailureListener { e ->
                    continuation.resumeWithException(userStorageException)
                }
        }
    }

    override suspend fun getUserData(userId: String): UserData = suspendCoroutine { continuation ->
        val userDocRef = firebaseFirestore.collection(USER_COLLECTION).document(userId)
        userDocRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val userData = document.toObject(UserData::class.java)
                    if (userData != null) {
                        continuation.resume(userData)
                    } else {
                        continuation.resumeWithException(userDataException)
                    }
                } else {
                    continuation.resumeWithException(userDataException)
                }
            }
            .addOnFailureListener { exception ->
                continuation.resumeWithException(userDataException)
            }
    }
}