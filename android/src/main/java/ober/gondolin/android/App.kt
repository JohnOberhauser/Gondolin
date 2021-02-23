package ober.gondolin.android

import android.app.Application
import ober.gondolin.common.database.DatabaseApplicationContext

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        application = this
        DatabaseApplicationContext.application = this
    }

    companion object {
        var application: App? = null
    }
}
