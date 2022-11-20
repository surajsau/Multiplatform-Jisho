//
//  DownloadManager.swift
//  iosApp
//
//  Created by Suraj GP on 24/04/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import FirebaseStorage
import Gzip

class DownloadManager: ObservableObject {
    
    static let ZipFileName = "jmdict.db.gz"
    
    private let storage = Storage.storage()
    
    @Published private(set) var doesDatabaseExist: Bool = false

    
    private let databaseFile = FileManager.default.urls(for: .applicationSupportDirectory, in: .userDomainMask)[0]
        .appendingPathComponent("databases")
        .appendingPathComponent("jmdict.db")
    
    private let downloadedFile = FileManager.default.urls(for: .documentDirectory, in: .userDomainMask)[0]
        .appendingPathComponent(DownloadManager.ZipFileName)
    
    func checkIfDatabaseExists() throws {
        print(self.databaseFile.path)
        self.doesDatabaseExist = try self.databaseFile.checkResourceIsReachable()
    }
    
    func download() async throws {
        let fileRef = storage.reference().child(DownloadManager.ZipFileName)
        
        return try await withCheckedThrowingContinuation { cont in
            fileRef.write(toFile: self.downloadedFile) { url, error in
                if let error = error {
                    cont.resume(throwing: error)
                } else {
                    cont.resume()
                }
            }
        }
    }
    
    func extract() async throws {
        return try await withCheckedThrowingContinuation { cont in
            do {
                let data = try Data(contentsOf: self.downloadedFile)
                let decompressedData: Data
                if data.isGzipped {
                    decompressedData = try data.gunzipped()
                } else {
                    decompressedData = data
                }
                
                try decompressedData.write(to: self.databaseFile)
                
                self.doesDatabaseExist = true
                
                cont.resume()
            } catch {
                cont.resume(throwing: error)
            }
        }
    }
}
