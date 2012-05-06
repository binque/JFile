/*
 * Copyright 2012 Bill La Forge
 *
 * This file is part of AgileWiki and is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License (LGPL) as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 * or navigate to the following url http://www.gnu.org/licenses/lgpl-2.1.txt
 *
 * Note however that only Scala, Java and JavaScript files are being covered by LGPL.
 * All other files are covered by the Common Public License (CPL).
 * A copy of this license is also included and can be
 * found as well at http://www.opensource.org/licenses/cpl1.0.txt
 */
package org.agilewiki.jfile;

import org.agilewiki.jactor.Actor;
import org.agilewiki.jactor.lpc.Request;
import org.agilewiki.jfile.block.Block;
import org.agilewiki.jid.scalar.vlens.actor.RootJid;

/**
 * Write the contents of a RootJid an then performs a force (flush) operation.
 */
public class WriteRootJid extends Request<Block, JFile> {
    public final RootJid rootJid;
    public final long position;
    public final int maxSize;

    /**
     * Write a RootJid and its header to the current position.
     *
     * @param rootJid The RootJid to be written.
     */
    public WriteRootJid(RootJid rootJid) {
        this.rootJid = rootJid;
        this.position = -1;
        maxSize = -1;
    }

    /**
     * Write a RootJid and its header.
     *
     * @param rootJid  The RootJid to be written.
     * @param position The location on disk where the RootJid and its header are to be written.
     */
    public WriteRootJid(RootJid rootJid, long position) {
        this.rootJid = rootJid;
        this.position = position;
        maxSize = -1;
    }

    /**
     * Write a RootJid and its header.
     * An exception is thrown if the total length of the data to be written exceeds maxSize.
     *
     * @param rootJid  The RootJid to be written.
     * @param position The location on disk where the RootJid and its header are to be written.
     * @param maxSize  The maximum length to be written.
     */
    public WriteRootJid(RootJid rootJid, long position, int maxSize) {
        this.rootJid = rootJid;
        this.position = position;
        this.maxSize = maxSize;
    }

    /**
     * Returns true when targetActor is an instanceof TARGET_TYPE
     *
     * @param targetActor The actor to be called.
     * @return True when targetActor is an instanceof TARGET_TYPE.
     */
    @Override
    public boolean isTargetType(Actor targetActor) {
        return targetActor instanceof JFile;
    }
}
