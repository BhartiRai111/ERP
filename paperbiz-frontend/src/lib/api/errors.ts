import type { ApiErrorBody } from "./types";

/** A failed API call. `errorNumber` is 0 when the backend gave none (network, 5xx without body). */
export class ApiRequestError extends Error {
  readonly status: number;
  readonly errorNumber: number;

  constructor(status: number, errorNumber: number, message: string) {
    super(message);
    this.name = "ApiRequestError";
    this.status = status;
    this.errorNumber = errorNumber;
  }
}

export function isApiErrorBody(value: unknown): value is ApiErrorBody {
  return (
    typeof value === "object" &&
    value !== null &&
    typeof (value as ApiErrorBody).message === "string" &&
    typeof (value as ApiErrorBody).errorNumber === "number"
  );
}
