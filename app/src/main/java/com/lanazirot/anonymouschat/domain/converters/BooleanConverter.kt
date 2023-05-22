package com.lanazirot.anonymouschat.domain.converters

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class BooleanConverterFactory : Converter.Factory() {
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        return if (type == Boolean::class.java) {
            BooleanConverter()
        } else {
            null
        }
    }

    private class BooleanConverter : Converter<ResponseBody, Boolean> {
        override fun convert(value: ResponseBody): Boolean {
            return value.string().toBoolean()
        }
    }
}
