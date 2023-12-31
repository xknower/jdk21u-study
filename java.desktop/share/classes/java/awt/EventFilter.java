package java.awt;

interface EventFilter {

    /**
     * Enumeration for possible values for {@code acceptEvent(AWTEvent ev)} method.
     * @see EventDispatchThread#pumpEventsForFilter
     */
    static enum FilterAction {
        /**
         * ACCEPT means that this filter do not filter the event and allows other
         * active filters to proceed it. If all the active filters accept the event, it
         * is dispatched by the {@code EventDispatchThread}
         * @see EventDispatchThread#pumpEventsForFilter
         */
        ACCEPT,
        /**
         * REJECT means that this filter filter the event. No other filters are queried,
         * and the event is not dispatched by the {@code EventDispatchedThread}
         * @see EventDispatchThread#pumpEventsForFilter
         */
        REJECT,
        /**
         * ACCEPT_IMMEDIATELY means that this filter do not filter the event, no other
         * filters are queried and to proceed it, and it is dispatched by the
         * {@code EventDispatchThread}
         * It is not recommended to use ACCEPT_IMMEDIATELY as there may be some active
         * filters not queried yet that do not accept this event. It is primarily used
         * by modal filters.
         * @see EventDispatchThread#pumpEventsForFilter
         * @see ModalEventFilter
         */
        ACCEPT_IMMEDIATELY
    }

    FilterAction acceptEvent(AWTEvent ev);
}
