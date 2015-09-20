package net.blaklizt.symbiosis.sym_persistence.complex_type;

import net.blaklizt.symbiosis.sym_persistence.enumeration.symbiosis_channel;
import net.blaklizt.symbiosis.sym_persistence.enumeration.symbiosis_user_status;
import net.blaklizt.symbiosis.sym_persistence.helper.symbiosis_entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "symbiosis_auth_user")
public class symbiosis_auth_user extends symbiosis_entity
{
    private Long symbiosis_user_id;
    private Integer symbiosis_channel_id;
    private Integer symbiosis_user_status_id;
    private String device_id;
    private Integer access_system_id;
    private Date registration_date;
    private Date last_auth_date;
    private Date last_login_date;
    private symbiosis_channel symbiosis_channel;
    private symbiosis_user_status symbiosis_user_status;
    private symbiosis_user symbiosis_user;

    @Column(name = "symbiosis_user_id")
    @Basic
    public Long get_symbiosis_user_id() {
        return symbiosis_user_id;
    }

    public void set_symbiosis_user_id(Long symbiosis_user_id) {
        this.symbiosis_user_id = symbiosis_user_id;
    }

    @Column(name = "symbiosis_channel_id")
    @Basic
    public Integer get_symbiosis_channel_id() {
        return symbiosis_channel_id;
    }

    public void set_symbiosis_channel_id(Integer symbiosis_channel_id) {
        this.symbiosis_channel_id = symbiosis_channel_id;
    }

    @Column(name = "symbiosis_user_status_id")
    @Basic
    public Integer get_symbiosis_user_status_id() {
        return symbiosis_user_status_id;
    }

    public void set_symbiosis_user_status_id(Integer symbiosis_user_status_id) {
        this.symbiosis_user_status_id = symbiosis_user_status_id;
    }

    @Column(name = "device_id")
    @Basic
    public String get_device_id() {
        return device_id;
    }

    public void set_device_id(String device_id) {
        this.device_id = device_id;
    }

    @Column(name = "access_system_id")
    @Basic
    public Integer get_access_system_id() {
        return access_system_id;
    }

    public void set_access_system_id(int access_system_id) {
        this.access_system_id = access_system_id;
    }

    @Column(name = "registration_date")
    @Basic
    public Date get_registration_date() {
        return registration_date;
    }

    public void set_registration_date(Date registration_date) {
        this.registration_date = registration_date;
    }

    @Column(name = "last_auth_date")
    @Basic
    public Date get_last_auth_date() {
        return last_auth_date;
    }

    public void set_last_auth_date(Date last_auth_date) {
        this.last_auth_date = last_auth_date;
    }

    @Column(name = "last_login_date")
    @Basic
    public Date get_last_login_date() {
        return last_login_date;
    }

    public void set_last_login_date(Date last_login_date) {
        this.last_login_date = last_login_date;
    }

    @JoinTable(name="symbiosis_user_status")
    @JoinColumn(name="id", insertable = false, updatable = false)
    @ManyToOne(targetEntity = symbiosis_user_status.class, fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    public symbiosis_user_status get_user_status() {
        return symbiosis_user_status;
    }

    public void set_user_status(symbiosis_user_status symbiosis_user_status) {
        this.symbiosis_user_status = symbiosis_user_status;
    }

    @JoinTable(name="symbiosis_channel")
    @JoinColumn(name="id", insertable = false, updatable = false)
    @ManyToOne(targetEntity = symbiosis_channel.class, fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    public symbiosis_channel get_channel() {
        return symbiosis_channel;
    }

    public void set_channel(symbiosis_channel symbiosis_channel) {
        this.symbiosis_channel = symbiosis_channel;
    }


    @JoinTable(name="symbiosis_user")
    @JoinColumn(name="id", insertable = false, updatable = false)
    @ManyToOne(targetEntity = symbiosis_user.class, fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    public symbiosis_user get_symbiosis_user() {
        return symbiosis_user;
    }

    public void set_symbiosis_user(symbiosis_user symbiosis_user) {
        this.symbiosis_user = symbiosis_user;
    }
}