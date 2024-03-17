export interface ChartData {
    JANUARY: number;
    FEBRUARY: number;
    MARCH: number;
    APRIL: number;
    MAY: number;
    JUNE: number;
    JULY: number;
    AUGUST: number;
    SEPTEMBER: number;
    OCTOBER: number;
    NOVEMBER: number;
    DECEMBER: number;
}

export interface MapData {
    name: string;
    latitude: number;
    longitude: number;
    color: string;
    shape: string;
    population: number;
}

export interface MonthData {
    date: string[];
    count: number;
}