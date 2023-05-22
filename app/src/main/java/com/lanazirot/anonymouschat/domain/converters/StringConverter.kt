package com.lanazirot.anonymouschat.domain.converters

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class StringConverter : Converter<ResponseBody, String> {
    override fun convert(value: ResponseBody): String? {
        return try {
            value.source().use { source ->
                source.readUtf8()
            }
        } catch (e: Exception) {
            null
        }
    }
}

class StringConverterFactory : Converter.Factory() {
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        if (type == String::class.java) {
            return StringConverter()
        }
        return null
    }
}
