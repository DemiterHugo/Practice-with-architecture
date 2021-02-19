package com.example.musicademi.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.koin.test.KoinTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.koin.test.get
import androidx.test.rule.GrantPermissionRule
import com.example.musicademi.R
import com.example.musicademi.model.server.TheMusicDb
import com.example.musicademi.ui.main.MainActivity
import com.example.musicademi.utils.fromJson
import okhttp3.mockwebserver.MockResponse
import com.jakewharton.espresso.OkHttp3IdlingResource
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.musicademi.ui.detail.DetailActivity

class UiTest: KoinTest  {

    private val mockWebServerRule = MockWebServerRule()

    @get:Rule
    val testRule: RuleChain = RuleChain
        .outerRule(mockWebServerRule)
        .around(
            GrantPermissionRule.grant(
                "android.permission.ACCESS_COARSE_LOCATION"
            )
        )
        .around(ActivityScenarioRule(MainActivity::class.java))
        //.around(mockWebServerRule)
        //.around(ActivityScenarioRule(DetailActivity::class.java))

    @Before
    fun setUp() {
        mockWebServerRule.server.enqueue(
            MockResponse().fromJson("popularArtists.json")
            )
       /* mockWebServerRule.server.enqueue(
            MockResponse().fromJson("popularAlbums.json"),
        )*/

        val resource = OkHttp3IdlingResource.create("OkHttp", get<TheMusicDb>().okHttpClient)
        IdlingRegistry.getInstance().register(resource)
    }

    @Test
    fun clickArtistNavigatesToDetail() {
       /* mockWebServerRule.server.enqueue(
            MockResponse().fromJson("popularArtists.json")
        )*/


        onView(withId(R.id.recyclerArtist)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                2,
                click()
            )
        )

        mockWebServerRule.server.enqueue(
            MockResponse().fromJson("popularAlbums.json")
        )

        onView(withId(R.id.artistDetailToolbar))
            .check(matches(withChild(withText("Radiohead"))))
    }
}