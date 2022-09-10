package com.nandaiqbalh.katakata.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.nandaiqbalh.katakata.R
import com.nandaiqbalh.katakata.databinding.ActivityMainBinding
import com.nandaiqbalh.katakata.helper.`interface`.OnDataPass
import com.nandaiqbalh.katakata.ui.fragment.HurufFragment
import com.nandaiqbalh.katakata.ui.fragment.KataFragment

class MainActivity : AppCompatActivity(), OnDataPass {

    // binding
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // set fragment
        setUpFragment()

    }

    private fun setUpFragment(){
        val hurufFragment = HurufFragment()
        val fragmentManager = supportFragmentManager

        fragmentManager.commit {
            add(R.id.nav_host_fragment_container, hurufFragment)
        }
    }

    override fun onDataPass(huruf: String) {

        val kataFragment = KataFragment()

        val bundle = Bundle()
        bundle.putString("huruf", huruf)

        kataFragment.arguments = bundle
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.nav_host_fragment_container, kataFragment)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}