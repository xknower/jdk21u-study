/**
 * Defines the JMX management agent.
 *
 * <p> This module allows a Java Virtual Machine to be monitored and managed
 * via JMX API.  See more information from the
 * {@extLink monitoring_and_management_using_jmx_technology
 * Monitoring and Management Using JMX} guide.
 *
 * @moduleGraph
 * @since 9
 */
module jdk.management.agent {
    requires java.management;
    requires java.management.rmi;

    exports jdk.internal.agent to jdk.jconsole;
}
