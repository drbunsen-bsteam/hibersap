/*
 * Copyright (c) 2008-2014 akquinet tech@spree GmbH
 *
 * This file is part of Hibersap.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this software except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.hibersap.execution;

import org.hibersap.session.Credentials;
import org.hibersap.session.SessionImplementor;
import org.hibersap.session.Transaction;

import java.util.Map;

/*
 * Implementations of this interface define the functionality how to communicate with SAP, using for
 * example the SAP Java Connector or a JCA resource adapter. The implementation to be used by a
 * session manager is specified by the <code>context</code> element of the hibersap.xml. The default
 * implementation is org.hibersap.execution.jco.JCoConnection. Implementations must provide a
 * default constructor.
 * 
 * @author Carsten Erker
 */
public interface Connection {

    /**
     * Set the credentials for the session. If custom credentials are provided, this method must be
     * called before the execute() method is called for the first time on the Session this
     * Connection belongs to.
     * <p/>
     * The credentials overwrite the configured properties. Only the credential fields that are set
     * (i.e. that are not null) will change the default behavior.
     *
     * @param credentials The Credentials
     */
    void setCredentials( Credentials credentials );

    /**
     * Begins a logical unit of work.
     *
     * @param session The Session this Connection belongs to.
     * @return The Transaction
     */
    Transaction beginTransaction( SessionImplementor session );

    /**
     * Returns the current transaction.
     *
     * @return The Transaction
     */
    Transaction getTransaction();

    /**
     * Calls a remote function module in the SAP system.
     *
     * @param bapiName    The function module name
     * @param functionMap The function module parameters
     */
    void execute( String bapiName, Map<String, Object> functionMap );

    /**
     * Closes this connection. Implementing classes must do everything that is needed to free
     * resources etc.
     */
    void close();
}
