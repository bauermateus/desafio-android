package com.picpay.desafio.android

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val recyclerView: RecyclerView by lazy {
        findViewById<RecyclerView>(R.id.recyclerView)
    }
    private val progressBar: ProgressBar by lazy {
        findViewById<ProgressBar>(R.id.user_list_progress_bar)
    }
    private val adapter: UserListAdapter by lazy {
        UserListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel: MainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        progressBar.visibility = View.VISIBLE

        viewModel.fetchUsers()

        viewModel.users.observe(this) {
                adapter.updateUsers(it)
        }
        viewModel.apiStatus.observe(this) {
            if (it) {
                progressBar.visibility = View.GONE
            } else {
                progressBar.visibility = View.GONE
                val message = getString(R.string.error)
                Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}
