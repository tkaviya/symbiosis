package net.blaklizt.symbiosis.sym_sync.api;

import net.blaklizt.symbiosis.sym_sync.server.file.SymSyncFile;
import net.blaklizt.symbiosis.sym_sync.server.queue.SymSyncDataQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.LinkedList;

/******************************************************************************
 *                                                                            *
 *    Created:     13 / 09 / 2015                                             *
 *    Platform:    Red Hat Linux 9                                            *
 *    Author:      Tich de Blak (Tsungai Kaviya)                              *
 *    Copyright:   Blaklizt Entertainment                                     *
 *    Website:     http://www.blaklizt.net                                    *
 *    Contact:     blaklizt@gmail.com                                         *
 *                                                                            *
 *    This program is free software; you can redistribute it and/or modify    *
 *    it under the terms of the GNU General Public License as published by    *
 *    the Free Software Foundation; either version 2 of the License, or       *
 *    (at your option) any later version.                                     *
 *                                                                            *
 *    This program is distributed in the hope that it will be useful,         *
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of          *
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.    See the         *
 *    GNU General Public License for more details.                            *
 *                                                                            *
 ******************************************************************************/

@RestController
@RequestMapping("/users/")
public class SymSyncResource {

    @Autowired SymSyncDataQueue symSyncDataQueue;

    @RequestMapping(value = "/{userId}/files", method = RequestMethod.GET)
    public ResponseEntity<Object> prepareFiles(
            @PathVariable(value = "userId") Long symbiosisUserId,
            HttpServletRequest httpServletRequest) throws Exception {

        symSyncDataQueue.prepareFileQueue(symbiosisUserId, false);

        HttpHeaders httpHeaders = new HttpHeaders();
        String path = (String) httpServletRequest.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);

        httpHeaders.setLocation(new URI(path));

        return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}/files/{fileId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getFile(
            @PathVariable(value = "userId") Long symbiosisUserId,
            @PathVariable(value = "fileId") Long fileId,
            HttpServletRequest httpServletRequest) throws Exception {

        LinkedList<SymSyncFile> syncFiles = symSyncDataQueue.getFiles(symbiosisUserId);

        HttpHeaders httpHeaders = new HttpHeaders();
        String path = (String) httpServletRequest.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);

        httpHeaders.setLocation(new URI(path));

        Object result = syncFiles.get(0).getFileName();

        return new ResponseEntity<>(result, httpHeaders, HttpStatus.OK);
    }
}
