package scooters.coup.com.coup

import android.databinding.DataBindingUtil
import android.support.v4.app.FragmentActivity
import android.os.Bundle
import android.os.PersistableBundle

import scooters.coup.com.coup.dagger.DaggerDependencies
import scooters.coup.com.coup.databinding.ActivityMapsBinding
import scooters.coup.com.coup.vm.MapState
import scooters.coup.com.coup.vm.MapState.*
import scooters.coup.com.coup.vm.ViewModel
import scooters.coup.com.coup.vm.setLifeCycleState
import javax.inject.Inject

class MapsActivity : FragmentActivity() {

    @Inject
    lateinit var viewModel: ViewModel

    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerDependencies.inject(this)
        setUpBinding()
        binding.setMapLifeCycleState(CREATE, savedInstanceState)
        viewModel.fetchScooters()
    }

    private fun setUpBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_maps)
        binding.viewModel = viewModel
        binding.executePendingBindings()
    }

    override fun onStart() {
        super.onStart()
        binding.setMapLifeCycleState(START, null)
    }

    override fun onResume() {
        super.onResume()
        binding.setMapLifeCycleState(RESUME, null)
    }

    override fun onPause() {
        binding.setMapLifeCycleState(PAUSE, null)
        super.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        binding.setMapLifeCycleState(SAVE_INSTANCE, outState)
        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onStop() {
        binding.setMapLifeCycleState(STOP, null)
        super.onStop()
    }

    override fun onDestroy() {
        binding.setMapLifeCycleState(DESTROY, null)
        viewModel.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        binding.setMapLifeCycleState(LOW_MEMORY, null)
        super.onLowMemory()
    }

    private fun ActivityMapsBinding.setMapLifeCycleState(mapState: MapState, savedInstanceState: Bundle?) {
        map.setLifeCycleState(mapState, savedInstanceState)
    }
}
