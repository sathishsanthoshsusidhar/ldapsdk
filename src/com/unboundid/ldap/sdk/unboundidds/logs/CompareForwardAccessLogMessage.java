/*
 * Copyright 2009-2018 Ping Identity Corporation
 * All Rights Reserved.
 */
/*
 * Copyright (C) 2015-2018 Ping Identity Corporation
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (GPLv2 only)
 * or the terms of the GNU Lesser General Public License (LGPLv2.1 only)
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see <http://www.gnu.org/licenses>.
 */
package com.unboundid.ldap.sdk.unboundidds.logs;



import com.unboundid.util.NotMutable;
import com.unboundid.util.ThreadSafety;
import com.unboundid.util.ThreadSafetyLevel;



/**
 * This class provides a data structure that holds information about a log
 * message that may appear in the Directory Server access log about a compare
 * request forwarded to a backend server.
 * <BR>
 * <BLOCKQUOTE>
 *   <B>NOTE:</B>  This class, and other classes within the
 *   {@code com.unboundid.ldap.sdk.unboundidds} package structure, are only
 *   supported for use against Ping Identity, UnboundID, and
 *   Nokia/Alcatel-Lucent 8661 server products.  These classes provide support
 *   for proprietary functionality or for external specifications that are not
 *   considered stable or mature enough to be guaranteed to work in an
 *   interoperable way with other types of LDAP servers.
 * </BLOCKQUOTE>
 */
@NotMutable()
@ThreadSafety(level=ThreadSafetyLevel.COMPLETELY_THREADSAFE)
public final class CompareForwardAccessLogMessage
       extends CompareRequestAccessLogMessage
{
  /**
   * The serial version UID for this serializable class.
   */
  private static final long serialVersionUID = -6923633064379281603L;



  // The port of the backend server to which the request has been forwarded.
  private final Integer targetPort;

  // The address of the backend server to which the request has been forwarded.
  private final String targetHost;

  // The protocol used to forward the request to the backend server.
  private final String targetProtocol;



  /**
   * Creates a new compare forward access log message from the provided message
   * string.
   *
   * @param  s  The string to be parsed as a compare forward access log message.
   *
   * @throws  LogException  If the provided string cannot be parsed as a valid
   *                        log message.
   */
  public CompareForwardAccessLogMessage(final String s)
         throws LogException
  {
    this(new LogMessage(s));
  }



  /**
   * Creates a new compare forward access log message from the provided log
   * message.
   *
   * @param  m  The log message to be parsed as a compare forward access log
   *            message.
   */
  public CompareForwardAccessLogMessage(final LogMessage m)
  {
    super(m);

    targetHost     = getNamedValue("targetHost");
    targetPort     = getNamedValueAsInteger("targetPort");
    targetProtocol = getNamedValue("targetProtocol");
  }



  /**
   * Retrieves the address of the backend server to which the request has been
   * forwarded.
   *
   * @return  The address of the backend server to which the request has been
   *          forwarded, or {@code null} if it is not included in the log
   *          message.
   */
  public String getTargetHost()
  {
    return targetHost;
  }



  /**
   * Retrieves the port of the backend server to which the request has been
   * forwarded.
   *
   * @return  The port of the backend server to which the request has been
   *          forwarded, or {@code null} if it is not included in the log
   *          message.
   */
  public Integer getTargetPort()
  {
    return targetPort;
  }



  /**
   * Retrieves the protocol used to forward the request to the backend server.
   *
   * @return  The protocol used to forward the request to the backend server, or
   *          {@code null} if it is not included in the log message.
   */
  public String getTargetProtocol()
  {
    return targetProtocol;
  }



  /**
   * {@inheritDoc}
   */
  @Override()
  public AccessLogMessageType getMessageType()
  {
    return AccessLogMessageType.FORWARD;
  }
}
