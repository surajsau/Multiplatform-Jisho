package `in`.surajsau.jisho.data.expected

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import `in`.surajsau.jisho.data.db.Jisho
import org.koin.core.scope.Scope

public actual fun Scope.createDriver(databaseName: String): SqlDriver {
    return NativeSqliteDriver(Jisho.Schema, databaseName)
}
