package com.nandaiqbalh.katakata.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nandaiqbalh.katakata.R
import com.nandaiqbalh.katakata.databinding.FragmentHurufBinding
import com.nandaiqbalh.katakata.helper.HurufAdapter
import com.nandaiqbalh.katakata.helper.`interface`.OnDataPass
import com.nandaiqbalh.katakata.helper.`interface`.OnItemClickCallback

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HurufFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HurufFragment : Fragment() {

    // binding
    private var _binding: FragmentHurufBinding? = null
    private val binding get() = _binding!!

    private lateinit var onDataPass: OnDataPass

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onDataPass = context as OnDataPass
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        
        // Inflate the layout for this fragment
        _binding = FragmentHurufBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpAdapter()
    }

    private fun setUpAdapter() {

        val list = resources.getStringArray(R.array.huruf).toList()

        val adapter = HurufAdapter()
        val layoutManager = LinearLayoutManager(requireContext())

        binding.rvHuruf.adapter = adapter
        binding.rvHuruf.layoutManager = layoutManager

        adapter.submitData(list)

        adapter.setOnItemClickCallback(object : OnItemClickCallback{
            override fun onItemClicked(data: String) {
                onDataPass.onDataPass(data)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}