package com.ahmetturk.definex.analytics

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

class Analytics {

    private var analytics: FirebaseAnalytics? = null

    fun initialize(firebaseAnalytics: FirebaseAnalytics) {
        analytics = firebaseAnalytics
    }

    fun sendEvent(event: AnalyticsEvent, bundle: Bundle? = null) {
        analytics?.logEvent(event.eventName, bundle)
    }

}
