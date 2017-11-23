export interface DbResponse<T> {
    total_rows: number;
    offset: number;
    rows: Row<T>[];
}

export interface Row<T> {
    id: string;
    key: string;
    value: Value;
    doc: T;
}

export interface Value {
    rev: string;
}
