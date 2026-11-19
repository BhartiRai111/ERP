# dm-base / dataman_queue_cache

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmBase/src/dataman_queue_cache/`). Hand-written notes are in the "Notes" section.

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
| ILRUCache.java | 25 |
| LRUCacheImpl.java | 230 |

## Depends on (imports from other packages)


## Classes (plain classes: auto-extracted)

### ILRUCache (ILRUCache.java, 25 lines)

Public methods:
- `void put(final K key, final V value, final long ttlInMilliSeconds)`
- `void put(final K key, final V value)`
- `V get(final K key)`
- `void release(final K key)`
- `V remove(final K key)`
- `void removeAll()`
- `int size()`
- `Collection<V> getValues()`

### LRUCacheImpl (LRUCacheImpl.java, 230 lines)
class; extends/implements: `ILRUCache<K, V>`

Public methods:
- `void CloseExecutorService()`
- `void put(K key, V value, long ttlInMilliSeconds)`
- `void put(K key, V value)`
- `V get(K key)`
- `void run()`
- `void release(K key)`
- `int size()`
- `void removeAll()`
- `V remove(K key)`
- `Collection<V> getValues()`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
