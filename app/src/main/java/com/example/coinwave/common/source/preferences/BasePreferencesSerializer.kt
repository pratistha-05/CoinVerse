package com.example.coinwave.common.source.preferences

import android.util.Log
import androidx.datastore.core.Serializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

abstract class BasePreferencesSerializer<T>(
  private val defaultInstance: () -> T,
  private val serializer: KSerializer<T>,
) : Serializer<T> {

  override val defaultValue: T
    get() = defaultInstance()

  override suspend fun readFrom(input: InputStream): T {
    return try {
      Json.decodeFromString(
        deserializer = serializer,
        string = input.readBytes().decodeToString()
      )
    } catch (exception: SerializationException) {
      Log.e("Error serializing preferences with ${serializer.descriptor}", exception.toString())
      defaultValue
    }
  }

  override suspend fun writeTo(t: T, output: OutputStream) {
    output.write(
      Json.encodeToString(
        serializer = serializer,
        value = t
      ).encodeToByteArray()
    )
  }
}