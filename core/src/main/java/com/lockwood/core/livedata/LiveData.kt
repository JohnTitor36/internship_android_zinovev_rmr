package com.lockwood.core.livedata

import androidx.fragment.app.Fragment
import androidx.lifecycle.*

fun <T : Any> LiveData<T>.requireValue(): T = checkNotNull(value)

fun <T> MutableLiveData<T>.onNext(next: T) {
    value = next
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

inline fun <X, Y> LiveData<X>.mapDistinct(crossinline transform: (X) -> Y): LiveData<Y> {
    return map(transform).distinctUntilChanged()
}