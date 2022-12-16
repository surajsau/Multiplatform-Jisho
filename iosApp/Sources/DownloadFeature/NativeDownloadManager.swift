//
//  NativeDownloadManager.swift
//  
//
//  Created by surajsau on 2022/12/11.
//

import download
import FirebaseStorage
import Gzip

public class NativeDownloadManager: DownloadManager {
    
    static let ZipFileName = "jmdict.db.gz"
        
    private let storage = Storage.storage()
    
    private let databaseFile = FileManager.default.urls(for: .documentDirectory, in: .userDomainMask)[0]
            .appendingPathComponent("databases")
            .appendingPathComponent("jmdict.db")
    
    private let downloadedFile = FileManager.default.urls(for: .documentDirectory, in: .userDomainMask)[0]
            .appendingPathComponent(NativeDownloadManager.ZipFileName)
    
    public init(){}

    public func checkIfDatabaseExists() -> Bool {
        let exists = try! self.databaseFile.checkResourceIsReachable()
        return exists
    }
    
    public func downloadFile(onCompletion: @escaping (FileStatus) -> Void) {
        let fileRef = storage.reference().child(NativeDownloadManager.ZipFileName)
        fileRef.write(toFile: downloadedFile) { url, error in
            if let error = error {
                onCompletion(FileStatusError(exception: KotlinException(message: error.localizedDescription)))
            } else {
                onCompletion(FileStatusDownloaded())
            }
        }
    }
    
    public func extractFile(onCompletion: @escaping (FileStatus) -> Void) {
        do {
            let data = try Data(contentsOf: self.downloadedFile)
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
