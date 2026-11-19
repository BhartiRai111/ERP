# dm-plugin: module note (survey)

> Status: DRAFT (auto-survey). Source: `legacy-gwt/dataman_V2/dmPlugin/src` (15 java files, 4217 lines). Har feature/entry ki apni file is folder me hai: SQL, fields, validators, RPC methods auto-extracted; "Notes (hand-written)" jahan likhe hain.
> "Used by" = kitni files (baaki features/modules me) is feature ke package ko import karti hain.

## Overview (hand-written)
`dm-plugin`: **client PC par chalne wala local plugin** ka server hissa (15 java): embedded HTTP server (NanoHTTPD jaisa `serve`, MIME types, SSL socket factory), plugin ping/session models. Browser se local hardware/printing ke liye (naam aur methods se; detail dekhna baaki).

## Summary
| Kind | Features | Lines |
|---|---|---|
| server/shared | 1 | 4217 |

## All features (most used first)
| Feature | Kind | Files | Lines | Services | Used by (files) | Mostly used by | Writes tables |
|---|---|---|---|---|---|---|---|
| [dataman_dmPlugin](dataman_dmPlugin.md) | server/shared | 15 | 4217 | 0 | 16 | stPlugin 16 | - |

## Dependencies (auto from imports of this module's java files)

**Other modules used** (number of import statements): `dm-base` 7, `dm-library-menu` 1

**Most used shared features:**

| Shared feature | Imports |
|---|---|
| `dm-base` / `self.shared` | 5 |
| `dm-base` / `jvmSerialization.shared` | 2 |
