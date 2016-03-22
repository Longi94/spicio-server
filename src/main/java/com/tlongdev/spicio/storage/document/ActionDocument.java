package com.tlongdev.spicio.storage.document;

/**
 * @author Long
 * @since 2016. 03. 19.
 */
public class ActionDocument {

    private long id;

    private long timeStamp;

    private int type;

    private UserSimpleDocument culprit;

    private UserSimpleDocument victim;

    private SeriesDocument series;

    private EpisodeDocument episode;
}
