// swift-tools-version: 5.6
// The swift-tools-version declares the minimum version of Swift required to build this package.

import PackageDescription

var package = Package(
    name: "JishoPackage",
    defaultLocalization: "en",
    platforms: [
        .iOS(.v15),
    ],
    products: [],
    dependencies: [
        .package(url: "https://github.com/firebase/firebase-ios-sdk.git", from: "9.6.0"),
        .package(url: "https://github.com/pointfreeco/swift-composable-architecture", from: "0.40.2"),
        .package(url: "https://github.com/cybozu/LicenseList", from: "0.1.5"),
        .package(url: "https://github.com/onevcat/Kingfisher", from: "7.3.2"),
    ],
    targets: [
        .binaryTarget(
            name: "shared",
            path: "../shared/build/XCFrameworks/debug/shared.xcframework"
        ),
        .plugin(
            name: "SwiftLintPlugin",
            capability: .buildTool(),
            dependencies: [
                .target(name: "SwiftLintBinary"),
            ]
        ),
        .plugin(
            name: "SwiftLintCommandPlugin",
            capability: .command(intent: .custom(verb: "swiftlint",
                                                 description: "Enforce Swift style and conventions")),
            dependencies: ["SwiftLintBinary"]),
        .plugin(
            name: "SwiftGenPlugin",
            capability: .buildTool(),
            dependencies: [
                .target(name: "swiftgen"),
            ]
        ),
        .plugin(
            name: "LicenseListPlugin",
            capability: .buildTool(),
            dependencies: [
                .target(name: "licenselist")
            ]
        ),
        .binaryTarget(
            name: "SwiftLintBinary",
            url: "https://github.com/realm/SwiftLint/releases/download/0.48.0/SwiftLintBinary-macos.artifactbundle.zip",
            checksum: "9c255e797260054296f9e4e4cd7e1339a15093d75f7c4227b9568d63edddba50"
        ),
        .binaryTarget(
          name: "swiftgen",
          url: "https://github.com/SwiftGen/SwiftGen/releases/download/6.6.2/swiftgen-6.6.2.artifactbundle.zip",
          checksum: "7586363e24edcf18c2da3ef90f379e9559c1453f48ef5e8fbc0b818fbbc3a045"
        ),
        .binaryTarget(
            name: "licenselist",
            url: "https://github.com/touyou/LicenseList/releases/download/0.1.5/licenselist.artifactbundle.zip",
            checksum: "02d1b096c60dd0a4f3ff67a6ec82d801c6a609867fc84aa9ad40d00b42395417"
        )
    ]
)

// Append common plugins
package.targets = package.targets.map { target -> Target in
    if target.type == .regular || target.type == .test {
        if target.plugins == nil {
            target.plugins = []
        }
        target.plugins?.append(.plugin(name: "SwiftLintPlugin"))
    }

    return target
}
