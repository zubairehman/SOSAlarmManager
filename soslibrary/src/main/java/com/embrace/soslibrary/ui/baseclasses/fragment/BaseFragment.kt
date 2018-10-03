package com.embrace.soslibrary.ui.baseclasses.fragment

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.widget.ProgressBar
import com.embrace.soslibrary.R
import com.michaelflisar.rxbus2.interfaces.IRxBusQueue
import com.michaelflisar.rxbus2.rx.RxDisposableManager
import dagger.android.support.AndroidSupportInjection
import io.reactivex.processors.BehaviorProcessor
import org.reactivestreams.Publisher
import java.util.*
import javax.inject.Inject


abstract class BaseFragment : Fragment(), IRxBusQueue{

    private var isActive: Boolean = false

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    private val mResumedProcessor = BehaviorProcessor.createDefault(false)

    private val TAG = "BaseFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val extras = arguments
        if (extras != null) {
            getExtras(extras.getSerializable("extras") as ArrayList<*>)
        }
    }

    override fun onResume() {
        super.onResume()
        isActive = true
        mResumedProcessor.onNext(true)
    }

    override fun onPause() {
        mResumedProcessor.onNext(false)
        isActive = false
        super.onPause()
    }

    override fun onDestroy() {
        RxDisposableManager.unsubscribe(this)
        super.onDestroy()
    }

    // ----------------
    // Abstract Methods
    // ----------------

    abstract fun getExtras(extras: ArrayList<*>)

    // --------------
    // Interface RxBus
    // --------------

    override fun isBusResumed(): Boolean {
        return mResumedProcessor.value!!
    }

    override fun getResumeObservable(): Publisher<Boolean> {
        return mResumedProcessor
    }

    // --------------
    // Commons
    // --------------

    fun showLoading(progressBar: ProgressBar?, emptyView: View?) {
        progressBar?.visibility = View.VISIBLE
        emptyView?.visibility = View.GONE
        progressBar?.isIndeterminate = true
    }

    fun hideLoading(progressBar: ProgressBar?, view: View?) {

        //Show layout with animation
        view?.animate()
                ?.alpha(1f)
                ?.setDuration(resources.getInteger(R.integer.anim_duration_long).toLong())
                ?.setListener(null)

        //Hide Progress Layout
        progressBar?.animate()
                ?.translationY(progressBar.height.toFloat())
                ?.alpha(0.0f)
                ?.setDuration(resources.getInteger(R.integer.anim_duration_long).toLong())
                ?.setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        progressBar.visibility = View.GONE
                    }
                })
    }

    fun isThisFragmentActive(): Boolean {
        return isActive
    }

    fun addExtras(extras: ArrayList<Any>?): BaseFragment {
        if (extras != null) {
            val args = Bundle()
            args.putSerializable("extras", extras)
            this.arguments = args
        }
        return this
    }

}
