package ua.edu.ucu.tempseries;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private static final double MIN_TEMPERATURE = -273.0;
    private static final double PRECISION = .0000001;
    private double[] temperatureSeries;

    private int size;

    public TemperatureSeriesAnalysis() {
        this.temperatureSeries = new double[]{};
        size = 0;

    }

    public TemperatureSeriesAnalysis(double[] tempSeries) {
        temperatureSeries = new double[tempSeries.length];
        System.arraycopy(tempSeries, 0,
                temperatureSeries, 0, tempSeries.length);
        this.size += temperatureSeries.length;

    }

    public int getSize() {
        return size;
    }

    public double average() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        } else {
            double sum = 0;
            for (double num : temperatureSeries) {
                sum += num;
            }
            return sum / temperatureSeries.length;
        }

    }

    public double deviation() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        } else {
            double avg = this.average();
            double sumOfSquares = 0;
            for (double num : temperatureSeries) {
                sumOfSquares += (num - avg) * (num - avg);
            }
            return Math.sqrt(sumOfSquares / temperatureSeries.length);

        }

    }

    public double min() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        } else {
            double min = temperatureSeries[0];
            for (double num : temperatureSeries) {
                if (num < min) {
                    min = num;
                }
            }
            return min;

        }
    }

    public double max() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        } else {
            double max = temperatureSeries[0];
            for (double num : temperatureSeries) {
                if (num > max) {
                    max = num;
                }
            }
            return max;

        }
    }

    public double findTempClosestToZero() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        } else {
            double closest = temperatureSeries[0];
            for (double num : temperatureSeries) {

                if (Math.abs(Math.abs(num) - Math.abs(
                        closest)) < PRECISION && (closest > 0 || num > 0)) {
                    closest = Math.abs(closest);
                }
                if (Math.abs(num) < Math.abs(closest)) {
                    closest = num;
                }
            }
            return closest;
        }
    }

    public double findTempClosestToValue(double tempValue) {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        } else {
            double closest = temperatureSeries[0];
            for (double num : temperatureSeries) {

                if (Math.abs((Math.abs(num - tempValue) - Math.abs(
                        closest - tempValue))) < PRECISION && (
                                closest > 0 || num > 0)) {
                    closest = Math.max(closest, num);

                }
                if (Math.abs(num - tempValue) < Math.abs(closest - tempValue)) {
                    closest = num;
                }
            }
            return closest;
        }
    }

    public double[] findTempsLessThen(double tempValue) {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        }
        int numValues = 0;
        for (double num : temperatureSeries) {
            if (num < tempValue) {
                numValues++;
            }
        }

        double[] valuesLess = new double[numValues];
        int i = 0;
        for (double num : temperatureSeries) {
            if (num < tempValue) {
                valuesLess[i++] = num;
            }
        }
        return valuesLess;

    }

    public double[] findTempsGreaterThen(double tempValue) {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        }
        int numValues = 0;
        for (double num : temperatureSeries) {
            if (num > tempValue) {
                numValues++;
            }
        }

        double[] valuesGreater = new double[numValues];
        int i = 0;
        for (double num : temperatureSeries) {
            if (num > tempValue) {
                valuesGreater[i++] = num;
            }
        }
        return valuesGreater;
    }

    public TempSummaryStatistics summaryStatistics() {
        return new TempSummaryStatistics(
                this.average(), this.deviation(), this.min(), this.max());
    }

    private int addTemp(double temp) {
        if (this.temperatureSeries.length <= this.size) {
            double[] tmp = new double[size * 2 + 1];
            if (this.temperatureSeries.length > 0) {
                for (int i = 0; i < size; i++) {
                    tmp[i] = this.temperatureSeries[i];
                }

            }
            this.temperatureSeries = tmp;

        }
        temperatureSeries[size] = temp;
        size += 1;
        return 0;
    }

    public int addTemps(double... temps) {
        for (double temp : temps) {
            if (temp < MIN_TEMPERATURE) {
                throw new InputMismatchException("Temperature below 0K");
            }
            addTemp(temp);
        }
        return 0;
    }

    public int getAllocatedSize() {
        return this.temperatureSeries.length;
    }

}
