package com.yz.common.extensions

import kotlinx.coroutines.*

fun <T> CoroutineScope.lazyCoroutine(
    block: suspend CoroutineScope.() -> T
): Deferred<T> {
    return async(start = CoroutineStart.LAZY) { block() }
}