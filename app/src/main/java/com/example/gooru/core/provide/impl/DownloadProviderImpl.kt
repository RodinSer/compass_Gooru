package com.example.gooru.core.provide.impl

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import com.example.gooru.core.provide.DownloadProvider
import java.io.File

class DownloadProviderImpl(context: Context) : DownloadProvider {

    private val downloadManager =
        context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

    override fun downloadFile(url: String, fileName: String) {

        val downloadRequest = DownloadManager
            .Request(Uri.parse(url))
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)//качать только по войфай или нет
            .setTitle("$FILE_NAME$fileName")
            .setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                File.separator + "$FILE_NAME$fileName"
            )
            .setNotificationVisibility(
                DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED
            ) // показать сообщение о скачивании

        downloadManager.enqueue(downloadRequest)
    }

    private companion object {
        const val FILE_NAME = "GooRu_"
    }
}