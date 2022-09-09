package com.triple.kotprac.common.event

import org.springframework.context.ApplicationEventPublisher


class Events {
    companion object {
        private var publisher: ApplicationEventPublisher? = null
        fun raise(event: Any) {
            publisher?.publishEvent(event)
        }

        fun setPublisher(publisher: ApplicationEventPublisher) {
            Events.publisher = publisher
        }
    }
}