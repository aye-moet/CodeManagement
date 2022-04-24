package com.am.codemanagement.fragment

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.am.codemanagement.R
import com.am.codemanagement.adapter.RecommendedListAdapter
import com.am.codemanagement.adapter.UpcommingMovieListAdapter
import com.am.codemanagement.data.vos.OfflineRealmVO
import com.am.codemanagement.data.vos.RecommendedVO
import com.am.codemanagement.data.vos.UpcommingMovieVO
import com.am.codemanagement.mvp.presenter.MainPresenterImpl
import com.am.codemanagement.mvp.view.MainView
import com.am.codemanagement.network.response.PopularListResponse
import com.am.codemanagement.network.response.UpcommingListResponse
import com.am.codemanagement.utils.*
import io.realm.Realm
import io.realm.Sort
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.fragment_pager.*
import java.util.*
import kotlin.collections.ArrayList

class PagerFragment : Fragment(), MainView {
    private lateinit var mPresenter: MainPresenterImpl
    private lateinit var mRecommendedAdapter: RecommendedListAdapter
    private lateinit var mUpcommingMovieAdapter: UpcommingMovieListAdapter
    private lateinit var mRecommendedList: MutableList<UpcommingMovieVO>
    private lateinit var mUpcommingMovieList: MutableList<UpcommingMovieVO>
    private lateinit var recommendedOfflineList: MutableList<OfflineRealmVO>
    private lateinit var dialog: Dialog
    private lateinit var realm: Realm
    private var isSize = false
    companion object{
        fun newFragment(): Fragment {
            val fragment = PagerFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pager, container, false)
        setUpPresenter()
        mPresenter.onCreateView()
        return view
    }

    private fun setUpPresenter() {
        mPresenter = MainPresenterImpl()
        mPresenter.initPresenter(this)
    }

    override fun upcommingResponse(response: UpcommingListResponse) {
        if (response.results.size > 0) {
            mUpcommingMovieList.addAll(response.results)
            mUpcommingMovieAdapter.setData(mUpcommingMovieList)
            if (isSize) {
                for (item in mUpcommingMovieList) {
                    SaveData(item)
                }
            }
            getAllData()
            Log.d("test_data_in","response in${realm.where(OfflineRealmVO::class.java).findAll().count()}")
        }
    }

    override fun popularResponse(response: PopularListResponse) {
        if (response.results.size > 0) {
            mRecommendedList.addAll(response.results)
            mRecommendedAdapter.setData(mRecommendedList)
            if (isSize) {
                for (item in mRecommendedList) {
                    SaveData(item)
                }
            }
        }
    }

    fun SaveData( vo: UpcommingMovieVO) {
        //saveTheDate()
        val recentRealm = realm.where<OfflineRealmVO>().findAll()
        Log.d("test_size", "size before = ${recentRealm.size}")
//        val recent = recentRealm.where().equalTo("id", vo.id).findFirst()
//        if (recent != null) {
//            if (!realm.isInTransaction) {
//                realm.beginTransaction()
//            }
//            recent.deleteFromRealm()
//            realm.commitTransaction()
//        }
//        var id = realm.where<OfflineRealmVO>().max("id")
//        id = if (id == null) {
//            1
//        } else {
//            id.toInt() + 1
//        }

        realm.executeTransaction {
            val recent = it.createObject<OfflineRealmVO>(vo.id)
            recent.adult = vo.adult
            recent.title = vo.title
            recent.backdrop_path = vo.backdrop_path
            recent.original_language = vo.original_language
            recent.original_title = vo.original_title
            recent.overview = vo.overview
            recent.popularity = vo.popularity
            recent.poster_path = vo.poster_path
            recent.release_date = vo.release_date
            recent.title = vo.title
            recent.video = vo.video
            recent.vote_average = vo.vote_average
            recent.vote_count = vo.vote_count
            when (vo.genre_ids.size) {
                1 -> {
                    recent.genre_ids1 = vo.genre_ids[0]
                }

                2 -> {
                    recent.genre_id2 = vo.genre_ids[1]
                }

                3 -> {
                    recent.genre_ids3 = vo.genre_ids[2]
                }
            }
        }

        val vo = realm.where<OfflineRealmVO>().findAll()
        recommendedOfflineList.addAll(realm.where(OfflineRealmVO::class.java).findAll())
        Log.d("test_size", "size = ${vo.size}")
        Log.d("test_size", "title ${vo[0]!!.id}")
        Log.d("test_size", "list size ${recommendedOfflineList.size}")
    }

    fun saveTheDate(): Boolean{
        val editor = requireActivity().getSharedPreferences("key", Context.MODE_PRIVATE).edit()

        val next7Day = getNextDay(7)
        //editor.putInt(Constant.RequestDate_KEY, next7Day)

        editor.putString("date", next7Day.toString())


        return editor.commit()
    }

    fun getNextDay(nextDay: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, nextDay)

        return calendar.time
    }


    override fun onResume() {
        super.onResume()
    }

    override fun init() {
        var prefs = requireActivity().getSharedPreferences("key", Context.MODE_PRIVATE)


        var requestDate = prefs.getString("date","new")
        Realm.init(requireContext())
        realm = Realm.getDefaultInstance()
        mRecommendedList = ArrayList()
        mUpcommingMovieList = ArrayList()
        recommendedOfflineList = ArrayList()
    }

    private fun getAllData() {
        val query = realm.where(OfflineRealmVO::class.java).findAll()
        if (query.size > 0) {
            recommendedOfflineList.addAll(query)
            Log.d("test_data","${recommendedOfflineList.size}")
        }
    }

    override fun setUpLoadingDialog() {
        dialog = com.am.codemanagement.utils.setUpLoadingDialog(requireContext())
    }

    override fun checkNetwork() {
        if (isNetwork(requireContext())) {
            mPresenter.fetchPopularData()
            mPresenter.fetchUpcommingData()
            mPresenter.showLoading()
        }
    }

    override fun listener() {

    }

    override fun showLoading() {
        dialog.show()
    }

    override fun hideLoading() {
        dialog.dismiss()
    }

    override fun showErrorMessage(message: String) {
        showErrorDialog(requireContext(), message)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.onViewCreated()
        val query = realm.where(OfflineRealmVO::class.java).findFirst()
        if (query == null) {
            isSize = true
        } else {
            getAllData()
        }
    }

    override fun setUpAdapter() {
        mRecommendedAdapter = RecommendedListAdapter(requireContext())
        rv_recommended.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        rv_recommended.adapter = mRecommendedAdapter

        mUpcommingMovieAdapter = UpcommingMovieListAdapter(requireContext())
        rv_upcomming_movie.layoutManager = LinearLayoutManager(requireContext())
        rv_upcomming_movie.adapter = mUpcommingMovieAdapter
    }
}