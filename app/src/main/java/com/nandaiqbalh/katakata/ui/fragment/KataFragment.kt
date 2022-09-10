package com.nandaiqbalh.katakata.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
<<<<<<< HEAD
=======
import androidx.navigation.fragment.navArgs
>>>>>>> dev
import androidx.recyclerview.widget.LinearLayoutManager
import com.nandaiqbalh.katakata.R
import com.nandaiqbalh.katakata.databinding.FragmentKataBinding
import com.nandaiqbalh.katakata.helper.KataAdapter
import com.nandaiqbalh.katakata.helper.`interface`.OnItemClickCallback

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [KataFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class KataFragment : Fragment() {
    
    // binding
    private var _binding: FragmentKataBinding? = null
    private val binding get() = _binding!!

    private val args: KataFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentKataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // set up adapter
        setUpAdapter()
    }

    private fun setUpAdapter(){

        // get data
        val huruf = args.huruf

        // set up title
        (activity as AppCompatActivity).supportActionBar?.title = "Words that start with $huruf"

        // set up toolbar title
        (activity as AppCompatActivity).supportActionBar?.title = "Words that start with $huruf"

        // filter kata
        val kataList = resources.getStringArray(R.array.kata).toList().filter { word -> word.startsWith(huruf) }

        val adapter = KataAdapter()
        val layoutManager = LinearLayoutManager(requireContext())

        binding.rvKata.adapter = adapter
        binding.rvKata.layoutManager = layoutManager

        adapter.submitData(kataList)
        adapter.setOnItemClickCallback(object : OnItemClickCallback {
            override fun onItemClicked(data: String) {

                // open browser
                openBrowser(data)
            }
        })
    }

    fun openBrowser(kata: String) {
        val url = "https://www.google.com/search?q=$kata"
        val browser: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, browser)

        startActivity(intent)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}