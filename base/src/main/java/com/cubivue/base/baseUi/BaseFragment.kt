package com.cubivue.base.baseUi

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.support.v4.app.Fragment
import android.view.View
import android.widget.ProgressBar
import com.cubivue.base.R
import com.michaelflisar.rxbus2.interfaces.IRxBusQueue
import com.michaelflisar.rxbus2.rx.RxDisposableManager
import dagger.android.support.AndroidSupportInjection
import io.reactivex.processors.BehaviorProcessor
import org.reactivestreams.Publisher
import javax.inject.Inject


abstract class BaseFragment : Fragment(), IRxBusQueue{

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    private val mResumedProcessor = BehaviorProcessor.createDefault(false)

    private val TAG = "BaseFragment"

    override fun onResume() {
        super.onResume()
        mResumedProcessor.onNext(true)
    }

    override fun onPause() {
        mResumedProcessor.onNext(false)
        super.onPause()
    }

    override fun onDestroy() {
        RxDisposableManager.unsubscribe(this)
        super.onDestroy()
    }

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
}
