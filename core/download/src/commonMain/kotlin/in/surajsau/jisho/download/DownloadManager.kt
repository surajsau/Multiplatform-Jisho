package `in`.surajsau.jisho.download

import org.koin.core.scope.Scope

interface DownloadManager {
    fun checkIfDatabaseExists(): Boolean
    suspend fun downloadFile(): FileStatus
    suspend fun extractFile(): FileStatus
}

expect fun Scope.downloadManager(): DownloadManager