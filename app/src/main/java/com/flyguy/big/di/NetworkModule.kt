package com.flyguy.big.di

import com.flyguy.big.config.AppConfig
import com.flyguy.big.data.network.ApiService
import com.flyguy.big.data.network.AuthService
import com.flyguy.big.data.network.TokenAuthenticator
import com.flyguy.big.data.network.TokenManager
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.Proxy
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.Collections
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BusinessRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthRetrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideHeaderInterceptor(tokenManager: TokenManager): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val requestBuilder = originalRequest.newBuilder()
                .header("User-Agent", AppConfig.userAgent)
                .header("X-App-Build", AppConfig.appBuildHeader)
                .header("X-App-Language", "zh-chs")
                .header("X-App-Platform", "Android")
                .header("X-App-Device-Id", AppConfig.getDeviceUuid())

            val noAuth = originalRequest.header("No-Auth")
            if (noAuth == null) {
                tokenManager.accessToken?.let {
                    requestBuilder.header("Authorization", "Bearer $it")
                }
            } else {
                requestBuilder.removeHeader("No-Auth")
            }

            chain.proceed(requestBuilder.build())
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        headerInterceptor: Interceptor,
        authenticator: TokenAuthenticator
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()

        builder.connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .protocols(Collections.singletonList(Protocol.HTTP_1_1))
            .addInterceptor(headerInterceptor)
            .addInterceptor(loggingInterceptor)
            .authenticator(authenticator)

        if (AppConfig.isDebug) {
            try {
                val trustAllCerts = object : X509TrustManager {
                    override fun checkClientTrusted(
                        chain: Array<X509Certificate>,
                        authType: String
                    ) {
                    }

                    override fun checkServerTrusted(
                        chain: Array<X509Certificate>,
                        authType: String
                    ) {
                    }

                    override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
                }
                val sslContext = SSLContext.getInstance("TLS")
                sslContext.init(null, arrayOf<TrustManager>(trustAllCerts), SecureRandom())
                builder.sslSocketFactory(sslContext.socketFactory, trustAllCerts)
                builder.hostnameVerifier { _, _ -> true }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else {
            builder.proxy(Proxy.NO_PROXY)
        }

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(GsonBuilder().setLenient().create())
    }

    @Provides
    @Singleton
    @BusinessRetrofit
    fun provideBusinessRetrofit(
        okHttpClient: OkHttpClient,
        gsonFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppConfig.baseUrl)
            .client(okHttpClient)
            .addConverterFactory(gsonFactory)
            .build()
    }

    @Provides
    @Singleton
    @AuthRetrofit
    fun provideAuthRetrofit(
        okHttpClient: OkHttpClient,
        gsonFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppConfig.authBaseUrl)
            .client(okHttpClient)
            .addConverterFactory(gsonFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(@BusinessRetrofit retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthService(@AuthRetrofit retrofit: Retrofit): AuthService {
        return retrofit.create(AuthService::class.java)
    }
}
