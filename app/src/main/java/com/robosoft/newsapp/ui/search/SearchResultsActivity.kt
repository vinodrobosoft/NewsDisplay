package com.robosoft.newsapp.ui.search

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.SearchRecentSuggestions
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.navigateUp
import com.robosoft.newsapp.MainActivity
import com.robosoft.newsapp.R
import com.robosoft.newsapp.databinding.ActivitySearchResultsBinding
import com.robosoft.newsapp.logs.NDLogs
import com.robosoft.newsapp.repository.MySuggestionProvider

class SearchResultsActivity : MainActivity() {

    override val TAG = "SearchResultsActivity"

    private lateinit var activitySearchResultsBinding : ActivitySearchResultsBinding

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        activitySearchResultsBinding = ActivitySearchResultsBinding.inflate(layoutInflater)
        setContentView(activitySearchResultsBinding.root)

        NDLogs.debug(TAG," Action ${intent?.action} ")
        val intent = getIntent()

        handleIntent(intent)

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        NDLogs.debug(TAG," onNewIntent Action ${intent?.action} ")
        if (intent != null) {
            handleIntent(intent)
        }
    }

    fun handleIntent(intent: Intent){
        if(Intent.ACTION_SEARCH == intent?.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also {
                    query ->
                NDLogs.debug(TAG," Query $query ")
                SearchRecentSuggestions(applicationContext,
                    MySuggestionProvider.AUTHORITY,
                    MySuggestionProvider.MODE)
                    .saveRecentQuery(query, null)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

   /* override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_main,menu)
        // Associate searchable configuration with the SearchView
        val searchManager =
            getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search)?.actionView as
                android.widget.SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }
        return true

    }*/
}