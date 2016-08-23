package test.com.money.model;

import java.util.Date;

public class WeChat {
    private Long wechatId;

    private String wechatAccount;

    private String wechatName;

    private String wechatUrl;

    private Date addTime;

    private Short state;

    private byte[] wechatPic;

    public Long getWechatId() {
        return wechatId;
    }

    public void setWechatId(Long wechatId) {
        this.wechatId = wechatId;
    }

    public String getWechatAccount() {
        return wechatAccount;
    }

    public void setWechatAccount(String wechatAccount) {
        this.wechatAccount = wechatAccount == null ? null : wechatAccount.trim();
    }

    public String getWechatName() {
        return wechatName;
    }

    public void setWechatName(String wechatName) {
        this.wechatName = wechatName == null ? null : wechatName.trim();
    }

    public String getWechatUrl() {
        return wechatUrl;
    }

    public void setWechatUrl(String wechatUrl) {
        this.wechatUrl = wechatUrl == null ? null : wechatUrl.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Short getState() {
        return state;
    }

    public void setState(Short state) {
        this.state = state;
    }

    public byte[] getWechatPic() {
        return wechatPic;
    }

    public void setWechatPic(byte[] wechatPic) {
        this.wechatPic = wechatPic;
    }
}