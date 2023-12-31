package com.sun.net.httpserver;

/**
 * Authenticator represents an implementation of an HTTP authentication
 * mechanism. Sub-classes provide implementations of specific mechanisms
 * such as Digest or Basic auth. Instances are invoked to provide verification
 * of the authentication information provided in all incoming requests.
 * Note. This implies that any caching of credentials or other authentication
 * information must be done outside of this class.
 */
public abstract class Authenticator {

    /**
     * Constructor for subclasses to call.
     */
    protected Authenticator () { }

    /**
     * Base class for return type from {@link #authenticate(HttpExchange)} method.
     */
    public abstract static class Result {

        /**
         * Constructor for subclasses to call.
         */
        protected Result () {}
    }

    /**
     * Indicates an authentication failure. The authentication
     * attempt has completed.
     */
    public static class Failure extends Result {

        private int responseCode;

        /**
         * Creates a {@code Failure} instance with given response code.
         *
         * @param responseCode the response code to associate with this
         *                     {@code Failure} instance
         */
        public Failure (int responseCode) {
            this.responseCode = responseCode;
        }

        /**
         * Returns the response code to send to the client.
         *
         * @return the response code associated with this {@code Failure} instance
         */
        public int getResponseCode() {
            return responseCode;
        }
    }

    /**
     * Indicates an authentication has succeeded and the
     * authenticated user {@linkplain HttpPrincipal principal} can be acquired by calling
     * {@link #getPrincipal()}.
     */
    public static class Success extends Result {
        private HttpPrincipal principal;

        /**
         * Creates a {@code Success} instance with given {@code Principal}.
         *
         * @param p the authenticated user you wish to set as {@code Principal}
         */
        public Success (HttpPrincipal p) {
            principal = p;
        }

        /**
         * Returns the authenticated user {@code Principal}.
         *
         * @return the {@code Principal} instance associated with the authenticated user
         *
         */
        public HttpPrincipal getPrincipal() {
            return principal;
        }
    }

    /**
     * Indicates an authentication must be retried. The
     * response code to be sent back is as returned from
     * {@link #getResponseCode()}. The {@code Authenticator} must also have
     * set any necessary response headers in the given {@link HttpExchange}
     * before returning this {@code Retry} object.
     */
    public static class Retry extends Result {

        private int responseCode;

        /**
         * Creates a {@code Retry} instance with given response code.
         *
         * @param responseCode the response code to associate with this
         *                     {@code Retry} instance
         */
        public Retry (int responseCode) {
            this.responseCode = responseCode;
        }

        /**
         * Returns the response code to send to the client.
         *
         * @return the response code associated with this {@code Retry} instance
         */
        public int getResponseCode() {
            return responseCode;
        }
    }

    /**
     * Called to authenticate each incoming request. The implementation
     * must return a {@link Failure}, {@link Success} or {@link Retry} object as appropriate:
     * <ul>
     *     <li> {@code Failure} means the authentication has completed, but has
     *     failed due to invalid credentials.
     *     <li> {@code Success} means that the authentication has succeeded,
     *     and a {@code Principal} object representing the user can be retrieved
     *     by calling {@link Success#getPrincipal()}.
     *     <li> {@code Retry} means that another HTTP {@linkplain HttpExchange exchange}
     *     is required. Any response headers needing to be sent back to the client are set
     *     in the given {@code HttpExchange}. The response code to be returned must be
     *     provided in the {@code Retry} object. {@code Retry} may occur multiple times.
     * </ul>
     *
     * @param exch the {@code HttpExchange} upon which authenticate is called
     * @return the result
     */
    public abstract Result authenticate (HttpExchange exch);
}
