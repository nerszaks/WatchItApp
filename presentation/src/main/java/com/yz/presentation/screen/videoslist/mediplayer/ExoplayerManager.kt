package com.yz.presentation.screen.videoslist.mediplayer

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import com.yz.presentation.screen.videoslist.PlayerManager
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class ExoplayerManager(context: Context) : PlayerManager<PlayerView> {

    private val player: SimpleExoPlayer

    init {
        trustAllCertificates()
        player = SimpleExoPlayer.Builder(context)
            .build()
    }

    @SuppressLint("CustomX509TrustManager", "TrustAllX509TrustManager")
    private fun trustAllCertificates() {
        val trustAllCerts: Array<TrustManager> = arrayOf<TrustManager>(object : X509TrustManager {

            override fun getAcceptedIssuers(): Array<X509Certificate>? = null

            override fun checkClientTrusted(certs: Array<X509Certificate?>?, authType: String?) {
            }

            override fun checkServerTrusted(certs: Array<X509Certificate?>?, authType: String?) {
            }

        })

        try {
            val sc: SSLContext = SSLContext.getInstance("TLS")
            sc.init(null, trustAllCerts, SecureRandom())
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory())
        } catch (e: KeyManagementException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

    }

    override fun play(view: PlayerView, url: String, seekTo: Long) {
        view.player = player
        view.player?.apply {
            val mediaItem = MediaItem.fromUri(url)
            setMediaItem(mediaItem)
            playWhenReady = true
            seekTo(seekTo)
            prepare()
        }
    }

    override fun pause() {
        player.pause()
    }

    override fun resume() {
        player.play()
    }

    override fun getCurrentPosition(): Long {
        return player.currentPosition
    }

    override fun release() {
        player.release()
    }

}