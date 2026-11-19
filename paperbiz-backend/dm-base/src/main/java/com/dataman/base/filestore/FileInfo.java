package com.dataman.base.filestore;

/** Metadata of a stored file. */
public record FileInfo(String id, String fileName, long length, String folder) {
}
