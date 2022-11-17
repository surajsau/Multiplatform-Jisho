package `in`.surajsau.jisho.preference

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.coroutines.FlowSettings
import com.russhwolf.settings.coroutines.toFlowSettings
import org.koin.core.scope.Scope
import platform.Foundation.NSUserDefaults

actual fun Scope.settings(): FlowSettings {
    return NSUserDefaultsSettings(NSUserDefaults.standardUserDefaults).toFlowSettings()
}