package com.petfriends.empty.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.petfriends.empty.R
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class RetrofitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        lifecycleScope.launch {
            flow {
                emit(Api.create().getMockData())
            }.catch {
                findViewById<TextView>(R.id.data).text = "error!"
            }.collect {
                    findViewById<TextView>(R.id.data).text = it.mock.msg
                }
        }

    }
}