package com.example.gooru.core.provide

interface DownloadProvider {

    fun downloadFile(url: String, fileName: String)
}