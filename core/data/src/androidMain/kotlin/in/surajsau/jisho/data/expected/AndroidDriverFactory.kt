package `in`.surajsau.jisho.data.expected

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import `in`.surajsau.jisho.data.db.Jisho
import org.koin.android.ext.koin.androidContext
import org.koin.core.scope.Scope

public actual fun Scope.createDriver(databaseName: String): SqlDriver {
    return AndroidSqliteDriver(Jisho.Schema, androidContext(), databaseName)
}
