package com.zedneypfe.loadenpfe

import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.navigation.NavigationView
import androidx.navigation.ui.AppBarConfiguration
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.zedneypfe.loadenpfe.fragments.*
import kotlinx.android.synthetic.main.activity_main.*
import org.intellij.lang.annotations.Language
import java.util.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
       supportActionBar?.title = getString(R.string.labelhome)
        nav_view.setNavigationItemSelectedListener(this)
        val actionToggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.drawer_open,
            R.string.drawer_close
        )
        drawer_layout.addDrawerListener(actionToggle)
        actionToggle.syncState()
        setFragment(EnvoyerDemandeFragment())
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.item_contact -> {
                setFragment(ContactFragment())
                supportActionBar?.title = getString(R.string.labelcontact)
            }
            R.id.item_mesdemandes ->{
                setFragment(MesDemandesFragment())
                supportActionBar?.title = getString(R.string.labelmesdemande)
            }
            R.id.item_profile -> {
                setFragment(MyAccountFragment())
                supportActionBar?.title = getString(R.string.labelmoncompte)
            }
            R.id.item_info ->{
                setFragment(AppIdentFragment())
                supportActionBar?.title = getString(R.string.labelinfo)
            }
            R.id.item_home ->{
                setFragment(EnvoyerDemandeFragment())
                supportActionBar?.title = getString(R.string.labelhome)
            }
        }

        closeDrawer()
        return true
    }

    override fun onBackPressed() {

        if (drawer_layout.isDrawerOpen(GravityCompat.START))
            closeDrawer()
        else
            super.onBackPressed()
    }

    private fun closeDrawer() {
        drawer_layout.closeDrawer(GravityCompat.START)
    }

    private fun setFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container_fragm, fragment)
        transaction.commit()

    }
    override fun onResume() {
        super.onResume()
        fixUpLocale()
    }

    private fun fixUpLocale() {
        val res = resources
        val config = res.configuration
        val newLocale = Locale("ar")
        Locale.setDefault(newLocale)
        val conf = Configuration(config)
        conf.setLocale(newLocale)
        res.updateConfiguration(conf, res.displayMetrics)

    }
}