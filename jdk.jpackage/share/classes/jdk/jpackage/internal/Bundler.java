package jdk.jpackage.internal;

import java.nio.file.Path;
import java.util.Map;

/**
 * Bundler
 *
 * The basic interface implemented by all Bundlers.
 */
public interface Bundler {
    /**
     * @return User Friendly name of this bundler.
     */
    String getName();

    /**
     * @return Command line identifier of the bundler.  Should be unique.
     */
    String getID();

    /**
     * @return The bundle type of the bundle that is created by this bundler.
     */
    String getBundleType();

    /**
     * Determines if this bundler will execute with the given parameters.
     *
     * @param params The parameters to be validate.  Validation may modify
     *               the map, so if you are going to be using the same map
     *               across multiple bundlers you should pass in a deep copy.
     * @return true if valid
     * @throws ConfigException If the configuration params are incorrect.  The
     *         exception may contain advice on how to modify the params map
     *         to make it valid.
     */
    public boolean validate(Map<String, ? super Object> params)
            throws ConfigException;

    /**
     * Creates a bundle from existing content.
     *
     * If a call to {@link #validate(java.util.Map)} date} returns true with
     * the parameters map, then you can expect a valid output.
     * However if an exception was thrown out of validate or it returned
     * false then you should not expect sensible results from this call.
     * It may or may not return a value, and it may or may not throw an
     * exception.  But any output should not be considered valid or sane.
     *
     * @param params The Bundle parameters,
     *               Keyed by the id from the ParamInfo.  Execution may
     *               modify the map, so if you are going to be using the
     *               same map across multiple bundlers you should pass
     *               in a deep copy.
     * @param outputParentDir
     *   The parent dir that the returned bundle will be placed in.
     * @return The resulting bundled file
     *
     * For a bundler that produces a single artifact file this will be the
     * location of that artifact (.exe file, .deb file, etc)
     *
     * For a bundler that produces a specific directory format output this will
     * be the location of that specific directory (.app file, etc).
     *
     * For a bundler that produce multiple files, this will be a parent
     * directory of those files (linux and windows images), whose name is not
     * relevant to the result.
     *
     * @throws java.lang.IllegalArgumentException for any of the following
     * reasons:
     *  <ul>
     *      <li>A required parameter is not found in the params list, for
     *      example missing the main class.</li>
     *      <li>A parameter has the wrong type of an object, for example a
     *      String where a File is required</li>
     *      <li>Bundler specific incompatibilities with the parameters, for
     *      example a bad version number format or an application id with
     *      forward slashes.</li>
     *  </ul>
     */
    public Path execute(Map<String, ? super Object> params,
            Path outputParentDir) throws PackagerException;

     /**
     * Removes temporary files that are used for bundling.
     */
    public void cleanup(Map<String, ? super Object> params);

    /**
     * Returns "true" if this bundler is supported on current platform.
     */
    public boolean supported(boolean runtimeInstaller);

    /**
     * Returns "true" if this bundler is he default for the current platform.
     */
    public boolean isDefault();
}
