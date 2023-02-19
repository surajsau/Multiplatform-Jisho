//
//  Logger.swift
//  
//
//  Created by surajsau on 2023/02/19.
//

import Foundation
import os

/// `os.Logger` を活用したLogger
///
/// - https://developer.apple.com/documentation/os/logger
/// -
///
/// Examples
/// ========
///
///     import Foundation
///     import os
///
///     final class FetchTeamsInteractor: FetchTeamsUseCase {
///         private static let logger = Logger.logger(category: String(describing: FetchTeamsInteractor.self))
///
///         func execute() async throws -> [Team] {
///             Self.logger.trace(message: "")
///             ...
///         }
///     }
///

public extension Logger {
    /// デフォルトのsubsystem
    static var defaultSubsystem: String {
        Bundle.main.bundleIdentifier!
    }

    /// Appで共通のLoggerのインスタンス
    static var `default`: Logger {
        Logger(subsystem: defaultSubsystem, category: "Epos-biz")
    }

    /// categoryを指定して Logger をインスタンス化
    static func logger(category: String) -> Logger {
        Logger(subsystem: defaultSubsystem, category: category)
    }

    /// OSバージョン、Appのバージョン情報をログに書き込む
    /// - Parameter additionalLogs: 追加したいログ。末尾に追加される。
    static func printAppInfo(additionalLogs: [(name: String, value: String)] = []) {
        let separator = String(repeating: "*", count: 60)
        Logger.default.info("\(separator)")
        Logger.default.info("Bundle ID: \(Bundle.main.bundleIdentifier!, privacy: .public)")
        Logger.default.info("iOS: \(ProcessInfo().operatingSystemVersionString, privacy: .public)")

        if let shortVersion = Bundle.main.infoDictionary?["CFBundleShortVersionString"] as? String,
           let bundleVersion = Bundle.main.infoDictionary?["CFBundleVersion"] as? String
        { // swiftlint:disable:this opening_brace
            Logger.default.info("App: \(shortVersion, privacy: .public) (\(bundleVersion, privacy: .public))")
        } else {
            Logger.default.fault(message: "CFBundleShortVersionString or CFBundleVersion is nil.")
            fatalError("[Logger] CFBundleShortVersionString or CFBundleVersion is nil.")
        }

        additionalLogs.forEach { item in
            Logger.default.info("\(item.name): \(item.value)")
        }

        Logger.default.info("\(separator)")
    }

    // MARK: - wrapper functions

    /// デバッグメッセージをログに書き込む。`Logger.debug()` の wrapper
    ///
    /// - `OSLogPrivacy`が `public` になる点に注意
    /// - 揮発性が高いため、Console.app では随時ログが消滅する
    /// - ref: https://developer.apple.com/documentation/os/logger/3551615-debug
    func debug(message: String, function: String = #function, line: Int = #line) {
        #if DEBUG
        debug("🔍 \(function, privacy: .public) L:\(line, privacy: .public) | \(message, privacy: .public)")
        #endif
    }

    /// トレースメッセージをログに書き込む。`Logger.trace()` の wrapper
    ///
    /// - `OSLogPrivacy`が `public` になる点に注意
    /// - ref: https://developer.apple.com/documentation/os/logger/3551624-trace
    func trace(message: String, function: String = #function, line: Int = #line, file: String = #file) {
        trace("\(function, privacy: .public) L:\(line, privacy: .public) | \(message, privacy: .public)")
    }

    /// 有益なメッセージをログに書き込む。`Logger.info()` の wrapper
    ///
    /// - `OSLogPrivacy`が `public` になる点に注意
    /// - ref: https://developer.apple.com/documentation/os/logger/3551618-info
    func info(message: String, function: String = #function, line: Int = #line, file: String = #file) {
        info("❕ \(function, privacy: .public) L:\(line, privacy: .public) | \(message, privacy: .public)")
    }

    /// エラーに関する情報をログに書き込む。`Logger.error()` の wrapper
    ///
    /// - `OSLogPrivacy`が `public` になる点に注意
    /// - ref: https://developer.apple.com/documentation/os/logger/3551616-error
    func error(message: String, function: String = #function, line: Int = #line, file: String = #file) {
        error("‼️ \(function, privacy: .public) L:\(line, privacy: .public) | \(message, privacy: .public)")
    }

    /// Appの実行時に発生するバグに関するメッセージをログに書き込む。`Logger.fault()` の wrapper
    ///
    /// - `OSLogPrivacy`が `public` になる点に注意
    /// - ref: https://developer.apple.com/documentation/os/logger/3551617-fault
    func fault(message: String, function: String = #function, line: Int = #line, file: String = #file) {
        fault("🚨 \(function, privacy: .public) L:\(line, privacy: .public) | \(message, privacy: .public)")
    }

    /// Appの実行における重大なイベントに関するメッセージをログに書き込む。`Logger.critical()` の wrapper
    ///
    /// - `OSLogPrivacy`が `public` になる点に注意
    /// - ref: https://developer.apple.com/documentation/os/logger/3551614-critical
    func critical(message: String, function: String = #function, line: Int = #line, file: String = #file) {
        critical("🔥 \(function, privacy: .public) L:\(line, privacy: .public) | \(message, privacy: .public)")
    }
}

#if canImport(FirebaseCrashlytics)
/// Crashlytics.record() に渡すError型
private struct CrashlyticsError: Error, CustomStringConvertible {
    let message: String
    let file: String
    let function: String
    let line: Int
    let code = 9999

    var description: String {
        "\(function) L:\(line) | \(message)"
    }

    /// NSErrorを返す
    /// ref. https://firebase.google.com/docs/crashlytics/customize-crash-reports?hl=ja&platform=ios#log-excepts
    var nsError: NSError {
        NSError(
            domain: "\(file.lastPathComponentWithoutPathExtension).\(function).\(line)",
            code: code,
            userInfo: [
                NSLocalizedDescriptionKey: message,
                "function": function,
                "line": line,
            ]
        )
    }
}
#endif

