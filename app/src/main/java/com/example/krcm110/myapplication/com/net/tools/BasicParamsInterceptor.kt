package com.example.krcm110.myapplication.com.net.tools

import android.text.TextUtils
import okhttp3.*
import okio.Buffer
import java.io.IOException
import java.util.ArrayList
import java.util.HashMap

public class BasicParamsInterceptor:Interceptor {
    internal var queryParamsMap: MutableMap<String, String> = HashMap()
    internal var paramsMap: MutableMap<String, String> = HashMap()
    internal var headerParamsMap: MutableMap<String, String>? = HashMap()
    internal var headerLinesList: MutableList<String> = ArrayList()


    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        var request: Request? = chain.request()
        val requestBuilder = request!!.newBuilder()

        // process header params inject
        if (headerParamsMap != null && headerParamsMap!!.size > 0) {
            val keys = headerParamsMap!!.keys
            for (headerKey in keys) {
                requestBuilder.addHeader(headerKey, headerParamsMap!![headerKey]).build()
            }
        }

        val headerBuilder = request.headers().newBuilder()
        if (headerLinesList.size > 0) {
            for (line in headerLinesList) {
                headerBuilder.add(line)
            }
            requestBuilder.headers(headerBuilder.build())
        }
        // process header params end


        // process queryParams inject whatever it's GET or POST
        if (queryParamsMap.size > 0) {
            request = injectParamsIntoUrl(request.url().newBuilder(), requestBuilder, queryParamsMap)
        }

        // process post body inject
        if (paramsMap.size > 0) {
            if (canInjectIntoBody(request)) {
                val formBodyBuilder = FormBody.Builder()
                for ((key, value) in paramsMap) {
                    formBodyBuilder.add(key, value)
                }

                val formBody = formBodyBuilder.build()
                var postBodyString = bodyToString(request?.body())
                postBodyString += (if (postBodyString.length > 0) "&" else "") + bodyToString(formBody)
                requestBuilder.post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8"), postBodyString))
            }
        }

        request = requestBuilder.build()
        return chain.proceed(request!!)
    }

    private fun canInjectIntoBody(request: Request?): Boolean {
        if (request == null) {
            return false
        }
        if (!TextUtils.equals(request.method(), "POST")) {
            return false
        }
        val body = request.body() ?: return false
        val mediaType = body.contentType() ?: return false
        return if (!TextUtils.equals(mediaType.subtype(), "x-www-form-urlencoded")) {
            false
        } else true
    }

    // func to inject params into url
    private fun injectParamsIntoUrl(httpUrlBuilder: HttpUrl.Builder, requestBuilder: Request.Builder, paramsMap: Map<String, String>): Request? {
        if (paramsMap.size > 0) {
            val iterator = paramsMap.entries.iterator()
            while (iterator.hasNext()) {
                val entry = iterator.next() as java.util.Map.Entry<*, *>
                httpUrlBuilder.addQueryParameter(entry.key as String, entry.value as String)
            }
            requestBuilder.url(httpUrlBuilder.build())
            return requestBuilder.build()
        }

        return null
    }

    private fun bodyToString(request: RequestBody?): String {
        try {
            val buffer = Buffer()
            if (request != null)
                request.writeTo(buffer)
            else
                return ""
            return buffer.readUtf8()
        } catch (e: IOException) {
            return "did not work"
        }

    }

    class Builder {

        internal var interceptor: BasicParamsInterceptor

        init {
            interceptor = BasicParamsInterceptor()
        }

        fun addParam(key: String, value: String): Builder {
            interceptor.paramsMap.put(key, value)
            return this
        }

        fun addParamsMap(paramsMap: Map<String, String>): Builder {
            interceptor.paramsMap.putAll(paramsMap)
            return this
        }

        fun addHeaderParam(key: String, value: String): Builder {
            interceptor.headerParamsMap!!.put(key, value)
            return this
        }

        fun addHeaderParamsMap(headerParamsMap: Map<String, String>): Builder {
            interceptor.headerParamsMap!!.putAll(headerParamsMap)
            return this
        }

        fun addHeaderLine(headerLine: String): Builder {
            val index = headerLine.indexOf(":")
            if (index == -1) {
                throw IllegalArgumentException("Unexpected header: $headerLine")
            }
            interceptor.headerLinesList.add(headerLine)
            return this
        }

        fun addHeaderLinesList(headerLinesList: List<String>): Builder {
            for (headerLine in headerLinesList) {
                val index = headerLine.indexOf(":")
                if (index == -1) {
                    throw IllegalArgumentException("Unexpected header: $headerLine")
                }
                interceptor.headerLinesList.add(headerLine)
            }
            return this
        }

        fun addQueryParam(key: String, value: String): Builder {
            interceptor.queryParamsMap.put(key, value)
            return this
        }

        fun addQueryParamsMap(queryParamsMap: Map<String, String>): Builder {
            interceptor.queryParamsMap.putAll(queryParamsMap)
            return this
        }

        fun build(): BasicParamsInterceptor {
            return interceptor
        }

    }
}
