package com.woocommerce.android.e2e.screens.mystore

import com.woocommerce.android.R
import com.woocommerce.android.e2e.helpers.util.Screen

class StatsComponent : Screen(STATS_DASHBOARD) {
    companion object {
        const val STATS_DASHBOARD = R.id.dashboardStats_root
    }

    override fun recover() {
        super.recover()
        clickOn(R.id.reviews)
        clickOn(R.id.dashboard)
    }

    private fun waitForGraphToLoad() {
        // One option to ensure stats load is to idle for n seconds to give time to the network request to
        // finish. The timeout duration may or may not be enough though. Here's an option that hopes to be
        // a bit more flexible. Conversion rate value relies on orders and visitors (2 independent async requests)
        // to be loaded, so its a pretty reliable source to determine that the stats have finished
        // loading. I'm leaving the previous one and this comment for reference, just in case
        // the option doesn't prove to more reliable.
        // idleFor(1000)
        if (!waitForElementToBeDisplayedWithoutFailure(R.id.conversionValueTextView)) {
            recover()
            waitForElementToBeDisplayed(R.id.conversionValueTextView)
            // idle for a bit in order to load labels as well
            idleFor(3000)
        } else {
            // idle for a bit in order to load labels as well
            idleFor(3000)
        }
    }

    fun switchToStatsDashboardWeekTab(): MyStoreScreen {
        selectItemWithTitleInTabLayout(R.string.this_week, R.id.app_bar_layout)
        waitForGraphToLoad()

        return MyStoreScreen()
    }

    fun switchToStatsDashboardMonthTab(): MyStoreScreen {
        selectItemWithTitleInTabLayout(R.string.this_month, R.id.app_bar_layout)
        waitForGraphToLoad()

        return MyStoreScreen()
    }
}
