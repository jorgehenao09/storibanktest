package com.jhtest.storibanktest.framework.firebase

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.jhtest.storibanktest.data.datasources.FirebaseStorageDataSource
import com.jhtest.storibanktest.data.models.BankTransaction
import com.jhtest.storibanktest.data.models.BankTransactionBody
import com.jhtest.storibanktest.data.models.UserData
import com.jhtest.storibanktest.ui.viewmodels.SignUpUserInfo
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

private const val USER_COLLECTION = "users"
private const val TRANSACTION_COLLECTION = "movements"

class FirebaseStorageDataSourceImpl
    @Inject
    constructor(
        private val firebaseFirestore: FirebaseFirestore,
        private val firebaseStorage: FirebaseStorage,
    ) : FirebaseStorageDataSource {
        private val userStorageException = Exception("Error al guardar el usuario")
        private val userDataException = Exception("Error al obtener el usuario")
        private val userTransactionsException = Exception("Error al obtener las transacciones")

        override suspend fun saveUserData(signUpUserInfo: SignUpUserInfo) {
            suspendCoroutine { continuation ->
                val userMap =
                    hashMapOf(
                        "name" to signUpUserInfo.name,
                        "lastName" to signUpUserInfo.lastName,
                        "email" to signUpUserInfo.email,
                        "faceId" to signUpUserInfo.faceId.toString(),
                    )

                firebaseFirestore.collection(USER_COLLECTION).document(signUpUserInfo.userId)
                    .set(userMap)
                    .addOnSuccessListener {
                        val storageRef = firebaseStorage.reference
                        val profileImageRef =
                            storageRef.child("profileImages/${signUpUserInfo.userId}.jpg")
                        profileImageRef.putFile(signUpUserInfo.faceId!!)
                            .addOnSuccessListener {
                                continuation.resume(Unit)
                            }
                            .addOnFailureListener { e ->
                                continuation.resumeWithException(userStorageException)
                            }
                    }
                    .addOnFailureListener { e ->
                        continuation.resumeWithException(userStorageException)
                    }
            }
        }

        override suspend fun getUserData(userId: String): UserData =
            suspendCoroutine { continuation ->
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

        override suspend fun getUserTransactions(userId: String): BankTransactionBody =
            suspendCoroutine { continuation ->
                val transactionsCollectionRef =
                    firebaseFirestore
                        .collection(USER_COLLECTION)
                        .document(userId)
                        .collection(TRANSACTION_COLLECTION)

                transactionsCollectionRef.get()
                    .addOnSuccessListener { querySnapshot ->
                        if (!querySnapshot.isEmpty) {
                            val bankTransactionList =
                                querySnapshot.documents.mapNotNull { document ->
                                    document.toObject(BankTransaction::class.java)
                                }
                            continuation.resume(BankTransactionBody(bankTransactionList))
                        } else {
                            continuation.resumeWithException(userTransactionsException)
                        }
                    }
                    .addOnFailureListener { exception ->
                        continuation.resumeWithException(userTransactionsException)
                    }
            }
    }
