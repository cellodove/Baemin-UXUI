package com.cellodove.baemin_ux_ui.ui.home


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.cellodove.baemin_ux_ui.databinding.FragmentHomeBinding
import com.cellodove.baemin_ux_ui.ui.common.setOnTouchAnimationListener
import com.cellodove.baemin_ux_ui.ui.home.ad.AdPagerAdapter
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel : HomeViewModel

    var bannerPosition = 0
    private val adapter = AdPagerAdapter()
    lateinit var job : Job

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        uiChange()
        return root
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun uiChange(){
        homeViewModel.couponExist.observe(viewLifecycleOwner){
            Log.e("","couponExist : $it")
            binding.couponButton.visibility = if(it) View.VISIBLE else View.GONE
        }

        binding.couponButton.setOnTouchAnimationListener {}
        binding.martType1.setOnTouchAnimationListener {}
        binding.martType2.setOnTouchAnimationListener {}
        binding.martType3.setOnTouchAnimationListener {}
        binding.baeminDeliveryCategory1.setOnTouchAnimationListener {}
        binding.baeminDeliveryCategory2.setOnTouchAnimationListener {}
        binding.baeminDeliveryCategory3.setOnTouchAnimationListener {}
        binding.baeminDeliveryCategory4.setOnTouchAnimationListener {}
        binding.baeminDeliveryCategory5.setOnTouchAnimationListener {}
        binding.baeminDeliveryCategory6.setOnTouchAnimationListener {}
        binding.baeminDeliveryCategory7.setOnTouchAnimationListener {}
        binding.baeminDeliveryCategory8.setOnTouchAnimationListener {}
        binding.baeminDeliveryCategory9.setOnTouchAnimationListener {}
        binding.baeminDeliveryCategory10.setOnTouchAnimationListener {}





        homeViewModel.adImageList.observe(viewLifecycleOwner){
            adapter.submitList(it)
            binding.adViewpager.adapter = adapter
            binding.adViewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

            bannerPosition = (Int.MAX_VALUE / 2)
            binding.adViewpager.setCurrentItem(bannerPosition,false)

            binding.viewpagerIndicator.text = "${(bannerPosition % it.size)+1} / ${it.size}"

            binding.adViewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    bannerPosition = position
                    binding.viewpagerIndicator.text = "${(bannerPosition % it.size)+1} / ${it.size}"

                }

                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                    when (state) {
                        ViewPager2.SCROLL_STATE_IDLE ->{
                            Log.e("","SCROLL_STATE_IDLE")
                            if (!job.isActive) scrollJobCreate()

                        }

                        ViewPager2.SCROLL_STATE_DRAGGING -> {
                            Log.e("","SCROLL_STATE_DRAGGING")
                            job.cancel()

                        }

                        ViewPager2.SCROLL_STATE_SETTLING -> {
                            Log.e("","SCROLL_STATE_DRAGGING")
                        }
                    }
                }
            })
        }
    }

    private fun scrollJobCreate() {
        job = lifecycleScope.launch {
            delay(4000)
            binding.adViewpager.setCurrentItem(++bannerPosition,true)
        }
    }

    override fun onResume() {
        super.onResume()
        scrollJobCreate()
    }

    override fun onPause() {
        super.onPause()
        job.cancel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}