package gupiao;

public class Zhangting {
    public String label;
    public String name;
    public String lianban;
    public String res;
    public String url;
    public String bianma;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBianma() {
        return bianma;
    }

    public void setBianma(String bianma) {
        this.bianma = bianma;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLianban() {
        return lianban;
    }

    public void setLianban(String lianban) {
        this.lianban = lianban;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    @Override
    public String toString() {
        return "Zhangting{" +
                "label='" + label + '\'' +
                ", name='" + name + '\'' +
                ", lianban='" + lianban + '\'' +
                ", res='" + res + '\'' +
                '}';
    }
}
