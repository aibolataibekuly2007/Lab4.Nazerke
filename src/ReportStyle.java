public class ReportStyle {
    private String backgroundColor;
    private String fontColor;
    private int fontSize;
    private String fontFamily;
    private boolean bold;
    private boolean italic;

    public ReportStyle() {
        this.backgroundColor = "#FFFFFF";
        this.fontColor = "#000000";
        this.fontSize = 12;
        this.fontFamily = "Arial";
        this.bold = false;
        this.italic = false;
    }

    public ReportStyle(String backgroundColor, String fontColor, int fontSize) {
        this.backgroundColor = backgroundColor;
        this.fontColor = fontColor;
        this.fontSize = fontSize;
        this.fontFamily = "Arial";
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    public boolean isBold() {
        return bold;
    }

    public void setBold(boolean bold) {
        this.bold = bold;
    }

    public boolean isItalic() {
        return italic;
    }

    public void setItalic(boolean italic) {
        this.italic = italic;
    }
}