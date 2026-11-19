# dm-base / dataman_queue

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmBase/src/dataman_queue/`). Hand-written notes are in the "Notes" section.

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
| BigArrayImpl.java | 617 |
| BigQueueImpl.java | 260 |
| FanOutQueueImpl.java | 441 |
| IBigArray.java | 44 |
| IBigQueue.java | 37 |
| IFanOutQueue.java | 56 |

## Depends on (imports from other packages)
`dataman.queue.page.IMappedPage`, `dataman.queue.page.IMappedPageFactory`, `dataman.queue.page.MappedPageFactoryImpl`, `dataman.queue.utils.Calculator`, `dataman.queue.utils.FileUtil`, `dataman.queue.utils.FolderNameValidator`

## Classes (plain classes: auto-extracted)

### BigArrayImpl (BigArrayImpl.java, 595 lines)
class; extends/implements: `IBigArray`

Public methods:
- `String getArrayDirectory()`
- `void removeAll()`
- `void removeBeforeIndex(long index)`
- `void removeBefore(long timestamp)`
- `long append(byte[] data)`
- `void flush()`
- `byte[] get(long index)`
- `long getTimestamp(long index)`
- `long size()`
- `long getHeadIndex()`
- `long getTailIndex()`
- `boolean isEmpty()`
- `boolean isFull()`
- `void close()`
- `int getDataPageSize()`
- `long findClosestIndex(long timestamp)`
- `long getBackFileSize()`
- `void limitBackFileSize(long sizeLimit)`
- `int getItemLength(long index)`

### BigQueueImpl (BigQueueImpl.java, 253 lines)
class; extends/implements: `IBigQueue`

Public methods:
- `boolean isEmpty()`
- `void enqueue(byte[] data)`
- `byte[] dequeue()`
- `ListenableFuture<byte[]> dequeueAsync()`
- `void removeAll()`
- `byte[] peek()`
- `ListenableFuture<byte[]> peekAsync()`
- `void applyForEach(ItemIterator iterator)`
- `void close()`
- `void gc()`
- `void flush()`
- `long size()`

### FanOutQueueImpl (FanOutQueueImpl.java, 433 lines)
class; extends/implements: `IFanOutQueue`

Public methods:
- `boolean isEmpty(String fanoutId)`
- `boolean isEmpty()`
- `long enqueue(byte[] data)`
- `byte[] dequeue(String fanoutId)`
- `byte[] peek(String fanoutId)`
- `int peekLength(String fanoutId)`
- `long peekTimestamp(String fanoutId)`
- `byte[] get(long index)`
- `int getLength(long index)`
- `long getTimestamp(long index)`
- `void removeBefore(long timestamp)`
- `void limitBackFileSize(long sizeLimit)`
- `long getBackFileSize()`
- `long findClosestIndex(long timestamp)`
- `void resetQueueFrontIndex(String fanoutId, long index)`
- `long size(String fanoutId)`
- `long size()`
- `void flush()`
- `void close()`
- `void removeAll()`
- `long getFrontIndex()`
- `long getRearIndex()`
- `long getFrontIndex(String fanoutId)`

### IBigArray (IBigArray.java, 44 lines)
interface; extends/implements: `Closeable`

### IBigQueue (IBigQueue.java, 37 lines)
interface; extends/implements: `Closeable`

Public methods:
- `boolean isEmpty()`
- `void enqueue(byte[] data)`
- `byte[] dequeue()`
- `ListenableFuture<byte[]> dequeueAsync()`
- `void removeAll()`
- `byte[] peek()`
- `ListenableFuture<byte[]> peekAsync()`
- `void applyForEach(ItemIterator iterator)`
- `void gc()`
- `void flush()`
- `long size()`
- `void forEach(byte[] item)`

### IFanOutQueue (IFanOutQueue.java, 56 lines)
interface; extends/implements: `Closeable`

Public methods:
- `boolean isEmpty(String fanoutId)`
- `boolean isEmpty()`
- `long enqueue(byte[] data)`
- `byte[] dequeue(String fanoutId)`
- `byte[] peek(String fanoutId)`
- `int peekLength(String fanoutId)`
- `long peekTimestamp(String fanoutId)`
- `byte[] get(long index)`
- `int getLength(long index)`
- `long getTimestamp(long index)`
- `long size(String fanoutId)`
- `long size()`
- `void flush()`
- `void removeAll()`
- `long getFrontIndex()`
- `long getFrontIndex(String fanoutId)`
- `long getRearIndex()`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
