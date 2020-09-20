package com.nexiilabs.stp.bankaccount;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stp_comm_address")
public class AddressModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "comm_address_id")
	private int addressId;
	@Column(name = "address_name")
	private String addressName;
	@Column(name = "address")
	private String address;
	@Column(name = "delete_status")
	private int deleteStatus;
	
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public String getAddressName() {
		return addressName;
	}
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getDeleteStatus() {
		return deleteStatus;
	}
	public void setDeleteStatus(int deleteStatus) {
		this.deleteStatus = deleteStatus;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddressModel [addressId=");
		builder.append(addressId);
		builder.append(", addressName=");
		builder.append(addressName);
		builder.append(", address=");
		builder.append(address);
		builder.append(", deleteStatus=");
		builder.append(deleteStatus);
		builder.append("]");
		return builder.toString();
	}
}
