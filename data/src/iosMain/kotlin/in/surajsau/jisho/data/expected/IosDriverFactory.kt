package `in`.surajsau.jisho.data.expected

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import `in`.surajsau.jisho.data.db.Jisho
import org.koin.core.scope.Scope

actual fun Scope.createDriver(databaseName: String): SqlDriver {
    return NativeSqliteDriver(Jisho.Schema, databaseName)
}
