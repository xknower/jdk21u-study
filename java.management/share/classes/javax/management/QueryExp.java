package javax.management;

// java import
import java.io.Serializable;


/**
 * <p>Represents relational constraints similar to database query "where
 * clauses". Instances of QueryExp are returned by the static methods of the
 * {@link Query} class.</p>
 *
 * <p>It is possible, but not
 * recommended, to create custom queries by implementing this
 * interface.  In that case, it is better to extend the {@link
 * QueryEval} class than to implement the interface directly, so that
 * the {@link #setMBeanServer} method works correctly.
 *
 * @see MBeanServer#queryNames MBeanServer.queryNames
 * @since 1.5
 */
public interface QueryExp extends Serializable {


     /**
      * Applies the QueryExp on an MBean.
      *
      * @param name The name of the MBean on which the QueryExp will be applied.
      *
      * @return  True if the query was successfully applied to the MBean, false otherwise
      *
      * @throws BadStringOperationException when an invalid string
      * operation is passed to a method for constructing a query
      * @throws BadBinaryOpValueExpException when an invalid expression
      * is passed to a method for constructing a query
      * @throws BadAttributeValueExpException when an invalid MBean
      * attribute is passed to a query constructing method
      * @throws InvalidApplicationException when an invalid apply is attempted
      */
     public boolean apply(ObjectName name) throws BadStringOperationException, BadBinaryOpValueExpException,
         BadAttributeValueExpException, InvalidApplicationException ;

     /**
      * Sets the MBean server on which the query is to be performed.
      *
      * @param s The MBean server on which the query is to be performed.
      */
     public void setMBeanServer(MBeanServer s) ;

 }
