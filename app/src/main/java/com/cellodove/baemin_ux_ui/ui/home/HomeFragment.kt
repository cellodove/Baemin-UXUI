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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager2.widget.ViewPager2
import com.cellodove.baemin_ux_ui.databinding.FragmentHomeBinding
import com.cellodove.baemin_ux_ui.ui.common.setOnTouchAnimationListener
import com.cellodove.baemin_ux_ui.ui.home.ad.AdPagerAdapter
import com.cellodove.baemin_ux_ui.ui.home.adapter.saledelivery.SaleDeliveryAdapter
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

    private val saleDeliveryAdapter = SaleDeliveryAdapter()

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

        binding.apply {
            couponButton.setOnTouchAnimationListener {}
            martType1.setOnTouchAnimationListener {}
            martType2.setOnTouchAnimationListener {}
            martType3.setOnTouchAnimationListener {}
            baeminDeliveryCategory1.setOnTouchAnimationListener {}
            baeminDeliveryCategory2.setOnTouchAnimationListener {}
            baeminDeliveryCategory3.setOnTouchAnimationListener {}
            baeminDeliveryCategory4.setOnTouchAnimationListener {}
            baeminDeliveryCategory5.setOnTouchAnimationListener {}
            baeminDeliveryCategory6.setOnTouchAnimationListener {}
            baeminDeliveryCategory7.setOnTouchAnimationListener {}
            baeminDeliveryCategory8.setOnTouchAnimationListener {}
            baeminDeliveryCategory9.setOnTouchAnimationListener {}
            baeminDeliveryCategory10.setOnTouchAnimationListener {}
            freshMenu.setOnTouchAnimationListener {}
            saleMenu.setOnTouchAnimationListener {}
            packagingMenu.setOnTouchAnimationListener {}
            localDelicaciesMenu.setOnTouchAnimationListener {}
            giftMenu.setOnTouchAnimationListener {}

            swipeRefresh.setSize(1000)

            swipeRefresh.setOnRefreshListener {
                lifecycleScope.launch {
                    delay(1000)
                    swipeRefresh.isRefreshing = false
                }
            }
        }

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
                            if (!job.isActive) scrollJobCreate()
                        }
                        ViewPager2.SCROLL_STATE_DRAGGING -> {
                            job.cancel()
                        }
                        ViewPager2.SCROLL_STATE_SETTLING -> {}
                    }
                }
            })

            showSaleDeliveryData(true)
        }


        homeViewModel.saleDeliveryList.observe(viewLifecycleOwner){
            val manager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false)
            binding.saleDeliveryList.adapter = saleDeliveryAdapter
            binding.saleDeliveryList.layoutManager = manager
            saleDeliveryAdapter.submitList(it)

            showSaleDeliveryData(false)
        }

    }


    private fun showSaleDeliveryData(isLoading : Boolean){
        if (isLoading){
            binding.loadingListView.startShimmer()
            binding.loadingListView.visibility = View.VISIBLE
            binding.saleDeliveryList.visibility = View.GONE
        }else{
            binding.loadingListView.stopShimmer()
            binding.loadingListView.visibility = View.GONE
            binding.saleDeliveryList.visibility = View.VISIBLE
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