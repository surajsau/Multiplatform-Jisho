package `in`.surajsau.jisho.download

import org.koin.core.scope.Scope

class IosDownloadManager : DownloadManager {

    override fun checkIfDatabaseExists(): Boolean {

    }

    override suspend fun downloadFile(): FileStatus {

    }

    override suspend fun extractFile(): FileStatus {

    }
}

actual fun Scope.downloadManager(): DownloadManager = IosDownloadManager()