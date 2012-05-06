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

import org.agilewiki.jfile.block.Block;

/**
 * Write the contents of a RootJid an then performs a force (flush) operation.
 */
public class ForcedWriteRootJid extends WriteRootJid {
    /**
     * Write a RootJid and its header to the current position,
     * and then force the operation to complete.
     *
     * @param block The Block used to manage the operation.
     */
    public ForcedWriteRootJid(Block block) {
        super(block);
    }

    /**
     * Write a RootJid and its header,
     * and then force the operation to complete.
     * An exception is thrown if the total length of the data to be written exceeds maxSize.
     *
     * @param block   The Block used to manage the operation.
     * @param maxSize The maximum length to be written.
     */
    public ForcedWriteRootJid(Block block, int maxSize) {
        super(block, maxSize);
    }
}
