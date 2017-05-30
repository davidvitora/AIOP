/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aiop.persistencia.entidades.timeline.file;

import br.com.aiop.persistencia.entidades.timeline.GenericTimeline;

/**
 *
 * @author David .V
 */
public class TimelineFileUpload extends GenericTimeline{
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
