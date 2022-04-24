package com.am.codemanagement.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.am.codemanagement.R
import com.am.codemanagement.adapter.CastListAdapter
import com.am.codemanagement.data.vos.*
import com.am.codemanagement.utils.IMAGE_URL
import com.am.codemanagement.utils.dateFormat
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.adapter_recommended_list.view.*

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var mAdapter: CastListAdapter
    private lateinit var list: ArrayList<CastVO>
    private lateinit var uplist: UpcommingMovieVO
    private lateinit var reclist: OfflineRealmCopyVO
    private lateinit var type : String

    companion object {
        const val LIST_OFFLINE = "offlineList"
        const val LIST_UP = "upList"
        const val TYPE = "type"
        fun newIntent(context: Context, vo: UpcommingMovieVO, type: String): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(LIST_UP,vo)
            intent.putExtra(TYPE,type)
            return intent
        }
        fun newIntent(context: Context, vo: OfflineRealmCopyVO, type: String): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(LIST_OFFLINE,vo)
            intent.putExtra(TYPE,type)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        init()
        loadData()
        setUpAdapter()
        listener()
    }

    private fun init() {
        list = ArrayList()
        type = intent.getStringExtra(TYPE) as String

    }

    private fun loadData() {
        if (type == "up") {
             uplist = intent.getSerializableExtra(LIST_UP) as UpcommingMovieVO
             Glide.with(this)
                 .load( IMAGE_URL +uplist.poster_path)
                 .error(R.drawable.welcome)
                 .into(iv_image)
             tv_name.text = uplist.original_title
             tv_percent.text = if (uplist.genre_ids.size > 0) {
                  "${uplist.genre_ids[0]} %"
             } else {
                 "${uplist.vote_average} %"
             }
             tv_year.text = "UA|${dateFormat(uplist.release_date)}"
             tv_votes.text = "${uplist.vote_count} votes"
             tv_language.text = if (uplist.original_language == "en") {
                 "English"
             } else if (uplist.original_language == "ko") {
                 "Korea"
             } else if (uplist.original_language == "ja") {
                 "Japan"
             } else if (uplist.original_language == "fr") {
                 "France"
             } else {
                 "El Salvador"
             }
             tv_description.text = uplist.overview
            } else {
            reclist = intent.getSerializableExtra(LIST_OFFLINE) as OfflineRealmCopyVO
            Glide.with(this)
                .load( IMAGE_URL +reclist.poster_path)
                .error(R.drawable.welcome)
                .into(iv_image)
//            if (reclist.isFavourite) {
//                Glide.with(this)
//                    .load(R.drawable.ic_baseline_favorite_border_24)
//                    .into(iv_fav)
//            } else {
//                Glide.with(this)
//                    .load(R.drawable.ic_baseline_favorite_24)
//                    .into(iv_fav)
//            }
            tv_name.text = reclist.original_title
            tv_percent.text = "${reclist.genre_ids1} %"
            tv_year.text = "UA|${dateFormat(reclist.release_date)}"
            tv_votes.text = "${reclist.vote_count} votes"
            tv_language.text =  if (reclist.original_language == "en") {
                "English"
            } else if (reclist.original_language == "ko") {
                "Korea"
            } else if (reclist.original_language == "ja") {
                "Japan"
            } else if (reclist.original_language == "fr") {
                "France"
            } else {
                "El Salvador"
            }
            tv_description.text = reclist.overview
        }

    }

    private fun setUpAdapter() {
        mAdapter = CastListAdapter(this)
        rv_cast_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_cast_list.adapter = mAdapter
        list.add(CastVO("Josh Gab",""))
        list.add(CastVO("Josh Gab",""))
        list.add(CastVO("Josh Gab",""))
        mAdapter.setData(list)
    }

    private fun listener() {
        iv_back.setOnClickListener {
            onBackPressed()
        }

        iv_fav.setOnClickListener {
            if (type != "up") {
                reclist.isFavourite = true
                Glide.with(this)
                    .load(R.drawable.ic_baseline_favorite_border_24)
                    .into(iv_fav)
            }
        }
    }
}