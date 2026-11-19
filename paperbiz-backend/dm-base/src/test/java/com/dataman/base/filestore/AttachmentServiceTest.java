package com.dataman.base.filestore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.dataman.base.exception.DmException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class AttachmentServiceTest {

    @TempDir
    Path tmp;

    private final MongoFileStore store = mock(MongoFileStore.class);
    private final FileLinkRepository links = mock(FileLinkRepository.class);
    private final AttachmentService service = new AttachmentService(store, links);

    @Test
    void requiredArgumentsKeepLegacyMessages() {
        assertEquals("DocId not provided to Upload File.",
                assertThrows(DmException.class, () -> service.saveFile("db", " ", "1", "R", "a.txt", tmp, "u")).getMessage());
        assertEquals("SNo. not provided to Upload File.",
                assertThrows(DmException.class, () -> service.saveFile("db", "D", "", "R", "a.txt", tmp, "u")).getMessage());
        assertEquals("RuleCode not provided to Upload File.",
                assertThrows(DmException.class, () -> service.saveFile("db", "D", "1", null, "a.txt", tmp, "u")).getMessage());
        assertEquals("File not found to get Uploaded.",
                assertThrows(DmException.class, () -> service.saveFile("db", "D", "1", "R", "a.txt", tmp.resolve("x"), "u")).getMessage());
    }

    @Test
    void saveStoresThenLinks() throws IOException {
        Path f = tmp.resolve("a.txt");
        Files.writeString(f, "hello");
        when(store.store(eq("db"), eq("R"), any(), eq("a.txt"), any(InputStream.class))).thenReturn("abc");
        assertEquals("abc", service.saveFile("db", "D", "1", "R", "a.txt", f, "u"));
        verify(links).insert("abc", "D", "1", "R", "a.txt", 5, "u");
    }

    @Test
    void uploadedFileIsRemovedWhenLinkingFails() throws IOException {
        Path f = tmp.resolve("a.txt");
        Files.writeString(f, "hello");
        when(store.store(eq("db"), eq("R"), any(), eq("a.txt"), any(InputStream.class))).thenReturn("abc");
        doThrow(new IllegalStateException("db down")).when(links).insert(any(), any(), any(), any(), any(), anyLong(), any());
        assertThrows(IllegalStateException.class, () -> service.saveFile("db", "D", "1", "R", "a.txt", f, "u"));
        verify(store).delete("db", "R", "abc");
    }

    @Test
    void deleteRemovesEveryLinkedFileThenTheLinks() {
        when(links.fileLinks("D", "1", "R")).thenReturn(List.of("a", "b"));
        service.deleteFiles("db", "D", "1", "R");
        verify(store).delete("db", "R", "a");
        verify(store).delete("db", "R", "b");
        verify(links).delete("D", "1", "R");
    }
}
