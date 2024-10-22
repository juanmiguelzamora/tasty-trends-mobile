package com.migsdev.tastytrends

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.roydev.tastytrends.Stalls

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewStallsAdapter: RecyclerViewStallsAdapter
    private var stallList = mutableListOf<Stalls>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_drawer_navigation)

        // Apply system window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawer_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val cartIcon = findViewById<ImageButton>(R.id.cartIcon)
        cartIcon.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

        // Set up RecyclerView
        recyclerView = findViewById(R.id.rvStallLists)
        recyclerViewStallsAdapter = RecyclerViewStallsAdapter(this, stallList)
        val layoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = recyclerViewStallsAdapter

        loadTestStallsData()
        prepareStallsListData()

        // Set click listener for the adapter
        recyclerViewStallsAdapter.onItemClick = { stall ->
            handleStallClick(stall)
        }

        // Initialize DrawerLayout
        drawerLayout = findViewById(R.id.drawer_layout)

        // Set up the Toolbar and hide the title
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false) // Hides the toolbar title

        // Set up NavigationView and listen for item selections
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        // Set up drawer toggle
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun loadTestStallsData() {
        stallList.add(
            Stalls(
                "jfc_12345678901234567890123456789012",
                "jfc",
                "roy_12345678901234567890123456789012",
                R.drawable.jfc.toString()
            )
        )
        stallList.add(
            Stalls(
                "arnold_12345678901234567890123456789012",
                "arnold",
                "roy_12345678901234567890123456789012",
                R.drawable.arnold.toString()
            )
        )
//        stallList.add(Stalls("arnold", R.drawable.arnold))
//        stallList.add(Stalls("happiness", R.drawable.happiness_food))
//        stallList.add(Stalls("specialvigan", R.drawable.speical_vigan))
//        stallList.add(Stalls("kyah", R.drawable.khay_sa_wrap))
//        stallList.add(Stalls("jdj", R.drawable.jdjdimsum))
//        stallList.add(Stalls("hotto", R.drawable.hotto))
//        stallList.add(Stalls("buko", R.drawable.buko))
//        stallList.add(Stalls("gaerlan", R.drawable.gaerlan))
//        stallList.add(Stalls("jos", R.drawable.jos))
//        stallList.add(Stalls("koreanfood", R.drawable.koreanfood))
//        stallList.add(Stalls("granny", R.drawable.grannysfoodcorner))
        recyclerViewStallsAdapter.notifyDataSetChanged()
    }
    private fun prepareStallsListData() {}

    private fun handleStallClick(stall: Stalls) {
        when (stall.shopName) {
            "jfc" -> {
                startActivity(Intent(this, StallActivity::class.java)) // Navigate to JFCActivity
            }
            "arnold" -> {
                startActivity(Intent(this, ArnoldActivity::class.java)) // Navigate to ArnoldActivity
            }
            else -> {
                Toast.makeText(this, "No activity found for this stall", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Handle navigation item selection
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navHome -> {
                startActivity(Intent(this, HomeActivity::class.java))
            }
            R.id.navProfile -> {
                startActivity(Intent(this, ProfileActivity::class.java))
            }
            R.id.navFavorite -> {
                startActivity(Intent(this, FavoritesActivity::class.java))
            }
            R.id.navOrders -> {
                startActivity(Intent(this, OrderActivity::class.java))
            }
        }
        // Close the drawer after selecting an item
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    // Handle back button behavior
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
