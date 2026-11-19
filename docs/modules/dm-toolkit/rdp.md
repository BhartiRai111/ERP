# dm-toolkit / rdp

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/rdp/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Files
| file | lines |
|---|---|
| AbstractRdp4jListener.java | 67 |
| AfterPollingCycleEvent.java | 10 |
| AfterStopEvent.java | 10 |
| BeforePollingCycleEvent.java | 10 |
| BeforeStartEvent.java | 6 |
| CachedFileElement.java | 37 |
| DefaultFileFilter.java | 13 |
| DirectoryListener.java | 13 |
| DirectoryPoller.java | 272 |
| DirectoryPollerBuilder.java | 104 |
| DirectoryPollerException.java | 24 |
| DirectoryPollerFuture.java | 27 |
| DirectoryPollerListener.java | 9 |
| Event.java | 6 |
| EventExposingDirectoryPoller.java | 16 |
| EventExposingFileElement.java | 19 |
| EventExposingPolledDirectory.java | 19 |
| FileAddedEvent.java | 13 |
| FileFilter.java | 10 |
| FileModifiedEvent.java | 13 |
| FileRemovedEvent.java | 13 |
| HashMapComparer.java | 71 |
| InitialContentEvent.java | 25 |
| InitialContentListener.java | 8 |
| IoErrorCeasedEvent.java | 12 |
| IoErrorListener.java | 9 |
| IoErrorRaisedEvent.java | 21 |
| JavaIoFileAdapter.java | 119 |
| ListenerNotifier.java | 172 |
| PollCycleListener.java | 10 |
| Poller.java | 299 |
| Rdp4jListener.java | 6 |
| RegexFileFilter.java | 20 |
| ScheduledRunnable.java | 79 |
| Util.java | 57 |
| spi/FileElement.java | 13 |
| spi/PolledDirectory.java | 26 |

## Depends on (imports from other packages)
`dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.rdp.spi`, `dataman.dmToolkit.sftp.server`, `dataman.queue.BigQueueImpl`, `dataman.queue.IBigQueue`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
**Directory poller** (file system / SFTP me nayi/badli/hatayi file par event: `FileAddedEvent`, `FileModifiedEvent`, `FileRemovedEvent`, regex filter, scheduled polling; rdp4j jaisi library ka copy, `dataman.queue` ke saath). `sftp` isi par bana hai. Naye project me Spring Integration / WatchService.
