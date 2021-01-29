package com.codegym.entity;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
public class Customer {

    @Id
    @GeneratedValue(generator = "my_generator")
    @GenericGenerator(name = "my_generator", strategy = "com.codegym.common.MyGenerator")
    @Column(name="id")
    private String id;

    @NotBlank(message = "vui lòng nhập họ tên nhé")
    @Pattern(regexp = "^([\\p{Lu}][\\p{Ll}]{1,8})(\\s([\\p{Lu}]|[\\p{Lu}][\\p{Ll}]{1,10})){0,6}$",
            message = " nhập họ tên vào bạn nhé viết hoa chữ cái đầu vào nha bạn ")
    private String name;

    @NotBlank(message = "vui lòng ko để trống nhé")
    @Email(message = "sai định dạng ! vidu: abc@abc.com.vn")
    private String email;

    @NotBlank(message = "vui lòng nhập ngày tháng năm sinh nhé")
    private String birthday;

    @NotBlank(message = "nhập số điện thoại bạn nhé")
    @Pattern(regexp = "^(090|091|\\(84\\)(\\+90|\\+91))(\\d{7})$",
            message = "sai định dạng !  090xxxxxxx || 091xxxxxxx || (84)+90xxxxxxx || (84)+91xxxxxxx (x: 0-9)")
    private String phone;
//    ------------------------------------ Tạo Mối Quan Hệ ---------------------------------------------
    @ManyToOne
    @JoinColumn(name = "type_customer_id", referencedColumnName = "id")
    private TypeCustomer typeCustomer;

    @ManyToOne
    @JoinColumn(name = "province_id", referencedColumnName = "id")
    private Province province;
//    ---------------------------------------------------------------------------------------------------


    public Customer() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public TypeCustomer getTypeCustomer() {
        return typeCustomer;
    }

    public void setTypeCustomer(TypeCustomer typeCustomer) {
        this.typeCustomer = typeCustomer;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }
}
