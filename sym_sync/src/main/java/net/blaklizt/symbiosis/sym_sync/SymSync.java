package net.blaklizt.symbiosis.sym_sync;

import net.blaklizt.symbiosis.sym_sync.server.queue.SymSyncDataQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ***************************************************************************
 * *
 * Created:     26 / 09 / 2015                                             *
 * Author:      Tich de Blak (Tsungai Kaviya)                              *
 * Copyright:   Blaklizt Entertainment                                     *
 * Website:     http://www.blaklizt.net                                    *
 * Contact:     blaklizt@gmail.com                                         *
 * *
 * This program is free software; you can redistribute it and/or modify    *
 * it under the terms of the GNU General Public License as published by    *
 * the Free Software Foundation; either version 2 of the License, or       *
 * (at your option) any later version.                                     *
 * *
 * This program is distributed in the hope that it will be useful,         *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of          *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.    See the         *
 * GNU General Public License for more details.                            *
 * *
 * ****************************************************************************
 */

@Service
public class SymSync {

    @Autowired
    SymSyncDataQueue symSyncDataQueue;

//    @Autowired DataTransport dataTransport;

    SymSync() {
        symSyncDataQueue.prepareDefaultFileQueue(true);
    }
}
