package com.am.codemanagement.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.am.codemanagement.R
import com.am.codemanagement.adapter.SearchListAdapter
import com.am.codemanagement.adapter.UpcommingMovieListAdapter
import com.am.codemanagement.data.vos.OfflineRealmVO
import com.am.codemanagement.data.vos.UpcommingMovieVO
import com.am.codemanagement.fragment.PagerFragment
import com.google.android.material.tabs.TabLayout
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var realm: Realm
    private lateinit var mAdapter: SearchListAdapter
    private lateinit var list: MutableList<OfflineRealmVO>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Realm.init(this)
        realm = Realm.getDefaultInstance()
        list = ArrayList()
        setUpTab()
        setUpAdapter()
        getOfflineData()
        listener()

    }

    private fun setUpAdapter() {
        mAdapter = SearchListAdapter(this)
        rv_search.layoutManager = LinearLayoutManager(this)
        rv_search.adapter = mAdapter
    }

    private fun getOfflineData() {
        val query = realm.where(OfflineRealmVO::class.java).findAll()
        //list.addAll(query)
        Log.d("test_main","count ${query.count()}")
    }

    private fun setUpTab() {
        tab.addTab(tab.newTab().setText("Movies" ))
        tab.addTab(tab.newTab().setText("Events"))
        tab.addTab(tab.newTab().setText("Plays"))
        tab.addTab(tab.newTab().setText("Sports"))
        tab.addTab(tab.newTab().setText("Activities"))
        loadFragment(PagerFragment.newFragment())
    }

    private fun listener() {
        tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                loadFragment(PagerFragment.newFragment())
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        et_search.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (et_search.text.isNotEmpty()) {
                        rv_search.visibility = View.VISIBLE
                        tab.visibility = View.GONE
                        frame_1.visibility = View.GONE
                        searchMovie(et_search.text.toString())
                    }
                    return true
                }

                return false
            }

        })

        iv_close.setOnClickListener {
            et_search.setText("")
            rv_search.visibility = View.GONE
            tab.visibility = View.VISIBLE
            frame_1.visibility = View.VISIBLE
        }
    }

    private fun searchMovie(searchItem: String) {
        val query = realm.where(OfflineRealmVO::class.java).contains("original_title",searchItem).findAll()
        list.addAll(query)
        mAdapter.setData(list)
    }

    private fun loadFragment(fragment: Fragment) {
        this.supportFragmentManager.beginTransaction().replace(R.id.frame_1, fragment).commit()
    }
}