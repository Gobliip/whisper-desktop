package com.gobliip.whisper.web.controllers;

import com.gobliip.chronos.client.retrofit.AttachmentsResource;
import org.apache.commons.io.IOUtils;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit.client.Response;

import java.io.IOException;

/**
 * Created by lsamayoa on 9/08/15.
 */
@RestController
@RequestMapping("/attachments")
public class AttachmentsController {
    @Autowired
    private AttachmentsResource attachmentsResource;

    @RequestMapping(value = "/{attachmentId}/raw")
    public ResponseEntity<byte[]> getRaw(
            @PathVariable("attachmentId") Long attachmentId
    ) throws IOException {
        final Response resp = attachmentsResource.getRaw(attachmentId);
        final byte[] attachment = IOUtils.toByteArray(resp.getBody().in());
        final Tika tika = new Tika();
        final HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.parseMediaType(tika.detect(attachment)));
        return new ResponseEntity<>(attachment, responseHeaders, HttpStatus.OK);
    }
}
