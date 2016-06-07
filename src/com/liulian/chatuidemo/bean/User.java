package com.liulian.chatuidemo.bean;


/**
 * User entity. @author MyEclipse Persistence Tools
 */
public class User extends Location implements java.io.Serializable {
	private static final long serialVersionUID = 6848921231724157394L;

	// Fields

	/**
	 * 
	 */
	Integer id;
	String username;
	String sex;
	String head_pic;
	String mobile;
	String hx_username;
	String hx_password;
	String reg_time;
	String accesskey;
	String city;
	String state;
	String birthday;
	String star;
	String pics;
	String signature;
	String back_pic;
	String test_active_days;
	String age;
	String user_level;
	String user_lever_icon;
	String next_level_days;
	String left_days;
	String level_parcent;
	String is_can_talk;
	String is_sayhi;

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", sex='" + sex + '\'' +
				", head_pic='" + head_pic + '\'' +
				", mobile='" + mobile + '\'' +
				", hx_username='" + hx_username + '\'' +
				", hx_password='" + hx_password + '\'' +
				", reg_time='" + reg_time + '\'' +
				", accesskey='" + accesskey + '\'' +
				", city='" + city + '\'' +
				", state='" + state + '\'' +
				", birthday='" + birthday + '\'' +
				", star='" + star + '\'' +
				", pics='" + pics + '\'' +
				", signature='" + signature + '\'' +
				", back_pic='" + back_pic + '\'' +
				", test_active_days='" + test_active_days + '\'' +
				", age='" + age + '\'' +
				", user_level='" + user_level + '\'' +
				", user_lever_icon='" + user_lever_icon + '\'' +
				", next_level_days='" + next_level_days + '\'' +
				", left_days='" + left_days + '\'' +
				", level_parcent='" + level_parcent + '\'' +
				", is_can_talk='" + is_can_talk + '\'' +
				", is_sayhi='" + is_sayhi + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof User)) return false;

		User user = (User) o;

		if (getId() != null ? !getId().equals(user.getId()) : user.getId() != null) return false;
		if (getUsername() != null ? !getUsername().equals(user.getUsername()) : user.getUsername() != null)
			return false;
		if (getSex() != null ? !getSex().equals(user.getSex()) : user.getSex() != null)
			return false;
		if (getHead_pic() != null ? !getHead_pic().equals(user.getHead_pic()) : user.getHead_pic() != null)
			return false;
		if (getMobile() != null ? !getMobile().equals(user.getMobile()) : user.getMobile() != null)
			return false;
		if (getHx_username() != null ? !getHx_username().equals(user.getHx_username()) : user.getHx_username() != null)
			return false;
		if (getHx_password() != null ? !getHx_password().equals(user.getHx_password()) : user.getHx_password() != null)
			return false;
		if (getReg_time() != null ? !getReg_time().equals(user.getReg_time()) : user.getReg_time() != null)
			return false;
		if (getAccesskey() != null ? !getAccesskey().equals(user.getAccesskey()) : user.getAccesskey() != null)
			return false;
		if (getCity() != null ? !getCity().equals(user.getCity()) : user.getCity() != null)
			return false;
		if (getState() != null ? !getState().equals(user.getState()) : user.getState() != null)
			return false;
		if (getBirthday() != null ? !getBirthday().equals(user.getBirthday()) : user.getBirthday() != null)
			return false;
		if (getStar() != null ? !getStar().equals(user.getStar()) : user.getStar() != null)
			return false;
		if (getPics() != null ? !getPics().equals(user.getPics()) : user.getPics() != null)
			return false;
		if (getSignature() != null ? !getSignature().equals(user.getSignature()) : user.getSignature() != null)
			return false;
		if (getBack_pic() != null ? !getBack_pic().equals(user.getBack_pic()) : user.getBack_pic() != null)
			return false;
		if (getTest_active_days() != null ? !getTest_active_days().equals(user.getTest_active_days()) : user.getTest_active_days() != null)
			return false;
		if (getAge() != null ? !getAge().equals(user.getAge()) : user.getAge() != null)
			return false;
		if (getUser_level() != null ? !getUser_level().equals(user.getUser_level()) : user.getUser_level() != null)
			return false;
		if (getUser_lever_icon() != null ? !getUser_lever_icon().equals(user.getUser_lever_icon()) : user.getUser_lever_icon() != null)
			return false;
		if (getNext_level_days() != null ? !getNext_level_days().equals(user.getNext_level_days()) : user.getNext_level_days() != null)
			return false;
		if (getLeft_days() != null ? !getLeft_days().equals(user.getLeft_days()) : user.getLeft_days() != null)
			return false;
		if (getLevel_parcent() != null ? !getLevel_parcent().equals(user.getLevel_parcent()) : user.getLevel_parcent() != null)
			return false;
		if (getIs_can_talk() != null ? !getIs_can_talk().equals(user.getIs_can_talk()) : user.getIs_can_talk() != null)
			return false;
		return getIs_sayhi() != null ? getIs_sayhi().equals(user.getIs_sayhi()) : user.getIs_sayhi() == null;

	}

	@Override
	public int hashCode() {
		int result = getId() != null ? getId().hashCode() : 0;
		result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
		result = 31 * result + (getSex() != null ? getSex().hashCode() : 0);
		result = 31 * result + (getHead_pic() != null ? getHead_pic().hashCode() : 0);
		result = 31 * result + (getMobile() != null ? getMobile().hashCode() : 0);
		result = 31 * result + (getHx_username() != null ? getHx_username().hashCode() : 0);
		result = 31 * result + (getHx_password() != null ? getHx_password().hashCode() : 0);
		result = 31 * result + (getReg_time() != null ? getReg_time().hashCode() : 0);
		result = 31 * result + (getAccesskey() != null ? getAccesskey().hashCode() : 0);
		result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
		result = 31 * result + (getState() != null ? getState().hashCode() : 0);
		result = 31 * result + (getBirthday() != null ? getBirthday().hashCode() : 0);
		result = 31 * result + (getStar() != null ? getStar().hashCode() : 0);
		result = 31 * result + (getPics() != null ? getPics().hashCode() : 0);
		result = 31 * result + (getSignature() != null ? getSignature().hashCode() : 0);
		result = 31 * result + (getBack_pic() != null ? getBack_pic().hashCode() : 0);
		result = 31 * result + (getTest_active_days() != null ? getTest_active_days().hashCode() : 0);
		result = 31 * result + (getAge() != null ? getAge().hashCode() : 0);
		result = 31 * result + (getUser_level() != null ? getUser_level().hashCode() : 0);
		result = 31 * result + (getUser_lever_icon() != null ? getUser_lever_icon().hashCode() : 0);
		result = 31 * result + (getNext_level_days() != null ? getNext_level_days().hashCode() : 0);
		result = 31 * result + (getLeft_days() != null ? getLeft_days().hashCode() : 0);
		result = 31 * result + (getLevel_parcent() != null ? getLevel_parcent().hashCode() : 0);
		result = 31 * result + (getIs_can_talk() != null ? getIs_can_talk().hashCode() : 0);
		result = 31 * result + (getIs_sayhi() != null ? getIs_sayhi().hashCode() : 0);
		return result;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getHead_pic() {
		return head_pic;
	}

	public void setHead_pic(String head_pic) {
		this.head_pic = head_pic;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getHx_username() {
		return hx_username;
	}

	public void setHx_username(String hx_username) {
		this.hx_username = hx_username;
	}

	public String getHx_password() {
		return hx_password;
	}

	public void setHx_password(String hx_password) {
		this.hx_password = hx_password;
	}

	public String getReg_time() {
		return reg_time;
	}

	public void setReg_time(String reg_time) {
		this.reg_time = reg_time;
	}

	public String getAccesskey() {
		return accesskey;
	}

	public void setAccesskey(String accesskey) {
		this.accesskey = accesskey;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	public String getPics() {
		return pics;
	}

	public void setPics(String pics) {
		this.pics = pics;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getBack_pic() {
		return back_pic;
	}

	public void setBack_pic(String back_pic) {
		this.back_pic = back_pic;
	}

	public String getTest_active_days() {
		return test_active_days;
	}

	public void setTest_active_days(String test_active_days) {
		this.test_active_days = test_active_days;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getUser_level() {
		return user_level;
	}

	public void setUser_level(String user_level) {
		this.user_level = user_level;
	}

	public String getUser_lever_icon() {
		return user_lever_icon;
	}

	public void setUser_lever_icon(String user_lever_icon) {
		this.user_lever_icon = user_lever_icon;
	}

	public String getNext_level_days() {
		return next_level_days;
	}

	public void setNext_level_days(String next_level_days) {
		this.next_level_days = next_level_days;
	}

	public String getLeft_days() {
		return left_days;
	}

	public void setLeft_days(String left_days) {
		this.left_days = left_days;
	}

	public String getLevel_parcent() {
		return level_parcent;
	}

	public void setLevel_parcent(String level_parcent) {
		this.level_parcent = level_parcent;
	}

	public String getIs_can_talk() {
		return is_can_talk;
	}

	public void setIs_can_talk(String is_can_talk) {
		this.is_can_talk = is_can_talk;
	}

	public String getIs_sayhi() {
		return is_sayhi;
	}

	public void setIs_sayhi(String is_sayhi) {
		this.is_sayhi = is_sayhi;
	}
}