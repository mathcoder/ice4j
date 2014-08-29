/*
 * ice4j, the OpenSource Java Solution for NAT and Firewall Traversal.
 * Maintained by the SIP Communicator community (http://sip-communicator.org).
 *
 * Distributable under LGPL license. See terms of license at gnu.org.
 */
package org.ice4j.ice;

import org.ice4j.*;
import org.ice4j.socket.*;

import java.util.*;

/**
 * Extends {@link org.ice4j.ice.HostCandidate} allowing the instance to have
 * a list of <tt>Socket</tt>s instead of just one socket. This is needed,
 * because with TCP, connections from different remote addresses result in
 * different <tt>Socket</tt> instances.
 *
 * @author Boris Grozev
 */
public class TcpHostCandidate
    extends HostCandidate
{
    /**
     * List of <tt>accept</tt>ed sockets for this <tt>TcpHostCandidate</tt>.
     */
    private final List<IceSocketWrapper> sockets
            = new LinkedList<IceSocketWrapper>();

    /**
     * Initializes a new <tt>TcpHostCandidate</tt>.
     *
     * @param transportAddress the transport address of this
     * <tt>TcpHostCandidate</tt>.
     * @param parentComponent the <tt>Component</tt> that this candidate
     * belongs to.
     */
    public TcpHostCandidate(TransportAddress transportAddress, Component parentComponent)
    {
        super(transportAddress,
              parentComponent);
    }

    public List<IceSocketWrapper> getIceSocketWrappers()
    {
        return sockets;
    }

    public void addSocket(IceSocketWrapper socket)
    {
        sockets.add(socket);
    }

    @Override
    protected void free()
    {
        super.free();
        for (IceSocketWrapper socket : sockets)
        {
            socket.close();
        }

    }
}