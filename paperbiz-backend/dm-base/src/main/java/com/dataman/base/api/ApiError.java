package com.dataman.base.api;

/** Error body returned for every failed request. */
public record ApiError(int errorNumber, String message) {
}
