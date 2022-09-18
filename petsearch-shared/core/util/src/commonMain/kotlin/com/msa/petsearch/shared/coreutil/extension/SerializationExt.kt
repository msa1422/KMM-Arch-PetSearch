package com.msa.petsearch.shared.coreutil.extension

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okio.ByteString.Companion.decodeBase64
import okio.ByteString.Companion.encodeUtf8

// Strings are Base64 encoded because, non encoded json can be a problem while navigating with Uri
// for example, when json contains an image url

inline fun <reified T> T.encodeToString() =
    Json.encodeToString(this).encodeUtf8().base64()

inline fun <reified T> String.decodeFromString() =
    Json.decodeFromString<T>(this.decodeBase64()?.utf8() ?: this)
