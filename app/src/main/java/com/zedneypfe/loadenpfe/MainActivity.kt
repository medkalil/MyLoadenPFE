package com.zedneypfe.loadenpfe

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.ui.AppBarConfiguration
import com.google.android.material.navigation.NavigationView
import com.zedneypfe.loadenpfe.Model.authModel
import com.zedneypfe.loadenpfe.fragments.MyAccountFragment
import com.zedneypfe.loadenpfe.fragments.NotificationFragment
import com.zedneypfe.loadenpfe.fragments.SignInFragment
import com.zedneypfe.loadenpfe.fragments.VerifSignInFragment
import com.zedneypfe.loadenpfe.fragments.client.EnvoyerDemandeFragment
import com.zedneypfe.loadenpfe.fragments.client.MesDemandesFragment
import com.zedneypfe.loadenpfe.fragments.constFragment.AppIdentFragment
import com.zedneypfe.loadenpfe.fragments.constFragment.ContactFragment
import com.zedneypfe.loadenpfe.fragments.provider.LesDemandesFragment
import com.zedneypfe.loadenpfe.storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    Communicator {

    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var drawerLayout: DrawerLayout
    var user_type: String? = ""
    //   var user_type_saved:String?=""


    //to test user
    //val user=authModel("ok","1","1234")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //this is where we show the first fragment : activity_main
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)

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

        // user_type=intent.getStringExtra("user_type")
        user_type = SharedPrefManager.getInstance(this).user_type

        //SharedPrefManager.getInstance(this).save_user_type(user_type.toString())

        //  user_type_saved = SharedPrefManager.getInstance(this).user_type


        println(user_type + " in Mainactivity")


        if (SharedPrefManager.getInstance(this).isLoggedIn == false) {

            changeMenuNotloggedIn()
            setFragment(EnvoyerDemandeFragment())
            //client
        } else if (user_type == 1.toString()) {
            changeMenuloggedIn()
            setFragment(EnvoyerDemandeFragment())
        } else if (user_type == 2.toString()) {
            changeMenuProvider()
            //lesdemandesFragment
            setFragment(LesDemandesFragment())
            supportActionBar?.title = getString(R.string.labellesdemandes)
        }

        //setFragment(EnvoyerDemandeFragment())
    }


    //Function for hide Drawer Menu Item
    //here is the sign out
    //and can manipule the title,color... of an item just m.findItem(R.id.item_signout).set...
    /*  fun hideItem() {
          val navigationView: NavigationView = findViewById(R.id.nav_view)
          val m: Menu = navigationView.menu
          m.findItem(R.id.item_signout).setVisible(false)
      }*/

    fun changeMenuNotloggedIn() {
        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.menu.clear()
        navigationView.inflateMenu(R.menu.drawer_menu_notloggedin)
    }

    fun changeMenuloggedIn() {
        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.menu.clear()
        navigationView.inflateMenu(R.menu.activity_main_drawer)
    }

    fun changeMenuProvider() {
        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.menu.clear()
        navigationView.inflateMenu(R.menu.drawer_menu_provider)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        //check if the user not logged IN
        if (SharedPrefManager.getInstance(this).isLoggedIn == false) {

            supportActionBar?.title = getString(R.string.label_sign_in)

            //navigate to the views doesn't require a login exp:contact us
            when (item.itemId) {

                R.id.item_contact -> {
                    setFragment(ContactFragment())
                    supportActionBar?.title = getString(R.string.labelcontact)
                }
                R.id.item_info -> {
                    setFragment(AppIdentFragment())
                    supportActionBar?.title = getString(R.string.labelinfo)
                }
                R.id.item_home -> {
                    setFragment(EnvoyerDemandeFragment())
                    supportActionBar?.title = getString(R.string.labelhome)
                }
                R.id.item_notif -> {
                    setFragment(NotificationFragment())
                    supportActionBar?.title = getString(R.string.labelnotif)
                }
                else -> {
                    setFragment(SignInFragment())
                    supportActionBar?.title = getString(R.string.label_sign_in)

                }
            }//when


            //loggedIn Provider
        } else if (user_type == 2.toString()) {

            println(user_type + " in navigation  provider")


            when (item.itemId) {
                R.id.item_contact -> {
                    setFragment(ContactFragment())
                    supportActionBar?.title = getString(R.string.labelcontact)
                }
                R.id.item_lesdemandes -> {
                    //lesdemandesFragment
                    setFragment(LesDemandesFragment())
                    supportActionBar?.title = getString(R.string.labellesdemandes)
                }
                R.id.item_profile -> {
                    setFragment(MyAccountFragment())
                    supportActionBar?.title = getString(R.string.labelmoncompte)
                }
                R.id.item_info -> {
                    setFragment(AppIdentFragment())
                    supportActionBar?.title = getString(R.string.labelinfo)
                }
                R.id.item_notif -> {
                    setFragment(NotificationFragment())
                    supportActionBar?.title = getString(R.string.labelnotif)
                }
                R.id.item_signout -> {
                    setFragment(SignoutFragment())
                    supportActionBar?.title = getString(R.string.labelhome)
                    //showNotLogeedInMenu()
                    //hideItem()
                    changeMenuNotloggedIn()
                }
            }//when


        }//LoggedIn client
        else {
            // the user already connected
            //showLogeedInMenu()
            println(user_type + " in navigation  client")


            when (item.itemId) {
                R.id.item_contact -> {
                    setFragment(ContactFragment())
                    supportActionBar?.title = getString(R.string.labelcontact)
                }
                R.id.item_mesdemandes -> {
                    setFragment(MesDemandesFragment())
                    supportActionBar?.title = getString(R.string.labelmesdemande)
                }
                R.id.item_profile -> {
                    setFragment(MyAccountFragment())
                    supportActionBar?.title = getString(R.string.labelmoncompte)
                }
                R.id.item_info -> {
                    setFragment(AppIdentFragment())
                    supportActionBar?.title = getString(R.string.labelinfo)
                }
                R.id.item_home -> {
                    setFragment(EnvoyerDemandeFragment())
                    supportActionBar?.title = getString(R.string.labelhome)
                }
                R.id.item_notif -> {
                    setFragment(NotificationFragment())
                    supportActionBar?.title = getString(R.string.labelnotif)
                }
                R.id.item_signout -> {
                    setFragment(SignoutFragment())
                    supportActionBar?.title = getString(R.string.labelhome)
                    //showNotLogeedInMenu()
                    //hideItem()
                    changeMenuNotloggedIn()
                }
            }//when

        }//else


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
        transaction.addToBackStack(null).commit()
    }

    override fun onResume() {
        super.onResume()
        fixUpLocale()
    }

    //we need this because we blocked the oncreate on configuration changes
    //
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
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

    //test for pass data from fragment to fragment with interface
    override fun passDataCom(code: String, phone: String, user_type: String) {
        val bundle = Bundle()
        bundle.putString("code", code)
        bundle.putString("phone", phone)
        bundle.putString("user_type", user_type)


        val transaction = this.supportFragmentManager.beginTransaction()
        val VerifSignInFragment = VerifSignInFragment()
        VerifSignInFragment.arguments = bundle

        transaction.replace(R.id.container_fragm, VerifSignInFragment)
        transaction.addToBackStack(null)
        transaction.setTransition(androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.commit()

    }



}