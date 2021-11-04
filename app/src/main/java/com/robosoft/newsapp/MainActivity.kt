package com.robosoft.newsapp

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isGone
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.robosoft.newsapp.Util.utility
import com.robosoft.newsapp.databinding.ActivityMainBinding
import com.robosoft.newsapp.logs.NDLogs
import com.robosoft.newsapp.ui.search.SearchResultsActivity

open class MainActivity : AppCompatActivity() {

    open val TAG = "MainActivity"
    lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var binding: ActivityMainBinding

    lateinit var toolBar: androidx.appcompat.widget.Toolbar

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        NDLogs.debug(TAG,"onCreate")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        toolBar = binding.toolbar
        initUi()
    }

    open fun initUi() {

        navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
        val view: View = layoutInflater.inflate(R.layout.toolbar_content, toolBar)

        //setUpClickListeners()
        //hideUI()
    }

    /*fun setUpClickListeners() {
        toolBar.findViewById<AppCompatImageView>(R.id.bookmarked_toolbar).setOnClickListener {
            navController.navigate(R.id.action_HomeFragment_to_BookmarkedFragment)
        }

        toolBar.findViewById<AppCompatImageView>(R.id.search_toolbar).setOnClickListener {
            utility.launchActivity(context = applicationContext,
                SearchResultsActivity::class.java, flag = Intent.FLAG_ACTIVITY_NEW_TASK)
            //navController.navigate(R.id.action_HomeFragment_to_SearchNewsFragment)
        }
    }

    fun hideUI() {

        toolBar.findViewById<AppCompatEditText>(R.id.search_edit_text)?.let {
            it.isGone = true
        }
        toolBar.findViewById<AppCompatTextView>(R.id.bookmarked_title)?.let {
            it.isGone = true
        }
    }*/

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
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
}