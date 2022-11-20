package `in`.surajsau.jisho.download

import org.koin.core.scope.Scope

class IosDownloadManager : DownloadManager {
    override fun checkIfDatabaseExists(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun downloadFile(): FileStatus {
        TODO("Not yet implemented")
    }

    override suspend fun extractFile(): FileStatus {
        TODO("Not yet implemented")
    }
}

actual fun Scope.downloadManager(): DownloadManager = IosDownloadManager()