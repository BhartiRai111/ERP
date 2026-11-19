# dm-base / dataman_queue_page

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmBase/src/dataman_queue_page/`). Hand-written notes are in the "Notes" section.

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
| IMappedPage.java | 21 |
| IMappedPageFactory.java | 45 |
| MappedPageFactoryImpl.java | 311 |
| MappedPageImpl.java | 165 |

## Depends on (imports from other packages)
`dataman.queue.cache.ILRUCache`, `dataman.queue.cache.LRUCacheImpl`, `dataman.queue.utils.FileUtil`

## Classes (plain classes: auto-extracted)

### IMappedPage (IMappedPage.java, 21 lines)

Public methods:
- `byte[] getLocal(int position, int length)`
- `void flush()`

### IMappedPageFactory (IMappedPageFactory.java, 45 lines)

### MappedPageFactoryImpl (MappedPageFactoryImpl.java, 310 lines)
class; extends/implements: `IMappedPageFactory`

Public methods:
- `IMappedPage acquirePage(long index)`
- `int getPageSize()`
- `String getPageDir()`
- `void releasePage(long index)`
- `void releaseCachedPages()`
- `void deleteAllPages()`
- `void deletePages(Set<Long> indexes)`
- `void deletePage(long index)`
- `Set<Long> getPageIndexSetBefore(long timestamp)`
- `void deletePagesBefore(long timestamp)`
- `void deletePagesBeforePageIndex(long pageIndex)`
- `Set<Long> getExistingBackFileIndexSet()`
- `int getCacheSize()`
- `long getPageFileLastModifiedTime(long index)`
- `long getFirstPageIndexBefore(long timestamp)`
- `void flush()`
- `Set<String> getBackPageFileSet()`
- `long getBackPageFileSize()`

### MappedPageImpl (MappedPageImpl.java, 165 lines)
class; extends/implements: `IMappedPage, Closeable`

Public methods:
- `void close()`
- `void setDirty(boolean dirty)`
- `void flush()`
- `byte[] getLocal(int position, int length)`
- `ByteBuffer getLocal(int position)`
- `void clean(ByteBuffer buffer)`
- `ByteBuffer getSourceBuffer()`
- `boolean isClosed()`
- `String toString()`
- `String getPageFile()`
- `long getPageIndex()`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
