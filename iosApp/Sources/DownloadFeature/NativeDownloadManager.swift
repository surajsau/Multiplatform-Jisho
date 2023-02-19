//
//  NativeDownloadManager.swift
//  
//
//  Created by surajsau on 2022/12/11.
//

import shared
import FirebaseStorage
import Gzip
import Utils
import os

public class NativeDownloadManager: DownloadManager {
    private let logger = Logger.logger(category: "NativeDownloadManager")

    static let ZipFileName = "jmdict.db.gz"

    private let storage = Storage.storage()

    private let databaseFile = FileManager.default.urls(for: .documentDirectory, in: .userDomainMask)[0]
            .appendingPathComponent("jmdict.db")

    private let downloadedFile = FileManager.default.urls(for: .documentDirectory, in: .userDomainMask)[0]
            .appendingPathComponent(NativeDownloadManager.ZipFileName)

    public init() {}

    public func checkIfDatabaseExists() -> Bool {
        let exists = try? self.databaseFile.checkResourceIsReachable()
        return exists ?? false
    }

    public func downloadFile(onCompletion: @escaping (FileStatus) -> Void) {
        let fileRef = storage.reference().child(NativeDownloadManager.ZipFileName)
        fileRef.write(toFile: downloadedFile) { _, error in
            if let error = error {
                onCompletion(FileStatusError(exception: KotlinException(message: error.localizedDescription)))
            } else {
                onCompletion(FileStatusDownloaded())
            }
        }
    }

    public func extractFile(onCompletion: @escaping (FileStatus) -> Void) {
        do {
            let data = try Data(contentsOf: downloadedFile)
            let decompressedData: Data
            if data.isGzipped {
                decompressedData = try data.gunzipped()
            } else {
                decompressedData = data
            }

            try decompressedData.write(to: self.databaseFile)
            onCompletion(FileStatusExtracted())
        } catch {
            onCompletion(FileStatusError(exception: KotlinException(message: error.localizedDescription)))
        }
    }

}
