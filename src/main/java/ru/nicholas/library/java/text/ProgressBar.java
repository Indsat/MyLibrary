package ru.nicholas.java.text;

public class ProgressBar {

    private final double current, max;

    private final int count;

    private final String yesColor, noColor, symbol;

    public ProgressBar(double current, double max, int symbolCount) {

        this.current = current;

        this.max = max;

        this.count = symbolCount;

        this.yesColor = "§a";

        this.noColor = "§7";

        this.symbol = "•";
    }

    public String getProgressBar() {

        double tenPercent = (current / max) * count;

        int percent = (int) Math.round(tenPercent);

        StringBuilder bar = new StringBuilder();

        for (int i = 0; i < percent; i++) {

            bar.append(symbol);
        }
        bar.append(yesColor);

        for (int i = 0; i < count - percent; i++) {

            bar.append(symbol);
        }

        bar.append(noColor);

        return bar.toString();
    }

    public String getPercent() {
        double allPercent = (current / max) * 100;

        return ((int) Math.round(allPercent)) + "%";
    }
}
