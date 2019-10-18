package ua.edu.ucu.tempseries;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private double[] temperatureSeries;
    private static final double MIN_TEMPERATURE = -273.0;

    public int getSize() {
        return size;
    }

    private int size;

    public TemperatureSeriesAnalysis() {
        double[] series = new double[]{};
        this.temperatureSeries = series;
        size = 0;

    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        this.temperatureSeries = temperatureSeries;
        this.size += temperatureSeries.length;

    }

    public double average() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        }
        else{
            double sum = 0;
            for(double num : temperatureSeries){
                sum+=num;
            }
            return sum / temperatureSeries.length;
        }

    }

    public double deviation() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        }
        else{
            double avg = this.average();
            double sum_of_squares = 0;
            for (double num : temperatureSeries){
                sum_of_squares += (num - avg) * (num - avg);
            }
            return Math.sqrt(sum_of_squares / temperatureSeries.length);

        }

    }

    public double min() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        }
        else{
            double min = temperatureSeries[0];
            for (double num : temperatureSeries){
                if(num < min){
                    min = num;
                }
            }
            return min;

        }
    }

    public double max() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        }
        else{
            double max = temperatureSeries[0];
            for (double num : temperatureSeries){
                if(num > max){
                    max = num;
                }
            }
            return max;

        }
    }

    public double findTempClosestToZero() {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        }
        else{
            double closest = temperatureSeries[0];
            for(double num: temperatureSeries){
                if(Math.abs(num) == Math.abs(closest)){
                    if(closest > 0 || num > 0){
                        closest = Math.abs(closest);
                    }
                }
                if(Math.abs(num) < Math.abs(closest)){
                    closest = num;
                }
            }
            return closest;
        }
    }

    public double findTempClosestToValue(double tempValue) {
        if (temperatureSeries.length == 0) {
            throw new IllegalArgumentException();
        }
        else{
            double closest = temperatureSeries[0];
            for(double num: temperatureSeries){
                if(Math.abs(num - tempValue) == Math.abs(closest - tempValue)){
                    if(closest > 0 || num > 0){
                        closest = Math.max(closest, num);
                    }
                }
                if(Math.abs(num - tempValue) < Math.abs(closest - tempValue)){
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
        for(double num: temperatureSeries){
            if(num < tempValue){
                numValues++;
            }
        }

        double valuesLess[] = new double[numValues];
        final int length = temperatureSeries.length;
        int i = 0;
        for(double num: temperatureSeries){
            if(num < tempValue){
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
        for(double num: temperatureSeries){
            if(num > tempValue){
                numValues++;
            }
        }

        double valuesGreater[] = new double[numValues];
        final int length = temperatureSeries.length;
        int i = 0;
        for(double num: temperatureSeries){
            if(num > tempValue){
                valuesGreater[i++] = num;
            }
        }
        return valuesGreater;
    }
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public TempSummaryStatistics summaryStatistics() {
        return new TempSummaryStatistics(this.average(), this.deviation(), this.min(), this.max());
    }

    private int addTemp(double temp) {
        if (this.temperatureSeries.length <= this.size) {
            double[] tmp = new double[size * 2 + 1];
            if (this.temperatureSeries.length > 0) {
                for(int i = 0; i < size; i++){
                    tmp[i] = this.temperatureSeries[i];
                }
                //System.arraycopy(tmp, 0, this.temperatureSeries, 0, temperatureSeries.length);
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
                throw new InputMismatchException("Temperature below 0 degrees K");
            }
            addTemp(temp);
        }
        return 0;
    }

    public int getAllocatedSize(){
        return this.temperatureSeries.length;
    }





    /*
    public int addTemps(double... temps) {
        int newLength = this.temperatureSeries.length + temps.length;
        int tempsLength = temps.length;
        int seriesLength = this.temperatureSeries.length;
        double[] newTempSeries = new double[newLength];
        for(int i = 0; i < newLength; i++){
            if(i < seriesLength){
                newTempSeries[i] = this.temperatureSeries[i];
            }
            else{
                newTempSeries[i] = temps[i - tempsLength];
            }
        }
        this.temperatureSeries = newTempSeries;
        return 0;

    }

     */

}
