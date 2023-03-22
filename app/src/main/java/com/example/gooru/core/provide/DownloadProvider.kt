package com.example.gooru.core.provide

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import java.io.File

interface DownloadProvider {
    fun downloadFile(url: String, fileName: String, format: String = "xlsx")
}

class DownloadProviderImpl( context: Context) : DownloadProvider {

    private val downloadManager =
        context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

    override fun downloadFile(url: String, fileName: String, format: String) {

        val downloadRequest = DownloadManager
            .Request(Uri.parse(url))
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)//качать только по войфай или нет
            .setTitle("GooRu_$fileName")
            .setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                File.separator + "GooRu_$fileName"
            )
            .setNotificationVisibility(
                DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED
            ) // показать сообщение о скачивании

        downloadManager.enqueue(downloadRequest)

    }
}