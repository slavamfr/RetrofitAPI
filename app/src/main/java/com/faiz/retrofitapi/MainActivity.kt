package com.faiz.retrofitapi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.faiz.retrofitapi.databinding.ActivityMainBinding
import com.faiz.retrofitapi.model.Users
import com.faiz.retrofitapi.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvUser.layoutManager = LinearLayoutManager(this)

        ApiClient.getInstance().getallusers().enqueue(object : Callback<Users> {
            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                if (response.isSuccessful) {
                    // Extract the list of Data objects from the Users response
                    val usersList = response.body()?.data ?: listOf()

                    binding.rvUser.adapter = UserAdapter(usersList) { user ->
                        // Set up intent to pass data to SecondActivity
                        val intent = Intent(this@MainActivity, SecondActivity::class.java)
                        intent.putExtra("userId", user.id)
                        intent.putExtra("userName", "${user.firstName} ${user.lastName}")
                        intent.putExtra("userEmail", user.email)
                        intent.putExtra("userAvatar", user.avatar)
                        startActivity(intent)
                    }
                }
            }

            override fun onFailure(call: Call<Users>, t: Throwable) {
                // Handle error
                Log.e("MainActivity", "Error fetching data: ${t.message}")
            }
        })
    }
}