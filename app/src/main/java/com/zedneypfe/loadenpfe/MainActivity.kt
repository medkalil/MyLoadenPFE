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
import com.zedneypfe.loadenpfe.Model.PHONE
import com.zedneypfe.loadenpfe.fragments.*
import com.zedneypfe.loadenpfe.storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_main.*
import org.intellij.lang.annotations.Language
import java.util.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration

    //test to save the user in sharedPrefrences then  launch the app
    var phone = PHONE("1", "PHONE", "966555555555", "WORK")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //this is where we show the first fragment : activity_main
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

        //SharedPrefManager.getInstance(this).saveUser(phone)
        SharedPrefManager.getInstance(this).clear()

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        //check if the user is logged In
        if (!SharedPrefManager.getInstance(this).isLoggedIn) {
            setFragment(SignInFragment())
            supportActionBar?.title = getString(R.string.label_sign_in)
        } else {

            setFragment(EnvoyerDemandeFragment())

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
            }//when

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


    /* //check responce of the key after login
   internal fun getresp() {

       val service = retrofit.create(ApiService::class.java)
       val call = service.getcode()

       call.enqueue(object : retrofit2.Callback<authModel> {

           override fun onFailure(call: Call<authModel>, t: Throwable) {
               Toast.makeText(this@MainActivity, call.toString(), Toast.LENGTH_SHORT).show()
           }

           override fun onResponse(call: Call<authModel>, response: Response<authModel>) {

                   Toast.makeText(this@MainActivity, res.to, Toast.LENGTH_SHORT).show()
           }
       })//enqueue

   }*/

}