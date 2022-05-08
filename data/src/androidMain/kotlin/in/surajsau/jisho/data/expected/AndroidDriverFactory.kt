package `in`.surajsau.jisho.data.expected

import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import `in`.surajsau.jisho.data.db.Jisho
import org.koin.android.ext.koin.androidContext
import org.koin.core.scope.Scope

actual fun Scope.createDriver(databaseName: String): SqlDriver {
    return AndroidSqliteDriver(Jisho.Schema, androidContext(), databaseName)
}
