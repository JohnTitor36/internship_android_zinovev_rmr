package com.lockwood.core.livedata

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

fun <T : Any> LiveData<T>.requireValue(): T = checkNotNull(value)

fun <T> MutableLiveData<T>.onNext(next: T) {
    value = next
}

/**
 * Подписка на [LiveData] c отслеживанием изменений через [observer].
 *
 * Пример подписки:
 * ```
 *  lateinit var viewModel: MyViewModel
 *
 *  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
 *      super.onViewCreated(view, savedInstanceState)
 *      val observer = Observer<String> { viewModel.checkIsValidCredentialsLength() }
 *      observe(viewModel.liveData, observer)
 *  }
 * ```
 */
fun <T, LD : LiveData<T>> Fragment.observe(
    liveData: LD,
    observer: Observer<T>
) {
    liveData.observe(viewLifecycleOwner, observer)
}

/**
 * Подписка на [LiveData].
 *
 * Пример подписки на изменения состояния:
 * ```
 *  lateinit var viewModel: MyViewModel
 *
 *  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
 *      super.onViewCreated(view, savedInstanceState)
 *      observe(viewModel.state, ::renderState)
 *  }
 * ```
 */
inline fun <T, LD : LiveData<T>> Fragment.observe(
    liveData: LD,
    crossinline block: (T) -> Unit
) {
    liveData.observe(viewLifecycleOwner, Observer { block(it) })
}