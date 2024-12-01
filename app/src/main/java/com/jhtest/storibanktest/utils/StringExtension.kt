package com.jhtest.storibanktest.utils

import java.util.regex.Pattern
import java.util.regex.Pattern.CASE_INSENSITIVE

val String.Companion.Empty
    inline get() = ""

fun String.isEmailValid(): Boolean {
    val emailRegex = "^[A-Za-z0-9](.*)(@)(.+)(\\.)(.+)"
    return emailRegex.toRegex().matches(this)
}

fun String.containsNoEmojis(): Boolean {
    val regex = "\\p{So}+"
    return Pattern.compile(regex, CASE_INSENSITIVE).matcher(this).find().not()
}

fun String.isValidLength(
    minLength: Int,
    maxLength: Int
): Boolean {
    return this.length in minLength..maxLength
}