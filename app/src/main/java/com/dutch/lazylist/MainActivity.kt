package com.dutch.lazylist

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import com.dutch.lazylist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

//        val navController = findNavController(R.id.fragmentContainerView)

//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView,
//                MainFragment()).commit()
//        }

    }
}