package com.robosoft.newsapp.ui.home

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.SearchRecentSuggestions
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isGone
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.robosoft.newsapp.MainActivity
import com.robosoft.newsapp.R
import com.robosoft.newsapp.logs.NDLogs
import com.robosoft.newsapp.repository.MySuggestionProvider

open class HomeActivity : MainActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        NDLogs.debug(TAG," Action ${intent?.action} ")
        val intent = getIntent()


        handleIntent(intent)

    }

    fun initialise() {

        navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
        val view: View = layoutInflater.inflate(R.layout.toolbar_content, toolBar)

        setUpClickListeners()
        hideUI()
    }

    fun setUpClickListeners() {
        toolBar.findViewById<AppCompatImageView>(R.id.bookmarked_toolbar).setOnClickListener {
            navController.navigate(R.id.action_HomeFragment_to_BookmarkedFragment)
        }

        toolBar.findViewById<AppCompatImageView>(R.id.search_toolbar).setOnClickListener {
            /*utility.launchActivity(context = applicationContext,
                SearchResultsActivity::class.java, flag = Intent.FLAG_ACTIVITY_NEW_TASK)*/
            navController.navigate(R.id.action_HomeFragment_to_SearchNewsFragment)
        }
    }

    fun hideUI() {

        toolBar.findViewById<AppCompatEditText>(R.id.search_edit_text)?.let {
            it.isGone = true
        }
        toolBar.findViewById<AppCompatTextView>(R.id.bookmarked_title)?.let {
            it.isGone = true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        NDLogs.debug(TAG," onNewIntent Action ${intent?.action} ")
        if (intent != null) {
            handleIntent(intent)
        }
    }

    fun handleIntent(intent: Intent){
        if(Intent.ACTION_SEARCH == intent.action) {
            //navController.navigate(R.id.action_HomeFragment_to_SearchNewsFragment)
            intent.getStringExtra(SearchManager.QUERY)?.also {
                    query ->
                NDLogs.debug(TAG," Query $query ")
                SearchRecentSuggestions(applicationContext,
                    MySuggestionProvider.AUTHORITY,
                    MySuggestionProvider.MODE)
                    .saveRecentQuery(query, null)
            }
        } else {
            initialise()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)

        // Associate searchable configuration with the SearchView
        val searchManager =
            getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu?.findItem(R.id.search)?.actionView as
                android.widget.SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.search) {
            navController.navigate(R.id.action_HomeFragment_to_SearchNewsFragment)
        }

        return super.onOptionsItemSelected(item)
    }
}