package net.blaklizt.symbiosis.sym_persistence.entity.complex_type;

import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_channel;
import net.blaklizt.symbiosis.sym_persistence.entity.enumeration.symbiosis_user_status;
import net.blaklizt.symbiosis.sym_persistence.entity.super_class.symbiosis_entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class symbiosis_auth_user extends symbiosis_entity
{
    private Long symbiosis_user_id;
    private Long symbiosis_channel_id;
    private Long symbiosis_user_status_id;
    private String device_id;
    private Long access_system_id;
    private Date registration_date;
    private Date last_auth_date;
    private Date last_login_date;
    private symbiosis_channel channel;
    private symbiosis_user_status user_status;
    private symbiosis_user user;

    public symbiosis_auth_user() {}

    public symbiosis_auth_user(
            Long symbiosis_user_id,
            Long symbiosis_channel_id,
            Long symbiosis_user_status_id,
            String device_id,
            Long access_system_id,
            Date registration_date,
            Date last_auth_date,
            Date last_login_date) {
        this.symbiosis_user_id = symbiosis_user_id;
        this.symbiosis_channel_id = symbiosis_channel_id;
        this.symbiosis_user_status_id = symbiosis_user_status_id;
        this.device_id = device_id;
        this.access_system_id = access_system_id;
        this.registration_date = registration_date;
        this.last_auth_date = last_auth_date;
        this.last_login_date = last_login_date;
    }

    @Column(name = "symbiosis_user_id")
    @Basic
    public Long getSymbiosis_user_id() {
        return symbiosis_user_id;
    }

    public void setSymbiosis_user_id(Long symbiosis_user_id) {
        this.symbiosis_user_id = symbiosis_user_id;
    }

    @Column(name = "symbiosis_channel_id")
    @Basic
    public Long getSymbiosis_channel_id() {
        return symbiosis_channel_id;
    }

    public void setSymbiosis_channel_id(Long symbiosis_channel_id) {
        this.symbiosis_channel_id = symbiosis_channel_id;
    }

    @Column(name = "symbiosis_user_status_id")
    @Basic
    public Long getSymbiosis_user_status_id() {
        return symbiosis_user_status_id;
    }

    public void setSymbiosis_user_status_id(Long symbiosis_user_status_id) {
        this.symbiosis_user_status_id = symbiosis_user_status_id;
    }

    @Column(name = "device_id")
    @Basic
    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    @Column(name = "access_system_id")
    @Basic
    public Long getAccess_system_id() {
        return access_system_id;
    }

    public void setAccess_system_id(Long access_system_id) {
        this.access_system_id = access_system_id;
    }

    @Column(name = "registration_date")
    @Basic
    public Date getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(Date registration_date) {
        this.registration_date = registration_date;
    }

    @Column(name = "last_auth_date")
    @Basic
    public Date getLast_auth_date() {
        return last_auth_date;
    }

    public void setLast_auth_date(Date last_auth_date) {
        this.last_auth_date = last_auth_date;
    }

    @Column(name = "last_login_date")
    @Basic
    public Date getLast_login_date() {
        return last_login_date;
    }

    public void setLast_login_date(Date last_login_date) {
        this.last_login_date = last_login_date;
    }

//    @JoinTable(name = "symbiosis_user_status")
////    @JoinColumn(name = "id", referencedColumnName="symbiosis_user_status_id")
//    @ManyToOne(targetEntity = symbiosis_user_status.class, fetch=FetchType.LAZY, cascade = CascadeType.ALL)
//    public symbiosis_user_status getUser_status() {
//        return user_status;
//    }
//
//    public void setUser_status(symbiosis_user_status user_status) {
//        this.user_status = user_status;
//    }
//
//    @JoinTable(name = "symbiosis_channel")
//    @JoinColumn(name ="id", referencedColumnName="symbiosis_channel_id")
//    @ManyToOne(targetEntity = symbiosis_channel.class, fetch=FetchType.LAZY, cascade = CascadeType.ALL)
//    public symbiosis_channel getChannel() {
//        return channel;
//    }
//
//    public void setChannel(symbiosis_channel channel) {
//        this.channel = channel;
//    }
//
//    @JoinTable(name = "symbiosis_user")
////    @JoinColumn(name ="id", referencedColumnName="symbiosis_user_id")
//    @ManyToOne(targetEntity = symbiosis_user.class, fetch=FetchType.LAZY, cascade = CascadeType.ALL)
//    public symbiosis_user getUser() {
//        return user;
//    }

//    public void setUser(symbiosis_user user) {
//        this.user = user;
//    }
}