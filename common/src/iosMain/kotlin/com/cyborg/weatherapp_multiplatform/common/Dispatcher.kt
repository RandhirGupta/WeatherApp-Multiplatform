package com.cyborg.weatherapp_multiplatform.common

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

internal actual val ApplicationDispatcher: CoroutineContext =
    NsQueueDispatcher(/*dispatch_get_main_queue()*/)

internal class NsQueueDispatcher : CoroutineDispatcher() {
    override fun dispatch(context: CoroutineContext, block: Runnable) {
//        dispatch_async(dispatchQueue) {
//            block.run()
//        }
    }
}